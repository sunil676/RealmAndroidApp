package com.sunil.realmandroidapp;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunil on 12/14/16.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> noteList;
    private Context context;
    OnInteractionDelete mListener;

    public NoteAdapter(Context context, List<Note> notes, Activity activity) {
        this.noteList = notes;
        this.context = context;
        this.mListener = (OnInteractionDelete) activity;
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    @Override
    public void onBindViewHolder(NoteViewHolder noteViewHolder, final int i) {
        Note note = noteList.get(i);
        noteViewHolder.title.setText(note.getTittle());
        noteViewHolder.description.setText(note.getComment());
        noteViewHolder.date.setText(note.getNoteDate()+"");
        noteViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onDeleteClick(i);
            }
        });
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_note, viewGroup, false);
        return new NoteViewHolder(itemView);
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.description)
        TextView description;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.delete)
        ImageView delete;


        public NoteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnInteractionDelete{
        void onDeleteClick(int position);
    }
}