package com.bluewebspark.classified.activity;

        import android.os.Bundle;
        import android.support.v7.widget.Toolbar;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;

        import com.bluewebspark.classified.R;

        import butterknife.BindView;
        import butterknife.ButterKnife;

public class ResetPasswordActivity extends BaseActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.imglock)
    ImageView imglock;
    @BindView(R.id.etnewpassword)
    EditText etnewpassword;
    @BindView(R.id.imglocked)
    ImageView imglocked;
    @BindView(R.id.etconfirmpassword)
    EditText etconfirmpassword;
    @BindView(R.id.btnsave)
    Button btnsave;

    @Override
    protected int getContentResId() {
        return R.layout.activity_resetpassword;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setToolbarWithBackButton("Reset Password");

    }

}
