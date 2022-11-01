(ns lista-clojure.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

;; ----------------------------------------------------

;; 1
(defn media [& args]
  (float (/ (apply + args) (count args))))

;; ----------------------------------------------------

;; 2

(defn produto [a b]
  (loop [indice 0 saida 0]
    (if (= indice (count (map * a b)))
      saida
      (recur (inc indice) (+ saida (nth (map * a b) indice))))))

;; ----------------------------------------------------

;; 3

(defn fibonacci [n]
  (if (= n 1) 1
      (if (= n 0) 0
          (+
           (- n 1)
           (- n 2)))))

(fibonacci 3)

(defn get-fibo [n]
  (loop [indice 0 saida '()]
    (if (>= indice n)
      saida
      (recur (inc indice) (conj saida (fibonacci indice))))))


;; ----------------------------------------------------

;; 4

(defn FizzBuzz []
  (for [n (range 1 100)]
    (if (and (= 0 (mod n 3)) (= 0 (mod n 5)))
      "FizzBuzz"
      (if (= 0 (mod n 3))
        "Fizz"
        (if (= 0 (mod n 5))
          "Buzz"
          n)))))

(FizzBuzz)

;; ----------------------------------------------------

;; 5

(defn de-reversa [sequen]
  (loop [indice (count sequen) saida '[]]
    (if (< indice 0)
      saida
      (recur (dec indice) (conj saida (get sequen indice))))))

;; ----------------------------------------------------

;; 6

(defn tres-mais-freq [col]
  (take 3
        (sort-by val >
                 (frequencies col))))

;; ----------------------------------------------------

;; 7

(defn size-by [col]
  (take 3
        (sort-by count > col)))

(size-by ["palavra" "dois" "tres" "aviao" "pato" "miscelaneo"])

;; ----------------------------------------------------

;; 8

(def Livros
  [{:title "Pride and Prejudice" :author "Austen" :genre :romance :copies-sold 15400}
   {:title "Fundation" :author "Isaac Asimov" :genre :sci-fi :copies-sold 28769}
   {:title "World War Z" :author "Brooks" :genre :horror :copies-sold 7250}
   {:title "Jurassic Park" :author "Crichton" :genre :sci-fi :copies-sold 13677}
   {:title "The Lord of Rings" :author "Tokien" :genre :fantasy :copies-sold 23450}
   {:title "Game of Thrones" :author "Martin" :genre :fantasy :copies-sold 19783}
   {:title "1984" :author "Orwell" :genre :sci-fi :copies-sold 2600}
   {:title "Dom Quixote" :author "Miguel Cervantes" :genre :romance :copies-sold 17355}
   {:title "Frankenstein" :author "Mary Shelley" :genre :horror :copies-sold 11373}
   {:title "Let Petit Prince" :author "Antoine de Saint-Exupéry" :genre :sci-fi :copies-sold
    4378}])

(map :genre Livros)

(defn get-total [col]
  (loop [index 0 total 0]
    (if (= index (count col))
      total
      (recur (inc index) (+ total (:copies-sold (get col index) ))))))

(get-total Livros)

;; ----------------------------------------------------

;; 9

(def db {:db
         {:nome "Biblioteca"
          :dados Livros
          :temp {:title "Dune" :author "Frank Herbert" :genre :sci-fi :copies-sold 4378}}})

(defn get-total-sci-fy [db]
  (loop [index 0 total (get (:temp (:db db)) :copies-sold)]
    (if (= index (count (:dados (:db db))))
      total
      (if (not (= (get (get (:dados (:db db)) index) :genre) :sci-fi))
        (recur (inc index) total)
        (recur (inc index) (+ total (:copies-sold (get (:dados (:db db)) index))))))))

(get-total-sci-fy db)

;; ----------------------------------------------------

;; 10

(defn info [col]
  (conj {:romance (apply + (map :copies-sold (filter #(= (:genre %) :romance) col)))
   :sci-fi (apply + (map :copies-sold (filter #(= (:genre %) :sci-fi) col)))
   :horror (apply + (map :copies-sold (filter #(= (:genre %) :horror) col)))
   :fantasy (apply + (map :copies-sold (filter #(= (:genre %) :fantasy) col)))})
  )

(info Livros)

;; Possibilidade de uma condicional para cada genero com um loop para gerar o map

(defn info2 [col]
  (loop [indice 0 saida '{:romance :copies-sold :sci-fi :copies-sold :horror :copies-sold :fantasy :copies-sold}]
    (if (= indice (count col))
      saida
      (cond
           (= (:genre (get col indice)) :romance) (recur (inc indice) (+ (:romance saida) (:copies-sold (get (:dados (:db db)) indice))))
           (= (:genre (get col indice)) :sci-fi) (recur (inc indice) (+ (:sci-fi saida) (:copies-sold (get (:dados (:db db)) indice))))
           (= (:genre (get col indice)) :horror) (recur (inc indice) (+ (:horror saida) (:copies-sold (get (:dados (:db db)) indice))))
           (= (:genre (get col indice)) :fantasy) (recur (inc indice) (+ (:fantasy saida) (:copies-sold (get (:dados (:db db)) indice))))
           :else "F")) 
      )
   )
 
(info2 Livros)

;; ----------------------------------------------------

;; 11

(defn onze [n x p]
  (loop [indice (+ 1 x) saida '()]
    (if (= (count saida) n)
      saida
      (if (= (mod indice p) 0)
        (recur (inc indice)(conj saida indice))
        (recur (inc indice) saida)
        )
      ))
  )

(onze 5 6 3)

;; ----------------------------------------------------

;; 12

;; 12) Conjectura de Goldbach: A conjectura de Goldbach diz que todo número par positivo
;; maior que 2 é a soma de dois números primos. Exemplo: 28 = 5 + 23. Foi confirmado
;; numericamente até números muito grandes. Escreva uma função para encontrar os
;; dois números primos que somam um dado inteiro par menos que 1.000.

(defn is-prime? [num]
  (empty?
   (filter
    #(= % 0)
    (map #(mod num %) (range 2 num)))))

(defn goldbach [n]
  (if (or (neg? n)(odd? n))
    "Numero impar ou negativo"
    (conj '[] (first (reverse (filter is-prime? (range 1 n)))) (- n (first (reverse (filter is-prime? (range 1 n))))))
    )
  )
  
(goldbach 1000)