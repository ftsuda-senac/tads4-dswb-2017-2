/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.tadsstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author fernando.tsuda
 */
@Controller
public class TesteController {
  
  @RequestMapping("/teste")
  public ModelAndView testeRoles() {
    return new ModelAndView("teste");
  }
  
  @RequestMapping("/teste-ajax-ws")
  public ModelAndView testeAjax() {
    return new ModelAndView("exemplos-jquery");
  }
  
}
