package cph.singersw.cphos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

    public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;

    //Explicit  พื้นที่ประกาสตัวแปร เพื่อจองหน่วยความจำทุกครั้ง
    private EditText userEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //Initial View  การผูกตัวแปร
        initialView();



    } //Main Method  เมทธอดหลัก

        private void initialView() {
            userEditText = (EditText) findViewById(R.id.edtUser);
            passwordEditText = (EditText) findViewById(R.id.edtPassword);
            textView = (TextView) findViewById(R.id.txtNewRegis);
            button = (Button) findViewById(R.id.btnLogin);

        }

    }//Main Class  คลาสหลัก
