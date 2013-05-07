(ns clou.client.core
  (:use-macros [dommy.core-compile :only [sel sel1]])
  (:require [clojure.browser.repl :as repl]
            [dommy.template :as dommy-template]
            [clojure.string :as string]
            [dommy.core :as dom]))

;; init browser connected repl

(defn start-repl-server [] (repl/connect "http://localhost:9000/repl"))

;; util logging helpers

(defn log [v & text]
  (let [vs (if (string? v)
             (apply str v text)
             v)]
    (. js/console (log vs))))

(defn log-obj
  "Print a JS object to the console."
  [obj]
  (.log js/console obj)
  obj)


(defn ->html
  [hiccup]
  (dommy-template/node hiccup))



;; Main view

(def view
  [:div.container-fluid {:id "content"}
    [:div.row-fluid
      [:div.span6 {:id "left-text"}
        [:form
          [:textarea {:id "text-input"}]]]
      [:div.span6 {:id "right-text"}
        [:div {:id "text-output"}]]]])


;; view helpers

(defn get-editor
  []
  (sel1 :#text-input))

(defn get-preview
  []
  (sel1 :#text-output))



;; markdown update render handler

(defn handle-update
  [editor instance change]
  (when editor
    (let [preview           (get-preview)
          editor-value      (.getValue editor)
          value             (if (> (count editor-value) 0)
                              editor-value
                              " ")
          rendered-elements (dommy-template/html->nodes
                              (js/markdown.toHTML value))
          preview-panel     (->html
                              [:div {:id "text-output"}])]
      (reduce #(dom/append! %1 (->html %2)) preview-panel rendered-elements)
      (dom/replace! preview
        preview-panel))))



(defn init-code-mirror
  []
  (let [text-area       (get-editor)
        update-delay    300
        cm-default-opts (clj->js
                          {:mode    "markdown"
                           :theme   "default"
                           :tabMode "indent"})
        editor          (CodeMirror/fromTextArea text-area cm-default-opts)]
    (.on editor "change" (partial handle-update editor))
    (js/setTimeout handle-update update-delay)))


(defn init-view
  []
  (dom/replace!
    (sel1 "#content")
    (->html view)))


(defn init-app
  []
  (start-repl-server)
  ; (init-view)
  ; (init-code-mirror)
  )


(defn when-ready
  [f]
  (set! (.-onload js/window) f))

(when-ready init-app)

