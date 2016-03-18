package week_3

object rationals {
  val x = new Rational(1, 3)                      //> x  : week_3.Rational = 1/3
  val y = new Rational(5, 7)                      //> y  : week_3.Rational = 5/7
  val z = new Rational(3, 2)                      //> z  : week_3.Rational = 3/2
  -z                                              //> res0: week_3.Rational = 3/-2
  x - y - z                                       //> res1: week_3.Rational = -79/42
  y + y                                           //> res2: week_3.Rational = 10/7
  x < y                                           //> res3: Boolean = true
  x max y                                         //> res4: week_3.Rational = 5/7
  new Rational(2)                                 //> res5: week_3.Rational = 2/1
}

class Rational(x: Int, y: Int) {
	require(y != 0, "Denominator must be different of Zero")
	private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
	// Def é util para quando não é chamado frequentemente
	def numer = x
	def denom = y
	
	def this(x: Int) = this(x, 1)
	
	def + (that: Rational) =
		new Rational(
			numer * that.denom + that.numer * denom,
			denom * that.denom)
	
	override def toString = {
		val g = gcd(x, y)
		numer / g + "/" + denom / g
	}
	
	def unary_- : Rational = new Rational (-numer, denom)
	
	def - (that: Rational) =
		this + -that
	
	def < (that: Rational) = numer * that.denom < that.numer * denom
	
	def max(that: Rational) = if(this < that) that else this
}