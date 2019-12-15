package com.snake.abbaqus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import link.fls.swipestack.SwipeStack;

import static com.snake.abbaqus.SQLiteDBHelper.TABLE_NAME;

public class SavedData extends AppCompatActivity {

    private Context mContext;
    private SwipeStack swipeStack;
    private List<Children> list = new ArrayList<>();
    private StackAdapter stackAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_data);

        mContext = this;
        swipeStack = findViewById(R.id.swipeStack);
        swipeStack.setListener(new SwipeStack.SwipeStackListener() {
            @Override
            public void onViewSwipedToLeft(int position) {
            }

            @Override
            public void onViewSwipedToRight(int position) {
            }

            @Override
            public void onStackEmpty() {
                swipeStack.resetStack();
            }
        });
        stackAdapter = new StackAdapter(mContext, list);
        readFromDB();
    }

    private void readFromDB() {
        SQLiteDatabase database = new SQLiteDBHelper(this).getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Log.d(this.getClass().getSimpleName(), "readFromDB: "+cursor.getColumnName(0)+cursor.getColumnName(2)+cursor.getColumnName(3)+cursor.getColumnName(4));
                list.add(new Children("", new InnerData(cursor.getString(1), cursor.getString(2), cursor.getString(4))));
                swipeStack.setAdapter(stackAdapter);
                stackAdapter.notifyDataSetChanged();
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}