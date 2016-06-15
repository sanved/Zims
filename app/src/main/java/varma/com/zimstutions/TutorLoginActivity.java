package varma.com.zimstutions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Chandu on 5/30/2016.
 */
public class TutorLoginActivity extends AppCompatActivity
{
    EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        email = (EditText)findViewById(R.id.login_email);
        password = (EditText)findViewById(R.id.login_password);

    }

    public void onClickRegister(View v)
    {
        startActivity(new Intent(this, RegistrationActivity.class));
    }

    public void onClickLogin(View e)
    {
        String tutor_email =  AcitivtyMain.sharedPreferences.getString("tutor_email","sorry");
        String tutor_password =  AcitivtyMain.sharedPreferences.getString("tutor_password","sorry");

        if(email.getText().toString().equals(tutor_email) && password.getText().toString().equals(tutor_password)) {
            //Intent i = new Intent(this,MapsActivity.class);
            //i.putExtra("login","tutor");
            //startActivity(i);
            //Toast.makeText(TutorLoginActivity.this, "Logged in as tutor", Toast.LENGTH_SHORT).showz();
        }
        else {
            Toast.makeText(TutorLoginActivity.this, "Invalid username / password", Toast.LENGTH_SHORT).show();
        }
    }
}
