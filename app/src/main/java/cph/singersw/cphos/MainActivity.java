package cph.singersw.cphos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private Button button;
        private String userString, passwordString;

    //Explicit  พื้นที่ประกาสตัวแปร เพื่อจองหน่วยความจำทุกครั้ง
    private EditText userEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initial View  การผูกตัวแปร
        initialView();


        //Controller  การทำคอนโทรลเลอร์เชื่อม
        controlller();



    } //Main Method  เมทธอดหลัก

        private void controlller() {
            textView.setOnClickListener(MainActivity.this);
            button.setOnClickListener(MainActivity.this);
        }

        private void initialView() {
            userEditText = (EditText) findViewById(R.id.edtUser);
            passwordEditText = (EditText) findViewById(R.id.edtPassword);
            textView = (TextView) findViewById(R.id.txtNewRegis);
            button = (Button) findViewById(R.id.btnLogin);

        }

        @Override
        public void onClick(View v) {

        //For TextView  (อยู่ในเทควิว)
            if (v == textView) {
                //Intent to SignUp  (ย้ายการทำงานจากหน้า Intent to SignUp)
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }

            //For Button
            if (v == button) {
                //Ger Value From EdiText
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                //Check Space
                if (userString.equals("") || passwordString.equals("")) {
                    //Have Space
                    Myalert myalert = new Myalert(MainActivity.this);
                    myalert.myDialog("Have Speace","Plese Fill All Every Blank");

                } else {
                    //No Spece
                    checkUserAnPass();
                }
            }
        }

        private void checkUserAnPass() {
            try {
                String urlJSON="http://swiftcodingthai.com/cph/getDataCPHos.php";
                boolean b = true;
                String[] columnString = new String[]{"id","Name","User","Password"};
                String[] loginString = new  String[columnString.length];


                GetData getData = new GetData(MainActivity.this);
                getData.execute(urlJSON);
                String strJSON = getData.get();
                Log.d("27AprilV1", "e checkUser ==>" +strJSON);

                JSONArray jsonArray = new JSONArray(strJSON);

                for (int i=0;i<jsonArray.length();i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (userString.equals(jsonObject.get(columnString[2]))) {
                        b=false;
                        for (int i1=0;i1<columnString.length;i1++) {
                            loginString[i1] = jsonObject.getString(columnString[i1]);
                            Log.d("27AprilV1","loginString(" + i1 + ") ==>" + loginString[i1]);
                        }
                    }
                }

            } catch (Exception e) {
                Log.d("27AprilV1", "e checkUser ==>" + e.toString());
            }
        }


    }//Main Class  คลาสหลัก
