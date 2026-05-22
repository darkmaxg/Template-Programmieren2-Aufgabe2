package org.htw.prog2.aufgabe1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;

public class SeqFile {
    HashSet<String> seqs = new HashSet<>();
    String firstSeq = "";
    boolean isValid = true;

    public SeqFile(String filename) {
        isValid = readFile(filename);
    }

    /**
     * Reads the specified FASTA file.
     * @param filename The path to the FASTA file
     * @return false if the file could not be parsed (wrong format, does not exist), true otherwise.
     */
    private boolean readFile(String filename) {
        File f = new File(filename);
        try {
            BufferedReader r = new BufferedReader(new FileReader(f));
            String currentLine;
            StringBuilder seq = new StringBuilder();
            currentLine = r.readLine();
            if(currentLine.charAt(0) != '>') {
                return false;
            }
            while((currentLine = r.readLine()) != null) {
                if(currentLine.charAt(0) == '>') {
                    if(addSequence(seq) == 0) {
                        return false;
                    }
                    seq = new StringBuilder();
                }
                else {
                    seq.append(currentLine.strip());
                }
            }
            if(addSequence(seq) == 0) {
                return false;
            }
            addSequence(seq);
        } catch(Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Adds the sequence in the passed StringBuilder to the internal list and also sets the first sequence if it
     * is still empty.
     * @param seq SequenceBuilder to get the sequence from.
     * @return The length of the added sequence.
     */
    private int addSequence(StringBuilder seq) {
        String seqString = seq.toString();
        seqs.add(seqString);
        if(firstSeq.isEmpty()) {
            firstSeq = seqString;
        }
        return seqString.length();
    }

    public int getNumberOfSequences() {
        if(isValid) {
            return seqs.size();
        }
        return 0;
    }

    public HashSet<String> getSequences() {
        if(isValid) {
            return seqs;
        }
        return new HashSet<>();
    }

    public String getFirstSequence() {
        if(isValid) {
            return firstSeq;
        }
        return "";
    }

    public boolean isValid() {
        return isValid;
    }
}
