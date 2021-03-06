(require '[clojure.string :as su])
(require '[clojure.set :as s])

(def inf Double/POSITIVE_INFINITY)

(def graph
  (let [unsplit-lines (su/split-lines (slurp "../data/107.txt"))
        split-lines (map #(su/split % #",") unsplit-lines)
        parse-element #(if (= "-" %) inf (Double/parseDouble %))
        rows (map #(map parse-element %) split-lines)]
    (vec (map vec rows))))

(def vertex-set
  ((comp set range count) graph))

; The cost must be divided by two because blindly summing the matrix
; representation will count each edge twice.
(def total-cost
  (let [sum-row (fn [row] (reduce + (filter #(not (= inf %)) row)))
        sum-graph (reduce + (map sum-row graph))]
    (/ sum-graph 2)))

(defn cost
  "Returns the cost of connecting node u to v"
  [edge]
  (let [u (first edge)
        v (last edge)]
    (nth (nth graph u) v)))

(defn neighbors
  "Returns the nodes connected to u by an edge"
  [u]
  (set
    (filter #(not (= inf (cost [u %])))
            (range (count graph)))))

(defn frontier-edges
  "Returns the frontier edges"
  [vertices]
  (set
    (for [u vertices
          v (s/difference (neighbors u) vertices)]
      #{u v})))

(defn cheapest-edge
  "Returns the cost of the cheapest frontier edge"
  [vertices edges]
  (let [frontier (vec (s/difference (frontier-edges vertices) edges))
        costs (map cost frontier)
        costs-map (zipmap costs frontier)
        min-cost (apply min (keys costs-map))]
    (get costs-map min-cost)))

(defn minimum-spanning-tree
  "Returns an MST calculated by Prim's algorithm"
  [graph]
  (loop [vertices #{0}
         edges #{}]
    (if (= vertices vertex-set)
      edges
      (let [edge (cheapest-edge vertices edges)]
        (recur (s/union vertices edge)
               (conj edges edge))))))

(let [mst (minimum-spanning-tree graph)
      mst-cost (reduce + (map cost mst))]
  (println (- total-cost mst-cost)))
