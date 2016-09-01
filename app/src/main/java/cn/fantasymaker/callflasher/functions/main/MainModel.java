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

import cn.fantasymaker.callflasher.util.ServiceUtil;
import cn.fantasymaker.callflasher.util.SharedpreferencesUtil;

/**
 * Created :  2016-08-31
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class MainModel implements MainContract.IMainModel {

    private static final String KEY_BOOTRUN = "isBootRun";

    @Override
    public boolean isBootRun() {
        return SharedpreferencesUtil.getBoolean(KEY_BOOTRUN, false);
    }

    @Override
    public void setBootRun(boolean bootRun) {
        SharedpreferencesUtil.putBoolean(KEY_BOOTRUN, bootRun);
    }

    @Override
    public boolean isFlashServiceRunning() {
        return ServiceUtil.isServiceRunning(FlashService.class);
    }
}
