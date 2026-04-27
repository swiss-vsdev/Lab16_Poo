import scala.collection.mutable.ArrayBuffer
import java.io._
import javax.sound.sampled.AudioSystem

class SortingTester {
  val algos: Array[Sort] = Array[Sort](SelectionSort, YSort)

  val arrayfactories: Array[ArrayFactory] =
    Array[ArrayFactory](RandomArrayFactory, AlmostSortedArrayFactory, ShuffleArrayFactory, InvertedSortedArrayFactory)

  val s: ArrayBuffer[Int] = ArrayBuffer[Int]()
  s.addOne(100)
  s.addOne(1000)
  s.addOne(10000)
  s.addOne(50000)
  s.addOne(100000)
  val size: Array[Int] = s.toArray

  def run(): Unit = {
    val musicfile2 = new File("./src/main/scala/windows-xp-startup.wav")
    val clip2 = AudioSystem.getClip()
    val audio2 = AudioSystem.getAudioInputStream(musicfile2)
    clip2.open(audio2)
    clip2.start()

    val pw = new PrintWriter(new FileOutputStream("./TestResults/results.csv"))
    pw.println("Algorithme,ArrayFactory,Taille,Temps[ms]")


    //for(reps <- 0 to 0){
      for (factory <- arrayfactories) {
        for (i <- size) {
          val a: Array[Int] = factory.create(i)
          for (algo <- algos) {
            for(reps <- 0 to 9){
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
      }
    //}

    val musicfile = new File("./src/main/scala/windows-xp-shutdown.wav")
    val clip = AudioSystem.getClip()
    val audio = AudioSystem.getAudioInputStream(musicfile)
    clip.open(audio)
    clip.start()
    pw.close()

    val time: Long = System.currentTimeMillis() + 7500

    //to not kill the fun
    while (System.currentTimeMillis() < time) {
      for (i <- 0 to 9) {
        Thread.sleep(15)
      }
    }
  }
}
object Tester extends App {
  val sortingTest = new SortingTester

  sortingTest.run()
}