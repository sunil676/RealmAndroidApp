package com.sunil.realmandroidapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

/**
 * Created by sunil on 12/14/16.
 */

public class NoteDetailActivity extends AppCompatActivity {

    @BindView(R.id.title)
    EditText title;
    @BindView(R.id.titleLayout)
    TextInputLayout titleLayout;
    @BindView(R.id.description)
    EditText description;
    @BindView(R.id.descriptionLayout)
    TextInputLayout descriptionLayout;
    @BindView(R.id.age)
    EditText dateEditText;
    @BindView(R.id.dateLayout)
    TextInputLayout dateLayout;
    @BindView(R.id.save)
    Button save;

    Realm realm;

    private boolean isCreate;
    private Long noteId;
    private Note mNote;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            isCreate = bundle.getBoolean("Create");
            noteId = bundle.getLong("NoteID");
        }
        if (!isCreate) {
            save.setText("Save");
            mNote = NoteManager.getNoteByID(realm, noteId);
            if (mNote != null) {
                title.setText(mNote.getTittle());
                description.setText(mNote.getComment());
                dateEditText.setText(mNote.getNoteDate() + "");
            }
        } else {
            save.setText("Add");
            Calendar c = Calendar.getInstance();
            System.out.println("Current time => " + c.getTime());
            dateEditText.setText(c.getTime()+"");
        }
    }

    @OnClick(R.id.save)
    public void saveClick(){
        if (isCreate){
            // insert note
            if (!valid()){
                return;
            }else{
                Note note = new Note();
                note.setTittle(title.getText().toString());
                note.setComment(description.getText().toString());
                note.setNoteDate(dateEditText.getText().toString());
                NoteManager.addNote(realm, note);
                finish();
            }

        }else{
            // update note
            if (!valid()){
                return;
            }else{
                mNote.setTittle(title.getText().toString());
                mNote.setComment(description.getText().toString());
                mNote.setNoteDate(dateEditText.getText().toString());
                NoteManager.updateNote(realm, mNote);
                finish();
            }
        }
    }

    private boolean valid(){
        boolean isValid;
        if (Utility.nullCheck(titleLayout, "Title")){
            isValid = false;
        }
        else if (Utility.nullCheck(descriptionLayout, "Comment")){
            isValid = false;
        }
        else if (Utility.nullCheck(dateLayout, "Date")){
            isValid = false;
        }else{
            isValid = true;
        }
        return  isValid;
    }
}
