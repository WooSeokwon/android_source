package com.example.wsw.management;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    //회원 등록과 관련한 Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText idText = (EditText) findViewById(R.id.idText); //회원 등록 디자인에 있는 id값이 온다
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final EditText nameText = (EditText) findViewById(R.id.nameText);
        final EditText ageText = (EditText) findViewById(R.id.ageText);

        Button registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() { //등록 버튼 리스너
            @Override
            public void onClick(View v) {
                final String userID = idText.getText().toString(); //각 객체에서 data를 가져온다.
                final String userPassword = passwordText.getText().toString();
                final String userName = nameText.getText().toString();
                final int userAge = Integer.parseInt(ageText.getText().toString()); //string 값을 int 값으로 가져온다.
                //윗 라인에도 예외처리가 필요하다(문자를 입력할 수 없거나, 숫자만 입력하게 하는 것)

                Response.Listener<String> responseListener = new Response.Listener<String>() { //responseListener를 통해 php - mysql을 통한 데이터 교환
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                           // boolean success = jsonResponse.getBoolean("success");
                            //boolean overlap = jsonResponse.getBoolean("overlap");
                            if (jsonResponse.getBoolean("success")) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("회원 등록 성공")
                                        .setPositiveButton("확인", null)
                                        .create()
                                        .show();
                                finish(); //변형 코드
                                /*Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent); - 원 코드*/
                            } /*else if(jsonResponse.getBoolean("overlap")){ //아이디 중복 확인은 버튼을 통해 구현하자
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("아이디 중복")
                                        .setNegativeButton("실패",null)
                                        .create()
                                        .show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            }*/
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("회원 등록 실패")
                                        .setNegativeButton("실패", null)
                                        .create()
                                        .show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(userID, userPassword, userName, userAge, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}
