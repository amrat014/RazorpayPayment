package com.oneroofit.razorpaypayment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {

    //Initialize variable;
    Button btnpay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnpay = findViewById(R.id.btn_pay);

        //Initialize amount

        String sAmount = "100";

        //Convert and Round off

        int amount =Math.round(Float.parseFloat(sAmount) * 100);

        btnpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize the Razorpay Checkout

                Checkout checkout = new Checkout();
                //set Key id
             checkout.setKeyID("rzp_test_wGgwoZxCkEV4Jc");

             //Set Image
                checkout.setImage(R.drawable.rp);

             //initialize json object

                JSONObject object = new JSONObject();


                try {
                    //Put name
                    object.put("name","AM4U Motivation");

                    //Put description
                    object.put("description","Test Payment");
                    //Put theme Color
                    object.put("theme.color","#0093DD");
                    //Put Currency unit
                    object.put("Currency","INR");
                    //Put Amount
                    object.put("amount",amount);
                    //Put Mobile number
                    object.put("prefill.contact","7990327163");
                    //Put Email
                    object.put("prefill.email","amrat014@gmail.com");
                    //open razorpay checkout Activity
                    checkout.open(MainActivity.this,object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    public void onPaymentSuccess(String s) {
        //Initialize alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //set Title
        builder.setTitle("Payment ID");

        //Set message
        builder.setMessage(s);

        //Show alert dialog

        builder.show();

    }

    @Override
    public void onPaymentError(int i, String s) {
        //Display toast
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}