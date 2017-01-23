package com.example.fabi.haushaltsbuch;

/**
 * Created by Fabian on 23.01.2017.
 */

public class ListHeaderValue {
    private String Jahr;
    private String Monat;
    private int anzahl;
    private float betrag;

    ListHeaderValue(String Jahr, String Monat, int anzahl, float betrag){
        this.Jahr = Jahr;
        this.Monat = Monat;
        this.anzahl = anzahl;
        this.betrag = betrag;
    }
}
