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

package cn.fantasymaker.callflasher.listener;

import android.os.SystemClock;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import cn.fantasymaker.callflasher.base.BaseApplication;
import cn.fantasymaker.callflasher.util.FlashlightUtil;

/**
 * Created :  2016-08-02
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class MyPhoneStateListener extends PhoneStateListener {

    private static boolean sCanFlash = false;

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                Log.i(BaseApplication.getAppName(), "挂断状态");
                sCanFlash = false;
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                Log.i(BaseApplication.getAppName(), "来电中, 号码是：" + incomingNumber);
                sCanFlash = true;
                flashLight();
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                Log.i(BaseApplication.getAppName(), "接听中");
                sCanFlash = false;
            default:
                break;
        }
        super.onCallStateChanged(state, incomingNumber);
    }

    private void flashLight() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean on = true;
                while (sCanFlash) {
                    FlashlightUtil.turnFlashLight(on);
                    if (on) {
                        SystemClock.sleep(10);
                    } else {
                        SystemClock.sleep(200);
                    }
                    on = !on;
                }
                FlashlightUtil.turnFlashLight(false);
                FlashlightUtil.release();
            }
        }).start();
    }
}
