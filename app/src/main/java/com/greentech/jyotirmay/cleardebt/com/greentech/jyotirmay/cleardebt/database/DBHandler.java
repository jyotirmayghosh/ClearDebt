package com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.database;

/**
 * Created by Jyotirmay on 19-Jan-18.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.greentech.jyotirmay.cleardebt.AddPayeeActivity;
import com.greentech.jyotirmay.cleardebt.R;

/**
 * Created by Jyotirmay on 14-Jan-18.
 */

public class DBHandler {

    public static final String TABLE_CATEGORY= "category_table";
    public static final String TABLE_ACCOUNT= "account_table";
    public static final String COL_ID="_id";
    public static final String COL_VALUE="value";
    public static final String COL_CATEGORY="category";
    public static final String COL_DATE="date";
    public static final String COL_TOTAL="total";
    public static final String COL_ACCOUNT="account";
    public static final String COL_NAME="name";

    DBOpenHelper openHelper;
    public DBHandler (Context context)
    {
        String dbName=context.getString(R.string.db_name);
        int dbVersion=context.getResources().getInteger(R.integer.db_version);
        openHelper=new DBOpenHelper(context, dbName, null, dbVersion);
    }

    public boolean enterCategory (String value, String category, String date)
    {
        SQLiteDatabase sqLiteDatabase=openHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(COL_VALUE, value);
        contentValues.put(COL_CATEGORY, category);
        contentValues.put(COL_DATE, date);

        long rowId=sqLiteDatabase.insert(TABLE_CATEGORY, null, contentValues);
        sqLiteDatabase.close();
        return rowId != -1;
    }

    public boolean deposit (String value, String acc_type, String name, String date)
    {
        SQLiteDatabase sqLiteDatabase=openHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(COL_VALUE, value);
        contentValues.put(COL_ACCOUNT, acc_type);
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_DATE, date);

        long rowId=sqLiteDatabase.insert(TABLE_ACCOUNT, null, contentValues);
        sqLiteDatabase.close();
        return rowId != -1;
    }

    public Cursor readTransaction(String date)
    {
        SQLiteDatabase db=openHelper.getReadableDatabase();
        String column[]={COL_ID, COL_VALUE, COL_CATEGORY};
        String whereClause= COL_DATE + " =?";
        String whereArgs[]={date};
        Cursor cursor= db.query(TABLE_CATEGORY, column, whereClause, whereArgs, null, null, COL_ID);
        return cursor;
    }

    public Cursor readTake()
    {
        SQLiteDatabase db=openHelper.getReadableDatabase();
        String column[]={COL_VALUE};
        String whereClause= COL_ACCOUNT + " =?";
        String ACC_TYPE=AddPayeeActivity.ACCOUNT_TAKE;
        String whereArgs[]={ACC_TYPE};
        Cursor cursor= db.query(TABLE_ACCOUNT, column, whereClause, whereArgs, null, null, null);
        return  cursor;
    }

    public Cursor readGive()
    {
        SQLiteDatabase db=openHelper.getReadableDatabase();
        String column[]={COL_VALUE};
        String whereClause= COL_ACCOUNT + " =?";
        String ACC_TYPE=AddPayeeActivity.ACCOUNT_GIVE;
        String whereArgs[]={ACC_TYPE};
        Cursor cursor= db.query(TABLE_ACCOUNT, column, whereClause, whereArgs, null, null, null);
        return  cursor;
    }

    public Cursor readTotalTransaction()
    {
        SQLiteDatabase db=openHelper.getReadableDatabase();
        return db.rawQuery("SELECT SUM( " +COL_VALUE+ ") as "+COL_TOTAL+" FROM " +TABLE_CATEGORY, null);
    }

    public Cursor readAccount()
    {
        SQLiteDatabase db=openHelper.getReadableDatabase();
        return db.query(TABLE_ACCOUNT, null, null, null, null, null, null);
    }

    public void removeTransaction(long itemId) {
        SQLiteDatabase db=openHelper.getWritableDatabase();
        String whereClause= COL_ID + " =?";
        String[] whereArgs={String.valueOf(itemId)};
        int count = db.delete(TABLE_CATEGORY, whereClause,whereArgs);
        Log.d("TAG", "count deleted - " + count);
    }

    public void removeDeposit (long itemId) {
        SQLiteDatabase db=openHelper.getWritableDatabase();
        String whereClause= COL_ID + " =?";
        String[] whereArgs={String.valueOf(itemId)};
        int count = db.delete(TABLE_ACCOUNT, whereClause,whereArgs);
        Log.d("TAG", "count deleted - " + count);
    }

    public class DBOpenHelper extends SQLiteOpenHelper {

        public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE "+ TABLE_CATEGORY+" ("
                    + COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COL_VALUE+" VARCHAR (100),"
                    + COL_CATEGORY+" VARCHAR (50),"
                    + COL_DATE+" VARCHAR(50))");

            db.execSQL("CREATE TABLE "+ TABLE_ACCOUNT+" ("
                    + COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COL_VALUE+" VARCHAR (100),"
                    + COL_ACCOUNT+" VARCHAR (20),"
                    + COL_NAME+" VARCHAR (100),"
                    + COL_DATE+" VARCHAR(50))");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onCreate(db);
        }
    }
}
