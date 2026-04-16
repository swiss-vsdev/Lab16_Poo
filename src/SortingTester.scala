import scala.collection.mutable.ArrayBuffer
import java.io._

class SortingTester {
  val algos : Array[Sort] = Array[Sort](SelectionSort, YSort)

  val s : ArrayBuffer[Int] = ArrayBuffer[Int]()
  s.addOne(100)
  s.addOne(1000)
  s.addOne(10000)
  s.addOne(50000)
  s.addOne(100000)
  val size : Array[Int] = s.toArray

  def run() : Unit = {
    val pw = new PrintWriter(new FileOutputStream("./TestResults/results.csv"))
    pw.println("Algorithme,Taille,Temps[ms]")
    for(i <- size){
      val a : Array[Int] = RandomArrayFactory.create(i)
      for(algo <- algos) {
        val a1 : Array[Int] = a.clone()
        var algoName = algo.getClass.getSimpleName()
        algoName = algoName.substring(0,(algoName.length-1))

        pw.print(algoName + ",")
        pw.print(i + ",")

        println(s"Algo : $algoName - Size : $i")
        val st = System.currentTimeMillis()

        algo.sort(a1)

        val duration = System.currentTimeMillis() - st

        pw.println(duration)
        println("Duration : " + duration + " [ms]\n")
      }
    }
    pw.close()
  }
}

object Tester extends App {
  val sortingTest = new SortingTester

  sortingTest.run()
}