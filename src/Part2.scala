trait Sort {
  def sort(a: Array[Int]): Array[Int]
}

object SelectionSort extends Sort {
  override def sort(a: Array[Int]): Array[Int] = {
    // println("Input Array :\n" + a.mkString(" ") + "\n")
    for(j <- a.indices) {
      var minIndex : Int = j
      for (i <- j until a.length-1) {
        if (a(i) > a(i + 1)){
          if (a(i+1) < a(minIndex)){
            minIndex = i + 1
          }
        }
      }
      val tmp: Int = a(j)
      a(j) = a(minIndex)
      a(minIndex) = tmp
    }
    a
  }
}