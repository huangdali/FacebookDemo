package com.jwkj.facebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.jwkj.facebook.bean.FBLoginResult;

import org.json.JSONObject;

import java.util.Arrays;

/**
 * 登录页
 */
public class MainActivity extends AppCompatActivity {
    private CallbackManager callbackManager;
    private TextView tvLogInfo;
    private ImageView ivHeadPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkLogin();
        tvLogInfo = (TextView) findViewById(R.id.tv_log_control);
        ivHeadPic = (ImageView) findViewById(R.id.iv_user_headerpic);
        //创建回调管理器对象
        callbackManager = CallbackManager.Factory.create();
        //方式一，使用自带的loginbutton来调起登录
        loginButton();


        //方式二，自定义按钮调登录，使用了loginbutton就不需要写下面的代码了
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
                        showMsg("取消登录了");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        ELog.hdl("登录异常了" + exception);
                    }
                });
    }

    /**
     * 检测是否已经登录过了
     */
    private void checkLogin() {
        AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        if (currentAccessToken == null) {
            ELog.hdl("当前没有登录");
            showMsg("未登录");
        } else {
            showMsg("账户已登录");
            getLoginInfo(currentAccessToken);
        }
    }

    private void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 使用自带的loginbutton来调起登录
     */
    private void loginButton() {
        //使用facebook自带的登录按钮
        final LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");//设置权限
        //loginButton.setFragment(this);//如果是在fragment里面使用登录，那么加上这一行代码
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                ELog.hdl("登录成功了");
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
                    showMsg("登录成功");
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

    public void onShared(View view) {
        startActivity(new Intent(this, SharedActivity.class));
    }
}
