package com.sistema.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um Cliente no sistema.
 * Herda da classe abstrata Usuario e implementa funcionalidades específicas de cliente.
 */
public class Cliente extends Usuario {
    private String cpf;
    private String endereco;
    private String telefone;
    private List<Produto> carrinhoCompras;
    private List<Produto> historicoCompras;
    
    /**
     * Construtor padrão
     */
    public Cliente() {
        super();
        this.carrinhoCompras = new ArrayList<>();
        this.historicoCompras = new ArrayList<>();
    }
    
    /**
     * Construtor com parâmetros
     * 
     * @param id Identificador único do cliente
     * @param nome Nome completo do cliente
     * @param email Email do cliente
     * @param senha Senha do cliente
     * @param cpf CPF do cliente
     * @param endereco Endereço do cliente
     * @param telefone Telefone do cliente
     */
    public Cliente(int id, String nome, String email, String senha, String cpf, String endereco, String telefone) {
        super(id, nome, email, senha);
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.carrinhoCompras = new ArrayList<>();
        this.historicoCompras = new ArrayList<>();
    }
    
    /**
     * Adiciona um produto ao carrinho de compras
     * 
     * @param produto Produto a ser adicionado
     * @param quantidade Quantidade a ser adicionada
     * @return true se a operação for bem-sucedida, false caso contrário
     */
    public boolean adicionarAoCarrinho(Produto produto, int quantidade) {
        if (produto == null || quantidade <= 0 || produto.getQuantidadeEstoque() < quantidade) {
            return false;
        }
        
        // Adiciona o produto ao carrinho (simplificado para este exemplo)
        carrinhoCompras.add(produto);
        return true;
    }
    
    /**
     * Remove um produto do carrinho de compras
     * 
     * @param idProduto ID do produto a ser removido
     * @return true se a operação for bem-sucedida, false caso contrário
     */
    public boolean removerDoCarrinho(int idProduto) {
        for (int i = 0; i < carrinhoCompras.size(); i++) {
            if (carrinhoCompras.get(i).getId() == idProduto) {
                carrinhoCompras.remove(i);
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Finaliza a compra dos produtos no carrinho
     * 
     * @return true se a compra for bem-sucedida, false caso contrário
     */
    public boolean finalizarCompra() {
        if (carrinhoCompras.isEmpty()) {
            return false;
        }
        
        // Adiciona os produtos do carrinho ao histórico de compras
        historicoCompras.addAll(carrinhoCompras);
        
        // Limpa o carrinho de compras
        carrinhoCompras.clear();
        
        return true;
    }
    
    /**
     * Calcula o valor total do carrinho de compras
     * 
     * @return Valor total do carrinho
     */
    public double calcularTotalCarrinho() {
        double total = 0.0;
        
        for (Produto produto : carrinhoCompras) {
            total += produto.getPreco();
        }
        
        return total;
    }
    
    @Override
    public void executarOperacao() {
        System.out.println("Cliente " + getNome() + " está realizando operações de compra.");
    }
    
    // Getters e Setters
    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getEndereco() {
        return endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public List<Produto> getCarrinhoCompras() {
        return new ArrayList<>(carrinhoCompras);
    }
    
    public List<Produto> getHistoricoCompras() {
        return new ArrayList<>(historicoCompras);
    }
    
    @Override
    public String toString() {
        return "Cliente [id=" + getId() + ", nome=" + getNome() + ", email=" + getEmail() 
               + ", cpf=" + cpf + ", endereco=" + endereco + ", telefone=" + telefone + "]";
    }
}
