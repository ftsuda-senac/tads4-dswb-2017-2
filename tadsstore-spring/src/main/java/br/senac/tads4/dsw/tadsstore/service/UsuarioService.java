/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.tadsstore.service;

import br.senac.tads4.dsw.tadsstore.config.SecurityConfig;
import br.senac.tads4.dsw.tadsstore.model.Papel;
import br.senac.tads4.dsw.tadsstore.model.UsuarioSistema;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author fernando.tsuda
 */
@Service
public class UsuarioService implements UserDetailsService {

  private static final Map<String, UsuarioSistema> USUARIOS;

  static {
    USUARIOS = new LinkedHashMap<String, UsuarioSistema>();
    USUARIOS.put("fulano", new UsuarioSistema("fulano",
            SecurityConfig.passwordEncoder().encode("abcd1234"),
            "Fulano da Silva",
            Arrays.asList(new Papel("ROLE_FODINHA"))));
    USUARIOS.put("ciclano", new UsuarioSistema("ciclano",
            SecurityConfig.passwordEncoder().encode("abcd1234"),
            "Ciclano de Souza",
            Arrays.asList(new Papel("ROLE_FODINHA"), new Papel("ROLE_FODAO"))));
    USUARIOS.put("beltrana", new UsuarioSistema("beltrana",
            SecurityConfig.passwordEncoder().encode("abcd1234"),
            "Beltrana das Cruzes",
            Arrays.asList(new Papel("ROLE_FODINHA"), new Papel("ROLE_FODAO"),
                    new Papel("ROLE_GOD"))));
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return USUARIOS.get(username);
  }

}
