package com.nexus.v3;

import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class MainActivity extends AppCompatActivity {

    private WebView webview;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webview = findViewById(R.id.webview);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        configureWebView();
        loadAsset();
    }

    private void configureWebView() {
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);

        // Mixed content policy
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        // Add JavaScript bridge
        webview.addJavascriptInterface(new JavaScriptBridge(this, vibrator), "Android");
        webview.setWebViewClient(new WebViewClient());
    }

    private void loadAsset() {
        webview.loadUrl("file:///android_asset/index.html");
    }
}
