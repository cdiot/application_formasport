package fr.formasport.catalogue.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import fr.formasport.catalogue.R;

public class FormationDetailActivity extends AppCompatActivity {

    TextView tv_id,tv_title,tv_duration,tv_description;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formation_detail);

        //Initializing Views
        tv_id = findViewById(R.id.tv_id);
        tv_title = findViewById(R.id.tv_title);
        tv_duration = findViewById(R.id.tv_duration);
        tv_description = findViewById(R.id.tv_description);

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        tv_id.setText("ID: "+ FormationListActivity.formationArrayList.get(position).getId_formation());
        tv_title.setText("Titre: "+FormationListActivity.formationArrayList.get(position).getTitle());
        tv_duration.setText("Duree: "+FormationListActivity.formationArrayList.get(position).getDuration());
        tv_description.setText("Description: "+FormationListActivity.formationArrayList.get(position).getDescription());

    }
}