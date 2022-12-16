package com.example.pecpec.Staffs.Faculty;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Parcelable;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.StaffViewAdapter> {


    private List<StaffData> list;
    private Context context;
    private String category;

    String category1 ;
    boolean userid;

    private FirebaseUser firebaseUser;

    public StaffAdapter(List<StaffData> list, Context context, String category) {
        this.list = list;
        this.context = context;
        this.category = category;
    }

    @NonNull
    @Override
    public StaffViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.faculty_item_layout,parent,false);

        return new StaffViewAdapter(view);

    }

    @Override
    public void onBindViewHolder(@NonNull StaffViewAdapter holder, int position) {

        StaffData item = list.get(position);
        holder.name.setText(item.getName());
        holder.post.setText(item.getPost());
        holder.email.setText(item.getEmail());

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        holder.updateBtn.setVisibility(View.VISIBLE);
        holder.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,UpdateStaffActivity.class);

                String userdbpushKey = item.getUserdbpushKey();
                intent.putExtra("name",item.getName());
                intent.putExtra("email",item.getEmail());
                intent.putExtra("post",item.getPost());
                intent.putExtra("image",item.getImage());
                intent.putExtra("key",item.getKey());
                intent.putExtra("category",category);
                intent.putExtra("userdbpushKey",userdbpushKey);
                context.startActivity(intent);
            }
        });


        //   holder.staffImageView.setImageURI(Uri.parse("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FS_and_H_Photos%2F7.jpg?alt=media&token=ca15b4b4-f219-4f01-a961-30d54b184cdd"));


        Glide.with(context).load(item.getImage()).into(holder.staffImageView);

        holder.staffImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullImagerActivity.class);
                intent.putExtra("image",  item.getImage());
                context.startActivity(intent);
            }
        });


//        if (item.getKey().equals(firebaseUser.getUid())){
//            holder.updateBtn.setVisibility(View.VISIBLE);
//
//            holder.updateBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent intent = new Intent(context,UpdateStaffActivity.class);
//                    intent.putExtra("name",item.getName());
//                    intent.putExtra("email",item.getEmail());
//                    intent.putExtra("post",item.getPost());
//                    intent.putExtra("image",item.getImage());
//                    intent.putExtra("key",item.getKey());
//                    intent.putExtra("category",category);
//                    context.startActivity(intent);
//
//                }
//            });
//        }else {
//            holder.updateBtn.setVisibility(View.GONE);
//        }





    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class StaffViewAdapter extends RecyclerView.ViewHolder {

        private TextView name,email,post;
        private Button updateBtn;
        private ImageView staffImageView;

        public StaffViewAdapter(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.StaffName);
            email=itemView.findViewById(R.id.StaffEmail);
            post=itemView.findViewById(R.id.StaffPost);
            updateBtn=itemView.findViewById(R.id.StaffUpdateInfo);
            staffImageView=itemView.findViewById(R.id.StaffImage);


        }
    }
}
