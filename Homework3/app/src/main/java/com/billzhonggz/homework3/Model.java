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
        sqLiteDatabase.execSQL("CREATE TABLE " + tableName1 + "(usual_id INTEGER PRIMARY KEY, SID INTEGER, R INTEGER, G INTEGER, B INTEGER);");
        sqLiteDatabase.execSQL("CREATE TABLE " + tableName2 + "(usual_id INTEGER PRIMARY KEY, SID INTEGER, X INTEGER, Y INTEGER);");
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
    }

    public void addPoint(int sid, int x, int y) {
        SQLiteDatabase wdb = this.getWritableDatabase();
        Object[] args = {sid, x, y};
        wdb.execSQL("INSERT INTO " + tableName2 + "(SID,X,Y) VALUES (?,?,?)", args);
        wdb.close();
    }

    public void deleteLastPoint(int sid) {
        SQLiteDatabase wdb = this.getWritableDatabase();
        wdb.execSQL("DELETE FROM " + tableName2 +
                " WHERE usual_id = (SELECT MIN(usual_id) FROM " + tableName2 + "WHERE SID=" + sid + ")");
        wdb.close();
    }

    public void addListener(IModelListener listener) {
        listeners.add(listener);
    }

    private void notifyAllModelListeners() {
        for (IModelListener listener : listeners)
            listener.notifyModelListener();
    }

    public void drawAll(Canvas canvas) {
        int sid, r, g, b, x1, y1, x2, y2, count;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        SQLiteDatabase rdb = this.getReadableDatabase();
        Cursor cursor1 = rdb.rawQuery("SELECT * FROM " + tableName1, null);
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
            for (count = 0; count < cursor2.getCount() - 1; count++) {
                x1 = cursor2.getInt(cursor2.getColumnIndex("X"));
                y1 = cursor2.getInt(cursor2.getColumnIndex("Y"));
                // Get next point.
                cursor2.moveToNext();
                x2 = cursor2.getInt(cursor2.getColumnIndex("X"));
                y2 = cursor2.getInt(cursor2.getColumnIndex("Y"));
                canvas.drawLine(x1, y1, x2, y2, paint);
            }
        }
    }
}
