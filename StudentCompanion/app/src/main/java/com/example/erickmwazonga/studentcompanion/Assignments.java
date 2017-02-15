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


public class Assignments extends Fragment {

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    StudentDBHelper studentDBHelper;
    Cursor cursor;
    ListAssignmentAdapter listAssignmentAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Assignments() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Assignments newInstance(String param1, String param2) {
        Assignments fragment = new Assignments();
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
        View view=inflater.inflate(R.layout.fragment_assignments, container, false);

        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),AddAssignment.class));
            }
        });

        listView = (ListView) view.findViewById(R.id.listViewAssignment);
        listAssignmentAdapter= new ListAssignmentAdapter(getActivity(),R.layout.row_layout_assignment);
        listView.setAdapter(listAssignmentAdapter);

        studentDBHelper = new StudentDBHelper(getContext());
        sqLiteDatabase = studentDBHelper.getReadableDatabase();
        cursor = studentDBHelper.getAssignments(sqLiteDatabase);

        if(cursor.moveToFirst()){
            do{
                String name,status,date,time;
                name = cursor.getString(0);
                status=cursor.getString(1);
                date =cursor.getString(2);
                time=cursor.getString(3);

                DataProviderAssignment dataProviderAssignment= new DataProviderAssignment(name,status,date,time);
                listAssignmentAdapter.add(dataProviderAssignment);

            }while (cursor.moveToNext());
        }
        return view;
    }

}
