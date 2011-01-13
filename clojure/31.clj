; 73682
; 2.68user 0.18system 0:02.28elapsed 125%CPU (0avgtext+0avgdata 0maxresident)k
; 0inputs+8outputs (0major+14656minor)pagefaults 0swaps

(defmacro defp [x y]
  (let [px (symbol (str "p" x))
        py (symbol (str "p" y))]
    `(defn ~px [n#]
       (if (>= n# 0)
         (+ (~px (- n# ~x)) (~py n#))
         0))))

(defn p1 [n] 1)

(defp 2 1)

(defp 5 2)

(defp 10 5)

(defp 20 10)

(defp 50 20)

(defp 100 50)

(defp 200 100)

(println (p200 200))