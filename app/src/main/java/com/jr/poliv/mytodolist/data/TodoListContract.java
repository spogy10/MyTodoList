package com.jr.poliv.mytodolist.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by poliv on 7/26/2017.
 */

public class TodoListContract {

    public static final String CONTENT_AUTHORITY = "com.jr.poliv.mytodolist";
    public static  final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

    public static final String PATH_TODO = "todo";

    public static final class TodoEntry implements BaseColumns{
        public static String TABLE_NAME = "todo";
        public static String COLUMN_DESCRIPTION = "description";
        public static String COLUMN_DATE = "date";
        public static String COLUMN_DONE = "done";


        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TODO).build();
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_TODO;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_TODO;


    }
}
