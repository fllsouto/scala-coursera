# Introdução a Programação Funcional com Scala - Week 3

## Orientação à Objetos em Scala
Podemos definir uma classe em Scala da seguinte maneira:

```
class Rational(x: Int, y: Int) {
  def numer = x
  def denom = y
  def add(that: Rational) =
  	new Rational(
	  numer * that.denom + that.numer * denom,
	  denom * that.denom)
//Aqui estou definindo um outro tipo de construtor
  def this(x: Int) = this(x, 1)

  override def toString = numer + "/" + denom

  def neg = new Rational (numer * (-1), denom)

  def sub(that: Rational) =
	add(that.neg)

//Uma das vantagens de definir um núcleo para a classe é que novos métodos podem ser construídos a partir da base.
//Posso fazer asserções no código utilizando o 'require' e o 'assert'
}
```

A habilidade de escolher diferentes tipos de implementação de dados sem afetar o cliente é chamado de **Abstração de dados** e é pedra fundamental na engenharia de software.

## Operadores
Existe uma precedência nativa de operadores em Scala, elas são:
```
(todos operadores com letras)
|
^
&
<>
= !
:
+ -
* / %

a + b ^? c ?^ d less a ==> b | c == ((a + b) ^? (c ?^ d)) less ((a ==> b) | c)

Além disso podemos reescrever nossas funções com nomes que fazem mais sentido

//Tenho que explicitamente colocar o unary_- para informar que será um operador unário
def unary_- : Rational = new Rational(-this.numer, this.denom)
def - (y: Rational): this + -that

//Com isso posso construir expressões do tipo:
// x + y
// -x
```

## Classes Abstratas
Classes abstratas podem ser definidas como um modelo, o nome dos seus métodos são definidos mas não implementados, algo semelhante a uma interface. Além disso uma classe abstrata não pode ser instanciada

Uma das técnicas de escrita de estrutura de dados em programação funcional é a estrutura persistente, mesmo que eu alterar por exemplo uma arvore binária, o ramo que foi alterado continua existindo intacto e um novo é criado e ambos exitem ao mesmo tempo

```
abstract class IntSet {
	def incl(x: Int): IntSet
	def contains(x: Int): Boolean
	def union(otherSet: IntSet): IntSet
}

//Para evitar excesso de instancias da classe Empty podemos cria-la como um 
//singleton, para isso basta colocar a keyword "object" em vez de classe na 
//definição
object Empty extends IntSet {
	def incl(x: Int): IntSet = new NonEmpty(x, object, object)
	def contains(x: Int): Boolean = false
	override def toString = "."
}

//Na implementação de métodos definidos em uma classe abstrata eu não
//preciso colocar a keyword "override", isso só é necessário quando estou
//de fato sobrescrevendo o método de uma superclasse que a minha herda
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
```

### Dynamic method dispatch
Linguagens orientadas a objeto comumente implementam o despache dinâmico de métodos, uma classe abstrata define um método, que é implementado por outras classes, onde cada um tem seu código proprio, a execução de cada trecho depende do tipo de objeto que o está chamando. Essa é a ideia central do **Polimorfismo**.

## Packages e Imports
Um arquivo pode ser empacotado dentro de um pacote utilizando a diretiva ```package <nome do package>``` e podemos importar outros packages da seguinte forma:

```
import nomedopackage.NomeDaClasse
import nomedopackage.{NomeDaClasse1, NomeDaClasse2}
import nomedopackage._
```

## Traits
Java e Scala são linguagem que aceitam apenas herança simples. Para contornar isso existe o conceito de **Trait**. Seu comportamente é similar as interfaces em Java, porém muito mais poderosas pois podem contar campos e metodos contretos.

```
class Square externs Shape with Planar with Movable ...
```

O tradeoff de traits é que elas não possuem parâmetros, apenas classes podem ter.