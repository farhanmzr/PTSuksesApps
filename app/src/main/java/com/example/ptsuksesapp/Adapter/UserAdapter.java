package com.example.ptsuksesapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ptsuksesapp.Activities.MainActivity;
import com.example.ptsuksesapp.Model.User;
import com.example.ptsuksesapp.R;

import java.util.List;

 public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {


     private Context mCtx;
     private List<User> userList;

     public UserAdapter(Context mCtx, List<User> userList) {
         this.mCtx = mCtx;
         this.userList = userList;
     }

     @Override
     public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         LayoutInflater inflater = LayoutInflater.from(mCtx);
         View view = inflater.inflate(R.layout.list_data, parent, false);
         return new UserViewHolder(view);
     }

     @Override
     public void onBindViewHolder(UserViewHolder holder, int position) {
         User user = userList.get(position);

         //loading the image

         holder.nama.setText(user.getNama());
         holder.email.setText(user.getEmail());
         holder.nohp.setText(String.valueOf(user.getNohp()));
         holder.pendidikan.setText(user.getPendidikan());
     }

     @Override
     public int getItemCount() {
         return userList.size();
     }

      class UserViewHolder extends RecyclerView.ViewHolder {

         TextView nama, email, nohp, pendidikan;

         public UserViewHolder(View itemView) {
             super(itemView);

             nama = itemView.findViewById(R.id.nama);
             email = itemView.findViewById(R.id.email);
             nohp = itemView.findViewById(R.id.nohp);
             pendidikan = itemView.findViewById(R.id.pendidikan);
             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                 }
             });
         }
     }

 }
