package com.example.caring.dbHandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


import com.example.caring.models.User;
import com.example.caring.models.achievement.AchievementModel;
import com.example.caring.models.education.Mark;
import com.example.caring.models.education.School;
import com.example.caring.models.health.Health;
import com.example.caring.models.health.MedicsN;
import com.example.caring.models.health.Vaccine;
import com.example.caring.models.task.Task;


import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DB_NAME = "caring";
    private static final String TABLE_NAME1 = "school";
    private static final String TABLE_NAME2 = "mark";
    private static final String TABLE_NAME3 = "achievement";
    private static final String TABLE_NAME4 = "user";
    private static final String TABLE_NAME5 = "health";
    private static final String TABLE_NAME6 = "medics";
    private static final String TABLE_NAME7 = "vaccine";
    private static final String TABLE_NAME8 = "task";

    //school table properties
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PHONE = "phone";
    private static final String START = "start";
    private static final String FINISH = "finish";

    //mark table properties
    private static final String GRADE = "grade";
    private static final String TERM = "term";
    private static final String TOTAL = "total";
    private static final String SUB_COUNT = "sub_count";
    private static final String AVERAGE = "average";

    //achievement table properties
    private static final String EVENT = "event";
    private static final String DESCRIPTION = "description";
    private static final String AWARD = "award";
    private static final String YEAR = "year";

    //user table properties
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    //health table properties
    private static final String HEIGHT = "height";
    private static final String WEIGHT = "weight";
    private static final String DOCTOR = "doctor";
    private static final String HEALTHCONDITION = "healthCondition";
    private static final String ALLERGIES = "allergies";

    //medics table properties
    private static final String MEDICINE = "medicine";
    private static final String TIME = "time";
    private static final String ATATIME = "atATime";

    //vaccine table properties
    private static final String VACCINENAME = "vaccineName";
    private static final String AGE = "age";

    //task table properties
    private static final String TITLE = "title";
    private static final String STARTED = "started";
    private static final String FINISHED = "finished";

    public DbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String TABLE_CREATE_QUERY1 = "CREATE TABLE " + TABLE_NAME1 +
                " ("
                +ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +NAME + " TEXT,"
                +PHONE + " INTEGER,"
                +START + " INTEGER,"
                +FINISH + " INTEGER"
                + ");";

        String TABLE_CREATE_QUERY2 = "CREATE TABLE " + TABLE_NAME2 +
                " ("
                +ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +GRADE + " INTEGER,"
                +TERM + " INTEGER,"
                +TOTAL + " REAL,"
                +SUB_COUNT + " INTEGER,"
                +AVERAGE + " REAL"
                + ");";

        String TABLE_CREATE_QUERY3 = "CREATE TABLE " + TABLE_NAME3 +
                " ("
                +ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +EVENT + " TEXT,"
                +DESCRIPTION + " TEXT,"
                +AWARD + " TEXT,"
                +YEAR + " INTEGER"
                + ");";

        String TABLE_CREATE_QUERY4 = "CREATE TABLE " + TABLE_NAME4 +
                " ("
                +USERNAME + " TEXT PRIMARY KEY,"
                +PASSWORD + " TEXT"
                + ");";


        String TABLE_CREATE_QUERY5 = "CREATE TABLE " + TABLE_NAME5 +
                "("
                +ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +HEIGHT+ " REAL,"
                +WEIGHT+ " REAL,"
                +DOCTOR+ " TEXT,"
                +HEALTHCONDITION+ " TEXT,"
                +ALLERGIES+ " TEXT"
                + ");" ;

        String TABLE_CREATE_QUERY6 = "CREATE TABLE " + TABLE_NAME6 +
                "("
                +ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +MEDICINE+ " TEXT,"
                +TIME+ " TEXT,"
                +ATATIME+ " TEXT,"
                +DESCRIPTION+ " TEXT"
                +");";

        String TABLE_CREATE_QUERY7 = "CREATE TABLE " + TABLE_NAME7 +
                "("
                +ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +VACCINENAME+ " TEXT,"
                +AGE+ " TEXT"
                +");";

        String TABLE_CREATE_QUERY8 = "CREATE TABLE " + TABLE_NAME8 +
                " ("
                +ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +TITLE + " TEXT,"
                +DESCRIPTION + " TEXT,"
                +STARTED + " TEXT,"
                +FINISHED + " TEXT"
                + ");";


        sqLiteDatabase.execSQL(TABLE_CREATE_QUERY1);
        sqLiteDatabase.execSQL(TABLE_CREATE_QUERY2);
        sqLiteDatabase.execSQL(TABLE_CREATE_QUERY3);
        sqLiteDatabase.execSQL(TABLE_CREATE_QUERY4);
        sqLiteDatabase.execSQL(TABLE_CREATE_QUERY5);
        sqLiteDatabase.execSQL(TABLE_CREATE_QUERY6);
        sqLiteDatabase.execSQL(TABLE_CREATE_QUERY7);
        sqLiteDatabase.execSQL(TABLE_CREATE_QUERY8);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /*-----------------------------------------school table crud--------------------------------------------------*/
    public boolean addSchool(School school){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        try{
            ContentValues contentValues = new ContentValues();

            contentValues.put(NAME, school.getName());
            contentValues.put(PHONE, school.getPhone());
            contentValues.put(START, school.getFrom());
            contentValues.put(FINISH, school.getTo());

            //save to table
            sqLiteDatabase.insert(TABLE_NAME1, null, contentValues);
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
            String query = "SELECT * FROM " + TABLE_NAME1;

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
            sqLiteDatabase.delete(TABLE_NAME1, ID + " =?", new String[]{String.valueOf(id)});
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
            Cursor cursor = sqLiteDatabase.query(TABLE_NAME1, new String[]{ID, NAME, PHONE, START, FINISH}, ID + " =?", new String[]{String.valueOf(id)}, null, null, null);
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

            int status = sqLiteDatabase.update(TABLE_NAME1, contentValues, ID + " =?", new String[]{String.valueOf(school.getId())});
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

    /*---------------------------------------------------mark table crud------------------------------------------------------------------------*/
    public boolean addMark(Mark mark){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        try{
            ContentValues contentValues = new ContentValues();

            contentValues.put(GRADE, mark.getGrade());
            contentValues.put(TERM, mark.getTerm());
            contentValues.put(TOTAL, mark.getTotal());
            contentValues.put(SUB_COUNT, mark.getSubCount());
            contentValues.put(AVERAGE, mark.calculateAverage(mark.getTotal(), mark.getSubCount()));

            //save to table
            sqLiteDatabase.insert(TABLE_NAME2, null, contentValues);
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

    public boolean deleteMark(int grade, int term){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        try{
            sqLiteDatabase.delete(TABLE_NAME2, GRADE + " =? AND " + TERM + " =?", new String[]{String.valueOf(grade), String.valueOf(term)});
            return true;
        }catch (SQLException e){
            Log.i("Error", "Delete Error " + e);
        }catch(Exception e){
            Log.i("Error", "Delete Error " + e);
        }finally{
            if(sqLiteDatabase != null) {
                sqLiteDatabase.close();
            }
        }
        return false;
    }

    public Mark getSingleMark(int grade, int term){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        try {
            Cursor cursor = sqLiteDatabase.query(TABLE_NAME2, new String[]{GRADE, TERM, TOTAL, SUB_COUNT, AVERAGE}, GRADE + " =? AND " + TERM + " =?", new String[]{String.valueOf(grade), String.valueOf(term)}, null, null, null);
            Mark mark;
            if(cursor != null){
                cursor.moveToFirst();
                mark = new Mark(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getDouble(2),
                        cursor.getInt(3),
                        cursor.getDouble(4));
                return mark;
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

    public int updateSingleMark(Mark mark){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        try{
            contentValues.put(GRADE, mark.getGrade());
            contentValues.put(TERM, mark.getTerm());
            contentValues.put(TOTAL, mark.getTotal());
            contentValues.put(SUB_COUNT, mark.getSubCount());
            contentValues.put(AVERAGE, mark.calculateAverage(mark.getTotal(), mark.getSubCount()));

            int status = sqLiteDatabase.update(TABLE_NAME2, contentValues, GRADE + " =? AND " + TERM + " =?", new String[]{String.valueOf(mark.getGrade()), String.valueOf(mark.getTerm())});
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

    public List<Mark> getYearMark(int grade){
        List<Mark> marks = new ArrayList<Mark>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        try{
            String query = "SELECT * FROM " + TABLE_NAME2 + " WHERE " + GRADE + " = " + grade;

            Cursor cursor = sqLiteDatabase.rawQuery(query, null);

            if(cursor.moveToFirst()){
                do{
                    Mark mark = new Mark();
                    mark.setId(cursor.getInt(0));
                    mark.setGrade(cursor.getInt(1));
                    mark.setTerm(cursor.getInt(2));
                    mark.setTotal(cursor.getInt(3));
                    mark.setSubCount(cursor.getInt(4));
                    mark.setAverage(cursor.getInt(5));

                    marks.add(mark);
                }while(cursor.moveToNext());
            }

            return marks;
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

    /*----------------------------------------------------Achievement-----------------------------------------------------------*/
    public boolean addAchievement(AchievementModel achievement){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        try{
            ContentValues contentValues = new ContentValues();

            contentValues.put(EVENT, achievement.getEvent());
            contentValues.put(DESCRIPTION, achievement.getDescription());
            contentValues.put(AWARD, achievement.getAward());
            contentValues.put(YEAR, achievement.getYear());

            //save to table
            sqLiteDatabase.insert(TABLE_NAME3, null, contentValues);
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

    public List<AchievementModel> getAllAchievement(){
        List<AchievementModel> achievements = new ArrayList<AchievementModel>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        try{
            String query = "SELECT * FROM " + TABLE_NAME3;

            Cursor cursor = sqLiteDatabase.rawQuery(query, null);

            if(cursor.moveToFirst()){
                do{
                    AchievementModel achievement = new AchievementModel();
                    achievement.setId(cursor.getInt(0));
                    achievement.setEvent(cursor.getString(1));
                    achievement.setDescription(cursor.getString(2));
                    achievement.setAward(cursor.getString(3));
                    achievement.setYear(cursor.getInt(4));

                    achievements.add(achievement);
                }while(cursor.moveToNext());
            }

            return achievements;
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

    public void deleteAchievement(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        try{
            sqLiteDatabase.delete(TABLE_NAME3, ID + " =?", new String[]{String.valueOf(id)});
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

    public AchievementModel getSingleAchievement(int id){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        try {
            Cursor cursor = sqLiteDatabase.query(TABLE_NAME3, new String[]{ID, EVENT, DESCRIPTION, AWARD, YEAR}, ID + " =?", new String[]{String.valueOf(id)}, null, null, null);
            AchievementModel achievement;
            if(cursor != null){
                cursor.moveToFirst();
                achievement = new AchievementModel(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4));
                return achievement;
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

    public int updateSingleAchievement(AchievementModel achievement){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        try{
            contentValues.put(EVENT, achievement.getEvent());
            contentValues.put(DESCRIPTION, achievement.getDescription());
            contentValues.put(AWARD, achievement.getAward());
            contentValues.put(YEAR, achievement.getYear());

            int status = sqLiteDatabase.update(TABLE_NAME3, contentValues, ID + " =?", new String[]{String.valueOf(achievement.getId())});
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

    /*--------------------------------------------------------------User-------------------------------------------------------------------------------*/
    public boolean addUser(User user){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        try{
            ContentValues contentValues = new ContentValues();

            contentValues.put(USERNAME, user.getUsername());
            contentValues.put(PASSWORD, user.getPassword());


            //save to table
            sqLiteDatabase.insert(TABLE_NAME4, null, contentValues);
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

    public User getSingleUser(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        try {
            Cursor cursor = sqLiteDatabase.query(TABLE_NAME4, new String[]{USERNAME,PASSWORD}, null, null, null, null, null);
            User user;
            if(cursor != null){
                cursor.moveToFirst();
                user = new User(cursor.getString(0),
                                cursor.getString(1));
                return user;
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

    /*--------------------------------------------------health----------------------------------------------------------------------*/
    public boolean addHealth(Health insertHealth){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        try{
            ContentValues contentValues = new ContentValues();

            contentValues.put(HEIGHT,insertHealth.getHeight());
            contentValues.put(WEIGHT,insertHealth.getWeight());
            contentValues.put(DOCTOR,insertHealth.getDoctor());
            contentValues.put(HEALTHCONDITION,insertHealth.getHealthCondition());
            contentValues.put(ALLERGIES,insertHealth.getAllergies());

            //save to table
            sqLiteDatabase.insert(TABLE_NAME5,null,contentValues);
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

    public Health getSingleHealth(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        try{
            Cursor cursor = sqLiteDatabase.query(TABLE_NAME5,new String[]{ID,HEIGHT,WEIGHT,DOCTOR,HEALTHCONDITION,ALLERGIES},
                    null,null,null,null,null );
            Health health;
            if(cursor != null) {
                cursor.moveToFirst();
                health = new Health(
                        cursor.getInt(0),
                        cursor.getDouble(1),
                        cursor.getDouble(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5));

                return health;
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

    public int updateHealth(Health insertHealth){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        try{
            contentValues.put(WEIGHT,insertHealth.getWeight());
            contentValues.put(HEIGHT,insertHealth.getHeight());
            contentValues.put(DOCTOR,insertHealth.getDoctor());
            contentValues.put(HEALTHCONDITION,insertHealth.getHealthCondition());
            contentValues.put(ALLERGIES,insertHealth.getAllergies());

            int status = sqLiteDatabase.update(TABLE_NAME5,contentValues,null,null);
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

    /*----------------------------------------------Medics-------------------------------------------------------------*/
    public List<MedicsN> getAllMedics(){
        List<MedicsN> mediList = new ArrayList<MedicsN>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        try {
            String query = "SELECT * FROM " + TABLE_NAME6;
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    MedicsN medics = new MedicsN();
                    medics.setMedicsId(cursor.getInt(0));
                    medics.setMedicine(cursor.getString(1));
                    medics.setTime(cursor.getString(2));
                    medics.setAtATime(cursor.getString(3));
                    medics.setDescription(cursor.getString(4));

                    mediList.add(medics);

                } while (cursor.moveToNext());
            }
            return mediList;
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

    public boolean addMedics(MedicsN medicsN){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        try{
            ContentValues contentValues = new ContentValues();

            contentValues.put(MEDICINE,medicsN.getMedicine());
            contentValues.put(TIME,medicsN.getTime());
            contentValues.put(ATATIME,medicsN.getAtATime());
            contentValues.put(DESCRIPTION,medicsN.getDescription());

            //save to table
            sqLiteDatabase.insert(TABLE_NAME6,null,contentValues);
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

    public MedicsN getSingleMedics(int id){

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        try{
            Cursor cursor = sqLiteDatabase.query(TABLE_NAME6,new String[]{ID,MEDICINE,TIME,ATATIME,DESCRIPTION},ID+" =?",
                    new String[]{String.valueOf(id)},null,null,null);

            MedicsN medicsN;

            if(cursor != null) {
                cursor.moveToFirst();
                medicsN = new MedicsN(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4));

                return medicsN;
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

    public void deleteMedics(int id){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        try {
            sqLiteDatabase.delete(TABLE_NAME6, ID + "=?", new String[]{String.valueOf(id)});
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

    public int updateMedics(MedicsN medicsN){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        try {
            contentValues.put(MEDICINE, medicsN.getMedicine());
            contentValues.put(TIME, medicsN.getTime());
            contentValues.put(ATATIME, medicsN.getAtATime());
            contentValues.put(DESCRIPTION, medicsN.getDescription());

            int status = sqLiteDatabase.update(TABLE_NAME6, contentValues, ID + "=?", new String[]{String.valueOf(medicsN.getMedicsId())});
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

    /*------------------------------------------------Vaccine----------------------------------------------------------------*/
    public List<Vaccine> getAllVaccine(){
        List<Vaccine> vaccineList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        try {
            String query = "SELECT * FROM " + TABLE_NAME7;
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {

                    Vaccine vaccine = new Vaccine();
                    vaccine.setVaccineId(cursor.getInt(0));
                    vaccine.setVaccineName(cursor.getString(1));
                    vaccine.setAge(cursor.getString(2));

                    vaccineList.add(vaccine);

                } while (cursor.moveToNext());
            }
            return vaccineList;
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

    public Vaccine getSingleVaccine(int id){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        try {
            Cursor cursor = sqLiteDatabase.query(TABLE_NAME7, new String[]{ID, VACCINENAME, AGE}, ID + " =?", new String[]{String.valueOf(id)}, null, null, null);
            Vaccine vaccine;
            if (cursor != null) {
                cursor.moveToFirst();
                vaccine = new Vaccine(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                );
                return vaccine;
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

    public int updateVaccine(Vaccine vaccine) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        try {
            contentValues.put(VACCINENAME, vaccine.getVaccineName());
            contentValues.put(AGE, vaccine.getAge());

            int status = sqLiteDatabase.update(TABLE_NAME7, contentValues, ID + " =?", new String[]{String.valueOf(vaccine.getVaccineId())});
            return status;
        } catch (SQLException e) {
            Log.i("Error", "Update Error " + e);
        } catch (Exception e) {
            Log.i("Error", "Update Error " + e);
        } finally {
            if (sqLiteDatabase != null) {
                sqLiteDatabase.close();
            }
        }
        return 0;
    }

    public void deleteVaccine(int id){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        try {
            sqLiteDatabase.delete(TABLE_NAME7, ID + " =?", new String[]{String.valueOf(id)});
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

    public boolean addVaccine(Vaccine vaccine){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        try{
            ContentValues contentValues = new ContentValues();

            contentValues.put(VACCINENAME,vaccine.getVaccineName());
            contentValues.put(AGE,vaccine.getAge());

            //save to table
            sqLiteDatabase.insert(TABLE_NAME7,null,contentValues);

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

    /*-------------------------------------------------Task-------------------------------------------------------------*/
    public boolean addTask(Task todo){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TITLE,todo.getTitle());
        contentValues.put(DESCRIPTION, todo.getDescription());
        contentValues.put(STARTED, todo.getStarted());
        contentValues.put(FINISHED, todo.getFinished());

        //save to table
        sqLiteDatabase.insert(TABLE_NAME8, null, contentValues);

        //close database
        sqLiteDatabase.close();
        return true;
    }

    public int countTask(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME8;

        Cursor cursor = db.rawQuery(query,null);

        return cursor.getCount();
    }

    public List<Task> getAllTasks(){
        List<Task> tasks = new ArrayList<Task>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME8;

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{

                Task task = new Task();
                task.setId(cursor.getInt(0));
                task.setTitle(cursor.getString(1));
                task.setDescription(cursor.getString(2));
                task.setStarted(cursor.getLong(3));
                task.setFinished(cursor.getLong(4));

                tasks.add(task);
            }while(cursor.moveToNext());
        }

        return tasks;
    }

    public void deleteTask(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME8, ID + " =?", new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }

    public Task getSingleTask(int id){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME8, new String[]{ID, TITLE, DESCRIPTION, STARTED, FINISHED}, ID + " =?", new String[]{String.valueOf(id)}, null, null, null);

        Task task;
        if(cursor != null){
            cursor.moveToFirst();
            task = new Task(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getLong(3),
                    cursor.getLong(4));
            return task;
        }
        return null;
    }

    public int updateSingleTask(Task task){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TITLE,task.getTitle());
        contentValues.put(DESCRIPTION, task.getDescription());
        contentValues.put(STARTED, task.getStarted());
        contentValues.put(FINISHED, task.getFinished());

        int status = sqLiteDatabase.update(TABLE_NAME8, contentValues, ID + " =?", new String[]{String.valueOf(task.getId())});
        sqLiteDatabase.close();

        return status;
    }
}
