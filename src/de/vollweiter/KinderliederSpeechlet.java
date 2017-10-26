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
import com.amazon.speech.speechlet.interfaces.system.SystemInterface;
import com.amazon.speech.speechlet.interfaces.system.SystemState;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class KinderliederSpeechlet implements Speechlet, AudioPlayer {

    private Logger logger = LoggerFactory.getLogger(KinderliederSpeechlet.class);

    //////////////////////////////////
    // Speechlet methods
    //////////////////////////////////
    @Override
    public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
        BasicConfigurator.configure();

        DynamoDbService dynamoDbService = new DynamoDbService();
        dynamoDbService.init();
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
                return stopResponse();
            case "AMAZON.StopIntent":
                return stopResponse();
            case "AMAZON.PauseIntent":
                // When currently playing an audio file, pause intent is invoked by "stop utterance" >:(
                return stopPlaybackResponse(request, session);
            case "AMAZON.HelpIntent":
                return helpResponse();
            case "RandomSongIntent":
                return playRandomSong();
            case "EntenIntent":
                return playSpecificSong(0);
            case "VoegelIntent":
                return playSpecificSong(1);
            case "MauerIntent":
                return playSpecificSong(2);
            case "WiesenIntent":
                return playSpecificSong(3);
            case "KuchenIntent":
                return playSpecificSong(4);
            case "JakobIntent":
                return playSpecificSong(5);
            case "WandernIntent":
                return playSpecificSong(6);
            case "KuckuckIntent":
                return playSpecificSong(7);
            case "AffenIntent":
                return playSpecificSong(8);
            case "MondIntent":
                return playSpecificSong(9);
            case "GedankenIntent":
                return playSpecificSong(10);
            case "HandwerkerIntent":
                return playSpecificSong(11);
            case "VogelhochzeitIntent":
                return playSpecificSong(12);
            case "ChinesenIntent":
                return playSpecificSong(13);
            case "MaennleinIntent":
                return playSpecificSong(14);
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
        getNextSongResponse();
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

    /**
     * Used when the audio stream is stopped by the stop directive.
     *
     * @param requestEnvelope the request envelope containing the playback nearly failed request to handle
     * @return
     */
    @Override
    public SpeechletResponse onPlaybackStopped(SpeechletRequestEnvelope<PlaybackStoppedRequest> requestEnvelope) {

        SystemState state = requestEnvelope.getContext().getState(SystemInterface.class, SystemState.class);

        DynamoDbService dynamoDbService = new DynamoDbService();
        try {
            dynamoDbService.updateSession(state.getUser().getUserId(),
                    requestEnvelope.getRequest().getOffsetInMilliseconds(), requestEnvelope.getRequest().getToken(),
                    "999");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //////////////////////////////////
    // Services
    //////////////////////////////////
    private SpeechletResponse getWelcomeResponse() {
        String speechText = "Hallo! Welches Lied möchtest du hören?";
        String repromptText = "Sag einfach zufälliges Lied oder den Namen eines bestimmten.";

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

    private SpeechletResponse helpResponse() {
        String speechText = "Die Namen aller Lieder findest du auf der Karte in der Alexa App. Sage zufälliges Lied oder den Namen von einem bestimmten.";
        String repromptText = "Sag einfach zufälliges Lied oder den Namen eines bestimmten. Die Namen aller Lieder findest du in der Alexa App";

        // Create the Simple card content with the repromt text.
        SimpleCard card = new SimpleCard();
        card.setTitle("Kinderlieder");

        AudioFileReference audioFileReference = new AudioFileReference();
        List<String> songTitles = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            songTitles.add(audioFileReference.getNameOfSong(i));
        }
        card.setContent(songTitles.toString());

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

    private SpeechletResponse getNextSongResponse() {
        String speechText = "Welches Lied möchtest du jetzt hören?";
        String repromptText = "Sag einfach zufälliges Lied oder den Namen eines bestimmten.";

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

    private SpeechletResponse playSpecificSong(int songNumber) {

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
        try {
            stream.setUrl(new AudioFileReference().getSpecificAudioFile(songNumber));
        } catch (SpeechletException e) {
            e.printStackTrace();
        }
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

        String speechText = "Zufälliges Lied ausgewählt";

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("Kinderlieder");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        Stream stream = new Stream();
        stream.setToken("1234");
        stream.setUrl(new AudioFileReference().getRandomAudioFile());
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

    private SpeechletResponse stopPlaybackResponse(IntentRequest request, Session session) {

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

    private SpeechletResponse stopResponse() {

        String speechText = "Okay";
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
