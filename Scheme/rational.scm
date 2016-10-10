;; implementation of a rational number data structure
;; includes some functions for applying this to resistors

;; variables to test with functions (intervals too)
(define A (cons 2 8))
(define B (cons 3 9))
(define C (cons 4 -5))
(define D (cons 5 -6))

;; construct a simplified rational number
(define (make-rat n d)
  (let ((g (gcd n d)))
  (if(= d 0)
     (display "Undefined - division by zero.")
  (if(neg? n d)
    (cons (/ (* n -1) g) (/ (* d -1) g))
    (cons (/ n g) (/ d g))))))

;; grab simplified pieces of a rational number
(define (numer x)
  (let ((g (gcd (car x) (cdr x))))
    (/ (car x) g)))
(define (denom x)
  (let ((g (gcd (car x) (cdr x))))
    (/ (cdr x) g)))

;; print out a rational number
(define (print-rat x)
  (display (numer x))
  (display "/")
  (display (denom x)))

;; functions to add, subtract, multiply, and divide
(define (add-rat x y)
  (make-rat (+ (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))
(define (sub-rat x y)
  (make-rat (- (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))
(define (mul-rat x y)
  (make-rat (* (numer x) (numer y))
            (* (denom x) (denom y))))
(define (div-rat x y)
  (make-rat (* (numer x) (denom y))
            (* (denom x) (numer y))))

;; test if all negative inputs or neg. in denom.
(define (neg? n d)
  (if(< n 0)
     (if(< d 0)
        #t
        #f)
     (if(< d 0)
       #t
       #f)))

;; function to test if equal rational numbers
(define (equal-rat? x y)
  (= (* (numer x) (denom y))
     (* (numer y) (denom x))))



;;; the following is code for intervals/resistors
;;; builds off the rational number implementation

;; construct an interval (min-max)
(define (make-interval a b) (cons a b))

;; get the upper and lower bounds
(define (lower-bound x)
  (car x))
(define (upper-bound x)
  (cdr x))

;; get the mid-point sub-interval
(define (sub-interval x)
  (/ (+ (upper-bound x) (lower-bound x)) 2))

;; add, multiply, and divide intervals
(define (add-interval x y)
  (make-interval (+ (lower-bound x) (lower-bound y))
                 (+ (upper-bound x) (upper-bound y))))
(define (mul-interval x y)
  (let ((p1 (* (lower-bound x) (lower-bound y)))
        (p2 (* (lower-bound x) (upper-bound y)))
        (p3 (* (upper-bound x) (lower-bound y)))
        (p4 (* (upper-bound x) (upper-bound y))))
    (make-interval (min p1 p2 p3 p4)
                   (max p1 p2 p3 p4))))
(define (div-interval x y)
  (if(span? y)
     (print "Undefined - divide by interval spanning zero.")
     (mul-interval x 
                (make-interval (/ 1.0 (upper-bound y))
                               (/ 1.0 (lower-bound y))))))

;; test if an interval spans zero
(define (span? y)
  (if(< (lower-bound y) 0)
     (if(> (upper-bound y) 0)
        #t
        #f)
     (if(< (upper-bound y) 0)
        #t
        #f)))

;; construct an interval (center-width)
(define (make-center-width c w)
  (make-interval (- c w) (+ c w)))

;; get the center and width of an interval
(define (center i)
  (/ (+ (lower-bound i) (upper-bound i)) 2))
(define (width i)
  (/ (- (upper-bound i) (lower-bound i)) 2))

;; construct an interval (center-percent)
(define (make-center-percent c p)
  (make-center-width c (* (/ p 100) c)))

;; get the percent width of interval (center is same)
(define (percent i)
  (* (/ (width i) (center i)) 100))

;; evaluate for parallel resistors (two methods)
(define (par1 r1 r2)
  (div-interval (mul-interval r1 r2)
                (add-interval r1 r2)))
(define (par2 r1 r2)
  (let ((one (make-interval 1 1))) 
    (div-interval one
                  (add-interval (div-interval one r1)
                                (div-interval one r2)))))

;; sample resistors for testing different methods
(define res1 (make-center-width 16000 800))
(define res2 (make-center-width 32000 4800))

;; returns output of the two resistor calculation methods
(define (print-par r1 r2)
  (let ((r3par1 (par1 r1 r2)))
  (let ((r3par2 (par2 r1 r2)))
  (display "par1: ")
  (print (center r3par1))
  (display " +/- ")
  (print (width r3par1))
  (newline)
  (display "par2: ")
  (print (center r3par2))
  (display " +/- ")
  (print (width r3par2)))))

;;; with r1 = 16000 +/- 800 AND r2 = 32000 +/- 4800
;;; par1: r3 = 11150 +/- 3430 AND par2: r3 = 10640 +/- 890
;;; conclude that par2 provides better bounds/more certainty
