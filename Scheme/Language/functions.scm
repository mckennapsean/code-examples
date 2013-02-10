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

;; generating custom functions

(define A '(0 1 2 3))
(define B '(4 5 6 7))

(define (f-0 f a b)
  (if (or (null? a) (null? b))
    '()
    (cons
      (f (car a) (car b))
      (f-0 f (cdr a) (cdr b)))))

(define (f-1 f a)
  (if (null? a)
    (f)
    (f (car a) (f-1 f (cdr a)))))

(define (f-2 u v)
  (f-1 + (f-0 * u v)))

(define (f-3 v)
  (sqrt (f-2 v v)))

(define (f-4 a b)
  (apply + (map * a b)))
