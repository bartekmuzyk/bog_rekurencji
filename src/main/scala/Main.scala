import scala.annotation.tailrec

/**
 * Przykład:
 * {{{
 *   myReverse(List("a", "b", "c")) == List("c", "b", "a")
 * }}}
 * @param list Lista do odwrócenia
 * @param acc Pozostawić domyślne
 * @tparam A Typ wartości w liście
 * @return Odwróconą listę `list`
 */
@tailrec
def myReverse[A](list: List[A], acc: List[A] = Nil): List[A] = list match {
  case head :: tail => myReverse(tail, head :: acc)
  case Nil => acc
}


/**
 * Przykład:
 * {{{
 *   mySize(List("a", "b", "c")) == 3
 * }}}
 * @param list Lista do policzenia długości
 * @param acc Pozostawić domyślne
 * @tparam A Typ wartości w liście
 * @return Długość listy `list`
 */
@tailrec
def mySize[A](list: List[A], acc: Int = 0): Int = list match {
  case _ :: tail => mySize(tail, acc + 1)
  case Nil => acc
}


/**
 * Przykład:
 * {{{
 *   myTake(List("a", "b", "c", "d", "e"), 3) == List("a", "b", "c")
 * }}}
 * @param list Lista, z której będą brane pierwsze elementy
 * @param amount Ilość elementów z początku listy do wzięcia
 * @param acc Pozostawić domyślne
 * @tparam A Typ wartości w liście
 * @return Pierwsze `amount` elementów z listy `list`
 */
@tailrec
def myTake[A](list: List[A], amount: Int = 1, acc: List[A] = Nil): List[A] = list match {
  case head :: tail if amount > 0 => myTake(tail, amount - 1, head :: acc)
  case head :: tail => myReverse(acc)
  case Nil => myReverse(acc)
}


/**
 * Przykład:
 * {{{
 *   myDrop(List("a", "b", "c", "d", "e"), 3) == List("d", "e")
 * }}}
 * @param list Lista, z której będą usuwane pierwsze elementy
 * @param amount Ilość elementów z początku listy do usunięcia
 * @param acc Pozostawić domyślne
 * @tparam A Typ wartości w liście
 * @return Wszystkie elementy z listy `list` oprócz pierwszych `amount` elementów
 */
@tailrec
def myDrop[A](list: List[A], amount: Int = 1, acc: List[A] = Nil): List[A] = list match {
  case head :: tail if amount > 0 => myDrop(tail, amount - 1, acc)
  case head :: tail => list
  case Nil => list
}


/**
 * Przykład:
 * {{{
 *   myDrop(List("a", "b", "c", "d", "e"), 3) == List("a", "b")
 * }}}
 * @param list Lista, z której będą usuwane ostatnie elementy
 * @param amount Ilość elementów z końca listy do usunięcia
 * @tparam A Typ wartości w liście
 * @return Wszystkie elementy z listy `list` oprócz ostatnich `amount` elementów
 */
def myDropRight[A](list: List[A], amount: Int = 1): List[A] = {
  myReverse(myDrop(myReverse(list), amount))
}

/**
 * Przykład:
 * {{{
 *   myFilter(List("Ala", "ma", "kota", "test", "foo", "bar", "a"), elem => elem.length == 3)
 *   // Powyższe zwróci tylko elementy mające długość 3:
 *   //   List("Ala", "foo", "bar")
 * }}}
 * @param list Lista, z której będą wybierane elementy
 * @param check Funkcja, przyjmująca element z listy `list` i zwracająca `true` lub `false` w zależności od tego, czy dana wartość ma zostać przyjęta czy odrzucona
 * @param acc Pozostawić domyślne
 * @tparam A Typ wartości w liście
 * @return Lista, zawierające wszystkie te elementy z listy `list`, które po włożeniu do funkcji `check` zwróciły `true`
 */
@tailrec
def myFilter[A](list: List[A], check: A => Boolean, acc: List[A] = Nil): List[A] = list match {
  case head :: tail if check(head) => myFilter(tail, check, head :: acc)
  case head :: tail => myFilter(tail, check, acc)
  case Nil => myReverse(acc)
}


