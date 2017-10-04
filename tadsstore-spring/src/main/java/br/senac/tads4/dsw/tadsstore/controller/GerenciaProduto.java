/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.tadsstore.controller;

import br.senac.tads4.dsw.tadsstore.common.entity.Produto;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author fernando.tsuda
 */
@Controller
@RequestMapping("/gerenciamento/produto")
public class GerenciaProduto {
  
  @RequestMapping
  public ModelAndView abrirFormulario() {
    return new ModelAndView("produto/input")
            .addObject("produto", new Produto());
  }
  
  @RequestMapping(value = "/salvar", method = RequestMethod.POST)
  public ModelAndView salvar(
          @ModelAttribute("produto") @Valid Produto p,
          BindingResult bindingResult,
          RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      return new ModelAndView("produto/input");
    }
    redirectAttributes.addFlashAttribute("msgSucesso", 
            "Produto " + p.getNome() + " cadastrado com sucesso");
    return new ModelAndView("redirect:/gerenciamento/produto");
  }
  
}
