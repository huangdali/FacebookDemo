package com.jwkj.facebook;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.widget.ShareDialog;
import com.hdl.elog.ELog;

public class SharedActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);


        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.p1);


        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        // this part is optional
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                ELog.hdl("成功了" + result.toString());
            }

            @Override
            public void onCancel() {
                ELog.hdl("取消了");
            }

            @Override
            public void onError(FacebookException error) {
                ELog.hdl("错误了" + error);
            }
        });
    }

    public void onShared(View view) {
        ELog.hdl("点击了");


        if (ShareDialog.canShow(ShareLinkContent.class)) {
//            sharedLink();//分享链接
            sharedPic();
        }


    }

    /**
     * 分享图片
     */
    private void sharedPic() {
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(bitmap)
                .build();
        ShareContent shareContent = new ShareMediaContent.Builder()
                .addMedium(photo)
                .build();
        shareDialog.show(shareContent);
    }

    /**
     * 分享链接
     */
    private void sharedLink() {
        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                .setContentTitle("Hello Facebook")
                .setContentDescription(
                        "The 'Hello Facebook' sample  showcases simple Facebook integration")
                .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
                .build();

        shareDialog.show(linkContent);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
