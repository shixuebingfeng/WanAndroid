package com.zlx.module_base.base_fg;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zlx.module_base.base_util.LogUtil;
import com.zlx.module_base.base_util.LogUtils;
import com.zlx.module_base.base_util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zlx on 2017/5/23.
 */

public abstract class BaseNavFg extends Fragment {

    private View view;
    private ViewGroup parent;
    protected Unbinder unbinder;
    protected Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtils.e("--------" + (view == null));
        if (view == null) {
            view = inflater.inflate(getLayoutId(), container, false);
        }
        parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        unbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    protected void initViews() {
    }

    protected abstract int getLayoutId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View getView() {
        return view;
    }


    @SuppressLint("CheckResult")
    protected void requestPermissions(String... permissions) {
        final RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(permissions)
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        getPermissionSuccess();
                    } else {
                        getPermissionFailured();
                    }
                });
    }

    protected void getPermissionSuccess() {
        LogUtil.show("Base--->getPermissionSuccess");
    }

    protected void getPermissionFailured() {
        LogUtil.show("Base--->getPermissionFailured");
    }

    protected void toast(String content) {
        ToastUtil.showShort(getContext(), content);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
