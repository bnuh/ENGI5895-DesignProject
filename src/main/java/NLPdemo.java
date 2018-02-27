import edu.stanford.nlp.simple.*;

public class NLPdemo {
    public static void main(String[] args) {
        Document doc = new Document("I hate Joe Johnson. Twitter is great.");
        for (Sentence sent : doc.sentences()) {
            System.out.println("The second word of the sentence '" + sent + "' is " + sent.nerTags());
            System.out.println("The sentiment of the sentence '" + sent + "' is " + sent.sentiment());        }
    }
}