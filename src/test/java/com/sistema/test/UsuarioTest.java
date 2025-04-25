package com.sistema.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.sistema.model.Usuario;
import com.sistema.model.Vendedor;
import com.sistema.model.Administrador;
import com.sistema.model.Cliente;

/**
 * Classe de teste para as classes de usuário
 */
public class UsuarioTest {
    
    private Vendedor vendedor;
    private Administrador administrador;
    private Cliente cliente;
    
    @Before
    public void setUp() {
        // Inicializa os objetos para os testes
        vendedor = new Vendedor(1, "João Silva", "joao@email.com", "senha123", 5.0);
        administrador = new Administrador(2, "Maria Souza", "maria@email.com", "admin456", "Avançado", "Vendas");
        cliente = new Cliente(3, "Pedro Santos", "pedro@email.com", "cliente789", "12345678901", "Rua A, 123", "11987654321");
    }
    
    @Test
    public void testVendedor() {
        // Testa os atributos do vendedor
        assertEquals(1, vendedor.getId());
        assertEquals("João Silva", vendedor.getNome());
        assertEquals("joao@email.com", vendedor.getEmail());
        assertEquals("senha123", vendedor.getSenha());
        assertEquals(5.0, vendedor.getComissao(), 0.001);
        assertEquals(0, vendedor.getTotalVendas());
        assertTrue(vendedor.isAtivo());
        
        // Testa o cálculo de comissão
        assertEquals(50.0, vendedor.calcularComissao(1000.0), 0.001);
        
        // Testa a alteração de atributos
        vendedor.setComissao(10.0);
        assertEquals(10.0, vendedor.getComissao(), 0.001);
        
        vendedor.setTotalVendas(5);
        assertEquals(5, vendedor.getTotalVendas());
    }
    
    @Test
    public void testAdministrador() {
        // Testa os atributos do administrador
        assertEquals(2, administrador.getId());
        assertEquals("Maria Souza", administrador.getNome());
        assertEquals("maria@email.com", administrador.getEmail());
        assertEquals("admin456", administrador.getSenha());
        assertEquals("Avançado", administrador.getNivelAcesso());
        assertEquals("Vendas", administrador.getDepartamento());
        assertTrue(administrador.isAtivo());
        
        // Testa a alteração de atributos
        administrador.setNivelAcesso("Máximo");
        assertEquals("Máximo", administrador.getNivelAcesso());
        
        administrador.setDepartamento("Estoque");
        assertEquals("Estoque", administrador.getDepartamento());
        
        // Testa a ativação/desativação de usuários
        Usuario usuario = new Vendedor();
        assertTrue(administrador.desativarUsuario(usuario));
        assertFalse(usuario.isAtivo());
        
        assertTrue(administrador.ativarUsuario(usuario));
        assertTrue(usuario.isAtivo());
    }
    
    @Test
    public void testCliente() {
        // Testa os atributos do cliente
        assertEquals(3, cliente.getId());
        assertEquals("Pedro Santos", cliente.getNome());
        assertEquals("pedro@email.com", cliente.getEmail());
        assertEquals("cliente789", cliente.getSenha());
        assertEquals("12345678901", cliente.getCpf());
        assertEquals("Rua A, 123", cliente.getEndereco());
        assertEquals("11987654321", cliente.getTelefone());
        assertTrue(cliente.isAtivo());
        
        // Testa a alteração de atributos
        cliente.setCpf("98765432109");
        assertEquals("98765432109", cliente.getCpf());
        
        cliente.setEndereco("Av. B, 456");
        assertEquals("Av. B, 456", cliente.getEndereco());
        
        cliente.setTelefone("11912345678");
        assertEquals("11912345678", cliente.getTelefone());
        
        // Testa o carrinho de compras
        assertTrue(cliente.getCarrinhoCompras().isEmpty());
        assertTrue(cliente.getHistoricoCompras().isEmpty());
    }
}
