(ns clojurewerkz.ogre.suite.add-vertex-test
  (:refer-clojure :exclude [and count drop filter group-by key key identity iterate loop map max min next not or range repeat reverse shuffle])
  (:require [clojure.test :refer [deftest testing is]]
            [clojurewerkz.ogre.core :as q])
  (:import (org.apache.tinkerpop.gremlin.structure T Vertex VertexProperty$Cardinality)
           (org.apache.tinkerpop.gremlin.process.traversal P)))

(defn get_g_VX1X_addVXanimalX_propertyXage_selectXaX_byXageXX_propertyXname_puppyX
  "g.V(v1Id).as('a').addV('animal').property('age', __.select('a').by('age')).property('name', 'puppy')"
  [g v1Id]
  (q/traverse g (q/V v1Id) (q/as :a)
                (q/add-V :animal)
                (q/property :age (q/__ (q/select :a) (q/by :age)))
                (q/property :name "puppy")))

(defn get_g_addVXpersonX_propertyXname_stephenX
  "g.addV('person').property('name', 'stephen')"
  [g]
  (q/traverse g (q/add-V :person)
                (q/property :name "stephen")))

(defn get_g_addVXpersonX_propertyXname_stephenX_propertyXname_stephenmX
  "g.addV('person').property('name', 'stephen').property('name', 'stephenm')"
  [g]
  (q/traverse g (q/add-V :person)
                (q/property :name "stephen")
                (q/property :name "stephenm")))

(defn get_g_addVXpersonX_propertyXsingle_name_stephenX_propertyXsingle_name_stephenmX
  "g.addV('person').property(VertexProperty.Cardinality.single, 'name', 'stephen').property(VertexProperty.Cardinality.single, 'name', 'stephenm')"
  [g]
  (q/traverse g (q/add-V :person)
                (q/property (VertexProperty$Cardinality/single) :name "stephen")
                (q/property (VertexProperty$Cardinality/single) :name "stephenm")))

(defn get_g_addVXpersonX_propertyXsingle_name_stephenX_propertyXsingle_name_stephenm_since_2010X
  "g.addV('person').property(VertexProperty.Cardinality.single, 'name', 'stephen').
     property(VertexProperty.Cardinality.single, 'name', 'stephenm', 'since', 2010)"
  [g]
  (q/traverse g (q/add-V :person)
                (q/property (VertexProperty$Cardinality/single) :name "stephen")
                (q/property (VertexProperty$Cardinality/single) :name "stephenm" :since (int 2010))))

(defn get_g_V_hasXname_markoX_propertyXfriendWeight_outEXknowsX_weight_sum__acl_privateX
  "g.V().has('name', 'marko').property('friendWeight', __.outE('knows').values('weight').sum(), 'acl', 'private')"
  [g]
  (q/traverse g (q/V)
                (q/has :name "marko")
                (q/property :friendWeight (q/__ (q/outE :knows) (q/values :weight) (q/sum)) :acl "private")))

(defn get_g_addVXanimalX_propertyXname_mateoX_propertyXname_gateoX_propertyXname_cateoX_propertyXage_5X
  "g.addV('animal').property('name', 'mateo').property('name', 'gateo').property('name', 'cateo').property('age', 5)"
  [g]
  (q/traverse g (q/add-V :animal)
                (q/property :name "mateo")
                (q/property :name "gateo")
                (q/property :name "cateo")
                (q/property :age (int 5))))

(defn get_g_V_addVXanimalX_propertyXname_valuesXnameXX_propertyXname_an_animalX_propertyXvaluesXnameX_labelX
  "g.V().addV('animal').property('name', __.values('name')).property('name', 'an animal').property(__.values('name'), __.label())"
  [g]
  (q/traverse g (q/V)
                (q/add-V :animal)
                (q/property :name (q/__ (q/values :name)))
                (q/property :name "an animal")
                (q/property (q/__ (q/values :name)) (q/__ (q/label)))))

(defn get_g_V_addVXanimalX_propertyXage_0X
  "g.V().addV('animal').property('age', 0)"
  [g]
  (q/traverse g (q/V)
                (q/add-V :animal)
                (q/property :age (int 0))))

(defn get_g_withSideEffectXa_markoX_addV_propertyXname_selectXaXX_name
  "g.withSideEffect('a', 'marko').addV().property('name', select('a')).values('name')"
  [g]
  (q/traverse g (q/with-side-effect :a "marko")
                (q/add-V)
                (q/property :name (q/__ (q/select :a)))
                (q/values :name)))

(defn get_g_withSideEffectXa_testX_V_hasLabelXsoftwareX_propertyXtemp_selectXaXX_valueMapXname_tempX
  "g.withSideEffect('a', 'test').V().hasLabel('software').property('temp', select('a')).valueMap('name', 'temp')"
  [g]
  (q/traverse g (q/with-side-effect :a "test")
                (q/V)
                (q/has-label :software)
                (q/property :temp (q/__ (q/select :a)))
                (q/value-map :name :temp)))
