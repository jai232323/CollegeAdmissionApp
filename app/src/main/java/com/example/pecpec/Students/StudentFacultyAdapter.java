package com.example.pecpec.Students;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pecpec.FullImagerActivity;
import com.example.pecpec.R;
import com.example.pecpec.Staffs.Faculty.StaffData;

import java.util.List;

public class StudentFacultyAdapter extends RecyclerView.Adapter<StudentFacultyAdapter.StaffViewAdapter>  {


    private List<StaffData> list;
    private Context context;
    private String mca;

    public StudentFacultyAdapter(List<StaffData> list, Context context, String mca) {
        this.list = list;
        this.context = context;
        this.mca=mca;
    }

    @NonNull
    @Override
    public StaffViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.faculty_item_layout_student,parent,false);

        return new StaffViewAdapter(view);

    }

    @Override
    public void onBindViewHolder(@NonNull StaffViewAdapter holder, int position) {

        StaffData item = list.get(position);
        holder.name.setText(item.getName());
        holder.post.setText(item.getPost());
        holder.email.setText(item.getEmail());

        //Glide.with(mContext).load(post.getPostimage()).into(viewHolder.post_image);
        Glide.with(context).load(item.getImage()).placeholder(R.drawable.staff_icon).into(holder.staffImageView);

        holder.staffImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullImagerActivity.class);
                intent.putExtra("image",item.getImage());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class StaffViewAdapter extends RecyclerView.ViewHolder{


        private TextView name,email,post;
        private ImageView staffImageView;


        public StaffViewAdapter(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.StaffName);
            email=itemView.findViewById(R.id.StaffEmail);
            post=itemView.findViewById(R.id.StaffPost);
            staffImageView=itemView.findViewById(R.id.StaffImage);

        }
    }
}
