; JAVA_OPTS="-Xmx1024M" time clojure 3.clj
; 6857
; 9.46user 0.34system 0:07.39elapsed 132%CPU (0avgtext+0avgdata 0maxresident)k
; 0inputs+64outputs (1major+139470minor)pagefaults 0swaps

(use '[clojure.set :only (union)])
(use '[clojure.contrib.math :only (ceil sqrt round)])

(defn fermat-factors [n]
  (if (= 0 (rem n 2))
    (sorted-set n (/ n 2))
    (loop [a (ceil (sqrt n))]
      (let [b (- (* a a) n)
            s (sqrt b)]
        (if (= s (round s))
          (sorted-set (- a s) (+ a s))
          (recur (inc a)))))))

(defn prime-factors [n]
  (let [factors (fermat-factors n)]
    (if (contains? factors 1)
      (disj factors 1)
      (reduce union (map prime-factors factors)))))

(println (reduce max (prime-factors 600851475143)))