package com.feicuiedu.treasure_20170606.user.register;

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

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_Username)
    EditText mEtUsername;
    @BindView(R.id.et_Password)
    EditText mEtPassword;
    @BindView(R.id.et_Confirm)
    EditText mEtConfirm;
    @BindView(R.id.btn_Register)
    Button mBtnRegister;
    private String mUserName;
    private String mPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);

        //toolbar
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            //返回箭头和标题
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.register);
        }

        mEtUsername.addTextChangedListener(mTextWatcher);
        mEtPassword.addTextChangedListener(mTextWatcher);
        mEtConfirm.addTextChangedListener(mTextWatcher);
    }

    //文本的监听
    private TextWatcher mTextWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            //判断
            mUserName = mEtUsername.getText().toString();
            mPassword = mEtPassword.getText().toString();
            String confirm = mEtConfirm.getText().toString();

            boolean canRegister=!(TextUtils.isEmpty(mUserName) ||
                    TextUtils.isEmpty(mPassword) ||
                    TextUtils.isEmpty(confirm)) && (mPassword.equals(confirm));
            mBtnRegister.setEnabled(canRegister);
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @OnClick(R.id.btn_Register)
    public void onViewClicked() {

        // 账号不符合规范
        if (RegexUtils.verifyUsername(mUserName)!=RegexUtils.VERIFY_SUCCESS){
            // 弹出对话框
            AlertDialogFragment.getInstances(getString(R.string.username_error),getString(R.string.username_rules))
                    .show(getSupportFragmentManager(),"usernameerror");
            return;
        }
        if (RegexUtils.verifyPassword(mPassword)!=RegexUtils.VERIFY_SUCCESS){
            // 弹出对话框
            AlertDialogFragment.getInstances(getString(R.string.password_error),getString(R.string.password_rules))
                    .show(getSupportFragmentManager(),"passworderror");
            return;
        }
        // TODO: 2017/6/7 做注册的网络请求
    }
}
