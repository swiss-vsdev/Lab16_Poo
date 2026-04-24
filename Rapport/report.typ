#import "@preview/isc-hei-report:0.7.1" : *

#let doc_language = "fr"

#show: project.with(
  title: "Labo 16 – Tris et complexité",
  subtitle: [Comparaison entre SelectionSort et YSort],
  authors: ("Gabriel Zeizer & Aurélien Santi",),
  date: datetime(year: 2026, month: 4, day: 24),

  course-name: "Programation orientée-objets",
  course-supervisor: "Prof. Dr P.-A. Mudry",
  semester: "Semestre de printemps",
  academic-year: "2025-2026",

  logo: image("figs/isc_logo.svg"),
  cover-image: image("figs/cover_image_placeholder.png"),
  cover-image-height: 8cm,
  cover-image-caption: [Benchmark de tri -- SelectionSort vs YSort],

  show-toc: true,
  language: doc_language,
  code-theme: "bluloco-light",
)

#import "@preview/acrostiche:0.7.0": *
#include "acronyms.typ"

= Introduction

Ce rapport présente une comparaison des performances de deux algorithmes de tri : *SelectionSort* et *YSort*. Les mesures ont été effectuées sur trois types de tableaux : aléatoires, presque triés, mélangés et inversés, pour des tailles allant de 100 à 100 000 éléments.

== Méthodologie

Les tests ont été réalisés à l'aide de trois factories :

- *RandomArrayFactory* : tableaux générés aléatoirement
- *AlmostSortedArrayFactory* : tableaux quasi-triés
- *ShuffleArrayFactory* : tableaux avec permutations aléatoires

Pour chaque combinaison `(algorithme, factory, taille)`, le temps d'exécution est mesuré en millisecondes. Les résultats bruts sont disponibles dans le fichier `results.csv`.

= Résultats

== Tableaux aléatoires

#figure(table(
  columns: (1.5fr, 1fr, 1fr),
  align: center + horizon,
  stroke: none,
  table.header[*Taille*][*SelectionSort (ms)*][*YSort (ms)*],
  [100],    [1],     [2],
  [1 000],  [5],     [1],
  [10 000], [215],   [4],
  [50 000], [2910], [4],
  [100 000],[11829],[10],
), caption: [Temps d'exécution sur `RandomArrayFactory`])

On constate une grosse différence à partir de $n = 10k$.

À $n = 100k$, SelectionSort est environ *1200x plus lent* que YSort.

== Tableaux presque triés

#figure(table(
  columns: (1.5fr, 1fr, 1fr),
  align: center + horizon,
  stroke: none,
  table.header[*Taille*][*SelectionSort (ms)*][*YSort (ms)*],
  [100],    [0],   [0],
  [1 000],  [0],   [0],
  [10 000], [29],  [1],
  [50 000], [908], [3],
  [100 000],[3834],[6],
), caption: [Temps d'exécution sur `AlmostSortedArrayFactory`])

== Tableaux mélangés

#figure(table(
  columns: (1.5fr, 1fr, 1fr),
  align: center + horizon,
  stroke: none,
  table.header[*Taille*][*SelectionSort (ms)*][*YSort (ms)*],
  [100],    [0],   [0],
  [1 000],  [0],   [0],
  [10 000], [26],  [0],
  [50 000], [628], [2],
  [100 000],[2519],[4],
), caption: [Temps d'exécution sur `ShuffleArrayFactory`])

== Tableaux inversés

#figure(table(
  columns: (1.5fr, 1fr, 1fr),
  align: center + horizon,
  stroke: none,
  table.header[*Taille*][*SelectionSort (ms)*][*YSort (ms)*],
  [100],    [0],   [0],
  [1 000],  [0],   [0],
  [10 000], [26],  [1],
  [50 000], [635], [2],
  [100 000],[2620],[4],
), caption: [Temps d'exécution sur `InvertedSortedArrayFactory`])


= Analyse

== Complexité observée

SelectionSort effectue systématiquement $frac(n(n-1), 2)$ comparaisons, ce qui correspond à une complexité :

$ T(n) = O(n^2) $

Cela explique la croissance quadratique observée : passer de $n = 10 000$ à $n = 100 000$ multiplie le temps par environ *50*, cohérent avec $10^2 = 100$ (facteur théorique).

YSort, lui, affiche une croissance quasi-linéaire sur tous les types de tableaux, compatible avec une complexité :

$ T(n) = O(n log n) $

== Insensibilité au type de tableau

SelectionSort reste lent même sur des tableaux presque triés, car son mécanisme de sélection du minimum ne tire aucun avantage de l'ordre existant. YSort, en revanche, bénéficie potentiellement des structures partiellement ordonnées.

== Ratio de performance

Le rapport $frac(T_"SelectionSort"(n), T_"YSort"(n))$ sur RandomArrayFactory :

#figure(table(
  columns: (1fr, 1fr),
  align: center + horizon,
  stroke: none,
  table.header[*Taille*][*Ratio*],
  [1 000],  [5×],
  [10 000], [54×],
  [50 000], [728x],
  [100 000],[1183×],
), caption: [Ratio SelectionSort / YSort sur tableaux aléatoires])

#pagebreak()
= Code Scala

#let code_sample = read("code/Task1.scala")
#figure(code()[
  #raw(code_sample, lang: "scala")
], caption: "Code du fichier `Task1.scala`")


#let code_sample = read("code/Part2.scala")
#figure(code()[
  #raw(code_sample, lang: "scala")
], caption: "Code du fichier `Part2.scala`")


#let code_sample = read("code/SortingTester.scala")
#figure(code()[
  #raw(code_sample, lang: "scala")
], caption: "Code du fichier `SortingTester.scala`")


= Conclusion

*YSort* surpasse largement *SelectionSort* sur tous les types et tailles de tableaux testés. L'écart croît avec $n$, confirmant la différence de classe de complexité entre $O(n^2)$ et $O(n log n)$. Pour tout usage pratique au-delà de quelques milliers d'éléments, SelectionSort devient inutilisable.
