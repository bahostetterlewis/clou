(ns clou.crossover.views.helpers)

(defn input [& opts]
	[:div {:class "row-fluid"}
		[:div {:class "navbar-form pull-left"}
			[:button {:type "submit" :class "btn btn-mini"} "update"]
			[:input (merge opts {:type "text" :class "span8"})]]])

(defn child-input-html [n]
	[:div
		[:div {:class "row-fluid"}
			[:h4 n]]
		(input)])

(defn row [body & opts]
	[:div (merge {:class "row"} opts) body])

(defn row-fluid [body & opts]
	[:div (merge {:class "row-fluid"} opts) body])

