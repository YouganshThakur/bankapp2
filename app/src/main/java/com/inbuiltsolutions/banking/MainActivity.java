package com.inbuiltsolutions.banking;

import android.app.ProgressDialog;
import android.os.RecoverySystem;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    String secretCode="bT3gX2dQ5eS5pX3cS0lV5kG5sR1bM8hV0fU6tA4oD1pH1qT4vB";
    String clientId="7cf68230-c909-4211-9f96-979c9990d3ae";
    String accountId="309002003225";
    String corpId="HACKTEST";
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_modes);
        progressDialog=new ProgressDialog(this);
    }
    public void testapi(View view) {

        String url="https://api.us.apiconnect.ibmcloud.com/rbl/rblhackathon/rbl/v1/accounts/balance/query?client_id="+clientId+"&client_secret="+secretCode;
        String requestBody="{\"getAccountBalanceReq\":{\"Header\":{\"TranID\":\"7367580155117568\",\"Corp_ID\":\"HACKTEST\",\"Approver_ID\":\"3642027054989312\"},\"Body\":{\"AcctId\":\"5018867703259920\"},\"Signature\":{\"Signature\":\"12345\"}}}";
        runApi(url,requestBody);
    }

    void runApi(String url,String requstBody)
    {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,requstBody);
        Request request=null;
        request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("accept", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String responseData = response.body().string();

                // Run view-related code back on the main thread
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView myTextView = (TextView) findViewById(R.id.responseTextView);
                        myTextView.setText(responseData);
                    }
                });
            }


        });

    }




}
