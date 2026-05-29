package org.htw.prog2.aufgabe1.exceptions;

import org.htw.prog2.aufgabe1.SeqFile;

import java.io.FileNotFoundException;

public class FileFormatException extends Exception {
    public FileFormatException(String message) {
        super(message);
    }
}