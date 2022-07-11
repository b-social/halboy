(ns halboy.support.api
  (:require [cheshire.core :as json]
            [halboy.resource :refer [new-resource add-links]]
            [halboy.json :refer [resource->json]]))

(defn- redirect-to [location]
  {:status  201
   :headers {"Location" location}})

(defn on-discover [url & kvs]
  {:request  {:url    url
              :method "GET"}
   :response {:status  200
              :headers {"Content-Type" "application/hal+json"}
              :body    (-> (new-resource)
                         ((partial apply add-links) kvs)
                         resource->json)}})

(defn on-head
  ([url response]
   {:request  {:urlPath url
               :method  "HEAD"}
    :response response}))

(defn on-get
  ([url response]
   {:request  {:urlPath url
               :method  "GET"}
    :response response})
  ([url params response]
   {:request  {:method          "GET"
               :urlPath         url
               :queryParameters params}
    :response response}))

(defn on-post
  ([url response]
   {:request  {:urlPath url
               :method  "POST"}
    :response response})
  ([url body response]
   {:request  {:urlPath      url
               :method       "POST"
               :bodyPatterns [{:equalToJson body}]}
    :response response}))

(defn on-post-with-headers
  ([url headers response]
   [{:method  :post
     :url     url
     :headers headers}
    response])
  ([url headers body response]
   [{:method  :post
     :url     url
     :headers headers
     :body    (json/generate-string body)}
    response]))

(defn on-post-redirect
  ([url location]
   (on-post url (redirect-to location)))
  ([url body location]
   (on-post url body (redirect-to location))))

(defn on-put
  ([url response]
   {:request  {:urlPath url
               :method  "PUT"}
    :response response})
  ([url body response]
   {:request  {:urlPath      url
               :method       "PUT"
               :bodyPatterns [{:equalToJson body}]}
    :response response}))

(defn on-put-redirect
  ([url location]
   (on-put url (redirect-to location)))
  ([url body location]
   (on-put url body (redirect-to location))))

(defn on-patch
  ([url response]
   {:request  {:urlPath url
               :method  "PATCH"}
    :response response})
  ([url body response]
   {:request  {:urlPath      url
               :method       "PATCH"
               :bodyPatterns [{:equalToJson body}]}
    :response response}))

(defn on-patch-redirect
  ([url location]
   (on-patch url (redirect-to location)))
  ([url body location]
   (on-patch url body (redirect-to location))))

(defn on-delete
  ([url response]
   {:request  {:urlPath url
               :method  "DELETE"}
    :response response})
  ([url params response]
   {:request  {:method          "DELETE"
               :urlPath         url
               :queryParameters params}
    :response response}))

(defn on-delete-with-headers
  [url headers response]
  {:request  {:urlPath url
              :method  "DELETE"
              :headers headers}
   :response response})
