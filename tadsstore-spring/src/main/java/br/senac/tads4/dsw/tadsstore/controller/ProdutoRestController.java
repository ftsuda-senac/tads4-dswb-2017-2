/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.tadsstore.controller;

import br.senac.tads4.dsw.tadsstore.common.entity.Categoria;
import br.senac.tads4.dsw.tadsstore.common.entity.Produto;
import br.senac.tads4.dsw.tadsstore.common.service.CategoriaService;
import br.senac.tads4.dsw.tadsstore.common.service.ProdutoService;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author fernando.tsuda
 */
@Controller
@RequestMapping("/rest/produto")
public class ProdutoRestController {

  @Autowired
  private ProdutoService service;

  @Autowired
  private CategoriaService categoriaService;

  @RequestMapping(method = RequestMethod.GET)
  public @ResponseBody
  List<Produto> listar() {
    return service.listar(0, 100);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @CrossOrigin(origins = "*")
  public @ResponseBody
  Produto obter(@PathVariable("id") Long idProd) {
    return service.obter(idProd);
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Produto> salvar(@RequestBody Produto p) {
    if (p != null) {
      p.setDtCadastro(new Date());
      if (p.getIdCategorias() != null && !p.getIdCategorias().isEmpty()) {
        Set<Categoria> categorias = new LinkedHashSet<Categoria>();
        for (Integer i : p.getIdCategorias()) {
          Categoria c = categoriaService.obter(i);
          Set<Produto> produtos = new LinkedHashSet<Produto>();
          produtos.add(p);
          c.setProdutos(produtos);
          categorias.add(c);
        }
        p.setCategorias(categorias);
      }
      service.incluir(p);
      return new ResponseEntity<Produto>(HttpStatus.CREATED);
    }
    return new ResponseEntity<Produto>(HttpStatus.BAD_REQUEST);
  }
}
