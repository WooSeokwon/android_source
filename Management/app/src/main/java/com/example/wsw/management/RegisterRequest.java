package com.example.wsw.management;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wsw on 2017-01-19.
 */

public class RegisterRequest extends StringRequest{

    final static private String URL = "http://pplesb2015.ivyro.net/Register.php";//파일을 매칭
    private Map<String, String> parameters; //Map 추가

    //생성자 만들기
    public RegisterRequest(String userID, String userPassword, String userName, int userAge, Response.Listener<String> listener){ //Response 관련한 변수 추가
        super(Method.POST, URL, listener, null); //해당 URL에 POST 방식으로 전송
        parameters = new HashMap<>(); //HASHMAP을 이용해서 초기화
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userName", userName);
        parameters.put("userAge", userAge+"");
        //parameters.put("userAge", userAge + ""); //뒤에 + ""함으로써 문자열로 변환
    }

    @Override
    public Map<String, String> getParams(){ //현재 가지고 있는 paramerer 반환
        return parameters;
    }
}
