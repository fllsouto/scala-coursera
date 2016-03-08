package week_1

import scala.annotation.tailrec

object exercise {
	
	def factorial(x: Int): Int = {
		@tailrec
		def baseFactorial(x: Int, acc: Int): Int = {
			if(x == 0) acc
			else baseFactorial((x-1), x*acc)
		}
		
		baseFactorial(x, 1)
	}                                         //> factorial: (x: Int)Int
	
	factorial(5)                              //> res0: Int = 120
	factorial(10)                             //> res1: Int = 3628800
	factorial(20)                             //> res2: Int = -2102132736
}