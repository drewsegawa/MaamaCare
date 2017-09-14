package drawde.mamacare.Mothers;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import drawde.mamacare.R;
import io.paperdb.Paper;

public class Register extends AppCompatActivity {
    EditText name, dob, weight;
    Button register;

    private DatePickerDialog date;
    private DatePickerDialog.OnDateSetListener dateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Paper.init(getApplicationContext());

        name = (EditText) findViewById(R.id.name);
        dob = (EditText) findViewById(R.id.dob);
        weight = (EditText) findViewById(R.id.weight);

        register = (Button) findViewById(R.id.register);

        dob.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    Calendar calendar = Calendar.getInstance();

                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int day = calendar.get(Calendar.DAY_OF_MONTH);

                    date = new DatePickerDialog(Register.this, android.R.style.Theme_Holo_Dialog_MinWidth,
                            dateListener,
                            year, month, day);

                    date.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    date.show();
                }
            }
        });

        dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Toast.makeText(Register.this, ""+i2+"/"+i1+"/"+i, Toast.LENGTH_SHORT).show();
                dob.setText(""+i2+"/"+i1+"/"+i);
            }
        };

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateRegistration()){
                    try {
                        JSONObject json = new JSONObject(Paper.book().read("mothers").toString());
                        JSONArray array = json.getJSONArray("mothers");

                        JSONObject reg = new JSONObject();

                        reg.put("id", array.length());
                        reg.put("name", name.getText().toString());
                        reg.put("dob", dob.getText().toString());
                        reg.put("weight", weight.getText().toString());
                        reg.put("vaccinated", "");
                        reg.put("birth", "");

                        array.put(reg);

                        JSONObject newData = new JSONObject();
                        newData.put("mothers", array);

                        Paper.book().write("mothers", newData);
                        Toast.makeText(getApplicationContext(), newData.toString(), Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private Boolean validateRegistration(){
        Boolean result = false;

        if (name.getText().toString().equals("")){
            name.getBackground().mutate().setColorFilter(getResources().getColor(R.color.error), PorterDuff.Mode.SRC_ATOP);
        }else if (dob.getText().toString().equals("")){
            dob.getBackground().mutate().setColorFilter(getResources().getColor(R.color.error), PorterDuff.Mode.SRC_ATOP);
        }else if (weight.getText().toString().equals("")){
            weight.getBackground().mutate().setColorFilter(getResources().getColor(R.color.error), PorterDuff.Mode.SRC_ATOP);
        }else{
            result = true;
        }

        return result;
    }
}
