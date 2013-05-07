(ns config.migrate-config)

; (defn- maybe-create-schema-table
;   "Creates the schema table if it doesn't already exist."
;   [args]
;   (exec-raw "CREATE TABLE IF NOT EXISTS schema_version (version INTEGER NOT NULL, created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now())"))

(defn current-db-version []
  ; (maybe-create-schema-table)
  ; (or (:version (first (select :schema_version (fields :version) (order :created_at :DESC) (limit 1)))) 0)
  1)

(defn update-db-version [version]
  ; (insert :schema_version (values {:version version}))
  1
  )

(defn migrate-config []
  {:directory "/src/clj/migrations/"
   :namespace-prefix "migrations"
   ; :init maybe-create-schema-table
   :current-version current-db-version
   :update-version update-db-version })