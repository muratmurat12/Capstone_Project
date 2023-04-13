package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.paymentsheet.PaymentSheet;
import com.stripe.android.paymentsheet.PaymentSheetResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Payment extends AppCompatActivity {

    PaymentSheet paymentSheet;
    String paymentIntentClientSecret;
    PaymentSheet.CustomerConfiguration configuration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        fetchApi();
        Button button = findViewById(R.id.pay_now);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (paymentIntentClientSecret != null)
                 paymentSheet.presentWithPaymentIntent(paymentIntentClientSecret,
                         new PaymentSheet.Configuration("ShoppingList",configuration));
                else
                    Toast.makeText(Payment.this, "API Loading .....", Toast.LENGTH_LONG).show();
            }
        });
        paymentSheet = new PaymentSheet(this,this::onPaymentSheetResult);
    }
    private void onPaymentSheetResult(final PaymentSheetResult paymentSheetResult){
        if (paymentSheetResult instanceof PaymentSheetResult.Canceled){
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
        }
         if (paymentSheetResult instanceof PaymentSheetResult.Failed){
            Toast.makeText(this, ((PaymentSheetResult.Failed) paymentSheetResult).getError().getMessage(), Toast.LENGTH_SHORT).show();
        }
          if (paymentSheetResult instanceof PaymentSheetResult.Completed){
            Toast.makeText(this, "Payment success", Toast.LENGTH_SHORT).show();
        }

    }

    public void fetchApi(){
        RequestQueue queue = Volley.newRequestQueue(this);
        //String url ="curl https://api.stripe.com/v1/charges -u sk_test_51MZzUpDyrcLl263SBjTcXI6SH0ABgWTDRQW1Aep5k05mCt4mc0yhxfj5vaAaYHGzOt40LiVgWNp4xozohlGRkl7U00qUC803KV";
        String url ="https://api.jsonbin.io/v3/b/6435c73eace6f33a22093f79";
          // String url ="http://localhost:63343/stripe-android-api/index.php?_ijt=vnn7bf390g50krb3qbs61gkf0g&_ij_reload=RELOAD_ON_SAVE";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            configuration = new PaymentSheet.CustomerConfiguration(
                                    jsonObject.getString("customer"),
                                    jsonObject.getString("ephemeralKey")
                            );
                            paymentIntentClientSecret = jsonObject.getString("paymentIntent");
                                    PaymentConfiguration.init(getApplicationContext(),jsonObject.getString("publishableKey"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                 error.printStackTrace();
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> paramV = new HashMap<>();
                paramV.put("authKey", "abc");
                return paramV;
            }
        };
        queue.add(stringRequest);
    }
}