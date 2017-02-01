package com.example.wsw.registration;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wsw on 2017-01-31.
 */

public class LoginRequest extends StringRequest{
    final static private String URL = "http://pplesb2015.ivyro.net/UserLogin.php";
    private Map<String, String> parameters;

    public LoginRequest(String userID, String userPassword, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
    }

    @Override
    public Map<String, String> getParams(){ //현재 가지고 있는 paramerer 반환
        return parameters;
    }
}
