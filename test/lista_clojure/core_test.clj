(ns lista-clojure.core-test
  (:require [clojure.test :refer :all]
            [lista-clojure.core :refer :all]))

(deftest median
  (testing "Function media exists."
    (is (= (media 5) 5.0)))
  (testing "Accepts n arguments"
    (is (= (media 5) 5.0)))
    (is (= (media 5 4 ) 4.5))
    (is (= (media 5 4 3 2) 3.5))
  )


(deftest prod
  (testing "Function produto exists."
    (is (= (produto [5] [2]) 10)))
  (testing "Accepts n arguments"
    (is (= (produto [5] [1]) 5)))
    (is (= (produto [1 2] [4 5]) 14))
    (is (= (produto [1 2 3] [4 5 6]) 32)))

(deftest fibo
  (testing "Function get-fibo exists."
    (is (= (get-fibo 5) [5 3 1 1 0])))
  (testing "Testing different outcomes"
    (is (= (get-fibo 20) '(35 33 31 29 27 25 23 21 19 17 15 13 11 9 7 5 3 1 1 0))))
  (is (= (get-fibo 15) '(25 23 21 19 17 15 13 11 9 7 5 3 1 1 0)))
  (is (= (get-fibo 3) '(1 1 0))))

;; (deftest Fizz
;;   (testing "Function FizzBuzz exists."
;;     (is (= FizzBuzz ))))

(deftest rev
  (testing "Function de-reversa exists."
    (is (= (de-reversa "palavra") [nil \a \r \v \a \l \a \p])))
  (testing "Accepts vectors or strings"
  (is (= (de-reversa "pedra") [nil \a \r \d \e \p]))
  (is (= (de-reversa [1 2 3 4 5]) [nil 5 4 3 2 1]))))


(deftest tres
  (testing "Function tres-mais-freq exists."
    (is (= (tres-mais-freq ["palavra" "dois" "tres" "palavra" "dois" "palavra"]) '(["palavra" 3] ["dois" 2] ["tres" 1]))))
  (testing "Accepts numbers and strings"
    (is (= (tres-mais-freq ["palavra" "dois" "tres" "palavra" "dois" "palavra"]) '(["palavra" 3] ["dois" 2] ["tres" 1]))))
  (is (= (tres-mais-freq [1 2 3 4 4 3 5 2 4]) [[4 3] [2 2] [3 2]])))

(deftest size
  (testing "Function size-by exists."
    (is (= (size-by ["palavra" "dois" "tres" "aviao" "pato" "miscelaneo"]) '("miscelaneo" "palavra" "aviao"))))
  )

(deftest total-livros
  (testing "Function get-total exists."
    (is (= (get-total Livros) 144035))))

(deftest total-livros-sci-fi
  (testing "Function get-total-sci-fy exists."
    (is (= (get-total-sci-fy db) 53802))))

(deftest info-livros
  (testing "Function info exists."
    (is (= (info Livros) {:romance 32755, :sci-fi 49424, :horror 18623, :fantasy 43233}))))

(deftest eleven
  (testing "Function onze exists."
    (is (= (onze 5 6 10) [50 40 30 20 10]))))


(deftest gold
  (testing "Function goldbach exists."
    (is (= (goldbach 6) [5 1] )))
  (testing "Testing different outcomes"
    (is (= (goldbach 56) [53 3]))
    (is (= (goldbach 200) [199 1]))
    (is (= (goldbach 1000) [997 3]))
    )
  )
