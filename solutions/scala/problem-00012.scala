import java.lang.Runtime
import scala.collection.mutable.HashSet
import scala.Math._

def exec(cmd: String) = {
  val process = Runtime.getRuntime.exec(cmd)
  val buffer = new StringBuffer(1024)
  var c = 0
  val in = process.getInputStream
  process.waitFor
  while ({ c = in.read; c != -1}) {
    buffer.append(c.toChar)
  }
  buffer.toString
}

def factor(n: BigInt): List[BigInt] = {
  val result = exec("factor " + n.toString)
  result.split(":")(1).trim.split(" ").toList.map(BigInt(_))
}

def divisors(n: BigInt): Int = {
  if (n == 1) return 1
  var d: Int = 1
  val f = factor(n)
  val s = new HashSet[BigInt]()
  f.foreach(s += _)
  s.foreach(p => {
    d *= (f.filter(_ == p).length + 1)
  })
  d
}

/*
If run with the println statement uncommented, gives the following result:
1:   Tn=1 best=1
2:   Tn=3 best=2
3:   Tn=6 best=4
7:   Tn=28 best=6
8:   Tn=36 best=9
15:   Tn=120 best=16
24:   Tn=300 best=18
32:   Tn=528 best=20
35:   Tn=630 best=24
63:   Tn=2016 best=36
80:   Tn=3240 best=40
104:   Tn=5460 best=48
224:   Tn=25200 best=90
384:   Tn=73920 best=112
560:   Tn=157080 best=128
935:   Tn=437580 best=144
1224:   Tn=749700 best=162
1664:   Tn=1385280 best=168
1728:   Tn=1493856 best=192
2015:   Tn=2031120 best=240
2079:   Tn=2162160 best=320
5984:   Tn=17907120 best=480
12375:   Tn=76576500 best=576
*/
var i: BigInt = 1
var n: BigInt = 1
var d: Int = 0
var best: Int = 0
while (d <= 500) {
  d = divisors(n)
  if (d > best) {
    best = d
    //println(i + ":   Tn=" + n + " best=" + d)
  }
  i += 1
  n += i
}
println(n)

