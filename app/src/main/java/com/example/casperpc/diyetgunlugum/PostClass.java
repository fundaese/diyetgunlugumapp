package com.example.casperpc.diyetgunlugum;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by CasperPc on 11.12.2017.
 */

public class PostClass extends ArrayAdapter<String> {


    private final ArrayList<String> postID;
    private final ArrayList<String> useremail;
    private final ArrayList<String> userImage; //uri
    private final  ArrayList<String> userComment;
    private final Activity context;
    private FirebaseDatabase database;
    private DatabaseReference reference;


    public PostClass(ArrayList<String> postID, ArrayList<String> useremail, ArrayList<String> userImage, ArrayList<String> userComment, Activity context) {
        super(context,R.layout.custom_view,useremail); //post-customview
        this.postID=postID;
        this.useremail = useremail;
        this.userImage = userImage;
        this.userComment = userComment;
        this.context = context;
        database=FirebaseDatabase.getInstance();
        reference=database.getReference();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater=context.getLayoutInflater();

        View customView=layoutInflater.inflate(R.layout.custom_view,null,true);
        TextView useremailText=(TextView)customView.findViewById(R.id.username);
        TextView commentText=(TextView)customView.findViewById(R.id.commentText);
        ImageView imageView=(ImageView)customView.findViewById(R.id.imageView);

        useremailText.setText(useremail.get(position));
        commentText.setText(userComment.get(position));
        Picasso.with(context).load(userImage.get(position)).into(imageView);

        return customView;
    }
}
