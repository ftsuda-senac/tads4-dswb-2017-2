/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.exemplosspring.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author fernando.tsuda
 */
@Controller
public class ExemploController {

  @RequestMapping("/xpto")
  public String executar() {
    return "exemplo";
  }

  @RequestMapping("/xpto2")
  public String executar2(
          @RequestParam("nome") String nome,
          @RequestParam("idade") int idade,
          Model modelo) {
    modelo.addAttribute("nomeAttr", nome);
    modelo.addAttribute("idadeAttr", idade);
    return "exemplo2";
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
