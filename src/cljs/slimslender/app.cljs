(ns slimslender.app)

(def value-a 1)

(defonce value-b 2)

(enable-console-print!)

(defn reload! []
  (println "Code updated.")
  (println "Trying values:" value-a value-b))

(defn main! []
  (println "App loaded!"))
