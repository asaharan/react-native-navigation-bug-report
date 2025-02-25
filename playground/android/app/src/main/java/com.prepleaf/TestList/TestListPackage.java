package com.prepleaf.TestList;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestListPackage implements ReactPackage {

    @Override
    public List<NativeModule> createNativeModules(
            ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();

        return modules;
    }

    public List<ViewManager> createViewManagers (
            ReactApplicationContext reactContext
    ) {
        return Arrays.asList(new TestListManager());
    }
}
