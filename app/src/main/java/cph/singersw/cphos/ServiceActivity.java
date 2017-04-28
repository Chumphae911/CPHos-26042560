package cph.singersw.cphos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private ImageView imageView;
    private ListView listView;
    private String[] loginString, nameStrings, dateStrings,detailStrings, qrcordString;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);


        initial();

        //Get Value Form Intent  (การรับค่าจาก intent)
        getValueFromIntent();   //

        createLiView();

        controller();

    }// Main Medhod

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            String result = data.getStringExtra("SCAN_RESULT");
            Log.d("28AprilV3", "Result From Scan ==>" + result);

            Intent intent = new Intent(ServiceActivity.this, DetailActivity.class);
            intent.putExtra("QRcode", result);
            startActivity(intent);

        }

    }

    private void controller() {
        imageView.setOnClickListener(ServiceActivity.this);
    }

    private void createLiView() {

        final String tag = "27AprilV2";
        String urlPHP = "http://swiftcodingthai.com/cph/getProduct.php";

        try {
            GetData getData = new GetData(ServiceActivity.this);
            getData.execute(urlPHP);
            String strJSON = getData.get();
            Log.d(tag, "JSON ==>" + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            nameStrings = new String[jsonArray.length()];
            dateStrings = new String[jsonArray.length()];
            detailStrings = new String[jsonArray.length()];
            qrcordString = new String[jsonArray.length()];

            for (int i=0; i<jsonArray.length();  i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameStrings[i] = jsonObject.getString("Name");
                dateStrings[i] = jsonObject.getString("Date_Receive");
                detailStrings[i] = jsonObject.getString("Description");
                qrcordString[i] = jsonObject.getString("QR_code");

            }
            MyAdapter myAdapter = new MyAdapter(ServiceActivity.this, nameStrings,
                    dateStrings, detailStrings);
            listView.setAdapter(myAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d(tag, "You Click ==>" + qrcordString[position]);

                    Intent intent = new Intent(ServiceActivity.this, DetailActivity.class);
                    intent.putExtra("QR_code", qrcordString[position]);
                    startActivity(intent);
                }
            });


        } catch (Exception e) {
            Log.d(tag, "e createListView ==>" + e.toString());
        }
    }

    private void getValueFromIntent() {
        loginString = getIntent().getStringArrayExtra("Login");
        textView.setText(loginString[1]);
    }

    private void initial() {
        textView = (TextView) findViewById(R.id.txtName);
        imageView = (ImageView) findViewById(R.id.imvQR);
        listView = (ListView) findViewById(R.id.livProduct);

    }

    @Override
    public void onClick(View v) {

        String tag = "28AprilV3";

        if (v == imageView) {

            try {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.putExtra("SCAN_MODE","QR_CODE_MODE");
                startActivityForResult(intent, 10);

            } catch (Exception e) {
                Log.d(tag, "e onClick ==>" + e.toString());

            }
        }

    }
}//Main Class
