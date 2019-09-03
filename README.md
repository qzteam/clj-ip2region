# clj-ip2region

[![Build Status](https://travis-ci.org/qzteam/clj-ip2region.svg?branch=master)](https://travis-ci.org/qzteam/clj-ip2region)
[![Coverage Status](https://coveralls.io/repos/github/qzteam/clj-ip2region/badge.svg?branch=master)](https://coveralls.io/github/qzteam/clj-ip2region?branch=master)
[![License](https://img.shields.io/github/license/qzteam/clj-ip2region.svg?maxAge=86400)](./LICENSE)
[![Dependencies Status](https://versions.deps.co/qzteam/clj-ip2region/status.png)](https://versions.deps.co/qzteam/clj-ip2region)
[![Clojars Project](https://img.shields.io/clojars/v/clj-ip2region.svg)](https://clojars.org/clj-ip2region)

[ip2region](https://github.com/lionsoul2014/ip2region) 的 Clojure 封装。

## Usage

```clojure
(require '[clj-ip2region.ip2region :as ip2region])
; => nil
(ip2region/ip->region "119.29.29.29")
; => {:country "中国", :province "北京", :city "北京市", :isp "腾讯"}
(ip2region/ip->region "8.8.8.8")
; => {:country "美国", :province "0", :city "0", :isp "Level3"}
```

## License

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
