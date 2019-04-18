(ns slimslender.main
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-node-io.core :as io :refer [slurp spit]]
            [cljs.core.async :refer [chan <! >!]]
            [cljs.analyzer :as cljs]
            [cljs.spec.alpha :as s]
            [clojure.pprint :refer [pprint]]
            [http.util :as util]
            [goog.string :as gstring]
            [goog.string.format]
            [atomist.promise :as promise]
            [hasch.core :as hasch]
            [atomist.cljs-log :as log]
            [atomist.json :as json]
            [cljs.reader :refer [read-string]]
            [rewrite-clj.parser :as p]
            [rewrite-clj.node :as n]
            [rewrite-clj.zip :as z]
            [cljs.nodejs :as nodejs]))

(defn edit-file [f editor & args]
  (try
    (spit f (apply editor (slurp f) args))
    true
    (catch :default ex
      (log/error "failed to apply editor " ex)
      false)))

(defn edit-library [s library-name library-version]
  (-> s
      (z/of-string)
      z/down
      (z/find-next-value :dependencies)
      (z/find z/next #(if-let [s (z/sexpr %)]
                        (and (symbol? s)
                             (= library-name (str s)))))
      (z/right)
      (z/edit (constantly library-version))
      (z/root-string)))

(defn ^:export editLibraryVersion
  ""
  [project libname version]
  ;; project is a js/Object!
  (log/info (gstring/format "Update library version %s/%s in %s" libname version (.-baseDir project)))
  (promise/chan->promise
   (go
     (if (<! (promise/from-promise (.hasFile project "project.clj")))
       (edit-file (str (.-baseDir project) "/project.clj") edit-library libname version)
       false))))