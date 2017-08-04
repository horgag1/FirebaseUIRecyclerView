package com.gerhorgan.todo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseUsers = FirebaseDatabase.getInstance().getReference().child("users");

        recycler = (RecyclerView) findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerAdapter<User, UserViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<User, UserViewHolder>(
                User.class,
                R.layout.user_row,
                UserViewHolder.class,
                databaseUsers
        ) {
            @Override
            protected void populateViewHolder(final UserViewHolder holder, User model, int position) {
                holder.txtName.setText(model.name);
                if (!model.image.equals("default"))
                    Picasso.with(MainActivity.this).load(model.image).into(holder.imgProfile);
            }
        };
        recycler.setAdapter(firebaseRecyclerAdapter);

}


    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        ImageView imgProfile;

        public UserViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtUser);
            imgProfile = (ImageView) itemView.findViewById(R.id.imageUser);
        }
    }

    }
