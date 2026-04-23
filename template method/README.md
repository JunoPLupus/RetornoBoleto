# 🩻 Padrão *Template Method*
É um padrão de projeto comportamental que permite definir um esqueleto/contrato para abstrair comportamentos em comum de objetos, reduzindo a duplicação de código e permitindo a variação de comportamento apenas onde importa.

## 🧐 Quais problemas esse padrão resolve?
No sistema de leitura de boletos, cada banco possui campos diferentes no `arquivo.csv`, mas o processo de leitura segue sempre a mesma estrutura.

A solução implementada [anteriormente](/strategy/orientado-objetos/README.md) envolvia a criação de uma interface `leituraArquivoRetorno` que definia um método `lerArquivo()`. Cada banco tinha sua própria implementação dessa interface, permitindo que o sistema lesse boletos de diferentes bancos de forma flexível e extensível.

Sem um contrato comum, cada implementação de `leituraArquivoRetorno` precisaria duplicar a lógica de estrutura do processo. Com o *Template Method*, o contrato é definido em uma classe/método abstrato, e as variações de comportamento são implementadas em classes/métodos concretos, promovendo a reutilização de código e facilitando a manutenção.

## ➿ Paradigmas utilizados
- [📦 Orientado a Objetos](/template%20method/orientado-objetos/README.md)
- [🔁 Funcional](/template%20method/funcional/README.md)