/**
 * Przykład:
 * {{{
 *   myGlue(List("a", "b", "c"), List("d", "e", "f")) == List("a", "b", "c", "d", "e", "f")
 * }}}
 * @param list1 Pierwsza lista
 * @param list2 Druga lista
 * @tparam A Typ wartości pierwszej listy
 * @tparam B Typ wartości drugiej listy
 * @return Lista powstała ze złączenia `list1` oraz `list2` (z zachowaniem oryginalnej kolejności)
 */
def myGlue[A, B](list1: List[A], list2: List[B]): List[A | B] = {
  @tailrec
  def helper(l1: List[A | B] = list1, l2: List[A | B] = list2, acc: List[A | B] = Nil): List[A | B] = (l1, l2) match {
    case (head :: tail, _) => helper(tail, l2, head :: acc)
    case (Nil, head :: tail) => helper(Nil, tail, head :: acc)
    case (Nil, Nil) => myReverse(acc)
  }
  helper()
}


/**
 * Przykład:
 * {{{
 *   myFlatten(List(List(1, 2), List(3), 4, List(5, 6, 7, 8), List(9), 10)) == List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
 * }}}
 * @param list Lista do spłaszczenia
 * @return Lista `list`, której podlisty zostały "wyciągnięte na wierzch"
 */
def myFlatten(list: List[Any]): List[Any] = list match {
  case Nil => Nil
  case (head: List[Any]) :: tail => myGlue(myFlatten(head), myFlatten(tail))
  case head :: tail => head :: myFlatten(tail)
}


/**
 * Przykład:
 * {{{
 *   myMinDouble(List(1.2, 0.4, 3.0, 4.2)) == 0.4
 * }}}
 * @param l Lista wejściowa
 * @return Najmniejsza wartość z listy `l`
 */
def myMinDouble(l: List[Double]): Option[Double] = {
  @tailrec
  def helper(list: List[Double], acc: Double): Double = list match {
    case head :: tail if head < acc => helper(tail, head)
    case head :: tail => helper(tail, acc)
    case Nil => acc
  }

  if mySize(l) == 0 then None
  else Some(helper(myTail(l), myHead(l).get))
}

/**
 * Przykład:
 * {{{
 *   myMinInt(List(1, 0, 3, 4)) == 0
 * }}}
 * @param l Lista wejściowa
 * @return Najmniejsza wartość z listy `l`
 */
def myMinInt(l: List[Int]): Option[Int] = myMinDouble(l.map(_.toDouble)) match {
  case Some(value) => Some(value.toInt)
  case None => None
}

/**
 * Przykład:
 * {{{
 *   myMaxDouble(List(1.2, 0.4, 3.0, 4.2)) == 4.2
 * }}}
 * @param l Lista wejściowa
 * @return Największa wartość z listy `l`
 */
def myMaxDouble(l: List[Double]): Option[Double] = {
  @tailrec
  def helper(list: List[Double], acc: Double): Double = list match {
    case head :: tail if head > acc => helper(tail, head)
    case head :: tail => helper(tail, acc)
    case Nil => acc
  }

  if mySize(l) == 0 then None
  else Some(helper(myTail(l), myHead(l).get))
}

/**
 * Przykład:
 * {{{
 *   myMaxInt(List(1, 0, 3, 4)) == 4
 * }}}
 * @param l Lista wejściowa
 * @return Największa wartość z listy `l`
 */
def myMaxInt(l: List[Int]): Option[Int] = myMaxDouble(l.map(_.toDouble)) match {
  case Some(value) => Some(value.toInt)
  case None => None
}


/**
 * Przykład:
 * {{{
 *   myHead(List("a", "b", "c", "d", "e", "f")) == "a"
 * }}}
 * @param list Lista wejściowa
 * @tparam A Typ wartości w liście
 * @return Pierwszy element z listy `list`
 */
def myHead[A](list: List[A]): Option[A] = list match {
  case head :: tail => Some(head)
  case Nil => None
}

/**
 * Przykład:
 * {{{
 *   myTail(List("a", "b", "c", "d", "e", "f")) == List("b", "c", "d", "e", "f")
 * }}}
 * @param list Lista wejściowa
 * @tparam A Typ wartości w liście
 * @return Wszystkie elementy z listy `list` oprócz pierwszego
 */
