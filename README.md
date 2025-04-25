# Sistema de Vendas - Documentação

## Visão Geral do Projeto

Este projeto implementa um sistema de vendas orientado a objetos em Java, focado na venda de produtos agrícolas como mudas, húmus e esterco. O sistema segue os princípios da Programação Orientada a Objetos (POO), utilizando conceitos como herança, polimorfismo, encapsulamento, classes abstratas e interfaces.

## Estrutura de Pacotes

O projeto está organizado nos seguintes pacotes:

- **com.sistema.model**: Contém todas as interfaces, classes abstratas e entidades do sistema
- **com.sistema.service**: Contém as classes responsáveis pelas regras de negócio e lógica extra
- **com.sistema.util**: Contém classes auxiliares para o sistema
- **com.sistema**: Contém a classe Main para execução da aplicação

## Classes Principais

### Pacote model

#### Classes Abstratas

- **Usuario**: Classe base para todos os tipos de usuário do sistema
- **Produto**: Classe base para todos os tipos de produto do sistema

#### Interfaces

- **Vendavel**: Define operações relacionadas à venda de produtos
- **Estocavel**: Define operações relacionadas ao controle de estoque

#### Classes de Usuário

- **Vendedor**: Representa um vendedor no sistema, com atributos como comissão e total de vendas
- **Administrador**: Representa um administrador no sistema, com atributos como nível de acesso e departamento
- **Cliente**: Representa um cliente no sistema, com atributos como CPF, endereço e carrinho de compras

#### Classes de Produto

- **Muda**: Representa uma muda de planta, com atributos como espécie, tempo de maturação e tipo de solo
- **Humus**: Representa húmus de minhoca, com atributos como origem, peso da embalagem e composição nutricional
- **Esterco**: Representa esterco animal, com atributos como tipo de animal, processamento e nível de acidez

#### Classe Sistema

- Implementa o padrão Singleton para garantir uma única instância do sistema
- Gerencia usuários e produtos
- Controla operações de login, cadastro e busca

### Pacote service

#### Classe Estoque

- Implementa o padrão Singleton para garantir uma única instância do estoque
- Gerencia a adição, remoção e consulta de produtos no estoque
- Controla a disponibilidade e quantidade de produtos

#### Classe Controlador

- Implementa o padrão Singleton para garantir uma única instância do controlador
- Serve como intermediário entre o Sistema e o Estoque
- Coordena as operações de venda, cadastro e geração de relatórios

### Pacote util

#### Classe Util

- Fornece métodos utilitários para o sistema, como formatação de data e valor
- Implementa validações básicas para CPF e email
- Gera IDs únicos para entidades do sistema

## Testes Unitários

O projeto inclui testes unitários para todas as classes principais, verificando:

- Atributos e métodos das classes de usuário
- Atributos e métodos das classes de produto
- Funcionamento do padrão Singleton
- Operações de cadastro, busca e remoção
- Operações de venda e controle de estoque
- Geração de relatórios

## Princípios de POO Aplicados

1. **Encapsulamento**: Todos os atributos são privados com getters e setters
2. **Herança**: Classes específicas herdam de classes abstratas (ex: Vendedor herda de Usuario)
3. **Polimorfismo**: Métodos abstratos são implementados de forma específica em cada subclasse
4. **Abstração**: Classes abstratas definem comportamentos comuns
5. **Interfaces**: Definem contratos que as classes devem implementar

## Padrões de Projeto Utilizados

1. **Singleton**: Aplicado nas classes Sistema, Estoque e Controlador para garantir instância única
2. **MVC (Model-View-Controller)**: Separação em pacotes model, service (controller) e a interface do usuário

## Execução do Sistema

A classe Main demonstra o funcionamento do sistema com exemplos de:

1. Criação de usuários (Administrador, Vendedor, Cliente)
2. Criação de produtos (Muda, Humus, Esterco)
3. Login no sistema
4. Realização de vendas
5. Geração de relatórios

## Estrutura de Dados

O sistema utiliza ArrayList para armazenar coleções de objetos, como:
- Lista de usuários
- Lista de produtos
- Carrinho de compras
- Histórico de compras

Esta estrutura foi escolhida por sua simplicidade e flexibilidade para adicionar, remover e buscar elementos.
