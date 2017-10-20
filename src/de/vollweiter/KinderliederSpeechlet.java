package de.vollweiter;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.speechlet.interfaces.audioplayer.AudioItem;
import com.amazon.speech.speechlet.interfaces.audioplayer.AudioPlayer;
import com.amazon.speech.speechlet.interfaces.audioplayer.PlayBehavior;
import com.amazon.speech.speechlet.interfaces.audioplayer.Stream;
import com.amazon.speech.speechlet.interfaces.audioplayer.directive.PlayDirective;
import com.amazon.speech.speechlet.interfaces.audioplayer.request.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;

import java.util.ArrayList;
import java.util.List;

public class KinderliederSpeechlet implements Speechlet, AudioPlayer {

    //////////////////////////////////
    // Speechlet methods
    //////////////////////////////////
    @Override
    public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {

    }

    @Override
    public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
        // Play song after launch
        return playSong();
    }

    @Override
    public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        switch (intentName) {
            case "HelloIntent":
                return getHelloResponse();
            default:
                throw new SpeechletException("Invalid Intent");

        }
    }

    //////////////////////////////////
    // Audio Player methods
    //////////////////////////////////
    @Override
    public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {

    }

    @Override
    public SpeechletResponse onPlaybackFailed(SpeechletRequestEnvelope<PlaybackFailedRequest> requestEnvelope) {
        return null;
    }

    @Override
    public SpeechletResponse onPlaybackFinished(SpeechletRequestEnvelope<PlaybackFinishedRequest> requestEnvelope) {
        return null;
    }

    @Override
    public SpeechletResponse onPlaybackNearlyFinished(SpeechletRequestEnvelope<PlaybackNearlyFinishedRequest> requestEnvelope) {
        return null;
    }

    @Override
    public SpeechletResponse onPlaybackStarted(SpeechletRequestEnvelope<PlaybackStartedRequest> requestEnvelope) {
        return null;
    }

    @Override
    public SpeechletResponse onPlaybackStopped(SpeechletRequestEnvelope<PlaybackStoppedRequest> requestEnvelope) {
        return null;
    }

    //////////////////////////////////
    // Services
    //////////////////////////////////
    private SpeechletResponse playSong() {

        String speechText = "Viel Spaß mit dem Lied";

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("Kinderlieder");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        Stream stream = new Stream();
        stream.setToken("1234");
        stream.setUrl("https://www.dropbox.com/s/fqrbcybxzcq0kdj/Yo%20Dave..where%27s%20the%20progress_.mp3?dl=0");
        stream.setOffsetInMilliseconds(0);

        AudioItem audioItem = new AudioItem();
        audioItem.setStream(stream);

        PlayDirective playDirective = new PlayDirective();
        playDirective.setAudioItem(audioItem);
        playDirective.setPlayBehavior(PlayBehavior.REPLACE_ALL);


        List<Directive> directives = new ArrayList<>();
        directives.add(playDirective);

        SpeechletResponse response = new SpeechletResponse();
        response.setOutputSpeech(speech);
        response.setDirectives(directives);
        response.setNullableShouldEndSession(true);
        response.setCard(card);

        return response;
    }

    private SpeechletResponse getHelloResponse() {
        String speechText = "Willkommen zu Kinderlieder. Sagen Sie Start für ein zufälliges Lied";

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("Kinderlieder");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }

}
