package de.vollweiter;

import com.amazon.speech.speechlet.SpeechletException;

import java.util.*;

public class AudioFileReference {

    private final String achDuLieberAugustin = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/AchDuLieberAugustin.mp3";
    private final String alleMeineEntchen = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/AlleMeineEntchen.mp3";
    private final String alleJahreWieder = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/AlleJahreWieder.mp3";
    private final String alleVoegelSindSchonDa = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/AlleVoegelSindSchonDa.mp3";
    private final String aufDerMauer = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/AufDerMauerAufDerLauer.mp3";
    private final String aufEinemBaum = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/AufEinemBaumEinKuckuckSass.mp3";
    private final String aufUnsrerWiese = "https://s3-eu-west-1.amazonaws.com/bucket-vollweiter/04_-_Kinder_wollen_singen_-_Auf_unsrer_Wiese_gehet_was.mp3";
    private final String backeBackeKuchen = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/BackeBackeKuchen.mp3";
    private final String buntSindSchonDieWälder = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/buntsindschondiewaelder.mp3";
    private final String bruderJakob = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/BruderJacob.mp3";
    private final String dasWandern = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/DasWandernIstDesMuellersLust.mp3";
    private final String dieAffen = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/DieAffenRasenDurchDenWald.mp3";
    private final String derKuckuckUndDerEsel = "https://s3-eu-west-1.amazonaws.com/bucket-vollweiter/08_-_Kinder_wollen_singen_-_Der_Kuckuck_und_der_Esel.mp3";
    private final String derMondIstAufgegangen = "https://s3-eu-west-1.amazonaws.com/bucket-vollweiter/10_-_Kinder_wollen_singen_-_Der_Mond_ist_aufgegangen.mp3";
    private final String dieGedankenSindFrei = "https://s3-eu-west-1.amazonaws.com/bucket-vollweiter/11_-_Kinder_wollen_singen_-_Die_Gedanken_sind_frei.mp3";
    private final String dieHandwerker = "https://s3-eu-west-1.amazonaws.com/bucket-vollweiter/12_-_Kinder_wollen_singen_-_Die_Handwerker.mp3";
    private final String dieVogelhochzeit = "https://s3-eu-west-1.amazonaws.com/bucket-vollweiter/13_-_Kinder_wollen_singen_-_Die_Vogelhochzeit.mp3";
    private final String dreiChinesenMitDemKontrabass = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/DreiChinesenMitDemKontraBass.mp3";
    private final String einJaeger = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/EinJaegerAusKurpfalz.mp3";
    private final String einMannDerSichKolumbusNannt = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/EinMannDerSichKolumbusNannt.mp3";
    private final String einMaennleinStehtImWalde = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/EinMaennleinStehtImWalde.mp3";
    private final String gruenGruenGruen = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/gruengruengruen.mp3";
    private final String gutenAbendGuteNacht = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/GutenAbendGutNacht.mp3";
    private final String haenschenKlein = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/haenschenklein.mp3";
    private final String haeschenInDerGrube = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/HaeschenInDerGrube.mp3";
    private final String tanteInMarokko = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/HabNeTanteInMarokko.mp3";
    private final String hejoSpannDenWagen = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/HejoSpannDenWagenAn.mp3";
    private final String hoppeHoppeReiter = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/HoppeHoppeReiter.mp3";
    private final String hoppHoppHopp = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/HoppHoppHopp.mp3";
    private final String horchWasKommt = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/HorchWasKommtVonDraussenRein.mp3";
    private final String ichGehMitMeinerLaterne = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/IchGehMitMeinerLaterne.mp3";
    private final String ihrKinderlein = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/IhrKinderleinKommet.mp3";
    private final String kommtEinVogelGeflogen = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/KommtEinVogelGeflogen.mp3";
    private final String kuckKuck = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/KuckuckKuckuckRuftsAusDemWald.mp3";
    private final String lasstUnsFrohUndMunterSein = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/LasstUnsFrohUndMunterSein.mp3";
    private final String leiseRieseltDerSchnee = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/LeiseRieseltDerSchnee.mp3";
    private final String morgenKinderWirdsWasGeben = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/MorgenKinderWirdsWasGeben.mp3";
    private final String morgenKommtDerWeihnachtsmann = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/MorgenKommtDerWeihnachtsMann.mp3";
    private final String nunWillDerLenz = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/NunWillDerLenzUnsGruessen.mp3";
    private final String ringelRingelReihe = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/RingelRingelReihe.mp3";
    private final String sanktMartin = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/SanktMartin.mp3";
    private final String schlafKindchenSchlaf = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/SchlafKindchenSchlaf.mp3";
    private final String schneefloeckchen = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/SchneefloeckchenWeissRoeckchen.mp3";
    private final String summSummSumm = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/SummSummSumm.mp3";
    private final String wennIchEinVoegleinWaer = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/WennIchEinVoegleinWaer.mp3";
    private final String werWillFleißigeHandwerkerSehn = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/WerWillFleissigeHandwerkerSehn.mp3";
    private final String zeigtHerEureFuesse = "http://www.kitalieder.de/fileadmin/kitalieder/mp3/ZeigtHerEureFuesse.mp3";


