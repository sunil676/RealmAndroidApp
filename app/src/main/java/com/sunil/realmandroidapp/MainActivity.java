package com.sunil.realmandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements NoteAdapter.OnInteractionDelete{

    @BindView(R.id.no_data)
    TextView noData;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.button_create)
    Button buttonCreate;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;

    Realm realm;
    NoteAdapter adapter;

    private List<Note> list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onResume() {
        super.onResume();
        list = new ArrayList<>();
        list = NoteManager.getAllNotes(realm);
        if (list == null && list.size() == 0) {
            noData.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            noData.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            adapter = new NoteAdapter(this, list, this);
            recyclerView.setAdapter(adapter);

        }
    }

    @OnClick(R.id.button_create)
    public void createNoteClick() {
        Intent intent = new Intent(MainActivity.this, NoteDetailActivity.class);
        intent.putExtra("Create", true);
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(final int position) {
        Note note = list.get(position);
        Long id = note.getId();
        NoteManager.deleteNote(realm, id);
        list.remove(note);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, adapter.getItemCount());
    }
}
