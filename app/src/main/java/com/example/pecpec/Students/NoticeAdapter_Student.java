package com.example.pecpec.Students;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.pecpec.FullImagerActivity;
import com.example.pecpec.R;
import com.example.pecpec.Staffs.Notice.NoticeData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NoticeAdapter_Student extends RecyclerView.Adapter<NoticeAdapter_Student.NoticeViewAdapter> {

    private Context context;
    private ArrayList<NoticeData> list;



    public NoticeAdapter_Student(Context context, ArrayList<NoticeData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoticeViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newsfeed_ite_layout_student,parent,false);

        return new NoticeViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewAdapter holder, int position) {
        NoticeData currentItem = list.get(position);

        holder.deleteNoticeTitle.setText(currentItem.getTitle());
        holder.date.setText(currentItem.getDate());
        holder.time.setText(currentItem.getTime());




        try {
            if (currentItem.getImage() !=null)
                Glide.with(context).load(currentItem.getImage()).into(holder.deleteNoticeImage);

        }catch (Exception e){
            e.printStackTrace();
        }
        holder.deleteNoticeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullImagerActivity.class);
                intent.putExtra("image",currentItem.getImage());
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NoticeViewAdapter extends RecyclerView.ViewHolder{


        private TextView deleteNoticeTitle,date,time;
        private ImageView deleteNoticeImage;


        public NoticeViewAdapter(@NonNull View itemView) {
            super(itemView);


            deleteNoticeTitle=itemView.findViewById(R.id.deleteNoticeTitle);
            deleteNoticeImage=itemView.findViewById(R.id.deleteNoticeImage);
            date=itemView.findViewById(R.id.date_student);
            time=itemView.findViewById(R.id.time_student);

        }
    }
}
