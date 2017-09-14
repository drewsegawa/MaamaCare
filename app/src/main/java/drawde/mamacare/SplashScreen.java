package drawde.mamacare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import io.paperdb.Paper;

public class SplashScreen extends AppCompatActivity {
    String mothers = "{\n" +
            "  \"mothers\": [\n" +
            "    {\"id\":\"0\", \"name\":\"Namakula Hasifa\", \"dob\":\"20/05/1990\", \"weight\":\"60kg\", \"vaccinated\":\"yes\", \"birth\":\"Yes\"},\n" +
            "    {\"id\":\"1\", \"name\":\"Namata Maria\", \"dob\":\"20/05/1992\", \"weight\":\"75kg\", \"vaccinated\":\"Yes\", \"birth\":\"Yes\"},\n" +
            "    {\"id\":\"2\", \"name\":\"Namutebi Lilian\", \"dob\":\"20/05/1991\", \"weight\":\"43kg\", \"vaccinated\":\"yes\", \"birth\":\"\"},\n" +
            "    {\"id\":\"3\", \"name\":\"Namaganda Grace\", \"dob\":\"20/05/1994\", \"weight\":\"52kg\", \"vaccinated\":\"\", \"birth\":\"\"}\n" +
            "  ]\n" +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Paper.init(getApplicationContext());
        Paper.book().write("first_run", true);

        if (Paper.book().read("first_run")){
            Paper.book().write("mothers", mothers);
            Paper.book().write("first_run", false);
        }

        Toast.makeText(getApplicationContext(), ""+Paper.book().read("first_run"), Toast.LENGTH_SHORT).show();

        Thread myThread = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(3000);

                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        myThread.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