    private final String achDuLieberAugustinTitel = "Ach du lieber Augustin";
    private final String alleMeineEntchenTitel = "Alle meine Entchen";
    private final String alleJahreWiederTitel = "Alle Jahre wieder";
    private final String alleVoegelSindSchonDaTitel = "Alle Voegel sind schon da";
    private final String aufDerMauerTitel = "Auf der Mauer auf der Lauer";
    private final String aufEinemBaumTitel = "Auf einem Baum ein Kuckuck sass";
    private final String aufUnsrerWieseTitel = "Auf unserer Wiese";
    private final String backeBackeKuchenTitel = "Backe backe Kuchen";
    private final String buntSindSchonDieWälderTitel = "Bunt sind schon die Waelder";
    private final String bruderJakobTitel = "Bruder Jakob";
    private final String dasWandernTitel = "Das Wandern ist des Muellers Lust";
    private final String dieAffenTitel = "Die Affen rasen durch den Wald";
    private final String derKuckuckUndDerEselTitel = "Der Kuckuck und der Esel";
    private final String derMondIstAufgegangenTitel = "Der Mond ist aufgegangen";
    private final String dieGedankenSindFreiTitel = "Die Gedanken sind frei";
    private final String dieHandwerkerTitel = "Die Handwerker";
    private final String dieVogelhochzeitTitel = "Die Vogelhochzeit";
    private final String dreiChinesenMitDemKontrabassTitel = "Drei Chinesen mit dem Kontrabass";
    private final String einJaegerTitel = "Ein Jaeger aus Kurpfalz";
    private final String einMannDerSichKolumbusNanntTitel = "Ein Mann der sich Kolumbus nannt";
    private final String einMaennleinStehtImWaldeTitel = "Ein maennlein steht im Walde";
    private final String gruenGruenGruenTitel = "Gruen gruen gruen sind alle meine Kleider";
    private final String gutenAbendGuteNachtTitel = "Guten Abend gute Nacht";
    private final String haenschenKleinTitel = "Haenschen Klein";
    private final String haeschenInDerGrubeTitel = "Haeschen in der Grube";
    private final String tanteInMarokkoTitel = "Hab ne Tante in Marokko";
    private final String hejoSpannDenWagenTitel = "Hejo spann den Wagen an";
    private final String hoppeHoppeReiterTitel = "Hoppe hoppe Reiter";
    private final String hoppHoppHoppTitel = "Hopp hopp hopp";
    private final String horchWasKommtTitel = "Horch was kommt von draussen rein";
    private final String ichGehMitMeinerLaterneTitel = "Ich geh mit meiner Laterne";
    private final String ihrKinderleinTitel = "Ihr Kinderlein kommet";
    private final String kommtEinVogelGeflogenTitel = "Kommt ein Vogel geflogen";
    private final String kuckKuckTitel = "Kuckuck Kuckuck rufts aus dem Wald";
    private final String lasstUnsFrohUndMunterSeinTitel = "Lasst uns froh und munter sein";
    private final String leiseRieseltDerSchneeTitel = "Leise rieselt der Schnee";
    private final String morgenKinderWirdsWasGebenTitel = "Morgen Kinder wirds was geben";
    private final String morgenKommtDerWeihnachtsmannTitel = "Morgen kommt der Weihnachtsmann";
    private final String nunWillDerLenzTitel = "Nun will der Lenz";
    private final String ringelRingelReiheTitel = "Ringel Ringel Reihe";
    private final String sanktMartinTitel = "Sankt Martin";
    private final String schlafKindchenSchlafTitel = "Schlaf Kindlein Schlaf";
    private final String schneefloeckchenTitel = "Schneefloeckchen Weissroeckchen";
    private final String summSummSummTitel = "Summ Summ Summ";
    private final String wennIchEinVoegleinWaerTitel = "Wenn ich ein Voeglein waer";
    private final String werWillFleißigeHandwerkerSehnTitel = "Wer will fleissige Handwerker sehn";
    private final String zeigtHerEureFuesseTitel = "Zeigt her eure Fuesse";


