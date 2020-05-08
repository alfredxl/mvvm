package com.xwl.common.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;

import com.afollestad.materialdialogs.MaterialDialog;
import com.gyf.immersionbar.ImmersionBar;
import com.xwl.common.R;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * @ProjectName: GcService
 * @Package: com.smart.gc.common.base
 * @ClassName: BaseActivity
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2019/11/1 10:45
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/11/1 10:45
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class BaseActivity<M extends BaseModel, V extends ViewDataBinding, VM extends BaseViewModel<M>>
        extends AppCompatActivity implements BaseContract {
    protected V dataBinding;
    protected VM viewModel;
    private MaterialDialog dialog;
    private MaterialDialog dialogProgress;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (needFull()) {
            ImmersionBar.with(this)
                    .fitsSystemWindows(true)
                    .statusBarColor(getStatusBarColorResId())
                    .init();
        }
        initMVVM();
        if (viewModel != null) {
            getLifecycle().addObserver(viewModel);
        }
        initView();
        initData();
    }

    protected boolean needFull() {
        return true;
    }

    protected abstract int getStatusBarColorResId();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (viewModel != null) {
            getLifecycle().removeObserver(viewModel);
        }
        dataBinding.unbind();
        dismissDialog();
        dismissLoading();
        viewModel = null;
    }

    protected void initMVVM() {
        Type superType = getClass().getGenericSuperclass();
        if (superType instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) superType;
            Type[] types = pt.getActualTypeArguments();
            if (types.length > 2) {
                Class mClass = (Class) types[2];
                try {
                    viewModel = (VM) ViewModelProviders.of(this).get(mClass);
                    viewModel.setBaseContract(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        dataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        dataBinding.setVariable(getViewModelId(), viewModel);
    }

    protected abstract int getViewModelId();

    protected abstract int getLayoutId();

    @Override
    public void showLoading(String message) {
        if (!isFinishing() && !TextUtils.isEmpty(message)) {
            if (dialogProgress == null) {
                dialogProgress = new MaterialDialog.Builder(this)
                        .progress(true, 0)
                        .cancelable(false)
                        .canceledOnTouchOutside(false).build();
            }
            dialogProgress.setContent(message);
            dialogProgress.show();
        }
    }

    @Override
    public void showDialog(int tileRes, int messageRes) {
        showDialog(getString(tileRes), getString(messageRes));
    }

    @Override
    public void showDialog(int tileRes, int messageRes, boolean endFinish) {
        showDialog(getString(tileRes), getString(messageRes), endFinish);
    }

    @Override
    public void showDialog(String tile, String message) {
        showDialog(tile, message, false);
    }

    @Override
    public void showDialog(String tile, String message, boolean endFinish) {
        if (!isFinishing() && !TextUtils.isEmpty(tile) && !TextUtils.isEmpty(message)) {
            if (dialog == null) {
                dialog = new MaterialDialog.Builder(this)
                        .title(R.string.point)
                        .positiveText(R.string.determine).build();

            }
            dialog.setOnDismissListener(dialog -> {
                if (endFinish) {
                    finish();
                }
            });
            dialog.setTitle(tile);
            dialog.setContent(message);
            dialog.show();
        }
    }

    @Override
    public void dismissLoading() {
        if (dialogProgress != null) {
            dialogProgress.dismiss();
        }
    }

    @Override
    public void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void showToast(String message) {
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
