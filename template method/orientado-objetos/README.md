# 🧐 Solução Implementada com Paradigma Orientado a Objetos
Foi criada a classe abstrata `LeituraArquivoRetornoTemplate` que implementa a interface/contrato `LeituraArquivoRetorno`.

Considerando que a única diferença entre as classes concretas era a forma de criar o objeto `Boleto`, o método `criarBoleto()` foi definido como um método abstrato na classe `LeituraArquivoRetornoTemplate`. Dessa forma, as classes concretas só precisam implementar esse método específico, enquanto o código comum para ler o arquivo e processar os dados fica centralizado na classe abstrata.

```java
public abstract class LeituraArquivoRetornoTemplate implements LeituraArquivoRetorno {

    // Método abstrato que as classes concretas devem implementar para criar o objeto Boleto
    protected abstract Boleto criarBoleto(String linha);
    
    @Override
    public List<Boleto> lerArquivo(String nomeArquivo) {
        // Código comum para ler o arquivo e processar os dados
        // lerLinhasArquivo(nomeArquivo);
        // ...
        // criarBoleto(linha);
        // ...
    }

    public List<String> lerLinhasArquivo(String nomeArquivo) {
        // Método auxiliar para ler as linhas do arquivo
        // ...
    }
}
```
As classes concretas, como `LeituraArquivoRetornoBancoBrasil` e `LeituraArquivoRetornoBradesco`, estendem a classe `LeituraArquivoRetornoTemplate` e implementam o método `criarBoleto()` de acordo com as necessidades específicas de cada banco.

```java
public class LeituraArquivoRetornoBancoBrasil extends LeituraArquivoRetornoTemplate {
    @Override
    protected Boleto criarBoleto(String linha) {
        // Implementação específica para criar o objeto Boleto do Banco do Brasil
        // ...
    }
}
```