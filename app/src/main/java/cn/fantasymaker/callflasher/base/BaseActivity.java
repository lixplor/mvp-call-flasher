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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import cn.fantasymaker.callflasher.base.BaseContract.IBaseView;

/**
 * Created :  2016-08-16
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public abstract class BaseActivity<V, P extends BasePresenterImpl> extends AppCompatActivity implements IBaseView<P>{

    protected P mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        mPresenter = createPresenter();
        if (!mPresenter.isViewBound()) {
            mPresenter.bindView((V) this);
        }
        initView();
        initData(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbindView();
    }

    /**
     * 设置布局, 为setContentView传入布局id
     *
     * @return 布局id
     */
    protected abstract int setLayout();

//    /**
//     * 创建Presenter
//     *
//     * @return 实现类的presenter
//     */
//    protected abstract P createPresenter();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 执行其他任务
     *
     * @param savedInstanceState 保存状态对象
     */
    protected abstract void initData(Bundle savedInstanceState);
}
