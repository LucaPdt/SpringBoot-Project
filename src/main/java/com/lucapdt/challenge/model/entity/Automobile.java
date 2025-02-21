package com.lucapdt.challenge.model.entity;

import jakarta.persistence.*;

import java.time.Year;


@Entity
@Table(name = "automobile")
public class Automobile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modello")
    private String modello;

    @Column(name = "motorizzazione")
    private String motorizzazione;

    @Column(name = "anno")
    private Year anno;

    @Column(name = "prezzo")
    private double prezzo;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato")
    private StatoAuto stato;

    public Automobile() {
    }

    public Automobile(String marca, String modello, String motorizzazione, Year anno, double prezzo, StatoAuto stato) {
        this.marca = marca;
        this.modello = modello;
        this.motorizzazione = motorizzazione;
        this.anno = anno;
        this.prezzo = prezzo;
        this.stato = stato;
    }

    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public String getMotorizzazione() {
        return motorizzazione;
    }

    public void setMotorizzazione(String motorizzazione) {
        this.motorizzazione = motorizzazione;
    }

    public Year getAnno() {
        return anno;
    }

    public void setAnno(Year anno) {
        this.anno = anno;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public StatoAuto getStato() {
        return stato;
    }

    public void setStato(StatoAuto stato) {
        this.stato = stato;
    }

    public enum StatoAuto {
        disponibile,venduta
    }
}
