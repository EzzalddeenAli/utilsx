//package org.kingtec.utils.Base;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.widget.ImageView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.Priority;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.bumptech.glide.load.resource.drawable.GlideDrawable;
//import com.bumptech.glide.request.RequestListener;
//import com.bumptech.glide.request.target.SimpleTarget;
//
//public class ImageSetter {
//
//    /**
//     * This method will set image into ImageView without using placeholder.
//     * it will handel caching.
//     * */
//    public static void setImage(Context context, String imagePath, ImageView imageView) {
//        try
//        {
//            Glide.with(context)
//                    .load(imagePath) // it can be a remote URL or a local absolute file path.
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .priority(Priority.IMMEDIATE)
//                    .skipMemoryCache(false)
//                    .dontAnimate()
//                    .into(imageView);
//
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }
//    }
//
//    /**
//     * This method will set image into ImageView with loading time placeholder
//     * and also with error.
//     * */
//    public static void setImage(Context context, String imagePath, ImageView imageView, int placeholder, int errImage) {
//        try
//        {
//            Glide.with(context)
//                    .load(imagePath)
//                    .placeholder(placeholder)
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .priority(Priority.IMMEDIATE)
//                    .error(errImage)
//                    .skipMemoryCache(false)
//                    .dontAnimate()
//                    .into(imageView);
//
//        }catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }
//    }
//
//    /**
//     * Load image with caching, placeholder and result callback.
//     * */
//    public static void setImage(Context context, String imagePath, ImageView imageView, int placeholder, RequestListener<String, GlideDrawable> listener) {
//        try
//        {
//            Glide.with(context)
//                    .load(imagePath)
//                    .placeholder(placeholder)
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .priority(Priority.IMMEDIATE)
//                    .error(placeholder)
//                    .skipMemoryCache(false)
//                    .dontAnimate()
//                    .listener(listener)
//                    .into(imageView);
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }
//    }
//
//    /**
//     * Download image from remote URL and get callback of result.
//     * */
//    public static void downloadImage(Context context, String imageSource, SimpleTarget<Bitmap> listener) {
//        try
//        {
//            Glide.with(context)
//                    .load(imageSource)
//                    .asBitmap()
//                    .into(listener);
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }
//    }
//}
//
