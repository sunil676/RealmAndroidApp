package com.sunil.realmandroidapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by sunil on 12/14/16.
 */

public class NoteManager {

    public static void addNote(Realm realm, Note noteData){
        realm.beginTransaction();
        Note note = realm.createObject(Note.class);
        note.setId(getCount(realm)+1);
        note.setTittle(noteData.getTittle());
        note.setComment(noteData.getComment());
        note.setNoteDate(noteData.getNoteDate());
        realm.commitTransaction();
    }

    public static List<Note> getAllNotes(Realm realm){
        List<Note> noteList = new ArrayList<>();
        RealmQuery query = realm.where(Note.class);
        RealmResults<Note> results = query.findAll();
        for (int index =0; index < results.size(); index++){
            Note note = results.get(index);
            noteList.add(note);
        }
        return noteList;

    }

    public static Note getNoteByID( Realm realm , long id){
        Note note = realm.where(Note.class).equalTo("id", id).findFirst();
        return note;
    }

    private static long getCount(Realm realm){
        long count = realm.where(Note.class).count();
        return count;
    }

    public static void updateNote(Realm realm, Note noteData){

       Note note = realm.where(Note.class).equalTo("id", noteData.getId()).findFirst();
        realm.beginTransaction();
        note.setTittle(noteData.getTittle());
        note.setComment(noteData.getComment());
        note.setNoteDate(noteData.getNoteDate());
        realm.commitTransaction();
    }


    public static void deleteNote(Realm realm, final Long id){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Note> result = realm.where(Note.class).equalTo("id", id).findAll();
                result.deleteAllFromRealm();
            }
        });
    }
}
