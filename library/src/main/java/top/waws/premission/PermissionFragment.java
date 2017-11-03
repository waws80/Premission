package top.waws.premission;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;

/**
 * Created on 2017/11/2.
 * @author liuxiongfei.
 * Desc 执行授权操作
 */

public final class PermissionFragment extends Fragment{

    private PermissionCallBack callBack;

    private String permission;

    private int requestCode;

    private String desc = "";

    public void setCallBack(PermissionCallBack callBack) {
        this.callBack = callBack;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        start();

    }

    /**
     * 判断权限是否需要申请
     */
    private void start(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(this.getActivity(),this.permission)
                    != PackageManager.PERMISSION_GRANTED){
                request();
            }else {
                this.callBack.next(Permission.OK);
            }

        }else {
            this.callBack.next(Permission.OK);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void request() {
        if (shouldShowRequestPermissionRationale(this.permission)){
            //用户之前拒绝过授权

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("提示")
                    .setMessage(this.desc)
                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //开始授权
                            Logger.d("开始申请拒绝过的权限 Permission："+permission);
                            requestPermissions(new String[]{permission},requestCode);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Logger.d("再次拒绝申请权限 Permission："+permission);
                            Logger.d("please give me a chance! please!\n the "+ permission+" is denied");
                        }
                    })
                    .setCancelable(false)
                    .show();

        }else {
            //开始授权
            Logger.d("开始申请权限 Permission："+this.permission);
            requestPermissions(new String[]{permission},requestCode);
        }
    }

    /**
     * 请求权限回调
     * @param requestCode requestCode
     * @param permissions permissions
     * @param grantResults grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (this.requestCode == requestCode){

            if (grantResults.length >0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Logger.d("获取权限成功 Permission："+permission);
                callBack.next(Permission.OK);
            }else {
                Logger.d("拒绝了权限 Permission："+permission);
                callBack.next(Permission.ERROR);
            }
        }
        destory();
    }

    /**
     * 销毁当前fragment
     */
    private void destory() {
        FragmentTransaction transaction = getActivity().getFragmentManager()
                .beginTransaction();
        transaction.remove(this).commit();
    }
}