def myTail[A](list: List[A]): List[A] = list match {
  case head :: tail => tail
  case Nil => Nil
}


/**
 * Przykład:
 * {{{
 *   myZip(List("a", "b", "c", "d", "e", "f")) => List(("a", 0), ("b", 1), ("c", 2), ("d", 3), ("e", 4), ("f", 5))
 * }}}
 * @param list Lista wejściowa
 * @param acc Pozostawić domyślne
 * @param index Pozostawić domyślne
 * @tparam A Typ wartości w liście
 * @return Elementy z listy `list` z "podoczepianymi" indeksami
 */
@tailrec
def myZip[A](list: List[A], acc: List[(A, Int)] = Nil, index: Int = 0): List[(A, Int)] = list match {
  case head :: tail => myZip(tail, (head, index) :: acc, index + 1)
  case Nil => myReverse(acc)
}

/**
 * Ta funkcja robi to samo co foldLeft wbudowany:
 * {{{
 *   val lista = List("abc", "ab", "a", "test")
 *
 *   myFoldLeft(lista, 0)((acc, elem) => acc + elem.length)
 *   // to wyżej to to samo co to niżej
 *   lista.foldLeft(0)((acc, elem) => acc + elem.length)
 * }}}
 * Przykład powyżej liczy sumę długości słów w liście.
 * @param list Lista wejściowa
 * @param startAcc Startowa wartość dla akumulatora
 * @param func Funkcja zmieniająca akumulator (przyjmuje po kolei parametry: akumulator, element z listy)
 * @tparam A Typ wartości w liście
 * @tparam B Typ akumulatora
 * @return Wartość znajdująca się w akumulatorze po ostatnim powtórzeniu
 */
def myFoldLeft[A, B](list: List[A], startAcc: B)(func: (B, A) => B): B = {
  @tailrec
  def helper(l: List[A], acc: B): B = l match {
    case head :: tail => helper(tail, func(acc, head))
    case Nil => acc
  }

  helper(list, startAcc)
}

/**
 * @param list Lista wejściowa
 * @param func Funkcja przyjmująca jakiś element z listy `list` i zwracająca coś, na co ma go zamienić
 * @tparam A Typ wartości w liście wejściowej
 * @tparam B yp wartości w liście wyjściowej
 * @return Lista `list`, której każdy element został zamieniony na to, co zwróciło `func` dla tego elementu
 */
def myMap[A, B](list: List[A])(func: A => B): List[B] = {
  @tailrec
  def helper(l: List[A], acc: List[B]): List[B] = {
    l match {
      case head :: tail => helper(tail, func(head) :: acc)
      case Nil => myReverse(acc)
    }
  }

  helper(list, Nil: List[B])
}

@main
def mainProg(): Unit = {
  println(
    myReverse(List(1, 2, 3, 4, 5))
  )

  println(
    mySize(List(2, 2, 7, 7))
  )

  println(
    myTake(List(1, 2, 3, 4, 5, 6, 7, 8), 3)
  )

  println(
    myDrop(List(1, 2, 3, 4, 5), 2)
  )

  println(
    myFilter(List(1, 2, 3, 4, 5), e => e % 2 != 0)
  )

  println(
    myGlue(List(1, 2, 3), List(4, 5, 6))
  )

  println(
    myFlatten(List(List(1, 2, 3), 4, List(5, 6), 7, List(8)))
  )

  println(
    myMinInt(List(4, 3, 1, 5))
  )

  println(
    myMaxInt(List(4, 3, 1, 5))
  )

  println(
    myZip(List("a", "b"))
  )

  println(
    myDropRight(List(1, 2, 3, 4, 5, 6), 2)
  )

  println(
    myFoldLeft(List("abc", "ab", "a", "test"), 0)((acc, elem) => acc + elem.length)
  )

  println(
    List("abc", "ab", "a", "test").foldLeft(0)((acc, elem) => acc + elem.length)
  )

  println(
    myMap(List("abc", "test", "uwu", "xd", "x"))(elem => elem.length)
  )
}
