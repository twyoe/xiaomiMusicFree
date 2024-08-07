import com.xiaomi.music;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class HookExample implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        // 确保仅对目标应用生效
        if (!lpparam.packageName.equals("com.miui.player")) {
            return;
        }

        // Hook FreeModeManager.getRemainFreeTime 方法
        findAndHookMethod("com.tencent.qqmusiclite.freemode.FreeModeManager", lpparam.classLoader,
                "getRemainFreeTime", "com.tencent.qqmusiclite.freemode.data.dto.Config", new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        param.setResult(114514L); // 修改返回值
                    }
                });

        // Hook FreeModeManager.isFreeModeEnableAvailable 方法
        findAndHookMethod("com.tencent.qqmusiclite.freemode.FreeModeManager", lpparam.classLoader,
                "isFreeModeEnableAvailable", new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        param.setResult(true);
                    }
                });

        // Hook FreeModeManager.isNoLoginFreeModeEffective 方法
        findAndHookMethod("com.tencent.qqmusiclite.freemode.FreeModeManager", lpparam.classLoader,
                "isNoLoginFreeModeEffective", new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        param.setResult(true);
                    }
                });

        // Hook AppConfig.isNeedAd 方法
        findAndHookMethod("com.tencent.config.AppConfig", lpparam.classLoader,
                "isNeedAd", new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        param.setResult(false);
                    }
                });
    }
}
