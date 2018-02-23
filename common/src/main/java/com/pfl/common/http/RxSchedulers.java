package com.pfl.common.http;

import android.content.Context;

import com.blankj.utilcode.util.NetworkUtils;
import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_user.UserInfo;
import com.pfl.common.exception.NoNetworkException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;

public class RxSchedulers {

    public static <T> ObservableTransformer<T, T> compose() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                // 没有网络
                                if (!NetworkUtils.isConnected() /*&& NetworkUtils.isAvailableByPing()*/) {
                                    throw new NoNetworkException();
                                }
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
