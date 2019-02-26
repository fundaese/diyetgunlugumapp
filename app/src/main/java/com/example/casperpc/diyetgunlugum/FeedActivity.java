package com.example.casperpc.diyetgunlugum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class FeedActivity extends AppCompatActivity {

    ArrayList<String> userIDFromFB;
    ArrayList<String> useremailsFromFB;
    ArrayList<String> userimageFromFB;
    ArrayList<String> usercommentFromFB;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    PostClass adapter;
    ListView listView;
    DatabaseReference newReference;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.add_post,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.add_post){
            Intent intent=new Intent(getApplicationContext(),UploadActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.log_out){
            FirebaseAuth.getInstance().signOut();
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        userIDFromFB = new ArrayList<String>();
        useremailsFromFB = new ArrayList<String>();
        usercommentFromFB=new ArrayList<String>();
        userimageFromFB=new ArrayList<String>();

        firebaseDatabase=FirebaseDatabase.getInstance();
        myRef=firebaseDatabase.getReference();

        adapter=new PostClass(userIDFromFB,useremailsFromFB,userimageFromFB,usercommentFromFB,this);
        listView=(ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        getDataFromFirebase();
    }

    protected void getDataFromFirebase(){

        newReference=firebaseDatabase.getReference("Posts");

        newReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //System.out.println("children"+dataSnapshot.getChildren());
                //System.out.println("key"+dataSnapshot.getKey());
                //System.out.println("value"+dataSnapshot.getValue());
                HashMap<String,String> hashMap ;
                for(DataSnapshot ds: dataSnapshot.getChildren()){

                    hashMap = (HashMap<String, String>) ds.getValue();

                    useremailsFromFB.add(hashMap.get("useremail"));
                    userimageFromFB.add(hashMap.get("downloadurl"));
                    usercommentFromFB.add(hashMap.get("comment"));
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
               // System.out.println("Hata:"+databaseError.getMessage().toString());
            }
        });
    }
}
