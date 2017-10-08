package com.mrabid.pro_maker.Activity;

import android.app.Dialog;
import android.content.ClipData;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.mrabid.pro_maker.R;

public class ChatActivity extends AppCompatActivity {

    public long id_user;
    ImageButton addSomething, addSomethingBack;
    RecyclerView recyclerView;
    RelativeLayout rltHiddenPersonalChat,hiddenToolbar;
    Dialog downDialog;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Abid");
        toolbar.setTitleTextColor(Color.WHITE);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_message_list);
        addSomething = (ImageButton)findViewById(R.id.add_something);
        addSomethingBack = (ImageButton)findViewById(R.id.add_something_back);
        rltHiddenPersonalChat = (RelativeLayout) findViewById(R.id.rlt_hiddenPersonalChat);
        hiddenToolbar = (RelativeLayout) findViewById(R.id.hidden_toolbar_personal_chat);

        addSomething.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSomething.setVisibility(View.GONE);
                addSomethingBack.setVisibility(View.VISIBLE);
                rltHiddenPersonalChat.setVisibility(View.VISIBLE);
            }
        });

        addSomethingBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSomethingBack.setVisibility(View.GONE);
                addSomething.setVisibility(View.VISIBLE);
                rltHiddenPersonalChat.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_personal_chat,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
            if(id == R.id.action_arrow_down){
                item.setVisible(false);
                hiddenToolbar.setVisibility(View.VISIBLE);
                Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down_tollbar);
                hiddenToolbar.startAnimation(slideDown);

                /*downDialog = new Dialog(ChatActivity.this);
                downDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                WindowManager.LayoutParams wmlp = downDialog.getWindow().getAttributes();
                wmlp.gravity = Gravity.TOP | Gravity.LEFT;
                wmlp.x = 0;
                wmlp.y = toolbar.getHeight();   //y position
                toolbar.bringToFront();
                toolbar.invalidate();
                downDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                downDialog.setContentView(R.layout.dialog_chat_personal);
                downDialog.show();*/
            }

        return super.onOptionsItemSelected(item);
    }
}
