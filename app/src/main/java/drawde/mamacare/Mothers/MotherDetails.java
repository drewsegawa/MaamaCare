package drawde.mamacare.Mothers;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import drawde.mamacare.R;
import io.paperdb.Paper;

public class MotherDetails extends AppCompatActivity {
    TextView name, dob, weight, vaccinated, birth;
    FloatingActionButton edit;

    String id, vaccined, delivered;

    RelativeLayout vaccine, deliver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mother_details);

        name = (TextView) findViewById(R.id.name);
        dob = (TextView) findViewById(R.id.dob);
        weight = (TextView) findViewById(R.id.weight);
        vaccinated = (TextView) findViewById(R.id.vaccination);
        birth = (TextView) findViewById(R.id.birth);

        vaccine = (RelativeLayout) findViewById(R.id.layout_vaccinated);
        deliver = (RelativeLayout) findViewById(R.id.layout_birth);

        edit = (FloatingActionButton) findViewById(R.id.mother_edit);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // initialise paper storage
        Paper.init(getApplicationContext());

        try {
            JSONObject json = new JSONObject(Paper.book().read("mothers").toString());
            JSONArray array = json.getJSONArray("mothers");
            name.setText((array.getJSONObject(Integer.parseInt(getIntent().getStringExtra("id").toString())).getString("name").toString()));
            dob.setText((array.getJSONObject(Integer.parseInt(getIntent().getStringExtra("id").toString())).getString("dob").toString()));
            weight.setText((array.getJSONObject(Integer.parseInt(getIntent().getStringExtra("id").toString())).getString("weight").toString()));

            id = array.getJSONObject(Integer.parseInt(getIntent().getStringExtra("id").toString())).getString("id");
            vaccined = array.getJSONObject(Integer.parseInt(getIntent().getStringExtra("id").toString())).getString("vaccinated");
            delivered = array.getJSONObject(Integer.parseInt(getIntent().getStringExtra("id").toString())).getString("birth").toString();

            // hide vaccinated or birth if vaccinated value is null or empty
            if (vaccined.toString().equals(null) || vaccined.equals("")){
                vaccine.setVisibility(View.GONE);
                deliver.setVisibility(View.GONE);
            }else{
                vaccinated.setText((array.getJSONObject(Integer.parseInt(getIntent().getStringExtra("id").toString())).getString("vaccinated").toString()));
            }

            // hide birth layout if value is null or empty
            if (delivered.equals(null) || delivered.equals("")){
                deliver.setVisibility(View.GONE);
            }else{
                birth.setText((array.getJSONObject(Integer.parseInt(getIntent().getStringExtra("id").toString())).getString("birth").toString()));
                edit.setVisibility(View.GONE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vaccined.toString().equals("") && vaccined.equals("")){
                    Intent intent = new Intent(getApplicationContext(), FollowUp.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                } else if (delivered.toString().equals(null) || delivered.equals("")){
                    Intent intent = new Intent(getApplicationContext(), Finalise.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            }
        });
    }
}
