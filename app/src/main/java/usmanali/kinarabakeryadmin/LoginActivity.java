package usmanali.kinarabakeryadmin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 */
public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.etusername) EditText username;
    @BindView(R.id.etpassword) EditText password;
    @BindView(R.id.textInputLayoutusername)  TextInputLayout textInputLayoutusername;
    @BindView(R.id.textInputLayoutpassword)  TextInputLayout textInputLayoutpassword;
    @BindView(R.id.toolbar) Toolbar toolbar;
    boolean Islogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Log In");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
         Islogin = prefs.getBoolean("Islogin", false);
    }
    @OnClick(R.id.loginbtn) public void login() {
        if (username.getText().toString().isEmpty())
            textInputLayoutusername.setError("Enter Username");
           else if (password.getText().toString().isEmpty())
                textInputLayoutpassword.setError("Enter Password");
             else if (!Islogin){
            customerlogin(username.getText().toString(),password.getText().toString());
            }else{
            Toast.makeText(LoginActivity.this,"You are already Logged in",Toast.LENGTH_LONG).show();
        }
        }
    public void customerlogin(String Username, String password){
        kinarabakeryservice service=apiclient.getClient().create(kinarabakeryservice.class);
        Call<ArrayList<user>> call=service.login(Username,password);
        final ProgressDialog pd=new ProgressDialog(LoginActivity.this);
        pd.setMessage("Please Wait");
        pd.setCancelable(false);
        pd.show();
        call.enqueue(new Callback<ArrayList<user>>() {
            @Override
            public void onResponse(Call<ArrayList<user>> call, Response<ArrayList<user>> response) {
                ArrayList<user> userdata=response.body();
                pd.dismiss();
                if(userdata.get(0).getStatus().equals("login Sucess")){
                    Toast.makeText(LoginActivity.this,"Login Sucess",Toast.LENGTH_LONG).show();
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                    prefs.edit().putBoolean("Islogin", true).commit();
                    prefs.edit().putString("Name",userdata.get(0).getName()).commit();
                    prefs.edit().putString("Username",userdata.get(0).getUsername()).commit();
                    prefs.edit().putString("Email",userdata.get(0).getEmail()).commit();
                    prefs.edit().putString("Address",userdata.get(0).getAddress()).commit();
                    prefs.edit().putString("Phone",userdata.get(0).getPhone()).commit();
                    prefs.edit().putString("Password",userdata.get(0).getPassword()).commit();
                    prefs.edit().putInt("Id",userdata.get(0).getId()).commit();
                    ActivityOptionsCompat optionsCompat= ActivityOptionsCompat.makeSceneTransitionAnimation(LoginActivity.this,null);
                    startActivity(new Intent(LoginActivity.this,MainActivity.class),optionsCompat.toBundle());
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_LONG).show();
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                    prefs.edit().putBoolean("Islogin", false).commit();
                    Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_LONG).show();
                    pd.dismiss();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<user>> call, Throwable t) {
                pd.dismiss();
                Log.e("Failure",t.toString() );
            }
        });
    }
    public void showstatus(String Status){
        Toast.makeText(LoginActivity.this,Status,Toast.LENGTH_LONG).show();
    }

}