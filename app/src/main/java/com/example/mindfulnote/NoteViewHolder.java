package com.example.mindfulnote;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    public TextView notetitle;
    public TextView notecontent;
    public LinearLayout mnote;

    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
        notetitle = itemView.findViewById(R.id.notetitle);
        notecontent = itemView.findViewById(R.id.notecontent);
        mnote = itemView.findViewById(R.id.note);
    }
}
