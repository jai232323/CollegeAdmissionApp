package com.example.pecpec.Staffs.Notice;


import android.annotation.SuppressLint;
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

import com.example.pecpec.FullImagerActivity;
import com.example.pecpec.R;
import com.example.pecpec.Staffs.StaffMainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewAdapter> {

    private Context context;
    private ArrayList<NoticeData> list;

    private DatabaseReference dbRef;

    private FirebaseUser firebaseUser;

    public NoticeAdapter(Context context, ArrayList<NoticeData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoticeViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newsfeed_item_layout,parent,false);

        return new NoticeViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewAdapter holder, @SuppressLint("RecyclerView") int position) {

        NoticeData currentItem = list.get(position);



        holder.deleteNoticeTitle.setText(currentItem.getTitle());

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        holder.deleteNoticeBtn.setVisibility(View.VISIBLE);
        holder.deleteNoticeBtn.setVisibility(View.VISIBLE);

        holder.deleteNoticeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure want to delete this notice ? ");
                builder.setIcon(R.drawable.ic_delete_blue);
                builder.setCancelable(true);
                builder.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Notice");
                                reference.child(currentItem.getKey()).removeValue()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(context,"Notice Deleted Successfully",Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(context, StaffMainActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                context.startActivity(intent);
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                notifyItemChanged(position);
                            }
                        }
                );
                builder.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }
                );
                AlertDialog dialog = null;
                try {
                    dialog = builder.create();
                }catch (Exception e){
                    e.printStackTrace();
                }
                if (dialog !=null){
                    dialog.show();
                }

            }
        });


//        if (currentItem.getKey().equals(currentItem.getUserid())){
//
//        }else {
//            holder.deleteNoticeBtn.setVisibility(View.GONE);
//        }

        try {
            if (currentItem.getImage() !=null)
                Picasso.get().load(currentItem.getImage()).into(holder.deleteNoticeImage);
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

    public class NoticeViewAdapter extends RecyclerView.ViewHolder {

        public Button deleteNoticeBtn;
        private TextView deleteNoticeTitle;
        private ImageView deleteNoticeImage;

        public NoticeViewAdapter(@NonNull View itemView) {
            super(itemView);

            deleteNoticeBtn=itemView.findViewById(R.id.deleteNoticeBtn);
            deleteNoticeTitle=itemView.findViewById(R.id.deleteNoticeTitle);
            deleteNoticeImage=itemView.findViewById(R.id.deleteNoticeImage);

        }
    }
}
