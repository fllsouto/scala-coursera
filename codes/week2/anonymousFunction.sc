package week2

object exerciseFunctions {

  //Normal Functions
  def sumInts(a: Int, b: Int): Int =
    if (a  > b) 0 else a + sumInts(a + 1, b)
  //> sumInts: (a: Int, b: Int)Int

  def cube(x: Int): Int = x * x * x         //> cube: (x: Int)Int

  def sumCubes(a: Int, b: Int): Int =
    if (a > b) 0 else cube(a) + sumCubes(a+1, b)
  //> sumCubes: (a: Int, b: Int)Int

  sumInts(1, 100)                           //> res0: Int = 5050
  // 1 + 8 + 27 + 64
  sumCubes(1,4)                             //> res1: Int = 100

  //High Order Functions

  def sum(f: Int => Int, a: Int, b: Int): Int =
    if (a > b) 0 else f(a) + sum(f, a + 1, b)
  //> sum: (f: Int => Int, a: Int, b: Int)Int


  def sumIntsHigh(a: Int, b: Int): Int = sum(id, a, b)
  //> sumIntsHigh: (a: Int, b: Int)Int
  def sumCubesHigh(a: Int, b: Int): Int = sum(cubes, a, b)
  //> sumCubesHigh: (a: Int, b: Int)Int
  def sumFactorialsHigh(a: Int, b: Int): Int = sum(factorial, a, b)
  //> sumFactorialsHigh: (a: Int, b: Int)Int

  def id(x: Int): Int = x                   //> id: (x: Int)Int
  def cubes(x: Int): Int = x * x * x        //> cubes: (x: Int)Int
  def factorial(x: Int): Int = if (x == 0) 1 else x * factorial(x-1)
  //> factorial: (x: Int)Int

  sumIntsHigh(1, 100)                       //> res2: Int = 5050
  sumCubesHigh(1, 5)                        //> res3: Int = 225
  sumFactorialsHigh(1, 10)                  //> res4: Int = 4037913

  //Anonymous Function

  def sumTailRec(f: Int => Int)(a: Int, b: Int): Int = {
    def loop(a: Int, acc: Int): Int = {
      if (a > b) acc
      else loop(a + 1, acc + f(a))
    }

    loop(a, 0)
  }                                         //> sumTailRec: (f: Int => Int)(a: Int, b: Int)Int

  def sumIntsAnony(a: Int, b: Int): Int = sumTailRec((x: Int) => x)( a, b)
  //> sumIntsAnony: (a: Int, b: Int)Int
  def sumCubesAnony(a: Int, b: Int): Int = sumTailRec((x: Int) => x * x * x)(a, b)
  //> sumCubesAnony: (a: Int, b: Int)Int


  sumIntsAnony(1, 100)                      //> res5: Int = 5050
  sumCubesAnony(1, 5)                       //> res6: Int = 225

  //High Order Returning

  def sumHighRet(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int = {
      if (a > b) 0
      else f(a) + sumF(a + 1, b)
    }
    sumF
  }                                         //> sumHighRet: (f: Int => Int)(Int, Int) => Int

  def sumIntsHighRet = sumHighRet((x: Int) => x)
  //> sumIntsHighRet: => (Int, Int) => Int
  def sumCubesHighRet = sumHighRet((x: Int) => x * x * x)
  //> sumCubesHighRet: => (Int, Int) => Int
  sumIntsHighRet(1, 100)                    //> res7: Int = 5050
  // 1 + 8 + 27 + 64
  sumCubesHighRet(1,4)                      //> res8: Int = 100


  def sumWithFunction(f: Int => Int) = sumHighRet(f)
  //> sumWithFunction: (f: Int => Int)(Int, Int) => Int
  sumWithFunction((x: Int) => x * x * x) (1, 5)   //> res9: Int = 225


  def product(f: Int => Int)(a: Int, b: Int): Int =
    if(a > b) 1 else f(a) * product(f)(a + 1, b)
  //> product: (f: Int => Int)(a: Int, b: Int)Int

  def factorialBase(x: Int): Int = product(x => x)(1, x)
  //> factorialBase: (x: Int)Int

  factorialBase(10)                               //> res10: Int = 3628800

  def basicOperation(f: Int => Int)(g: (Int, Int) => Int)(base: Int)(a: Int, b: Int): Int =
    if (a > b) base else g(f(a), basicOperation(f)(g)(base)(a + 1, b))
  //> basicOperation: (f: Int => Int)(g: (Int, Int) => Int)(base: Int)(a: Int, b:
  //|  Int)Int

  def sumSerieBasic(a: Int, b: Int): Int =
    basicOperation(x => x)((y, z) => y + z)(0)(a, b)
  //> sumSerieBasic: (a: Int, b: Int)Int

  def productSerieBasic(a: Int, b: Int): Int =
    basicOperation(x => x)((y, z) => y * z)(1)(a, b)
  //> productSerieBasic: (a: Int, b: Int)Int

  sumSerieBasic(4, 4)                       //> res11: Int = 4
  sumSerieBasic(2, 4)                       //> res12: Int = 9

  productSerieBasic(1, 10)                  //> res13: Int = 3628800
}