package com.mrabid.pro_maker.Activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.mrabid.pro_maker.R;

public class ChatActivity extends AppCompatActivity {

    public long id_user;
    ImageButton addSomething, addSomethingBack;
    RecyclerView recyclerView;
    RelativeLayout rltHiddenPersonalChat;
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
                downDialog = new Dialog(ChatActivity.this);
                downDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                WindowManager.LayoutParams wmlp = downDialog.getWindow().getAttributes();
                wmlp.gravity = Gravity.TOP | Gravity.LEFT;
                wmlp.x = 0;
                wmlp.y = toolbar.getHeight();   //y position
                toolbar.bringToFront();
                downDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                downDialog.setContentView(R.layout.dialog_chat_personal);
                downDialog.show();
            }

        return super.onOptionsItemSelected(item);
    }
}
