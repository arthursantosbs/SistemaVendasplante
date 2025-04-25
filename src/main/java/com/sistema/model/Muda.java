package com.sistema.model;

/**
 * Classe que representa um produto do tipo Muda no sistema.
 * Herda da classe abstrata Produto e implementa a interface Vendavel.
 */
public class Muda extends Produto implements Vendavel, Estocavel {
    private String especie;
    private int tempoMaturacao;
    private String tipoSolo;
    
    /**
     * Construtor padrão
     */
    public Muda() {
        super();
    }
    
    /**
     * Construtor com parâmetros
     * 
     * @param id Identificador único da muda
     * @param nome Nome da muda
     * @param descricao Descrição detalhada da muda
     * @param preco Preço unitário da muda
     * @param quantidadeEstoque Quantidade disponível em estoque
     * @param especie Espécie da planta
     * @param tempoMaturacao Tempo de maturação em dias
     * @param tipoSolo Tipo de solo recomendado
     */
    public Muda(int id, String nome, String descricao, double preco, int quantidadeEstoque, 
                String especie, int tempoMaturacao, String tipoSolo) {
        super(id, nome, descricao, preco, quantidadeEstoque);
        this.especie = especie;
        this.tempoMaturacao = tempoMaturacao;
        this.tipoSolo = tipoSolo;
    }
    
    @Override
    public String getInformacoesEspecificas() {
        return "Espécie: " + especie + ", Tempo de Maturação: " + tempoMaturacao + 
               " dias, Tipo de Solo Recomendado: " + tipoSolo;
    }
    
    @Override
    public boolean vender(int quantidade) {
        if (quantidade <= 0 || quantidade > getQuantidadeEstoque()) {
            return false;
        }
        
        return reduzirEstoque(quantidade);
    }
    
    @Override
    public double calcularValorTotal(int quantidade) {
        if (quantidade <= 0) {
            return 0.0;
        }
        
        return getPreco() * quantidade;
    }
    
    @Override
    public boolean verificarDisponibilidade(int quantidade) {
        if (quantidade <= 0) {
            return false;
        }
        
        return getQuantidadeEstoque() >= quantidade;
    }
    
    @Override
    public boolean adicionarAoEstoque(int quantidade) {
        if (quantidade <= 0) {
            return false;
        }
        
        return adicionarEstoque(quantidade);
    }
    
    @Override
    public boolean removerDoEstoque(int quantidade) {
        if (quantidade <= 0 || quantidade > getQuantidadeEstoque()) {
            return false;
        }
        
        return reduzirEstoque(quantidade);
    }
    
    @Override
    public int verificarQuantidadeEmEstoque() {
        return getQuantidadeEstoque();
    }
    
    /**
     * Calcula o tempo estimado até a colheita
     * 
     * @param diasPlantado Dias desde o plantio
     * @return Dias restantes até a colheita
     */
    public int calcularTempoAteColheita(int diasPlantado) {
        if (diasPlantado >= tempoMaturacao) {
            return 0;
        }
        
        return tempoMaturacao - diasPlantado;
    }
    
    // Getters e Setters
    public String getEspecie() {
        return especie;
    }
    
    public void setEspecie(String especie) {
        this.especie = especie;
    }
    
    public int getTempoMaturacao() {
        return tempoMaturacao;
    }
    
    public void setTempoMaturacao(int tempoMaturacao) {
        this.tempoMaturacao = tempoMaturacao;
    }
    
    public String getTipoSolo() {
        return tipoSolo;
    }
    
    public void setTipoSolo(String tipoSolo) {
        this.tipoSolo = tipoSolo;
    }
    
    @Override
    public String toString() {
        return "Muda [id=" + getId() + ", nome=" + getNome() + ", preço=" + getPreco() + 
               ", estoque=" + getQuantidadeEstoque() + ", espécie=" + especie + 
               ", tempo de maturação=" + tempoMaturacao + " dias]";
    }
}
