package com.feicuiedu.treasure_20170606.user.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.feicuiedu.treasure_20170606.R;
import com.feicuiedu.treasure_20170606.commons.RegexUtils;
import com.feicuiedu.treasure_20170606.custom.AlertDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录页面
 * 1.toolbar作为ActionBar展示
 * 2.账号和密码的EditText输入监听
 * 3.点击登录的buttn，验证账号和密码的正确性
 * 4.点击登录进行网络请求
 */
public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_Username)
    EditText mEtUsername;
    @BindView(R.id.et_Password)
    EditText mEtPassword;
    @BindView(R.id.btn_Login)
    Button mBtnLogin;
    private String mUserName;
    private String mPasseord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //触发onContentChanged方法,视图的操作等放在onContentChanged里实现
        setContentView(R.layout.activity_login);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();

        ButterKnife.bind(this);

        //toolbar作为ActionBar展示
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            //设置返回箭头，Android已经处理好了，内部是选项菜单处理的，已经提供就好了id：android.R.id.home
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);//设置返回箭头

            //设置标题
            getSupportActionBar().setTitle(R.string.login);
        }

        //设置文本变化的监听
        mEtUsername.addTextChangedListener(mTextWatcher);
        mEtPassword.addTextChangedListener(mTextWatcher);
    }

    //文本变化的监听
    private TextWatcher mTextWatcher=new TextWatcher() {

        //文本变化前
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        //文本变化中
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }
        //文本变化后
        @Override
        public void afterTextChanged(Editable s) {
            mUserName = mEtUsername.getText().toString();
            mPasseord = mEtPassword.getText().toString();
            //判断用户名和密码都不为空才可以点击
            boolean canLogin= !(TextUtils.isEmpty(mUserName) || TextUtils.isEmpty(mPasseord));
            mBtnLogin.setEnabled(canLogin);
        }
    };


//用于处理选项菜单的选择事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // 处理ActionBar的返回箭头的事件
            case android.R.id.home:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_Login)
    public void onViewClicked() {
        //账号不符合规则，弹出一个对话框
        if (RegexUtils.verifyUsername(mUserName) != RegexUtils.VERIFY_SUCCESS) {
            // 弹出一个对话框提示：采用DialogFragment的方式
            AlertDialogFragment.getInstances(getString(R.string.username_error),getString(R.string.username_rules))
                    .show(getSupportFragmentManager(),"usernameerror");// 展示
            return;
        }
        //密码不符合规范
        if (RegexUtils.verifyPassword(mPasseord) != RegexUtils.VERIFY_SUCCESS) {
            // 弹出一个对话框提示
            AlertDialogFragment.getInstances(getString(R.string.password_error),getString(R.string.password_rules))
                    .show(getSupportFragmentManager(),"passworderror");
            return;
        }

        //TODO 验证都没问题，需要进行网络请求完成登录
    }
}
