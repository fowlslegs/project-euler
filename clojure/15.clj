; JAVA_OPTS="-Xmx1024M" time clojure 15.clj
; 137846528820
; 0.90user 0.02system 0:00.65elapsed 141%CPU (0avgtext+0avgdata 0maxresident)k
; 0inputs+64outputs (1major+13952minor)pagefaults 0swaps

(defn fact [n]
    (cond (= n 0) 0
          (= n 1) 1
          true    (* n (fact (- n 1)))))

(let [n (fact (+ 20 20))
      d (* (fact 20) (fact 20))
      r (/ n d)]
  (println r))
