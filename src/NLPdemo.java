package nlp;

import edu.stanford.nlp.simple.*;

public class NLPdemo {

    Sentence sent = new Sentence("Lucy is in the sky with diamonds.");
    List<String> nerTags = sent.nerTags();  // [PERSON, O, O, O, O, O, O, O]
    String firstPOSTag = sent.posTag(0);
}
