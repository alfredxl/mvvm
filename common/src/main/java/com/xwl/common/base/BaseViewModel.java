package com.xwl.common.base;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.DefaultLifecycleObserver;


import com.xwl.common.util.RxBus;

import java.lang.ref.SoftReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @ProjectName: GcService
 * @Package: com.smart.gc.common.base
 * @ClassName: BaseViewModel
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2019/11/1 10:46
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/11/1 10:46
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseViewModel<M extends BaseModel> extends AndroidViewModel implements DefaultLifecycleObserver,
        Handler.Callback, Observable {
    private transient PropertyChangeRegistry mCallbacks;
    protected M model;
    protected SoftReference<BaseContract> baseContract;
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    protected Handler handler = new Handler(Looper.getMainLooper(), this);

    public BaseViewModel(@NonNull Application application) {
        super(application);
        createModel();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    protected void createModel() {
        Type superType = getClass().getGenericSuperclass();
        if (superType instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) superType;
            Type[] types = pt.getActualTypeArguments();
            if (types.length > 0) {
                Class mClass = (Class) types[0];
                try {
                    model = (M) mClass.newInstance();
                    model.setCompositeDisposable(compositeDisposable);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setBaseContract(BaseContract baseContract) {
        this.baseContract = new SoftReference<>(baseContract);
    }

    protected BaseContract getBaseContract() {
        if (baseContract != null) {
            return baseContract.get();
        }
        return null;
    }

    protected void showToast(int resId) {
        showToast(getStringRes(resId));
    }

    protected void showToast(String message) {
        BaseContract contract = getBaseContract();
        if (contract != null) {
            contract.showToast(message);
        }
    }

    protected void showDialog(int titleResId, int messageResId) {
        showDialog(getStringRes(titleResId), getStringRes(messageResId));
    }

    protected void showDialog(String title, String message) {
        BaseContract contract = getBaseContract();
        if (contract != null) {
            contract.showDialog(title, message);
        }
    }

    protected void dismissDialog() {
        BaseContract contract = getBaseContract();
        if (contract != null) {
            contract.dismissDialog();
        }
    }

    protected void showLoading(String message) {
        BaseContract contract = getBaseContract();
        if (contract != null) {
            contract.showLoading(message);
        }
    }

    protected void dismissLoading() {
        BaseContract contract = getBaseContract();
        if (contract != null) {
            contract.dismissLoading();
        }
    }

    protected void finish() {
        BaseContract contract = getBaseContract();
        if (contract != null) {
            contract.finish();
        }
    }

    protected void onBackPressed() {
        BaseContract contract = getBaseContract();
        if (contract != null) {
            contract.onBackPressed();
        }
    }

    protected String getStringRes(int ResId) {
        return getApplication().getResources().getString(ResId);
    }

    @Override
    public boolean handleMessage(Message msg) {
        return true;
    }

    /*** 订阅RxBus消息 ***/
    protected <C> void toObservable(Consumer<C> consumer, Class<C> mClass) {
        RxBus.getInstance().toObservable(mClass).subscribe(new Observer<C>() {
            private Disposable d;

            @Override
            public void onSubscribe(Disposable d) {
                this.d = d;
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(C c) {
                if (consumer != null) {
                    try {
                        consumer.accept(c);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                compositeDisposable.remove(d);
            }

            @Override
            public void onComplete() {
                compositeDisposable.remove(d);
            }
        });
    }

    protected void postRxBus(Object object) {
        RxBus.getInstance().post(object);
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        synchronized (this) {
            if (mCallbacks == null) {
                mCallbacks = new PropertyChangeRegistry();
            }
        }
        mCallbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.remove(callback);
    }

    public void notifyChange() {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.notifyCallbacks(this, 0, null);
    }

    public void notifyPropertyChanged(int fieldId) {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.notifyCallbacks(this, fieldId, null);
    }
}
