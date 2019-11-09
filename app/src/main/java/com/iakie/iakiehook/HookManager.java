package com.iakie.iakiehook;

import android.util.Log;

import com.iakie.iakiehook.module.KuWoMusic;

import java.util.HashSet;
import java.util.Set;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Author: iqiqiya
 * Date: 2019/11/9
 * Time: 19:26
 * Blog: blog.77sec.cn
 * Github: github.com/iqiqiya
 */
public class HookManager implements IXposedHookLoadPackage {

    private Set<Hook> hooks = new HashSet<>();
    public HookManager(){
        this.hooks.add(new KuWoMusic());
    }


    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals(BuildConfig.APPLICATION_ID)){
            XposedHelpers.findAndHookMethod("com.iakie.iakiehook.MainActivity",lpparam.classLoader,"isModuleActive",
                    new Object[]{XC_MethodReplacement.returnConstant(Boolean.valueOf(true))});
        }
        for (Hook hook : this.hooks){
            try {
                if (hook.canHook(lpparam.packageName)){
                    hook.startHook(lpparam);
                }
            } catch (Throwable e){
                Log.i("iakie",e.toString());
            }
        }
    }
}
