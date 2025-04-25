package com.sistema.model;

/**
 * Classe que representa um produto do tipo Esterco no sistema.
 * Herda da classe abstrata Produto e implementa a interface Vendavel.
 */
public class Esterco extends Produto implements Vendavel, Estocavel {
    private String tipoAnimal;
    private double pesoEmbalagem;
    private boolean processado;
    private String nivelAcidez;
    
    /**
     * Construtor padrão
     */
    public Esterco() {
        super();
    }
    
    /**
     * Construtor com parâmetros
     * 
     * @param id Identificador único do esterco
     * @param nome Nome do esterco
     * @param descricao Descrição detalhada do esterco
     * @param preco Preço unitário do esterco
     * @param quantidadeEstoque Quantidade disponível em estoque
     * @param tipoAnimal Tipo de animal de origem do esterco
     * @param pesoEmbalagem Peso da embalagem em kg
     * @param processado Indica se o esterco é processado ou in natura
     * @param nivelAcidez Nível de acidez do esterco
     */
    public Esterco(int id, String nome, String descricao, double preco, int quantidadeEstoque, 
                String tipoAnimal, double pesoEmbalagem, boolean processado, String nivelAcidez) {
        super(id, nome, descricao, preco, quantidadeEstoque);
        this.tipoAnimal = tipoAnimal;
        this.pesoEmbalagem = pesoEmbalagem;
        this.processado = processado;
        this.nivelAcidez = nivelAcidez;
    }
    
    @Override
    public String getInformacoesEspecificas() {
        return "Tipo Animal: " + tipoAnimal + ", Peso da Embalagem: " + pesoEmbalagem + 
               " kg, Processado: " + (processado ? "Sim" : "Não") + ", Nível de Acidez: " + nivelAcidez;
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
     * Verifica se o esterco é adequado para um determinado tipo de planta
     * 
     * @param tipoPlanta Tipo de planta a ser verificada
     * @return true se o esterco for adequado para a planta, false caso contrário
     */
    public boolean verificarCompatibilidadePlanta(String tipoPlanta) {
        // Lógica simplificada para verificar compatibilidade
        if (tipoPlanta == null || tipoPlanta.isEmpty()) {
            return false;
        }
        
        // Exemplo: esterco bovino é compatível com plantas que precisam de pH neutro
        if (tipoAnimal.equalsIgnoreCase("bovino") && nivelAcidez.equalsIgnoreCase("neutro")) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Calcula o tempo de decomposição do esterco
     * 
     * @return Tempo estimado de decomposição em dias
     */
    public int calcularTempoDecomposicao() {
        if (processado) {
            return 15; // Esterco processado decompõe mais rápido
        } else {
            return 45; // Esterco in natura leva mais tempo para decompor
        }
    }
    
    // Getters e Setters
    public String getTipoAnimal() {
        return tipoAnimal;
    }
    
    public void setTipoAnimal(String tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }
    
    public double getPesoEmbalagem() {
        return pesoEmbalagem;
    }
    
    public void setPesoEmbalagem(double pesoEmbalagem) {
        this.pesoEmbalagem = pesoEmbalagem;
    }
    
    public boolean isProcessado() {
        return processado;
    }
    
    public void setProcessado(boolean processado) {
        this.processado = processado;
    }
    
    public String getNivelAcidez() {
        return nivelAcidez;
    }
    
    public void setNivelAcidez(String nivelAcidez) {
        this.nivelAcidez = nivelAcidez;
    }
    
    @Override
    public String toString() {
        return "Esterco [id=" + getId() + ", nome=" + getNome() + ", preço=" + getPreco() + 
               ", estoque=" + getQuantidadeEstoque() + ", tipo animal=" + tipoAnimal + 
               ", processado=" + (processado ? "Sim" : "Não") + "]";
    }
}
