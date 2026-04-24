# 🧐 Solução Implementada com Paradigma Funcional

Diferente da abordagem orientada a objetos, no paradigma funcional esses padrões tendem a ser implementados simultaneamente e resolvem problemas distintos.

---

## 1. O Template Method - A Estrutura

No arquivo `ProcessadorBoletos.java`, o método `processar()` define o fluxo comum:

```java
public final void processar(String arquivo){
    List<Boleto> boletos = lerArquivo(arquivo);
  
    for (Boleto boleto : boletos) 
        System.out.println(boleto);
}
```

O modificador `final` evita que o fluxo seja alterado/sobreescrito. A estratégia varia apenas em **como** cada boleto é criado, não em **como** o arquivo é processado.

---

## 2. A Strategy - A Função Variável

A parte que varia é injetada como uma função no construtor:

```java
public class ProcessadorBoletos {
    public Function<String, Boleto> criarBoleto;  // Strategy como função
  
    public ProcessadorBoletos(Function<String, Boleto> criarBoleto) {
        this.criarBoleto = criarBoleto;
    }
  
    public void setCriarBoleto(Function<String, Boleto> criarBoleto) {
        this.criarBoleto = criarBoleto;  // Trocar strategy em tempo de execução
    }
}
```

Um contrato é definido com `Function<String, Boleto>`onde este atributo `criarBoleto` pode receber qualquer função, contanto que tenha uma `String`como parâmetro e retorne um objeto`Boleto`.

---

## 3. Aplicando a Strategy no Fluxo

Dentro do método `lerArquivo()`, a função `criarBoleto` é executada:

```java
private List<Boleto> lerArquivo(String nomeArquivo) {
    final List<String> linhasArquivo = lerLinhasArquivo(nomeArquivo);
    List<Boleto> boletos = new ArrayList<>(linhasArquivo.size());
  
    for (String linha : linhasArquivo) {
        var boleto = criarBoleto.apply(linha);  // Strategy é executada aqui
        boletos.add(boleto);
    }
    return boletos;
}
```

---

## 4. Diferentes Estratégias - Uma para Cada Banco

#### **Banco do Brasil**

```java
public class LeituraArquivoRetornoBancoBrasil {
    public static Boleto criarBoleto(String linha) {
        var boleto = new Boleto();
  
        // ... campos específicos do Banco do Brasil
  
        return boleto;
    }
}
```

#### **Bradesco**

```java
public class LeituraArquivoRetornoBradesco {
    public static Boleto criarBoleto(String linha) {
        var boleto = new Boleto();
  
        // ... campos específicos do Bradesco
  
        return boleto;
    }
}
```

## 5. Selecionando a Estratégia em Tempo de Execução

```java
static void main() {
    final var processador = new ProcessadorBoletos(
            LeituraArquivoRetornoBancoBrasil::criarBoleto);  // Strategy 1
    processador.processar("banco-brasil.csv");
  
    processador.setCriarBoleto(
            LeituraArquivoRetornoBradesco::criarBoleto);     // Strategy 2
    processador.processar("bradesco.csv");
}
```

---

## **🤔 Por que Strategy e Template Method Ficam Juntos no Paradigma Funcional?**

### No Paradigma Orientado a Objetos:

```
LeituraArquivoRetorno (interface)
└── Template Method (estrutura fixa)
    │
    └── Subclasses
        ├── LeituraArquivoRetornoA (Override método criarBoleto)
        ├── LeituraArquivoRetornoB (Override método criarBoleto)
        └── LeituraArquivoRetornoN (Override método criarBoleto)
```

---

### No Paradigma Funcional:

```
ProcessadorBoletos (Template Method)
│
├── Estrutura fixa: lerArquivo() + processar()
│
└── Função injetada (Strategy): criarBoleto()
    ├── Function 1: LeituraArquivoRetornoBancoBrasil::criarBoleto
    ├── Function 2: LeituraArquivoRetornoBradesco::criarBoleto
    └── Function N: Qualquer função que aceite String e retorne Boleto
```

### Razão da Fusão:

1. **Abstração da mesma natureza**: Ambos usam `Function<String, Boleto>` como abstração única
2. **Reutilização direta**: A mesma classe (`ProcessadorBoletos`) encapsula ambos os padrões
3. **Sem hierarquia de classes**: Funções são cidadãos de primeira classe, dispensam polimorfismo de classes
4. **Composição sobre herança**: As estratégias são compostas (injetadas), não herdadas
