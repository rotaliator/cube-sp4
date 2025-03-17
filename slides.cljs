(ns slides
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]))

(def solution (r/atom []))

(defn play-alg [setup alg opts]
  [:twisty-player (merge {"experimental-setup-alg" setup
                          :alg                     alg
                          #_#_:background          "#FFFFFF"
                          "hint-facelets"          "none"
                          :border                  :black}
                         opts)])

(defn gen-solution
  [config]
  (let [steps     (:steps config)
        solutions (first (:solutions config))
        setups    (reductions (fn [acc s] (str acc " " s)) "" solutions)]
    (for [[step setup solution] (map vector steps setups solutions)]
      [:section
       [:h5 {:dangerouslySetInnerHTML {:__html step}}]
       [play-alg setup solution {}]])))


(-> (js/fetch "steps.edn")
    (.then (fn [r] (.text r)))
    (.then (fn [r]
             (println "config loaded...")
             (let [config (read-string r)]
               (reset! solution (gen-solution config))))))


(defn slides []
  [:<>

   [:section
    [:h2 "Kostka Rubika"]
    [:img {:src "assets/img/product_3x3_main.jpeg"}]]

   [:section
    [:h3 "Wynalazca Ernő Rubik 🇭🇺"]
    [:img {:src "assets/img/erno-rubik-w-invention-rubik-26423107_57d26a.jpg"}]]

   [:section
    [:h3 "Pierwsza kostka wykonana przez Erno Rubika w 1974r (prototyp)"]
    [:img {:src "assets/img/9861038_orig.jpg"}]
    [:small
     [:p "w 1975r Ernő Rubik zgłasza patent na mechanizm kostki"]]]

   [:section
    [:h3 "Pierwsza \"Magiczna Kostka\" wyprodukowana na Węgrzech 🇭🇺 w 1977r"]
    [:img {:src "assets/img/magic-cube.jpg"}]
    [:p "Nie była znana poza Węgrami."]
    [:p "Ernő Rubik zabrał kostkę na międzynarodowe targi zabawek gdzie wzbudziła duże zainteresowanie"]]

   [:section
    [:h3 "Zmieniono nazwę kostki na \"Kostka Rubika\""]
    [:img {:src "assets/img/1980.jpg"}]
    [:small
     [:p "Kostka stała się dostępna prawie na całym świecie"]
     [:p "Bardzo szybko zyskała popularność..."]]]

   [:section
    [:h3 "Powstały kolejne kostki..."]
    [:img {:src "assets/img/2x2-4x4-5x5-rubiks-cube.JPG"}]]

   [:section
    [:h3 "Na ile sposobów można pomieszać kostkę 3x3x3 ? 🤔"]
    [:p "43 252 003 274 489 856 000 (ponad 43 tryliony)"]
    [:p "To więcej niż ziaren piasku na wszystkich plażach świata 🏖️"]
    #_[:p "Taka ilość kostek zakryłaby ziemię 275 razy "]
    (let [height (-> (* 43252003274489856000 57) ;; mm
                     (/ 1000) ; m
                     (/ 300000000) ; sek
                     (/ 3600)  ; godz
                     (/ 24) ; dni
                     (/ 365)  ;lat
                     (/ 1.0)
                     js/Math.round)]
      [:p "Ułożona z nich wieża miałaby wysokość około " height " lat świetlnych 😱"])]

   [:section
    [:h2 "Kostka 5x5x5 ma...."]
    [:small
     [:p "282 870 942 277 741 856 536 180 333 107 150 328 293 127 731 985 672 134 721 536 000 000 000 000 000 kombinacji"]
     [:p "To więcej niż szacowana ilość atomów we wszechświecie 🤯.. których jest około:"]
     [:p "100 000 000 000 000 000 000 000 000 000 000 000 000 000 000 000 000 000 000 000 000 000 000 000"]]]

   [:section
    [:h3 "ale na tym nie koniec..."]
    [:h3 "21x21x21"]
    [:img {:src "assets/img/moyu-21x21x21.jpg"}]]

   [:section
    [:h3 "34x34x34"]
    [:div [:img {:src "assets/img/34x34x34.jpg"}]]
    [:small [:a {:href "https://www.youtube.com/watch?v=ocy09pzME4E"} "Video"]]]

   [:section
    [:h3 "Powstały jeszcze większe kostki!"]
    [:img {:src "assets/img/tony_fisher.jpg"}]
    [:p "Konstruktor Tony Fisher"]]

   [:section
    [:h3 "i nawet 100x100x100 !"]
    [:img {:src "assets/img/100x100x100.jpg"}]
    [:p "Konstruktor, oczywiście Tony Fisher"]]

   [:section
    [:h3 "Początki speedcubingu"]
    [:h3 "Pierwsze mistrzostwa świata w układaniu kostki. Rok 1982"]
    [:img {:src "assets/img/minh-thai-first-speedcubing-competition-record-1982.jpg"}]
    [:p "Czas zwycięzcy: 22.95"]]

   [:section
    [:h3 "W 2003 powstaje WCA - World Cube Association"]
    [:img {:src "assets/img/WCA Logo.svg"}]
    [:p [:a {:target "_blank" :href "https://www.worldcubeassociation.org/"} "Strona WCA"]]
    [:p [:a {:target "_blank" :href "https://www.speedcubing.pl"} "Polskie Stowarzyszenie Speedcubingu"]]]

   [:section
    [:h3 "Oficjalny rekord pojedynczego ułożenia..."]
    [:img {:src "assets/img/max-park-new-3x3-wr-3-13-v0-py7svomxnf5b1.webp"}]
    [:small
     [:p "... to 3.13s 🤯"]
     [:p "Max Park 🇺🇸"]]]

   [:section
    [:h3 "Już nieaktualny!"]
    [:img {:src "assets/img/max-park-new-3x3-wr-3-13-v0-py7svomxnf5b1.webp"}]
    [:small
     [:p "... to 3.13s 🤯"]
     [:p "Max Park 🇺🇸"]]]

   [:section
    [:h3 "Aktualnym rekordzistą jest Yiheng Wang 🇨🇳 (wiek: 11 lat)..."]
    #_[:img {:src "assets/img/Yiheng_Wang_308.jpg"}]
    [:video {:controls true}
     [:source {:src "/assets/vid/Yiheng_Wang_308.mp4" :type "video/mp4"}]
     "Your browser does not support the video tag."]
    [:small
     [:p "... z czasem 3.08s 🚀"]
     [:a {:target "_blank" :href "https://www.youtube.com/watch?v=3BKV7mYhgpI"} "Video"]]]

   [:section
    [:h3 "Yiheng Wang 🇨🇳 używa (znacznie rozbudowanej 😉) metody"]
    [:h3 "opracowanej już w 1983r..."]]

   [:section
    [:h3 "Rok 1983"]
    [:p "Jessica Fridrich 🇨🇿 opracowuje metodę nazwaną jej nazwiskiem"]
    [:img {:src "assets/img/jessica-fridrich-1.jpg"}]
    [:small
     [:p "Metoda znana również pod nazwą CFOP"]
     [:a {:target "_blank" :href "https://www.speedcubing.pl/aktualnosci/ciekawostki/573/kobieta-ktora-zmienila-oblicze-kostki-rubika"} "Wywiad"]]]

   [:section
    [:h3 "Zbigniew Zborowski 🇵🇱"]
    [:small
     [:p "Opracowuje własną metodę układania kostki ZZ"]
     [:p "Z.Z. i Ron van Bruchem 🇳🇱 dopracowują i popularyzują metodę ZBLL, której aktualnie używają czołowi speedcuberzy."]]
    [:img {:src "assets/img/zbyszek-zborowski.jpg"}]]

   [:section
    [:h3 [:a {:target "_blank" :href "https://www.worldcubeassociation.org/persons/2016KOLA02"} "Tymon Kolasiński 🇵🇱"]]
    [:p "Najlepszy polski speedcuber i jeden z najlepszych na świecie"]
    [:img {:src "assets/img/tymon.jpg"}]]

   [:section
    [:h3 [:a {:target "_blank" :href "https://www.worldcubeassociation.org/persons/2016KOLA02"} "Tymon Kolasiński 🇵🇱"]]
    [:p "Brązowy medalista Mistrzostw Świata 3x3x3 🥉 (za wspomnianymi wcześniej Maxem Parkiem🥇 i Yiheng Wangiem🥈 )"]
    [:p "Mistrz Świata 4x4x4🥇 i 6x6x6🥇"]
    [:p "Rekordzista świata w średniej 4x4x4 i pojedynczym ułożeniu 5x5x5 (30.45)"]
    [:p "Były rekordzista świata w średniej 3x3x3 i Pyraminx"]]

   [:section
    [:h3 [:a {:target "_blank" :href "https://www.worldcubeassociation.org/persons/2021ZAJD03"} "Teodor Zajder 🇵🇱 (wiek, 8 lat)"]]
    [:img {:src "assets/img/teodor_zajder_2025.jpg"}]]

   [:section
    [:h3 [:a {:target "_blank" :href "https://www.worldcubeassociation.org/persons/2021ZAJD03"} "Teodor Zajder 🇵🇱"]]
    [:p "Rekordzista świata pojedynczym ułożeniu 2x2x2 (0.43s)"]
    [:p "Rekordzista Europy w średniej 2x2x2"]
    [:p "Mistrz Europy 2024 w 2x2x2 🥇"]]

   [:section
    [:h3 [:a {:target "_blank" :href "https://www.worldcubeassociation.org/persons/2013BOBE01"}
          "Krzysztof Bober 🇵🇱"]]
    [:p "Aktualny Mistrz Świata w układaniu wielu kostek 3x3x3 bez patrzenia 🙈"]
    [:img {:src "assets/img/krzysztof_bober.jpg"}]
    [:p "na zdjęciu (udana) próba pobicia rekordu Europy 51/55 58:06"]]

   [:section
    [:h2 "Układamy!"]
    [:p "Metodą LBL - Warstwa po warstwie"]
    [:a {:target "_blank" :href "https://www.youtube.com/watch?v=3VIlk6XN7PM"} "wg. doskonałego poradnika Adama Polkowskiego"]]

   [:section
    [:table
     [:tr [:th "LBL - osiem kroków"] [:th "CFOP - cztery kroki"]]
     [:tr [:td
           [:span "Stokrotka 🌼 / Myszy do sera 🐁"]
           [:br]
           [:span "Biały krzyż ➕ / Myszy do piwnicy 🏠"]] [:td "➕ Cross"]]
     [:tr [:td
           [:span "Pierwsza warstwa / Koty 🐈"]
           [:br]
           [:span "Druga wartstwa / Papugi 🦜"]] [:td "F2L - First Two Layers"]]

     [:tr [:td
           [:span "Żółty krzyż ➕"]
           [:br]
           [:span "Warsztat samochodowy 🚗"]] [:td "OLL – Orientation of the Last Layer 🟨"]]

     [:tr [:td
           [:span "Permutacja A / Sanki 🛷"]
           [:br]
           [:span "Permutacja U / Rower 🚴‍♂️"]] [:td "PLL – Permutation of the Last Layer ☑️"]]]]

   [:section
    [:h2 "Zanim pomieszacie kostki... Rozgrzewka!"]
    [:p ["Cykle..."]]
    [:ul
     [:li "Góra ⬆️"]
     [:li "Lewo ⬅️"]
     [:li "Dół ⬇️"]
     [:li "Prawo ➡️"]]
    [:p "Powtarzamy 6 razy"]]


   [:section
    [:p "(Góra ⬆️ Lewo ⬅️ Dół ⬇️ Prawo ➡️)*6"]
    [play-alg "" "R U R' U' R U R' U' R U R' U' R U R' U' R U R' U' R U R' U'" {}]]

   (nth @solution 1 [:section])
   (nth @solution 2 [:section])
   (nth @solution 3 [:section])
   (nth @solution 4 [:section])

   [:section
    [:h4 "kolejny etap... żółty krzyż ➕"]
    [play-alg "x2 R U2 R2 U' R2 U' R2 U2 R U'" "" {:visualization "experimental-2D-LL" "control-panel" :none}]]

   [:section
    [:h4 "kropka •"]
    [play-alg "x2 R U' R2 D' r U r' D R2 U R'" "" {:visualization "experimental-2D-LL" "control-panel" :none}]]
   [:section
    [:h4 "but 👟"]
    [play-alg "x2 F (U R U' R') (U R U' R') F'" "" {:visualization "experimental-2D-LL" "control-panel" :none}]]
   [:section
    [:h4 "minus ➖"]
    [play-alg "x2 F (R U R' U') (R U R' U') F'" "" {:visualization "experimental-2D-LL" "control-panel" :none}]]

   [:section
    [:h4 "Jaki to przypadek? • 👟 ➖"]]
   [:section
    [:h4 "?"]
    [play-alg "x2 F R U R' U' F' U" "" {:visualization "experimental-2D-LL" "control-panel" :none}]]
   [:section
    [:h4 "?"]
    [play-alg "x2 r U r' R U R' U' r U' r'" "" {:visualization "experimental-2D-LL" "control-panel" :none}]]
   [:section
    [:h4 "?"]
    [play-alg "x2 L U F' U' L' U L F L' U" "" {:visualization "experimental-2D-LL" "control-panel" :none}]]

   (nth @solution 5 [:section])
   (nth @solution 6 [:section])
   (nth @solution 7 [:section])
   (nth @solution 8 [:section])

   [:section
    [:h4 "🤗"]
    [play-alg "" "" {"control-panel" :none}]]

   [:section
    [:h4 "Przydatne linki..."]
    [:p [:a {:target "_blank" :href "https://www.youtube.com/@Rubitrener"} "Kanał Adama Polkowskiego na YT"]]
    [:p [:a {:target "_blank" :href "https://rubitrener.kursy.onl"} "Kurs CFOP Adama Polkowskiego (Płatny)"]]
    [:p [:a {:target "_blank" :href "https://www.youtube.com/@GrzegorzPacewicz"} "Kanał Grzegorza Pacewicza na YT"]]
    [:p [:a {:target "_blank" :href "https://www.cubeskills.com/uploads/pdf/tutorials/f2l.pdf"} "Algorytmy F2L Felixa Zemdegsa"]]
    [:p [:a {:target "_blank" :href "https://cubingapp.com/algorithms/"} "Zbiór algorytmów"]]]

   [:section
    [:h4 "Polecane kostki"]
    [:small
     [:p [:a {:href "https://strefa-kostek.pl/moyu-rs3m-v5-magnetic-3x3x3-6255.html?gad_source=1#/44-kolor-color_bright"}
          "MoYu RS3M V5 Magnetic 3x3x3 30zł"]]
     [:p [:a {:href "https://strefa-kostek.pl/yj-yulong-v2-m-3x3x3-3284.html#/44-kolor-color_bright"}
          "YJ YuLong V2 M 3x3x3 40zł"]]]]

   [:section
    [:h4 "Dodatkowe informacje na temat historii"]
    [:p [:a {:target "_blank" :href "https://ruwix.com/the-rubiks-cube/history-rubiks-cube/important-dates-timeline/"} "Oś czasu"]]
    [:p [:a {:target "_blank" :href "https://www.speedcubing.pl/aktualnosci/ciekawostki/573/kobieta-ktora-zmienila-oblicze-kostki-rubika"}
         "Wywiad z Jessicą Fridrich"]]]])


