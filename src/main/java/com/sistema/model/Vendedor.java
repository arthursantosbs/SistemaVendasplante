package com.sistema.model;

/**
 * Classe que representa um Vendedor no sistema.
 * Herda da classe abstrata Usuario e implementa funcionalidades específicas de vendedor.
 */
public class Vendedor extends Usuario {
    private double comissao;
    private int totalVendas;
    
    /**
     * Construtor padrão
     */
    public Vendedor() {
        super();
        this.comissao = 0.0;
        this.totalVendas = 0;
    }
    
    /**
     * Construtor com parâmetros
     * 
     * @param id Identificador único do vendedor
     * @param nome Nome completo do vendedor
     * @param email Email do vendedor
     * @param senha Senha do vendedor
     * @param comissao Percentual de comissão do vendedor
     */
    public Vendedor(int id, String nome, String email, String senha, double comissao) {
        super(id, nome, email, senha);
        this.comissao = comissao;
        this.totalVendas = 0;
    }
    
    /**
     * Realiza uma venda de produto
     * 
     * @param produto Produto a ser vendido
     * @param quantidade Quantidade a ser vendida
     * @return true se a venda for bem-sucedida, false caso contrário
     */
    public boolean realizarVenda(Produto produto, int quantidade) {
        if (produto == null || quantidade <= 0 || produto.getQuantidadeEstoque() < quantidade) {
            return false;
        }
        
        if (produto.reduzirEstoque(quantidade)) {
            this.totalVendas++;
            return true;
        }
        
        return false;
    }
    
    /**
     * Calcula a comissão do vendedor sobre uma venda
     * 
     * @param valorVenda Valor total da venda
     * @return Valor da comissão
     */
    public double calcularComissao(double valorVenda) {
        return valorVenda * (comissao / 100.0);
    }
    
    @Override
    public void executarOperacao() {
        System.out.println("Vendedor " + getNome() + " está realizando operações de venda.");
    }
    
    // Getters e Setters
    public double getComissao() {
        return comissao;
    }
    
    public void setComissao(double comissao) {
        this.comissao = comissao;
    }
    
    public int getTotalVendas() {
        return totalVendas;
    }
    
    public void setTotalVendas(int totalVendas) {
        this.totalVendas = totalVendas;
    }
    
    @Override
    public String toString() {
        return "Vendedor [id=" + getId() + ", nome=" + getNome() + ", email=" + getEmail() 
               + ", comissao=" + comissao + "%, totalVendas=" + totalVendas + "]";
    }
}
