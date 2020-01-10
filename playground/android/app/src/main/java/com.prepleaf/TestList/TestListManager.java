package com.prepleaf.TestList;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.prepleaf.TestList.Views.RootView;

import java.util.Map;

public class TestListManager extends SimpleViewManager<RootView> {
    private static final String REACT_CLASS = "TestList";

    @NonNull
    @Override
    public String getName(){
        return REACT_CLASS;
    }

    @NonNull
    @Override
    public RootView createViewInstance(@NonNull ThemedReactContext context){
        Log.d("createViewInstance", "created");
        return new RootView(context);
    }

    @ReactProp(name="tabs")
    public void setTabs(RootView view, @Nullable ReadableArray data){
        view.setTabs(data);
        Log.d("TestList setTabs","number of tabs are"+data.size());
    }

    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder()
                .put(
                        "clickAssessment",
                        MapBuilder.of(
                                "phasedRegistrationNames",
                                MapBuilder.of("bubbled", "onClickAssessment")
                        )
                )
                .build();
    }
}
