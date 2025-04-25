#!/bin/bash

# Script para compilar e executar o projeto

# Cria diretório para os arquivos compilados
mkdir -p SistemaVendas/target/classes

# Compila as classes do projeto
echo "Compilando o projeto..."
javac -d SistemaVendas/target/classes SistemaVendas/src/main/java/com/sistema/model/*.java SistemaVendas/src/main/java/com/sistema/service/*.java SistemaVendas/src/main/java/com/sistema/util/*.java SistemaVendas/src/main/java/com/sistema/*.java

# Verifica se a compilação foi bem-sucedida
if [ $? -eq 0 ]; then
    echo "Compilação concluída com sucesso!"
    
    # Executa o programa
    echo "Executando o programa..."
    java -cp SistemaVendas/target/classes com.sistema.Main
else
    echo "Erro na compilação do projeto."
fi
