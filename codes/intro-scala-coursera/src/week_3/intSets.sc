package week_3

object insets {
	val t1 = new NonEmpty(3, new Empty, new Empty)
	val t2 = t1 incl 4
	val t3 = t2 incl 42
}

abstract class IntSet {
	def incl(x: Int): IntSet
	def contains(x: Int): Boolean
	def union(otherSet: IntSet): IntSet
}

object Empty extends IntSet {
	def incl(x: Int): IntSet = new NonEmpty(x, object, object)
	def contains(x: Int): Boolean = false
	override def toString = "."
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {

	def contains(x: Int): Boolean =
		if (x < elem) left contains x
		else if (x > elem) right contains x
		else true

	def incl(x: Int): IntSet =
		if(x < elem) new NonEmpty(elem, left incl x, right)
		else if (x > elem) new NonEmpty(elem, left, right incl x)
		else this
	
	override def toString = "{" + left  + elem + right + "}"
}