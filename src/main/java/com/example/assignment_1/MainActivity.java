package com.example.assignment_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button show;
    Button save ;
    EditText name ;
    EditText number ;
    EditText adress ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        show = findViewById(R.id.show);
        save = findViewById(R.id.save);
        name = findViewById(R.id.name);
        number = findViewById(R.id.number);
        adress = findViewById(R.id.adress);


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this , Recycle.class);
                startActivity(intent);
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String names = name.getText().toString().trim();
                String numbers = number.getText().toString().trim();
                String adresses = adress.getText().toString().trim();

                Map<String,Object> info = new HashMap<>();
                info.put("name",names);
                info.put("number",numbers);
                info.put("adress",adresses);

                db.collection("information").add(info).addOnSuccessListener(documentReference ->
                {
                    Toast.makeText(MainActivity.this ,"Save is Success...",Toast.LENGTH_SHORT).show();
                    Log.d("TTT" , " Android is Done : "+ documentReference.getId());
                }).addOnFailureListener(e -> {
                    Toast.makeText(MainActivity.this ,"Save is Not...",Toast.LENGTH_LONG).show();
                    Log.d("TTT" , e.getMessage());
                });
            }
        });

    }
}