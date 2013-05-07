(ns clou.crossover.views.registration)


(defn register-form
  []
  [:div.row
    [:div.span4.offset4
      [:div.row
        [:label "Email"]
        [:input.input {:type :text}]]
      [:div.row
        [:label "Password"]
        [:input.input {:type :text}]]
      [:div.row
        [:label "Password Confirmation"]
        [:input.input {:type :text}]]
      [:div.row
        [:btn.btn.btn-primary "Sign up"]]]])