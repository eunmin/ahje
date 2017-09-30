(defproject ahje "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.openkoreantext/open-korean-text "2.1.0"]]
  :main ^:skip-aot ahje.core
  :uberjar-name "ahje.jar"
  :profiles {:uberjar {:aot :all}})
