(ns clou.crossover.views.util.bootstrap)

(defn element [k args & opts]
	[k (into {} opts) args])

(defn row [args & opts]
	(element :div.row args (into {} opts)))