package com.example.registrationpractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    DatabaseHelper db;
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        db = new DatabaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mTextCnfPassword = (EditText)findViewById(R.id.edittext_cnf_password);
        mButtonRegister = (Button)findViewById(R.id.button_register);
        mTextViewLogin = (TextView)findViewById(R.id.textview_register);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainIntent = new Intent(SignUp.this,MainActivity.class);
                startActivity(MainIntent);
            }
        });
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cfm_pwd = mTextCnfPassword.getText().toString().trim();

                if (pwd.equals(cfm_pwd)){
                    long val = db.addUser(user,pwd);
                    if(val > 0 ){
                        Toast.makeText(SignUp.this,"Registration Completed",Toast.LENGTH_SHORT).show();
                        Intent movetoMain = new Intent(SignUp.this,MainActivity.class);
                        startActivity(movetoMain);
                    }
                    else {
                        Toast.makeText(SignUp.this,"Registration Failure",Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(SignUp.this,"Passwords are unmatched",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
