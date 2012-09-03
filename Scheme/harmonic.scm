;; by Sean McKenna
;; Harmonic series
;; Takes the input of n and an empty list
;; Sums the reciprocals to create harmonic series

(define (harmonic n l)
  (if (eq? n 0)
    (display (apply + l))
    (harmonic (- n 1) (cons (/ 1 n) l))
  )
)

;; Test functions
(harmonic 1 '())
(newline)
(harmonic 2 '())
(newline)
(harmonic 3 '())
(newline)
(harmonic 4 '())
(newline)
(harmonic 5 '())
(newline)
(harmonic 100 '())
(newline)
