package usmanali.kinarabakeryadmin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Show_order_details extends AppCompatActivity {
    @BindView(R.id.name)TextView name;
    @BindView(R.id.username) TextView username;
    @BindView(R.id.address) TextView address;
    @BindView(R.id.email) TextView email;
    @BindView(R.id.phone) TextView phone;
    @BindView(R.id.items) TextView items;
    @BindView(R.id.price) TextView price;
    @BindView(R.id.orderdatetime) TextView orderdatetime;
    @BindView(R.id.orderid) TextView orderid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_order_details);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        name.setText(getIntent().getStringExtra("order_name"));
        username.setText(getIntent().getStringExtra("order_username"));
        address.setText(getIntent().getStringExtra("order_address"));
        email.setText(getIntent().getStringExtra("order_email"));
        phone.setText(getIntent().getStringExtra("order_phone"));
        items.setText(getIntent().getStringExtra("order_items"));
        price.setText("Rs "+getIntent().getStringExtra("order_price"));
        orderdatetime.setText(getIntent().getStringExtra("order_datetime"));
        orderid.setText(String.valueOf(getIntent().getIntExtra("order_id",0)));
    }

}
