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
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

  @Autowired
  private ProdutoService produtoService;

  @Autowired
  private CategoriaService categoriaService;

  @RequestMapping
  public ModelAndView abrirFormulario() {
    return new ModelAndView("produto/input")
            .addObject("produto", new Produto())
            .addObject("categorias", categoriaService.listar());
  }

  @RequestMapping("/{id}")
  public ModelAndView abrirAlteracao(@PathVariable("id") Long idProduto) {
    Produto p = produtoService.obter(idProduto);
    p.setCategorias(p.getCategorias());
    return new ModelAndView("produto/input")
            .addObject("produto", p)
            .addObject("categorias", categoriaService.listar());
  }

  @RequestMapping(value = "/{id}/remover", method = RequestMethod.POST)
  public ModelAndView removerProduto(@PathVariable("id") Long idProduto) {
    produtoService.remover(idProduto);
    return new ModelAndView("redirect:/gerenciamento/produto");
  }

  @RequestMapping(value = "/salvar", method = RequestMethod.POST)
  public ModelAndView salvar(
          @ModelAttribute("produto") @Valid Produto p,
          BindingResult bindingResult,
          RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      return new ModelAndView("produto/input");
    }
    boolean inclusao = (p.getId() == null);
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
    if (inclusao) {
      produtoService.incluir(p);
    } else {
      produtoService.alterar(p);
    }

    redirectAttributes.addFlashAttribute("msgSucesso",
            "Produto " + p.getNome() + " cadastrado com sucesso");
    return new ModelAndView("redirect:/gerenciamento/produto");
  }

}
