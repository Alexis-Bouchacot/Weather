package com.example.meteo;

import java.util.ArrayList;

public class Ville {
    private String nom;
    private ArrayList<String> temp;
    private String description;
    private String temp_min_max;
    private ArrayList<String> heure;
    private ArrayList<String> temp_jour;
    private ArrayList<String> jour;
    private ArrayList<String> logo;
    private double direction;
    private String vitesse;
    private int humidity;

    public Ville(){
    }

    public Ville(String nom, ArrayList<String> temp, String description) {
        this.nom = nom;
        this.temp = temp;
        this.description = description;
    }

    public Ville(String nom, ArrayList<String> temp, ArrayList<String> logo) {
        this.nom = nom;
        this.temp = temp;
        this.logo = logo;
    }

    public Ville(String nom, ArrayList<String> temp, String description, String temp_min_max, ArrayList<String> heure, ArrayList<String> temp_jour, ArrayList<String> jour,ArrayList<String> logo, double direction, String vitesse, int humidity) {
        this.nom = nom;
        this.temp = temp;
        this.description = description;
        this.temp_min_max = temp_min_max;
        this.heure = heure;
        this.temp_jour = temp_jour;
        this.jour = jour;
        this.logo = logo;
        this.direction = direction;
        this.vitesse = vitesse;
        this.humidity = humidity;
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<String> getTemp() {
        return temp;
    }

    public String getDescription() {
        return description;
    }

    public String getTemp_min_max() {
        return temp_min_max;
    }

    public ArrayList<String> getHeure(){
        return heure;
    }

    public ArrayList<String> getTemp_jour(){
        return temp_jour;
    }

    public ArrayList<String> getJour(){
        return jour;
    }

    public ArrayList<String> getLogo(){
        return logo;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTemp(ArrayList<String> temp) {
        this.temp = temp;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTemp_min_max(String temp_min_max) {
        this.temp_min_max = temp_min_max;
    }

    public void setHeure(ArrayList<String> heure){
        this.heure = heure;
    }

    public void setTemp_jour(ArrayList<String> temp_jour){
        this.temp_jour = temp_jour;
    }

    public void setJour(ArrayList<String> jour){
        this.jour = jour;
    }

    public void setLogo(ArrayList<String> logo){
        this.logo = logo;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getVitesse() {
        return vitesse;
    }

    public void setVitesse(String vitesse) {
        this.vitesse = vitesse;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
