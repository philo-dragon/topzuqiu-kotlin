package com.pfl.module2.mvp.module2;

import com.blankj.utilcode.util.ToastUtils;
import com.pfl.common.entity.base.AccessToken;
import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.exception.ApiException;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by rocky on 2018/1/2.
 */

public class Module2Persenter {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private Module2View view;


    @Inject
    public Module2Persenter(LifecycleProvider lifecycle, RetrofitService service, Module2View view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }


    public void requestData() {
        service.getToken("client_credentials", "282307895618", "b9c6c8f954dbbf7274910585a95efce1")
                .compose(RxSchedulers.<HttpResponse<AccessToken>>compose())
                .compose(lifecycle.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<AccessToken>>() {
                    @Override
                    public void onNext(HttpResponse<AccessToken> httpResponse) {

                        if (httpResponse.isSuccess()) {
                            view.onSuccess(httpResponse.getData().getAccess_token());
                        } else {
                            onError(new ApiException(httpResponse.getCode(), httpResponse.getMsg()));
                        }

                    }
                });
    }
}
