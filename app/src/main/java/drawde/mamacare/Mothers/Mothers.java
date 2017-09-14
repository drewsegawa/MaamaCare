package drawde.mamacare.Mothers;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import drawde.mamacare.R;
import io.paperdb.Paper;

public class Mothers extends AppCompatActivity {
    SearchView search;
    ListView mothersList;
    FloatingActionButton newMother;

    //String [] mothers;
    ArrayList<String> mothers = new ArrayList<String>();

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mothers);

        Paper.init(getApplicationContext());

        search = (SearchView) findViewById(R.id.search);
        mothersList = (ListView) findViewById(R.id.mothers);
        newMother = (FloatingActionButton) findViewById(R.id.new_mother);

        populateList(Paper.book().read("mothers").toString());

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mothers);
        mothersList.setAdapter(adapter);

        mothersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Toast.makeText(getApplicationContext(), ""+i, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MotherDetails.class);
                intent.putExtra("id", getId(i, Paper.book().read("mothers").toString()));
                startActivity(intent);
            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        newMother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });
    }

    // populate list array
    private void populateList(String data){
        JSONArray array = null;

        try {
            JSONObject json = new JSONObject(data);
            array = json.getJSONArray("mothers");

            for (int i=0; i < array.length(); i++){
                mothers.add(array.getJSONObject(i).getString("name").toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // get mothers id
    private String getId(int id, String data){
        String mother_id = "";

        try {
            JSONObject json = new JSONObject(data);
            JSONArray array = json.getJSONArray("mothers");
            mother_id = array.getJSONObject(id).getString("id").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mother_id;
    }
}
