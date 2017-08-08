package com.ruiqin.permission.module.home;

import com.ruiqin.permission.base.BaseModel;
import com.ruiqin.permission.base.BasePresenter;
import com.ruiqin.permission.base.BaseView;
import com.ruiqin.permission.module.home.adapter.MainRecyclerAdapter;
import com.ruiqin.permission.module.home.bean.MainRecyclerData;

import java.util.List;

/**
 * Created by ruiqin.shen
 * 类说明：
 */

public interface MainContract {
    interface Model extends BaseModel {
        List<MainRecyclerData> initData();
    }

    interface View extends BaseView {
        void setRecyclerAdapterSuccess(MainRecyclerAdapter mainRecyclerAdapter);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        abstract void setAdapter();
    }
}
