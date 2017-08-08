package com.ruiqin.permission.commonality.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.ruiqin.permission.R;
import com.ruiqin.permission.util.ToastUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by ruiqin.shen
 * 类说明：分享dialog
 */

public class ShareDialog extends Dialog {

    private static Context mContext;

    public ShareDialog(@NonNull Context context, String link) {
        this(context, R.style.CustomDialogTheme, link);
    }

    public ShareDialog(@NonNull Context context, @StyleRes int themeResId, String link) {
        super(context, themeResId);
        mContext = context;
        this.link = link;
    }

    String desc;
    String link;
    String logo_url;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_share);
        ButterKnife.bind(this);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);
    }

    public void setShareTitle(String title) {
        this.title = title;
    }

    public void setShareDesc(String desc) {
        this.desc = desc;
    }

    public void setShareLogoUrl(String logo_url) {
        this.logo_url = logo_url;
    }

    @OnClick({R.id.share_wx_circle, R.id.share_wx_chat, R.id.share_qq_space, R.id.share_qq_chat})
    public void onViewClicked(View view) {
//        cancel();
        hide();
        switch (view.getId()) {
            case R.id.share_wx_circle://微信朋友圈
                share2WX_Circle();
                break;
            case R.id.share_wx_chat://微信好友
                share2WX_Chat();
                break;
            case R.id.share_qq_space://qq空间
                share2QQ_Space();
                break;
            case R.id.share_qq_chat://qq好友
                share2QQ_Chat();
                break;
        }
    }

    /**
     * 分享到QQ好友
     */
    private void share2QQ_Chat() {
        shareAction(SHARE_MEDIA.QQ);
    }

    /**
     * 分享到QQ空间
     */
    private void share2QQ_Space() {
        shareAction(SHARE_MEDIA.QZONE);
    }


    /**
     * 分享到微信好友
     *
     * @return
     */
    private void share2WX_Chat() {
        shareAction(SHARE_MEDIA.WEIXIN);
    }

    /**
     * 分享到微信朋友圈
     */
    private void share2WX_Circle() {
        shareAction(SHARE_MEDIA.WEIXIN_CIRCLE);
    }

    /**
     * 创建action
     */
    private void shareAction(SHARE_MEDIA platform) {
        new ShareAction((Activity) mContext).setPlatform(platform)
                .withMedia(getUMWeb())
                .setCallback(umShareListener)
                .share();
    }

    private UMWeb getUMWeb() {
        UMWeb web = new UMWeb(link);
        if (title != null) {
            web.setTitle(title);//标题
        }

        if (desc != null) {
            web.setDescription(desc);//描述
        }

        if (logo_url != null) {
            web.setThumb(new UMImage(mContext, logo_url));
        }
        return web;
    }


    private static UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
            ToastUtils.showShort("开始分享");
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            ToastUtils.showShort("分享成功啦");
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastUtils.showShort("分享失败啦, " + t.getMessage());
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtils.showShort("分享取消了");
        }
    };

}
