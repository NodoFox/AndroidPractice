package com.nodovitt.criminalintent.model;

import java.util.UUID;

public class Crime {

    private UUID ID;
    private String title;
    
    public Crime(){
        //generate unique ID;
        this.ID = UUID.randomUUID();
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public UUID getID() {
        return ID;
    } 
}
