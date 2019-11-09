package com.iakie.iakiehook;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Author: iqiqiya
 * Date: 2019/11/9
 * Time: 19:37
 * Blog: blog.77sec.cn
 * Github: github.com/iqiqiya
 */
public interface Hook {
    boolean canHook(String str);

    void startHook(XC_LoadPackage.LoadPackageParam loadPackageParam)
        throws Throwable;
}
