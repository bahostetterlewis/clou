(ns clou.crossover.views.layout.application
	(:use [hiccup.element :only [javascript-tag]])
	(:require [clou.crossover.views.util.bootstrap :as b]
						[clou.crossover.config.includes :as includes]))

(defn include-js
  "Include a list of external javascript files."
  [& scripts]
  (for [script scripts]
    [:script {:type "text/javascript", :src script}]))

(defn include-css
  "Include a list of external stylesheet files."
  [& styles]
  (for [style styles]
    [:link {:type "text/css", :href style, :rel "stylesheet"}]))

(defn head []
	[:head
	  [:meta {:charset "utf-8"}]
    [:meta {:http-equiv "X-UA-Compatible" :content "IE=Edge,chrome=1"}]
    [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
		[:title (:title "clou")]
		(map include-css includes/styles)
		(map include-js includes/javascripts)])

(defn layout
	[args]
	[:html {:lang "en"}
		(head)
		[:body.application [:div.container {:id :content} args]]])


