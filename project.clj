(defproject clj-ip2region "1.0.0-SNAPSHOT"
  :description "ip2region clojure wrapper"
  :url "https://github.com/qzteam/clj-ip2region"
  :license {:name "EPL-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"
            :distribution :repo}
  :scm {:name "git" :url "https://github.com/qzteam/clj-ip2region"}
  :global-vars {*warn-on-reflection* true}
  :javac-options ["-source" "8" "-target" "8" "-g"]
  :java-source-paths ["src/java"]
  :source-paths ["src/clj"]
  :test-paths ["test/clj"]
  :resource {:resource-paths ["ext/ip2region/data"]
             :target-path "target/resources/clj-ip2region"
             :skip-stencil [ #".*" ]
             :includes [#".*/ip2region\.db"]}
  :resource-paths ["target/resources"]
  :hooks [leiningen.resource]
  :dependencies [[org.lionsoul/ip2region "1.7.2"]]
  :exclusions [org.clojure/clojure]
  :plugins [[lein-kibit "0.1.6"]
            [lein-cloverage "1.1.1"]
            [lein-pprint "1.2.0"]
            [lein-resource "17.06.1"]]
  :profiles {:provided {:dependencies [[org.clojure/clojure "1.10.1"]]}}
  :repl-options {:init-ns clj-ip2region.ip2region}
  :deploy-repositories [["clojars" {:url "https://clojars.org/repo/"
                                    :username :env
                                    :password :env}]]
  :release-tasks [["vcs" "assert-committed"]
                  ["change" "version" "leiningen.release/bump-version" "release"]
                  ["vcs" "commit" "[lein release] prepare release %s"]
                  ["vcs" "tag" "v"]
                  ["deploy" "clojars"]
                  ["change" "version" "leiningen.release/bump-version"]
                  ["vcs" "commit" "[lein release] prepare for next development iteration"]
                  ["vcs" "push"]])
