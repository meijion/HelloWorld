
package com.hung.webjsdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

//helloworld
public class MainActivity extends Activity
{

    private WebView myWebView = null;
    private Button myButton = null;

    @SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" })
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myWebView = (WebView) findViewById(R.id.myWebView);
                                                    
        // 寰楀埌璁剧疆灞炴�х殑瀵硅薄
        WebSettings webSettings = myWebView.getSettings();

        // 浣胯兘JavaScript
        webSettings.setJavaScriptEnabled(true);

        // 鏀寔涓枃锛屽惁鍒欓〉闈腑涓枃鏄剧ず涔辩爜
        webSettings.setDefaultTextEncodingName("UTF-8");

        // 闄愬埗鍦╓ebView涓墦寮�缃戦〉锛岃�屼笉鐢ㄩ粯璁ゆ祻瑙堝櫒
        myWebView.setWebViewClient(new WebViewClient());

        // 濡傛灉涓嶈缃繖涓紝JS浠ｇ爜涓殑鎸夐挳浼氭樉绀猴紝浣嗘槸鎸変笅鍘诲嵈涓嶅脊鍑哄璇濇
        // Sets the chrome handler. This is an implementation of WebChromeClient
        // for use in handling JavaScript dialogs, favicons, titles, and the
        // progress. This will replace the current handler.
        myWebView.setWebChromeClient(new WebChromeClient()
        {

            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                    JsResult result)
            {
                // TODO Auto-generated method stub
                return super.onJsAlert(view, url, message, result);
            }

        });

        // 鐢↗avaScript璋冪敤Android鍑芥暟锛�
        // 鍏堝缓绔嬫ˉ姊佺被锛屽皢瑕佽皟鐢ㄧ殑Android浠ｇ爜鍐欏叆妗ユ绫荤殑public鍑芥暟
        // 缁戝畾妗ユ绫诲拰WebView涓繍琛岀殑JavaScript浠ｇ爜
        // 灏嗕竴涓璞¤捣涓�涓埆鍚嶄紶鍏ワ紝鍦↗S浠ｇ爜涓敤杩欎釜鍒悕浠ｆ浛杩欎釜瀵硅薄锛屽彲浠ヨ皟鐢ㄨ繖涓璞＄殑涓�浜涙柟娉�
        myWebView.addJavascriptInterface(new WebAppInterface(this),
                "myInterfaceName");

        // 杞藉叆椤甸潰锛氭湰鍦癶tml璧勬簮鏂囦欢
        myWebView.loadUrl("file:///android_asset/sample.html");

        // 杩欓噷鐢ㄤ竴涓狝ndroid鎸夐挳鎸変笅鍚庤皟鐢↗S涓殑浠ｇ爜
        myButton = (Button) findViewById(R.id.button1);
        myButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // 鐢ˋndroid浠ｇ爜璋冪敤JavaScript鍑芥暟锛�
                myWebView.loadUrl("javascript:myFunction()");
                System.out.println("123");
                // 杩欓噷瀹炵幇鐨勬晥鏋滃拰鍦ㄧ綉椤典腑鐐瑰嚮绗竴涓寜閽殑鏁堟灉涓�鑷�

            }
        });

    }

    /**
     * 鑷畾涔夌殑Android浠ｇ爜鍜孞avaScript浠ｇ爜涔嬮棿鐨勬ˉ姊佺被
     * 
     * @author 1
     * 
     */
 
    public class WebAppInterface
    {
        Context mContext;

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c)
        {
            mContext = c;
        }

        /** Show a toast from the web page */
        // 濡傛灉target 澶т簬绛変簬API 17锛屽垯闇�瑕佸姞涓婂涓嬫敞瑙�
        // @JavascriptInterface
        public void showToast(String toast)
        {
            // Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
            Toast.makeText(mContext, toast, Toast.LENGTH_LONG).show();
        }
    }

}
