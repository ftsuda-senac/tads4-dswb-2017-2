/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.tadsstore.common;

import javax.persistence.Persistence;

/**
 *
 * @author fernando.tsuda
 */
public class GerarBD {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Persistence.generateSchema("TADSSTORE_PU", null);
  }
  
}
