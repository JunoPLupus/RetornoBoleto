# 🧩 Padrão *Strategy*
É um padrão de projeto comportamental que permite definir uma família de algoritmos, encapsular cada um deles e torná-los intercambiáveis. Ele permite que o algoritmo varie independentemente dos clientes que o utilizam.

## 🧐 Quais problemas esse padrão resolve?
No sistema de leitura de boletos, cada banco possui campos diferentes no `arquivo.csv`, o que inicialmente poderia ser resolvido apenas com herança, uma superclasse `LeituraArquivoRetorno` e subclasses para cada banco. No entanto, isso levaria a um código rígido e muito acoplado, onde a adição de um novo banco exigiria a modificação da hierarquia de classes, o que aumentaria o risco de quebrar a aplicação por ter que modificar código legado. 

O padrão _Strategy_ resolve esse problema priorizando composição sobre herança, onde `ProcessorBoleto` tem uma referência a um objeto de uma classe que implementa a interface `LeituraArquivoRetorno`, permitindo que o comportamento de leitura seja alterado em tempo de execução sem modificar o código do cliente.

## ➿ Paradigmas utilizados
- [📦 Orientado a Objetos](/strategy/orientado-objetos/README.md)

- A implementação deste padrão com o paradigma funcional exige um contrato, exigindo a implementação do _Template Method_, você pode conferir a implementação do _Strategy_+_Template Method_ com o paradigma funcional [aqui](/template%20method/funcional/README.md).

