package com.example.mindfulnote.meditation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mindfulnote.R;
import com.example.mindfulnote.meditation.MeditationAdapter;
import com.example.mindfulnote.meditation.MeditationItem;

import java.util.ArrayList;
import java.util.List;

public class MeditationFragment extends Fragment {

    private RecyclerView recyclerView;
    private MeditationAdapter adapter;
    private List<MeditationItem> meditationItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("creating view");
        View view = inflater.inflate(R.layout.fragment_meditation, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewMeditation);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        meditationItems = generateMeditationItems();
        adapter = new MeditationAdapter(meditationItems, new MeditationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle item click
                // use meditationItems.get(position) to get the clicked item data
                // have a VideoActivity to play videos

                Intent intent = new Intent(getActivity(), VideoActivity.class);
                intent.putExtra(VideoActivity.EXTRA_VIDEO_URL, position);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<MeditationItem> generateMeditationItems() {
        // Populate this list with data (image, headline, description) for each card
        List<MeditationItem> items = new ArrayList<>();
        items.add(new MeditationItem(R.drawable.meditation5min, "5 Minute Simple Guided Hands-On Meditation", "Enjoy an easier way to meditate with this simple 5 minute guided routine. All you need are a couple of common household objects to hold - oranges, bean bags, baseballs, stones... anything comfortable that has a bit of weight."));
        items.add(new MeditationItem(R.drawable.meditation10min, "10 Minute Simple Guided Hands-On Meditation", "Embark on a tranquil journey with this 10-minute guided hands-on meditation. This meditation provides a brief escape to reset and rejuvenate, promoting a sense of calm and mindfulness."));
        items.add(new MeditationItem(R.drawable.meditation15min, "15 Minute Simple Guided Hands-On Meditation", "Indulge in a peaceful 15-minute guided hands-on meditation, a thoughtful practice that invites you to connect with the present moment.This extended meditation provides an opportunity to delve deeper into a tranquil state, promoting a profound sense of well-being and presence."));
        items.add(new MeditationItem(R.drawable.video4, "4-7-8 Calm Breathing Exercise", "4-7-8 rhythmic breathing is a core part of many meditation and yoga practices as it promotes relaxation and can also be used to help you fall asleep in a shorter period of time. The goal is to calm your sympathetic nervous system, which controls the fight-or-flight response. "));
        return items;
    }
}
