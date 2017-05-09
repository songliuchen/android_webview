package tszs.itfreer.webview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity
{
    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sp = getSharedPreferences("tszs_webview", Context.MODE_PRIVATE);
        String url= sp.getString("url","https://www.songliuchen.com");
        if(url == null || url.trim().length()==0)
        {
            url="https://www.songliuchen.com";
        }
        webview = (WebView)findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setBuiltInZoomControls(false);
        webview.getSettings().setDisplayZoomControls(false);
        webview.requestFocus();
        webview.setWebViewClient(new TszsWebViewClient());
        webview.loadUrl(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            Intent intent = new Intent(MainActivity.this,SettingActivity.class);
            startActivityForResult(intent,100);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack())
        {
            webview.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // 回调方法，从第二个页面回来的时候会执行这个方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == 100 && resultCode ==100)
        {
            SharedPreferences sp = getSharedPreferences("tszs_webview", Context.MODE_WORLD_READABLE);
            String url = sp.getString("url", "https://www.songliuchen.com");
            webview.loadUrl(url);
        }
    }
    public class TszsWebViewClient extends WebViewClient
    {
    }
}
