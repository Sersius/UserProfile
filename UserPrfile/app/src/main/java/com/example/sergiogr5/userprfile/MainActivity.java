package com.example.sergiogr5.userprfile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private UserProfile user_profile;
    private Gson gson;
    private TextView nameview;
    private TextView userview;
    private TextView followingnumview;
    private TextView followersnumview;
    private TextView abouttextview;
    private ImageView profileimageview;
    private ImageView backgroundview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gson = new Gson();

        nameview = findViewById(R.id.name);
        userview = findViewById(R.id.alias);
        followingnumview = findViewById(R.id.followingview);
        followersnumview = findViewById(R.id.followersview);
        abouttextview = findViewById(R.id.about);

        try {
            InputStream stream = getAssets().open("SergioGomez.json");
            InputStreamReader reader = new InputStreamReader(stream);
            user_profile = gson.fromJson(reader, UserProfile.class);

            updateUserInfo();
        }
        catch (IOException e) {
            Toast.makeText(MainActivity.this, "Error parsing json", Toast.LENGTH_SHORT).show();
        }

        profileimageview = findViewById(R.id.profilepick);
        backgroundview = findViewById(R.id.backgroundview);

        Glide.with(this).load("file:///android_asset/SergioGomez.png"). apply(RequestOptions.circleCropTransform().circleCrop()).into(profileimageview);
        Glide.with(this).load("file:///android_asset/background.jpg").into(backgroundview);
    }

    private void updateUserInfo() {
        nameview.setText(user_profile.getName() + " " + user_profile.getLastname() );
        userview.setText(user_profile.getHandle());
        followingnumview.setText(user_profile.getFollowing());
        followersnumview.setText(user_profile.getFollowers());
        abouttextview.setText(user_profile.getAbout());
    }
}



