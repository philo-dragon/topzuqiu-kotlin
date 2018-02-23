package cn.com.topzuqiu.di.main;

import com.pfl.common.di.AppComponent;
import com.pfl.common.di.scope.ActivityScope;

import cn.com.topzuqiu.ui.activity.MainActivity;
import dagger.Component;

/**
 * Created by rocky on 2017/12/29.
 */

@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
