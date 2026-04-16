class SortApplication {
  def displayArray(a: Array[Int]) : Unit = {
    println(a.mkString(" "))
  }
}

object RunSortApplication extends App {
  val SortApp1 = new SortApplication
  SortApp1.displayArray(RandomArrayFactory.create(20))
  SortApp1.displayArray(ShuffleArrayFactory.create(20))
  SortApp1.displayArray(AlmostSortedArrayFactory.create(20))
}