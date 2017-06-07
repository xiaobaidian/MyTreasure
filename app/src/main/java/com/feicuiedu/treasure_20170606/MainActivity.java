package com.feicuiedu.treasure_20170606;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.feicuiedu.treasure_20170606.commons.ActivityUtils;
import com.feicuiedu.treasure_20170606.user.login.LoginActivity;
import com.feicuiedu.treasure_20170606.user.register.RegisterActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    private Unbinder mUnbinder;
    private ActivityUtils mActivityUtils;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);
        mActivityUtils = new ActivityUtils(this);

    }

    @OnClick({R.id.btn_Register,R.id.btn_Login})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_Register:
                mActivityUtils.startActivity(RegisterActivity.class);
                break;
            case R.id.btn_Login:
                mActivityUtils.startActivity(LoginActivity.class);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
