package de.vollweiter;

import com.amazon.speech.speechlet.SpeechletException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AudioFileReference {

    private final String alleMeineEntchen = "https://s3-eu-west-1.amazonaws.com/bucket-vollweiter/01_-_Kinder_wollen_singen_-_Alle_meine_Entchen.mp3";
    private final String alleVoegelSindSchonDa = "https://s3-eu-west-1.amazonaws.com/bucket-vollweiter/02_-_Kinder_wollen_singen_-_Alle_Voegel_sind_schon_da.mp3";
    private final String aufDerMauer = "https://s3-eu-west-1.amazonaws.com/bucket-vollweiter/03_-_Kinder_wollen_singen_-_Auf_der_Mauer_auf_der_Lauer.mp3";
    private final String aufUnsrerWiese = "https://s3-eu-west-1.amazonaws.com/bucket-vollweiter/04_-_Kinder_wollen_singen_-_Auf_unsrer_Wiese_gehet_was.mp3";
    private final String backeBackeKuchen = "https://s3-eu-west-1.amazonaws.com/bucket-vollweiter/05_-_Kinder_wollen_singen_-_Backe_backe_Kuchen.mp3";
    private final String bruderJakob = "https://s3-eu-west-1.amazonaws.com/bucket-vollweiter/06_-_Kinder_wollen_singen_-_Bruder_Jakob.mp3";
    private final String dasWandern = "https://s3-eu-west-1.amazonaws.com/bucket-vollweiter/07_-_Kinder_wollen_singen_-_Das_Wandern_ist_des_Muellers_Lust.mp3";
    private final String derKuckuckUndDerEsel = "https://s3-eu-west-1.amazonaws.com/bucket-vollweiter/08_-_Kinder_wollen_singen_-_Der_Kuckuck_und_der_Esel.mp3";
    private final String dieAffen = "https://s3-eu-west-1.amazonaws.com/bucket-vollweiter/09_-_Kinder_wollen_singen_-_Die_Affen_rasen_durch_den_Wald.mp3";
    private final String derMondIstAufgegangen = "https://s3-eu-west-1.amazonaws.com/bucket-vollweiter/10_-_Kinder_wollen_singen_-_Der_Mond_ist_aufgegangen.mp3";
    private final String dieGedankenSindFrei = "https://s3-eu-west-1.amazonaws.com/bucket-vollweiter/11_-_Kinder_wollen_singen_-_Die_Gedanken_sind_frei.mp3";
    private final String dieHandwerker = "https://s3-eu-west-1.amazonaws.com/bucket-vollweiter/12_-_Kinder_wollen_singen_-_Die_Handwerker.mp3";
    private final String dieVogelhochzeit = "https://s3-eu-west-1.amazonaws.com/bucket-vollweiter/13_-_Kinder_wollen_singen_-_Die_Vogelhochzeit.mp3";
    private final String dreiChinesenMitDemKontrabass = "https://s3-eu-west-1.amazonaws.com/bucket-vollweiter/14_-_Kinder_wollen_singen_-_3_Chinesen_mit_dem_Kontrabass.mp3";
    private final String einMaennleinStehtImWalde = "https://s3-eu-west-1.amazonaws.com/bucket-vollweiter/15_-_Kinder_wollen_singen_-_Ein_Maennlein_steht_im_Walde.mp3";

    private final String alleMeineEntchenTitle = "Alle meine Entchen";
    private final String alleVoegelSindSchonDaTitle = "Alle Vögel sind schon da";
    private final String aufDerMauerTitle = "Auf der Mauer auf der Lauer";
    private final String aufUnsrerWieseTitle = "Auf unserer Wiese";
    private final String backeBackeKuchenTitle = "Backe backe Kuchen";
    private final String bruderJakobTitle = "Bruder Jakob";
    private final String dasWandernTitle = "Das Wandern ist des Müllers Lust";
    private final String derKuckuckUndDerEselTitle = "Der Kuckuck und der Esel";
    private final String dieAffenTitle = "Die Affen rasen durch den Wald";
    private final String derMondIstAufgegangenTitle = "Der Mond ist aufgegangen";
    private final String dieGedankenSindFreiTitle = "Die Gedanken sind frei";
    private final String dieHandwerkerTitle = "Die Handwerker";
    private final String dieVogelhochzeitTitle = "Die Vogelhochzeit";
    private final String dreiChinesenMitDemKontrabassTitle = "Drei Chinesen mit dem Kontrabass";
    private final String einMaennleinStehtImWaldeTitle = "Ein Männlein steht im Walde";


    public List<String> getAllAudioFiles() {
        List<String> audioFilesList = new ArrayList<>();

        audioFilesList.add(alleMeineEntchen);
        audioFilesList.add(alleVoegelSindSchonDa);
        audioFilesList.add(aufDerMauer);
        audioFilesList.add(aufUnsrerWiese);
        audioFilesList.add(backeBackeKuchen);
        audioFilesList.add(bruderJakob);
        audioFilesList.add(dasWandern);
        audioFilesList.add(derKuckuckUndDerEsel);
        audioFilesList.add(dieAffen);
        audioFilesList.add(derMondIstAufgegangen);
        audioFilesList.add(dieGedankenSindFrei);
        audioFilesList.add(dieHandwerker);
        audioFilesList.add(dieVogelhochzeit);
        audioFilesList.add(dreiChinesenMitDemKontrabass);
        audioFilesList.add(einMaennleinStehtImWalde);

        return audioFilesList;
    }

    private int getRandomInt(int min, int max) {
        Random random = new Random();
        int randomInt = random.nextInt((max - min) + 1) + min;

        return randomInt;
    }

    public String getRandomAudioFile() {
        return getAllAudioFiles().get(getRandomInt(0, (getAllAudioFiles().size()-1)));
    }

    public String getSpecificAudioFile(int songNumber) throws SpeechletException {

        switch (songNumber) {
            case 0:
                return alleMeineEntchen;
            case 1:
                return alleVoegelSindSchonDa;
            case 2:
                return aufDerMauer;
            case 3:
                return aufUnsrerWiese;
            case 4:
                return backeBackeKuchen;
            case 5:
                return bruderJakob;
            case 6:
                return dasWandern;
            case 7:
                return derKuckuckUndDerEsel;
            case 8:
                return dieAffen;
            case 9:
                return derMondIstAufgegangen;
            case 10:
                return dieGedankenSindFrei;
            case 11:
                return dieHandwerker;
            case 12:
                return dieVogelhochzeit;
            case 13:
                return dreiChinesenMitDemKontrabass;
            case 14:
                return einMaennleinStehtImWalde;
            default:
                throw new SpeechletException("Dieses Lied kenne ich nicht");

        }
    }

    public String getNameOfSong(int songNumber) {
        switch (songNumber) {
            case 0:
                return alleMeineEntchenTitle;
            case 1:
                return alleVoegelSindSchonDaTitle;
            case 2:
                return aufDerMauerTitle;
            case 3:
                return aufUnsrerWieseTitle;
            case 4:
                return backeBackeKuchenTitle;
            case 5:
                return bruderJakobTitle;
            case 6:
                return dasWandernTitle;
            case 7:
                return derKuckuckUndDerEselTitle;
            case 8:
                return dieAffenTitle;
            case 9:
                return derMondIstAufgegangenTitle;
            case 10:
                return dieGedankenSindFreiTitle;
            case 11:
                return dieHandwerkerTitle;
            case 12:
                return dieVogelhochzeitTitle;
            case 13:
                return dreiChinesenMitDemKontrabassTitle;
            case 14:
                return einMaennleinStehtImWaldeTitle;
            default:
                return "Kein Titel";
        }
    }
}
