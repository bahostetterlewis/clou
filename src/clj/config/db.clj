(ns config.db
  (:refer-clojure :exlude [char]))

(def mysql-db {:subname "//127.0.0.1:3306/clou"
               :classname "com.mysql.jdbc.Driver"
               :subprotocol "mysql"
               :user "clou_db"
               :password "clou_db"})