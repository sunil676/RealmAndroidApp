package com.sunil.realmandroidapp;

import io.realm.RealmObject;

/**
 * Created by sunil on 12/14/16.
 */

public class Note extends RealmObject {

    private long id;
    private String tittle;
    private String comment;
    private String noteDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }
}
