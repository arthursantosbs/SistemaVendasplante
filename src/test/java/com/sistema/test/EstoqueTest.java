package com.sistema.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.sistema.service.Estoque;
import com.sistema.model.Produto;
import com.sistema.model.Muda;
import com.sistema.model.Humus;
import com.sistema.model.Esterco;

/**
 * Classe de teste para a classe Estoque
 */
public class EstoqueTest {
    
    private Estoque estoque;
    private Muda muda;
    private Humus humus;
    private Esterco esterco;
    
    @Before
    public void setUp() {
        // Obtém a instância do estoque (Singleton)
        estoque = Estoque.getInstancia();
        
        // Limpa o estoque para os testes
        for (Produto produto : estoque.listarProdutos()) {
            estoque.removerProduto(produto.getId());
        }
        
        // Inicializa os objetos para os testes
        muda = new Muda(1, "Muda de Tomate", "Muda de tomate orgânico", 5.0, 100, 
                        "Solanum lycopersicum", 60, "Argiloso");
        
        humus = new Humus(2, "Húmus Premium", "Húmus de minhoca de alta qualidade", 15.0, 50, 
                         "Minhoca Californiana", 2.5, "Rico em nitrogênio e fósforo");
        
        esterco = new Esterco(3, "Esterco Bovino", "Esterco bovino processado", 12.0, 30, 
                             "Bovino", 5.0, true, "Neutro");
    }
    
    @Test
    public void testSingletonInstance() {
        // Testa se a instância é única (Singleton)
        Estoque outraInstancia = Estoque.getInstancia();
        assertSame(estoque, outraInstancia);
    }
    
    @Test
    public void testAdicionarProduto() {
        // Testa a adição de produtos ao estoque
        assertTrue(estoque.adicionarProduto(muda));
        assertTrue(estoque.adicionarProduto(humus));
        assertTrue(estoque.adicionarProduto(esterco));
        
        // Verifica se os produtos foram adicionados
        assertEquals(3, estoque.listarProdutos().size());
        
        // Testa a adição de produto duplicado (mesmo ID)
        Muda outraMuda = new Muda(1, "Outra Muda", "Descrição", 10.0, 50, "Espécie", 30, "Solo");
        assertFalse(estoque.adicionarProduto(outraMuda));
        
        // Verifica se o número de produtos não mudou
        assertEquals(3, estoque.listarProdutos().size());
    }
    
    @Test
    public void testBuscarProduto() {
        // Adiciona produtos para o teste
        estoque.adicionarProduto(muda);
        estoque.adicionarProduto(humus);
        
        // Testa a busca de produto por ID
        assertEquals(muda, estoque.buscarProduto(1));
        assertEquals(humus, estoque.buscarProduto(2));
        assertNull(estoque.buscarProduto(99)); // ID inexistente
    }
    
    @Test
    public void testRemoverProduto() {
        // Adiciona produtos para o teste
        estoque.adicionarProduto(muda);
        estoque.adicionarProduto(humus);
        estoque.adicionarProduto(esterco);
        
        // Testa a remoção de produto
        assertTrue(estoque.removerProduto(1));
        assertEquals(2, estoque.listarProdutos().size());
        assertNull(estoque.buscarProduto(1));
        
        // Testa a remoção de produto inexistente
        assertFalse(estoque.removerProduto(99));
    }
    
    @Test
    public void testAtualizarQuantidade() {
        // Adiciona produto para o teste
        estoque.adicionarProduto(muda);
        
        // Testa a atualização de quantidade
        assertTrue(estoque.atualizarQuantidade(1, 200));
        assertEquals(200, estoque.buscarProduto(1).getQuantidadeEstoque());
        
        // Testa a atualização com quantidade negativa (deve falhar)
        assertFalse(estoque.atualizarQuantidade(1, -50));
        
        // Testa a atualização de produto inexistente
        assertFalse(estoque.atualizarQuantidade(99, 100));
    }
    
    @Test
    public void testVerificarDisponibilidade() {
        // Adiciona produto para o teste
        estoque.adicionarProduto(muda); // 100 unidades em estoque
        
        // Testa a verificação de disponibilidade
        assertTrue(estoque.verificarDisponibilidade(1, 50));
        assertTrue(estoque.verificarDisponibilidade(1, 100));
        assertFalse(estoque.verificarDisponibilidade(1, 150));
        
        // Testa a verificação com quantidade negativa ou zero
        assertFalse(estoque.verificarDisponibilidade(1, 0));
        assertFalse(estoque.verificarDisponibilidade(1, -10));
        
        // Testa a verificação de produto inexistente
        assertFalse(estoque.verificarDisponibilidade(99, 10));
    }
    
    @Test
    public void testContadores() {
        // Adiciona produtos para o teste
        estoque.adicionarProduto(muda); // 100 unidades
        estoque.adicionarProduto(humus); // 50 unidades
        estoque.adicionarProduto(esterco); // 30 unidades
        
        // Testa o contador de produtos diferentes
        assertEquals(3, estoque.getTotalProdutosDiferentes());
        
        // Testa o contador de itens totais
        assertEquals(180, estoque.getTotalItensEstoque());
        
        // Remove um produto e verifica novamente
        estoque.removerProduto(1);
        assertEquals(2, estoque.getTotalProdutosDiferentes());
        assertEquals(80, estoque.getTotalItensEstoque());
    }
}
