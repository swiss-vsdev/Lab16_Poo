class SortingTesterOld {
  def selectionSortRandomArray(size : Int): Unit = {
    println("---------------------------------------------------------")
    println("Selection Sort - Random Array - " + size + " :")
    println("---------------------------------------------------------")
    val a = RandomArrayFactory.create(size)
    val st = System.currentTimeMillis()
    SelectionSort.sort(a)
    val duration = System.currentTimeMillis() - st
    println("Duration : " + duration + " [ms]\n")
  }
  def selectionSortInvertedSortedArray(size : Int): Unit = {
    println("---------------------------------------------------------")
    println("Selection Sort - InvertedSortedArray - " + size + " :")
    println("---------------------------------------------------------")
    val a = InvertedSortedArrayFactory.create(size)
    val st = System.currentTimeMillis()
    SelectionSort.sort(a)
    val duration = System.currentTimeMillis() - st
    println("Duration : " + duration + " [ms]\n")
  }
  def selectionSortShuffleArray(size : Int): Unit = {
    println("---------------------------------------------------------")
    println("Selection Sort - ShuffleArray - " + size + " :")
    println("---------------------------------------------------------")
    val a = ShuffleArrayFactory.create(size)
    val st = System.currentTimeMillis()
    SelectionSort.sort(a)
    val duration = System.currentTimeMillis() - st
    println("Duration : " + duration + " [ms]\n")
  }
}

object OldTester extends App {
  val sortingTest = new SortingTesterOld

  sortingTest.selectionSortRandomArray(1000)
  sortingTest.selectionSortRandomArray(10000)
  sortingTest.selectionSortRandomArray(100000)

  sortingTest.selectionSortInvertedSortedArray(1000)
  sortingTest.selectionSortInvertedSortedArray(10000)
  sortingTest.selectionSortInvertedSortedArray(100000)

  sortingTest.selectionSortShuffleArray(1000)
  sortingTest.selectionSortShuffleArray(10000)
  sortingTest.selectionSortShuffleArray(100000)
}