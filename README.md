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

Os resultados são exportados para arquivos CSV e posteriormente analisados utilizando Python para geração de gráficos e análise estatística.

---

# Objetivos

- Comparar desempenho entre algoritmos seriais e paralelos
- Avaliar impacto do paralelismo
- Identificar limitações de cada algoritmo
- Gerar métricas e gráficos para análise

---

# Tecnologias Utilizadas

## Java
Utilizado para implementação dos algoritmos e execução dos benchmarks.

### Recursos utilizados:
- ForkJoinPool
- Programação concorrente
- Geração de CSV

## Python
Utilizado para análise dos resultados e geração de gráficos.

### Bibliotecas:
- Pandas
- Matplotlib
- Seaborn

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
├── analise.py
├── resultados.csv
└── README.md
