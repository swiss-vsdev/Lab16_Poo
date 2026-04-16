import scala.collection.mutable.ArrayBuffer
import scala.util.Random

trait ArrayFactory {
  def create(size : Int) : Array[Int]
}

object InsertionSort {
  def sort(a: Array[Int]): Array[Int] = {
    for(i <- 1 until a.length){
      var j = i
      val tmp = a(i)
      while ((j > 0) && (a(j-1) > tmp)){
        a(j) = a(j-1)
        j += -1
      }
      a(j) = tmp
    }
    a
  }
}

object RandomArrayFactory extends ArrayFactory {
  override def create(size: Int): Array[Int] = {
    val a : Array[Int] = new Array[Int](size)
    for(i <- a.indices){
      val random = (new Random()).nextInt(size)
      a(i) = random
    }
    a
  }
}

object InvertedSortedArrayFactory extends ArrayFactory {
  override def create(size: Int): Array[Int] = {
    val a : Array[Int] = new Array[Int](size)
    for(i <- a.indices){
      a(i) = size - (i+1)
    }
    a
  }
}

object ShuffleArrayFactory extends ArrayFactory {
  override def create(size: Int): Array[Int] = {
    val a : Array[Int] = new Array[Int](size)
    var cnt = 1
    for(i <- a.indices){
      if((i % 2) == 0){
        a(i) = i/2
      } else if (i == 0) {
        a(i) = i
      } else {
        a(i) = size - cnt
        cnt += 1
      }
    }
    a
  }
}


object Task1 extends App {
  println(RandomArrayFactory.create(11).mkString(" "))
  println(InvertedSortedArrayFactory.create(11).mkString(" "))
  println(ShuffleArrayFactory.create(11).mkString(" "))
}