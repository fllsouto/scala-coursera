# Introdução a Programação Funcional com Scala - Week 2

## Funções de primeira ordem

Linguagens funcionais tratam funções como valores de primeira classe, isso quer dizer que como qualquer outro tipo de parâmetro, uma função pode ser passada como parâmetro e retornada, isso cria uma maior flexibilidade na composição de funções. Uma função que aceita outras como parâmetro ou as devolve é chamada de **função de primeira ordem**

Uma função anônima é uma definição que não recebe nome, ela são consideradas **Syntax Sugar** pois podem ser sempre definidas como uma função nomeada.

### Retorno de primeira ordem

```
//Função de primeira ordem, que recebe e retorna outra funções
def sumHighRet(f: Int => Int): (Int, Int) => Int = {
	def sumF(a: Int, b: Int): Int = {
		if (a > b) 0
		else f(a) + sumF(a + 1, b)
	}
	sumF
}

//Criando uma closure
def sumIntsHighRet = sumHighRet((x: Int) => x)
def sumCubesHighRet = sumHighRet((x: Int) => x * x * x)

//Criando uma fábrica de clojures
def sumWithFunction(f: Int => Int) = sumHighRet(f)

//Essa chamada recebe qualquer função como parâmetro e retorna a definição com um bind na função anônima
//Com essa nova função posso chamar a sequência desejada, com isso posso compor um somatório de a até b com
//qualquer outra função.

//A avaliação das funções ocorre da esquerda para a direita, de dentro para fora.
sumWithFunction((x: Int) => x * x * x) (1, 5)
```

###Expansão de uma lista com multiplos parâmetros

```
//A seguinte definição quando n > 1
def f(args_1)...(args_n) = E

//equivale a
def f(args_1)...(args_n-1) = { def g(args_n) = E;g}
//onde g é um novo identificador

//Usando funções anônimas fica:
def f(args_1)...(args_n-1) = (args_n => E)

//Repetindo o processo n vezes:
def f = (args_1 => ... ((args_n => E)))

//O nome desse processo é Currying, com essa técnica podemos transformar uma função de n parâmetros em n composições de 1 parâmetro
```

Podemos definir a geradora de somatórias de uma forma mais clara, porém sem recursão de cauda:
```
def sum(f: Int => Int)(a: Int, b: Int): Int = 
	if(a > b) 0 else f(a) + sum(f)(a + 1, b)
```

###Exemplo do Ponto-fixo

Um número **x** é chamado de **ponto fixo de uma função **f** se :

```f(x) = x```. Para achar o ponto fixo podemos aplicar sucessivamente o resultado de ```f(x) = x``` a ela mesma até que não varie mais(ou que a mudança seja suficientemente pequena).

Ex
```
f(x) = 1 + x/2
O ponto fixo é 2 => f(2) = 1 + 2/2 = 1 + 1 = 2
```

###Substituições de função

A substituição de funções de primeira ordem é uma ferramenta muito útil quando se quer construir uma abstração funcional. Considere

```
def fixedPoint(f: Double => Double)(firstGuess: Double) = {
  	def iterate(guess: Double): Double = {
  		println("Guess: " + guess)
  		val next = f(guess)
  		if(isCloseEnough(guess, next)) next
  		else iterate(next)
  	}
  	iterate(firstGuess)
  }

def averageDamp(f: Double => Double)(x: Double) = (x + f(x))/2

def sqrt(x: Double) =
  	fixedPoint(averageDamp(y => x / y))(1)

sqrt(4)
fixedPoint(averageDamp(y => x / y))(1), tq x == 4
fixedPoint(averageDamp(y => 4 / y))(1)
fixedPoint((x + f(x)) / 2)(1), tq f(x) == y => 4 / y, x == y
fixedPoint((x + (x == y => 4 / y)) / 2)(1)

//A função f(x) passada para dentro estava definida em função de y, mas o que eu passar para dentro dela será interpretado, mesmo que seja f(x) == (y => ...)

g(x) = ((x + (x == y => 4 / y)) / 2)
g(1) == ((1 + (1 == y => 4 / y)) / 2) == ((1 + (1 == 1 => 4 / 1)) / 2) ==
((1 + (4 / 1)) / 2) == ((1 + 4) / 2) == (5 / 2) == 2.5
```

## Elementos da linguagem Scala
Podemos definir os elementos que constituem a liguagem Scala utilizando uma sintaxe livre de contexto, para isso usamos a **Extended Backus-Naur Form (EBNF)**, onde:
- **|** denora uma alternativa
- **[...]** denota uma opção (0 ou 1)
- **{...}** denota uma repetição (0 ou mais)

###Tipos
```
Type = SimpleType | FunctionType
FunctionType = SimpleType '=>' Type
			 | '(' [Types] ')' '=>' Type
SimpleType = Identifier
Types = Type { ',' Type}

Um tipo pode ser:
- Um tipo numérico (int, double, byte, char, long, float)
- Um tipo booleano com valores V e F
- Uma String
- Um tipo de função, como Int => Int, (Int, Int) => Int

```

###Expressões
```
Expr = InfixExpr | FunctionExpr
	 | if '(' Expr ')' Expr else Expr
InfixExpr = PrefixExpr | InfixExpr Operator InfixExpr
Operator = Identifier
PrefixExpr = [ '+' | '-' | '!' | '~' ] SimpleExpr
SimpleExpr = Identifier | Literal | SimpleExpr '.' Identifier | Block
FunctionExp = Bindings '=>' Expr
Bindings = Identifier [ ':' SimpleType ]
		 | '(' [Binding { ',' Binding }] ')'
Binding = Identifier [ ':' Type ]
Block = '{' {Def ';' } Expr }

Uma expressão pode ser
- Um identificador, como x ou isGoodEnough
- Um literal, como 0, 1.0, "abc"
- Uma aplicação de função, como sqrt(x)
- A aplicação de um operador, como -x, y + x
- Uma seleção, como math.abs
- Uma expressão condicional, como if (x < 0) -x else x
- Um bloco, como {val = math.abs(y); x * 2}
- Uma função anônima, como x => x + 1
```

###Definições
```
Def = FunDef | ValDef
FunDef = def Identifier {'(' [Parameters] ')'}
		 [ ':' Type ] '=' Expr
ValDef = val Identifier [ ':' Type ] '=' Expr
Parameter = Identifier ':' [ '=>' ] Type
Parameters = Parameters { ',' Parameters}

Uma definição pode ser
- Uma definição de função, como "def square(x: Int) = x * x"
- A definição de um valor, como "val y = square(x)"

Um parâmetro pode ser
- Um parâmetro CBV, como (x: Int)
- Um parâmetro CBN, como (y: => Double)
```
