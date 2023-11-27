package com.example.mindfulnote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditNoteActivity extends AppCompatActivity {
    Intent data;

    private EditText medittitle, editcontent;
    private FloatingActionButton savebtn;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        data = getIntent();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        medittitle = findViewById(R.id.edittitleofnote);
        editcontent = findViewById(R.id.editcontentofnote);
        savebtn = findViewById(R.id.saveeditnote);
        medittitle.setText(data.getStringExtra("title"));
        editcontent.setText(data.getStringExtra("content"));
        Toolbar toolbar = findViewById(R.id.toolbarofeditnote);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newTitle = medittitle.getText().toString();
                String newContent = editcontent.getText().toString();
                if (newTitle.isEmpty() || newContent.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Something is empty", Toast.LENGTH_SHORT).show();
                } else {
                    DocumentReference ref = firebaseFirestore.collection(Constants.COLLECTION_PATH).document(firebaseUser.getUid()).collection(Constants.COLLECTION_NAME).document(data.getStringExtra("noteId"));
                    Map<String, Object> newNote = new HashMap<>();
                    newNote.put("title", newTitle);
                    newNote.put("content", newContent);
                    ref.set(newNote).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "Note updated successfully!", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Fail to update the note", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}