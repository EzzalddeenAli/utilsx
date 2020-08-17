//package org.kingtec.utils.tools;
//
//import android.annotation.SuppressLint;
//import android.annotation.TargetApi;
//import android.content.ContentUris;
//import android.content.Context;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Environment;
//import android.provider.DocumentsContract;
//import android.provider.MediaStore;
//import android.util.Base64;
//
//import java.io.BufferedReader;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.List;
//
//public class FileSaveUtil {
//    public static final String SD_CARD_PATH = Environment.getExternalStorageDirectory().toString() + "/ObsApp/";
//
//    public static final String image_dir = SD_CARD_PATH
//            + "Images/";
//    public static final String image_blur_dir = SD_CARD_PATH
//            + ".thumb/";
//    public static final String file_dir = SD_CARD_PATH
//            + "Documents/";
//    public static final String REPORTS = SD_CARD_PATH
//            + "REPORTS/";
//    public static final String image_profile = SD_CARD_PATH
//            + "ImageProfile/";
//    /**
//     * SD卡是否存在
//     **/
//
//    public static final String PDF_PATH = getAppPath()
//            + "PDF/";
//
//    public static final String getPdfPath() {
//
//        return getDirPath(PDF_PATH
//                + "/");
//    }
//
//
//    public static boolean isFileExists(File file) {
//        return file.exists();
//    }
//
//    public static String getAppPath() {
//
//        return getDirPath(SD_CARD_PATH);
//    }
//
//    public static File getFile(String path) {
////        final String image_dir = FileSaveUtil.image_profile;
//        File dir = new File(path);
//        return dir;
//    }
//
//    public static String getDirPath(String path) {
//        File dir = new File(path);
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//
//
//        return path;
//    }
//
//
////
////
////    File dir = new File(mDirString);
////			if (!dir.exists()) {
////        dir.mkdirs();
////    }
////
////    String fileNameString = generalFileName();
////    File file = new File(dir, fileNameString);
////
////    mCurrentFilePathString = file.getAbsolutePath();
//
//
//    //
//    private String getSavePicPath() {
//        final String dir = FileSaveUtil.SD_CARD_PATH + "image_data/";
//        try {
//            FileSaveUtil.createSDDirectory(dir);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        String fileName = System.currentTimeMillis() + ".jpg";
//        return dir + fileName;
//    }
//
////
////
////    private void showDialog(final String path) {
////        new Thread(new Runnable() {
////
////            @Override
////            public void run() {
////                // // TODO Auto-generated method stub
////                try {
////                    String GalPicPath = getSavePicPath();
////                    Bitmap bitmap = ImageUtils.changeDrawableColor(path);
////                    boolean isSave = FileSaveUtil.saveBitmap(
////                            PictureUtil.reviewPicRotate(bitmap, GalPicPath),
////                            GalPicPath);
////                    File file = new File(GalPicPath);
////                    if (file.exists() && isSave) {
////                        sendImage(GalPicPath);
////                    }
////                } catch (Exception e) {
////                    e.printStackTrace();
////                }
////            }
////        }).start();
////    }
////
////
////
////
////tbbv.setOnHeadIconClickListener(new HeadIconSelectorView.OnHeadIconClickListener() {
////
////        @SuppressLint("InlinedApi")
////        @Override
////        public void onClick(int from) {
////            switch (from) {
////                case ChatBottomView.FROM_CAMERA:
////                    if (!CAN_WRITE_EXTERNAL_STORAGE) {
////                        Toast.makeText(BaseActivity.this, "权限未开通\n请到设置中开通相册权限", Toast.LENGTH_SHORT).show();
////                    } else {
////                        final String state = Environment.getExternalStorageState();
////                        if (Environment.MEDIA_MOUNTED.equals(state)) {
////                            camPicPath = getSavePicPath();
////                            Intent openCameraIntent = new Intent(
////                                    MediaStore.ACTION_IMAGE_CAPTURE);
////                            Uri uri = Uri.fromFile(new File(camPicPath));
////                            openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
////                            startActivityForResult(openCameraIntent,
////                                    ChatBottomView.FROM_CAMERA);
////                        } else {
////                            showToast("请检查内存卡");
////                        }
////                    }
////                    break;
////                case ChatBottomView.FROM_GALLERY:
////                    if (!CAN_WRITE_EXTERNAL_STORAGE) {
////                        Toast.makeText(BaseActivity.this, "权限未开通\n请到设置中开通相册权限", Toast.LENGTH_SHORT).show();
////                    } else {
////                        String status = Environment.getExternalStorageState();
////                        if (status.equals(Environment.MEDIA_MOUNTED)) {// 判断是否有SD卡
////                            Intent intent = new Intent();
////                            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
////                                intent.setAction(Intent.ACTION_GET_CONTENT);
////                            } else {
////                                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
////                                intent.addCategory(Intent.CATEGORY_OPENABLE);
////                                intent.putExtra("crop", "true");
////                                intent.putExtra("scale", "true");
////                                intent.putExtra("scaleUpIfNeeded", true);
////                            }
////                            intent.setType("image/*");
////                            startActivityForResult(intent,
////                                    ChatBottomView.FROM_GALLERY);
////                        } else {
////                            showToast("没有SD卡");
////                        }
////                    }
////                    break;
////
////                case ChatBottomView.FROM_PHRASE:
////                    if (mess_lv.getVisibility() == View.GONE) {
////                        tbbv.setVisibility(View.GONE);
////                        emoji.setBackgroundResource(R.mipmap.emoji);
////                        voiceIv.setBackgroundResource(R.mipmap.voice_btn_normal);
////                        mess_lv.setVisibility(View.VISIBLE);
////                        KeyBoardUtils.hideKeyBoard(BaseActivity.this,
////                                mEditTextContent);
////                        mess_iv.setBackgroundResource(R.mipmap.chatting_setmode_keyboard_btn_normal);
////                    }
////            }
////        }
////
////    });
////
////
////
////
////
////
////
////
////
////    @TargetApi(23)
////    @Override
////    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
////        // TODO Auto-generated method stub
////        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
////        switch (requestCode) {
////            case SDK_PERMISSION_REQUEST:
////                Map<String, Integer> perms = new HashMap<String, Integer>();
////                // Initial
////                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
////                perms.put(Manifest.permission.RECORD_AUDIO, PackageManager.PERMISSION_GRANTED);
////                // Fill with results
////                for (int i = 0; i < permissions.length; i++)
////                    perms.put(permissions[i], grantResults[i]);
////                // Check for ACCESS_FINE_LOCATION
////                if (perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
////                    // Permission Denied
////                    CAN_WRITE_EXTERNAL_STORAGE = false;
////                    Toast.makeText(this, "禁用图片权限将导致发送图片功能无法使用！", Toast.LENGTH_SHORT)
////                            .show();
////                }
////                if (perms.get(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
////                    CAN_RECORD_AUDIO = false;
////                    Toast.makeText(this, "禁用录制音频权限将导致语音功能无法使用！", Toast.LENGTH_SHORT)
////                            .show();
////                }
////                break;
////            default:
////                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
////        }
////    }
////
////    private boolean CAN_WRITE_EXTERNAL_STORAGE = true;
//
//
//    private boolean hasSD = false;
//    /**
//     * 当前程序包的路径
//     **/
//    private String FILESPATH;
//
//
//    /**
//     * 获取文件夹下的所有文件名
//     */
//    public static List<String> getFileName(String fileName) {
//        List<String> fileList = new ArrayList<String>();
//        String path = fileName; // 路径
//        File f = new File(path);
//        if (!f.exists()) {
//            System.out.println(path + " not exists");
//            return null;
//        }
//
//        File[] fa = f.listFiles();
//        for (int i = 0; i < fa.length; i++) {
//            File fs = fa[i];
//            if (!fs.isDirectory()) {
//                fileList.add(fs.getName());
//            }
//        }
//        return fileList;
//    }
//
//    /**
//     * 在SD卡上创建文件
//     *
//     * @throws IOException
//     */
//    public static File createSDFile(String fileName) throws IOException {
//        File file = new File(fileName);
//        if (!isFileExists(file))
//            if (file.isDirectory()) {
//                file.mkdirs();
//            } else {
//                file.createNewFile();
//            }
//        return file;
//    }
//
//    /**
//     * 在SD卡上创建文件夹
//     *
//     * @throws IOException
//     */
//    public static File createSDDirectory(String fileName) throws IOException {
//        File file = new File(fileName);
//        if (!isFileExists(file))
//            file.mkdirs();
//        return file;
//    }
//
////    /**
////     * @content 存储内容
////     * @file 文件目录
////     * @isAppend 是否追加
////     */
////    public synchronized static void writeString(String content, String file, boolean isAppend) {
////        try {
////            createSDDirectory(saveFn);
////            createSDDirectory(savelistFn);
////            createSDDirectory(savechannelFn);
////            byte[] data = content.getBytes("utf-8");
////            writeBytes(file, data, isAppend);
////        } catch (Exception e) {
////            System.out.println(e.getMessage());
////        }
////
////    }
//
//    public synchronized static boolean writeBytes(String filePath, byte[] data,
//                                                  boolean isAppend) {
//        try {
//            FileOutputStream fos;
//            if (isAppend)
//                fos = new FileOutputStream(filePath, true);
//            else
//                fos = new FileOutputStream(filePath);
//            fos.write(data);
//            fos.close();
//            return true;
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return false;
//    }
//
//    /**
//     * 读取SD卡中文本文件
//     *
//     * @param fileName
//     * @return
//     */
//    public synchronized static String readSDFile(String fileName) {
//        StringBuffer sb = new StringBuffer();
//        File f1 = new File(fileName);
//        String str = null;
//        try {
//            InputStream is = new FileInputStream(f1);
//            InputStreamReader input = new InputStreamReader(is, StandardCharsets.UTF_8);
//            @SuppressWarnings("resource")
//            BufferedReader reader = new BufferedReader(input);
//            while ((str = reader.readLine()) != null) {
//                sb.append(str);
//            }
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return sb.toString();
//    }
//
//    public String getFILESPATH() {
//        return FILESPATH;
//    }
//
//    public boolean hasSD() {
//        return hasSD;
//    }
//
//    /**
//     * 删除单个文件
//     *
//     * @param filePath 被删除文件的文件名
//     * @return 文件删除成功返回true，否则返回false
//     */
//    public static boolean deleteFile(String filePath) {
//        File file = new File(filePath);
//        if (file.isFile() && file.exists()) {
//            return file.delete();
//        }
//        return false;
//    }
//
//    /**
//     * 删除文件夹以及目录下的文件
//     *
//     * @param filePath 被删除目录的文件路径
//     * @return 目录删除成功返回true，否则返回false
//     */
//    public static boolean deleteDirectory(String filePath) {
//        boolean flag = false;
//        // 如果filePath不以文件分隔符结尾，自动添加文件分隔符
//        if (!filePath.endsWith(File.separator)) {
//            filePath = filePath + File.separator;
//        }
//        File dirFile = new File(filePath);
//        if (!dirFile.exists() || !dirFile.isDirectory()) {
//            return false;
//        }
//        flag = true;
//        File[] files = dirFile.listFiles();
//        // 遍历删除文件夹下的所有文件(包括子目录)
//        for (int i = 0; i < files.length; i++) {
//            if (files[i].isFile()) {
//                // 删除子文件
//                flag = deleteFile(files[i].getAbsolutePath());
//                if (!flag)
//                    break;
//            } else {
//                // 删除子目录
//                flag = deleteDirectory(files[i].getAbsolutePath());
//                if (!flag)
//                    break;
//            }
//        }
//        if (!flag)
//            return false;
//        // 删除当前空目录
//        return dirFile.delete();
//    }
//
//    public static boolean saveBitmap(Bitmap bm, String picName) {
//        try {
//            File f = new File(picName);
//            if (f.exists()) {
//                f.delete();
//            }
//            FileOutputStream out = new FileOutputStream(f);
//            bm.compress(Bitmap.CompressFormat.PNG, 100, out);
//            out.flush();
//            out.close();
//            return true;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return false;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public static boolean saveBitmap(Bitmap bm, File f) {
//        try {
//            if (f.exists()) {
//                f.delete();
//            }
//            FileOutputStream out = new FileOutputStream(f);
//            bm.compress(Bitmap.CompressFormat.PNG, 100, out);
//            out.flush();
//            out.close();
//            return true;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return false;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    /**
//     * 把文件转换成base64
//     *
//     * @param path
//     * @return
//     */
//    public static String encodeBase64File(String path) throws Exception {
//        byte[] videoBytes;
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        @SuppressWarnings("resource")
//        FileInputStream fis = new FileInputStream(new File(path));
//        byte[] buf = new byte[1024];
//        int n;
//        while (-1 != (n = fis.read(buf)))
//            baos.write(buf, 0, n);
//        videoBytes = baos.toByteArray();
//        return Base64.encodeToString(videoBytes, Base64.NO_WRAP);
//    }
//
//    /**
//     * 根据相册媒体库路径转换成sd卡路径
//     *
//     * @param context
//     * @param uri
//     * @return
//     */
//    @TargetApi(Build.VERSION_CODES.KITKAT)
//    public static String getPath(final Context context, final Uri uri) {
//        final boolean isOverKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
//        // DocumentProvider
//        if (isOverKitKat && DocumentsContract.isDocumentUri(context, uri)) {
//            // ExternalStorageProvider
//            if (isExternalStorageDocument(uri)) {
//                final String docId = DocumentsContract.getDocumentId(uri);
//                final String[] split = docId.split(":");
//                final String type = split[0];
//                if ("primary".equalsIgnoreCase(type)) {
//                    return Environment.getExternalStorageDirectory() + "/"
//                            + split[1];
//                }
//            }
//            // DownloadsProvider
//            else if (isDownloadsDocument(uri)) {
//                final String id = DocumentsContract.getDocumentId(uri);
//                final Uri contentUri = ContentUris.withAppendedId(
//                        Uri.parse("content://downloads/public_downloads"),
//                        Long.valueOf(id));
//                return getDataColumn(context, contentUri, null, null);
//            }
//            // MediaProvider
//            else if (isMediaDocument(uri)) {
//                final String docId = DocumentsContract.getDocumentId(uri);
//                final String[] split = docId.split(":");
//                final String type = split[0];
//                Uri contentUri = null;
//                if ("image".equals(type)) {
//                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//                } else if ("video".equals(type)) {
//                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
//                } else if ("audio".equals(type)) {
//                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//                }
//                final String selection = "_id=?";
//                final String[] selectionArgs = new String[]{split[1]};
//                return getDataColumn(context, contentUri, selection,
//                        selectionArgs);
//            }
//        }
//        // MediaStore (and general)
//        else if ("content".equalsIgnoreCase(uri.getScheme())) {
//            // Return the remote address
//            if (isGooglePhotosUri(uri))
//                return uri.getLastPathSegment();
//            return getDataColumn(context, uri, null, null);
//        }
//        // File
//        else if ("file".equalsIgnoreCase(uri.getScheme())) {
//            return uri.getPath();
//        }
//        return null;
//    }
//
//    @SuppressLint("NewApi")
//    public static String getDataColumn(Context context, Uri uri,
//                                       String selection, String[] selectionArgs) {
//        Cursor cursor = null;
//        final String column = "_data";
//        final String[] projection = {column};
//        try {
//            cursor = context.getContentResolver().query(uri, projection,
//                    selection, selectionArgs, null);
//            if (cursor != null && cursor.moveToFirst()) {
//                final int index = cursor.getColumnIndexOrThrow(column);
//                return cursor.getString(index);
//            }
//        } finally {
//            if (cursor != null)
//                cursor.close();
//        }
//        return null;
//    }
//
//    public static boolean isGooglePhotosUri(Uri uri) {
//        return "com.google.android.apps.photos.content".equals(uri
//                .getAuthority());
//    }
//
//    public static boolean isDownloadsDocument(Uri uri) {
//        return "com.android.providers.downloads.documents".equals(uri
//                .getAuthority());
//    }
//
//    public static boolean isMediaDocument(Uri uri) {
//        return "com.android.providers.media.documents".equals(uri
//                .getAuthority());
//    }
//
//    public static boolean isExternalStorageDocument(Uri uri) {
//        return "com.android.externalstorage.documents".equals(uri
//                .getAuthority());
//    }
//}