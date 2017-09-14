/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.exemplosspringjar.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author fernando.tsuda
 */
@Controller
@RequestMapping("/")
public class ExemploController {

  @RequestMapping("/xpto")
  public String executar() {
    return "exemplo";
  }

  @RequestMapping("/xpto2")
  public String executar2(
          @RequestParam("nome") String nome,
          @RequestParam(value = "idade", required = false) Integer idade,
          Model modelo) {
    modelo.addAttribute("nomeAttr", nome);
    modelo.addAttribute("idadeAttr", idade);
    return "exemplo2";
  }

  @RequestMapping("/xpto3/{nomePessoa}")
  public String executar3(
          @PathVariable("nomePessoa") String nome,
          @RequestParam(value = "idade", required = false) Integer idade,
          Model modelo) {
    modelo.addAttribute("nomeAttr", nome);
    modelo.addAttribute("idadeAttr", idade);
    return "exemplo2";
  }

  @RequestMapping("/xpto3mv/{nomePessoa}")
  public ModelAndView executar3MV(
          @PathVariable("nomePessoa") String nome,
          @RequestParam(value = "idade", required = false) Integer idade) {
    ModelAndView resposta = new ModelAndView("exemplo2");
    resposta.addObject("nomeAttr", nome);
    resposta.addObject("idadeAttr", idade);
    return resposta;
  }

  @RequestMapping("/formatadores")
  public ModelAndView formatadores() {
    ModelAndView resposta = new ModelAndView("formatadores");
    resposta.addObject("dataHora", new Date());
    resposta.addObject("moeda", new BigDecimal(3.14));
    resposta.addObject("booleanoV", true);
    resposta.addObject("booleanoF", false);
    resposta.addObject("valorAleatorio", System.currentTimeMillis() % 3);
    return resposta;
  }

  @RequestMapping("/produtos")
  public String listarProdutos(Model modelo) {
    List<Produto> produtos = new ArrayList<>();
    produtos.add(new Produto("produto 1", new BigDecimal(9.99), new Date()));
    produtos.add(new Produto("produto 2", new BigDecimal(5.99), new Date()));
    produtos.add(new Produto("produto 3", new BigDecimal(10.99), new Date()));
    produtos.add(new Produto("produto 4", new BigDecimal(4.59), new Date()));
    produtos.add(new Produto("produto 5", new BigDecimal(12.99), new Date()));
    modelo.addAttribute("lista", produtos);
    return "lista";
  }

}
