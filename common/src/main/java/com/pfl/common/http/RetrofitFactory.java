package com.pfl.common.http;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    private static RetrofitFactory INSTANCE;

    private static final String BASE_URL = "http://apitest.topzuqiu.cn/";
    private static final long TIMEOUT = 15;
    private static final long MAX_TRY_COUNT = 3;
    private final RetrofitService service;

    private RetrofitFactory() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addNetworkInterceptor(netIntercepter)
                .retryOnConnectionFailure(true)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        service = retrofit.create(RetrofitService.class);
    }

    public static RetrofitFactory getInstance() {
        if (null == INSTANCE) {
            synchronized (RetrofitFactory.class) {
                if (null == INSTANCE) {
                    INSTANCE = new RetrofitFactory();
                }
            }
        }

        return INSTANCE;
    }

    //网络拦截器：失败重连 3 次
    private static Interceptor netIntercepter = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            int tryCount = 0;
            while (!response.isSuccessful() && tryCount < MAX_TRY_COUNT) {
                tryCount++;
                response = chain.proceed(request);
            }
            return response;
        }
    };


    public RetrofitService getService() {
        return service;
    }

}