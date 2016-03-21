package week1

import scala.annotation.tailrec

object exercise {

  def factorial(x: Int): Int = {
    @tailrec
    def baseFactorial(x: Int, acc: Int): Int = {
      if (x == 0) acc
      else baseFactorial((x - 1), x * acc)
    }

    baseFactorial(x, 1)
  }
  factorial(12)
}