/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.exemplosspringjar.controller;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author fernando.tsuda
 */
public class Produto {

  private String nome;

  private BigDecimal preco;

  private Date dtCadastro;

  public Produto() {
  }

  public Produto(String nome, BigDecimal preco, Date dtCadastro) {
    this.nome = nome;
    this.preco = preco;
    this.dtCadastro = dtCadastro;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public BigDecimal getPreco() {
    return preco;
  }

  public void setPreco(BigDecimal preco) {
    this.preco = preco;
  }

  public Date getDtCadastro() {
    return dtCadastro;
  }

  public void setDtCadastro(Date dtCadastro) {
    this.dtCadastro = dtCadastro;
  }

}
