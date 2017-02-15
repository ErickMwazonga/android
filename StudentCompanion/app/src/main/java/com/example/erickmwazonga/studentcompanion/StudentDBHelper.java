package com.example.erickmwazonga.studentcompanion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Erick Mwazonga on 10/22/2016.
 */
public class StudentDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "COMPANIONDB";
    private static final int DATABASE_VERSION = 1;

    private static final String LESSON_CREATE_QUERY =
            "CREATE TABLE " + StudentDBTables.Lesson.TABLE_NAME + "(" + StudentDBTables.Lesson.KEY + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    StudentDBTables.Lesson.DAY + " TEXT," + StudentDBTables.Lesson.NAME + " TEXT," + StudentDBTables.Lesson.CODE + " TEXT," + StudentDBTables.Lesson.TEACHER + " TEXT," +
                    StudentDBTables.Lesson.VENUE + " TEXT," + StudentDBTables.Lesson.STARTTIME + " TEXT);";

    private static final String EXERCISE_CREATE_QUERY =
            "CREATE TABLE " + StudentDBTables.Exercise.TABLE_NAME + "(" + StudentDBTables.Exercise.KEY + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    StudentDBTables.Exercise.NAME + " TEXT," + StudentDBTables.Exercise.DESCRIPTION + " TEXT," +
                    StudentDBTables.Exercise.CHECKDATE + " TEXT," + StudentDBTables.Exercise.CHECKTIME + " TEXT);";


    private static final String TASK_CREATE_QUERY =
            "CREATE TABLE " + StudentDBTables.Task.TABLE_NAME + "(" + StudentDBTables.Task.KEY + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    StudentDBTables.Task.NAME + " TEXT," + StudentDBTables.Task.DESCRIPTION + " TEXT," +
                    StudentDBTables.Task.TASKDATE + " TEXT," + StudentDBTables.Task.TASKTIME + " TEXT);";

    private static final String ASSIGNMENT_CREATE_QUERY =
            "CREATE TABLE " + StudentDBTables.Assignment.TABLE_NAME + "(" + StudentDBTables.Assignment.KEY + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    StudentDBTables.Assignment.NAME + " TEXT," + StudentDBTables.Assignment.SUBJECT + " TEXT," + StudentDBTables.Assignment.DESCRIPTION + " TEXT," +
                    StudentDBTables.Assignment.STATUS + " TEXT," + StudentDBTables.Assignment.DUEDATE + " TEXT," + StudentDBTables.Assignment.SUBMITTIME + " TEXT);";

    private static final String EXAM_CREATE_QUERY =
            "CREATE TABLE " + StudentDBTables.Exam.TABLE_NAME + "(" + StudentDBTables.Exam.KEY + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    StudentDBTables.Exam.NAME + " TEXT," + StudentDBTables.Exam.DESCRIPTION + " TEXT," + StudentDBTables.Exam.VENUE + " TEXT," +
                    StudentDBTables.Exam.EXAMDATE + " TEXT," + StudentDBTables.Exam.STARTTIME + " TEXT);";

    private static final String HOLIDAY_CREATE_QUERY =
            "CREATE TABLE " + StudentDBTables.Holiday.TABLE_NAME + "(" + StudentDBTables.Holiday.KEY + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    StudentDBTables.Holiday.NAME + " TEXT," + StudentDBTables.Holiday.STARTDATE + " TEXT," + StudentDBTables.Holiday.ENDDATE + " TEXT);";


    public StudentDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //this.context = context;
        Log.e("Database Operations", "Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(LESSON_CREATE_QUERY);
        Log.e("Database Operations", "Lesson Table Created");
        sqLiteDatabase.execSQL(EXERCISE_CREATE_QUERY);
        Log.e("Database Operations", "Exercise Table Created");
        sqLiteDatabase.execSQL(TASK_CREATE_QUERY);
        Log.e("Database Operations", "Task Table Created");
        sqLiteDatabase.execSQL(ASSIGNMENT_CREATE_QUERY);
        Log.e("Database Operations", "Assignment Table Created");
        sqLiteDatabase.execSQL(EXAM_CREATE_QUERY);
        Log.e("Database Operations", "Exam Table Created");
        sqLiteDatabase.execSQL(HOLIDAY_CREATE_QUERY);
        Log.e("Database Operations", "Holiday Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + StudentDBTables.Exercise.TABLE_NAME);
        onCreate(sqLiteDatabase);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + StudentDBTables.Exercise.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public void addLesson(String day, String name, String code, String teacher, String venue, String startTime, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(StudentDBTables.Lesson.DAY, day);
        contentValues.put(StudentDBTables.Lesson.NAME, name);
        contentValues.put(StudentDBTables.Lesson.CODE, code);
        contentValues.put(StudentDBTables.Lesson.TEACHER, teacher);
        contentValues.put(StudentDBTables.Lesson.VENUE, venue);
        contentValues.put(StudentDBTables.Lesson.STARTTIME, startTime);
        db.insert(StudentDBTables.Lesson.TABLE_NAME, null, contentValues);
        Log.e("Database Operations", "A row inserted");
    }

    public void addExercise(String name, String description, String checkDate, String checkTime, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(StudentDBTables.Exercise.NAME, name);
        contentValues.put(StudentDBTables.Exercise.DESCRIPTION, description);
        contentValues.put(StudentDBTables.Exercise.CHECKDATE, checkDate);
        contentValues.put(StudentDBTables.Exercise.CHECKTIME, checkTime);
        db.insert(StudentDBTables.Exercise.TABLE_NAME, null, contentValues);
        Log.e("Database Operations", "A row inserted");
    }

    public void addTask(String name, String description, String taskDate, String startTime, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(StudentDBTables.Task.NAME, name);
        contentValues.put(StudentDBTables.Task.DESCRIPTION, description);
        contentValues.put(StudentDBTables.Task.TASKDATE, taskDate);
        contentValues.put(StudentDBTables.Task.TASKTIME, startTime);
        db.insert(StudentDBTables.Task.TABLE_NAME, null, contentValues);
        Log.e("Database Operations", "A row inserted");
    }

    public void addAssignment(String name, String subject, String description, String status, String dueDate, String submitTime, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(StudentDBTables.Assignment.NAME, name);
        contentValues.put(StudentDBTables.Assignment.SUBJECT, subject);
        contentValues.put(StudentDBTables.Assignment.DESCRIPTION, description);
        contentValues.put(StudentDBTables.Assignment.STATUS, status);
        contentValues.put(StudentDBTables.Assignment.DUEDATE, dueDate);
        contentValues.put(StudentDBTables.Assignment.SUBMITTIME, submitTime);
        db.insert(StudentDBTables.Assignment.TABLE_NAME, null, contentValues);
        Log.e("Database Operations", "A row inserted");
    }

    public void addExam(String name, String description, String venue, String examDate, String startTime, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(StudentDBTables.Exam.NAME, name);
        contentValues.put(StudentDBTables.Exam.DESCRIPTION, description);
        contentValues.put(StudentDBTables.Exam.VENUE, venue);
        contentValues.put(StudentDBTables.Exam.EXAMDATE, examDate);
        contentValues.put(StudentDBTables.Exam.STARTTIME, startTime);
        db.insert(StudentDBTables.Exam.TABLE_NAME, null, contentValues);
        Log.e("Database Operations", "A row inserted");
    }

    public void addHoliday(String name, String startDate, String endDate, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(StudentDBTables.Holiday.NAME, name);
        contentValues.put(StudentDBTables.Holiday.STARTDATE, startDate);
        contentValues.put(StudentDBTables.Holiday.ENDDATE, endDate);
        db.insert(StudentDBTables.Holiday.TABLE_NAME, null, contentValues);
        Log.e("Database Operations", "A row inserted");
    }

    public Cursor getExercises(SQLiteDatabase db){
        Cursor cursor;
        String [] projections = {
                StudentDBTables.Exercise.NAME,
                StudentDBTables.Exercise.CHECKDATE,
                StudentDBTables.Exercise.CHECKTIME};
        cursor = db.query(StudentDBTables.Exercise.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;
    }
    public Cursor getTasks(SQLiteDatabase db){
        Cursor cursor;
        String [] projections = {
                StudentDBTables.Task.NAME,
                StudentDBTables.Task.TASKDATE,
                StudentDBTables.Task.TASKTIME};
        cursor = db.query(StudentDBTables.Task.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;
    }
    public Cursor getAssignments(SQLiteDatabase db){
        Cursor cursor;
        String [] projections = {
                StudentDBTables.Assignment.NAME,
                StudentDBTables.Assignment.STATUS,
                StudentDBTables.Assignment.DUEDATE,
                StudentDBTables.Assignment.SUBMITTIME};
        cursor = db.query(StudentDBTables.Assignment.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;
    }

    public Cursor getExams(SQLiteDatabase db){
        Cursor cursor;
        String [] projections = {
                StudentDBTables.Exam.NAME,
                StudentDBTables.Exam.VENUE,
                StudentDBTables.Exam.EXAMDATE,
                StudentDBTables.Exam.STARTTIME};
        cursor = db.query(StudentDBTables.Exam.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;
    }

    public Cursor getHolidays(SQLiteDatabase db){
        Cursor cursor;
        String [] projections = {
                StudentDBTables.Holiday.NAME,
                StudentDBTables.Holiday.STARTDATE,
                StudentDBTables.Holiday.ENDDATE};
        cursor = db.query(StudentDBTables.Holiday.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;
    }

//    //Get content from tables in the database
//    public Cursor getLessons(SQLiteDatabase db){
//        Cursor cursor;
//        String [] projections = {
//                //StudentDBTables.Lesson.DAY,
//                StudentDBTables.Lesson.NAME,
//                StudentDBTables.Lesson.VENUE,
//                StudentDBTables.Lesson.STARTTIME};
//        cursor = db.query(StudentDBTables.Lesson.TABLE_NAME,projections,null,null,null,null,null);
//        return cursor;
//    }

    public Cursor getDayLessons(String day, SQLiteDatabase sqLiteDatabase){
        String [] projections = {
                StudentDBTables.Lesson.NAME,
                StudentDBTables.Lesson.VENUE,
                StudentDBTables.Lesson.STARTTIME};
        String selection = StudentDBTables.Lesson.DAY +" LIKE ?";
        String [] selection_args ={day};
        Cursor cursor = sqLiteDatabase.query( StudentDBTables.Lesson.TABLE_NAME,projections,selection,selection_args,null,null,null);
        return cursor;
    }
}
