package varma.com.zimstutions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chandu on 6/9/2016.
 */
public class TestDatabaseActivity extends AppCompatActivity
{
    private EditText editTextName;
    private EditText editTextAddress;
    private EditText editTextPhone;
    private EditText editTextEmail;
    private TextView textViewPersons;
    private Button buttonSave;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);

        Firebase.setAndroidContext(this);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        buttonSave = (Button) findViewById(R.id.buttonSave);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editTextPhone = (EditText)findViewById(R.id.editTextPhone);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);

        textViewPersons = (TextView) findViewById(R.id.textViewPersons);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating firebase object
                Firebase ref = new Firebase(Config.FIREBASE_URL);

                //Getting values to store
                String name = editTextName.getText().toString().trim();
                String address = editTextAddress.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();


                //Creating Person object
                Person person = new Person();
                //Adding values
                person.setName(name);
                person.setAddress(address);
                person.setEmail(email);
                person.setPhone(phone);

                //Storing values to firebase
                //ref.child("Person").setValue(person);
                //ref.child("User").push().

                //ref.push().child(""+phone).setValue(person);

                ref.child("Student").child(""+phone).setValue(person);
                //writeNewPost(name,address,name,address);

                //Value event listener for realtime data update
               /* ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                            //Getting the data from snapshot
                            Person person = postSnapshot.getValue(Person.class);

                            //Adding it to a string
                            String string = "Name: "+person.getName()+"\nAddress: "+person.getAddress()+"\n\n";

                            //Displaying it on textview
                            textViewPersons.setText(string);
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        System.out.println("The read failed: " + firebaseError.getMessage());
                    }
                }); */

            }
        });
    }
    private void writeNewPost(String userId, String username, String title, String body) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        Toast.makeText(this, "write new post", Toast.LENGTH_SHORT).show();
        String key = databaseReference.child("posts").push().getKey();
        Post post = new Post(userId, username, title, body);

        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/posts/" + key, postValues);
        childUpdates.put("/user-posts/" + userId + "/" + key, postValues);

        databaseReference.updateChildren(childUpdates);
    }
}
