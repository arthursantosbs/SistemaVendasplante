package com.sistema.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.sistema.service.Controlador;
import com.sistema.model.Produto;
import com.sistema.model.Muda;
import com.sistema.model.Usuario;
import com.sistema.model.Vendedor;
import com.sistema.model.Cliente;

/**
 * Classe de teste para a classe Controlador
 */
public class ControladorTest {
    
    private Controlador controlador;
    private Vendedor vendedor;
    private Cliente cliente;
    private Muda muda;
    
    @Before
    public void setUp() {
        // Obtém a instância do controlador (Singleton)
        controlador = Controlador.getInstancia();
        
        // Inicializa os objetos para os testes
        vendedor = new Vendedor(1, "João Silva", "joao@email.com", "senha123", 5.0);
        cliente = new Cliente(2, "Pedro Santos", "pedro@email.com", "cliente456", "12345678901", "Rua A, 123", "11987654321");
        muda = new Muda(1, "Muda de Tomate", "Muda de tomate orgânico", 5.0, 100, 
                        "Solanum lycopersicum", 60, "Argiloso");
        
        // Limpa usuários e produtos anteriores
        for (Usuario usuario : controlador.listarUsuarios()) {
            controlador.realizarLogin("joao@email.com", "senha123"); // Login para permitir remoção
        }
        
        for (Produto produto : controlador.listarProdutos()) {
            controlador.removerProduto(produto.getId());
        }
        
        // Cadastra usuários e produtos para os testes
        controlador.cadastrarUsuario(vendedor);
        controlador.cadastrarUsuario(cliente);
        controlador.cadastrarProduto(muda);
    }
    
    @Test
    public void testSingletonInstance() {
        // Testa se a instância é única (Singleton)
        Controlador outraInstancia = Controlador.getInstancia();
        assertSame(controlador, outraInstancia);
    }
    
    @Test
    public void testLoginLogout() {
        // Testa login com credenciais corretas
        assertTrue(controlador.realizarLogin("joao@email.com", "senha123"));
        
        // Testa logout
        controlador.realizarLogout();
        
        // Testa login com credenciais incorretas
        assertFalse(controlador.realizarLogin("joao@email.com", "senhaerrada"));
    }
    
    @Test
    public void testCadastroUsuario() {
        // Testa o cadastro de um novo usuário
        Vendedor novoVendedor = new Vendedor(3, "Ana Silva", "ana@email.com", "senha789", 7.0);
        assertTrue(controlador.cadastrarUsuario(novoVendedor));
        
        // Verifica se o usuário foi cadastrado
        assertNotNull(controlador.buscarUsuarioPorId(3));
    }
    
    @Test
    public void testCadastroProduto() {
        // Testa o cadastro de um novo produto
        Muda novaMuda = new Muda(2, "Muda de Alface", "Muda de alface orgânica", 3.0, 150, 
                                "Lactuca sativa", 45, "Arenoso");
        assertTrue(controlador.cadastrarProduto(novaMuda));
        
        // Verifica se o produto foi cadastrado
        assertNotNull(controlador.buscarProduto(2));
        
        // Verifica se o produto está no estoque
        assertTrue(controlador.verificarDisponibilidadeProduto(2, 100));
    }
    
    @Test
    public void testAtualizacaoEstoque() {
        // Testa a atualização de estoque
        assertTrue(controlador.atualizarEstoque(1, 200));
        
        // Verifica se o estoque foi atualizado
        assertEquals(200, controlador.buscarProduto(1).getQuantidadeEstoque());
        
        // Testa a atualização de estoque de produto inexistente
        assertFalse(controlador.atualizarEstoque(99, 100));
    }
    
    @Test
    public void testRealizarVenda() {
        // Testa a realização de venda
        int estoqueInicial = controlador.buscarProduto(1).getQuantidadeEstoque();
        assertTrue(controlador.realizarVenda(1, 20));
        
        // Verifica se o estoque foi atualizado após a venda
        assertEquals(estoqueInicial - 20, controlador.buscarProduto(1).getQuantidadeEstoque());
        
        // Testa a venda com quantidade maior que o estoque
        assertFalse(controlador.realizarVenda(1, 1000));
        
        // Testa a venda de produto inexistente
        assertFalse(controlador.realizarVenda(99, 10));
    }
    
    @Test
    public void testGeracaoRelatorios() {
        // Testa a geração de relatório de estoque
        String relatorioEstoque = controlador.gerarRelatorioEstoque();
        assertNotNull(relatorioEstoque);
        assertTrue(relatorioEstoque.contains("RELATÓRIO DE ESTOQUE"));
        
        // Testa a geração de relatório de vendas
        String relatorioVendas = controlador.gerarRelatorioVendas();
        assertNotNull(relatorioVendas);
        assertTrue(relatorioVendas.contains("RELATÓRIO DE VENDAS"));
    }
}
