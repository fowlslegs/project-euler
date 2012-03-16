; 31875000
; 0.27user 0.02system 0:00.30elapsed 99%CPU (0avgtext+0avgdata 39216maxresident)k
; 0inputs+0outputs (0major+12089minor)pagefaults 0swaps

(require racket/generator)

(define g
  (generator
    ()
    (for* ((a (in-range 1 333))
           (b (in-range a (/ (- 1000 a) 2))))
          (let ((c (- 1000 a b)))
            (when (= (+ (* a a) (* b b))
                     (* c c))
              (yield (* a b c)))))))

(display (g))
(newline)