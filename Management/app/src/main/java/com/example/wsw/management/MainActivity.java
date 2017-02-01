package com.example.wsw.management;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView idText = (TextView) findViewById(R.id.idText);
        TextView passwordText = (TextView) findViewById(R.id.passwordText);
        TextView welcomeMessage = (TextView) findViewById(R.id.welcomMessage);
        Button managementButton = (Button) findViewById(R.id.managementButton);
        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String userPassword = intent.getStringExtra("userPassword");
        String message = "환영합니다" + userID + "님!";

        idText.setText(userID);
        passwordText.setText(userPassword);
        welcomeMessage.setText(message);

        if(!userID.equals("admin")){
            //managementButton.setEnabled(false);
            managementButton.setVisibility(View.GONE);
        }

        managementButton.setOnClickListener(new View.OnClickListener() { //회원 관리에 대한 버튼
            @Override
            public void onClick(View v) {
                new BackgroundTask().execute(); //클래스의 실행
            }
        });
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> //내부 클래스. 회원 목록 가져오기
    {
        String target;

        @Override
        protected void onPreExecute(){ //특정 웹사이트의 파일을 초기화
            target = "http://pplesb2015.ivyro.net/List.php";
        }
        @Override
        protected String doInBackground(Void... voids){ // 실질적으로 회원 목록 가져오기
            try{
                URL url = new URL(target); //접속 위해 URL 객체
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); //자바에서 웹 파싱할 때 사용되는것과 동일
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)); //하나씩 읽어오기
                String temp;  //매 열마다 입력을 받을 수 있게
                StringBuffer stringBuilder = new StringBuffer(); //매 열마다 읽어서 StrungBuilder에 넣는다
                while((temp = bufferedReader.readLine()) != null){//다음 라인을 읽은 후 모든 열을 읽고
                    stringBuilder.append(temp + "\n"); //temp를 추가
                }
                bufferedReader.close(); //닫아준다
                inputStream.close(); //닫아준다
                httpURLConnection.disconnect(); //http도 닫아준다.
                return stringBuilder.toString().trim(); //해당 문자열의 집합을 반환
            }catch(Exception e){ //예외처리
                e.printStackTrace();
            }
            return null; //오류 발생 시 null값 반환
        }

        @Override
        public void onProgressUpdate(Void... values){ //상속만 받아주고 상용하지는 않는다
            super.onProgressUpdate(values);
        }

        @Override
        public void onPostExecute(String result){ //모든 파싱이 끝난 후 managementActivity로 전환
            Intent intent = new Intent(MainActivity.this, ManagementActivity.class);
            intent.putExtra("userList", result); //파싱한 결과를 그대로 전달
            MainActivity.this.startActivity(intent);

        }
    }
}
