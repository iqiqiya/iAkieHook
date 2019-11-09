package com.iakie.iakiehook.module;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.iakie.iakiehook.Hook;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Author: iqiqiya
 * Date: 2019/11/9
 * Time: 19:42
 * Blog: blog.77sec.cn
 * Github: github.com/iqiqiya
 * Version: 适配酷我音乐v9.2.5.0
 */
public class KuWoMusic implements Hook {

    private Class list;

    @Override
    public boolean canHook(String str) {
        return str.equals("cn.kuwo.player");
    }

    @Override
    public void startHook(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        StringBuilder sb = new StringBuilder();
        sb.append("start Hook KuWo: ");
        sb.append(loadPackageParam.packageName);
        Log.i("kuwo", sb.toString());
        doHook(loadPackageParam);
    }

    private void doHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        Log.i("iAkie","酷我音乐VIP开启成功");
        XposedBridge.log("酷我音乐VIP开启成功");
        //开始准备
        //构造参数类型
        try {
            list = Class.forName("java.util.List");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ClassLoader classLoader = loadPackageParam.classLoader;
        //对比直接赋值true可以提高空间和时间性能
        Boolean valueOf = Boolean.valueOf(true);

        String classA = "cn.kuwo.mod.vipreal.VipRealInfo";
        String methodA = "getState";
        /**原方法
         * public int getState() {
         *         return 1;
         *     }
         */

        String classB = "cn.kuwo.mod.vipnew.ConsumptionQueryUtil";
        String methodB = "hasBought";
        /**原方法
         * private boolean hasBought(long j, List<String> list) {
         *         return true;
         *     }
         */

        String classC = "cn.kuwo.mod.shortcut.ShortcutsMgr";
        String methodC = "prepareShortcut";
        /**原方法
         * public static boolean prepareShortcut(Intent intent) {
         *         if (intent != null && VERSION.SDK_INT >= 25) {
         *             return N_MR1_ShortcutsMgr.prepareShortcut(intent);
         *         }
         *         return false;
         *     }
         */

        XposedHelpers.findAndHookMethod(classA, classLoader, methodA, new Object[]{
                XC_MethodReplacement.returnConstant(Integer.valueOf(1))
        });

        XposedHelpers.findAndHookMethod(classB, classLoader, methodB, new Object[]{
                Long.TYPE, list, XC_MethodReplacement.returnConstant(valueOf)
        });

        XposedHelpers.findAndHookMethod(classC, classLoader, methodC, new Object[]{
                Intent.class, XC_MethodReplacement.returnConstant(valueOf)
        });
    }
}
