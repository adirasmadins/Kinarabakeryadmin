package usmanali.kinarabakeryadmin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by SAJIDCOMPUTERS on 9/10/2017.
 */

public class orders_list_adapter extends RecyclerView.Adapter<viewholder> {
    ArrayList<orders> ordersArrayList;
    Context context;

    public orders_list_adapter(ArrayList<orders> ordersArrayList, Context context) {
        this.ordersArrayList = ordersArrayList;
        this.context = context;
    }

    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.show_orders_layout,parent,false);
        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(viewholder holder, int position) {
        final orders order=ordersArrayList.get(position);
        holder.order_price.setText("Rs "+order.getPrice());
        holder.order_id.setText("Order Number "+String.valueOf(order.getOrderid()));
        holder.order_date.setText(order.getOrderdatetime());
         holder.order_details.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent i=new Intent(context,Show_order_details.class);
                 i.putExtra("order_id",order.getOrderid());
                 i.putExtra("order_datetime",order.getOrderdatetime());
                 i.putExtra("order_name",order.getName());
                 i.putExtra("order_username",order.getUsername());
                 i.putExtra("order_email",order.getEmail());
                 i.putExtra("order_address",order.getAddress());
                 i.putExtra("order_price",order.getPrice());
                 i.putExtra("order_items",order.getItems());
                 i.putExtra("order_phone",order.getPhone());
                 context.startActivity(i);
             }
         });
    }

    @Override
    public int getItemCount() {
        return ordersArrayList.size();
    }
}
class viewholder extends RecyclerView.ViewHolder{
TextView order_date,order_id,order_price,order_details,order_status;
    public viewholder(View itemView) {
        super(itemView);
        order_date=(TextView) itemView.findViewById(R.id.order_date);
        order_id=(TextView) itemView.findViewById(R.id.order_id);
        order_price=(TextView) itemView.findViewById(R.id.order_price);
        order_details=(TextView) itemView.findViewById(R.id.order_detail);
        order_status=(TextView) itemView.findViewById(R.id.order_status);
    }
}
