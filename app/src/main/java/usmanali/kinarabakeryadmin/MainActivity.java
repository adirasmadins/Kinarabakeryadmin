package usmanali.kinarabakeryadmin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
@BindView(R.id.dashboard) RecyclerView dashboard;
    Boolean Islogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dashboard.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        dashboard.setAdapter(new dashboard_adapter(MainActivity.this));
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Islogin = prefs.getBoolean("Islogin", false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(!Islogin) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
        }else if(Islogin){
            getMenuInflater().inflate(R.menu.authenticated_admin_menu, menu);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.login) {
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }else if (id==R.id.logout){
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
            prefs.edit().remove("Islogin").commit();
            prefs.edit().remove("Name").commit();
            prefs.edit().remove("Username").commit();
            prefs.edit().remove("Email").commit();
            prefs.edit().remove("Address").commit();
            prefs.edit().remove("Phone").commit();
            prefs.edit().remove("Password").commit();
            prefs.edit().remove("Id").commit();
            startActivity(new Intent(MainActivity.this,MainActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
