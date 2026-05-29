package org.htw.prog2.aufgabe1;

import org.htw.prog2.aufgabe1.exceptions.FileFormatException;

import java.io.*;
import java.util.*;

public class MutationPatterns {
    /**
     * Contructor für MutationPatterns. Liest die CSV-Datei infile ein.
     * @param infile Pfad zu CSV-Datei zum Einlesen
     * @throws IOException bei allgemeinen IO-Fehlern
     * @throws FileNotFoundException falls die Datei nicht gefunden wurde
     * @throws FileFormatException falls das Format der Definitionszeile inkorrekt ist oder die Anzahl der Spalten
     * nicht in jeder Zeile gleich ist
     */
    ArrayList<String> muts = new ArrayList<>();
    public MutationPatterns(String infile) throws IOException, FileNotFoundException, FileFormatException {
        File f = new File(infile);
        BufferedReader r = new BufferedReader(new FileReader (f));
        int elementNumber = 0;
        while(r.ready()){
            String currentLine = r.readLine();
            if(currentLine.charAt(0) == '\"'){
                elementNumber = parseDrugs(currentLine).size() + 2;
            }
            else if(currentLine.charAt(0) != '#'){
                String[] lineArray = currentLine.split(";");
                if(lineArray.length != elementNumber){
                    throw new FileFormatException(currentLine + " hat eine falsche Anzahl an Elementen");
                }
                muts.add(currentLine);
            }
        }
    }

    /**
     * Gibt die Anzahl der eingelesenen Mutationspattern zurück.
     * @return Anzahl der eingelesenen Mutationspattern
     */
    public int getNumberOfMutations() {
        return muts.size();
    }

    /**
     * Parst die Definitionszeile.
     * @param line Definitionszeile aus der CSV-Datei
     * @return Liste der Medikamentennamen aus der Definitionszeile
     */
    public static List<String> parseDrugs(String line) throws FileFormatException {
        String[] lineArray = line.split(";");
        if(!lineArray[0].equals("\"Mutation Patterns\"")){
            throw new FileFormatException("Das erste Element lautet nicht \"Mutation Patterns\"");
        }
        if(!lineArray[1].equals("\"Number of Sequences\"")){
            throw new FileFormatException("Das erste Element lautet nicht \"Number of Sequences\"");
        }
        List<String> l = new ArrayList<>();
        for(int i = 2; i < lineArray.length; i++){
            if(!lineArray[i].endsWith(" foldn\"")){
                throw new FileFormatException("Das Medikament endet nicht mit foldn\"");
            }
            String medicament = lineArray[i].replace(" foldn", "").replace("\"", "");
            l.add(medicament);
        }
        return l;
    }
}
