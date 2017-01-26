package com.example.fabi.haushaltsbuch;

import java.util.Currency;
import java.util.Date;

/**
 * Created by Fabian on 21.01.2017.
 */

class Value {
    private int id;
    private Date datum;
    private String beschreibung;
    private Float betrag;
    private String kategorie;

    Value(){}

    Value(int id, Date datum, String beschreibung, Float betrag, String kategorie){
        this.id = id;
        this.datum = datum;
        this.beschreibung = beschreibung;
        this.betrag = betrag;
        this.kategorie = kategorie;
    }

    Date getDatum() {
        return datum;
    }

    void setDatum(Date datum) {
        this.datum = datum;
    }

    String getBeschreibung() {
        return beschreibung;
    }

    void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    Float getBetrag() {
        return betrag;
    }

    void setBetrag(Float betrag) {
        this.betrag = betrag;
    }

    String getKategorie() {
        return kategorie;
    }

    void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }
}
