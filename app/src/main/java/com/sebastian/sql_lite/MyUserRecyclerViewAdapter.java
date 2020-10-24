package com.sebastian.sql_lite;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.sebastian.sql_lite.dataBase.User;

import java.util.List;


public class MyUserRecyclerViewAdapter extends RecyclerView.Adapter<MyUserRecyclerViewAdapter.ViewHolder> {

    private List<User> users;
    private Context context;

    public MyUserRecyclerViewAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    public void updateListUser(List<User> users ){
        this.users = users;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.textUsers.setText(
            new StringBuilder().append(users.get(position).getId())
                .append(" - ")
                .append(users.get(position).getName())
                .append(" ")
                .append(users.get(position).getLastName()
            )
        );

        holder.id = users.get(position).getId();

        holder.cardViewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, EditUserctivity.class);
                intent.putExtra("id",holder.id);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {

        return users ==null ? 0 : users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textUsers;
        private CardView cardViewUser;
        private String id;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            textUsers = view.findViewById(R.id.textUsers);
            cardViewUser = view.findViewById(R.id.cardUser);
        }

    }
}