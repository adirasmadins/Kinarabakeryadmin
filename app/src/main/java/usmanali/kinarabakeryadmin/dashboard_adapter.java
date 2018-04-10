package usmanali.kinarabakeryadmin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by SAJIDCOMPUTERS on 9/11/2017.
 */

public class dashboard_adapter extends RecyclerView.Adapter<dashboardviewholder> {
    int images[]={R.drawable.add_products,R.drawable.manage_orders};
    String titles[]={"Add Products","Manage Orders"};
    Boolean Islogin;
    public dashboard_adapter(Context context) {
        this.context = context;
    }

    Context context;
    @Override
    public dashboardviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_layout,parent,false);
        return new dashboardviewholder(v);
    }

    @Override
    public void onBindViewHolder(final dashboardviewholder holder,  int position) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Islogin = prefs.getBoolean("Islogin", false);
    holder.imageView.setImageResource(images[position]);
        holder.action_name.setText(titles[position]);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(holder.getAdapterPosition()==0&&Islogin){
                   context.startActivity(new Intent(context,add_products_activity.class));
               }else if (holder.getAdapterPosition()==1&&Islogin){
                   context.startActivity(new Intent(context,Show_orders_Activity.class));
               }else{
                   Toast.makeText(context,"Please Login First",Toast.LENGTH_LONG).show();
               }
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.length;
    }
}
class dashboardviewholder extends RecyclerView.ViewHolder{
ImageView imageView;
    CardView card;
    TextView action_name;
    public dashboardviewholder(View itemView) {
        super(itemView);
        imageView=(ImageView) itemView.findViewById(R.id.image);
        card=(CardView) itemView.findViewById(R.id.card);
        action_name=(TextView) itemView.findViewById(R.id.action_name);
    }
}