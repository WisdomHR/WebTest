package com.lhr.webtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    //1. xml 에 선언해둔 view들과 연결할 변수들 선언
    Button mMove, mBefore, mHome, mForward;
    EditText mEdit;
    WebView mWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //2. 선언한 변수들과 xml 파일의 뷰들을 연결
        mMove = (Button)findViewById(R.id.move);
        mBefore = (Button)findViewById(R.id.before1);
        mEdit = (EditText)findViewById(R.id.edit);
        mWeb = (WebView)findViewById(R.id.web);
        mHome = (Button)findViewById(R.id.home);
        mForward = (Button)findViewById(R.id.forward);

        //3. 이벤트 처리
        mMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String : 문자열
                String url = mEdit.getText().toString();//사용자가 EditText에 입력한 글자들

                if(url.startsWith("http://") != true)
                    url = "http://" + url;

                //사용자가 입력한 url 정보를 WebView에 load시키기
                mWeb.loadUrl(url);
            }
        });

        mBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mWeb.canGoBack() == true)
                {
                    mWeb.goBack();
                }
            }
        });

        mHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //daum.net 페이지로 이동
                mWeb.loadUrl("http://www.daum.net");
            }
        });
        mForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음 페이지가 존재하면 이동
                if(mWeb.canGoForward() == true)
                    mWeb.goForward();
            }
        });
        mWeb.setWebViewClient(new MyWebViewClient());
    }
}

class MyWebViewClient extends WebViewClient
{
    //ctrl + o 누르기
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return super.shouldOverrideUrlLoading(view, url);
    }
}
