/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.InstituicaoDAO;
import dao.UsuarioDAO;
import util.TipoPessoa;

/**
 *
 * @author lucas
 */
public class Teste {
    
    public static void main(String[] args) {
        
        InstituicaoDAO iDao = InstituicaoDAO.getInstance();
        UsuarioDAO uDao = UsuarioDAO.getInstance();
        Instituicao i = new Instituicao();
        i.setNome("Cefet");
        iDao.create(i);
        Usuario user = new Usuario();
        user.setInstituicao(i);
        user.setSenha("123");
        user.setTipo(TipoPessoa.ADM);
        user.setEmail("lucashsilva99@gmail.com");
        uDao.create(user);
        
    }
    
}
