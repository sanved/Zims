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
public class LoginActivity extends AppCompatActivity
{
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        email = (EditText)findViewById(R.id.login_email);
        password = (EditText)findViewById(R.id.login_password);
    }

    public void onClickRegister(View v)
    {
        startActivity(new Intent(this,StudentRegistrationActivity.class));
    }

    public void onClickLogin(View v)
    {
       String student_email =  AcitivtyMain.sharedPreferences.getString("stu_email","sorry");
       String studnet_password =  AcitivtyMain.sharedPreferences.getString("stu_password","sorry");

        if(email.getText().toString().trim().equals(student_email) && password.getText().toString().trim().equals(studnet_password))
        {
            //Intent i = new Intent(this,MapsActivity.class);
            //i.putExtra("login","student");
            //startActivity(i);
           // Toast.makeText(LoginActivity.this, "Logged in as student", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(LoginActivity.this, "Invalid username / password", Toast.LENGTH_SHORT).show();
        }
    }
}
