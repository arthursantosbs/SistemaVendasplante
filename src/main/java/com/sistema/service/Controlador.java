package com.sistema.service;

import java.util.ArrayList;
import java.util.List;

import com.sistema.model.Produto;
import com.sistema.model.Sistema;
import com.sistema.model.Usuario;

/**
 * Classe responsável pelo controle das operações do sistema.
 * Implementa o padrão Singleton e gerencia as operações entre o Sistema e o Estoque.
 */
public class Controlador {
    private static Controlador instancia;
    private Sistema sistema;
    private Estoque estoque;
    
    /**
     * Construtor privado para implementar o padrão Singleton
     */
    private Controlador() {
        this.sistema = Sistema.getInstancia();
        this.estoque = Estoque.getInstancia();
    }
    
    /**
     * Método para obter a instância única do controlador (Singleton)
     * @return Instância única do controlador
     */
    public static Controlador getInstancia() {
        if (instancia == null) {
            instancia = new Controlador();
        }
        return instancia;
    }
    
    /**
     * Realiza o login de um usuário no sistema
     * @param email Email do usuário
     * @param senha Senha do usuário
     * @return true se o login for bem-sucedido, false caso contrário
     */
    public boolean realizarLogin(String email, String senha) {
        return sistema.login(email, senha);
    }
    
    /**
     * Realiza o logout do usuário atual
     */
    public void realizarLogout() {
        sistema.logout();
    }
    
    /**
     * Cadastra um novo usuário no sistema
     * @param usuario Usuário a ser cadastrado
     * @return true se o cadastro for bem-sucedido, false caso contrário
     */
    public boolean cadastrarUsuario(Usuario usuario) {
        return sistema.cadastrarUsuario(usuario);
    }
    
    /**
     * Cadastra um novo produto no sistema e no estoque
     * @param produto Produto a ser cadastrado
     * @return true se o cadastro for bem-sucedido, false caso contrário
     */
    public boolean cadastrarProduto(Produto produto) {
        boolean cadastroSistema = sistema.cadastrarProduto(produto);
        boolean cadastroEstoque = estoque.adicionarProduto(produto);
        
        return cadastroSistema && cadastroEstoque;
    }
    
    /**
     * Remove um produto do sistema e do estoque
     * @param idProduto ID do produto a ser removido
     * @return true se a remoção for bem-sucedida, false caso contrário
     */
    public boolean removerProduto(int idProduto) {
        boolean remocaoSistema = sistema.removerProduto(idProduto);
        boolean remocaoEstoque = estoque.removerProduto(idProduto);
        
        return remocaoSistema && remocaoEstoque;
    }
    
    /**
     * Atualiza a quantidade de um produto no estoque
     * @param idProduto ID do produto a ser atualizado
     * @param novaQuantidade Nova quantidade do produto
     * @return true se a atualização for bem-sucedida, false caso contrário
     */
    public boolean atualizarEstoque(int idProduto, int novaQuantidade) {
        Produto produto = sistema.buscarProdutoPorId(idProduto);
        
        if (produto == null) {
            return false;
        }
        
        produto.setQuantidadeEstoque(novaQuantidade);
        return estoque.atualizarQuantidade(idProduto, novaQuantidade);
    }
    
    /**
     * Realiza uma venda de produto
     * @param idProduto ID do produto a ser vendido
     * @param quantidade Quantidade a ser vendida
     * @return true se a venda for bem-sucedida, false caso contrário
     */
    public boolean realizarVenda(int idProduto, int quantidade) {
        Produto produto = sistema.buscarProdutoPorId(idProduto);
        
        if (produto == null || !estoque.verificarDisponibilidade(idProduto, quantidade)) {
            return false;
        }
        
        produto.reduzirEstoque(quantidade);
        return estoque.atualizarQuantidade(idProduto, produto.getQuantidadeEstoque());
    }
    
    /**
     * Busca um produto pelo ID
     * @param idProduto ID do produto a ser buscado
     * @return Produto encontrado ou null se não existir
     */
    public Produto buscarProduto(int idProduto) {
        return sistema.buscarProdutoPorId(idProduto);
    }
    
    /**
     * Lista todos os produtos disponíveis
     * @return Lista de produtos
     */
    public List<Produto> listarProdutos() {
        return sistema.getProdutos();
    }
    
    /**
     * Lista todos os produtos em estoque
     * @return Lista de produtos em estoque
     */
    public List<Produto> listarProdutosEmEstoque() {
        return estoque.listarProdutos();
    }
    
    /**
     * Lista todos os usuários cadastrados
     * @return Lista de usuários
     */
    public List<Usuario> listarUsuarios() {
        return sistema.getUsuarios();
    }
    
    /**
     * Verifica se um produto está disponível no estoque
     * @param idProduto ID do produto a ser verificado
     * @param quantidade Quantidade desejada
     * @return true se o produto estiver disponível na quantidade desejada, false caso contrário
     */
    public boolean verificarDisponibilidadeProduto(int idProduto, int quantidade) {
        return estoque.verificarDisponibilidade(idProduto, quantidade);
    }
    
    /**
     * Gera um relatório de estoque
     * @return Relatório de estoque em formato de texto
     */
    public String gerarRelatorioEstoque() {
        List<Produto> produtos = estoque.listarProdutos();
        StringBuilder relatorio = new StringBuilder();
        
        relatorio.append("=== RELATÓRIO DE ESTOQUE ===\n");
        relatorio.append("Total de produtos diferentes: ").append(estoque.getTotalProdutosDiferentes()).append("\n");
        relatorio.append("Total de itens em estoque: ").append(estoque.getTotalItensEstoque()).append("\n\n");
        relatorio.append("Detalhamento dos produtos:\n");
        
        for (Produto produto : produtos) {
            relatorio.append("- ").append(produto.toString()).append("\n");
            relatorio.append("  Informações específicas: ").append(produto.getInformacoesEspecificas()).append("\n");
        }
        
        return relatorio.toString();
    }
    
    /**
     * Gera um relatório de vendas (simplificado para este exemplo)
     * @return Relatório de vendas em formato de texto
     */
    public String gerarRelatorioVendas() {
        return "=== RELATÓRIO DE VENDAS ===\n" +
               "Este é um relatório simplificado para demonstração.\n" +
               "Em uma implementação completa, este relatório incluiria:\n" +
               "- Total de vendas realizadas\n" +
               "- Valor total das vendas\n" +
               "- Produtos mais vendidos\n" +
               "- Vendedores com melhor desempenho\n";
    }
}
