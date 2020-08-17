package org.kingtec.utils.Base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;


public class BaseClass extends Base {
    private static final String TAG_Districtzone_id = "districtzone_id";
    private static final String TAG_FULLNAME = "fullName";
    private static final String TAG_Id = "id";
    private static final String TAG_TOKEN = "fbchghkhjhhh";
    private static final String TAG_PASSWORD = "password";
    private static final String TAG_RESULTS = "result";
    private static final String BranchName = "BranchName";
    private static final String firstLogin = "firstLogin";
    private static final String TAG_ROLE = "TAG_ROLE";
    private static final String TAG_School_id = "school_id";
    private static final String TAG_USERNAME = "username";
    private static final String TAG_Fabs = "Fabs";
    private static final String setSelectedUser = "selectedUser";
    public static final String USER_NAME = "USERNAME";

    public BaseClass(Context context) {
        super(context);
    }


    @SuppressLint("CommitPrefEdits")
    public void se6tUserType(String userType) {
        getSharedPref().edit().remove(User.SCHOOL);


    }

    @SuppressLint("CommitPrefEdits")
    public void clearPref() {
        setPass("");
        setId("");
        setBranchName("");
        setFullName("");
        setSelectedUser("");
        setRole("");
        setUserName("");
    }

    @SuppressLint("CommitPrefEdits")
    public boolean setId(String userType) {
        SharedPreferences.Editor editor = this.getSharedPref().edit();
        editor.putString(TAG_Id, userType);
        return editor.commit();
    }

    @SuppressLint("CommitPrefEdits")
    public String getId() {
        return getSharedPref().getString(TAG_Id, "");
    }


    @SuppressLint("CommitPrefEdits")
    public boolean setApi(String userType) {
        SharedPreferences.Editor editor = this.getSharedPref().edit();
        editor.putString(TAG_Id + "Api", userType);
        return editor.commit();
    }

    @SuppressLint("CommitPrefEdits")
    public String getApi() {


        return getSharedPref().getString(TAG_Id + "Api", "http://192.168.191.1:45459/");
    }

    @SuppressLint("CommitPrefEdits")
    public boolean setWeb(String userType) {
        SharedPreferences.Editor editor = this.getSharedPref().edit();
        editor.putString(TAG_Id + "Web", userType);
        return editor.commit();
    }

    @SuppressLint("CommitPrefEdits")
    public String getWeb() {
        return getSharedPref().getString(TAG_Id + "Web", "http://192.168.191.1:45461/");
    }

    @SuppressLint("CommitPrefEdits")
    public boolean setToken(String userType) {
        SharedPreferences.Editor editor = this.getSharedPref().edit();
        editor.putString(TAG_TOKEN, userType);
        return editor.commit();
    }

    @SuppressLint("CommitPrefEdits")
    public String getToken() {
        return getSharedPref().getString(TAG_TOKEN, "");
    }

    @SuppressLint("CommitPrefEdits")
    public boolean setSelectedUser(String userType) {
        SharedPreferences.Editor editor = this.getSharedPref().edit();
        editor.putString(setSelectedUser, userType);
        return editor.commit();
    }

    @SuppressLint("CommitPrefEdits")
    public String getSelectedUser() {
        return getSharedPref().getString(setSelectedUser, "");
    }

    @SuppressLint("CommitPrefEdits")
    public boolean setUserName(String userType) {
        SharedPreferences.Editor editor = this.getSharedPref().edit();
        editor.putString(TAG_USERNAME, userType);
        return editor.commit();
    }

    public boolean showFabs(boolean userType) {
        SharedPreferences.Editor editor = this.getSharedPref().edit();
        editor.putBoolean(TAG_Fabs, userType);
        return editor.commit();
    }

    @SuppressLint("CommitPrefEdits")
    public boolean showFabs() {
        return getSharedPref().getBoolean(TAG_Fabs, true);
    }

    @SuppressLint("CommitPrefEdits")
    public String getUserName() {
        return getSharedPref().getString(TAG_USERNAME, "");
    }


    @SuppressLint("CommitPrefEdits")
    public boolean setFullName(String userType) {
        SharedPreferences.Editor editor = this.getSharedPref().edit();
        editor.putString(TAG_FULLNAME, userType);
        return editor.commit();
    }

    @SuppressLint("CommitPrefEdits")
    public String getFullName() {
        return getSharedPref().getString(TAG_FULLNAME, "");
    }

    @SuppressLint("CommitPrefEdits")
    public boolean setRole(String userType) {
        SharedPreferences.Editor editor = this.getSharedPref().edit();
        editor.putString(TAG_ROLE, userType);
        return editor.commit();
    }

