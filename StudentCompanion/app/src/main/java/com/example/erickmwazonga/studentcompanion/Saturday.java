package com.example.erickmwazonga.studentcompanion;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class Saturday extends Fragment {
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    StudentDBHelper studentDBHelper;
    Cursor cursor;
    ListLessonAdapter listLessonAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Saturday() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Saturday newInstance(String param1, String param2) {
        Saturday fragment = new Saturday();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_saturday, container, false);

        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),AddLesson.class));
            }
        });
        listView = (ListView) view.findViewById(R.id.listViewSaturday);
        listLessonAdapter = new ListLessonAdapter(getContext(), R.layout.row_layout_lesson);
        listView.setAdapter(listLessonAdapter);

        studentDBHelper = new StudentDBHelper(getContext());
        sqLiteDatabase = studentDBHelper.getReadableDatabase();
        //String day = StudentDBTables.Lesson.DAY;
        cursor = studentDBHelper.getDayLessons("Saturday", sqLiteDatabase);

        if (cursor.moveToFirst()) {
            String name, venue, time;
            name = cursor.getString(0);
            venue = cursor.getString(1);
            time = cursor.getString(2);

            DataProviderLesson dataProviderLesson = new DataProviderLesson(name, venue, time);
            listLessonAdapter.add(dataProviderLesson);
        }

        return view;
    }

}
