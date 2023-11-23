package com.example.mindfulnote.meditation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mindfulnote.R;
import java.util.List;

public class MeditationAdapter extends RecyclerView.Adapter<MeditationAdapter.ViewHolder> {

    private List<MeditationItem> meditationItems;
    private OnItemClickListener listener;

    public MeditationAdapter(List<MeditationItem> meditationItems, OnItemClickListener listener) {
        this.meditationItems = meditationItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meditation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MeditationItem item = meditationItems.get(position);
        holder.imageView.setImageResource(item.getImageResource());
        holder.textViewHeadline.setText(item.getHeadline());
        holder.textViewDescription.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return meditationItems.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewHeadline;
        TextView textViewDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewMood);
            textViewHeadline = itemView.findViewById(R.id.textViewHeadline);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        // Method to get the video URL based on position
        public String getVideoUrlForPosition(int position) {
            String resourceName = "video" + (position + 1); // video files are named video1, video2, etc.
            int rawId = itemView.getContext().getResources().getIdentifier(resourceName, "raw", itemView.getContext().getPackageName());
            return "android.resource://" + itemView.getContext().getPackageName() + "/" + rawId;
        }
    }
}
