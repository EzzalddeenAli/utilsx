//package org.kingtec.utils.tools;
//
//import android.content.Context;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.content.res.Resources;
//import android.graphics.Point;
//import android.os.Build;
//import android.util.TypedValue;
//import android.view.Display;
//import android.view.WindowManager;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import androidx.annotation.IdRes;
//import androidx.annotation.IntDef;
//import androidx.annotation.LayoutRes;
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.widget.SearchView;
//import androidx.fragment.app.DialogFragment;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//import androidx.recyclerview.widget.DividerItemDecoration;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
///**
// * Created by gaoxuan on 2016/10/1.
// */
//public class Utils {
//    private static HashMap<String, String> path2NameMap = new HashMap<>();
//
//
//    public static void initPath2NameMap(HashMap hashMap) {
//        path2NameMap = hashMap;
//    }
//
//    public static String getPathFromName(String name) {
//        if (path2NameMap.containsKey(name))
//            return path2NameMap.get(name);
//        return name;
//    }
//
//
//    public static void addFragmentToActivity(FragmentManager fragmentManager, Fragment fragment, int frameId) {
//        checkNotNull(fragmentManager);
//        checkNotNull(fragment);
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.add(frameId, fragment);
//        transaction.commitAllowingStateLoss();
//    }
//
//    public static <T> T checkNotNull(T reference) {
//        if (reference == null) {
//            throw new NullPointerException();
//        }
//        return reference;
//    }
//
//    public static boolean checkHasMatchApp(String packageName, Context context) {
//        final PackageManager packageManager = context.getPackageManager();
//        List<PackageInfo> packages = packageManager.getInstalledPackages(0);
//        if (packages != null) {
//            for (int i = 0; i < packages.size(); i++) {
//                String pn = packages.get(i).packageName;
//                if (pn.equals(packageName))
//                    return true;
//            }
//        }
//        return false;
//    }
//
//    public static int dp2px(int dpSize, Context context) {
//        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpSize, context.getResources().getDisplayMetrics());
//    }
//
//    public static void addIndex(ArrayList<String> indexList, int currentIndex) {
//        if (indexList == null) return;
//        if (indexList.contains(String.valueOf(currentIndex))) {
//            for (int index = 0; index < indexList.size(); index++) {
//                if (indexList.get(index).equals(String.valueOf(currentIndex))) {
//                    indexList.remove(index);
//                    break;
//                }
//            }
//        }
//        indexList.add(String.valueOf(currentIndex));
//    }
//
//    public static void removeIndex(ArrayList<String> indexList, int currentIndex) {
//        if (!indexList.contains(String.valueOf(currentIndex))) return;
//        if (indexList.contains(String.valueOf(currentIndex))) {
//            for (int index = 0; index < indexList.size(); index++) {
//                if (indexList.get(index).equals(String.valueOf(currentIndex))) {
//                    indexList.remove(index);
//                    return;
//                }
//            }
//        }
//    }
//
//    private static int screenWidth = 0;
//    private static int screenHeight = 0;
//
//    public static int dpToPx(int dp) {
//        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
//    }
//
//    public static int getScreenHeight(Context c) {
//        if (screenHeight == 0) {
//            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
//            Display display = wm.getDefaultDisplay();
//            Point size = new Point();
//            display.getSize(size);
//            screenHeight = size.y;
//        }
//
//        return screenHeight;
//    }
//
//    public static int getScreenWidth(Context c) {
//        if (screenWidth == 0) {
//            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
//            Display display = wm.getDefaultDisplay();
//            Point size = new Point();
//            display.getSize(size);
//            screenWidth = size.x;
//        }
//
//        return screenWidth;
//    }
//
//    public static boolean isAndroid5() {
//        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
//    }
//}
