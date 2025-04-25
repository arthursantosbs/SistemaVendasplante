package com.sistema.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe utilitária com métodos auxiliares para o sistema.
 */
public class Util {
    
    /**
     * Formata uma data no padrão dd/MM/yyyy
     * 
     * @param data Data a ser formatada
     * @return String com a data formatada
     */
    public static String formatarData(Date data) {
        if (data == null) {
            return "";
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }
    
    /**
     * Formata um valor monetário no padrão brasileiro
     * 
     * @param valor Valor a ser formatado
     * @return String com o valor formatado
     */
    public static String formatarValor(double valor) {
        return String.format("R$ %.2f", valor);
    }
    
    /**
     * Valida se um CPF é válido (implementação simplificada)
     * 
     * @param cpf CPF a ser validado
     * @return true se o CPF for válido, false caso contrário
     */
    public static boolean validarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return false;
        }
        
        // Implementação simplificada - apenas verifica se tem 11 dígitos
        // Em uma implementação real, deveria verificar os dígitos verificadores
        return cpf.matches("\\d{11}");
    }
    
    /**
     * Valida se um email é válido (implementação simplificada)
     * 
     * @param email Email a ser validado
     * @return true se o email for válido, false caso contrário
     */
    public static boolean validarEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        
        // Implementação simplificada - apenas verifica se tem @ e .
        return email.contains("@") && email.contains(".");
    }
    
    /**
     * Gera um ID único baseado no timestamp atual
     * 
     * @return ID único
     */
    public static int gerarId() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }
}
