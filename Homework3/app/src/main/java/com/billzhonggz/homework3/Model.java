package com.billzhonggz.homework3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by ZHONG on 2017/4/30.
 */
public class Model extends SQLiteOpenHelper {
    private ArrayList<IModelListener> listeners;
    private String tableName1 = "SnakeTable";
    private String tableName2 = "PointData";

    public Model(Context context) {
        super(context, "snakes.db", null, 1);
        listeners = new ArrayList<IModelListener>();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create tables.
        sqLiteDatabase.execSQL("CREATE TABLE " + tableName1 + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, SID INTEGER, R INTEGER, G INTEGER, B INTEGER);");
        sqLiteDatabase.execSQL("CREATE TABLE " + tableName2 + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, SID INTEGER, X INTEGER, Y INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Nothing to do.
    }

    public void addSnake(int sid, int r, int g, int b) {
        SQLiteDatabase wdb = this.getWritableDatabase();
        Object[] args = {sid, r, g, b};
        wdb.execSQL("INSERT INTO " + tableName1 + "(SID,R,G,B) VALUES (?,?,?,?)", args);
        wdb.close();
        this.notifyAllModelListeners();
    }

    public void addPoint(int sid, int x, int y) {
        SQLiteDatabase wdb = this.getWritableDatabase();
        Object[] args = {sid, x, y};
        wdb.execSQL("INSERT INTO " + tableName2 + "(SID,X,Y) VALUES (?,?,?)", args);
        wdb.close();
        this.notifyAllModelListeners();
    }

    public void deleteLastPoint(int sid) {
        SQLiteDatabase wdb = this.getWritableDatabase();
        wdb.execSQL("DELETE FROM " + tableName2 +
                " WHERE _id=(SELECT MIN(_id) FROM " + tableName2 + " WHERE SID=" + sid + ");");
        wdb.close();
        this.notifyAllModelListeners();
    }

    public void addListener(IModelListener listener) {
        listeners.add(listener);
    }

    private void notifyAllModelListeners() {
        for (IModelListener listener : listeners)
            listener.notifyModelListener();
    }

    public void drawAll(Canvas canvas) {
        int sid, r, g, b;
        ArrayList<Float> points = new ArrayList<Float>();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        SQLiteDatabase rdb = this.getReadableDatabase();
        Cursor cursor1 = null;
        try {
            // Do query.
            cursor1 = rdb.rawQuery("SELECT * FROM " + tableName1, null);
            // Clear list.
            points.clear();
            while (cursor1.moveToNext()) {
                // Find snake id.
                sid = cursor1.getInt(cursor1.getColumnIndex("SID"));
                // Take corresponding color parameters.
                r = cursor1.getInt(cursor1.getColumnIndex("R"));
                g = cursor1.getInt(cursor1.getColumnIndex("G"));
                b = cursor1.getInt(cursor1.getColumnIndex("B"));
                // Set color.
                paint.setColor(Color.rgb(r, g, b));
                // Query all points.
                Cursor cursor2 = rdb.rawQuery("SELECT * FROM " + tableName2 + " WHERE SID=" + sid, null);
                // Traversal the cursor, draw lines between neighbour points.
                // Check availability for cursor2.
                try {
                    while (cursor2.moveToNext()) {
                        points.add((float)cursor2.getInt(cursor2.getColumnIndex("X")));
                        points.add((float)cursor2.getInt(cursor2.getColumnIndex("Y")));
                    }
                } finally {
                    cursor2.close();
                }
                float[] pointsArray = new float[points.size()];
                int i = 0;
                for (Float f : points) {
                    pointsArray[i++] = (f != null ? f : Float.NaN);
                }
                canvas.drawLines(pointsArray,paint);
            }
        } finally {
            cursor1.close();
        }
        rdb.close();
    }
}
