(ns migrations.20130328161532-create-users)
  (:use [config.db])
  (:require [clojure.java.jdbc :as sql]))

(defn up
  "Migrates the database up to version 20130328161532."
  []
  (sql/with-connection mysql-db
    (sql/create-table :users
      [:id                    :integer  "PRIMARY KEY" "AUTO_INCREMENT"]
      [:email                 (varchar 255)]
      [:password              (varchar 255)]
      [:password_confirmation (varchar 255)]
      [:created_at            :timestamp "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]
      [:updated_at            :timestamp]))
  (println "clou.migrations.20130328161532-create-users up..."))

(defn down
  "Migrates the database down from version 20130328161532."
  []
  (sql/drop-table :users)
  (println "clou.migrations.20130328161532-create-users down..."))