/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.tadsstore.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author fernando.tsuda
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView mostrarTela() {
    return new ModelAndView("upload");
  }

  @RequestMapping(method = RequestMethod.POST)
  public ModelAndView uploadArquivo(
          @RequestParam("arquivo") MultipartFile arquivo,
          RedirectAttributes redirectAttributes) {
    if (arquivo.isEmpty()) {
      redirectAttributes.addFlashAttribute("mensagemErro",
              "Erro ao carregar arquivo");
      return new ModelAndView("redirect:/upload");
    }

    try {
      byte[] bytes = arquivo.getBytes();
      Path destino = Paths.get("C:/desenv/uploads/"
              + arquivo.getOriginalFilename());
      Files.write(destino, bytes);

      redirectAttributes.addFlashAttribute("mensagem",
              "Arquivo " + arquivo.getOriginalFilename()
              + " carregado com sucesso");
      return new ModelAndView("redirect:/upload");
    } catch (IOException ex) {
      redirectAttributes.addFlashAttribute("mensagemErro",
              "Erro ao carregar arquivo - " + ex.getMessage());
      return new ModelAndView("redirect:/upload");
    }
  }

}
