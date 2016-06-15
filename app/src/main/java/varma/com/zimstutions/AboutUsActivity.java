package varma.com.zimstutions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Chandu on 6/12/2016.
 */
public class AboutUsActivity extends AppCompatActivity
{
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us_layout);

        tv = (TextView)findViewById(R.id.tv_about_us);
        Firebase ref = new Firebase("https://zims.firebaseio.com/aboutus");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String jsonData = "" + dataSnapshot;
                //Toast.makeText(StudentOperationsActivity.this, "name : " + example.get8b96e522A36147c4871091a3b308a667().getName(), Toast.LENGTH_SHORT).show();


                //Example ex = new Gson().fromJson(jsonData,Example.class);
                //Toast.makeText(StudentOperationsActivity.this, "json " + ex.getBd8dad94E5ed4d88A1b2513c7b74e377().getExperience(), Toast.LENGTH_SHORT).show();

                tv.setText("" + dataSnapshot.getValue());
                //dataSnapshot.getValue().equals("subject");
                Log.e("subject", "" + dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
}
