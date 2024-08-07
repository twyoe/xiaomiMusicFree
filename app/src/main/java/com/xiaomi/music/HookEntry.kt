package com.xiaomi.music

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class XiaomiMusicModule : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        if (lpparam.packageName != "com.miui.player") return

        // Hook FreeModeManager.getRemainFreeTime
        XposedHelpers.findAndHookMethod(
            "com.tencent.qqmusiclite.freemode.FreeModeManager",
            lpparam.classLoader,
            "getRemainFreeTime",
            "com.tencent.qqmusiclite.freemode.data.dto.Config",
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam): Any {
                    return 114514L
                }
            }
        )

        // Hook FreeModeManager.isFreeModeEnableAvailable
        XposedHelpers.findAndHookMethod(
            "com.tencent.qqmusiclite.freemode.FreeModeManager",
            lpparam.classLoader,
            "isFreeModeEnableAvailable",
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam): Any {
                    return true
                }
            }
        )

        // Hook FreeModeManager.isNoLoginFreeModeEffective
        XposedHelpers.findAndHookMethod(
            "com.tencent.qqmusiclite.freemode.FreeModeManager",
            lpparam.classLoader,
            "isNoLoginFreeModeEffective",
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam): Any {
                    return true
                }
            }
        )

        // Hook AppConfig.isNeedAd
        XposedHelpers.findAndHookMethod(
            "com.tencent.config.AppConfig",
            lpparam.classLoader,
            "isNeedAd",
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam): Any {
                    return false
                }
            }
        )
    }
}