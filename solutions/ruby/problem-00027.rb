#!/usr/bin/env ruby
# [minhuang@mocha:ruby]$ time ./problem-00027.rb 
# -59231
#
# real    0m3.473s
# user    0m3.436s
# sys     0m0.028s
require 'set'

# A simple quadratic
def f(n, a, b)
  n*n + a*n + b
end

# For how many values is a quadratic prime?
def count(ps, a, b)
  c = 0
  (0..b).each do |n|
    v = f(n, a, b)
    if ps.include? v
      c += 1
    else
      return c
    end
  end
  c
end

# A list of the primes under 2 million will be fine
def primes
  file = File.new("../../data/primes.txt")
  set = Set.new
  while (p = file.gets)
    set.add(p.to_i)
  end
  file.close
  set
end


b_max   = 1000
ps      = primes
bs      = ps.take_while { |b| b <= b_max }
bs      = bs + bs.map { |b| -b }
a, b, c = 0, 0, 0

bs.each do |b_i|
  (-b_max..b_max).each do |a_i|
    c_i = count(ps, a_i, b_i)
    if c_i > c
      a, b, c = a_i, b_i, c_i
    end
  end
end

puts a*b
