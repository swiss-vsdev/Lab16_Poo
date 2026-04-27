class SizeFinder {
  val algos: Array[Sort] = Array[Sort](SelectionSort, YSort)

  val arrayfactories: Array[ArrayFactory] =
    Array[ArrayFactory](RandomArrayFactory, AlmostSortedArrayFactory, ShuffleArrayFactory, InvertedSortedArrayFactory)

  def run(): Unit = {
    var duration: Long = 0
    var lo: Int = 0
    var hi: Int = 0
    var mid: Int = 0

    for (algo <- algos) {
      val algoname: String = algo.getClass.getSimpleName
      println(s"===== ${algoname.substring(0, algoname.length - 1)} =====")

      // Choix des bornes
      if (algo == SelectionSort) {
        lo = 1000; hi = 100000
      } else {
        lo = 1000; hi = 10000000
      }

      // Recherche dichotomique
      while (hi - lo > 500) {
        mid = (lo + hi) / 2
        val a = arrayfactories(0).create(mid) // Utilisation de RandomArrayFactory

        val st = System.currentTimeMillis()
        algo.sort(a)
        duration = System.currentTimeMillis() - st

        if (duration < 900) {
          lo = mid
        } else if (duration > 1100) {
          hi = mid
        } else {
          lo = hi
        }

        if(hi-lo > 500) println(s"Size : $mid - Duration : ${duration}ms")
      }
      println("------- Size found ! -------")
      println(s"Size : ~$mid - Duration : ${duration}ms ≈ 1s \n")
    }
  }
}

object tableSizeFinder extends App {
  val sf = new SizeFinder
  sf.run()
}