    public LinkedList<String> getAllAudioFiles() {
        LinkedList<String> audioFilesList = new LinkedList<>();

        audioFilesList.add(achDuLieberAugustin);
        audioFilesList.add(alleMeineEntchen);
        audioFilesList.add(alleJahreWieder);
        audioFilesList.add(alleVoegelSindSchonDa);
        audioFilesList.add(aufDerMauer);
        audioFilesList.add(aufEinemBaum);
        audioFilesList.add(aufUnsrerWiese);
        audioFilesList.add(backeBackeKuchen);
        audioFilesList.add(buntSindSchonDieWälder);
        audioFilesList.add(bruderJakob);
        audioFilesList.add(dasWandern);
        audioFilesList.add(dieAffen);
        audioFilesList.add(derKuckuckUndDerEsel);
        audioFilesList.add(derMondIstAufgegangen);
        audioFilesList.add(dieGedankenSindFrei);
        audioFilesList.add(dieHandwerker);
        audioFilesList.add(dieVogelhochzeit);
        audioFilesList.add(dreiChinesenMitDemKontrabass);
        audioFilesList.add(einJaeger);
        audioFilesList.add(einMannDerSichKolumbusNannt);
        audioFilesList.add(einMaennleinStehtImWalde);
        audioFilesList.add(gruenGruenGruen);
        audioFilesList.add(gutenAbendGuteNacht);
        audioFilesList.add(haenschenKlein);
        audioFilesList.add(haeschenInDerGrube);
        audioFilesList.add(tanteInMarokko);
        audioFilesList.add(hejoSpannDenWagen);
        audioFilesList.add(hoppeHoppeReiter);
        audioFilesList.add(hoppHoppHopp);
        audioFilesList.add(horchWasKommt);
        audioFilesList.add(ichGehMitMeinerLaterne);
        audioFilesList.add(ihrKinderlein);
        audioFilesList.add(kommtEinVogelGeflogen);
        audioFilesList.add(kuckKuck);
        audioFilesList.add(lasstUnsFrohUndMunterSein);
        audioFilesList.add(leiseRieseltDerSchnee);
        audioFilesList.add(morgenKinderWirdsWasGeben);
        audioFilesList.add(morgenKommtDerWeihnachtsmann);
        audioFilesList.add(nunWillDerLenz);
        audioFilesList.add(ringelRingelReihe);
        audioFilesList.add(sanktMartin);
        audioFilesList.add(schlafKindchenSchlaf);
        audioFilesList.add(schneefloeckchen);
        audioFilesList.add(summSummSumm);
        audioFilesList.add(wennIchEinVoegleinWaer);
        audioFilesList.add(werWillFleißigeHandwerkerSehn);
        audioFilesList.add(zeigtHerEureFuesse);

        return audioFilesList;
    }

    public LinkedList<String> getRandomAudioFileList() {
        LinkedList<String> audioFiles;
        audioFiles = getAllAudioFiles();
        Collections.shuffle(audioFiles);
        return audioFiles;
    }

