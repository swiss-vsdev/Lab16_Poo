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

Ce rapport présente une comparaison des performances de deux algorithmes de tri : *SelectionSort* et *YSort*. Les mesures ont été effectuées sur quatre types de tableaux : aléatoires, presque triés, mélangés et inversés, pour des tailles allant de 100 à 100 000 éléments.

== Méthodologie

Les tests ont été réalisés à l'aide de quatre factories :

- *RandomArrayFactory* : tableaux générés aléatoirement
- *AlmostSortedArrayFactory* : tableaux quasi-triés
- *ShuffleArrayFactory* : tableaux avec permutations aléatoires
- *InvertedSortedArrayFactory* : tableaux inversés

Pour chaque combinaison `(algorithme, factory, taille)`, le temps d'exécution est mesuré en millisecondes. Les résultats bruts sont disponibles dans le fichier `results.csv`.
= Tests
Les factories et l'algorithme SelectionSort ont été validés à l'aide des tests unitaires *ScalaTest* (`ArrayFactorySpec.scala` et `SortSpec.scala`) fournis avec le labo.

Aucune erreur n'a été relevée.
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
#align(center)[#image("../TestResults/random.png", width: 50%)]

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
#align(center)[#image("../TestResults/almost.png", width: 50%)]

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
#align(center)[#image("../TestResults/shuffle.png", width: 50%)]

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
#align(center)[#image("../TestResults/inverted.png", width: 50%)]


= Analyse

== Résultats attendus vs observés

On s'attendait à ce que SelectionSort soit plus lent en général, mais on observe également qu'il est aussi lent sur les tableaux presque triés que sur les aléatoires. Normalement on pourrait penser qu'un tableau déjà quasi-trié serait plus rapide à trier, mais SelectionSort parcourt toujours tout le tableau pour trouver le minimum donc ça change rien. YSort lui profite un peu des structures partiellement ordonnées, ce qui explique ses temps encore plus bas sur AlmostSorted.

== Complexité observée

SelectionSort effectue systématiquement $frac(n(n-1), 2)$ comparaisons, ce qui correspond à une complexité :

$ T(n) = O(n^2) $

Cela explique la croissance quadratique observée : passer de $n = 10 000$ à $n = 100 000$ multiplie le temps par environ *50*, cohérent avec $10^2 = 100$ (facteur théorique).

YSort, lui, affiche une croissance quasi-linéaire sur tous les types de tableaux, compatible avec une complexité :

$ T(n) = O(n log n) $

#pagebreak()

=== Temps prévu pour $10^12$ éléments
YSort a une complexité $O(n log n)$. En se basant sur notre mesure à $n = 100k$ ($10^5$) ≈ 10 ms pour des tableaux aléatoires, on peut estimer $t(n)$ :

$ t(n) approx t_(10^5) dot frac(n log n, 10^5 log 10^5) $

Avec $n = 10^12$ :

$ t(10^12) = 10[\m\s] dot frac(10^12 dot log(10^12), 10^5 dot log(10^5)) = 10 dot frac(12 dot 10^7,5) = 24 dot 10^7 [\m\s] = 2. overline(7) "jours" $

=== SelectionSort à $10^12$ éléments

Avec $O(n^2)$, en extrapolant depuis $n = 100k$ ($10^5$) ≈ 11'829 ms :

$ t(n) approx t_(10^5) dot frac((10^n)^2, (10^5)^2) $

Avec $n = 10^12$ :

$ t(10^12) = 11829[\m\s] dot frac((10^12)^2, (10^5)^2) = 11829 dot frac(10^24,10^10) = 11829 dot 10^14[\m\s] approx 375'095 "siècles" $

Autrement dit : totalement impossible et irréaliste.

=== Un algorithme de tri peut-il être meilleur que $O(n)$ ?

Non. Pour trier $n$ éléments, il faut au minimum lire chaque élément une fois. Il est impossible de trier sans connaître les valeurs contenues.

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

#let code_sample = read("code/SortApplication.scala")
#figure(code()[
  #raw(code_sample, lang: "scala")
], caption: "Code du fichier `SortApplication.scala`")

#pagebreak()

#let code_sample = read("code/Part2.scala")
#figure(code()[
  #raw(code_sample, lang: "scala")
], caption: "Code du fichier `Part2.scala`")


#let code_sample = read("code/SortingTester.scala")
#figure(code()[
  #raw(code_sample, lang: "scala")
], caption: "Code du fichier `SortingTester.scala`")

#let code_sample = read("code/Task8.scala")
#figure(code()[
  #raw(code_sample, lang: "scala")
], caption: "Code du fichier `Task8.scala`")

= Conclusion

*YSort* surpasse largement *SelectionSort* sur tous les types et tailles de tableaux testés. L'écart croît avec $n$, confirmant la différence de classe de complexité entre $O(n^2)$ et $O(n log n)$. Pour tout usage pratique au-delà de quelques milliers d'éléments, SelectionSort devient inutilisable.