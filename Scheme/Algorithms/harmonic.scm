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

;; implementation of generating a harmonic series

;; definition of harmonic series (recursive)
(define (harmonic n l)
  (if (eq? n 0)
    (display (apply + l))
    (harmonic (- n 1) (cons (/ 1 n) l))
  )
)

;; test functions
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
