## Introdução a Programação Funcional com Scala - Week 2

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

//O nome desse processo é Currying

```
