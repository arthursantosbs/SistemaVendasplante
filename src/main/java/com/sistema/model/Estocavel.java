package com.sistema.model;

/**
 * Interface que define operações de controle de estoque no sistema.
 */
public interface Estocavel {
    
    /**
     * Adiciona uma quantidade ao estoque
     * @param quantidade Quantidade a ser adicionada
     * @return true se a operação for bem-sucedida, false caso contrário
     */
    boolean adicionarAoEstoque(int quantidade);
    
    /**
     * Remove uma quantidade do estoque
     * @param quantidade Quantidade a ser removida
     * @return true se a operação for bem-sucedida, false caso contrário
     */
    boolean removerDoEstoque(int quantidade);
    
    /**
     * Verifica a quantidade disponível em estoque
     * @return Quantidade disponível em estoque
     */
    int verificarQuantidadeEmEstoque();
}
