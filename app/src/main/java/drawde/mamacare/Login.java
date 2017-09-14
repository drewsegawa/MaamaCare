package drawde.mamacare;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import drawde.mamacare.Mothers.Mothers;

public class Login extends AppCompatActivity {
    EditText username, password;

    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if(validateLogin(username.getText().toString(), password.getText().toString())){
                    //login(username.getText().toString(), password.getText().toString());
                //}
                Intent intent = new Intent(getApplicationContext(), Mothers.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateLogin(String user, String pass){
        Boolean result = false;

        if (user.equals("")){
            username.getBackground().mutate().setColorFilter(getResources().getColor(R.color.error), PorterDuff.Mode.SRC_ATOP);
        }else if (pass.equals("")){
            password.getBackground().mutate().setColorFilter(getResources().getColor(R.color.error), PorterDuff.Mode.SRC_ATOP);
        }else if (password.length() < 5){
            password.getBackground().mutate().setColorFilter(getResources().getColor(R.color.error), PorterDuff.Mode.SRC_ATOP);
            Toast.makeText(Login.this, "Password must be greater than 5 characters.", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }

        return result;
    }

    // method to handle actual login to the server
    private void login(String username, String password){
        if (username.equals("andrew") && password.equals("s3g@w@")){
            Intent intent = new Intent(getApplicationContext(), Mothers.class);
            startActivity(intent);
        }else {
            Toast.makeText(Login.this, "Unrecognised Username or Password.", Toast.LENGTH_SHORT).show();
            //Snackbar.make(view, "Unrecognised Username or Password.", Snackbar.LENGTH_LONG)
                    //.setAction("Action", null).show();
        }
    }
}
