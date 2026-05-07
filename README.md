# algoritmos-paralelos

# Algoritmos de Ordenação Seriais e Paralelos em Java

## Descrição do Projeto

Este projeto tem como objetivo analisar o desempenho de algoritmos de ordenação em ambientes seriais e paralelos utilizando Java. Foram implementadas versões seriais e paralelas dos algoritmos:

- QuickSort
- MergeSort
- InsertionSort
- BubbleSort

Os testes foram realizados variando:
- tamanho do vetor
- tipo de entrada
- quantidade de threads

Os resultados são exportados para arquivos CSV para posterior análise estatística e comparação de desempenho.

---

# Objetivos

- Comparar desempenho entre algoritmos seriais e paralelos
- Avaliar impacto do paralelismo
- Identificar limitações de cada algoritmo
- Gerar métricas para análise de desempenho

---

# Tecnologias Utilizadas

## Java

Utilizado para implementação dos algoritmos e execução dos benchmarks.

### Recursos utilizados:
- ForkJoinPool
- Programação concorrente
- Programação paralela
- Geração de CSV

---

# Estrutura do Projeto

```text
Comp2/
│
├── Main.java
├── BenchmarkRunner.java
├── CsvWriter.java
├── DataGenerator.java
│
├── QuickSortSerial.java
├── QuickSortParalelo.java
│
├── MergeSortSerial.java
├── MergeSortParalelo.java
│
├── InsertionSortSerial.java
├── InsertionSortParalelo.java
│
├── BubbleSortSerial.java
├── BubbleSortParalelo.java
│
├── resultados.csv
└── README.md
```

---

# Algoritmos Implementados

| Algoritmo | Complexidade Média |
|---|---|
| BubbleSort | O(n²) |
| InsertionSort | O(n²) |
| MergeSort | O(n log n) |
| QuickSort | O(n log n) |

---

# Como Executar o Projeto

## 1. Clonar o repositório

```bash
git clone https://github.com/KaylanyVitoria/comp2-algoritmos-paralelos.git
```

---

## 2. Entrar na pasta do projeto

```bash
cd comp2-algoritmos-paralelos
```

---

## 3. Compilar os arquivos Java

```bash
javac *.java
```

---

## 4. Executar o projeto

```bash
java Main
```

---

# Funcionamento

O sistema executa benchmarks dos algoritmos de ordenação em diferentes cenários:

- Vetores aleatórios
- Vetores ordenados
- Vetores reversos
- Vetores quase ordenados

Também são realizados testes utilizando diferentes quantidades de threads:

- 1 thread
- 2 threads
- 4 threads
- 8 threads

Os tempos de execução são registrados automaticamente em arquivos CSV.

---

# Resultados Esperados

Espera-se comparar o desempenho entre algoritmos sequenciais e paralelos, identificando:

- impacto do paralelismo
- custo de gerenciamento de threads
- eficiência dos algoritmos
- comportamento em diferentes tipos de entrada

---

# Integrantes

- Kaylany Vitória Andrade Sousa
- Maria Bianca Holanda

---

# Referências

- Cormen et al. – Introduction to Algorithms
- Java Concurrency in Practice
- Oracle Java Documentation
- ForkJoinPool Documentation
