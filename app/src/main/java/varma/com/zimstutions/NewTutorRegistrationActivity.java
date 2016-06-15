package varma.com.zimstutions;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

/**
 * Created by Chandu on 6/11/2016.
 */
public class NewTutorRegistrationActivity extends AppCompatActivity
{
    EditText et1,et2,et3,et4,et5,et6;
    String uid = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutor_registration_layout);

        et1 = (EditText)findViewById(R.id.tut_et_name);
        et2 = (EditText)findViewById(R.id.tut_et_phone);
        et3 = (EditText)findViewById(R.id.tut_et_qualification);
        et4 = (EditText)findViewById(R.id.tut_et_subject);
        et5 = (EditText)findViewById(R.id.tut_et_classes);
        et6 = (EditText)findViewById(R.id.tut_et_experience);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        uid = b.getString("uid");
    }

    public void onClickTutorRegister(View v)
    {
        final Firebase ref = new Firebase(Config.FIREBASE_URL);

        final String name = et1.getText().toString().trim();
        final String phone = et2.getText().toString().trim();
        String qualification =et3.getText().toString().trim();
        String subject = et4.getText().toString().trim();
        String cls = et5.getText().toString().trim();
        String experience = et6.getText().toString().trim();

        final Tutor tutor = new Tutor();
        final String unique_id = generateUniqueId(name,phone);

        tutor.setName(name);
        tutor.setPhone(phone);
        tutor.setQualification(qualification);
        tutor.setSubject(subject);
        tutor.setCls(cls);
        tutor.setExperience(experience);
        tutor.setExperience(unique_id);
        tutor.setUniique_id(unique_id);

        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Your unique id is " + unique_id);
        ad.setMessage("Please note this id for future use");
        ad.setPositiveButton("Next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ref.child("Tutor").child("" + uid).setValue(tutor);


                Toast.makeText(NewTutorRegistrationActivity.this, "will perform next operations here", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(this,M));
            }
        });
        ad.show();

        Toast.makeText(NewTutorRegistrationActivity.this, "should navigate to maps activity", Toast.LENGTH_SHORT).show();
    }

    public String generateUniqueId(String name, String phone)
    {
        String[] mnth = {"A","B","C","D","E","F","G","H","I","J","K","L"};
        String unique_id = null;

        DatePicker date = new DatePicker(this);
        int mth = date.getMonth();

        String ph = phone.substring(phone.length()-3);
        String na = name.substring(2,4);

        unique_id = "5"+""+na+""+ph+""+mnth[mth-1];
        return unique_id;
    }
}
