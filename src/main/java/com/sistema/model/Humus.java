package com.sistema.model;

/**
 * Classe que representa um produto do tipo Humus no sistema.
 * Herda da classe abstrata Produto e implementa a interface Vendavel.
 */
public class Humus extends Produto implements Vendavel, Estocavel {
    private String origem;
    private double pesoEmbalagem;
    private String composicaoNutricional;
    
    /**
     * Construtor padrão
     */
    public Humus() {
        super();
    }
    
    /**
     * Construtor com parâmetros
     * 
     * @param id Identificador único do húmus
     * @param nome Nome do húmus
     * @param descricao Descrição detalhada do húmus
     * @param preco Preço unitário do húmus
     * @param quantidadeEstoque Quantidade disponível em estoque
     * @param origem Origem do húmus
     * @param pesoEmbalagem Peso da embalagem em kg
     * @param composicaoNutricional Composição nutricional do húmus
     */
    public Humus(int id, String nome, String descricao, double preco, int quantidadeEstoque, 
                String origem, double pesoEmbalagem, String composicaoNutricional) {
        super(id, nome, descricao, preco, quantidadeEstoque);
        this.origem = origem;
        this.pesoEmbalagem = pesoEmbalagem;
        this.composicaoNutricional = composicaoNutricional;
    }
    
    @Override
    public String getInformacoesEspecificas() {
        return "Origem: " + origem + ", Peso da Embalagem: " + pesoEmbalagem + 
               " kg, Composição Nutricional: " + composicaoNutricional;
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
     * Calcula a área que pode ser fertilizada com uma embalagem
     * 
     * @param areaMetroQuadrado Área em metros quadrados por kg
     * @return Área total que pode ser fertilizada com uma embalagem
     */
    public double calcularAreaFertilizavel(double areaMetroQuadrado) {
        if (areaMetroQuadrado <= 0) {
            return 0.0;
        }
        
        return pesoEmbalagem * areaMetroQuadrado;
    }
    
    // Getters e Setters
    public String getOrigem() {
        return origem;
    }
    
    public void setOrigem(String origem) {
        this.origem = origem;
    }
    
    public double getPesoEmbalagem() {
        return pesoEmbalagem;
    }
    
    public void setPesoEmbalagem(double pesoEmbalagem) {
        this.pesoEmbalagem = pesoEmbalagem;
    }
    
    public String getComposicaoNutricional() {
        return composicaoNutricional;
    }
    
    public void setComposicaoNutricional(String composicaoNutricional) {
        this.composicaoNutricional = composicaoNutricional;
    }
    
    @Override
    public String toString() {
        return "Humus [id=" + getId() + ", nome=" + getNome() + ", preço=" + getPreco() + 
               ", estoque=" + getQuantidadeEstoque() + ", origem=" + origem + 
               ", peso da embalagem=" + pesoEmbalagem + " kg]";
    }
}
