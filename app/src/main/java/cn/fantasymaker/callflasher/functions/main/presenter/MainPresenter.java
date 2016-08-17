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

package cn.fantasymaker.callflasher.functions.main.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import cn.fantasymaker.callflasher.base.IBaseView;
import cn.fantasymaker.callflasher.functions.main.FlashService;
import cn.fantasymaker.callflasher.functions.main.view.IMainView;
import cn.fantasymaker.callflasher.util.FlashlightUtil;
import cn.fantasymaker.callflasher.util.ServiceUtil;

/**
 * Created :  2016-08-16
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class MainPresenter implements IMainPresenter {

    private IMainView mMainView;

    @Override
    public void bindView(IBaseView v) {
        mMainView = (IMainView) v;
    }

    @Override
    public void unbindView() {
        mMainView = null;
    }

    @Override
    public void enableFlashlight(boolean enable) {
        FlashlightUtil.turnFlashLight(enable);
        if (!enable) {
            FlashlightUtil.release();
        }
        String state = enable ? "开启" : "关闭";
        mMainView.showSnackMessage("手电筒" + state);
    }

    @Override
    public void enableFlashOnCall(boolean enable) {

    }

    @Override
    public void alertFlash() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean on = true;
                for (int i = 0; i < 4; i++) {
                    FlashlightUtil.turnFlashLight(on);
                    if (on) {
                        SystemClock.sleep(10);
                    } else {
                        SystemClock.sleep(50);
                    }
                    on = !on;
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
            mMainView.showSnackMessage("开启来电闪光功能");
            context.startService(intent);
        } else {
            mMainView.showSnackMessage("关闭来电闪光功能");
            context.stopService(intent);
        }
    }

    @Override
    public boolean isFlashServiceRunning() {
        return ServiceUtil.isServiceRunning(FlashService.class);
    }
}
