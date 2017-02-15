package com.example.erickmwazonga.studentcompanion;

/**
 * Created by Erick Mwazonga on 10/22/2016.
 */
public class StudentDBTables {
    public static abstract class Lesson {
        public static final String TABLE_NAME="lesson";
        public static final String KEY = "key";
        public static final String DAY = "day";
        public static final String NAME = "name";
        public static final String CODE = "code";
        public static final String TEACHER = "teacher";
        public static final String VENUE = "venue";
        public static final String STARTTIME = "start_Time";
    }

    public static abstract class Exercise{
        public static final String TABLE_NAME="exercise";
        public static final String KEY = "key";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String CHECKDATE = "checkDate";
        public static final String CHECKTIME = "startTime";
    }

    public static abstract class Task{
        public static final String TABLE_NAME="task";
        public static final String KEY = "key";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String TASKDATE = "taskDate";
        public static final String TASKTIME = "taskTime";
    }

    public static abstract class Assignment{
        public static final String TABLE_NAME="assignment";
        public static final String KEY = "key";
        public static final String NAME = "name";
        public static final String SUBJECT = "subject";
        public static final String DESCRIPTION = "description";
        public static final String STATUS = "status";
        public static final String DUEDATE = "dueDate";
        public static final String SUBMITTIME = "submitTime";
    }

    public static abstract class Exam{
        public static final String TABLE_NAME="exam";
        public static final String KEY = "key";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String VENUE = "venue";
        public static final String EXAMDATE = "examDate";
        public static final String STARTTIME = "startTime";
    }

    public static abstract class Holiday{
        public static final String TABLE_NAME="holiday";
        public static final String KEY = "key";
        public static final String NAME = "name";
        public static final String STARTDATE = "startDate";
        public static final String ENDDATE = "endTime";
    }
}
