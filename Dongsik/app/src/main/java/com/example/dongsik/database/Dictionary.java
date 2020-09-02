package com.example.dongsik.database;

public class Dictionary {

    private int num;
    private String chrono_time;
    private String species;

    public Dictionary (int num, String species,String chrono_time){
        this.num = num;
        this.species = species;
        this.chrono_time = chrono_time;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getChrono_time() {
        return chrono_time;
    }

    public void setChrono_time(String chrono_time) {
        this.chrono_time = chrono_time;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}
