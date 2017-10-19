/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.tadsstore.repository;

import br.senac.tads4.dsw.tadsstore.common.entity.Categoria;
import br.senac.tads4.dsw.tadsstore.common.entity.Produto;
import br.senac.tads4.dsw.tadsstore.common.service.ProdutoService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fernando.tsuda
 */
//@Service
public class ProdutoServiceSpringImpl implements ProdutoService {
  
  @Autowired
  private ProdutoRepository repo;

  @Override
  public List<Produto> listar(int offset, int quantidade) {
    Iterable<Produto> produtos = repo.findAll();
    Iterator it = produtos.iterator();
    List<Produto> lista = new ArrayList<Produto>();
    while(it.hasNext()) {
      Produto p = (Produto) it.next();
      lista.add(p);
    }
    return lista;
    
  }

  @Override
  public List<Produto> listarPorCategoria(Categoria categoria, int offset, int quantidade) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Produto obter(long idProduto) {
    return repo.findOne(idProduto);
  }

  @Override
  public void incluir(Produto p) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void alterar(Produto p) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void remover(long idProduto) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
}
