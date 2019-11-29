package com.bwei.zxmoth1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bwei.zxmoth1.Base.BaseActivity;

public class Main2Activity extends BaseActivity {

    private WebView webwiew1;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        final String key = intent.getStringExtra("key");
        Log.i("xxx",key);
        webwiew1.loadUrl(key);
        webwiew1.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(key);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        webwiew1.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
            }
        });
    }

    @Override
    protected void initView() {
        webwiew1 = findViewById(R.id.webwiew1);
        WebSettings settings = webwiew1.getSettings();
        settings.setJavaScriptEnabled(true);
    }

    @Override
    protected int laoutid() {
        return R.layout.activity_main2;
    }
}
