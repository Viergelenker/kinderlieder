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
import java.util.LinkedList;
import java.util.List;

public class KinderliederSpeechlet implements Speechlet, AudioPlayer {

    private Logger logger = LoggerFactory.getLogger(KinderliederSpeechlet.class);

    //////////////////////////////////
    // Speechlet methods
    //////////////////////////////////
    @Override
    public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
        BasicConfigurator.configure();

        logger.info("Session started");

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
                return stopPlaybackResponse();
            case "AMAZON.ResumeIntent":
                return resumeRequest(session);
            case "AMAZON.HelpIntent":
                return helpResponse();
            case "AMAZON.NextIntent":
                return getNextSongResponse(session);
            case "RandomSongIntent":
                return playRandomSong(session);
            case "EntenIntent":
                return playSpecificSong(0, session);
            case "VoegelIntent":
                return playSpecificSong(1, session);
            case "MauerIntent":
                return playSpecificSong(2, session);
            case "WiesenIntent":
                return playSpecificSong(3, session);
            case "KuchenIntent":
                return playSpecificSong(4, session);
            case "JakobIntent":
                return playSpecificSong(5, session);
            case "WandernIntent":
                return playSpecificSong(6, session);
            case "KuckuckIntent":
                return playSpecificSong(7, session);
            case "AffenIntent":
                return playSpecificSong(8, session);
            case "MondIntent":
                return playSpecificSong(9, session);
            case "GedankenIntent":
                return playSpecificSong(10, session);
            case "HandwerkerIntent":
                return playSpecificSong(11, session);
            case "VogelhochzeitIntent":
                return playSpecificSong(12, session);
            case "ChinesenIntent":
                return playSpecificSong(13, session);
            case "MaennleinIntent":
                return playSpecificSong(14, session);
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
        logger.info("Playback nearly finished");
        return queueNextSong(requestEnvelope);
    }

    @Override
    public SpeechletResponse onPlaybackStarted(SpeechletRequestEnvelope<PlaybackStartedRequest> requestEnvelope) {

        SystemState state = requestEnvelope.getContext().getState(SystemInterface.class, SystemState.class);

        try {
            new DynamoDbService().updateSession(state.getUser().getUserId(),
                    requestEnvelope.getRequest().getOffsetInMilliseconds(),
                    requestEnvelope.getRequest().getToken(),
                    requestEnvelope.getRequest().getToken(),
                    new DynamoDbService().getUserSession(state.getUser().getUserId()).getSongQueue());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                    requestEnvelope.getRequest().getOffsetInMilliseconds(),
                    requestEnvelope.getRequest().getToken(),
                    requestEnvelope.getRequest().getToken(),
                    dynamoDbService.getUserSession(state.getUser().getUserId()).getSongQueue());
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("Playback stopped");
        return null;
    }

    //////////////////////////////////
    // Services
    //////////////////////////////////
    private SpeechletResponse getWelcomeResponse() {
        String speechText = "Hallo! Welches Lied möchtest du hören?";
        String repromptText = "Sag einfach zufälliges Lied oder den Namen eines Bestimmten.";

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

    private SpeechletResponse queueNextSong(SpeechletRequestEnvelope<PlaybackNearlyFinishedRequest> requestEnvelope) {

        SystemState state = requestEnvelope.getContext().getState(SystemInterface.class, SystemState.class);

        logger.info("Queue next song");

        UserSession userSession = null;

        try {
            userSession = new DynamoDbService().getUserSession(state.getUser().getUserId());
        } catch (Exception e) {
            logger.error("Unable to obtain userSession Object.");
        }

        logger.info("User session obtained");

        if (userSession.getSongQueue().isEmpty()) {

            return null;
        } else {

            String url = null;
            // Don't worry about this line, i just use the last object of my songList because it's cheaper to remove than the first entry.
            url = userSession.getSongQueue().get(userSession.getSongQueue().size() - 1);

            Stream stream = new Stream();
            stream.setUrl(url);
            stream.setOffsetInMilliseconds(0);
            stream.setExpectedPreviousToken(requestEnvelope.getRequest().getToken());
            stream.setToken(url);

            logger.info("Removing current song from list");
            List<String> songs = userSession.getSongQueue();
            songs.remove(userSession.getSongQueue().size() - 1);

            logger.info("Update the new session");
            try {
                new DynamoDbService().updateSession(state.getUser().getUserId(), 0, url,
                        requestEnvelope.getRequest().getToken(), songs);
            } catch (Exception e) {
                logger.warn(e.getMessage());
            }

            AudioItem audioItem = new AudioItem();
            audioItem.setStream(stream);

            PlayDirective playDirective = new PlayDirective();
            playDirective.setAudioItem(audioItem);
            playDirective.setPlayBehavior(PlayBehavior.ENQUEUE);

            List<Directive> directives = new ArrayList<>();
            directives.add(playDirective);

            SpeechletResponse response = new SpeechletResponse();
            response.setDirectives(directives);
            // response.setNullableShouldEndSession(true);

            logger.info("Return enqueue response");
            return response;
        }
    }

    private SpeechletResponse getNextSongResponse(Session session) {

        Stream stream = new Stream();
        String url = "empty";

        UserSession userSession = null;

        logger.info("Getting next song...");

        try {
            userSession = new DynamoDbService().getUserSession(session.getUser().getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (userSession.getSongQueue().isEmpty()) {

            logger.info("Song list empty");
            String speechText = "Tut mir Leid, das waren alle Lieder. Starte die Wiedergabe erneut mit einem zufälligen," +
                    " oder nenne mir ein bestimmtes Lied.";
            String repromptText = "Sag einfach zufälliges Lied oder den Namen eines bestimmten Liedes.";

            // Create the plain text output.
            PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
            speech.setText(speechText);
            PlainTextOutputSpeech repromptOutput = new PlainTextOutputSpeech();
            repromptOutput.setText(repromptText);

            // Create reprompt
            Reprompt reprompt = new Reprompt();
            reprompt.setOutputSpeech(repromptOutput);

            return SpeechletResponse.newAskResponse(speech, reprompt);

        } else {
            stream.setUrl(userSession.getSongQueue().get(userSession.getSongQueue().size() - 1));
            stream.setOffsetInMilliseconds(userSession.getSongQueue().size() - 1);
            stream.setToken(userSession.getSongQueue().get(userSession.getSongQueue().size() - 1));

            List<String> songs = userSession.getSongQueue();
            songs.remove(userSession.getSongQueue().size() - 1);

            try {
                new DynamoDbService().updateSession(session.getUser().getUserId(), 0, url, url, songs);
            } catch (Exception e) {
                logger.warn(e.getMessage());
            }

            AudioItem audioItem = new AudioItem();
            audioItem.setStream(stream);

            PlayDirective playDirective = new PlayDirective();
            playDirective.setAudioItem(audioItem);
            playDirective.setPlayBehavior(PlayBehavior.REPLACE_ALL);


            List<Directive> directives = new ArrayList<>();
            directives.add(playDirective);

            SpeechletResponse response = new SpeechletResponse();
            response.setDirectives(directives);
            response.setNullableShouldEndSession(true);

            return response;
        }
    }

    private SpeechletResponse playSpecificSong(int songNumber, Session session) {

        String speechText = "Viel Spaß mit dem Lied";

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("Kinderlieder");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        Stream stream = new Stream();
        String url = "empty";
        try {
            url = new AudioFileReference().getSpecificAudioFile(songNumber);
        } catch (SpeechletException e) {
            e.printStackTrace();
        }
        stream.setUrl(url);
        stream.setOffsetInMilliseconds(0);
        stream.setToken(url);

        LinkedList<String> songs = new AudioFileReference().getRandomAudioFileList();
        songs.removeFirstOccurrence(url);

        try {
            new DynamoDbService().updateSession(session.getUser().getUserId(), 0, url, url, songs);
        } catch (Exception e) {
            logger.warn(e.getMessage()  );
        }

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

    private SpeechletResponse playRandomSong(Session session) {

        String speechText = "Zufälliges Lied ausgewählt";

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("Kinderlieder");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        LinkedList<String> songs = new AudioFileReference().getRandomAudioFileList();

        Stream stream = new Stream();
        String url = songs.get(0);
        stream.setToken(url);
        stream.setUrl(url);
        stream.setOffsetInMilliseconds(0);

        songs.removeFirst();

        try {
            new DynamoDbService().updateSession(session.getUser().getUserId(), 0, url, url, songs);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

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

    private SpeechletResponse resumeRequest(Session session) {

        UserSession userSession = null;
        try {
            userSession = new DynamoDbService().getUserSession(session.getUser().getUserId());
        } catch (Exception e) {
            logger.error("Unable to obtain userSession Object.");
        }

        Stream stream = new Stream();
        stream.setUrl(userSession.getToken());
        stream.setOffsetInMilliseconds(userSession.getOffset());
        stream.setToken(userSession.getToken());

        logger.info("Stream ready" + stream.toString());

        AudioItem audioItem = new AudioItem();
        audioItem.setStream(stream);

        PlayDirective playDirective = new PlayDirective();
        playDirective.setAudioItem(audioItem);
        playDirective.setPlayBehavior(PlayBehavior.REPLACE_ALL);

        logger.info("Play directive set up");

        List<Directive> directives = new ArrayList<>();
        directives.add(playDirective);

        SpeechletResponse response = new SpeechletResponse();
        response.setDirectives(directives);
        response.setNullableShouldEndSession(true);
        return response;
    }

}
