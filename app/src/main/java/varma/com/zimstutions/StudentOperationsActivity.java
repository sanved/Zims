package varma.com.zimstutions;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.database.Query;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Chandu on 6/12/2016.
 */
public class StudentOperationsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    TextView tv,tv2;
    public String[] mPlanetTitles = {"menu 1","menu 2","menu 3","About US","Contact Us"};
    ListView listView;

    String authdata;

    EditText et_search;
    ViewFlipper viewFlipper;

    com.github.clans.fab.FloatingActionButton ffab,fab1,fab2,fab3;
    FloatingActionMenu fmenu;

    FloatingActionMenu materialDesignFAM;
    com.github.clans.fab.FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3,fab4;

    public static SharedPreferences sharedPreferences;
    LinearLayout ll;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*viewFlipper = (ViewFlipper)findViewById(R.id.view_flipper);

        viewFlipper.startFlipping();
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(3000); */

        ll = (LinearLayout)findViewById(R.id.ll1);
        listView = (ListView)findViewById(R.id.listView1);

        sharedPreferences = getSharedPreferences("count",MODE_PRIVATE);

        int count = sharedPreferences.getInt("count",0);
        if(count > 0)
        {
            displayRecentSearches(count);
        }


        tv = (TextView)findViewById(R.id.textView1);
        tv2 =(TextView)findViewById(R.id.textView4);

        et_search = (EditText)findViewById(R.id.et_search);

        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton1 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);

        fab4 = new com.github.clans.fab.FloatingActionButton(this);


        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked

            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked

            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked

            }
        });


        Intent i = getIntent();
        Bundle b = i.getExtras();
        final String uid = b.getString("uid");

        // listView = (ListView)findViewById(R.id.listView1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        Firebase ref = new Firebase("https://zims.firebaseio.com/Tutor/");
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    String data = retriveData(uid);
                    tv2.setText(""+data);
                    Toast.makeText(StudentOperationsActivity.this, "opened", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(StudentOperationsActivity.this, "closed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        navigationView.setNavigationItemSelectedListener(this);

        //TextView tv1 = (TextView)findViewById(R.id.textView4);


        //com.firebase.client.Query query = ref.orderByValue().equalTo("subject","science");

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
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //If a user is currently authenticated, display a logout menu
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
      }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            NewStudentLoginActivity newstudnet = new NewStudentLoginActivity();
            newstudnet.logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    } */



    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        int id = menuItem.getItemId();
        switch (id)
        {
            case R.id.nav_about:
            {
                startActivity(new Intent(this,AboutUsActivity.class));

                break;
            }
            case R.id.nav_contacs_us:
            {
                Toast.makeText(StudentOperationsActivity.this, "contact us", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.nav_my_tutuions:
            {
                Toast.makeText(StudentOperationsActivity.this, "my tutions", Toast.LENGTH_SHORT).show();
                break;

            }
            case R.id.nav_share:
            {
                Toast.makeText(StudentOperationsActivity.this, "share ", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.nav_logout:
            {
                startActivity(new Intent(this,AcitivtyMain.class));
                Toast.makeText(StudentOperationsActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                break;
            }

        }
        return false;
    }

    public String retriveData(String uid)
    {
        Firebase ref = new Firebase("https://zims.firebaseio.com/Student/"+uid);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("dataSnapshot", "" + dataSnapshot);
                authdata = ""+dataSnapshot;
                //Toast.makeText(NewStudentLoginActivity.this, "data " + dataSnapshot, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return authdata;
    }

    public void showSearchResult(View v)
    {
        Toast.makeText(StudentOperationsActivity.this, "search result", Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor se = sharedPreferences.edit();

        int count =sharedPreferences.getInt("count",0);

            se.putInt("count",count);
            se.putString("search"+count, et_search.getText().toString().trim());
        if(se.commit())
        {
            count++;
            Toast.makeText(StudentOperationsActivity.this, "c : "+count, Toast.LENGTH_SHORT).show();
            displayRecentSearches(count);
        }

    }
    public void displayRecentSearches(int count)
    {
        ll.setVisibility(View.VISIBLE);

        ArrayList aa = new ArrayList();
        try {
            for(int i = 1;i<=count;i++)
            {
                aa.add(""+sharedPreferences.getString("search"+i,"sorry"));
            }

        }
        catch (Exception e)
        {
            Toast.makeText(StudentOperationsActivity.this, "" + e, Toast.LENGTH_SHORT).show();
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,aa.toArray());
        listView.setAdapter(arrayAdapter);

    }



}
