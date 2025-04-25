package com.sistema.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa o sistema principal da aplicação.
 * Gerencia usuários, produtos e operações do sistema.
 */
public class Sistema {
    private static Sistema instancia;
    private List<Usuario> usuarios;
    private List<Produto> produtos;
    private Usuario usuarioLogado;
    
    /**
     * Construtor privado para implementar o padrão Singleton
     */
    private Sistema() {
        this.usuarios = new ArrayList<>();
        this.produtos = new ArrayList<>();
        this.usuarioLogado = null;
    }
    
    /**
     * Método para obter a instância única do sistema (Singleton)
     * @return Instância única do sistema
     */
    public static Sistema getInstancia() {
        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }
    
    /**
     * Método para realizar login no sistema
     * @param email Email do usuário
     * @param senha Senha do usuário
     * @return true se o login for bem-sucedido, false caso contrário
     */
    public boolean login(String email, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha) && usuario.isAtivo()) {
                this.usuarioLogado = usuario;
                return true;
            }
        }
        return false;
    }
    
    /**
     * Método para realizar logout do sistema
     */
    public void logout() {
        this.usuarioLogado = null;
    }
    
    /**
     * Método para cadastrar um novo usuário no sistema
     * @param usuario Usuário a ser cadastrado
     * @return true se o cadastro for bem-sucedido, false caso contrário
     */
    public boolean cadastrarUsuario(Usuario usuario) {
        // Verifica se já existe um usuário com o mesmo email
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(usuario.getEmail())) {
                return false;
            }
        }
        return usuarios.add(usuario);
    }
    
    /**
     * Método para remover um usuário do sistema
     * @param id ID do usuário a ser removido
     * @return true se a remoção for bem-sucedida, false caso contrário
     */
    public boolean removerUsuario(int id) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == id) {
                usuarios.remove(i);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Método para cadastrar um novo produto no sistema
     * @param produto Produto a ser cadastrado
     * @return true se o cadastro for bem-sucedido, false caso contrário
     */
    public boolean cadastrarProduto(Produto produto) {
        // Verifica se já existe um produto com o mesmo ID
        for (Produto p : produtos) {
            if (p.getId() == produto.getId()) {
                return false;
            }
        }
        return produtos.add(produto);
    }
    
    /**
     * Método para remover um produto do sistema
     * @param id ID do produto a ser removido
     * @return true se a remoção for bem-sucedida, false caso contrário
     */
    public boolean removerProduto(int id) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == id) {
                produtos.remove(i);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Método para buscar um produto pelo ID
     * @param id ID do produto a ser buscado
     * @return Produto encontrado ou null se não existir
     */
    public Produto buscarProdutoPorId(int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }
    
    /**
     * Método para buscar um usuário pelo ID
     * @param id ID do usuário a ser buscado
     * @return Usuário encontrado ou null se não existir
     */
    public Usuario buscarUsuarioPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }
    
    // Getters e Setters
    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }
    
    public List<Produto> getProdutos() {
        return new ArrayList<>(produtos);
    }
    
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
}
