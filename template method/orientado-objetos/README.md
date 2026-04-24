# 🧐 Solução Implementada com Paradigma Orientado a Objetos

## 🧑‍🤝‍🧑 O Problema: Duplicação de Código

Após implementar o padrão [**Strategy**](../../strategy/orientado-objetos/README.md), cada banco teria sua própria classe implementando a interface `LeituraArquivoRetorno`. O problema é que **todas essas classes duplicariam o mesmo código** de leitura de arquivo!

Se precisássemos alterar a forma como lemos o arquivo (por exemplo, para lidar com um novo formato), teríamos que modificar todas as classes, aumentando o risco de erros.

> Isso violaria o princípio _DRY_ (_Don't Repeat Yourself_) e tornaria a manutenção difícil.

---

## 🩻 A Solução: Template Method

O padrão Template Method elimina essa duplicação criando uma classe abstrata que encapsula o fluxo comum e deixa apenas a parte específica (neste caso, criar o boleto) para as subclasses implementarem.

### 1️⃣ A Estrutura Comum na Classe Abstrata

A classe `LeituraArquivoRetornoTemplate` implementa a interface `LeituraArquivoRetorno` e define o fluxo fixo:

```java
abstract class LeituraArquivoRetornoTemplate implements LeituraArquivoRetorno {

    protected abstract Boleto criarBoleto(String linha);  // ← Só isso varia

    @Override
    public final List<Boleto> lerArquivo(String nomeArquivo) {
        // 1. Lê as linhas do arquivo (chamando o método auxiliar lerLinhasArquivo())
        // 2. Para cada linha, cria um boleto (chamando o método abstrato criarBoleto())
    }

    // 3. Método auxiliar reutilizável (comum a todos)
    private List<String> lerLinhasArquivo(String nomeArquivo) {
        // Lógica para ler as linhas do arquivo e retornar uma lista de strings
    }
}
```

> O método lerArquivo() é público `final` e implementado uma única vez, garantindo que todos sigam o mesmo fluxo.

### 2️⃣ As Implementações Concretas

Agora cada banco implementa apenas o método abstrato `criarBoleto()`, sem duplicar a lógica de leitura de arquivo:

#### Banco do Brasil

```java
public class LeituraArquivoRetornoBancoBrasil extends LeituraArquivoRetornoTemplate {

    @Override
    protected Boleto criarBoleto(String linha) {

        // ... campos específicos do Banco do Brasil

    }
}
```

#### Bradesco

```java
public class LeituraArquivoRetornoBradesco extends LeituraArquivoRetornoTemplate {

    @Override
    protected Boleto criarBoleto(String linha) {

        // ... campos específicos do banco Bradesco

    }
}
```

> Vantagem: Cada classe é pequena, clara e apenas implementa o comportamento que é diferente!

### 3️⃣ Usando Ambos os Padrões: Strategy + Template Method

O `ProcessadorBoletos` trabalha com a interface `LeituraArquivoRetorno` (_Strategy_), mas na prática recebe instâncias de classes que estendem `LeituraArquivoRetornoTemplate` (_Template Method_):

```java
public class ProcessadorBoletos {
    private LeituraArquivoRetorno leituraArquivoRetorno;  // Strategy

    public ProcessadorBoletos(LeituraArquivoRetorno leituraArquivoRetorno) {
        this.leituraArquivoRetorno = leituraArquivoRetorno;
    }

    public void setLeituraArquivoRetorno(LeituraArquivoRetorno leituraArquivoRetorno) {
        this.leituraArquivoRetorno = leituraArquivoRetorno;
    }

    public void processarBoleto(String nomeArquivo) {
        // Delega para a implementação específica (que usa Template Method internamente)
        List<Boleto> boletos = this.leituraArquivoRetorno.lerArquivo(nomeArquivo);
        imprimirBoletosNaTela(boletos);
    }
}
```

Executando:

```java
public class Main {
    public static void main(String[] args) {
        // Injeta a estratégia Banco do Brasil
        ProcessadorBoletos processador = new ProcessadorBoletos(
                new LeituraArquivoRetornoBancoBrasil());
        processador.processarBoleto("banco-brasil.csv");

        // Troca a estratégia para Bradesco em tempo de execução
        processador.setLeituraArquivoRetorno(
                new LeituraArquivoRetornoBradesco());
        processador.processarBoleto("bradesco.csv");
    }
}
```


Juntos, eles formam uma solução poderosa que é flexível (_Strategy_), manutenível (_Template Method_) e sem duplicação (_DRY_).
