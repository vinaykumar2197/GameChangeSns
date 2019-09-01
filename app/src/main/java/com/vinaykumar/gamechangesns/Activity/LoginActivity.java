package com.vinaykumar.gamechangesns.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vinaykumar.gamechangesns.R;

public class LoginActivity extends AppCompatActivity {


    // 3 rows country name , capital , button learn more >  summary (  3 fields >region , sub region & population )
    private EditText etUserName , etPassword ;
    private Button btSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initCreate();
    }

    private void initCreate(){
        etUserName = (EditText) findViewById(R.id.et_user_name);
        etPassword = (EditText) findViewById(R.id.et_password);
        btSignIn = (Button) findViewById(R.id.bt_sign_in);

//        validateInputs();

        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateInputs();
            }
        });
    }

    private void validateInputs(){
        String userName = etUserName.getText().toString();
        String password = etPassword.getText().toString();

        if(TextUtils.isEmpty(userName)){
            Toast.makeText(getApplicationContext(),"Kindly enter your user name",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"Kindly enter your password",Toast.LENGTH_LONG).show();
            return;
        }
        // username =
        if(userName.equals("abc") && password.equals("123")){
            Intent intent = new Intent(getApplicationContext(),ListActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(),"Invalid credentials",Toast.LENGTH_LONG).show();
        }
    }
}
