package week_2
import math.abs

object fixedPoint {
  val tolerance = 0.00000001                      //> tolerance  : Double = 1.0E-8
  def isCloseEnough(x: Double, y: Double) =
  	abs((x- y) / x) / x < tolerance           //> isCloseEnough: (x: Double, y: Double)Boolean
  
  def fixedPoint(f: Double => Double)(firstGuess: Double) = {
  	def iterate(guess: Double): Double = {
  		println("Guess: " + guess)
  		val next = f(guess)
  		println("Next: " + next)
  		if(isCloseEnough(guess, next)) next
  		else iterate(next)
  	}
  	iterate(firstGuess)
  }                                               //> fixedPoint: (f: Double => Double)(firstGuess: Double)Double
 	def averageDamp(f: Double => Double)(x: Double) =
 		(x + f(x))/2                      //> averageDamp: (f: Double => Double)(x: Double)Double

	  def sqrt(x: Double) =
	  	fixedPoint(averageDamp(y => x / y))(1)
                                                  //> sqrt: (x: Double)Double
  sqrt(4)                                         //> Guess: 1.0
                                                  //| Next: 2.5
                                                  //| Guess: 2.5
                                                  //| Next: 2.05
                                                  //| Guess: 2.05
                                                  //| Next: 2.000609756097561
                                                  //| Guess: 2.000609756097561
                                                  //| Next: 2.0000000929222947
                                                  //| Guess: 2.0000000929222947
                                                  //| Next: 2.000000000000002
                                                  //| Guess: 2.000000000000002
                                                  //| Next: 2.0
                                                  //| res0: Double = 2.0
  
  sqrt(2)                                         //> Guess: 1.0
                                                  //| Next: 1.5
                                                  //| Guess: 1.5
                                                  //| Next: 1.4166666666666665
                                                  //| Guess: 1.4166666666666665
                                                  //| Next: 1.4142156862745097
                                                  //| Guess: 1.4142156862745097
                                                  //| Next: 1.4142135623746899
                                                  //| Guess: 1.4142135623746899
                                                  //| Next: 1.414213562373095
                                                  //| res1: Double = 1.414213562373095
}