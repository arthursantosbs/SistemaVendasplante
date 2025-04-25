package com.sistema.model;

/**
 * Classe abstrata que representa um produto no sistema.
 * Serve como base para os diferentes tipos de produtos.
 */
public abstract class Produto {
    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private int quantidadeEstoque;
    
    /**
     * Construtor padrão
     */
    public Produto() {
    }
    
    /**
     * Construtor com parâmetros
     * 
     * @param id Identificador único do produto
     * @param nome Nome do produto
     * @param descricao Descrição detalhada do produto
     * @param preco Preço unitário do produto
     * @param quantidadeEstoque Quantidade disponível em estoque
     */
    public Produto(int id, String nome, String descricao, double preco, int quantidadeEstoque) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }
    
    /**
     * Método abstrato que define características específicas de cada tipo de produto
     * @return Informações específicas do tipo de produto
     */
    public abstract String getInformacoesEspecificas();
    
    /**
     * Método para atualizar o estoque após uma venda
     * @param quantidade Quantidade vendida
     * @return true se a operação foi bem-sucedida, false caso contrário
     */
    public boolean reduzirEstoque(int quantidade) {
        if (quantidade <= 0 || quantidade > this.quantidadeEstoque) {
            return false;
        }
        this.quantidadeEstoque -= quantidade;
        return true;
    }
    
    /**
     * Método para adicionar produtos ao estoque
     * @param quantidade Quantidade a ser adicionada
     * @return true se a operação foi bem-sucedida, false caso contrário
     */
    public boolean adicionarEstoque(int quantidade) {
        if (quantidade <= 0) {
            return false;
        }
        this.quantidadeEstoque += quantidade;
        return true;
    }
    
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
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public double getPreco() {
        return preco;
    }
    
    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }
    
    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
    
    @Override
    public String toString() {
        return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + ", quantidadeEstoque=" + quantidadeEstoque + "]";
    }
}
