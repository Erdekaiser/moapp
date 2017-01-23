package com.example.fabi.haushaltsbuch;

import java.util.Currency;
import java.util.Date;

/**
 * Created by Fabian on 21.01.2017.
 */

public class Value {
    private int id;
    private Date datum;
    private int monat;
    private String beschreibung;
    private Float betrag;
    private String kategorie;

    public Value(){

    }

    public Value(int id, Date datum, String beschreibung, Float betrag, String kategorie){
        this(id, datum, 0, beschreibung, betrag, kategorie);
    }

    public Value(int id, Date datum, int monat, String beschreibung, Float betrag, String kategorie){
        this.id = id;
        this.datum = datum;
        this.monat = monat;
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

    public Float getBetrag() {
        return betrag;
    }

    public void setBetrag(Float betrag) {
        this.betrag = betrag;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }


    public int getMonat() {
        return monat;
    }

    public void setMonat(int monat) {
        this.monat = monat;
    }
}
