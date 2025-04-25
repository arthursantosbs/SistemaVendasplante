package com.sistema;

import com.sistema.model.*;
import com.sistema.service.Controlador;
import com.sistema.util.Util;

/**
 * Classe principal que inicia o sistema
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("Iniciando o Sistema de Vendas...");
        
        // Obtém a instância do controlador
        Controlador controlador = Controlador.getInstancia();
        
        // Cria alguns usuários para demonstração
        Administrador admin = new Administrador(
            Util.gerarId(), 
            "Admin Principal", 
            "admin@sistema.com", 
            "admin123", 
            "Máximo", 
            "Administração"
        );
        
        Vendedor vendedor = new Vendedor(
            Util.gerarId(), 
            "Vendedor Exemplo", 
            "vendedor@sistema.com", 
            "vend123", 
            10.0
        );
        
        Cliente cliente = new Cliente(
            Util.gerarId(), 
            "Cliente Exemplo", 
            "cliente@email.com", 
            "cli123", 
            "12345678901", 
            "Rua Exemplo, 123", 
            "11987654321"
        );
        
        // Cadastra os usuários no sistema
        controlador.cadastrarUsuario(admin);
        controlador.cadastrarUsuario(vendedor);
        controlador.cadastrarUsuario(cliente);
        
        // Cria alguns produtos para demonstração
        Muda mudaTomate = new Muda(
            Util.gerarId(), 
            "Muda de Tomate", 
            "Muda de tomate orgânico de alta qualidade", 
            5.0, 
            100, 
            "Solanum lycopersicum", 
            60, 
            "Argiloso"
        );
        
        Humus humusPremium = new Humus(
            Util.gerarId(), 
            "Húmus Premium", 
            "Húmus de minhoca de alta qualidade", 
            15.0, 
            50, 
            "Minhoca Californiana", 
            2.5, 
            "Rico em nitrogênio e fósforo"
        );
        
        Esterco estercoBovino = new Esterco(
            Util.gerarId(), 
            "Esterco Bovino", 
            "Esterco bovino processado", 
            12.0, 
            30, 
            "Bovino", 
            5.0, 
            true, 
            "Neutro"
        );
        
        // Cadastra os produtos no sistema
        controlador.cadastrarProduto(mudaTomate);
        controlador.cadastrarProduto(humusPremium);
        controlador.cadastrarProduto(estercoBovino);
        
        // Realiza algumas operações para demonstração
        System.out.println("\nRealizando login como vendedor...");
        if (controlador.realizarLogin("vendedor@sistema.com", "vend123")) {
            System.out.println("Login realizado com sucesso!");
            
            // Realiza uma venda
            System.out.println("\nRealizando venda de 10 mudas de tomate...");
            if (controlador.realizarVenda(mudaTomate.getId(), 10)) {
                System.out.println("Venda realizada com sucesso!");
            } else {
                System.out.println("Falha ao realizar a venda.");
            }
            
            controlador.realizarLogout();
        }
        
        // Realiza login como administrador
        System.out.println("\nRealizando login como administrador...");
        if (controlador.realizarLogin("admin@sistema.com", "admin123")) {
            System.out.println("Login realizado com sucesso!");
            
            // Gera relatórios
            System.out.println("\nGerando relatório de estoque:");
            System.out.println(controlador.gerarRelatorioEstoque());
            
            System.out.println("\nGerando relatório de vendas:");
            System.out.println(controlador.gerarRelatorioVendas());
            
            controlador.realizarLogout();
        }
        
        System.out.println("\nSistema de Vendas finalizado.");
    }
}
