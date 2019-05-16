package com.example.lenovo.javaandr;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.*;
import java.util.*;


public class GamePage extends AppCompatActivity {

   ArrayList<String> numberlist = new ArrayList<>();            //lista
   public int JsonLenght = 0;
   int queue=1;
   public int result=0;
   int engine = 0;
   String odp;
   String odp1;
   String odp2;
   String odp3;









   public void on_start() throws IOException, JSONException {
       TextView slowoView =  (TextView) findViewById(R.id.pytanie);
       TextView odp1View =  (TextView) findViewById(R.id.odp1);
       TextView odp2View =  (TextView) findViewById(R.id.odp2);
       TextView odp3View =  (TextView) findViewById(R.id.odp3);


       odp1View.setBackgroundResource(R.color.def);
       odp2View.setBackgroundResource(R.color.def);
       odp3View.setBackgroundResource(R.color.def);



       String json;
       InputStream is = getAssets().open("QuizMedyczna.JSON");
       int size = is.available();
       byte[] buffer = new byte[size];
       is.read(buffer);
       is.close();

       json=new String(buffer, "UTF-8");
       JSONArray jsonArray = new JSONArray(json);


       JSONObject obj = jsonArray.getJSONObject(0);
       slowoView.setText(obj.getString("pytanie"));
       odp1View.setText(obj.getString("o1"));
       odp2View.setText(obj.getString("o2"));
       odp3View.setText(obj.getString("o3"));
       odp = obj.getString("odp");
       odp1 = obj.getString("o1");
       odp2 = obj.getString("o2");
       odp3 = obj.getString("o3");

   }


   public void get_json() throws IOException, JSONException {

       if (queue == JsonLenght) {
           TextView slowoView = (TextView) findViewById(R.id.pytanie);
           double wynik=((result/(double)queue)*100);
           wynik*=100;
           wynik = Math.round(wynik);
           wynik/=100;

           slowoView.setText(result+" / "+queue+ "   "+ wynik+"%");

           TextView odp1_view =  (TextView) findViewById(R.id.odp1);
           TextView odp2_view =  (TextView) findViewById(R.id.odp2);
           TextView odp3_view =  (TextView) findViewById(R.id.odp3);

           odp1_view.setBackgroundResource(R.color.white);
           odp2_view.setBackgroundResource(R.color.white);
           odp3_view.setBackgroundResource(R.color.white);

           odp1_view.setText("");
           odp2_view.setText("");
           odp3_view.setText("");


       }
       else {




           TextView slowoView = (TextView) findViewById(R.id.pytanie);
           TextView odp1View = (TextView) findViewById(R.id.odp1);
           TextView odp2View = (TextView) findViewById(R.id.odp2);
           TextView odp3View = (TextView) findViewById(R.id.odp3);


           String json;
           InputStream is = getAssets().open("QuizMedyczna.JSON");
           int size = is.available();
           byte[] buffer = new byte[size];
           is.read(buffer);
           is.close();

           json = new String(buffer, "UTF-8");
           JSONArray jsonArray = new JSONArray(json);


           JSONObject obj = jsonArray.getJSONObject(queue - 1);

           JsonLenght = jsonArray.length();


           JSONObject obj2 = jsonArray.getJSONObject(queue);
           slowoView.setText(obj2.getString("pytanie"));
           odp1View.setText(obj2.getString("o1"));
           odp2View.setText(obj2.getString("o2"));
           odp3View.setText(obj2.getString("o3"));
           odp = obj2.getString("odp");
           odp1 = obj2.getString("o1");
           odp2 = obj2.getString("o2");
           odp3 = obj2.getString("o3");


           queue++;


       }
   }






