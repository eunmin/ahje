(ns ahje.core
  (:gen-class)
  (:import org.openkoreantext.processor.OpenKoreanTextProcessorJava
           org.openkoreantext.processor.KoreanPosJava))

(defn normalize [text]
  (OpenKoreanTextProcessorJava/normalize text))

(defn tokenize [normalized-text]
  (map (comp #(update % :pos str)
             #(dissoc % :class)
             bean)
       (OpenKoreanTextProcessorJava/tokensToJavaKoreanTokenList
        (OpenKoreanTextProcessorJava/tokenize normalized-text))))

(defn filter-pos [tokens pos]
  (->> tokens
       (filter #(= (:pos %) pos))
       (map :text)))

(def poss ["Noun"
           "Verb"
           "Adjective"
           "Adverb"
           "Determiner"
           "ExclamationJosa"
           "Eomi"
           "PreEomi"
           "ConjunctionModifier"
           "VerbPrefix"
           "Suffix"
           "Unknown"])

(defn -main [& [filename pos]]
  (try
    (let [text (slurp filename)
          pos (or pos "Noun")]
      (doseq [noun (filter-pos (tokenize (normalize text)) pos)]
        (println noun)))
    (catch Throwable t
      (println "Usage: java -jar ahje.jar filename" poss))))
