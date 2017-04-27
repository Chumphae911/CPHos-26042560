package cph.singersw.cphos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

                } else {
                    //No Spece
                    Myalert myalert = new Myalert(MainActivity.this);
                    myalert.myDialog("Have Speace","Plese Fill All Every Blank");
                }
            }
        }


    }//Main Class  คลาสหลัก
