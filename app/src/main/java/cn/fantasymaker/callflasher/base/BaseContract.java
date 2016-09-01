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

package cn.fantasymaker.callflasher.base;

/**
 * Created :  2016-09-01
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public interface BaseContract {

    interface IBaseModel {

    }

    interface IBaseView<P> {

        /**
         * 创建Presenter
         *
         * @return 实现类的presenter
         */
        P createPresenter();
    }

    interface IBasePresenter<V> {

        /**
         * P绑定V
         *
         * @param v 要绑定的view
         */
        void bindView(V v);

        /**
         * 断开P和V的关联, 避免内存泄漏
         */
        void unbindView();

        /**
         * 判断V是否已经绑定
         *
         * @return true绑定; 否则false
         */
        boolean isViewBound();

        /**
         * 获取V
         *
         * @return 实现类view
         */
        V getView();
    }
}
