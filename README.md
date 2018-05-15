> # PermissionDemo
> #Android动态权限Demo

# 注意：完成了权限的封装，需要查看未封装的，可以切换到permission_noaml分支上

关于动态权限的一些介绍和代码介绍，这篇博文 [android6.0运行时权限扩展篇](http://blog.csdn.net/jianesrq0724/article/details/77100942)

代码中使用了RxJava和Lambda表达式，

- [RxJava2的使用以及RxJava2和RxJava的对比](http://blog.csdn.net/jianesrq0724/article/details/54892758)， 

- [android中lambda表达式的使用，以及如何引入](http://blog.csdn.net/jianesrq0724/article/details/54892943)

## 效果图
1. 进入首页用户未授予权限  
![](https://github.com/jianesrq0724/PermissionDemo/blob/master/gif/1.gif)

1. 用户授予权限  
![](https://github.com/jianesrq0724/PermissionDemo/blob/master/gif/2.gif)


* 是否引入ButterKnife
* ButterKnife在library和组件化的时候很不方便，这里先不适用ButterKnkife，使用findViewById,后期考虑databinding
* 插件Android Layout ID convert可以方便的生成findViewById

## RxManage 对线程管理

## 耗时等待转圈封装
* 网络请求等耗时操作，通过BaseActivity+RxJava完成封装，通过Loadinghelper来处理同时进行多个网络请求的进度条

## BasePresenter的创建使用弱引用，防止内存泄漏
*     protected WeakReference<T> mViewRef;
  
      public void attachView(T view) {
          mViewRef = new WeakReference<>(view);
      }
     
     

## 集成以下功能
* 异常崩溃的捕获
* log日志的本地保存
* 常用的工具类
* 多编译环境buildTypes
* 防止快速点击启动多个页面
* toolBar封装

--- 
# 待解决的问题:
* 封装BaseRecyclerView

