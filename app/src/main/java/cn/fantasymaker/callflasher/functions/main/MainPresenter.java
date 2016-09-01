/*
 *     Copyright © 2016 Fantasymaker
 *
 *     Permission is hereby granted, free of charge, to any person obtaining a copy
 *     of this software and associated documentation files (the "Software"), to deal
 *     in the Software without restriction, including without limitation the rights
 *     to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *     copies of the Software, and to permit persons to whom the Software is
 *     furnished to do so, subject to the following conditions:
 *
 *     The above copyright notice and this permission notice shall be included in all
 *     copies or substantial portions of the Software.
 *
 *     THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *     IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *     FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *     AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *     LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *     OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *     SOFTWARE.
 */

package cn.fantasymaker.callflasher.functions.main;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import cn.fantasymaker.callflasher.base.BasePresenterImpl;
import cn.fantasymaker.callflasher.functions.main.MainContract.IMainModel;
import cn.fantasymaker.callflasher.functions.main.MainContract.IMainPresenter;
import cn.fantasymaker.callflasher.functions.main.MainContract.IMainView;
import cn.fantasymaker.callflasher.util.FlashlightUtil;

/**
 * Created :  2016-08-16
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class MainPresenter extends BasePresenterImpl<IMainView> implements IMainPresenter {

    private IMainModel mMainModel;

    public MainPresenter(IMainModel iMainModel) {
        mMainModel = iMainModel;
    }

    @Override
    public void enableFlashlight(boolean enable) {
        boolean success = FlashlightUtil.turnFlashLight(enable);
        if (success && !enable) {
            FlashlightUtil.release();
        } else {
            // TODO: 16/8/17 主线程提示用户无法打开相机闪光灯
        }
        String state = enable ? "开启" : "关闭";
        getView().showSnackMessage("手电筒" + state);
    }

    @Override
    public void alertFlash() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean on = true;
                for (int i = 0; i < 4; i++) {
                    boolean success = FlashlightUtil.turnFlashLight(on);
                    if (success) {
                        if (on) {
                            SystemClock.sleep(10);
                        } else {
                            SystemClock.sleep(50);
                        }
                        on = !on;
                    } else {
                        // TODO: 16/8/17 主线程提示用户无法打开相机闪光灯
                    }
                }
                FlashlightUtil.turnFlashLight(false);
                FlashlightUtil.release();
            }
        }).start();
    }

    @Override
    public void enableFlashService(Context context, boolean enable) {
        Intent intent = new Intent(context, FlashService.class);
        if (enable) {
            getView().showSnackMessage("开启来电闪光功能");
            context.startService(intent);
        } else {
            getView().showSnackMessage("关闭来电闪光功能");
            context.stopService(intent);
        }
    }

    @Override
    public boolean isFlashServiceRunning() {
        return mMainModel.isFlashServiceRunning();
    }

    @Override
    public boolean isBootRun() {
        return mMainModel.isBootRun();
    }

    @Override
    public void setBootRun(boolean isBootRun) {
        mMainModel.setBootRun(isBootRun);
    }
}
