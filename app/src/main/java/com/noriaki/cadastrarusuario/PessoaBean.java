package com.noriaki.cadastrarusuario;

import java.io.Serializable;

/**
 * Created by Noriaki on 01/02/2015.
 */
public class PessoaBean implements Serializable {
    private String usuario;
    private String email;
    private String cpf;
    private String senha;

    public PessoaBean(String usuario, String email, String cpf, String senha) {
        this.usuario = usuario;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
