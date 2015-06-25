(defproject tic-tac-toe "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :profiles {:dev {:dependencies [[speclj "3.2.0"]]}
             :uberjar {:aot :all}}
  :plugins [[speclj "3.2.0"]]
  :main tic-tac-toe.core
  :aot [tic-tac-toe.core
        tic-tac-toe.ai-player
        tic-tac-toe.board
        tic-tac-toe.rules]
  :test-paths ["spec"])
