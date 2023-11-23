package com.example.mindfulnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mindfulnote.meditation.MeditationFragment;
import com.example.mindfulnote.chat.ChatActivity;
import com.example.mindfulnote.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            int id = item.getItemId(); // get the item ID

            if (id == R.id.home) {
                replaceFragment(new HomeFragment());
            } else if (id == R.id.meditation) {
                replaceFragment(new MeditationFragment());
            } else if (id == R.id.note) {
                startActivity(new Intent(this, NoteActivity.class));
            } else if (id == R.id.chat) {
                // replaceFragment(new ChatFragment());
                Intent intentChat = new Intent(this, ChatActivity.class);
                startActivity(intentChat);
            } else if (id == R.id.logout) {
                Toast.makeText(getApplicationContext(), "Please select icon", Toast.LENGTH_SHORT).show();
            } else { // handle the default case
                Toast.makeText(getApplicationContext(), "Please select icon", Toast.LENGTH_SHORT).show();
            }

            return true;

        });

    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }


}
