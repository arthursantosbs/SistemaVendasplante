package com.sistema.model;

/**
 * Classe que representa um Administrador no sistema.
 * Herda da classe abstrata Usuario e implementa funcionalidades específicas de administrador.
 */
public class Administrador extends Usuario {
    private String nivelAcesso;
    private String departamento;
    
    /**
     * Construtor padrão
     */
    public Administrador() {
        super();
        this.nivelAcesso = "Básico";
        this.departamento = "Geral";
    }
    
    /**
     * Construtor com parâmetros
     * 
     * @param id Identificador único do administrador
     * @param nome Nome completo do administrador
     * @param email Email do administrador
     * @param senha Senha do administrador
     * @param nivelAcesso Nível de acesso do administrador
     * @param departamento Departamento do administrador
     */
    public Administrador(int id, String nome, String email, String senha, String nivelAcesso, String departamento) {
        super(id, nome, email, senha);
        this.nivelAcesso = nivelAcesso;
        this.departamento = departamento;
    }
    
    /**
     * Ativa um usuário no sistema
     * 
     * @param usuario Usuário a ser ativado
     * @return true se a operação for bem-sucedida, false caso contrário
     */
    public boolean ativarUsuario(Usuario usuario) {
        if (usuario == null) {
            return false;
        }
        
        usuario.setAtivo(true);
        return true;
    }
    
    /**
     * Desativa um usuário no sistema
     * 
     * @param usuario Usuário a ser desativado
     * @return true se a operação for bem-sucedida, false caso contrário
     */
    public boolean desativarUsuario(Usuario usuario) {
        if (usuario == null) {
            return false;
        }
        
        usuario.setAtivo(false);
        return true;
    }
    
    /**
     * Gera um relatório de vendas
     * 
     * @return Relatório de vendas em formato de texto
     */
    public String gerarRelatorioVendas() {
        return "Relatório de Vendas gerado pelo administrador " + getNome() + " do departamento " + departamento;
    }
    
    /**
     * Gera um relatório de estoque
     * 
     * @return Relatório de estoque em formato de texto
     */
    public String gerarRelatorioEstoque() {
        return "Relatório de Estoque gerado pelo administrador " + getNome() + " do departamento " + departamento;
    }
    
    @Override
    public void executarOperacao() {
        System.out.println("Administrador " + getNome() + " está realizando operações administrativas.");
    }
    
    // Getters e Setters
    public String getNivelAcesso() {
        return nivelAcesso;
    }
    
    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }
    
    public String getDepartamento() {
        return departamento;
    }
    
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
    @Override
    public String toString() {
        return "Administrador [id=" + getId() + ", nome=" + getNome() + ", email=" + getEmail() 
               + ", nivelAcesso=" + nivelAcesso + ", departamento=" + departamento + "]";
    }
}
