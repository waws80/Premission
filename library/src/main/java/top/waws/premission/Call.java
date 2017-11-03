package top.waws.premission;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created on 2017/11/2.
 * @author liuxiongfei.
 * Desc 权限请求核心类
 */
 final class Call {

    private String mPremission;
    private int mRequestCode;
    private Activity mActivity;
    private PermissionCallBack callBack;
    private String mDesc;

    Call(String permission, int requestCode, Activity activity,
         PermissionCallBack callBack, String desc){
        this.mPremission = permission;
        this.mRequestCode = requestCode;
        this.mActivity = activity;
        this.callBack = callBack;
        this.mDesc = desc;
        build();
    }

    private void build(){
        if (this.mPremission == null || this.mPremission.isEmpty()){
            throw new IllegalArgumentException("premission isEmpty");
        }
        if (this.callBack == null){
            throw new IllegalArgumentException("callBack is null");
        }
        PermissionFragment fragment = new PermissionFragment();
        fragment.setRequestCode(this.mRequestCode);
        fragment.setPermission(this.mPremission);
        fragment.setCallBack(this.callBack);
        if (mDesc == null){
            mDesc = "";
        }
        fragment.setDesc(this.mDesc);
        FragmentManager manager = this.mActivity.getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(fragment,"PermissionFragment").commit();
    }




}
