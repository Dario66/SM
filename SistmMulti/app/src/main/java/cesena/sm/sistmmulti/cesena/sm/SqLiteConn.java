package cesena.sm.sistmmulti.cesena.sm;

/**
 * Created by Dario5 on 22/02/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import cesena.sm.sistmmulti.cesena.sm.Repos;
import cesena.sm.sistmmulti.cesena.sm.Student;


public class SqLiteConn extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactsManager";

    // Contacts table name
    private static final String TABLE_REPORT = "report";

    private static final String TABLE_STUDENT = "student";

    public static final String KEY_ID2 = "_cod";
    public static final String KEY_TESTO2 = "testo";
    public static final String KEY_LEVEL2 = "level";
    public static final String KEY_CATEGORY2="category";



    public SqLiteConn(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_TOP_TABLE = "CREATE TABLE " + TABLE_STUDENT + " ("
                + KEY_ID2 + " INTEGER PRIMARY KEY,"
                + KEY_TESTO2 + " TEXT,"
                + KEY_LEVEL2 + " INTEGER,"
                + KEY_CATEGORY2 + " TEXT"
                + ")";

        db.execSQL(CREATE_TOP_TABLE);
        CREATE_TOP_TABLE = "CREATE TABLE " + TABLE_REPORT + " ("
                + KEY_ID2 + " INTEGER PRIMARY KEY,"
                + KEY_TESTO2 + " TEXT,"
                + KEY_LEVEL2 + " INTEGER,"
                + KEY_CATEGORY2 + " TEXT"
                + ")";

        db.execSQL(CREATE_TOP_TABLE);


    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
      /* db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
       */
        // Create tables again
       /*onCreate(db);

       db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS);
       onCreate(db);*/
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORT);
        onCreate(db);


    }

    public void addStud(Student parola) {

        ContentValues values = new ContentValues();
        values.put(KEY_TESTO2, parola.getTesto2());
        values.put(KEY_LEVEL2, parola.getLevel2());
        values.put(KEY_CATEGORY2, parola.getCategory2());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_STUDENT, null, values);
        db.close();
    }
    Student getStud(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_STUDENT, new String[] { KEY_ID2,
                        KEY_TESTO2, KEY_LEVEL2,KEY_CATEGORY2 }, KEY_ID2 + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Student m1 = new Student(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getInt(2),cursor.getString(3));
        // return contact
        return m1;
    }
    public boolean ExistStuds(){
        boolean a;
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            a=true;
        }else{
            a=false;
        }
        return a;

    }
    public List<Student> getAllStuds() {
        List<Student> contactList2 = new ArrayList<Student>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student contact2 = new Student();
                contact2.setCOD2(Integer.parseInt(cursor.getString(0)));
                contact2.setTesto2(cursor.getString(1));
                contact2.setLevel2(cursor.getInt(2));
                contact2.setCategory2(cursor.getString(3));
                // Adding contact to list
                contactList2.add(contact2);
            } while (cursor.moveToNext());
        }
        return contactList2;
    }
    public void deleteStuds(Student parola) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENT, KEY_ID2 + " = ?",
                new String[] { String.valueOf(parola.getCOD2()) });
        db.close();
    }

    public List<Student> getAllStudsSort() {
        List<Student> contactList2 = new ArrayList<Student>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT +" ORDER BY level DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student contact2 = new Student();
                contact2.setCOD2(Integer.parseInt(cursor.getString(0)));
                contact2.setTesto2(cursor.getString(1));
                contact2.setLevel2(cursor.getInt(2));
                contact2.setCategory2(cursor.getString(3));
                // Adding contact to list
                contactList2.add(contact2);
            } while (cursor.moveToNext());
        }
        return contactList2;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////


    public void addRepo(Repos parola) {

        ContentValues values = new ContentValues();
        values.put(KEY_TESTO2, parola.getTesto2());
        values.put(KEY_LEVEL2, parola.getLevel2());
        values.put(KEY_CATEGORY2, parola.getCategory2());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_REPORT, null, values);
        db.close();
    }
    Repos getRepo(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_REPORT, new String[] { KEY_ID2,
                        KEY_TESTO2, KEY_LEVEL2,KEY_CATEGORY2 }, KEY_ID2 + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Repos m1 = new Repos(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3));
        // return contact
        return m1;
    }
    public boolean ExistRepos(){
        boolean a;
        String selectQuery = "SELECT  * FROM " + TABLE_REPORT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            a=true;
        }else{
            a=false;
        }
        return a;

    }
    public List<Repos> getAllRepos() {
        List<Repos> contactList2 = new ArrayList<Repos>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_REPORT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Repos contact2 = new Repos();
                contact2.setCOD2(Integer.parseInt(cursor.getString(0)));
                contact2.setTesto2(cursor.getString(1));
                contact2.setLevel2(cursor.getString(2));
                contact2.setCategory2(cursor.getString(3));
                // Adding contact to list
                contactList2.add(contact2);
            } while (cursor.moveToNext());
        }
        return contactList2;
    }
    public void deleteAllRepos() {
        String selectQuery = "DELETE  * FROM " + TABLE_REPORT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
    }
    public void deleteRepos(Repos parola) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REPORT, KEY_ID2 + " = ?",
                new String[] { String.valueOf(parola.getCOD2()) });
        db.close();
    }

    public List<Repos> getAllReposSort() {
        List<Repos> contactList2 = new ArrayList<Repos>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_REPORT +" ORDER BY level DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Repos contact2 = new Repos();
                contact2.setCOD2(Integer.parseInt(cursor.getString(0)));
                contact2.setTesto2(cursor.getString(1));
                contact2.setLevel2(cursor.getString(2));
                contact2.setCategory2(cursor.getString(3));
                // Adding contact to list
                contactList2.add(contact2);
            } while (cursor.moveToNext());
        }
        return contactList2;
    }





}