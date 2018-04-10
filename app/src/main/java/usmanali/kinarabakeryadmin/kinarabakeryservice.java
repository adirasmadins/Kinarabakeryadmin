package usmanali.kinarabakeryadmin;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by SAJIDCOMPUTERS on 4/13/2017.
 */

public interface kinarabakeryservice {
    @FormUrlEncoded
    @POST("insert_products_to_db.php")
    Call<String> insertproducts(@Field("productname") String productname, @Field("price") String price, @Field("catorgery") String catorgery, @Field("quantity") String quantity, @Field("image") String image, @Field("Weight") String product_weight);
    @GET("get_all_orders.php")
    Call<ArrayList<orders>> get_all_orders();
    @FormUrlEncoded
    @POST("adminlogin.php")
    Call<ArrayList<user>> login(@Field("Username")String Username,@Field("Password")String Password);
}
