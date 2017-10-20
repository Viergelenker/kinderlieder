package de.vollweiter;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.speechlet.interfaces.audioplayer.AudioItem;
import com.amazon.speech.speechlet.interfaces.audioplayer.AudioPlayer;
import com.amazon.speech.speechlet.interfaces.audioplayer.PlayBehavior;
import com.amazon.speech.speechlet.interfaces.audioplayer.Stream;
import com.amazon.speech.speechlet.interfaces.audioplayer.directive.PlayDirective;
import com.amazon.speech.speechlet.interfaces.audioplayer.directive.StopDirective;
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

        // Which song
        return getWelcomeResponse();
    }

    @Override
    public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        switch (intentName) {
            case "AMAZON.CancelIntent":
                return stopPlaybackResponse();
            case "AMAZON.StopIntent":
                return stopPlaybackResponse();
                // When currently playing an audio file, pause intent is invoked by "stop utterance" >:(
            case "AMAZON.PauseIntent":
                return stopPlaybackResponse();
            case "SpecificSongIntent":
                return playSpecificSong();
            case "RandomSongIntent":
                return playRandomSong();
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
    private SpeechletResponse getWelcomeResponse() {
        String speechText = "Hallo, was möchtest du hören?";
        String repromptText = "Sag mir einfach ein bestimmtes Lied, oder zufällige Wiedergabe";

        // Create the Simple card content with the repromt text.
        SimpleCard card = new SimpleCard();
        card.setTitle("Kinderlieder");
        card.setContent(repromptText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);
        PlainTextOutputSpeech repromptOutput = new PlainTextOutputSpeech();
        repromptOutput.setText(repromptText);

        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptOutput);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }

    private SpeechletResponse playSpecificSong() {

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
        stream.setUrl("https://s3-eu-west-1.amazonaws.com/bucket-vollweiter/01_-_Kinder_wollen_singen_-_Alle_meine_Entchen.mp3");
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

    private SpeechletResponse playRandomSong() {

        String speechText = "Viel Spaß mit dem Lied";

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("Kinderlieder");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        AudioFileReference audioFileReference = new AudioFileReference();

        Stream stream = new Stream();
        stream.setToken("1234");
        stream.setUrl(audioFileReference.getRandomAudioFile());
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

    private SpeechletResponse stopPlaybackResponse() {

        String speechText = "Okay, Wiedergabe angehalten.";
        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        List<Directive> directives = new ArrayList<>();
        directives.add(new StopDirective());

        SpeechletResponse response = new SpeechletResponse();
        response.setDirectives(directives);
        response.setOutputSpeech(speech);

        return response;
    }

    private SpeechletResponse unknownSongResponse() {
        String speechText = "Tut mir Leid, dieses Lied kenne ich nicht. Probiere es noch einmal!";

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
