; 162
; 4.23user 0.33system 0:03.13elapsed 145%CPU (0avgtext+0avgdata 0maxresident)k
; 0inputs+8outputs (0major+20637minor)pagefaults 0swaps

(require '[clojure.contrib.str-utils2 :as su])

(declare charmap triangle-word-scores)

(defn triangle [n]
  (quot (* n (inc n)) 2))

(defn triangle? [n]
  (contains? triangle-word-scores n))

(defn word-score [word]
  (reduce + (map #(get charmap %) word)))

(def words
  (map #(su/replace % #"\"" "")
       (su/split (slurp "../data/42.txt") #",")))

(def longest-word
  (apply max (map count words)))

(def charmap
  (zipmap
    (map char (range (int \A) (inc (int \Z))))
    (range 1 (inc 26))))

(def triangles
  (map triangle (iterate inc 1)))

(def triangle-word-scores
  (set (take-while #(<= % (* longest-word 26)) triangles)))

(println
  (count
    (filter triangle?
      (map word-score words))))
