package com.limpiezas.neteo.ui.conocenos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.limpiezas.neteo.ui.BaseToolbarActivity;
import com.limpiezas.neteo.R;
import com.limpiezas.neteo.ui.menu.MenuFragment;

import butterknife.BindView;

/**
 * Created by alavpa on 3/8/16.
 */
public class ConocenosActivity extends BaseToolbarActivity implements ConocenosView{

    @BindView(R.id.map)
    WebView map;
    @BindView(R.id.tv_email)
    TextView tv_email;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_facebook)
    TextView tv_facebook;
    @BindView(R.id.tv_twitter)
    TextView tv_twitter;
    @BindView(R.id.tv_web)
    TextView tv_web;

    ConocenosPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conocenos);

        initMenu(MenuFragment.MENU_CONOCENOS);

        presenter = new ConocenosPresenter(this);
        presenter.init();

        map.getSettings().setJavaScriptEnabled(true);
        map.loadUrl("file:///android_asset/map.html");

        tv_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickEmail();
            }
        });

        tv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickTel();
            }
        });

        tv_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickFacebook();
            }
        });

        tv_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickTwitter();
            }
        });

        tv_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickWeb();
            }
        });

    }

    @Override
    public void sendEmail(String subject, String to){
        Intent intent = getEmailIntent(subject,"",to,null);
        startActivity(intent);
    }

    @Override
    public void call(String tel) {
        Intent intent = getCallIntent(tel);
        startActivity(intent);
    }

    @Override
    public void openFacebook(String url) {
        Intent intent = getUrlIntent(url);
        startActivity(intent);

    }

    @Override
    public void openTwitter(String url) {
        Intent intent = getUrlIntent(url);
        startActivity(intent);
    }

    @Override
    public void openWeb(String url) {
        Intent intent = getUrlIntent(url);
        startActivity(intent);
    }

    public Intent getEmailIntent(String subject, @NonNull String text, @Nullable String receiver, @Nullable Uri attachment) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("message/rfc822");

        if (TextUtils.isEmpty(receiver)) {
            shareIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{});
        }
        else {
            shareIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { receiver });
        }
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);

        if (attachment != null) {
            shareIntent.putExtra(Intent.EXTRA_STREAM, attachment);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }

        return Intent.createChooser(shareIntent, null);
    }

    public Intent getCallIntent(String phone){


        String uri = "tel:" + phone ;
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(uri));
        return intent;
    }

    public Intent getUrlIntent(String url){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        return intent;
    }
}
