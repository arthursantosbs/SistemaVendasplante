package com.sistema.service;

import java.util.ArrayList;
import java.util.List;
import com.sistema.model.Produto;

/**
 * Classe responsável pelo controle de estoque do sistema.
 * Gerencia a adição, remoção e consulta de produtos no estoque.
 */
public class Estoque {
    private static Estoque instancia;
    private List<Produto> produtosEmEstoque;
    
    /**
     * Construtor privado para implementar o padrão Singleton
     */
    private Estoque() {
        this.produtosEmEstoque = new ArrayList<>();
    }
    
    /**
     * Método para obter a instância única do estoque (Singleton)
     * @return Instância única do estoque
     */
    public static Estoque getInstancia() {
        if (instancia == null) {
            instancia = new Estoque();
        }
        return instancia;
    }
    
    /**
     * Adiciona um produto ao estoque
     * @param produto Produto a ser adicionado
     * @return true se a operação for bem-sucedida, false caso contrário
     */
    public boolean adicionarProduto(Produto produto) {
        if (produto == null) {
            return false;
        }
        
        // Verifica se o produto já existe no estoque
        for (Produto p : produtosEmEstoque) {
            if (p.getId() == produto.getId()) {
                return false;
            }
        }
        
        return produtosEmEstoque.add(produto);
    }
    
    /**
     * Atualiza a quantidade de um produto no estoque
     * @param idProduto ID do produto a ser atualizado
     * @param novaQuantidade Nova quantidade do produto
     * @return true se a operação for bem-sucedida, false caso contrário
     */
    public boolean atualizarQuantidade(int idProduto, int novaQuantidade) {
        if (novaQuantidade < 0) {
            return false;
        }
        
        for (Produto produto : produtosEmEstoque) {
            if (produto.getId() == idProduto) {
                produto.setQuantidadeEstoque(novaQuantidade);
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Remove um produto do estoque
     * @param idProduto ID do produto a ser removido
     * @return true se a operação for bem-sucedida, false caso contrário
     */
    public boolean removerProduto(int idProduto) {
        for (int i = 0; i < produtosEmEstoque.size(); i++) {
            if (produtosEmEstoque.get(i).getId() == idProduto) {
                produtosEmEstoque.remove(i);
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Busca um produto no estoque pelo ID
     * @param idProduto ID do produto a ser buscado
     * @return Produto encontrado ou null se não existir
     */
    public Produto buscarProduto(int idProduto) {
        for (Produto produto : produtosEmEstoque) {
            if (produto.getId() == idProduto) {
                return produto;
            }
        }
        
        return null;
    }
    
    /**
     * Verifica se um produto está disponível no estoque na quantidade desejada
     * @param idProduto ID do produto a ser verificado
     * @param quantidade Quantidade desejada
     * @return true se o produto estiver disponível na quantidade desejada, false caso contrário
     */
    public boolean verificarDisponibilidade(int idProduto, int quantidade) {
        Produto produto = buscarProduto(idProduto);
        
        if (produto == null || quantidade <= 0) {
            return false;
        }
        
        return produto.getQuantidadeEstoque() >= quantidade;
    }
    
    /**
     * Lista todos os produtos em estoque
     * @return Lista de produtos em estoque
     */
    public List<Produto> listarProdutos() {
        return new ArrayList<>(produtosEmEstoque);
    }
    
    /**
     * Obtém a quantidade total de produtos diferentes no estoque
     * @return Quantidade total de produtos diferentes
     */
    public int getTotalProdutosDiferentes() {
        return produtosEmEstoque.size();
    }
    
    /**
     * Obtém a quantidade total de itens no estoque (soma de todas as quantidades)
     * @return Quantidade total de itens
     */
    public int getTotalItensEstoque() {
        int total = 0;
        
        for (Produto produto : produtosEmEstoque) {
            total += produto.getQuantidadeEstoque();
        }
        
        return total;
    }
}
