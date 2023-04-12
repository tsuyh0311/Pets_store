package com.huawei.petsstore01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.support.account.AccountAuthManager;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.request.AccountAuthParamsHelper;
import com.huawei.hms.support.account.result.AuthAccount;
import com.huawei.hms.support.account.service.AccountAuthService;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.support.hwid.ui.HuaweiIdAuthButton;

public class loginActivity extends AppCompatActivity {
    EditText username , password ;
    Button login ;
    com.huawei.hms.support.hwid.ui.HuaweiIdAuthButton HuaweiIdAuthButton;
    private AccountAuthService mAuthService;
    private AccountAuthParams mAuthParam;
    private static final String TAG = "Account";
    private static final int REQUEST_CODE_SIGN_IN = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        HuaweiIdAuthButton = findViewById(R.id.HuaweiIdAuthButton);
        setHint();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    Intent intent = new Intent();
                    intent.setClass(loginActivity.this, MainPageActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(loginActivity.this, "您输入的账号或密码有误", Toast.LENGTH_LONG);
                }
            }
        });

        HuaweiIdAuthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                silentSignInByHwId();
            }
        });

    }

    private void silentSignInByHwId() {
        // 1、配置登录请求参数AccountAuthParams，包括请求用户id(openid、unionid)、email、profile（昵称、头像）等。
        // 2、DEFAULT_AUTH_REQUEST_PARAM默认包含了id和profile（昵称、头像）的请求。
        // 3、如需要请求获取用户邮箱，需要setEmail();
        // 1. Configure the login request parameters AccountAuthParams, including the requested user id (openid, unionid),
        // email, profile (nickname, avatar), etc.
        // 2. DEFAULT_AUTH_REQUEST_PARAM includes requests for id and profile (nickname, avatar) by default.
        // 3. If you need to get the user mailbox again, you need setEmail();
        mAuthParam = new AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM)
                .setEmail()
                .createParams();

        // 使用请求参数构造华为帐号登录授权服务AccountAuthService
        // Use request parameters to construct a Huawei account login authorization service AccountAuthService
        mAuthService = AccountAuthManager.getService(this, mAuthParam);

        // 使用静默登录进行华为帐号登录
        // Use silent sign in for HUAWEI ID login
        Task<AuthAccount> task = mAuthService.silentSignIn();
        task.addOnSuccessListener(new OnSuccessListener<AuthAccount>() {
            @Override
            public void onSuccess(AuthAccount authAccount) {
                // 静默登录成功，处理返回的帐号对象AuthAccount，获取帐号信息
                // Silent sign in is successful, the returned account object AuthAccount is processed,account information is obtained and processed
                Intent intent = new Intent();
                intent.setClass(loginActivity.this, MainPageActivity.class);
                startActivity(intent);
            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                // 静默登录失败，使用getSignInIntent()方法进行前台显式登录
                // Silent sign in fails, use the getSignInIntent() method to log in from the foreground
                if (e instanceof ApiException) {
                    ApiException apiException = (ApiException) e;
                    Intent signInIntent = mAuthService.getSignInIntent();
                    startActivityForResult(signInIntent, REQUEST_CODE_SIGN_IN);
                    Toast.makeText(loginActivity.this, "第三方登录失败请再次登录", Toast.LENGTH_LONG);
                }
            }
        });
    }


    public void setHint(){
        SpannableString spannableString1 = new SpannableString("请输入您的账号");
        AbsoluteSizeSpan absoluteSizeSpan1 = new AbsoluteSizeSpan(20,true);
        spannableString1.setSpan(absoluteSizeSpan1,0,spannableString1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        username.setHint(spannableString1);
        SpannableString spannableString2 = new SpannableString("请输入您的密码");
        AbsoluteSizeSpan absoluteSizeSpan2 = new AbsoluteSizeSpan(20,true);
        spannableString1.setSpan(absoluteSizeSpan2,0,spannableString2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        password.setHint(spannableString2);
    }


}