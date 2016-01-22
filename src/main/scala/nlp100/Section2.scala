import scala.io.Source
import java.io.PrintWriter

object p10 {
  def main(args: Array[String]) {
    println(Source.fromInputStream(getClass.getResourceAsStream("/hightemp.txt")).getLines.size)
  }
}

object p11 {
  val str = "replace	one	tab	to	one	space"
  def main(args: Array[String]) {
    println(str.replaceAll("\t", " "))
  }
}

object p12 {
  def main(args: Array[String]) {
    val col1 = new PrintWriter("col1.txt")
    val col2 = new PrintWriter("col2.txt")
    Source.fromInputStream(getClass.getResourceAsStream("/hightemp.txt")).getLines().foreach { line =>
      val words = line.split("\t")
      col1.write(words(0)+"\n")
      col2.write(words(1)+"\n")
    }
    col1.close()
    col2.close()
  }
}

object p13 {
  def main(args: Array[String]) {
    Source.fromInputStream(getClass.getResourceAsStream("/col1.txt")).getLines().zip(Source.fromInputStream(getClass.getResourceAsStream("/col2.txt")).getLines()).foreach {
     x => println(x._1 + "\t" + x._2)
   }
  }
}

object p14 {
  def main(args: Array[String]) {
    Source.fromFile(getClass.getResource("/").getPath + "hightemp.txt").getLines().take(args(0).toInt).foreach(println)
  }
}

object p15 {
  def main(args: Array[String]) {
    Source.fromFile(getClass.getResource("/").getPath + "hightemp.txt").getLines().toStream.takeRight(args(0).toInt).foreach(println)
  }
}

object p16 {
  def main(args: Array[String]) {
    val n = args(0).toInt
    val splits = (1 to n).map(i => new PrintWriter(s"split.txt.$i"))
    val (lines, lines2) = Source.fromFile(getClass.getResource("/").getPath + "hightemp.txt").getLines().duplicate 

    // size method need to iterate
    val size = lines2.size/n
    (0 until n).foreach(i => lines.take(size).foreach(splits(i).println))
    splits.foreach(_.close)
  }
}
