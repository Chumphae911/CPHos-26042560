package cph.singersw.cphos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText nameEditText, userEditText, passEditText;
    private Button button;
    private String nameString, userString, passwordString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initialView();
        controller();


    }//Main Medhot

    private void controller() {
        button.setOnClickListener(SignUpActivity.this);
    }

    private void initialView() {

        nameEditText = (EditText) findViewById(R.id.edtname);
        userEditText = (EditText) findViewById(R.id.edtUser);
        passEditText = (EditText) findViewById(R.id.edtPassword);
        button = (Button) findViewById(R.id.btnRegis);
    }

    @Override
    public void onClick(View v) {
        if (v == button) {

            //Get Value From Edit Text  (ดึงค่าจาก Edit  มาประมวลผล)
            nameString = nameEditText.getText().toString().trim();
            userString = userEditText.getText().toString().trim();
            passwordString = passEditText.getText().toString().trim();

            //Check Space   การสร้างเงื่อนไข nameStringม userString, passwordString

            if (nameString.equals("") || userString.equals("") || passwordString.equals("")) {

                //Have Spac
                Myalert myAlert = new Myalert(SignUpActivity.this);
                myAlert.myDialog("มีช่องว่าง", "กรุณากรอกทุกช่อง");
            }
            else {
                //No Space
                try {
                    PortData portData = new PortData(SignUpActivity.this);
                    portData.execute(userString, nameString, passwordString);

                    String result = portData.get();
                    Log.d("26AprilV1", "result ==>" + result);

                    if (Boolean.parseBoolean(result)) {
                        //True  short = ทำงาน 4 วินาที
                        Toast.makeText(SignUpActivity.this, "Upload Value To Server OK",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        //False
                        Toast.makeText(SignUpActivity.this, "Cannot Upload",
                                Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Log.d("26AprilV1", "e SignUpActivity ==>" + e.toString());
                }
            }
        }


    }
}// Main Class
