package com.sistema.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.sistema.model.Produto;
import com.sistema.model.Muda;
import com.sistema.model.Humus;
import com.sistema.model.Esterco;

/**
 * Classe de teste para as classes de produto
 */
public class ProdutoTest {
    
    private Muda muda;
    private Humus humus;
    private Esterco esterco;
    
    @Before
    public void setUp() {
        // Inicializa os objetos para os testes
        muda = new Muda(1, "Muda de Tomate", "Muda de tomate orgânico", 5.0, 100, 
                        "Solanum lycopersicum", 60, "Argiloso");
        
        humus = new Humus(2, "Húmus Premium", "Húmus de minhoca de alta qualidade", 15.0, 50, 
                         "Minhoca Californiana", 2.5, "Rico em nitrogênio e fósforo");
        
        esterco = new Esterco(3, "Esterco Bovino", "Esterco bovino processado", 12.0, 30, 
                             "Bovino", 5.0, true, "Neutro");
    }
    
    @Test
    public void testMuda() {
        // Testa os atributos da muda
        assertEquals(1, muda.getId());
        assertEquals("Muda de Tomate", muda.getNome());
        assertEquals("Muda de tomate orgânico", muda.getDescricao());
        assertEquals(5.0, muda.getPreco(), 0.001);
        assertEquals(100, muda.getQuantidadeEstoque());
        assertEquals("Solanum lycopersicum", muda.getEspecie());
        assertEquals(60, muda.getTempoMaturacao());
        assertEquals("Argiloso", muda.getTipoSolo());
        
        // Testa os métodos da interface Vendavel
        assertTrue(muda.verificarDisponibilidade(50));
        assertFalse(muda.verificarDisponibilidade(150));
        assertEquals(250.0, muda.calcularValorTotal(50), 0.001);
        
        // Testa os métodos da interface Estocavel
        assertEquals(100, muda.verificarQuantidadeEmEstoque());
        assertTrue(muda.adicionarAoEstoque(50));
        assertEquals(150, muda.verificarQuantidadeEmEstoque());
        assertTrue(muda.removerDoEstoque(30));
        assertEquals(120, muda.verificarQuantidadeEmEstoque());
        
        // Testa o método específico da classe Muda
        assertEquals(30, muda.calcularTempoAteColheita(30));
        assertEquals(0, muda.calcularTempoAteColheita(70));
    }
    
    @Test
    public void testHumus() {
        // Testa os atributos do húmus
        assertEquals(2, humus.getId());
        assertEquals("Húmus Premium", humus.getNome());
        assertEquals("Húmus de minhoca de alta qualidade", humus.getDescricao());
        assertEquals(15.0, humus.getPreco(), 0.001);
        assertEquals(50, humus.getQuantidadeEstoque());
        assertEquals("Minhoca Californiana", humus.getOrigem());
        assertEquals(2.5, humus.getPesoEmbalagem(), 0.001);
        assertEquals("Rico em nitrogênio e fósforo", humus.getComposicaoNutricional());
        
        // Testa os métodos da interface Vendavel
        assertTrue(humus.verificarDisponibilidade(30));
        assertFalse(humus.verificarDisponibilidade(60));
        assertEquals(450.0, humus.calcularValorTotal(30), 0.001);
        
        // Testa os métodos da interface Estocavel
        assertEquals(50, humus.verificarQuantidadeEmEstoque());
        assertTrue(humus.adicionarAoEstoque(20));
        assertEquals(70, humus.verificarQuantidadeEmEstoque());
        assertTrue(humus.removerDoEstoque(10));
        assertEquals(60, humus.verificarQuantidadeEmEstoque());
        
        // Testa o método específico da classe Humus
        assertEquals(25.0, humus.calcularAreaFertilizavel(10.0), 0.001);
    }
    
    @Test
    public void testEsterco() {
        // Testa os atributos do esterco
        assertEquals(3, esterco.getId());
        assertEquals("Esterco Bovino", esterco.getNome());
        assertEquals("Esterco bovino processado", esterco.getDescricao());
        assertEquals(12.0, esterco.getPreco(), 0.001);
        assertEquals(30, esterco.getQuantidadeEstoque());
        assertEquals("Bovino", esterco.getTipoAnimal());
        assertEquals(5.0, esterco.getPesoEmbalagem(), 0.001);
        assertTrue(esterco.isProcessado());
        assertEquals("Neutro", esterco.getNivelAcidez());
        
        // Testa os métodos da interface Vendavel
        assertTrue(esterco.verificarDisponibilidade(20));
        assertFalse(esterco.verificarDisponibilidade(40));
        assertEquals(240.0, esterco.calcularValorTotal(20), 0.001);
        
        // Testa os métodos da interface Estocavel
        assertEquals(30, esterco.verificarQuantidadeEmEstoque());
        assertTrue(esterco.adicionarAoEstoque(15));
        assertEquals(45, esterco.verificarQuantidadeEmEstoque());
        assertTrue(esterco.removerDoEstoque(5));
        assertEquals(40, esterco.verificarQuantidadeEmEstoque());
        
        // Testa os métodos específicos da classe Esterco
        assertEquals(15, esterco.calcularTempoDecomposicao());
        esterco.setProcessado(false);
        assertEquals(45, esterco.calcularTempoDecomposicao());
    }
}