    @SuppressLint("CommitPrefEdits")
    public boolean setRoleInt(int userType) {
        SharedPreferences.Editor editor = this.getSharedPref().edit();
        editor.putInt(TAG_ROLE + "int", userType);
        return editor.commit();
    }

    @SuppressLint("CommitPrefEdits")
    public int getRoleInt() {
        return getSharedPref().getInt(TAG_ROLE + "int", 0);
    }

    @SuppressLint("CommitPrefEdits")
    public String getPass() {
        return getSharedPref().getString(TAG_School_id, "");
    }

    @SuppressLint("CommitPrefEdits")
    public boolean setPass(String userType) {
        SharedPreferences.Editor editor = this.getSharedPref().edit();
        editor.putString(TAG_School_id, userType);
        return editor.commit();
    }

    @SuppressLint("CommitPrefEdits")
    public String getPhone() {
        return getSharedPref().getString("setPhone", "");
    }

    @SuppressLint("CommitPrefEdits")
    public boolean setPhone(String userType) {
        SharedPreferences.Editor editor = this.getSharedPref().edit();
        editor.putString("setPhone", userType);
        return editor.commit();
    }

    @SuppressLint("CommitPrefEdits")
    public String getRole() {
        return getSharedPref().getString(TAG_ROLE, "");
    }

    @SuppressLint("CommitPrefEdits")
    public boolean setBranchName(String userType) {
        SharedPreferences.Editor editor = this.getSharedPref().edit();
        editor.putString(BranchName, userType);
        return editor.commit();
    }

    @SuppressLint("CommitPrefEdits")
    public String getBranchName() {
        return getSharedPref().getString(BranchName, "1");
    }

    @SuppressLint("CommitPrefEdits")
    public boolean setFirstLogin(String userType) {
        SharedPreferences.Editor editor = this.getSharedPref().edit();
        editor.putString(firstLogin, userType);
        return editor.commit();
    }

    @SuppressLint("CommitPrefEdits")
    public String getFirstLogin() {
        return getSharedPref().getString(firstLogin, "");
    }


    @SuppressLint("CommitPrefEdits")
    public boolean setUserType(String userType) {
        SharedPreferences.Editor editor = this.getSharedPref().edit();
        editor.putString("type-", userType);
        return editor.commit();
    }

    @SuppressLint("CommitPrefEdits")
    public String getUserType() {
        return getSharedPref().getString("type-", User.PARENT);
    }

    @SuppressLint("CommitPrefEdits")
    public boolean setUserUnivId(String univId) {
        SharedPreferences.Editor editor = this.getSharedPref().edit();
        editor.putString("23", univId);
        return editor.commit();
    }

    @SuppressLint("CommitPrefEdits")
    public String getUserUnivId() {

        return getSharedPref().getString("23", null);
    }

    @SuppressLint("CommitPrefEdits")
    public String getUserUnivId(String is) {

        return getSharedPref().getString("23" + is, null);
    }

    @SuppressLint("CommitPrefEdits")
    public boolean setUnivUser(String user) {
        SharedPreferences.Editor editor = this.getSharedPref().edit();
        editor.putString("unv-" + getUserUnivId(), user);
        return editor.commit();
    }

    @SuppressLint("CommitPrefEdits")
    public String getUnivUser() {
        return getSharedPref().getString("unv-" + getUserUnivId(), null);
    }

    public boolean isAdmin() {

        return isValid(getUserType()) && getUserType().equals(User.ADMIN);
    }

    public boolean isAdmin(User user) {
        return isValid(user.getType()) && user.getType().equals(User.ADMIN);
    }

    public boolean isUser() {
        return isValid(getUserType()) && getUserType().equals(User.PARENT);
    }

    public boolean isUser(User user) {
        return isValid(user.getType()) && user.getType().equals(User.PARENT);
    }


    public boolean isUniversity(User user) {
        return isValid(user.getType()) && user.getType().equals(User.SCHOOL);
    }

    public boolean isUniversity() {
        return isValid(getUserType()) && getUserType().equals(User.SCHOOL);
    }

    public boolean isUniversityOwner(String unvid) {
        if (!initAuth()) return false;
        return isValid(unvid) && isValid(getUserUnivId()) && getUserUnivId().trim().equals(unvid.trim());
    }

    public int getUnivAddVisable(String unvid) {
        return (isUniversityOwner(unvid)) ? View.VISIBLE : View.GONE;
    }
}
