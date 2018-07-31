package xyz.hyfree.sinteam.dmobile.TBS;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;

import java.util.Map;

public class TBSWebView extends com.tencent.smtt.sdk.WebView {
    public TBSWebView(Context context) {
        super(context);
    }

    public TBSWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TBSWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public TBSWebView(Context context, AttributeSet attributeSet, int i, boolean b) {
        super(context, attributeSet, i, b);
    }

    public TBSWebView(Context context, AttributeSet attributeSet, int i, Map<String, Object> map, boolean b) {
        super(context, attributeSet, i, map, b);
    }
    public static TBSWebView GetAGeneralFactory(Context context){
        final TBSWebView tbsWebView=new TBSWebView(context);
        //配置webview
        //打开后开始加载
        final com.tencent.smtt.sdk.WebSettings webSettings=tbsWebView.getSettings();

        //开发模式，允许调试
        //setWebContentsDebuggingEnabled(true);　
        if (Build.VERSION.SDK_INT>=19)
            tbsWebView.setWebContentsDebuggingEnabled(true);

        //支持JavaScript
        webSettings.setJavaScriptEnabled(true);

        // 支持使用localStorage(H5页面的支持)
        webSettings.setDomStorageEnabled(true);

        // 支持数据库
        webSettings.setDatabaseEnabled(true);

        // 支持缓存
        webSettings.setAppCacheEnabled(true);
        String appCaceDir = context.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        webSettings.setAppCachePath(appCaceDir);

        // 设置可以支持缩放
        // webSettings.setUseWideViewPort(true);

        // 扩大比例的缩放
        // webSettings.setSupportZoom(true);
        //webSettings.setBuiltInZoomControls(true);

        // 隐藏缩放按钮
        webSettings.setDisplayZoomControls(false);

        // 自适应屏幕
        //webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //webSettings.setLoadWithOverviewMode(true);

        // 隐藏滚动条
        tbsWebView.setHorizontalScrollBarEnabled(false);
        tbsWebView.setVerticalScrollBarEnabled(false);

        //硬件加速
        //tbsWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        //允许js调用java方法
        if (Build.VERSION.SDK_INT<17)
            tbsWebView.addJavascriptInterface(context,"android");

        //处理UA
        webSettings.setUserAgentString("Mozilla/5.0 (Linux; Android 5.1; m2 note Build/LMY47D) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile MQQBrowser/6.2 TBS/036222 Safari/537.36 V1_AND_SQ_6.3.3_358_YYB_D QQ/6.3.3.2755 NetType/WIFI WebP/0.3.0 Pixel/1080");

        //支持插件
        webSettings.setPluginState(com.tencent.smtt.sdk.WebSettings.PluginState.ON);

        //允许加载不安全的来源（不推荐）
        if (Build.VERSION.SDK_INT>=21)
            webSettings.setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        // 处理网页内的连接（自身打开）,重写webClient
        tbsWebView.setWebViewClient(new com.tencent.smtt.sdk.WebViewClient(){

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                tbsWebView.loadUrl(url);

                return true;
                //  return super.shouldOverrideUrlLoading(view, url);
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                tbsWebView.loadUrl(request.getUrl().toString());
                return true;
                // return super.shouldOverrideUrlLoading(view, request);
            }
        });
        return tbsWebView;
    }
    public static void FastSetting(final TBSWebView tbsWebView,Context context){

        //配置webview
        //打开后开始加载
        final com.tencent.smtt.sdk.WebSettings webSettings=tbsWebView.getSettings();

        //开发模式，允许调试
        //setWebContentsDebuggingEnabled(true);　
        if (Build.VERSION.SDK_INT>=19)
            tbsWebView.setWebContentsDebuggingEnabled(true);

        //支持JavaScript
        webSettings.setJavaScriptEnabled(true);

        // 支持使用localStorage(H5页面的支持)
        webSettings.setDomStorageEnabled(true);

        // 支持数据库
        webSettings.setDatabaseEnabled(true);

        // 支持缓存
        webSettings.setAppCacheEnabled(true);
        String appCaceDir = context.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        webSettings.setAppCachePath(appCaceDir);

        // 设置可以支持缩放
        // webSettings.setUseWideViewPort(true);

        // 扩大比例的缩放
        // webSettings.setSupportZoom(true);
        //webSettings.setBuiltInZoomControls(true);

        // 隐藏缩放按钮
        webSettings.setDisplayZoomControls(false);

        // 自适应屏幕
        //webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //webSettings.setLoadWithOverviewMode(true);

        // 隐藏滚动条
        tbsWebView.setHorizontalScrollBarEnabled(false);
        tbsWebView.setVerticalScrollBarEnabled(false);

        //硬件加速
        //tbsWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        //允许js调用java方法
        if (Build.VERSION.SDK_INT<17)
            tbsWebView.addJavascriptInterface(context,"android");

        //处理UA
        webSettings.setUserAgentString("Mozilla/5.0 (Linux; Android 5.1; m2 note Build/LMY47D) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile MQQBrowser/6.2 TBS/036222 Safari/537.36 V1_AND_SQ_6.3.3_358_YYB_D QQ/6.3.3.2755 NetType/WIFI WebP/0.3.0 Pixel/1080");

        //支持插件
        webSettings.setPluginState(com.tencent.smtt.sdk.WebSettings.PluginState.ON);

        //允许加载不安全的来源（不推荐）
        if (Build.VERSION.SDK_INT>=21)
            webSettings.setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        // 处理网页内的连接（自身打开）,重写webClient
        tbsWebView.setWebViewClient(new com.tencent.smtt.sdk.WebViewClient(){

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                tbsWebView.loadUrl(url);

                return true;
                //  return super.shouldOverrideUrlLoading(view, url);
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                tbsWebView.loadUrl(request.getUrl().toString());
                return true;
                // return super.shouldOverrideUrlLoading(view, request);
            }
        });

    }

}
