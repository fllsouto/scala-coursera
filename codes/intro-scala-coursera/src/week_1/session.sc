package week_1

object session {

	def abs(x: Double): Double = if (x < 0) -x else x
                                                  //> abs: (x: Double)Double
  def sqrt(x: Double) = {
  
		def sqrtIter(guess: Double): Double =
			if (isGoodEnough(guess)) guess
			else sqrtIter(improve(guess))
			
		def isGoodEnough(guess: Double): Boolean =
			abs(guess * guess - x)/x < 0.001
		
		def improve(guess: Double) =
			(guess + x/ guess) / 2
		
		sqrtIter(1.0)
	}                                         //> sqrt: (x: Double)Double
	
	
	sqrt(2)                                   //> res0: Double = 1.4142156862745097
	sqrt(4)                                   //> res1: Double = 2.000609756097561
	sqrt(1e-6)                                //> res2: Double = 0.0010000001533016628
	sqrt(10e60)                               //> res3: Double = 3.162294643473563E30
	
	
	def blockTest(x: Int) = {
		def pow2(x: Int): Int = x*x
		def pow3(x: Int): Int = pow2(x)*x
		
		pow3(x)
	}                                         //> blockTest: (x: Int)Int
	
	blockTest(10)                             //> res4: Int = 1000
}