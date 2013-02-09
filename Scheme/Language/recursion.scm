;; by Sean McKenna
;; recursively searching through a list

(define numbers '(1 2 3 4 5 6))

(define letters '(a b c d e f))

(define (my-length some-list)
  (if (null? some-list)
      0
      (+ 1 (my-length (cdr some-list)))))

(define (my-append a-list b-list)
  (if (null? a-list) 
      b-list
      (cons (car a-list) (my-append (cdr a-list) b-list))))

(define (my-list a . b)
  (if (null? a)
      '()
      (cons a b)))

(define (my-reverse some-list)
  (if (null? some-list)
      '()
      (my-append (my-reverse (cdr some-list)) (my-list (car some-list)))))

(define (smallest integer-list)
  (if (null? integer-list)
      0
      (smallest-helper (car integer-list) (cdr integer-list))))

(define (smallest-helper guess remaining-integers)
  (cond
    ((null? remaining-integers) guess)
    ((< guess (car remaining-integers)) (smallest-helper guess (cdr remaining-integers)))
    (else (smallest-helper (car remaining-integers) (cdr remaining-integers)))))
