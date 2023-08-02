package com.himedia.androidshoppingmall;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    // 서버 URL 설정 ( PHP 파일 연동 )
    //private static final String URL ="http://gdkgate.dothome.co.kr/android/Login.php";
    final static private String URL = "http://3.37.214.236/android_001/Login.php";

    private Map<String, String> map;

    public LoginRequest(String member_id, String member_pw, Response.Listener<String> listener)
    {
        super(Method.POST, URL, listener, null);
        map = new HashMap<>();
        map.put("member_id", member_id);
        map.put("member_pw", member_pw);
    }

    @Override
    protected  Map<String, String> getParams() throws AuthFailureError
    {
        return map;
    }
}