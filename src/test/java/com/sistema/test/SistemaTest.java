package com.sistema.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.sistema.model.Sistema;
import com.sistema.model.Usuario;
import com.sistema.model.Vendedor;
import com.sistema.model.Administrador;
import com.sistema.model.Cliente;
import com.sistema.model.Produto;
import com.sistema.model.Muda;

/**
 * Classe de teste para a classe Sistema
 */
public class SistemaTest {
    
    private Sistema sistema;
    private Vendedor vendedor;
    private Administrador administrador;
    private Cliente cliente;
    private Muda muda;
    
    @Before
    public void setUp() {
        // Obtém a instância do sistema (Singleton)
        sistema = Sistema.getInstancia();
        
        // Inicializa os objetos para os testes
        vendedor = new Vendedor(1, "João Silva", "joao@email.com", "senha123", 5.0);
        administrador = new Administrador(2, "Maria Souza", "maria@email.com", "admin456", "Avançado", "Vendas");
        cliente = new Cliente(3, "Pedro Santos", "pedro@email.com", "cliente789", "12345678901", "Rua A, 123", "11987654321");
        muda = new Muda(1, "Muda de Tomate", "Muda de tomate orgânico", 5.0, 100, 
                        "Solanum lycopersicum", 60, "Argiloso");
    }
    
    @Test
    public void testSingletonInstance() {
        // Testa se a instância é única (Singleton)
        Sistema outraInstancia = Sistema.getInstancia();
        assertSame(sistema, outraInstancia);
    }
    
    @Test
    public void testCadastroUsuario() {
        // Limpa usuários anteriores para o teste
        while (!sistema.getUsuarios().isEmpty()) {
            sistema.removerUsuario(sistema.getUsuarios().get(0).getId());
        }
        
        // Testa o cadastro de usuários
        assertTrue(sistema.cadastrarUsuario(vendedor));
        assertTrue(sistema.cadastrarUsuario(administrador));
        assertTrue(sistema.cadastrarUsuario(cliente));
        
        // Verifica se os usuários foram cadastrados
        assertEquals(3, sistema.getUsuarios().size());
        
        // Testa a busca de usuário por ID
        assertEquals(vendedor, sistema.buscarUsuarioPorId(1));
        assertEquals(administrador, sistema.buscarUsuarioPorId(2));
        assertEquals(cliente, sistema.buscarUsuarioPorId(3));
        
        // Testa a remoção de usuário
        assertTrue(sistema.removerUsuario(1));
        assertEquals(2, sistema.getUsuarios().size());
        assertNull(sistema.buscarUsuarioPorId(1));
    }
    
    @Test
    public void testCadastroProduto() {
        // Limpa produtos anteriores para o teste
        while (!sistema.getProdutos().isEmpty()) {
            sistema.removerProduto(sistema.getProdutos().get(0).getId());
        }
        
        // Testa o cadastro de produto
        assertTrue(sistema.cadastrarProduto(muda));
        
        // Verifica se o produto foi cadastrado
        assertEquals(1, sistema.getProdutos().size());
        
        // Testa a busca de produto por ID
        assertEquals(muda, sistema.buscarProdutoPorId(1));
        
        // Testa a remoção de produto
        assertTrue(sistema.removerProduto(1));
        assertEquals(0, sistema.getProdutos().size());
        assertNull(sistema.buscarProdutoPorId(1));
    }
    
    @Test
    public void testLoginLogout() {
        // Limpa usuários anteriores para o teste
        while (!sistema.getUsuarios().isEmpty()) {
            sistema.removerUsuario(sistema.getUsuarios().get(0).getId());
        }
        
        // Cadastra um usuário para teste
        sistema.cadastrarUsuario(vendedor);
        
        // Testa login com credenciais corretas
        assertTrue(sistema.login("joao@email.com", "senha123"));
        assertEquals(vendedor, sistema.getUsuarioLogado());
        
        // Testa logout
        sistema.logout();
        assertNull(sistema.getUsuarioLogado());
        
        // Testa login com credenciais incorretas
        assertFalse(sistema.login("joao@email.com", "senhaerrada"));
        assertNull(sistema.getUsuarioLogado());
    }
}
