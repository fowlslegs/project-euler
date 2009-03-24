/*
Each new term in the Fibonacci sequence is generated by adding the previous two terms. By starting with 1 and 2, the first 10 terms will be:

1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...

Find the sum of all the even-valued terms in the sequence which do not exceed four million.
*/
import scala.Math._

val phi = (1.0 + sqrt(5.0)) / 2.0
def fib(n: Int) = floor(pow(phi, n) / pow(5.0, .5) + .5)

var i = 0
var f = 0D
var x = 0D
while (f <= 4E6) {
  f = fib(i)
  i += 1
  if (f % 2 == 0.0)
    x += f
}
println(x)
