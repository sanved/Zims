package varma.com.zimstutions;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.firebase.client.Firebase;

/**
 * Created by Chandu on 6/10/2016.
 */
public class NewStudentRegistrationActivity extends AppCompatActivity
{
    String uid = null;

    EditText et1, et2, et3, et4,et5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_registration_layout_new);

        et1 = (EditText)findViewById(R.id.stu_et_name);
        et2 = (EditText)findViewById(R.id.stu_et_parent);
        et3 = (EditText)findViewById(R.id.stu_et_phone);
        et4 = (EditText)findViewById(R.id.stu_et_school);
        et5 = (EditText)findViewById(R.id.stu_et_class);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        uid = b.getString("uid");

    }
    public void onClickStudentRegistration(View v)
    {
        String name = et1.getText().toString().trim();
        String parent_name = et2.getText().toString().trim();
        String phone = et3.getText().toString().trim();
        String school = et4.getText().toString().trim();
        String cls = et5.getText().toString().trim();

        final Student student = new Student();

        student.setName(name);
        student.setParent(parent_name);
        student.setPhone(phone);
        student.setSchool(school);
        student.setCls(cls);

        final String unique_id = generateUniqueId(name,phone);

        student.setUnique_id(unique_id);
        final Firebase ref = new Firebase(Config.FIREBASE_URL);

        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Your unique id is " + unique_id);
        ad.setMessage("Please note this id for future use");
        ad.setPositiveButton("Next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ref.child("Student").child("" + uid).setValue(student);


            }
        });
        ad.show();

    }

    public String generateUniqueId(String name, String phone)
    {
        String[] mnth = {"A","B","C","D","E","F","G","H","I","J","K","L"};
        String unique_id = null;

        DatePicker date = new DatePicker(this);
        int mth = date.getMonth();
        System.out.println("Month is" +mth);

        String ph = phone.substring(phone.length() - 3);
        String na = name.substring(2,4);

        unique_id = "7"+""+na+""+ph+""+mnth[mth-1];
        return unique_id;
    }
}
