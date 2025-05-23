package com.sistema.model;

/**
 * Classe abstrata que representa um usuário no sistema.
 * Serve como base para os diferentes tipos de usuários.
 */
public abstract class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private boolean ativo;

    /**
     * Construtor padrão
     */
    public Usuario() {
        this.ativo = true;
    }

    /**
     * Construtor com parâmetros
     * 
     * @param id Identificador único do usuário
     * @param nome Nome completo do usuário
     * @param email Email do usuário
     * @param senha Senha do usuário
     */
    public Usuario(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.ativo = true;
    }

    /**
     * Método abstrato que define as operações específicas de cada tipo de usuário
     */
    public abstract void executarOperacao();

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", ativo=" + ativo + "]";
    }
}
