/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.exemplosspringjar.controller;

import br.senac.tads4.dsw.exemplosspringjar.modelo.Pessoa;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author fernando.tsuda
 */
@Controller
@RequestMapping("/pessoa")
public class PessoaController {

  @RequestMapping("/input")
  public ModelAndView mostrarFormulario() {
    ModelAndView resultado = new ModelAndView("formulario");
    resultado.addObject("pessoa", new Pessoa());
    return resultado;
  }
  
  @RequestMapping("/input/{id}")
  public ModelAndView mostrarFormularioEdicao(
          @PathVariable("id") Long id) {
    Pessoa p = new Pessoa();
    p.setNome("Ciclano da Silva");
    p.setIdade(25);
    p.setSexo(1);
    p.setPreferencias(new String[] { "Viagens", "Gastronomia"});
    p.setContato(true);
    return new ModelAndView("formulario").addObject("pessoa", p);
  }
  
  @RequestMapping(value = "/salvar", method = RequestMethod.POST)
  public ModelAndView salvar(@ModelAttribute Pessoa p) {
    return new ModelAndView("resultado").addObject("pessoa", p);
  }

}
