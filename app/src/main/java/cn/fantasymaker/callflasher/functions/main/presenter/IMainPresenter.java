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

import cn.fantasymaker.callflasher.base.IBasePresenter;

/**
 * Created :  2016-08-16
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public interface IMainPresenter extends IBasePresenter {

    /**
     * 开启/关闭闪光灯常亮
     *
     * @param enable true开启; 否则false
     */
    void enableFlashlight(boolean enable);

    /**
     * 开启/关闭来电闪光
     *
     * @param enable true开启; 否则false
     */
    void enableFlashOnCall(boolean enable);

    /**
     * 闪烁提醒
     */
    void alertFlash();

    /**
     * 开启/关闭来电状态监听的前台服务
     *
     * @param enable true开启; false关闭
     */
    void enableFlashService(Context context, boolean enable);

    /**
     * 来电闪服务是否正在运行
     *
     * @return true则正在运行; 否则false
     */
    boolean isFlashServiceRunning();
}
