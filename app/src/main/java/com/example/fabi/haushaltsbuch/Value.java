package com.example.fabi.haushaltsbuch;

import java.util.Currency;
import java.util.Date;

/**
 * Created by Fabian on 21.01.2017.
 */

public class Value {
    private int id;
    private Date datum;
    private String beschreibung;
    private Currency betrag;
    private String kategorie;

    public Value(){

    }

    public Value(int id, Date datum, String beschreibung, Currency betrag, String kategorie){
        this.id = id;
        this.datum = datum;
        this.beschreibung = beschreibung;
        this.betrag = betrag;
        this.kategorie = kategorie;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Currency getBetrag() {
        return betrag;
    }

    public void setBetrag(Currency betrag) {
        this.betrag = betrag;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }
}
