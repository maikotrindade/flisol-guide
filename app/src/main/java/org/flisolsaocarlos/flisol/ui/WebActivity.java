package org.flisolsaocarlos.flisol.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;

import org.flisolsaocarlos.flisol.R;
import org.flisolsaocarlos.flisol.model.Supporter;


public class WebActivity extends Activity {

    WebView mWebView;
    Supporter supporter;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_layout);

        Intent intent = getIntent();
        String titleActivity = intent.getStringExtra("title");
        String url = intent.getStringExtra("url");

        setTitle(titleActivity);
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(url);
    }

    @Override
    public void finish() {
        overridePendingTransition(R.anim.end_in, R.anim.end_out);
        super.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}