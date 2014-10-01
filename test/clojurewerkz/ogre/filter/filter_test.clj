(ns clojurewerkz.ogre.filter.filter-test
  (:use [clojure.test])
  (:require [clojurewerkz.ogre.core :as q]
            [clojurewerkz.ogre.vertex :as v]
            [clojurewerkz.ogre.graph :as g]
            [clojurewerkz.ogre.test-util :as u]))

(deftest test-filter-step
  (testing "test_g_V_filterXfalseX"
    (let [g (g/new-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/filter (constantly false))                    
                      (q/into-vec!))]
      (is (= 0 (count vs)))))

  (testing "test_g_V_filterXtrueX"
    (let [g (g/new-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/filter (constantly true))                    
                      (q/into-vec!))]
      (is (= 6 (count vs)))))
  
  (testing "test_g_V_filterXlang_eq_javaX()"
    (let [g (g/new-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/filter (partial u/prop-pred :lang = "java"))                    
                      (q/into-vec!))]
      (is (= 2 (count vs)))
      (is (= #{"lop" "ripple"} (u/get-names-set vs)))))

  (testing "test_g_v1_out_filterXage_gt_30X"
    (let [g (g/new-tinkergraph)
          vs (q/query (v/find-by-id g 1)
                      (q/-->)
                      (q/filter (fn [v]
                                  (let [age (v/get v :age)]
                                    (and (not (nil? age)) (> age 30)))))                    
                      (q/into-vec!))]
      (is (= 1 (count vs)))
      (is (= #{32} (set (map #(v/get % :age) vs))))))

  (testing "test_g_V_filterXname_startsWith_m_OR_name_startsWith_pX()"
    (let [g (g/new-tinkergraph)
          vs (q/query (v/get-all-vertices g)
                      (q/filter #(->> %
                                    (v/get % :name)
                                    first
                                    #{\m \p}
                                    boolean))                    
                      (q/into-vec!))]
      (is (= 2 (count vs)))
      (is (= #{"marko" "peter"} (u/get-names-set vs))))))