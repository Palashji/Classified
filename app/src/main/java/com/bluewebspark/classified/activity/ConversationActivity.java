package com.bluewebspark.classified.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bluewebspark.classified.AppController;
import com.bluewebspark.classified.R;
import com.bluewebspark.classified.Utility.S;
import com.bluewebspark.classified.app.Config;
import com.bluewebspark.classified.data.datahelper.UserDataHelper;
import com.bluewebspark.classified.utils.ApiClient;
import com.bluewebspark.classified.utils.ApiInterface;
import com.bluewebspark.classified.utils.Helper;
import com.bluewebspark.classified.utils.JSONParser;
import com.bluewebspark.classified.utils.KeyboardUtils;
import com.bluewebspark.classified.utils.NotificationUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sohel on 3/31/2017.
 */

/*public class ConversationActivity extends BaseActivity implements View.OnClickListener {
    RelativeLayout sendMessage;
    int channel_id;
    EditText editEmojicon;
    @BindView(R.id.userImage)
    CircleImageView userImage;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.fabProgress)
    ProgressBar fabProgress;
    LinearLayout inputeTextLayout;
    ImageView emojiIcon;
    private Handler mainThreadHandler;
    private static final int DELAY_SHOWING_SMILE_PANEL = 200;
   private ImageButton mentionBtn;
   private ArrayList<Messages> msgArrayList;
    ConversationAdapter adapter_1;

    private String id;
    private String name;
    private String image;
    private String callingFrom;
    private String user_id;

    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private AppController appController;

    @Override
    protected int getContentResId() {
        return R.layout.chat_single_chat_screen;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        *//*appController= (AppController) this.getApplicationContext();
        appController.setCurrentActivity(this);*//*

        mainThreadHandler = new Handler(Looper.getMainLooper());
        msgArrayList = new ArrayList<>();

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        image = getIntent().getStringExtra("image");
        callingFrom = getIntent().getStringExtra("callingFrom");

        S.E("name : : " + name);

        setToolbarWithBackButton("");

        title.setText(name);
        if (!image.equals(""))
            S.setImageByPicasoProfile(ConversationActivity.this, image, userImage, null);

        initRecyclerView();
        initViews();
        initListener();
        msgArrayList.clear();

        user_id = UserDataHelper.getInstance().getUserDataModel().get(0).getUserId();

        msgArrayList.clear();
        getMessages();

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                S.E("call get message");
                msgArrayList.clear();
                getMessages();
            }
        };
    }

    private void setSmilePanelIcon(int resourceId) {
        emojiIcon.setImageResource(resourceId);
    }

    private void initListener() {
        emojiIcon.setOnClickListener(this);
        sendMessage.setOnClickListener(this);
        mentionBtn.setOnClickListener(this);
        editEmojicon.setOnClickListener(this);
    }

    private void initViews() {
        mLayoutManager.setStackFromEnd(true);
        editEmojicon = (EditText) findViewById(R.id.editEmojicon);
        sendMessage = (RelativeLayout) findViewById(R.id.send);
        inputeTextLayout = (LinearLayout) findViewById(R.id.inpute_TextLayout);
        emojiIcon = (ImageView) findViewById(R.id.emojiIcon);
        mentionBtn = (ImageButton) findViewById(R.id.mentionBtn);
    }

    protected void initRecyclerView() {
        initViewFlipper();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        // The number of Columns
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void getMessages() {
        setMainData();
        new JSONParser(this).parseRetrofitRequestWithautProgress(ApiClient.getClient().create(ApiInterface.class).chatting_history(
                user_id,
                id
        ), new Helper() {
            @Override
            public void backResponse(String response) {
                S.E("chat history response : " + response);
                if (!response.equals("error")) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("response");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject json = jsonArray.getJSONObject(i);
                            Messages messages = new Messages();
                            messages.setMessageBy(json.getString("messageBy"));
                            messages.setMessageFor(json.getString("messageFor"));
                            messages.setMessage(json.getString("message"));
                            messages.setMessageDate(json.getString("messageDate"));
                            messages.setByUserProfile(json.getString("byUserProfile"));
                            messages.setForUserProfile(json.getString("forUserProfile"));
                            msgArrayList.add(messages);
                        }
                        adapter_1 = new ConversationAdapter(ConversationActivity.this, msgArrayList);
                        mRecyclerView.setAdapter(adapter_1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        *//*String user_id = UserDataHelper.getInstance().getUserDataModel().get(0).getUserId();

        msgArrayList.add(new Messages(user_id, "2", "Hello, How are you?", "08/05/2018", "", ""));
        msgArrayList.add(new Messages(user_id, "2", "Hello, How are you?", "08/05/2018", "", ""));
        msgArrayList.add(new Messages("2", user_id, "Hello, How are you?", "08/05/2018", "", ""));
        msgArrayList.add(new Messages(user_id, "2", "Hello, How are you?", "08/05/2018", "", ""));
        msgArrayList.add(new Messages(user_id, "2", "Hello, How are you?", "08/05/2018", "", ""));
        msgArrayList.add(new Messages("2", user_id, "Hello, How are you?", "08/05/2018", "", ""));
        msgArrayList.add(new Messages(user_id, "2", "Hello, How are you?", "08/05/2018", "", ""));
        msgArrayList.add(new Messages(user_id, "2", "Hello, How are you?", "08/05/2018", "", ""));
        msgArrayList.add(new Messages("2", user_id, "Hello, How are you?", "08/05/2018", "", ""));
        adapter_1 = new ConversationAdapter(ConversationActivity.this, msgArrayList);
        mRecyclerView.setAdapter(adapter_1);*//*
    }

    private void visibleOrHideSmilePanel() {
        if (isSmilesLayoutShowing()) {
            KeyboardUtils.showKeyboard(ConversationActivity.this);
        } else {
            KeyboardUtils.hideKeyboard(ConversationActivity.this);
            showSmileLayout();
        }
        editEmojicon.requestFocus();
    }

    private boolean isSmilesLayoutShowing() {
//        return emojiconsFragment.getVisibility() == View.VISIBLE;
        return false;
    }

    private void showSmileLayout() {
        mainThreadHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                emojiconsFragment.setVisibility(View.VISIBLE);
                setSmilePanelIcon(R.drawable.chat_keyboard);
            }
        }, DELAY_SHOWING_SMILE_PANEL);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send:
                setMessageTo();
                editEmojicon.getText().clear();
                msgArrayList.clear();
                getMessages();
//                msgArrayList.add(new Messages("1", "1", "2", editEmojicon.getText().toString(), "1", "2", "30/03/2018"));
//                adapter_1.notifyDataSetChanged();
                editEmojicon.getText().clear();
                break;
            case R.id.emojiIcon:
                visibleOrHideSmilePanel();
                break;
            case R.id.mentionBtn:
                break;
            case R.id.editEmojicon:
                break;
        }
    }

    private void setMessageTo() {
        fabProgress.setVisibility(View.VISIBLE);
        new JSONParser(this).parseRetrofitRequestWithautProgress(ApiClient.getClient().create(ApiInterface.class).sendChatMessage(
                user_id,
                id,
                editEmojicon.getText().toString()
        ), new Helper() {
            @Override
            public void backResponse(String response) {
                S.E("send chat message response : " + response);
                fabProgress.setVisibility(View.GONE);
                msgArrayList.clear();
                getMessages();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (callingFrom.equals("adapter")) {
            if (UserDataHelper.getInstance().getUserDataModel().get(0).getUserType().equals("1")) {
                Bundle bundle = new Bundle();
                bundle.putString("callingFor", "chat");
                S.I_clear(ConversationActivity.this, TrainerMainActivity.class, bundle);
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("callingFor", "chat");
                S.I_clear(ConversationActivity.this, TraineeMainActivity.class, bundle);
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        notificationCallFromHandler();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(Config.REGISTRATION_COMPLETE));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(Config.PUSH_NOTIFICATION));
        NotificationUtils.clearNotifications(this);
        *//*appController = (AppController) this.getApplicationContext();
        appController.setCurrentActivity(this);*//*
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (UserDataHelper.getInstance().getUserDataModel().get(0).getUserType().equals("1")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("callingFor", "chat");
                    S.I_clear(ConversationActivity.this, TrainerMainActivity.class, bundle);
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("callingFor", "chat");
                    S.I_clear(ConversationActivity.this, TraineeMainActivity.class, bundle);
                }
                break;
        }
        return true;
    }
}*/