    public String getSpecificAudioFile(int songNumber) throws SpeechletException {

        switch (songNumber) {
            case 0:
                return achDuLieberAugustin;
            case 1:
                return alleMeineEntchen;
            case 2:
                return alleJahreWieder;
            case 3:
                return alleVoegelSindSchonDa;
            case 4:
                return aufDerMauer;
            case 5:
                return aufEinemBaum;
            case 6:
                return aufUnsrerWiese;
            case 7:
                return backeBackeKuchen;
            case 8:
                return buntSindSchonDieWälder;
            case 9:
                return bruderJakob;
            case 10:
                return dasWandern;
            case 11:
                return dieAffen;
            case 12:
                return derKuckuckUndDerEsel;
            case 13:
                return derMondIstAufgegangen;
            case 14:
                return dieGedankenSindFrei;
            case 15:
                return dieHandwerker;
            case 16:
                return dieVogelhochzeit;
            case 17:
                return dreiChinesenMitDemKontrabass;
            case 18:
                return einJaeger;
            case 19:
                return einMannDerSichKolumbusNannt;
            case 20:
                return einMaennleinStehtImWalde;
            case 21:
                return gruenGruenGruen;
            case 22:
                return gutenAbendGuteNacht;
            case 23:
                return haenschenKlein;
            case 24:
                return haeschenInDerGrube;
            case 25:
                return tanteInMarokko;
            case 26:
                return hejoSpannDenWagen;
            case 27:
                return hoppeHoppeReiter;
            case 28:
                return hoppHoppHopp;
            case 29:
                return horchWasKommt;
            case 30:
                return ichGehMitMeinerLaterne;
            case 31:
                return ihrKinderlein;
            case 32:
                return kommtEinVogelGeflogen;
            case 33:
                return kuckKuck;
            case 34:
                return lasstUnsFrohUndMunterSein;
            case 35:
                return leiseRieseltDerSchnee;
            case 36:
                return morgenKinderWirdsWasGeben;
            case 37:
                return morgenKommtDerWeihnachtsmann;
            case 38:
                return nunWillDerLenz;
            case 39:
                return ringelRingelReihe;
            case 40:
                return sanktMartin;
            case 41:
                return schlafKindchenSchlaf;
            case 42:
                return schneefloeckchen;
            case 43:
                return summSummSumm;
            case 44:
                return wennIchEinVoegleinWaer;
            case 45:
                return werWillFleißigeHandwerkerSehn;
            case 46:
                return zeigtHerEureFuesse;
            default:
                throw new SpeechletException("Dieses Lied kenne ich nicht");
        }
    }

    public String getNameOfSong(int songNumber) {
        switch (songNumber) {
            case 0:
                return achDuLieberAugustinTitel;
            case 1:
                return alleMeineEntchenTitel;
            case 2:
                return alleJahreWiederTitel;
            case 3:
                return alleVoegelSindSchonDaTitel;
            case 4:
                return aufDerMauerTitel;
            case 5:
                return aufEinemBaumTitel;
            case 6:
                return aufUnsrerWieseTitel;
            case 7:
                return backeBackeKuchenTitel;
            case 8:
                return buntSindSchonDieWälderTitel;
            case 9:
                return bruderJakobTitel;
            case 10:
                return dasWandernTitel;
            case 11:
                return dieAffenTitel;
            case 12:
                return derKuckuckUndDerEselTitel;
            case 13:
                return derMondIstAufgegangenTitel;
            case 14:
                return dieGedankenSindFreiTitel;
            case 15:
                return dieHandwerkerTitel;
            case 16:
                return dieVogelhochzeitTitel;
            case 17:
                return dreiChinesenMitDemKontrabassTitel;
            case 18:
                return einJaegerTitel;
            case 19:
                return einMannDerSichKolumbusNanntTitel;
            case 20:
                return einMaennleinStehtImWaldeTitel;
            case 21:
                return gruenGruenGruenTitel;
            case 22:
                return gutenAbendGuteNachtTitel;
            case 23:
                return haenschenKleinTitel;
            case 24:
                return haeschenInDerGrubeTitel;
            case 25:
                return tanteInMarokkoTitel;
            case 26:
                return hejoSpannDenWagenTitel;
            case 27:
                return hoppeHoppeReiterTitel;
            case 28:
                return hoppHoppHoppTitel;
            case 29:
                return horchWasKommtTitel;
            case 30:
                return ichGehMitMeinerLaterneTitel;
            case 31:
                return ihrKinderleinTitel;
            case 32:
                return kommtEinVogelGeflogenTitel;
            case 33:
                return kuckKuckTitel;
            case 34:
                return lasstUnsFrohUndMunterSeinTitel;
            case 35:
                return leiseRieseltDerSchneeTitel;
            case 36:
                return morgenKinderWirdsWasGebenTitel;
            case 37:
                return morgenKommtDerWeihnachtsmannTitel;
            case 38:
                return nunWillDerLenzTitel;
            case 39:
                return ringelRingelReiheTitel;
            case 40:
                return sanktMartinTitel;
            case 41:
                return schlafKindchenSchlafTitel;
            case 42:
                return schneefloeckchenTitel;
            case 43:
                return summSummSummTitel;
            case 44:
                return wennIchEinVoegleinWaerTitel;
            case 45:
                return werWillFleißigeHandwerkerSehnTitel;
            case 46:
                return zeigtHerEureFuesseTitel;
            default:
                return "Kein Titel";
        }
    }
}
