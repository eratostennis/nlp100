import scala.io.Source

object p10 {
  def main(args: Array[String]) {
    println(Source.fromInputStream(getClass.getResourceAsStream("/hightemp.txt")).getLines.size)
  }
}
