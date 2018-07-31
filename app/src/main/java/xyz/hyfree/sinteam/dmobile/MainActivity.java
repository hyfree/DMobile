package xyz.hyfree.sinteam.dmobile;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;

import java.net.URL;
import java.util.ArrayList;

import xyz.hyfree.sinteam.dmobile.TBS.TBSWebView;
import xyz.hyfree.sinteam.dmobile.Util.HttpDownloader;

public class MainActivity extends AppCompatActivity {
    //

    LinearLayout arena;
    ActionBar actionBar;
    BottomNavigationView navigation;


    TBSWebView mWebView;

    //全屏播放的容器
    FrameLayout mFullVideoBox;
    //全屏播放
    IX5WebChromeClient.CustomViewCallback mCallBack;
    //是否是在全屏播放
    Boolean isFullScr=false;
    //电源管理（全屏的时候常亮）
    private PowerManager powerManager = null;
    private PowerManager.WakeLock wakeLock = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                   // mTextMessage.setText(R.string.title_home);
                    mWebView.loadUrl("http://m.dilidili.wang/");
                    return true;
                case R.id.navigation_classify:
                    //mTextMessage.setText(R.string.title_classify);
                    mWebView.loadUrl("file:///android_asset/www/classify.html");
                    return true;
                case R.id.navigation_like:
                   // mTextMessage.setText(R.string.title_like);
                    //arena.addView(classfiy);
                    mWebView.loadUrl("file:///android_asset/www/collection.html");
                    return true;
                case R.id.navigation_user:
                   /// mTextMessage.setText(R.string.title_setup);
                    mWebView.loadUrl("file:///android_asset/www/setup.html");
                    return true;
            }
            return false;
        }
    };

    //使全屏时，手机旋转
    public void fullScreen(){
        if (getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mWebView.saveState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mWebView.restoreState(savedInstanceState);
        super.onRestoreInstanceState(savedInstanceState);
    }
    public static boolean isGrantExternalRW(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(

                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            activity.requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE

            }, 1);

            return false;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar=getSupportActionBar();



        mFullVideoBox=(FrameLayout)findViewById(R.id.myFullVideoBox);

        //绑定低栏
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);//低栏按钮监听

        //电源管理
        powerManager = (PowerManager) this.getSystemService(Service.POWER_SERVICE);
        wakeLock = this.powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Lock");

        //反射，消除低栏动画
        BottomNavigationViewHelper.disableShiftMode(navigation);

        //舞台容器
        arena=(LinearLayout)findViewById(R.id.arena);

        //检查客户端系统
        if (Build.VERSION.SDK_INT<17){
            new AlertDialog.Builder(this)
                    .setTitle("提醒：")
                    .setMessage("你手机的当前android版本太低，app可能无法运行。最低环境要求为：Android 4.2, 4.2.2")
                    .setPositiveButton("确定",null)
                    .show();
        }
        /*配置mWebView*/
         // mWebView=(WebView)findViewById(R.id.web);
        mWebView=(xyz.hyfree.sinteam.dmobile.TBS.TBSWebView) findViewById(R.id.web);
        TBSWebView.FastSetting(mWebView,this);
          //打开后开始加载
        mWebView.loadUrl("http://m.dilidili.wang/");
        final com.tencent.smtt.sdk.WebSettings webSettings=mWebView.getSettings();

        //开发模式，允许调试
        //setWebContentsDebuggingEnabled(true);　
        if (Build.VERSION.SDK_INT>=19)
        mWebView.setWebContentsDebuggingEnabled(true);

