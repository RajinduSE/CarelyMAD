package com.example.caring.dbHandlers.education;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.caring.models.education.School;

import java.util.ArrayList;
import java.util.List;

public class SchoolDbHandler extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DB_NAME = "caring";
    private static final String TABLE_NAME = "school";

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PHONE = "phone";
    private static final String START = "start";
    private static final String FINISH = "finish";


    public SchoolDbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String TABLE_CREATE_QUERY = "CREATE TABLE " + TABLE_NAME +
                " ("
                +ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +NAME + " TEXT,"
                +PHONE + " INTEGER,"
                +START + " INTEGER,"
                +FINISH + " INTEGER"
                + ");";
        sqLiteDatabase.execSQL(TABLE_CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;

        //drop table if exists
        sqLiteDatabase.execSQL(DROP_TABLE_QUERY);

        //create table again
        onCreate(sqLiteDatabase);
    }

    public boolean addSchool(School school){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        try{
            ContentValues contentValues = new ContentValues();

            contentValues.put(NAME, school.getName());
            contentValues.put(PHONE, school.getPhone());
            contentValues.put(START, school.getFrom());
            contentValues.put(FINISH, school.getTo());

            //save to table
            sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
            return true;
        }catch (SQLException e){
            Log.i("Error", "Insert Error " + e);
        }catch (Exception e){
            Log.i("Error", "Delete Error " + e);
        }finally {
            //close database
            sqLiteDatabase.close();
        }
        return false;
    }

    public List<School> getAllSchools(){
        List<School> schools = new ArrayList<School>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        try{
            String query = "SELECT * FROM " + TABLE_NAME;

            Cursor cursor = sqLiteDatabase.rawQuery(query, null);

            if(cursor.moveToFirst()){
                do{
                    School school = new School();
                    school.setId(cursor.getInt(0));
                    school.setName(cursor.getString(1));
                    school.setPhone(cursor.getInt(2));
                    school.setFrom(cursor.getInt(3));
                    school.setTo(cursor.getInt(4));

                    schools.add(school);
                }while(cursor.moveToNext());
            }

            return schools;
        }catch (SQLException e){
            Log.i("Error", "Display Error " + e);
        }catch(Exception e){
            Log.i("Error", "Display Error " + e);
        }finally{
            if(sqLiteDatabase != null) {
                sqLiteDatabase.close();
            }
        }
        return null;
    }

    public void deleteSchool(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        try{
            sqLiteDatabase.delete(TABLE_NAME, ID + " =?", new String[]{String.valueOf(id)});
        }catch (SQLException e){
            Log.i("Error", "Delete Error " + e);
        }catch(Exception e){
            Log.i("Error", "Delete Error " + e);
        }finally{
            if(sqLiteDatabase != null) {
                sqLiteDatabase.close();
            }
        }
    }

    public School getSingleSchool(int id){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        try {
            Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[]{ID, NAME, PHONE, START, FINISH}, ID + " =?", new String[]{String.valueOf(id)}, null, null, null);
            School school;
            if(cursor != null){
                cursor.moveToFirst();
                school = new School(cursor.getInt(0),
                                    cursor.getString(1),
                                    cursor.getInt(2),
                                    cursor.getInt(3),
                                    cursor.getInt(4));
                return school;
            }
        }catch (SQLException e){
            Log.i("Error", "Retrieving Error " + e);
        }catch(Exception e){
            Log.i("Error", "Retrieving Error " + e);
        }finally{
            if(sqLiteDatabase != null) {
                sqLiteDatabase.close();
            }
        }
        return null;
    }

    public int updateSingleSchool(School school){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        try{
            contentValues.put(NAME, school.getName());
            contentValues.put(PHONE, school.getPhone());
            contentValues.put(START, school.getFrom());
            contentValues.put(FINISH, school.getTo());

            int status = sqLiteDatabase.update(TABLE_NAME, contentValues, ID + " =?", new String[]{String.valueOf(school.getId())});
            return status;
        }catch (SQLException e){
            Log.i("Error", "Update Error " + e);
        }catch(Exception e){
            Log.i("Error", "Update Error " + e);
        }finally{
            if(sqLiteDatabase != null) {
                sqLiteDatabase.close();
            }
        }
        return 0;
    }
}
