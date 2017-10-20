package de.vollweiter;

import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletV2;
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

public final class KinderliederRequestStreamHandler extends SpeechletRequestStreamHandler {
    private static final Set<String> supportedApplicationIds = new HashSet<String>();

    static {
        supportedApplicationIds.add("amzn1.ask.skill.8aa2a253-c283-4b89-b528-1084ea84dd70");
    }

    public KinderliederRequestStreamHandler() {
        super(new KinderliederSpeechlet(), supportedApplicationIds);
    }
}
