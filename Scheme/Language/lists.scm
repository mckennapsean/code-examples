;; Copyright 2013 Sean McKenna
;; 
;;    Licensed under the Apache License, Version 2.0 (the "License");
;;    you may not use this file except in compliance with the License.
;;    You may obtain a copy of the License at
;; 
;;        http://www.apache.org/licenses/LICENSE-2.0
;; 
;;    Unless required by applicable law or agreed to in writing, software
;;    distributed under the License is distributed on an "AS IS" BASIS,
;;    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
;;    See the License for the specific language governing permissions and
;;    limitations under the License.
;;

;; creating a variety of list functions

;; default lists; I would suggest A for most testing
(define(A) '(10 30 -20 -100 60 80 110 -34 -40 86))
(define(B) '(0 1 2 3 4 5 6 7 8 9))

;; returns the length of a list
(define(len l)
  (if(null? l)
     0
     (+ (len (cdr l)) 1)))

;; returns the sum of integers in a list
(define(sum l)
  (if(null? l)
     0
     (+ (sum (cdr l)) (car l))))

;; returns the minimum integer in a list
(define(mini l)
  (if(null? (cdr l))
     (car l)
     (if(< (car l) (car (cdr l)))
        (mini (cons (car l) (cdr (cdr l))))
        (mini (cdr l)))))

;; returns the maximum integer in a list
(define(maxi l)
  (if(null? (cdr l))
     (car l)
     (if(> (car l) (car (cdr l)))
        (maxi (cons (car l) (cdr (cdr l))))
        (maxi (cdr l)))))

;; returns a min (<) or max (>) of a list
(define(minmax f l)
  (if(eq? f <)
     (mini l)
     (maxi l)))

;; returns a list that has been reversed
(define(rev l)
  (if(null? l)
     '()
     (append (rev (cdr l)) (list (car l)))))

;; combines list 1 and list 2
(define(comb l l2)
  (append l l2))
