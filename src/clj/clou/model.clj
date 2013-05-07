(ns clou.model
  (:refer-clojure :exclude [find])
  (:use [korma core db]
        [config.db]))

(defdb clou-db (mysql mysql-db))

(defentity file)

(defentity user
  (table :users)
  (has-many file))

(defn add-user
  [values]
  (when (contains? values :user)
    (let [user (:user values)]
      (-> values
        (dissoc :user)
        (assoc :users_id (:id user))))))

(defentity file
  (table :files)
  (prepare add-user)
  (belongs-to user))


(defprotocol Queryable
  "An sql query protocol for quick searching."
  (find-user  [arg])
  (find-users [arg])
  (find-file  [arg])
  (find-files [arg]))

(extend java.lang.Number
  Queryable
  {:find-user  (fn [id] (first (select user (where {:id id}) (with file))))
   :find-users (fn [id] (into [] (select user (where {:id id}) (with file))))
   :find-file  (fn [id] (first (select file (where {:id id}) (with user))))
   :find-files (fn [id] (select file (where {:id id}) (with user)))})

(extend java.lang.String
  Queryable
  {:find-user  (fn [email] (first (select user (where {:email email}) (with file))))
   :find-users (fn [email] (select user (where {:email email}) (with file)))
   :find-file  (fn [n] (first (select file (where {:name n}) (with user))))
   :find-files (fn [n] (select file (where {:name n}) (with user)))})

(extend clojure.lang.PersistentArrayMap
  Queryable
  {:find-user  (fn [m] (first (select user (where m) (with file))))
   :find-users (fn [m] (select user (where m) (with file)))
   :find-file  (fn [m] (first (select file (where m) (with user))))
   :find-files (fn [m] (select file (where m) (with user)))})

(defn create-user
  [data]
  (insert user (values data)))

(defn create-file
  [data]
  (insert file (values data)))


(comment
  (insert user
    (values
      {:email "jon@ghost.com"
       :password "pass"
       :password_confirmation "pass"}))

  (insert file
    (values
      {:name "Test File8"
       :user (first (select user))
       :body "hjbfwbf wlasargergrifb wliwbf lwhfb fwlhbw "})))