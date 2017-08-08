package com.ruiqin.permission.module.home;

import com.ruiqin.permission.module.home.bean.MainRecyclerData;
import com.ruiqin.permission.module.test.TestActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruiqin.shen
 * 类说明：
 */

public class MainModel implements MainContract.Model {
    @Override
    public List<MainRecyclerData> initData() {
        List<MainRecyclerData> recyclerDataList = new ArrayList<>();
        recyclerDataList.add(new MainRecyclerData("TestActivity", TestActivity.class));
        return recyclerDataList;
    }
}
