package com.pfl.module2.di.module2;

import com.pfl.common.di.AppComponent;
import com.pfl.common.di.scope.FragmentScope;
import com.pfl.module2.ui.fragment.Module2Fragment;

import dagger.Component;

/**
 * Created by rocky on 2018/1/2.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = Module2Module.class)
public interface Module2Component {

    void inject(Module2Fragment fragment);
}
