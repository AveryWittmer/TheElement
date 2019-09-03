package com.example.elementtwoplayergame;

import android.content.res.AssetManager;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Random;

public class FileRelated {

static String ChooseDeck(String deckSelection){
    String filePath = "Some Random Path, this can't be null apparently.";
    if (deckSelection.equals("earth")) {
        filePath = "EarthStarter";
    }
    if (deckSelection.equals("water")) {
        filePath = "WaterStarter";
    }
    if (deckSelection.equals("fire")) {
        filePath = "FireStarter";
    }
    if (deckSelection.equals("metal")) {
        filePath = "MetalStarter";
    }
    if (deckSelection.equals("wood")) {
        filePath = "WoodStarter";
    }
    return filePath;
}
    static String ChooseElements(String deckSelection){
        String filePath = "Some Random Path, this can't be null apparently.";
        if (deckSelection.equals("earth")) {
            filePath = "EarthElements";
        }
        if (deckSelection.equals("water")) {
            filePath = "WaterElements";
        }
        if (deckSelection.equals("fire")) {
            filePath = "FireElements";
        }
        if (deckSelection.equals("metal")) {
            filePath = "MetalElements";
        }
        if (deckSelection.equals("wood")) {
            filePath = "WoodElements";
        }
        return filePath;
    }
    static String ChooseExcelFile(String excelSelection){
        String filePath = "Some Random Path, this can't be null apparently.";
        if (excelSelection.equals("earth")) {
            filePath = "EarthSheet.csv";
        }
        if (excelSelection.equals("water")) {
            filePath = "WaterSheet.csv";
        }
        if (excelSelection.equals("fire")) {
            filePath = "FireSheet.csv";
        }
        if (excelSelection.equals("metal")) {
            filePath = "MetalSheet.csv";
        }
        if (excelSelection.equals("wood")) {
            filePath = "WoodSheet.csv";
        }
        return filePath;
    }
    static String RandomDeck(){
        String[] decks = {"fire", "water", "earth", "wood", "metal"};
        int randomIndex = new Random().nextInt(decks.length);
        return decks[randomIndex];
    }
}
