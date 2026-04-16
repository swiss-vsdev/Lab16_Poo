import scala.util.Random

trait ArrayFactory {
  def create(size : Int) : Array[Int]
}

object RandomArrayFactory extends ArrayFactory {
  override def create(size: Int): Array[Int] = {
    val tmp : Array[Int] = new Array[Int](size)
    for(i <- tmp.indices){
      val random = (new Random()).nextInt(size)
      tmp(i) = random
    }
    tmp
  }

}

object Task1 extends App {
  println(RandomArrayFactory.create(11).mkString(" "))
}