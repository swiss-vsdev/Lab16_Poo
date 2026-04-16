class SortApplication {
  def displayArray(a: Array[Int]) : Unit = {
    println("Output Array : \n" + a.mkString(" ") + "\n")
  }
}

object RunSortApplication extends App {
  val SortApp1 = new SortApplication
  println("---------------------------------------------------------")
  println("Testing ArrayFactories :")
  println("---------------------------------------------------------")
  println("RandomArrayFactory :")
  SortApp1.displayArray(RandomArrayFactory.create(20))
  println("ShuffleArrayFactory :")
  SortApp1.displayArray(ShuffleArrayFactory.create(20))
  println("AlmostSortedArrayFactory :")
  SortApp1.displayArray(AlmostSortedArrayFactory.create(20))
  println("---------------------------------------------------------")
  println("Testing Sorting Algorithm :")
  println("---------------------------------------------------------")
  println("SelectionSort - RandomArrayFactory :")
  SortApp1.displayArray(SelectionSort.sort(RandomArrayFactory.create(20)))
  println("SelectionSort - ShuffleArrayFactory :")
  SortApp1.displayArray(SelectionSort.sort(ShuffleArrayFactory.create(20)))
  println("SelectionSort - AlmostSortedArrayFactory :")
  SortApp1.displayArray(SelectionSort.sort(AlmostSortedArrayFactory.create(20)))
}