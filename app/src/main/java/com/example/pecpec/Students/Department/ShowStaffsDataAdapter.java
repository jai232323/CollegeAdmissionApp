package com.example.pecpec.Students.Department;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pecpec.R;
import com.example.pecpec.Staffs.Faculty.StaffData;

import java.util.ArrayList;

public class ShowStaffsDataAdapter extends RecyclerView.Adapter<ShowStaffsDataAdapter.ViewHolder> {


    private Context context;
    private ArrayList<StaffData> items;

    public ShowStaffsDataAdapter(Context context, ArrayList<StaffData> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.show_staffadapter,parent,false);
        return new ShowStaffsDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        StaffData data = items.get(position);

        Glide.with(context).load(data.getImage()).into(holder.StaffImage);
        holder.StaffName.setText(data.getName());
        holder.StaffEmail.setText(data.getEmail());
        holder.StaffDepartment.setText(data.getSpinner());
        holder.StaffPost.setText(data.getPost());
        holder.StaffPhoneNo.setText(data.getPhoneNo());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{


        ImageView StaffImage;
        TextView StaffName,StaffEmail,StaffPhoneNo,StaffPost,StaffDepartment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            StaffImage = itemView.findViewById(R.id.StaffImage);
            StaffName = itemView.findViewById(R.id.StaffName);
            StaffEmail = itemView.findViewById(R.id.StaffEmail);
            StaffPhoneNo = itemView.findViewById(R.id.StaffPhoneNo);
            StaffPost = itemView.findViewById(R.id.StaffPost);
            StaffDepartment = itemView.findViewById(R.id.StaffDepartment);
        }
    }
}
