package varma.com.zimstutions;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Chandu on 5/30/2016.
 import android.widget.EditText;
 */
public class StudentRegistrationActivity extends AppCompatActivity
{
    EditText[] et;
    ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_registration);

        et = new EditText[5];

        et[0] = (EditText)findViewById(R.id.student_name);
        et[1] = (EditText)findViewById(R.id.student_phone);
        et[2] = (EditText)findViewById(R.id.student_email);

        et[3] = (EditText)findViewById(R.id.student_password);
        et[4] = (EditText)findViewById(R.id.student_confirm_password);

        arrayList = new ArrayList<String>();

    }
    public void onClickStudentRegister(View v)
    {
        if(et[3].getText().toString().trim().equals(et[4].getText().toString().trim()))
        {
            for(int i =0;i<et.length;i++)
            {
                arrayList.add(et[i].getText().toString().trim());
            }

            DatePicker dp = new DatePicker(this);
            int date = dp.getDayOfMonth();
            int month = dp.getMonth();

            TimePicker tm = new TimePicker(this);
            //int minute  = tm.getMinute();

            String email = et[2].getText().toString().trim();
            String sub_email = email.substring(0, 3);

            Random random = new Random();
            int random_number = random.nextInt(2000);

            String final_id = sub_email + ""+date +"" + ""+month + "" + random_number;

            SharedPreferences.Editor se = AcitivtyMain.sharedPreferences.edit();
            se.putString("stu_email",et[2].getText().toString().trim());
            se.putString("stu_password", et[3].getText().toString().trim());

            if(se.commit())
            {
                for (int i = 0; i < et.length; i++) {
                    et[i].setText("");
                }

                AlertDialog.Builder ad = new AlertDialog.Builder(this);
                ad.setTitle("Your unique id");

                ad.setMessage("" + final_id);

                ad.setPositiveButton("Continue", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        startActivity(new Intent(StudentRegistrationActivity.this, LoginActivity.class));
                    }
                });
                ad.show();
               // Toast.makeText(StudentRegistrationActivity.this, "Saved", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(StudentRegistrationActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
