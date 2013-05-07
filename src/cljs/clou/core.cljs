(ns clou.client.core
  (:use-macros [dommy.core-compile :only [sel sel1]])
  (:require [clojure.browser.repl :as repl]
            [dommy.template :as dommy-template]
            [clojure.string :as string]
            [dommy.core :as dommy]))

(defn log [v & text]
  (let [vs (if (string? v)
             (apply str v text)
             v)]
    (. js/console (log vs))))

(def cm-default-opts
  (clj->js
    {:mode "markdown"
     :theme "default"
     :tabMode "indent"}))

(defn get-editor
  []
  (sel1 :#text-input))

(defn get-preview
  []
  (sel1 :#text-output))

(def update-delay 300)

(defn handle-update
  [editor instance change]
  (when editor
    (let [preview (get-preview)
          editor-value (.getValue editor)
          value (if (> (count editor-value) 0)
                  editor-value
                  " ")
          rendered-elements (dommy-template/html->nodes
                          (js/markdown.toHTML value))
          preview-panel (dommy-template/node
                              [:div {:id "text-output"}])]
      (reduce #(dommy/append! %1 (dommy-template/node %2)) preview-panel rendered-elements)
      (dommy/replace! preview
        preview-panel))))

(defn handle-timeout
  []
  (js/clearTimeout update-delay)
  (js/setTimeout handle-update update-delay))

(defn init-code-mirror
  []
  (let [text-area (get-editor)
        editor (CodeMirror/fromTextArea text-area cm-default-opts)]
    (.on editor "change" (partial handle-update editor))
    (js/setTimeout handle-update update-delay)))

(defn start-repl-server [] (repl/connect "http://localhost:9000/repl"))

(defn generate-id-from-name
  [n]
  (str (string/lower-case (string/replace n #" " "-")) "-menu-item"))

(defn view
  []
  [:div.container-fluid {:id "content"}
    [:div.row-fluid
      [:div.span6 {:id "left-text"}
        [:form
          [:textarea {:id "text-input"}]]]
      [:div.span6 {:id "right-text"}
        [:div {:id "text-output"}]]]])

(defn init-view
  []
  (dommy/replace!
    (first (sel "#content"))
    (dommy-template/node (view))))

(defn relative-url
  []
  (let [url (.-URL js/document)
        a (dommy-template/node [:a {:href url}])]
    (.-pathname a)))


(defn init-app
  []
  (start-repl-server)
  (init-view)
  (init-code-mirror))


(defn when-ready
  [f]
  (set! (.-onload js/window) f))

(when-ready init-app)

