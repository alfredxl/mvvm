package com.xwl.common.base;


import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @ProjectName: GcService
 * @Package: com.smart.gc.common.base
 * @ClassName: BaseModel
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2019/11/1 10:47
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/11/1 10:47
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseModel {
    protected CompositeDisposable compositeDisposable;

    public void setCompositeDisposable(CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
    }

    public abstract class BaseObserver<T> implements Observer<T> {
        private Disposable d;

        @Override
        public void onSubscribe(Disposable d) {
            this.d = d;
            compositeDisposable.add(d);
        }

        @Override
        public void onError(Throwable e) {
            dispose();
        }

        @Override
        public void onNext(T t) {

        }

        @Override
        public void onComplete() {
            dispose();
        }

        private void dispose() {
            if (d != null) {
                d.dispose();
                compositeDisposable.remove(d);
                d = null;
            }
        }
    }
}
