package cn.fantasymaker.callflasher.functions.main.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.fantasymaker.callflasher.R;
import cn.fantasymaker.callflasher.base.BaseActivity;
import cn.fantasymaker.callflasher.functions.main.model.MainModel;
import cn.fantasymaker.callflasher.functions.main.presenter.MainPresenter;
import cn.fantasymaker.callflasher.util.FlashlightUtil;

public class MainActivity extends BaseActivity<MainActivity, MainPresenter> implements IMainView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.switch_enable_flash)
    SwitchCompat mSwitchEnableFlash;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.switch_boot_run)
    SwitchCompat mSwitchBootRun;

    private Snackbar mSnackbar;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(new MainModel());
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mSwitchEnableFlash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!mPresenter.isFlashServiceRunning()) {
                        mPresenter.alertFlash(); //先闪两下提示用户, 如权限被拦截可帮助调起权限授权供用户允许
                        mPresenter.enableFlashService(MainActivity.this, isChecked);
                    }
                } else {
                    mPresenter.enableFlashService(MainActivity.this, isChecked);
                }
            }
        });
        mSwitchBootRun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPresenter.setBootRun(isChecked);
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mSwitchEnableFlash.setChecked(mPresenter.isFlashServiceRunning());
        mSwitchBootRun.setChecked(mPresenter.isBootRun());
    }


    @OnClick({R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                mPresenter.enableFlashlight(!FlashlightUtil.sState);
                break;
        }
    }

    @Override
    public void showSnackMessage(String message) {
        if (mSnackbar == null) {
            mSnackbar = Snackbar.make(mFab, message, Snackbar.LENGTH_SHORT);
        }
        mSnackbar.setText(message);
        mSnackbar.show();
    }
}
