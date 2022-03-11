package com.example.assignment_1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Recycle extends AppCompatActivity {


    ProgressDialog pd;
    RecyclerView rv;
    RecyclerViewAdapter adapter;
    ArrayList<items> item;
    FirebaseFirestore db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);


        db = FirebaseFirestore.getInstance();

        pd = new ProgressDialog(Recycle.this);
        pd.setCancelable(false);
        pd.setMessage("Please Wait The Information.... :)");
        pd.show();

        rv = findViewById(R.id.rv_user);
        rv.setHasFixedSize(true);
        RecyclerView.LayoutManager  LM= new LinearLayoutManager(this);
        rv.setLayoutManager(LM);

        item = new ArrayList<items>();
        adapter = new RecyclerViewAdapter(Recycle.this,item);
        rv.setAdapter(adapter);

        Show_item();
    }

    private void Show_item() {

        db.collection("information").orderBy("name" , Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override

            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if(e != null){
                    if(pd.isShowing())
                        pd.dismiss();
                    Log.e("FireBase" , e.getMessage());
                    return;
                }

                for (DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()){

                    if(dc.getType() == DocumentChange.Type.ADDED){
                        Log.d("test" , String.valueOf(dc.getDocument()));
                        item.add(dc.getDocument().toObject(items.class));

                    }
                    adapter.notifyDataSetChanged();
                    if(pd.isShowing())
                        pd.dismiss();
                }
            }
        });
    }
}