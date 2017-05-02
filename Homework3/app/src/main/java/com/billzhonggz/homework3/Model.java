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
 * Homework Assignment 3
 * Mobile Application Development
 * 1430003045 Junru Zhong (Bill)
 * May 2nd, 2017
 */

public class Model extends SQLiteOpenHelper {
    private ArrayList<IModelListener> listeners;
    // Identify table names.
    private String tableName1 = "SnakeData";
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

    public synchronized void addSnake(int sid, int r, int g, int b) {
        SQLiteDatabase wdb = this.getWritableDatabase();
        Object[] args = {sid, r, g, b};
        wdb.execSQL("INSERT INTO " + tableName1 + "(SID,R,G,B) VALUES (?,?,?,?)", args);
        wdb.close();
        this.notifyAllModelListeners();
    }

    public synchronized void addPoint(int sid, int x, int y) {
        SQLiteDatabase wdb = this.getWritableDatabase();
        Object[] args = {sid, x, y};
        wdb.execSQL("INSERT INTO " + tableName2 + "(SID,X,Y) VALUES (?,?,?)", args);
        wdb.close();
        this.notifyAllModelListeners();
    }

    public synchronized void deleteLastPoint(int sid) {
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

    public synchronized void drawAll(Canvas canvas) {
        // Initialize variables.
        int sid, r, g, b, x1, y1, x2, y2, count;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        // Find a database.
        SQLiteDatabase rdb = this.getReadableDatabase();
        // Initialize cursors.
        Cursor cursor1 = rdb.rawQuery("SELECT * FROM " + tableName1, null);
        Cursor cursor2 = null;

        // Read cursor1 for snakes.
        // For each snake, search points according to SID.
        while (cursor1.moveToNext()) {
            // Find snake id.
            sid = cursor1.getInt(cursor1.getColumnIndex("SID"));
            // Take corresponding color parameters.
            r = cursor1.getInt(cursor1.getColumnIndex("R"));
            g = cursor1.getInt(cursor1.getColumnIndex("G"));
            b = cursor1.getInt(cursor1.getColumnIndex("B"));
            // Set color.
            paint.setColor(Color.rgb(r, g, b));

            // Query all points for each snake.
            cursor2 = rdb.rawQuery("SELECT * FROM " + tableName2 + " WHERE SID=" + sid, null);
            // Get the length of this query result.
            count = cursor2.getCount();
            // Get the first point. If the cursor isn't null.
            if (cursor2.moveToFirst()) {
                x1 = cursor2.getInt(cursor2.getColumnIndex("X"));
                y1 = cursor2.getInt(cursor2.getColumnIndex("Y"));
                // Loop to find points and draw lines.
                for (int i = 0; i < count - 1; i++) {
                    cursor2.moveToNext();
                    x2 = cursor2.getInt(cursor2.getColumnIndex("X"));
                    y2 = cursor2.getInt(cursor2.getColumnIndex("Y"));
                    // Do draw line.
                    canvas.drawLine((float) x1, (float) y1, (float) x2, (float) y2, paint);
                    // Shift current point to the previous point.
                    x1 = x2;
                    y1 = y2;
                }
            }
        }
        // Close database connection.
        if (cursor2 != null)
            cursor2.close();
        cursor1.close();
        rdb.close();
    }
}