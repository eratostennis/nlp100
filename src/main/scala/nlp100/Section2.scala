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
