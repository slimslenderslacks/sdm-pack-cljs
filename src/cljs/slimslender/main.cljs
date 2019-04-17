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
            [atomist.json :as json]))

(defn ^:export addPlugin
  ""
  [p]
  (log/info "addPlugin to " p)
  (promise/chan->promise
   (go
     true)))