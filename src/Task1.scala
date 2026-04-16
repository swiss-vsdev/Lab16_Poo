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

object FisherYates {
  def mix(a: Array[Int]): Array[Int] = {
    for(i <- (a.length-1) to 1 by -1){
      val j = (new Random()).nextInt(i)
      val tmp = a(j)
      a(j) = a(i)
      a(i) = tmp
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

object AlmostSortedArrayFactory extends ArrayFactory {
  override def create(size: Int): Array[Int] = {
    val p : Double = 0.3 // 30%
    val times : Int = (math.round(size * p)).toInt
    val a : Array[Int] = new Array[Int](size)

    for(i <- a.indices){
      a(i) = i
    }

    for(i <- 0 until times){
      var k : Int = 0
      while (k == 0){
        k = (new Random()).nextInt(a.length-1)
      }
      val j = (new Random()).nextInt(k)
      val tmp = a(j)
      a(j) = a(i)
      a(i) = tmp
    }
    a
  }
}
