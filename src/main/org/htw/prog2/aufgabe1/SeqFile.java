package org.htw.prog2.aufgabe1;

import org.htw.prog2.aufgabe1.exceptions.FileFormatException;

import java.io.*;
import java.util.HashSet;

public class SeqFile {
    HashSet<String> seqs = new HashSet<>();
    String firstSeq = "";

    public SeqFile(String filename) throws FileFormatException, IOException {
        readFile(filename);
    }

    /**
     * Reads the specified FASTA file.
     * @param filename The path to the FASTA file
     * @return false if the file could not be parsed (wrong format, does not exist), true otherwise.
     */
    private void readFile(String filename) throws FileFormatException, IOException {
        File f = new File(filename);
        BufferedReader r = new BufferedReader(new FileReader(f));
        String currentLine;
        StringBuilder seq = new StringBuilder();
        currentLine = r.readLine();
        if(currentLine.charAt(0) != '>') {
            throw new FileFormatException("FASTA File does not start with sequence header line.");
        }
        while((currentLine = r.readLine()) != null) {
            if(currentLine.charAt(0) == '>') {
                if(addSequence(seq) == 0) {
                    throw new FileFormatException("Two header lines are directly following each other.");
                }
                seq = new StringBuilder();
            }
            else {
                seq.append(currentLine.strip());
            }
        }
        if(addSequence(seq) == 0) {
            throw new FileFormatException("The last line is a sequence header.");
        }
        addSequence(seq);
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

    public int getNumberOfSequences() {return seqs.size();}

    public HashSet<String> getSequences() {return seqs;}

    public String getFirstSequence() {return firstSeq;}
}
