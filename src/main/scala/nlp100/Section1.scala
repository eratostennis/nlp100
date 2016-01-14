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

object p3 {
  val str = "Now I need a drink, alcoholic of course, after the heavy lectures involving quantum mechanics."
  def main(args: Array[String]) {
    // use split to tokenize
    str.replaceAll("[,.]","").split(" ").map { w => w.length }.foreach(println)
  }
}

object p4 {
  val str = "Hi He Lied Because Boron Could Not Oxidize Fluorine. New Nations Might Also Sign Peace Security Clause. Arthur King Can."
  val oneWordGroup = Seq(1,5,6,7,8,15,16,19)
  def main(args: Array[String]) {
    val words = str.replaceAll("[,.]","").split(" ").zipWithIndex.map {
      case (word, index) if (oneWordGroup.contains(index)) => word.substring(0,1) -> index
      case (word, index) if (!oneWordGroup.contains(index)) => word.substring(0,2) -> index
    }.toMap
    println(words)
  }
}

object p5 {
  val str1 = "I am an NLPer"
  val n1 = 2
  def main(args: Array[String]) {
    println(wordNgram(str1, n1))
    println(charNgram(str1, n1))
  }
  def ngram[T](n: Int)(xs: Iterable[T]) = xs.sliding(n)
  def wordNgram(str: String, n: Int): List[Iterable[String]] = {
    return ngram(n)(str.split(" ")).toList
  }
  def charNgram(str: String, n: Int): List[Iterable[Char]] = {
    return ngram(n)(str).toList
  }
}

object p6 {
  val str1 = "paraparaparadise"
  val str2 = "paragraph"
  def main(args: Array[String]) {
    val X = p5.charNgram(str1, 2).toSet
    val Y = p5.charNgram(str2, 2).toSet
    println(X.union(Y))
    println(X.intersect(Y))
    println(X.diff(Y))
    println(s"X contains se? ${X.contains("se")}")
    println(s"Y contains se? ${Y.contains("se")}")
  }
}

object p7 {
  val usage = "--x String --y String --z String"
  def main(args: Array[String]) {
    // Option settings 
    if (args.length == 0) println(usage)
    val arglist = args.toList
    type OptionMap = Map[Symbol, Any]

    def nextOption(map : OptionMap, list: List[String]) : OptionMap = { 
      def isSwitch(s : String) = (s(0) == '-')
      list match {
        case Nil => map 
        case "--x" :: value :: tail =>
                               nextOption(map ++ Map('x -> value.toString), tail)
        case "--y" :: value :: tail =>
                               nextOption(map ++ Map('y -> value.toString), tail)
        case "--z" :: value :: tail =>
                               nextOption(map ++ Map('z -> value.toString), tail)
        case string :: opt2 :: tail if isSwitch(opt2) =>  
                               nextOption(map ++ Map('infile -> string), list.tail)
        case string :: Nil =>  nextOption(map ++ Map('infile -> string), list.tail)
        case option :: tail => println("Unknown option "+option) 
                               exit(1) 
      }   
    }   
    val options = nextOption(Map(),arglist)
    println(s"${options.get('x).get.toString}時の${options.get('y).get.toString}は${options.get('z).get.toString}")
  }
}
