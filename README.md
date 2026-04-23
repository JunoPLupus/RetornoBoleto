![Java 25](https://img.shields.io/badge/Java-25-333333?style=for-the-badge&logo=openjdk&logoColor=5C2F1F&labelColor=ED8B00)
![Orientado à Objetos](https://img.shields.io/badge/Orientado_a_Objetos-252525?style=for-the-badge)
![Funcional](https://img.shields.io/badge/Funcional-252525?style=for-the-badge)

# 📖 Sobre o Projeto
Este projeto foi desenvolvido para a disciplina de Padrões de Projeto com o objetivo de implementar padrões de projeto em situações reais. 


## 🤯 Problema Inicial
O projeto começou com a necessidade de criar um sistema de pagamento que pudesse suportar a leitura de arquivos de boletos de diferentes bancos, onde cada um usava diferentes campos.

Para resolver essas questões reduzindo a duplicação de código e seguindo os princípios SOLID, foram utilizados padrões de projeto gradualmente.


## Sobre as Implementações de Paradigmas
A implementação OO tende a ser mais explícita e navegável — você vê a hierarquia de classes, sabe exatamente onde cada banco está implementado, é fácil de rastrear. Já a funcional tende a ser mais concisa, mas exige que o leitor entenda bem `Function<>` e composição de funções pra não parecer mágica.

Em aplicações maiores a diferença se acentua: o _OO_ escala bem quando você tem muitos comportamentos variando juntos (um banco novo provavelmente teria mais de um método diferente), enquanto o funcional escala bem quando os comportamentos são independentes e combináveis.

Nesta simples aplicação ambas as abordagens são equivalentes em complexidade. O objetivo aqui é apenas demonstrar como o padrão se manifesta de formas diferentes dependendo do paradigma adotado.

## 🧩 Padrões utilizados
- 🧩 [Strategy](strategy/README.md)
- 🩻 [Template Method](template%20method/README.md)
