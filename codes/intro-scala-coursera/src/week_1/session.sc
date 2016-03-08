package week_1

import scala.annotation.tailrec

object session {

	def abs(x: Double): Double = if (x < 0) -x else x
  def sqrt(x: Double) = {
  
		def sqrtIter(guess: Double): Double =
			if (isGoodEnough(guess)) guess
			else sqrtIter(improve(guess))
			
		def isGoodEnough(guess: Double): Boolean =
			abs(guess * guess - x)/x < 0.001
		
		def improve(guess: Double) =
			(guess + x/ guess) / 2
		
		sqrtIter(1.0)
	}
	
	
	sqrt(2)
	sqrt(4)
	sqrt(1e-6)
	sqrt(10e60)
	
	
	def blockTest(x: Int) = {
		def pow2(x: Int): Int = x*x
		def pow3(x: Int): Int = pow2(x)*x
		
		pow3(x)
	}
	
	blockTest(10)
	
	@tailrec
	def gcd(a: Int, b: Int): Int = {
		if(b == 0) a
		else gcd(b, a % b)
	}
	
	gcd(15, 6)
}