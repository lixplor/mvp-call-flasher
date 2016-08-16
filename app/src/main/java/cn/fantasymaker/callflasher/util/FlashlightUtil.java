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

package cn.fantasymaker.callflasher.util;

import android.hardware.Camera;

/**
 * Created :  2016-08-02
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class FlashlightUtil {

    private static Camera sCamera;
    public static boolean sState;

    public static void turnFlashLight(boolean on) {
        sState = on;
        if (sCamera == null) {
            try {
                sCamera = Camera.open();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        Camera.Parameters parameters = sCamera.getParameters();
        if (parameters != null) {
            if (sState) {
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                sCamera.setParameters(parameters);
            } else {
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                sCamera.setParameters(parameters);
            }
        }
    }

    public static void release() {
        if (sCamera != null) {
            sCamera.release();
            sCamera = null;
        }
    }
}
