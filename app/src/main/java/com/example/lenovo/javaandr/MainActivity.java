package com.example.lenovo.javaandr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = this.getWindow().getDecorView();
        view.setBackgroundResource(R.drawable.ic_launcher_background);



        Button medycznaButton = (Button)findViewById(R.id.MedycznaButton);
        medycznaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), GamePage.class);
                startActivity(startIntent);

            }
        });

        Button technicznaButton = (Button)findViewById(R.id.TechnicznaButton);
        technicznaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), GamePage.class);
                startActivity(startIntent);

            }
        });


        Button informatykaButton = (Button)findViewById(R.id.InformatykaButton);
        informatykaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), GamePage.class);
                startActivity(startIntent);

            }
        });





    }
}
