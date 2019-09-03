;
; Copyright (c) 2019 the original author or authors.
; Licensed under the Eclipse Public License 2.0
; which is available at http://www.eclipse.org/legal/epl-2.0
;

(ns clj-ip2region.ip2region-test
  (:require [clj-ip2region.ip2region :as ip2region]
            [clojure.test :refer :all]))

(declare thrown?)

(deftest test-ip2region
  (testing "test nil input"
    (is (thrown? NullPointerException (ip2region/ip->region nil))))

  (testing "test invalid ip (always return 0"
    (is (= {:country "0", :province "0", :city "内网IP", :isp "内网IP"} (ip2region/ip->region ""))))

  (testing "test normal ip"
    (is (= "美国" (:country (ip2region/ip->region "8.8.8.8"))))))
