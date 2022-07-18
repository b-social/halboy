(defproject halboy "6.0.0"
  :description "a hypermedia parser and navigator"
  :license {:name "MIT License"
            :url  "https://opensource.org/licenses/MIT"}
  :url "https://github.com/jimmythompson/halboy"
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [http-kit "2.5.3"]
                 [cheshire "5.10.2"]
                 [medley "1.3.0"]
                 [uritemplate-clj "1.3.1"]
                 [org.clojure/core.cache "1.0.225"]
                 [org.bovinegenius/exploding-fish "0.3.6"]]
  :plugins [[lein-eftest "0.5.9"]]
  :profiles {:shared {:dependencies [[nrepl "0.9.0"]]}
             :test   [:shared {:dependencies [[http-kit.fake "0.2.2"]
                                              [eftest "0.5.9"]]}]
             :repl   [:shared {:dependencies [[http-kit.fake "0.2.2"]
                                              [eftest "0.5.9"]
                                              [b-social/wiremock-wrapper "0.1.0"
                                               ;:exclusions [org.eclipse.jetty/jetty-servlet
                                               ;             org.eclipse.jetty/jetty-webapp
                                               ;             org.eclipse.jetty/jetty-xml
                                               ;             org.eclipse.jetty/jetty-servlets]
                                               ]
                                              [org.slf4j/jcl-over-slf4j "1.7.25"]
                                              [org.slf4j/jul-to-slf4j "1.7.25"]
                                              [org.slf4j/log4j-over-slf4j "1.7.25"]
                                              [ch.qos.logback/logback-classic "1.2.3"
                                               :exclusions [org.slf4j/slf4j-api org.slf4j/slf4j-log4j12]]]}]

             }
  :eftest {:multithread? false})
