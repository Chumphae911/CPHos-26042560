package cph.singersw.cphos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private TextView nameTextView, dateTextView, detailTextView, receiveTextView;
    private String qrCordString;
    private String tag ="28AprilV1";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initailView();

        controller();

        //Get Value From Tntent
        getValueFromTntent();

        //Show View
        showView();

    }//Main Method

    private void showView() {

        MyConstant myConstant = new MyConstant();
        String[] columnProduct = myConstant.getColumnProduct();
        String urlPHP = myConstant.getUrlGetProductWhereQR();

        try {

            GetProductWhereQR getProductWhereQR = new GetProductWhereQR(DetailActivity.this);
            getProductWhereQR.execute(columnProduct[2], qrCordString, urlPHP);

            String strJSON = getProductWhereQR.get();
            Log.d(tag,"e showView ==>"+ strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            String[] resultString = new String[columnProduct.length];
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            for (int i=0;i<resultString.length;i++){
                resultString[i] = jsonObject.getString(columnProduct[i]);
                Log.d(tag, "result("+ i +") ==>" + resultString[i]);
            }

            nameTextView.setText(resultString[1]);
            dateTextView.setText(resultString[5]);
            detailTextView.setText(resultString[4]);
            receiveTextView.setText(findNameReceive(resultString[3]));


        } catch (Exception e) {
            Log.d(tag, "e showView ==>" + e.toString());
        }
    }

    private String findNameReceive(String idReceive) {

        String tag2 = "28AprilV2";
        MyConstant myConstant = new MyConstant();

        try {

            GetProductWhereQR getProductWhereQR = new GetProductWhereQR(DetailActivity.this);
            getProductWhereQR.execute("id", idReceive, myConstant.getUrlGetUserWhereID());
            String strJSON = getProductWhereQR.get();
            Log.d(tag2, "JSON ==>" + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            return jsonObject.getString("Name");


        } catch (Exception e) {
            Log.d(tag2, "e findName ==>" + e.toString());
            return null;
        }

    }


    private void getValueFromTntent() {
        qrCordString = getIntent().getStringExtra("QRcode");
        Log.d(tag, "QRcode ==>" + qrCordString);
    }

    private void controller() {

        imageView.setOnClickListener(DetailActivity.this);

    }

    private void initailView() {
        imageView = (ImageView) findViewById(R.id.imvBack);
        nameTextView = (TextView) findViewById(R.id.txtName);
        dateTextView = (TextView) findViewById(R.id.txtDate);
        detailTextView = (TextView) findViewById(R.id.txtDetail);
        receiveTextView = (TextView) findViewById(R.id.txtReceiveName);

    }


    @Override
    public void onClick(View v) {

        if (v == imageView) {
            finish();
        }


    }
}//Main Class
