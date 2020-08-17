package org.kingtec.utils.tools;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class FileUtil {
    private static final String SD_CARD_PATH = Environment.getExternalStorageDirectory().toString() + "/AmanaApp/";

    public static final String PDF_PATH = getAppPath()
            + ".images/";
    public static final String PDF_TEMP = getAppPath()
            + ".temps/";
    public static final String DEC_PATH = getAppPath()
            + "reports/";

    public static String getImagesPath() {

        return getDirPath(PDF_PATH);
    }

    public static String getDecPath() {

        return getDirPath(DEC_PATH);
    }

    public static String getTempPath() {

        return getDirPath(PDF_TEMP);
    }

    public static File getFilePath(String path) {

        return new File(getAppPath(
        ), path);
    }

    public static File getTempPath(String path) {

        return new File(getTempPath(
        ), clearToB64(path));
    }

    public static String clearToB64(String text) {
//        Log.d(TAG, "removeFirebaseForbiddenChars");
        String toReplace = "";
        text = Base64.encodeToString(text.getBytes(), Base64.DEFAULT);
        text = text.replace("https://firebasestorage.googleapis.com/", toReplace);
        text = text.replace(".", toReplace);
        text = text.replace("$", toReplace);
        text = text.replace("#", toReplace);
        text = text.replace("[", toReplace);
        text = text.replace("]", toReplace);
        text = text.replace("|", toReplace);
        text = text.replace("\"", toReplace);
        text = text.replace("/", toReplace);
        text = text.replace("&", toReplace);
        text = text.replace("=", toReplace);
        text = text.replace("?", toReplace);
        text = text.replace("@", toReplace);
        text = text.replace("'", toReplace);
        return text;
    }

    public static boolean isFileExists(File file) {
        return file.exists();
    }

    public static String getAppPath() {
        return getDirPath(SD_CARD_PATH);
    }

    public static File getFile(String path) {
//        final String image_dir = FileSaveUtil.image_profile;
        File dir = new File(path);
        return dir;
    }

    public static String getDirPath(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }


        return path;
    }

    private static final int EOF = -1;
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    private FileUtil() {

    }

    public static String readFile(String filePath) {
        String fileContent = "";
        File file = new File(filePath);
        if (file == null || !file.isFile()) {
            return null;
        }

        BufferedReader reader = null;
        try {
            InputStreamReader is = new InputStreamReader(new FileInputStream(file));
            reader = new BufferedReader(is);
            String line = null;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                fileContent += line + " ";
            }
            reader.close();
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileContent;
    }

    public static String readFile(InputStream inputStream) {
        String fileContent = "";

        BufferedReader reader = null;
        try {
            InputStreamReader is = new InputStreamReader(inputStream);
            reader = new BufferedReader(is);
            String line = null;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                fileContent += line + " ";
            }
            reader.close();
            inputStream.close();
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileContent;
    }

    private static ArrayList<String> getDevMountList() {
        String[] toSearch = readFile("/system/etc/vold.fstab").split(" ");
        ArrayList<String> out = new ArrayList<String>();
        for (int i = 0; i < toSearch.length; i++) {
            if (toSearch[i].contains("dev_mount")) {
                if (new File(toSearch[i + 2]).exists()) {
                    out.add(toSearch[i + 2]);
                }
            }
        }
        return out;
    }

    public static String getExternalSdCardPath() {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sdCardFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
            return sdCardFile.getAbsolutePath();
        }

        String path = null;

        File sdCardFile = null;

        ArrayList<String> devMountList = getDevMountList();

        for (String devMount : devMountList) {
            File file = new File(devMount);

            if (file.isDirectory() && file.canWrite()) {
                path = file.getAbsolutePath();

                String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
                File testWritable = new File(path, "test_" + timeStamp);

                if (testWritable.mkdirs()) {
                    testWritable.delete();
                } else {
                    path = null;
                }
            }
        }

        if (path != null) {
            sdCardFile = new File(path);
            return sdCardFile.getAbsolutePath();
        }

        return null;
    }

    public static void copy(String oldPath, String newPath) {
        File file = new File(oldPath);
        if (file.isDirectory())
            copyFolder(oldPath, newPath);
        else
            copyFile(oldPath, newPath);
    }

    private static void copyFile(String oldPath, String newPath) {
//        Logger.d("FileUtils copyFile oldPath:" + oldPath + ", newPath:" + newPath);
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            File oldFile = new File(oldPath);
            File newFile = new File(newPath + File.separator + oldFile.getName());
            if (!newFile.exists())
                newFile.createNewFile();
            inputStream = new FileInputStream(oldFile);
            outputStream = new FileOutputStream(newFile);
            byte[] buffer = new byte[8192];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
        } catch (Exception e) {
//            Logger.d("FileUtil", "error in copyFile()");
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null)
                    outputStream.close();
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void copyFolder(String oldPath, String newPath) {
        try {
            File oldFile = new File(oldPath);
            File newFile = new File(newPath + File.separator + oldFile.getName());
            if (!newFile.exists())
                newFile.mkdirs();
            for (File temp : oldFile.listFiles()) {
                if (temp.isDirectory()) {
                    copyFolder(temp.getAbsolutePath(), newPath + File.separator + temp.getName());
                } else {
                    copyFile(oldPath, newPath);
                }
            }
        } catch (Exception e) {
//            Logger.d("FileUtil", "error in copyFolder()");
            e.printStackTrace();
        }
    }

    public static void move(String oldPath, String newPath) {
//        Logger.d("TAG move ", "old path:" + oldPath + ", new path:" + newPath);
        new File(oldPath).renameTo(new File(newPath));
    }

    public static void delete(String path) {
        File file = new File(path);
        if (file.isDirectory())
            deleteFolder(file);
        else
            deleteFile(file);
    }

    public static void rename() {

    }

    private static boolean deleteFile(File file) {
        boolean result = false;
        if (file != null) {
            try {
                File file2 = file;
                file2.delete();
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
                result = false;
            }
        }
        return result;
    }

    private static boolean deleteFolder(File folder) {
        boolean result = false;
        try {
            String[] childs = folder.list();
            if (childs == null || childs.length <= 0) {
                if (folder.delete()) {
                    result = true;
                }
            } else {
                for (int i = 0; i < childs.length; i++) {
                    String childName = childs[i];
                    String childPath = folder.getPath() + File.separator + childName;
                    File filePath = new File(childPath);
                    if (filePath.exists() && filePath.isFile()) {
                        if (filePath.delete()) {
                            result = true;
                        } else {
                            result = false;
                            break;
                        }
                    } else if (filePath.exists() && filePath.isDirectory()) {
                        if (deleteFolder(filePath)) {
                            result = true;
                        } else {
                            result = false;
                            break;
                        }
                    }
                }
                folder.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public static boolean createNewFolder(String path) {
//        Logger.i("TAG createNewFolder path:" + path);
        File dirFile = new File(path);
        try {
            if (!dirFile.exists()) {
                boolean result = dirFile.mkdirs();
                if (result)
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static File createNewFile(String path, String name) {
//        Logger.i("TAG createNewFolder path:" + path);
        File file = new File(path, name);
        try {
            if (!file.exists())
                file.createNewFile();
            else
                return file;
        } catch (Exception e) {
            e.printStackTrace();
            return file;
        }
        return file;
    }

    public static String getExtension(String file) {

        int dotposition = file.lastIndexOf(".");
        final String extension = file.substring(dotposition + 1);

        return extension;
    }

    public static void openFile(String path, Context mContext) {


        File file = new File(path);
        Intent target = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        Uri uri = Uri.fromFile(file);
        target.setDataAndType(uri, "application/" + FileUtil.getExtension(file.getName()));
        target.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        Intent intent = Intent.createChooser(target, "Open File");
        try {
            mContext.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(mContext, "No app to open", Toast.LENGTH_LONG).show();
        }
    }

    public static void writeToFile(File d, String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(d));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static File from(Context context, Uri uri) throws IOException {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        String fileName = getFileName(context, uri);
        String[] splitName = splitFileName(fileName);
        File tempFile = File.createTempFile(splitName[0], splitName[1]);
        tempFile = rename(tempFile, fileName);
        tempFile.deleteOnExit();
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(tempFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (inputStream != null) {
            copy(inputStream, out);
            inputStream.close();
        }

        if (out != null) {
            out.close();
        }
        return tempFile;
    }

    private static String[] splitFileName(String fileName) {
        String name = fileName;
        String extension = "";
        int i = fileName.lastIndexOf(".");
        if (i != -1) {
            name = fileName.substring(0, i);
            extension = fileName.substring(i);
        }

        return new String[]{name, extension};
    }

    private static String getFileName(Context context, Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf(File.separator);
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    private static File rename(File file, String newName) {
        File newFile = new File(file.getParent(), newName);
        if (!newFile.equals(file)) {
            if (newFile.exists() && newFile.delete()) {
                Log.d("FileUtil", "Delete old " + newName + " file");
            }
            if (file.renameTo(newFile)) {
                Log.d("FileUtil", "Rename file to " + newName);
            }
        }
        return newFile;
    }

    private static long copy(InputStream input, OutputStream output) throws IOException {
        long count = 0;
        int n;
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        while (EOF != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }
}
