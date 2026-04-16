import scala.collection.mutable.ArrayBuffer

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
    for(i <- size){
      val a : Array[Int] = RandomArrayFactory.create(i)
      for(algo <- algos) {
        val a1 : Array[Int] = a.clone()
        val algoName = algo.getClass.getSimpleName()
        println(s"Algo : ${algoName.substring(0,(algoName.length-1))} - Size : $i")
        val st = System.currentTimeMillis()
        algo.sort(a1)
        val duration = System.currentTimeMillis() - st
        println("Duration : " + duration + " [ms]\n")
      }
    }
  }
}

object Tester extends App {
  val sortingTest = new SortingTester

  sortingTest.run()
}