package com.example.pecpec.Students.Gallery;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pecpec.FullImagerActivity;
import com.example.pecpec.R;

import java.util.List;

public class StudentGalleryAdapter extends RecyclerView.Adapter<StudentGalleryAdapter.ViewAdapter> {

    private Context context;
    private List<String> images;

    public StudentGalleryAdapter(Context context, List<String> images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gallery_image,parent,false);
        return new ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {

        Glide.with(context).load(images.get(position)).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullImagerActivity.class);
                intent.putExtra("image",images.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewAdapter extends RecyclerView.ViewHolder{

        private ImageView imageView;

        public ViewAdapter(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.image_gallery_student);
        }
    }
}
