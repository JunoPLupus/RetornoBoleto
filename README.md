![Java 25](https://img.shields.io/badge/Java-25-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Orientado à Objetos](https://img.shields.io/badge/Orientado_a_Objetos-252525?style=for-the-badge)

# 📖 Sobre o Projeto
Este projeto foi desenvolvido para a disciplina de Padrões de Projeto com o objetivo de implementar o padrão *Strategy*. 

## 🧩 Padrão *Strategy*
É um padrão de projeto comportamental que permite definir uma família de algoritmos, encapsular cada um deles e torná-los intercambiáveis. Ele permite que o algoritmo varie independentemente dos clientes que o utilizam.

## 🤯 Problema Inicial
O projeto começou com a necessidade de criar um sistema de pagamento que pudesse suportar a leitura de arquivos de boletos de diferentes bancos, onde cada um usava diferentes campos.

## 🧐 Solução Implementada
A solução implementada envolvia a criação de uma interface `leituraArquivoRetorno` que definia um método `lerArquivo()`. Cada banco tinha sua própria implementação dessa interface, permitindo que o sistema lesse boletos de diferentes bancos de forma flexível e extensível.

Após a implementação do padrão *Strategy*, o sistema é capaz de ler boletos de diferentes bancos sem a necessidade de modificar o código existente, apenas adicionando novas classes que implementam a interface `leituraArquivoRetorno`.

Porém houve um problema: as classes concretas que implementavam a interface `leituraArquivoRetorno`, por possuírem processos semelhantes na leitura de linhas, acabaram por ter código duplicado. Para resolver isso, foi implementado o padrão *Template Method*, criando uma classe abstrata `LeituraArquivoRetornoTemplate` que implementa a interface e contém o código comum, enquanto as classes concretas apenas implementam o método `criarBoleto()`, já que a diferença entre as classes concretas era apenas na criação do objeto `Boleto`.