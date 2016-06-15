package varma.com.zimstutions;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//import android.support.v7.app.AppCompatActivity;


/**
 * Created by Chandu on 5/30/2016.
 */
public class AcitivtyMain extends Activity
{
    DatabaseReference databaseReference;
    public static SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        sharedPreferences = getSharedPreferences("email",MODE_PRIVATE);

    }

    public void openStudentLoginPage(View v)
    {
        SharedPreferences.Editor se = sharedPreferences.edit();
        se.putString("login", "student");
        if(se.commit())
        {
            startActivity(new Intent(this,NewStudentLoginActivity.class));
        }
    }
    public void openTutorLoginPage(View v)
    {
        SharedPreferences.Editor se = sharedPreferences.edit();
        se.putString("login", "tutor");
        if(se.commit())
        {
            startActivity(new Intent(this,NewTutorLoginActivity.class));
        }
    }
}
