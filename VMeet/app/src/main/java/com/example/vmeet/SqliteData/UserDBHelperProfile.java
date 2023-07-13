package com.example.vmeet.SqliteData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class UserDBHelperProfile extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "USERINFO.DB";
    public static final int DATABASE_VERSION = 1;
    public static final String CREATE_QUERY =
            "CREATE TABLE " + UserInfoDataProfile.UserData.TABLE_NAME + "(" + UserInfoDataProfile.UserData.NAME + " TEXT," +
                    UserInfoDataProfile.UserData.USER_EMAIL + " TEXT," + UserInfoDataProfile.UserData.USER_NAME + " TEXT," +
                    UserInfoDataProfile.UserData.USER_PHONE + " TEXT," + UserInfoDataProfile.UserData.USER_TYPE + " TEXT," +
                    UserInfoDataProfile.UserData.GENDER + " TEXT," + UserInfoDataProfile.UserData.DOB + " TEXT," +
                    UserInfoDataProfile.UserData.HOME_ADDRESS + " TEXT," + UserInfoDataProfile.UserData.CITY + " TEXT," +
                    UserInfoDataProfile.UserData.STATE + " TEXT," + UserInfoDataProfile.UserData.COUNTRY + " TEXT," +
                    UserInfoDataProfile.UserData.PIN_CODE + " TEXT);";

    public UserDBHelperProfile(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.e("Database created", "Database created successfully and open...... ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertDataInDataBase(String Name, String UserName, String UserEmail, String Phone, String User_Type, String Gender,
                                     String DOB, String Home_Address, String City, String State, String Country, String Pin_Code, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserInfoDataProfile.UserData.NAME, Name);
        contentValues.put(UserInfoDataProfile.UserData.USER_EMAIL, UserEmail);
        contentValues.put(UserInfoDataProfile.UserData.USER_NAME, UserName);
        contentValues.put(UserInfoDataProfile.UserData.USER_PHONE, Phone);
        contentValues.put(UserInfoDataProfile.UserData.USER_TYPE, User_Type);
        contentValues.put(UserInfoDataProfile.UserData.GENDER, Gender);
        contentValues.put(UserInfoDataProfile.UserData.DOB, DOB);
        contentValues.put(UserInfoDataProfile.UserData.HOME_ADDRESS, Home_Address);
        contentValues.put(UserInfoDataProfile.UserData.CITY, City);
        contentValues.put(UserInfoDataProfile.UserData.STATE, State);
        contentValues.put(UserInfoDataProfile.UserData.COUNTRY, Country);
        contentValues.put(UserInfoDataProfile.UserData.PIN_CODE, Pin_Code);
        db.insert(UserInfoDataProfile.UserData.TABLE_NAME, null, contentValues);

    }


    public void deleteInformation(String UserName, SQLiteDatabase sqLiteDatabase) {
        String selectLine = UserInfoDataProfile.UserData.USER_EMAIL + " LIKE ? ";
        String[] selection_args = {UserName};
        sqLiteDatabase.delete(UserInfoDataProfile.UserData.TABLE_NAME, selectLine, selection_args);

    }

    public Cursor getInformation(SQLiteDatabase sqLiteDatabase) {
        Cursor cursor;
        String[] projections = {UserInfoDataProfile.UserData.NAME, UserInfoDataProfile.UserData.USER_EMAIL, UserInfoDataProfile.UserData.USER_NAME,
                UserInfoDataProfile.UserData.USER_PHONE, UserInfoDataProfile.UserData.USER_TYPE, UserInfoDataProfile.UserData.GENDER, UserInfoDataProfile.UserData.DOB
                , UserInfoDataProfile.UserData.HOME_ADDRESS, UserInfoDataProfile.UserData.CITY, UserInfoDataProfile.UserData.STATE, UserInfoDataProfile.UserData.COUNTRY, UserInfoDataProfile.UserData.PIN_CODE};
        cursor = sqLiteDatabase.query(UserInfoDataProfile.UserData.TABLE_NAME, projections, null, null, null, null, null);
        return cursor;
    }

}
