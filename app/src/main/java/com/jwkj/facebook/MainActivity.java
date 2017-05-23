package com.jwkj.facebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;
import com.hdl.elog.ELog;

import org.json.JSONObject;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private CallbackManager callbackManager;
    private TextView tvLogInfo;
    private ImageView ivHeadPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ELog.hdl("到分享页面了");
        callbackManager = CallbackManager.Factory.create();
        final LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        tvLogInfo = (TextView) findViewById(R.id.tv_log_control);
        ivHeadPic = (ImageView) findViewById(R.id.iv_user_headerpic);

        loginButton.setReadPermissions("email");
        //loginButton.setFragment(this);//如果是在fragment里面使用登录，那么加上这一行代码
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        ELog.hdl("登录成功了");
                        getLoginInfo(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        ELog.hdl("取消登录了");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        ELog.hdl("登录异常了" + exception);
                    }
                });


        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                ELog.hdl("登录成功了");
                AccessToken token = loginResult.getAccessToken();
                ELog.hdl("getToken \t" + token.getToken());
                tvLogInfo.append("getToken =\t" + token.getToken() + "\n\n");
                ELog.hdl("getApplicationId \t" + token.getApplicationId());
                ELog.hdl("getUserId \t" + token.getUserId());
                ELog.hdl("getDeclinedPermissions \t" + token.getDeclinedPermissions());
                ELog.hdl("getExpires \t" + token.getExpires());
                ELog.hdl("getLastRefresh \t" + token.getLastRefresh());
                ELog.hdl("getPermissions \t" + token.getPermissions());
                ELog.hdl("getSource \t" + token.getSource());
                ELog.hdl("describeContents \t" + token.describeContents());
                ELog.hdl("isExpired \t" + token.isExpired());
                getLoginInfo(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                // App code
                ELog.hdl("取消登录了");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                ELog.hdl("登录异常了" + exception);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 获取登录信息
     *
     * @param accessToken
     */
    public void getLoginInfo(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                if (object != null) {
                    FBLoginResult result = new Gson().fromJson(object.toString(), FBLoginResult.class);
                    ELog.hdl("获取登录结果了：" + result);
                    tvLogInfo.append(result.toString());
                    Glide.with(MainActivity.this).load(result.getPicture().getData().getUrl()).into(ivHeadPic);
                }
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,gender,birthday,email,picture,locale,updated_time,timezone,age_range,first_name,last_name");
        request.setParameters(parameters);
        request.executeAsync();
    }

    public void onLogin(View view) {
        tvLogInfo.setText("");
        ELog.hdl("点击了");
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));

    }
}
