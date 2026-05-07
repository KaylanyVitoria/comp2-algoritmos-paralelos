import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

sns.set(style="whitegrid")

# Lê o CSV corretamente (já com cabeçalho)
df = pd.read_csv("resultados.csv")

# 🔥 REMOVE QUALQUER LINHA QUE SEJA CABEÇALHO REPETIDO
df = df[df["algoritmo"] != "algoritmo"]

# 🔥 CONVERTE TUDO QUE PRECISA PRA NÚMERO
df["tempo_ms"] = pd.to_numeric(df["tempo_ms"], errors="coerce")
df["threads"] = pd.to_numeric(df["threads"], errors="coerce")
df["tamanho"] = pd.to_numeric(df["tamanho"], errors="coerce")
df["execucao"] = pd.to_numeric(df["execucao"], errors="coerce")

# 🔥 REMOVE LINHAS QUE DERAM ERRO
df = df.dropna()

# 🔥 GARANTE TIPOS CERTOS
df = df.astype({
    "threads": "int",
    "tamanho": "int",
    "execucao": "int",
    "tempo_ms": "float"
})

print("\nTipos das colunas:")
print(df.dtypes)

print("\nAmostra dos dados:")
print(df.head())

# ===== GRÁFICO =====
media = df.groupby("algoritmo")["tempo_ms"].mean()

media.plot(kind="bar")
plt.title("Tempo médio por algoritmo")
plt.ylabel("Tempo (ms)")
plt.xticks(rotation=0)
plt.show()