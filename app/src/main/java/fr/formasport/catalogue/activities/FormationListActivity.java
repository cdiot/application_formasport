package fr.formasport.catalogue.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.formasport.catalogue.R;
import fr.formasport.catalogue.adapters.CustomListView;
import fr.formasport.catalogue.models.Formation;

public class FormationListActivity extends AppCompatActivity {

    ListView listView;
    CustomListView customListView;
    public static ArrayList<Formation> formationArrayList = new ArrayList<>();
    String url = "https://formasport.christopherdiot.zd.fr/controllers/contCatalogueFormation.php";
    Formation formation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formation_list);
        goToActivity((Button)findViewById(R.id.btn_logout), LoginActivity.class);


        listView = findViewById(R.id.lstFormation);
        customListView = new CustomListView(this, formationArrayList);
        listView.setAdapter(customListView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id_formation) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"Consulter cette formation"};
                builder.setTitle(formationArrayList.get(position).getTitle());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:

                                startActivity(new Intent(getApplicationContext(), FormationDetailActivity.class)
                                        .putExtra("position",position));

                                break;
                        }
                    }
                });


                builder.create().show();


            }
        });

        retrieveData();
    }

    public void retrieveData(){

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        formationArrayList.clear();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if (success.equals("1")) {

                                for (int i=0;i<jsonArray.length();i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id_formation = object.getString("id_formation");
                                    String title = object.getString("titre");
                                    String duration = object.getString("duree");
                                    String description = object.getString("description");

                                    formation = new Formation(id_formation, title, duration, description);
                                    formationArrayList.add(formation);
                                    customListView.notifyDataSetChanged();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FormationListActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void goToActivity(Button btn, final Class classe){
        btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(FormationListActivity.this, classe);
                startActivity(intent);
            }
        });
    }
}