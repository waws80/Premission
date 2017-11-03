package top.waws.premission;

import android.app.Activity;
import android.app.Fragment;
import android.support.annotation.NonNull;

/**
 * 权限申请封装类
 * Created on 2017/11/2.
 * @author liuxiongfei
 */
@SuppressWarnings("All")
public final class Permission {

    /**
     * 是否开启了调试模式
     */
    private static boolean DEBUG = false;

    public static final int OK = 100;

    public static final int ERROR = 101;

    private static final int REQUEST_CODE = 101010;

    private String mPermission;

    private Activity mActivity;

    private String mDesc;

    private Permission(){
    }

    /**
     * 内部静态类专门获取Permission实类对象
     */
    private static final class Builder{
        private static final Permission PERMISSION = new Permission();
    }

    /**
     * 不开启调试模式的初始化
     * @return Permission
     */
    public static Permission init(){
        return init(false);
    }

    /**
     * 开启调试模式的初始化
     * @param debug 是否开启调试模式
     * @return Permission
     */
    public static Permission init(@NonNull boolean debug) {
        DEBUG = debug;
        Logger.init(DEBUG);
        return Builder.PERMISSION;
    }

    /**
     * 设置请求的权限
     * @param premission 权限
     * @return Permission
     */
    public Permission request(@NonNull String premission) {
        this.mPermission = premission;
        return this;
    }

    /**
     * 构建上下文
     * @param activity
     * @return
     */
    public Permission build(@NonNull Activity activity){
        this.mActivity = activity;
        return this;
    }

    public Permission desc(@NonNull String desc){
        this.mDesc = desc;
        return this;
    }

    /**
     * 构建上下文
     * @param fragment
     * @return
     */
    public Permission build(@NonNull Fragment fragment){
        return build(fragment.getActivity());
    }

    /**
     * 构建上下文
     * @param fragmentv4
     * @return
     */
    public Permission build(@NonNull android.support.v4.app.Fragment fragmentv4){
        return build(fragmentv4.getActivity().getParent());
    }

    /**
     * 执行请求权限
     * @param activity Activity
     */
    public void execute(@NonNull PermissionCallBack callBack){
        new Call(mPermission, REQUEST_CODE, this.mActivity, callBack,this.mDesc);
    }


}
