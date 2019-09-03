;
; Copyright (c) 2019 the original author or authors.
; Licensed under the Eclipse Public License 2.0
; which is available at http://www.eclipse.org/legal/epl-2.0
;

(ns clj-ip2region.ip2region
  (:require [clojure.java.io :as io]
            [clojure.string :as str])
  (:import (com.jiliguala.ip2region IpSearcher)
           (java.io ByteArrayOutputStream)
           (java.util Objects)
           (org.lionsoul.ip2region DataBlock)))

(defn- file->bytes [file]
  (with-open [in (io/input-stream file)
              out (ByteArrayOutputStream.)]
    (io/copy in out)
    (.toByteArray out)))

(defonce ^:private ^IpSearcher ip-searcher
  (IpSearcher. (file->bytes (io/resource "clj-ip2region/ip2region.db"))))

(defn ip->region
  [^String ip]
  (Objects/requireNonNull ip "ip->region only accept non nil value")
  (let [region-str (.getRegion ^DataBlock (.memorySearch ip-searcher ip))
        [country _ province city isp] (str/split region-str #"\|")]
    {:country country
     :province province
     :city city
     :isp isp}))
