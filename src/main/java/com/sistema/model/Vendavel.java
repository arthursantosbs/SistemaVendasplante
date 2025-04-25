package com.sistema.model;

/**
 * Interface que define operações de venda no sistema.
 */
public interface Vendavel {
    
    /**
     * Realiza a venda de um produto
     * @param quantidade Quantidade a ser vendida
     * @return true se a venda for bem-sucedida, false caso contrário
     */
    boolean vender(int quantidade);
    
    /**
     * Calcula o valor total da venda
     * @param quantidade Quantidade a ser vendida
     * @return Valor total da venda
     */
    double calcularValorTotal(int quantidade);
    
    /**
     * Verifica se o produto está disponível para venda
     * @param quantidade Quantidade desejada
     * @return true se o produto estiver disponível na quantidade desejada, false caso contrário
     */
    boolean verificarDisponibilidade(int quantidade);
}
