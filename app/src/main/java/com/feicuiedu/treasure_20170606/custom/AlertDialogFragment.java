package com.feicuiedu.treasure_20170606.custom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.feicuiedu.treasure_20170606.R;

/**
 * Created by 蔡传飞 on 2017-06-07.
 */

public class AlertDialogFragment extends DialogFragment {
    private static final String KEY_TITLE = "key_title";
    private static final String KEY_MESSAGE = "key_message";

    /**
     * 展示的对话框视图：官方推荐有两种方式：
     * 1. onCreateView():返回一个对话框的视图，采用的是搭建一个layou布局的作为对话框视图
     * 2. onCreateDialog():可以直接创建AlertDialog
     *
     * 显示的方法：
     * show()方法
     */

    // 需要传递数据，对外提供一个创建方法：在里面通过setArguments方式传递数据

    public static AlertDialogFragment getInstances(String title,String message){
        AlertDialogFragment dialogFragment = new AlertDialogFragment();

        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE,title);
        bundle.putString(KEY_MESSAGE,message);

        // 官方推荐的传递数据的方法
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // 拿到传递过来的数据
        String title = getArguments().getString(KEY_TITLE);
        String message = getArguments().getString(KEY_MESSAGE);

        return new AlertDialog.Builder(getActivity())
                .setTitle(title)// 标题
                .setMessage(message)// 信息
                .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();// 消失
                    }
                })
                .create();
    }
}