//        //支持JavaScript
//        webSettings.setJavaScriptEnabled(true);
//
//        // 支持使用localStorage(H5页面的支持)
//        webSettings.setDomStorageEnabled(true);
//
//        // 支持数据库
//        webSettings.setDatabaseEnabled(true);
//
//        // 支持缓存
//        webSettings.setAppCacheEnabled(true);
//        String appCaceDir = this.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
//        webSettings.setAppCachePath(appCaceDir);
//
//        // 设置可以支持缩放
//       // webSettings.setUseWideViewPort(true);
//
//        // 扩大比例的缩放
//        // webSettings.setSupportZoom(true);
//        //webSettings.setBuiltInZoomControls(true);
//
//        // 隐藏缩放按钮
//        webSettings.setDisplayZoomControls(false);
//
//        // 自适应屏幕
//        //webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        //webSettings.setLoadWithOverviewMode(true);
//
//        // 隐藏滚动条
//        mWebView.setHorizontalScrollBarEnabled(false);
//        mWebView.setVerticalScrollBarEnabled(false);
//
//        //硬件加速
//         //mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//
//        //允许js调用java方法
//        if (Build.VERSION.SDK_INT<17)
//         mWebView.addJavascriptInterface(MainActivity.this,"android");
//
//        //处理UA
//        webSettings.setUserAgentString("Mozilla/5.0 (Linux; Android 5.1; m2 note Build/LMY47D) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile MQQBrowser/6.2 TBS/036222 Safari/537.36 V1_AND_SQ_6.3.3_358_YYB_D QQ/6.3.3.2755 NetType/WIFI WebP/0.3.0 Pixel/1080");
//
//        //支持插件
//        webSettings.setPluginState(WebSettings.PluginState.ON);
//
//        //允许加载不安全的来源（不推荐）
//        if (Build.VERSION.SDK_INT>=21)
//        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        // 处理网页内的连接（自身打开）,重写webClient
        mWebView.setWebViewClient(new com.tencent.smtt.sdk.WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(com.tencent.smtt.sdk.WebView webView, String s) {
                mWebView.loadUrl(s);
                return  true;
                //return super.shouldOverrideUrlLoading(webView, s);
            }
            @Override
            public boolean shouldOverrideUrlLoading(com.tencent.smtt.sdk.WebView webView, com.tencent.smtt.export.external.interfaces.WebResourceRequest webResourceRequest) {
                mWebView.loadUrl(webResourceRequest.getUrl().toString());
                return true;
               // return super.shouldOverrideUrlLoading(webView, webResourceRequest);
            }
        });

        //重写mWebView的WebChromeClient
        mWebView.setWebChromeClient(new com.tencent.smtt.sdk.WebChromeClient(){
            @Override
            //取消全屏
            public void onHideCustomView() {
                fullScreen();
                if (mCallBack!=null){
                    mCallBack.onCustomViewHidden();
                }
               mWebView.setVisibility(View.VISIBLE);
                mFullVideoBox.removeAllViews();
                mFullVideoBox.setVisibility(View.GONE);
                isFullScr=false;
                // getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
                //显示状态栏
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN) ;
                actionBar.show();
                navigation.setVisibility(View.VISIBLE
                );
                //取消屏幕常亮，onPause()方法中执行
                wakeLock.release();

                super.onHideCustomView();
            }

            @Override
            //开始全屏
            public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback callback) {
                fullScreen();
                mWebView.setVisibility(View.GONE);
                mFullVideoBox.setVisibility(View.VISIBLE);
                mFullVideoBox.addView(view);
                mCallBack=callback;
                isFullScr=true;
                //隐藏状态栏
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                //隐藏标题栏
                actionBar.hide();
                //隐藏低栏按钮
                navigation.setVisibility(View.GONE);
                wakeLock.acquire();
                super.onShowCustomView(view, callback);
            }
        });


        mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction()==KeyEvent.ACTION_DOWN){
                    if (keyCode== KeyEvent.KEYCODE_BACK&&mWebView.canGoBack()){
                        mWebView.goBack();
                        return true;
                    }
                }

                return false;
            }
        });
        mWebView.setDownloadListener(new com.tencent.smtt.sdk.DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(url));
                startActivity(intent);

            }
        });
      //  if(isGrantExternalRW(this))
           //   new downloadMP4Thread().start();
       // mWebView.setWebContentsDebuggingEnabled(true);
        //mWebView config end


    }



}
class downloadFileThread extends Thread{
    public void run(){

        HttpDownloader httpDownloader=new HttpDownloader();
        String fileData=httpDownloader.downloadFiles("http://mystudy.bj.bcebos.com/AndroidDemo_009.xml");
        System.out.println(fileData);
    }
}
class downloadMP4Thread extends Thread{
    public void run(){
        HttpDownloader httpDownloader=new HttpDownloader();
        int downloadResult=httpDownloader.downloadFiles(
                "http://fengkui.bj.bcebos.com/%E8%B6%B3%E9%9F%B3.mp3","BoBoMusic","voice.mp3");
        System.out.println("下载结果："+downloadResult);
    }
}

