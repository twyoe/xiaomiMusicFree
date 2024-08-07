package com.xiaomi.music

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class XiaomiMusicModule : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        if (lpparam.packageName != "com.miui.player") return

        XposedHelpers.findAndHookMethod(
            "com.tencent.qqmusiclite.freemode.FreeModeManager",
            lpparam.classLoader,
            "getRemainFreeTime",
            "com.tencent.qqmusiclite.freemode.data.dto.Config",
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam): Any = 114514L
            }
        )

        XposedHelpers.findAndHookMethod(
            "com.tencent.qqmusiclite.freemode.FreeModeManager",
            lpparam.classLoader,
            "isFreeModeEnableAvailable",
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam): Any = true
            }
        )

        XposedHelpers.findAndHookMethod(
            "com.tencent.qqmusiclite.freemode.FreeModeManager",
            lpparam.classLoader,
            "isNoLoginFreeModeEffective",
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam): Any = true
            }
        )

        XposedHelpers.findAndHookMethod(
            "com.tencent.config.AppConfig",
            lpparam.classLoader,
            "isNeedAd",
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam): Any = false
            }
        )
    }
}