package de.vollweiter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AudioFileReference {

    private static String alleMeineEntchen = "https://s3-eu-west-1.amazonaws.com/bucket-vollweiter/01_-_Kinder_wollen_singen_-_Alle_meine_Entchen.mp3";
    private static String alleVögelSindSchonDa = "https://s3-eu-west-1.amazonaws.com/bucket-vollweiter/02_-_Kinder_wollen_singen_-_Alle_Voegel_sind_schon_da.mp3";
    private static String aufDerMauer = "https://s3-eu-west-1.amazonaws.com/bucket-vollweiter/03_-_Kinder_wollen_singen_-_Auf_der_Mauer_auf_der_Lauer.mp3";
    private static String backeBackeKuchen = "https://s3-eu-west-1.amazonaws.com/bucket-vollweiter/05_-_Kinder_wollen_singen_-_Backe_backe_Kuchen.mp3";
    private static String dieAffen = "https://s3-eu-west-1.amazonaws.com/bucket-vollweiter/09_-_Kinder_wollen_singen_-_Die_Affen_rasen_durch_den_Wald.mp3";

    private List<String> getAllAudioFiles() {
        List<String> audioFilesList = new ArrayList<>();
        audioFilesList.add(alleMeineEntchen);
        audioFilesList.add(alleVögelSindSchonDa);
        audioFilesList.add(aufDerMauer);
        audioFilesList.add(backeBackeKuchen);
        audioFilesList.add(dieAffen);

        return audioFilesList;
    }

    private int getRandomInt(int min, int max) {
        Random random = new Random();
        int randomInt = random.nextInt((max - min) + 1) + min;

        return randomInt;
    }

    public String getRandomAudioFile() {
        return getAllAudioFiles().get(getRandomInt(0, getAllAudioFiles().size()));
    }
}
