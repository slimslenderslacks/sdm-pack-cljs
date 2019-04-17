(defproject slimslender/cljs-editors "0.5.7"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.238"]
                 [rewrite-cljs "0.4.4"]
                 [cljs-node-io "0.5.0"]
                 [noencore "0.1.16"]
                 [metosin/spec-tools "0.6.1"]
                 [org.clojure/test.check "0.10.0-alpha2"]
                 [cljfmt "0.5.7"]
                 [com.atomist/cljs-http "0.0.1"]
                 [com.rpl/specter "1.1.1"]
                 [io.replikativ/hasch "0.3.4"]
                 [binaryage/oops "0.6.3"]]

  :plugins [[lein-cljsbuild "1.1.5"]
            [lein-doo "0.1.10"]
            [lein-set-version "0.4.1"]]

  :profiles {:dev {:dependencies []
                   :source-paths ["dev"]
                   :repl-options {:init-ns user}}}
  :clean-targets
  [[:cljsbuild :builds 0 :compiler :output-to]
   [:cljsbuild :builds 0 :compiler :output-dir]
   :target-path
   :compile-path]
  :cljsbuild {:builds [{:id "prod"
                        :source-paths ["src/cljs"]
                        :compiler {:main slimslender.main
                                   :target :nodejs
                                   :output-to "output/main.js"
                                   :output-dir "out"
                                   :npm-deps {:xml-js "1.6.7"
                                              :semver "5.5.0"}
                                   :install-deps true
                                   :optimizations :simple
                                   :pretty-print true
                                   :parallel-build true}}
                       {:id "unit-tests"
                        :source-paths ["src/cljs" "test/cljs"]
                        :compiler {:output-to "unit-tests.js"
                                   :main slimslender.tests
                                   :target :nodejs
                                   :optimizations :none}}]})