(defonce state (r/atom nil))

(defn get-slide-count []
  (aget
    (js/document.querySelectorAll "section")
    "length"))

(defn move-slide! [state ev dir-fn]
  (.preventDefault ev)
  (let [new-state  (swap! state update :slide dir-fn)]
    (set! (.-hash js/window.location) (str "#" (:slide new-state)))))

(defn clickable? [ev]
  (let [tag-name (.toLowerCase (aget ev "target" "tagName"))]
    (contains? #{"button" "label" "select"
                 "textarea" "input" "a"
                 "details" "summary"}
               tag-name)))

(defn keydown
  [ev]
  (when (not (clickable? ev))
    (let [k (aget ev "key")]
      (cond
        (contains? #{"ArrowLeft" "ArrowUp" "PageUp"} k)
        (move-slide! state ev dec)
        (contains? #{"ArrowRight" "ArrowDown" "PageDown" "Enter" " "} k)
        (move-slide! state ev inc)
        (contains? #{"Escape" "Home" "h"} k)
        (swap! state assoc :slide 0)
        (contains? #{"End"} k)
        (swap! state assoc :slide (dec (get-slide-count)))))))

(defn component:show-slide [state]
  [:style (str "section:nth-child("
               (inc (mod (:slide @state) (get-slide-count)))
               ") { display: block; }")])

(defn component:touch-ui [state]
  [:div#touch-ui
   {:style {:opacity
            (if (:touch-ui @state) 0 1)}}
   [:div {:on-click #(move-slide! state % dec)} "⟪"]
   [:div {:on-click #(move-slide! state % inc)} "⟫"]])

(defn component:slide-viewer [state]
  [:<>
   [:main {:on-click
           #(when (not (clickable? %))
              (swap! state update :touch-ui not))}
    [slides]]
   [component:show-slide state]
   [component:touch-ui state]])

(rdom/render
  [component:slide-viewer state]
  (.getElementById js/document "app"))
(defonce keylistener (aset js/window "onkeydown" #(keydown %)))
; trigger a second render so we get the sections count


(let [page (some-> (.-hash js/window.location) (clojure.string/replace "#" ""))
      page (if (empty? page) 0 (js/parseInt page 10))]
  (swap! state assoc :slide page :touch-ui true))
