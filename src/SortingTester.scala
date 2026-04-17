import scala.collection.mutable.ArrayBuffer
import java.io._

class SortingTester {
  val algos: Array[Sort] = Array[Sort](SelectionSort, YSort)

  val arrayfactories: Array[ArrayFactory] =
    Array[ArrayFactory](RandomArrayFactory, AlmostSortedArrayFactory, ShuffleArrayFactory)

  val s: ArrayBuffer[Int] = ArrayBuffer[Int]()
  s.addOne(100)
  s.addOne(1000)
  s.addOne(10000)
  s.addOne(50000)
  s.addOne(100000)
  val size: Array[Int] = s.toArray

  def run(): Unit = {
    val pw = new PrintWriter(new FileOutputStream("./TestResults/results.csv"))
    pw.println("Algorithme,Taille,Temps[ms]")
    for (factory <- arrayfactories) {
      for (i <- size) {
        val a: Array[Int] = factory.create(i)
        for (algo <- algos) {
          val a1: Array[Int] = a.clone()
          var algoName = algo.getClass.getSimpleName()
          var factoryName = factory.getClass.getSimpleName()
          algoName = algoName.substring(0, (algoName.length - 1))
          factoryName = factoryName.substring(0, (factoryName.length - 1))

          pw.print(algoName + ",")
          pw.print(factoryName + ",")
          pw.print(i + ",")

          println(s"Algo : $algoName - Factory : $factoryName - Size : $i")
          val st = System.currentTimeMillis()

          algo.sort(a1)

          val duration = System.currentTimeMillis() - st

          pw.println(duration)
          println("Duration : " + duration + " [ms]\n")

        }
      }
    }
    pw.close()
  }
}
object Tester extends App {
  val sortingTest = new SortingTester

  sortingTest.run()
}