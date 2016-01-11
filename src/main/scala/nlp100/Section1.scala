object p0 {
  val str = "stressed"
  def main(args: Array[String]) {
    println(str.reverse)
  }
}

object p1 {
  val str = "パタトクカシーー"
  def main(args: Array[String]) {
    // use collect(/map) with index => zipWithIndex
    val ans = str.zipWithIndex.collect {
      case (word, index) if (index % 2 != 0) => word
    }.mkString
    println(ans)
  }
}

object p2 {
  val str1 = "パトカー"
  val str2 = "タクシー"
  def main(args: Array[String]) {
    // aggregate the contents of two lists into a single list of pairs 
    val ans = str1.zip(str2).flatMap { w => Seq(w._1, w._2) }.mkString
    println(ans)
  }
}
