package winning.webviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends Activity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showWebView();
    }

    private void showWebView() {

        // webView与js交互代码
        try {
            mWebView = new WebView(this);
            setContentView(mWebView);

            mWebView.requestFocus();
            // 必须设置WebChromeClient
            mWebView.setWebChromeClient(new WebChromeClient() {
            });

            mWebView.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK
                            && mWebView.canGoBack()) {
                        mWebView.goBack();
                        return true;
                    }
                    return false;
                }
            });

            WebSettings webSettings = mWebView.getSettings();
            // 必须支持JavaScriptEnabled
            webSettings.setJavaScriptEnabled(true);
            webSettings.setDefaultTextEncodingName("utf-8");
            // 给html提供java 的对象，必须在loadUrl之前调用
            mWebView.addJavascriptInterface(getHtmlObject(), "jsObj");
            mWebView.loadUrl("file:///android_asset/test.html");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Object getHtmlObject() {
        Object insertObj = new Object() {
            // 4.2后，必须加@JavascriptInterface，不加js调用不了
            @JavascriptInterface
            public String HtmlcallJava() {
                return "Html call Java haha";
            }

            @JavascriptInterface
            public String HtmlcallJava2(final String param) {
                return "Html call Java : " + param;
            }

            @JavascriptInterface
            public void JavacallHtml() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // java 调用js代码
                        mWebView.loadUrl("javascript: showFromHtml()");
                    }
                });
            }

            @JavascriptInterface
            public void JavacallHtml2() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mWebView.loadUrl("javascript: showFromHtml2('aaa')");
                    }
                });
            }
        };

        return insertObj;
    }
}
