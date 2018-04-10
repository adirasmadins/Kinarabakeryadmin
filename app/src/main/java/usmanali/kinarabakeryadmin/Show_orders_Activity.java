package usmanali.kinarabakeryadmin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Show_orders_Activity extends AppCompatActivity {

    @BindView(R.id.orderslist) RecyclerView orderlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_orders_);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        orderlist.setLayoutManager(new LinearLayoutManager(Show_orders_Activity.this));
        track();
    }
      public void track(){
         kinarabakeryservice service=apiclient.getClient().create(kinarabakeryservice.class);
          Call<ArrayList<orders>> call =service.get_all_orders();
          final ProgressDialog pd=new ProgressDialog(Show_orders_Activity.this);
          pd.setMessage("Please Wait");
          pd.setCancelable(true);
          pd.show();
          call.enqueue(new Callback<ArrayList<orders>>() {
              @Override
              public void onResponse(Call<ArrayList<orders>> call, Response<ArrayList<orders>> response) {
                  ArrayList<orders> ordersdetail=response.body();
                  pd.dismiss();
                  if(ordersdetail.size()>0) {
                      orderlist.setAdapter(new orders_list_adapter(ordersdetail, Show_orders_Activity.this));
                  }else{
                      Toast.makeText(Show_orders_Activity.this,"No Order history found",Toast.LENGTH_LONG).show();
                  }
              }

              @Override
              public void onFailure(Call<ArrayList<orders>> call, Throwable t) {
                  Log.e("Ordertracking",t.toString());
              }
          });
      }
}
