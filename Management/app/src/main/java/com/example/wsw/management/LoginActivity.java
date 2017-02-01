package com.example.wsw.management;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //객체 각각의 id를 가져온다
        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final Button loginButton = (Button) findViewById(R.id.loginButton);
        final TextView registerButton = (TextView) findViewById(R.id.registerButton);

        //register Button을 클릭 시 이벤트
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class); //intent를 통해 RegisterActivity로 이동한다
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        //login Button 클릭 시 - php - muslq과 연동하여 아이디 비밀번호 확인
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userID = idText.getText().toString(); //입력된 아이디와 비밀번호 가져옴
                final String usePassword = passwordText.getText().toString();
                Response.Listener<String> responseListener =  new Response.Listener<String>() {  //이 리스너는 LoginRequest에 파라미터로 사용
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success"); //Json을 통해 success를 전송받았는지 bool값 return
                            if(success){ //만약 success가 true 라면
                                String userID = jsonResponse.getString("userID"); //userID가져오고
                                String userPassword = jsonResponse.getString("userPassword"); //userPassword를 가져오고
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class); //intent 선언하고
                                intent.putExtra("userID", userID); //intent에다 userID와
                                intent.putExtra("userPassword", userPassword); //userPassword 보낸 후
                                LoginActivity.this.startActivity(intent); //화면 전환
                            }else{ //아니라면(아이디와 비밀번호가 없다면)
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("로그인에 실패하셨습니다.")
                                        .setNegativeButton("다시 시도", null)
                                        .create()
                                        .show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID, usePassword, responseListener);  //위의 responseListener을 파라미터로!
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this); //큐에 volley를 통해 넣음
                queue.add(loginRequest); //queue add!
            }
        });
        //loginButton.setOnClickListener(new View.OnClickListener(){});
    }
}
