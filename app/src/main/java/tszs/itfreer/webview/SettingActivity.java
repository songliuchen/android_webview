package tszs.itfreer.webview;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class SettingActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    TextView textview=(TextView) findViewById(R.id.textView);
                    URL urlobj = new URL(textview.getText().toString());

                    SharedPreferences sp = SettingActivity.this.getSharedPreferences("tszs_webview", Context.MODE_PRIVATE);
                    sp.edit().putString("url", textview.getText().toString());
                    sp.edit().commit();

                    Toast toast=Toast.makeText(getApplicationContext(), "地址修改成功！", Toast.LENGTH_SHORT);
                    toast.show();
                    SettingActivity.this.finish();
                }
                catch (MalformedURLException e)
                {
                    Toast toast=Toast.makeText(getApplicationContext(), "输入的url不合法！", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}