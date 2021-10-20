/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author student
 */
public class Helper {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // TODO code application logic here
    }

    private final String path;
    private final Map<String, Integer> chosenLanguage;

    public Map<String, Integer> getChosenLanguage() {
        return chosenLanguage;
    }

    public Helper(String path) {
        this.path = path;
        chosenLanguage = new HashMap<>();
    }

    public void writeToFile() {

        try (FileWriter fw = new FileWriter(path)) {
            for (String i:chosenLanguage.keySet()) {
                fw.write(i + ":" + chosenLanguage.get(i) + "\n");
            }
        }
        catch (IOException e) {
            System.out.println("Nie znaleziono pliku!");
            }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void readFromFile(){

        String line;
        File file = new File(path);
        FileInputStream fis;
        BufferedReader br;

        try {
            fis = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(fis));
            while ((line = br.readLine()) != null) {
                String[] elem = line.split(":");
                chosenLanguage.put(elem[0], Integer.parseInt(elem[1]));
                }
            br.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku!");
        } 
        catch (IOException e) {
            System.out.println("Błąd odczytu pliku!");
        }
    }

    public void appendNewValues (ArrayList<String> values){
        for (String language: values) {
            int count = chosenLanguage.containsKey(language) ? chosenLanguage.get(language) : 0;
            chosenLanguage.put(language, count + 1);
        }
    }
    
}
