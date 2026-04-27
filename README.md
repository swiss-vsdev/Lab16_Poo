# Labo 16 – Tris et complexité

**Cours** : Programmation orientée-objets (101.2)  
**Professeur** : Prof. Dr P.-A. Mudry  
**Auteurs** : Gabriel Zeizer & Aurélien Santi  
**Semestre** : Printemps 2025-2026

---

## Description

Benchmark comparant **SelectionSort** et **YSort** sur quatre types de tableaux, pour des tailles allant de 100 à 100 000 éléments. Les résultats sont analysés en termes de complexité temporelle et de ratio de performance.

---

## Structure du projet

```

src/main/scala/
├── Task1.scala          \# Trait ArrayFactory + 4 implémentations
├── SortApplication.scala\# Affichage et vérification des factories
├── Part2.scala          \# Trait Sort + SelectionSort
├── SortingTester.scala  \# Benchmark complet → results.csv
└── Task8.scala          \# Recherche dichotomique taille ~1s

TestResults/
└── results.csv          \# Résultats bruts des mesures

report/
└── report.pdf           \# Rapport final

```

---

## Lancer les benchmarks

### Tester les factories & SelectionSort
```

run RunSortApplication

```

### Lancer le benchmark complet (génère results.csv)
```

run Tester

```
> Mesure 10 répétitions par combinaison (algo × factory × taille).  
> Joue Windows XP startup/shutdown 🎵

### Recherche dichotomique (taille triée en ~1s)
```

run tableSizeFinder

```

---

## Factories disponibles

| Factory | Description |
|---|---|
| `RandomArrayFactory` | Nombres aléatoires |
| `AlmostSortedArrayFactory` | Trié à 70%, 30% mal placés |
| `ShuffleArrayFactory` | Pattern `{0, n-1, 1, n-2, ...}` |
| `InvertedSortedArrayFactory` | Décroissant de n-1 à 0 |

---

## Résultats clés

| n | SelectionSort | YSort | Ratio |
|---|---|---|---|
| 1 000 | 5 ms | 1 ms | 5× |
| 10 000 | 215 ms | 4 ms | 54× |
| 100 000 | 11 829 ms | 10 ms | 1183× |

- **SelectionSort** : O(n²) — insensible au type de tableau  
- **YSort** : O(n log n) — rapide sur tous les types


