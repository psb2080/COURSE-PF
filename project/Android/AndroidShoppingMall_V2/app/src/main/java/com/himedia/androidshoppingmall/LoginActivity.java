package com.himedia.androidshoppingmall;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.himedia.androidshoppingmall.Data.PreferenceManager;
import com.himedia.androidshoppingmall.Data.UserDBHelper;
import com.himedia.androidshoppingmall.Fragment.MyFragment;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private Animation logoAni; // 위치 이동
    private Animation loginFormAni; // 투명도 조절
    private ImageView imgView;
    private LinearLayout loginForm;
    private UserDBHelper dbHelper;
    private PreferenceManager pManager;

    private EditText id;
    private EditText pw;

    private Button loginTv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = UserDBHelper.getInstance(getApplicationContext());
        pManager = new PreferenceManager();

        imgView = findViewById(R.id.logoImg);
        loginForm = findViewById(R.id.loginfrom_view);
        id = findViewById(R.id.idEt);
        pw = findViewById(R.id.pwEt);


        startAnime() ;
        loginCheck() ;


    }

    private void startAnime() {
        logoAni = new TranslateAnimation(0, 0, 0, -250); // from 어디서 to 어디까지 이동할건지. 가운데를 중심으로 위, 왼쪽: - 아래, 오른쪽: +
        logoAni.setDuration(2000); // 지속시간
        logoAni.setFillAfter(true); // 이동 후 이동한 자리에 남아있을건지
        logoAni.setStartOffset(1500); // 딜레이
        logoAni.setInterpolator(new AccelerateDecelerateInterpolator()); // interpolator 설정. AccelerteDecelerate : 시작지점에 가속했다 종료시점에 감속

        loginFormAni = new AlphaAnimation(0, 1);
        loginFormAni.setDuration(1000);
        loginFormAni.setStartOffset(3000);

        imgView.setAnimation(logoAni); // 애니메이션 세팅
        loginForm.setAnimation(loginFormAni);
    }


    public void onLogin(View v) {
        // 로그인 id, pw 확인 후 일치 확인, 이후 main Activity로 이동
        if(!accountCheck()) {
            return;
        }

        pManager.setString(this, "user_id", String.valueOf(id.getText()));

        setEmptyEt();
        startMainActivity();

    }

    public void onRegister(View v) {
        // register Activity로 이동
        setEmptyEt();

        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void loginCheck() {
        String loginId = pManager.getString(this, "user_id");

        if(loginId.length() != 0) { // preference가 비어있지 않으면 바로 Main실행.
            startMainActivity();
        }
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private boolean accountCheck() {
        //db에 접근해서 id, pw 확인 후 일치 시 true, 불일치시 false return.
        //EditText에 현재 입력되어 있는 값을 가져온다.
        String member_id = id.getText().toString();
        String member_pw = pw.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    //Register.php에서 $response["success"] = true; 값을 받음
                    boolean success = jsonObject.getBoolean("success");  // true, false 가져옴

                    if ( success )
                    {
                        String member_id = jsonObject.getString("member_id");
                        String member_pw = jsonObject.getString("member_pw");


                        Intent intent = new Intent(LoginActivity.this, MyFragment.class);
                        intent.putExtra("member_id",member_id);
                        intent.putExtra("member_pw",member_pw);


                        Toast.makeText(getApplicationContext(),"로그인에 성공하였습니다.",Toast.LENGTH_SHORT).show();

                        setEmptyEt();
                        startMainActivity(  );


                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"로그인에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        LoginRequest MyFragment = new LoginRequest(member_id, member_pw, responseListener);
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        queue.add(MyFragment);
        return false;
    }


    private void setEmptyEt() {
        id.setText("");
        pw.setText("");
    }
}