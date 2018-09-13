package xyz.hyfree.sinteam.dmobile;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
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
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;

import xyz.hyfree.sinteam.dmobile.ACModel.AM_main;
import xyz.hyfree.sinteam.dmobile.TBS.TBSWebView;
import xyz.hyfree.sinteam.dmobile.Util.HttpDownloader;

public class MainActivity extends AppCompatActivity {
    //

    LinearLayout arena;
    ActionBar actionBar;
    BottomNavigationView navigation;
    //TBS组件
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
    //低栏按钮
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                   // mTextMessage.setText(R.string.title_home);
                    mWebView.loadUrl("file:///android_asset/www/home.html");
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
        //绑定全屏播放容器
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
        if (Build.VERSION.SDK_INT<22){
            new AlertDialog.Builder(this)
                    .setTitle("提醒：")
                    .setMessage("你手机的当前android版本太低，app部分功能可能无法运行。最低环境要求为：Android 5.1")
                    .setPositiveButton("确定",null)
                    .show();
        }

        /*配置mWebView*/
         // mWebView=(WebView)findViewById(R.id.web);
        mWebView=(xyz.hyfree.sinteam.dmobile.TBS.TBSWebView) findViewById(R.id.web);
        TBSWebView.FastSetting(mWebView,this);
          //打开后开始加载
        mWebView.loadUrl("file:///android_asset/www/home.html");
        final com.tencent.smtt.sdk.WebSettings webSettings=mWebView.getSettings();

        //开发模式，允许调试
        //setWebContentsDebuggingEnabled(true);　
        if (Build.VERSION.SDK_INT>=19)
        mWebView.setWebContentsDebuggingEnabled(true);

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

        //mWebView的下载事件监听器

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
        alert(this);


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(isFullScr){

                return  true;
            }
            if(mWebView.canGoBack())
            {
                mWebView.goBack();//返回上一页面
                return true;
            }
            else
            {
                System.exit(0);//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    //弹窗
    public  void alert(final Context context){

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setIcon(R.drawable.ic_home_black_24dp);
        builder.setTitle("选择启动模式");
        //    指定下拉列表的显示数据
        final String[] cities = {"动漫APP模式", "商城购物模式"};
        //    设置一个下拉的列表选择项
        builder.setItems(cities, new DialogInterface.OnClickListener()
        {
            String MyMessage="";
            @Override
            public void onClick(DialogInterface dialog, final int which)
            {

                switch (which){
                    case 0:

                        break;
                    case 1:
                        Intent intent=new Intent(MainActivity.this,xyz.hyfree.sinteam.dmobile.view.MainActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }

                Toast.makeText(MainActivity.this, "选择的模式为：" + cities[which], Toast.LENGTH_SHORT).show();

            }
        });
        builder.show();
    }


}
///下载线程类
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