    public GamePage() throws JSONException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);

        try {
            on_start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Button start_to_menu = (Button) findViewById(R.id.quiz_to_menu);
        start_to_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startIntent);

            }
        });




        Button next_button = (Button) findViewById(R.id.next_button);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(engine == 1){
                TextView odp1_view =  (TextView) findViewById(R.id.odp1);
                TextView odp2_view =  (TextView) findViewById(R.id.odp2);
                TextView odp3_view =  (TextView) findViewById(R.id.odp3);

                odp1_view.setBackgroundResource(R.color.def);
                odp2_view.setBackgroundResource(R.color.def);
                odp3_view.setBackgroundResource(R.color.def);
                engine = 0;
                try {
                    get_json();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }




                }
            }
        });



       final Button odp1_button = (Button) findViewById(R.id.odp1);
        odp1_button.setOnClickListener(new View.OnClickListener() {

            TextView odp1_view =  (TextView) findViewById(R.id.odp1);
            TextView odp2_view =  (TextView) findViewById(R.id.odp2);
            TextView odp3_view =  (TextView) findViewById(R.id.odp3);

            @Override
            public void onClick(View view) {
                if (engine == 0) {


                    if (odp.equals(odp1)) {
                        odp1_view.setBackgroundResource(R.color.green);
                        odp2_view.setBackgroundResource(R.color.red);
                        odp3_view.setBackgroundResource(R.color.red);
                        engine = 1;
                        result++;
                    } else if (odp.equals(odp2)) {
                        odp1_view.setBackgroundResource(R.color.red);
                        odp2_view.setBackgroundResource(R.color.green);
                        odp3_view.setBackgroundResource(R.color.red);
                        engine = 1;
                    } else if (odp.equals(odp3)) {
                        odp1_view.setBackgroundResource(R.color.red);
                        odp2_view.setBackgroundResource(R.color.red);
                        odp3_view.setBackgroundResource(R.color.green);
                        engine = 1;
                    }


                }
            }
        });


        final Button odp2_button = (Button) findViewById(R.id.odp2);
        odp2_button.setOnClickListener(new View.OnClickListener() {

            TextView odp1_view =  (TextView) findViewById(R.id.odp1);
            TextView odp2_view =  (TextView) findViewById(R.id.odp2);
            TextView odp3_view =  (TextView) findViewById(R.id.odp3);

            @Override
            public void onClick(View view) {
                if (engine == 0) {

                    if (odp.equals(odp1)) {
                        odp1_view.setBackgroundResource(R.color.green);
                        odp2_view.setBackgroundResource(R.color.red);
                        odp3_view.setBackgroundResource(R.color.red);
                        engine = 1;
                    } else if (odp.equals(odp2)) {
                        odp1_view.setBackgroundResource(R.color.red);
                        odp2_view.setBackgroundResource(R.color.green);
                        odp3_view.setBackgroundResource(R.color.red);
                        result++;
                        engine = 1;
                    } else if (odp.equals(odp3)) {
                        odp1_view.setBackgroundResource(R.color.red);
                        odp2_view.setBackgroundResource(R.color.red);
                        odp3_view.setBackgroundResource(R.color.green);
                        engine = 1;
                    }


                }
            }
        });



        final Button odp3_button = (Button) findViewById(R.id.odp3);
        odp3_button.setOnClickListener(new View.OnClickListener() {

            TextView odp1_view =  (TextView) findViewById(R.id.odp1);
            TextView odp2_view =  (TextView) findViewById(R.id.odp2);
            TextView odp3_view =  (TextView) findViewById(R.id.odp3);

            @Override
            public void onClick(View view) {
                if (engine == 0) {

                    if (odp.equals(odp1)) {
                        odp1_view.setBackgroundResource(R.color.green);
                        odp2_view.setBackgroundResource(R.color.red);
                        odp3_view.setBackgroundResource(R.color.red);
                        engine =1;
                    } else if (odp.equals(odp2)) {
                        odp1_view.setBackgroundResource(R.color.red);
                        odp2_view.setBackgroundResource(R.color.green);
                        odp3_view.setBackgroundResource(R.color.red);
                        engine = 1;
                    } else if (odp.equals(odp3)) {
                        odp1_view.setBackgroundResource(R.color.red);
                        odp2_view.setBackgroundResource(R.color.red);
                        odp3_view.setBackgroundResource(R.color.green);
                        engine =1;
                        result++;
                    }


                }
            }
        });



    }

}
