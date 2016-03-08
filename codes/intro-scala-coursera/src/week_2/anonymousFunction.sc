package week_2

object exerciseFunctions {

	//Normal Functions
	def sumInts(a: Int, b: Int): Int =
		if (a  > b) 0 else a + sumInts(a + 1, b)
                                                  //> sumInts: (a#93416: Int#1093, b#93417: Int#1093)Int#1093
	
	def cube(x: Int): Int = x * x * x         //> cube: (x#93665: Int#1093)Int#1093
	
	def sumCubes(a: Int, b: Int): Int =
		if (a > b) 0 else cube(a) + sumCubes(a+1, b)
                                                  //> sumCubes: (a#93669: Int#1093, b#93670: Int#1093)Int#1093
	
	sumInts(1, 100)                           //> res0: Int#1093 = 5050
	// 1 + 8 + 27 + 64
	sumCubes(1,4)                             //> res1: Int#1093 = 100
	
	//High Order Functions
	
	def sum(f: Int => Int, a: Int, b: Int): Int =
		if (a > b) 0 else f(a) + sum(f, a + 1, b)
                                                  //> sum: (f#93676: Int#1093 => Int#1093, a#93677: Int#1093, b#93678: Int#1093)In
                                                  //| t#1093
	
	
	def sumIntsHigh(a: Int, b: Int): Int = sum(id, a, b)
                                                  //> sumIntsHigh: (a#93685: Int#1093, b#93686: Int#1093)Int#1093
	def sumCubesHigh(a: Int, b: Int): Int = sum(cubes, a, b)
                                                  //> sumCubesHigh: (a#93690: Int#1093, b#93691: Int#1093)Int#1093
	def sumFactorialsHigh(a: Int, b: Int): Int = sum(factorial, a, b)
                                                  //> sumFactorialsHigh: (a#93695: Int#1093, b#93696: Int#1093)Int#1093
	
	def id(x: Int): Int = x                   //> id: (x#93687: Int#1093)Int#1093
	def cubes(x: Int): Int = x * x * x        //> cubes: (x#93692: Int#1093)Int#1093
	def factorial(x: Int): Int = if (x == 0) 1 else x * factorial(x-1)
                                                  //> factorial: (x#93697: Int#1093)Int#1093
	
	sumIntsHigh(1, 100)                       //> res2: Int#1093 = 5050
	sumCubesHigh(1, 5)                        //> res3: Int#1093 = 225
	sumFactorialsHigh(1, 10)                  //> res4: Int#1093 = 4037913

	//Anonymous Function

	def sumTailRec(f: Int => Int)(a: Int, b: Int): Int = {
		def loop(a: Int, acc: Int): Int = {
			if (a > b) acc
			else loop(a + 1, acc + f(a))
		}
		
		loop(a, 0)
	}                                         //> sumTailRec: (f#93708: Int#1093 => Int#1093)(a#93709: Int#1093, b#93710: Int
                                                  //| #1093)Int#1093
	
	def sumIntsAnony(a: Int, b: Int): Int = sumTailRec((x: Int) => x)( a, b)
                                                  //> sumIntsAnony: (a#93720: Int#1093, b#93721: Int#1093)Int#1093
	def sumCubesAnony(a: Int, b: Int): Int = sumTailRec((x: Int) => x * x * x)(a, b)
                                                  //> sumCubesAnony: (a#93724: Int#1093, b#93725: Int#1093)Int#1093
	
	
	sumIntsAnony(1, 100)                      //> res5: Int#1093 = 5050
	sumCubesAnony(1, 5)                       //> res6: Int#1093 = 225
	
	//High Order Returning
	
	def sumHighRet(f: Int => Int): (Int, Int) => Int = {
		def sumF(a: Int, b: Int): Int = {
			if (a > b) 0
			else f(a) + sumF(a + 1, b)
		}
		sumF
	}                                         //> sumHighRet: (f#93731: Int#1093 => Int#1093)(Int#1093, Int#1093) => Int#1093
                                                  //| 
	
	def sumIntsHighRet = sumHighRet((x: Int) => x)
                                                  //> sumIntsHighRet: => (Int#1093, Int#1093) => Int#1093
	def sumCubesHighRet = sumHighRet((x: Int) => x * x * x)
                                                  //> sumCubesHighRet: => (Int#1093, Int#1093) => Int#1093
	sumIntsHighRet(1, 100)                    //> res7: Int#1093 = 5050
	// 1 + 8 + 27 + 64
	sumCubesHighRet(1,4)                      //> res8: Int#1093 = 100
	
	
	def sumWithFunction(f: Int => Int) = sumHighRet(f)
                                                  //> sumWithFunction: (f#93753: Int#1093 => Int#1093)(Int#1093, Int#1093) => Int
                                                  //| #1093
  sumWithFunction((x: Int) => x * x * x) (1, 5)   //> res9: Int#1093 = 225
}