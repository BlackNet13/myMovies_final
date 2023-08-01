package sg.rp.edu.rp.c346.id22038845.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    Button btnUpdate, btnDelete, btnCancel;
    EditText ed1, ed2, ed3, ed5;

    ArrayList<String> strList;

    Spinner rateSpn;

    String rating = "";

    Movies data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        ed3 = findViewById(R.id.ed3);
        ed5 = findViewById(R.id.ed5);

        rateSpn = findViewById(R.id.spinner);

        Intent x = getIntent();
        data = (Movies) x.getSerializableExtra("data");
        String title = data.getTitle();
        String genre = data.getGenre();
        int year = data.getYear();
        String gtrating = data.getRating();
        String link = data.getLink();

        ed1.setText(title);
        ed2.setText(genre);
        ed3.setText(String.valueOf(year));
        ed5.setText(link);

        int pos = 0;

        switch(gtrating){
            case "G":
                pos = 0;
                break;
            case "PG":
                pos = 1;
                break;
            case "PG13":
                pos = 2;
                break;
            case "NC16":
                pos = 3;
                break;
            case "M18":
                pos = 4;
                break;
            case "R21":
                pos = 5;
                break;
        }

        rateSpn.setSelection(pos);

        rateSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        rating = "G";
                        break;
                    case 1:
                        rating = "PG";
                        break;
                    case 2:
                        rating = "PG13";
                        break;
                    case 3:
                        rating = "NC16";
                        break;
                    case 4:
                        rating = "M18";
                        break;
                    case 5:
                        rating = "R21";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity3.this);
                data.setMovie(ed1.getText().toString(),ed2.getText().toString(), Integer.parseInt(ed3.getText().toString()), rating, ed5.getText().toString());
                dbh.updateMovie(data);
                dbh.close();
                Toast toast = Toast.makeText(MainActivity3.this, "Movie: <"+ ed1.getText()+"> has been updated",Toast.LENGTH_LONG);

                toast.show();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity3.this);
                dbh.deleteMovie(data.getId());
                Toast toast = Toast.makeText(MainActivity3.this, "Movie has been deleted",Toast.LENGTH_LONG);

                toast.show();
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}