# 🧐 Solução Implementada com Paradigma Orientado a Objetos

## 🧩 A Estratégia: Interface como Contrato

A implementação começa com uma **interface** que define o contrato que todos os bancos devem seguir:

```java
public interface LeituraArquivoRetorno {
    DateTimeFormatter FORMATO_DATA = ofPattern("dd/MM/yyyy");
    DateTimeFormatter FORMATO_DATA_HORA = ofPattern("dd/MM/yyyy HH:mm:ss");
    List<Boleto> lerArquivo(String nomeArquivo);
    Boleto criarBoleto(String nomeArquivo);
}
```

> Essa interface define dois métodos que cada banco deve implementar de sua forma.

---

## A Composição: ProcessadorBoletos

Em vez de usar apenas **herança** (extensão de classe), o `ProcessadorBoletos` usa **composição** para receber a estratégia:

```java
public class ProcessadorBoletos {
    private LeituraArquivoRetorno leituraArquivoRetorno;  // ← Composição: referência a interface
    public ProcessadorBoletos() {}
    public ProcessadorBoletos(LeituraArquivoRetorno leituraArquivoRetorno) {
        this.leituraArquivoRetorno = Objects.requireNonNull(
                leituraArquivoRetorno,
                "LeituraArquivoRetorno não pode ser nulo"
        );
    }
    // Permite trocar a estratégia em tempo de execução
    public void setLeituraArquivoRetorno(LeituraArquivoRetorno leituraArquivoRetorno) {
        this.leituraArquivoRetorno = leituraArquivoRetorno;
    }
    public void processarBoleto(String nomeArquivo) {
        // Delega para a estratégia escolhida
        List<Boleto> boletos = leituraArquivoRetorno.lerArquivo(nomeArquivo);
        imprimirBoletosNaTela(boletos);
    }
}
```

**Observações:**

- `ProcessadorBoletos` **não herda** de `LeituraArquivoRetorno`
- `ProcessadorBoletos` **tem uma referência** a `LeituraArquivoRetorno` (composição)
- A estratégia pode ser **trocada em tempo de execução** via `setLeituraArquivoRetorno()`

---

## Implementações por Banco

### Banco do Brasil

```java
public class LeituraArquivoRetornoBancoBrasil implements LeituraArquivoRetorno {
    @Override
    public final List<Boleto> lerArquivo(String nomeArquivo) {
        // 1. Lê as linhas do arquivo (chamando o método auxiliar lerLinhasArquivo())
        // 2. Para cada linha, cria um boleto (chamando o método abstrato criarBoleto())
    }

    // 3. Método auxiliar reutilizável
    private List<String> lerLinhasArquivo(String nomeArquivo) {
        // Lógica para ler as linhas do arquivo e retornar uma lista de strings
    }
    @Override
    public Boleto criarBoleto(String linha) {
        
        // ... campos específicos do Banco do Brasil
        
    }
}
```

### Bradesco

```java
public class LeituraArquivoRetornoBradesco implements LeituraArquivoRetorno {
    @Override
    public final List<Boleto> lerArquivo(String nomeArquivo) {
        // 1. Lê as linhas do arquivo (chamando o método auxiliar lerLinhasArquivo())
        // 2. Para cada linha, cria um boleto (chamando o método abstrato criarBoleto())
    }

    // 3. Método auxiliar reutilizável
    private List<String> lerLinhasArquivo(String nomeArquivo) {
        // Lógica para ler as linhas do arquivo e retornar uma lista de strings
    }
    
    @Override
    public Boleto criarBoleto(String linha) {
        
        // ... campos específicos do banco Bradesco
        
    }
}
```

---

## Usando as Estratégias

```java
public class Main {
    public static void main(String[] args) {
        // Cria o processador com estratégia Banco do Brasil
        ProcessadorBoletos processador = new ProcessadorBoletos(
                new LeituraArquivoRetornoBancoBrasil()
        );
        processador.processarBoleto("banco-brasil.csv");
        
        // Troca a estratégia para Bradesco em tempo de execução
        processador.setLeituraArquivoRetorno(
                new LeituraArquivoRetornoBradesco());
        processador.processarBoleto("bradesco.csv");
    }
}
```

---

## 🔄 Composição vs Herança

### Abordagem com Herança (❌ Problema)

Se tivéssemos apenas herança, seria assim:

```java
// ❌ Herança: classe pai
public abstract class ProcessadorBoletos {
    public abstract Boleto criarBoleto(String linha);
    public final void processarBoleto(String arquivo) {
        // ... lógica comum
        // chama criarBoleto(linha)
    }
}
// ❌ Herança: subclasses
public class ProcessadorBancoBrasil extends ProcessadorBoletos { ... }
public class ProcessadorBradesco extends ProcessadorBoletos { ... }
// ❌ Usar: obrigado a instanciar subclasses específicas
ProcessadorBoletos processador = new ProcessadorBancoBrasil();  // Tipo fixo!
processador.processarBoleto("arquivo.csv");
// ❌ Trocar para Bradesco: precisa criar nova instância
processador = new ProcessadorBradesco();  // Nova instância necessária
```

**Problemas da herança:**

- Tipo fixo (não é `ProcessadorBoletos`, é `ProcessadorBrazileiro`)
- Acoplamento forte com a hierarquia de classes
- Difícil adicionar novos bancos sem modificar a hierarquia existente
- Hierarquia rígida e complexa

**Vantagens da composição:**

- Tipo consistente (`ProcessadorBoletos` sempre)
- Baixo acoplamento (independente da implementação)
- Fácil adicionar novos bancos sem modificar código existente
- Estratégia pode ser trocada em tempo de execução
- Seguir o princípio _Open/Closed_: aberto para extensão, fechado para modificação

---

## 🚨 Um Problema Identificado: Duplicação de Código

Observe que tanto `LeituraArquivoRetornoBancoBrasil` quanto `LeituraArquivoRetornoBradesco` têm **exatamente o mesmo código** em três lugares:

1. **Método `lerArquivo()`** - idêntico em ambas as classes
2. **Método `lerLinhasArquivo()`** - duplicado entre as duas classes
3. **Lógica de leitura de arquivo** - repetida desnecessariamente
   Isso viola o princípio **DRY** (_Don't Repeat Yourself_)!
   Se precisássemos corrigir um bug no `lerLinhasArquivo()`, teríamos que modificar **ambas as classes**. Isso é propenso a erros.


Veremos como o padrão [**Template Method**](../../template%20method/README.md) pode resolver esse problema eliminando a duplicação e promovendo a reutilização de código comum.