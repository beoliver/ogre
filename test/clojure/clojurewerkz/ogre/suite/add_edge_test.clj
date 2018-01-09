(ns clojurewerkz.ogre.suite.add-edge-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex)
           (org.apache.tinkerpop.gremlin.process.traversal P Traversal)
           (java.util ArrayList)))

(defn get_g_VX1X_asXaX_outXcreatedX_addEXcreatedByX_toXaX_propertyXweight_2X
  "g.V(v1Id).as('a').out('created').addE('createdBy').to('a').property('weight', 2.0d)"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/as :a)
                (q/out :created)
                (q/add-E :createdBy)
                (q/to :a)
                (q/property :weight 2.0)))

(defn get_g_VX1X_asXaX_outXcreatedX_addEXcreatedByX_toXaX
  "g.V(v1Id).as('a').out('created').addE('createdBy').to('a')"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/as :a)
                (q/out :created)
                (q/add-E :createdBy)
                (q/to :a)))

(defn get_g_V_aggregateXxX_asXaX_selectXxX_unfold_addEXexistsWithX_toXaX_propertyXtime_nowX
  "g.V().aggregate('x').as('a').select('x').unfold().addE('existsWith').to('a').property('time', 'now')"
  [g]
  (q/traverse g (q/V)
                (q/aggregate :x) (q/as :a)
                (q/select :x)
                (q/unfold)
                (q/add-E :existsWith)
                (q/to :a)
                (q/property :time "now")))

(defn get_g_V_asXaX_outXcreatedX_inXcreatedX_whereXneqXaXX_asXbX_addEXcodeveloperX_fromXaX_toXbX_propertyXyear_2009X
  "g.V().as('a').out('created').in('created').where(P.neq('a')).as('b').addE('co-developer').from('a').to('b').property('year', 2009)"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/out :created)
                (q/in :created)
                (q/where (P/neq "a")) (q/as :b)
                (q/add-E :codeveloper)
                (q/from :a)
                (q/to :b)
                (q/property :year (int 2009))))

(defn get_g_V_asXaX_inXcreatedX_addEXcreatedByX_fromXaX_propertyXyear_2009X_propertyXacl_publicX
  "g.V().as('a').in('created').addE('createdBy').from('a').property('year', 2009).property('acl', 'public')"
  [g]
  (q/traverse g (q/V) (q/as :a)
                (q/in :created)
                (q/add-E :createdBy)
                (q/from :a)
                (q/property :year (int 2009))
                (q/property :acl "public")))

(defn get_g_addV_asXfirstX_repeatXaddEXnextX_toXaddVX_inVX_timesX5X_addEXnextX_toXselectXfirstXX
  "g.addV().as('first').repeat(__.addE('next').to(__.addV()).inV()).times(5).addE('next').to(select('first'))"
  [g]
  (q/traverse g (q/add-V) (q/as :first)
                (q/repeat (q/__ (q/add-E :next) (q/to (q/__ (q/add-V))) (q/inV)))
                  (q/times 5)
                (q/add-E :next) (q/to (q/__ (q/select :first)))))


(defn get_g_withSideEffectXb_bX_VXaX_addEXknowsX_toXbX_propertyXweight_0_5X
  "g.withSideEffect('b', b).V(a).addE('knows').to('b').property('weight', 0.5d)"
  [g]
  (let [a (q/traverse g (q/V) (q/has :name "marko") (q/next!))
        b (q/traverse g (q/V) (q/has :name "peter") (q/next!))]
    (q/traverse g (q/with-side-effect :b b) (q/V a) (q/add-E :knows) (q/to :b) (q/property :weight 0.5))))

(defn get_g_addEXknowsX_fromXaX_toXbX_propertyXweight_0_1X
  "g.addE('knows').from(a).to(b).property('weight', 0.1d)"
  [g]
  (let [a (q/traverse g (q/V) (q/has :name "marko") (q/next!))
        b (q/traverse g (q/V) (q/has :name "peter") (q/next!))]
    (q/traverse g
                (q/add-E :knows)
                (q/from a)
                (q/to b)
                (q/property :weight 0.1))))

(defn get_g_VXaX_addEXknowsX_toXbX_propertyXweight_0_1X
  "g.V(a).addE('knows').to(b).property('weight', 0.1d)"
  [g]
  (let [a (q/traverse g (q/V) (q/has :name "marko") (q/next!))
        b (q/traverse g (q/V) (q/has :name "peter") (q/next!))]
    (q/traverse g
                (q/V a)
                (q/add-E :knows)
                (q/to b)
                (q/property :weight 0.1))))
