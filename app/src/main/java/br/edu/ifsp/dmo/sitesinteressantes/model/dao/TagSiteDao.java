package br.edu.ifsp.dmo.sitesinteressantes.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dmo.sitesinteressantes.model.TagSite;

public class TagSiteDao {
    private SQLiteHelper mHelper;
    private SQLiteDatabase mDatabase;

    public TagSiteDao(Context context){
        mHelper = new SQLiteHelper(context);
    }

    public void create(TagSite tag){
        ContentValues values = new ContentValues();
        values.put(DataBaseContratct.TableTag.COLUMN_TAG, tag.getTag());

        mDatabase = mHelper.getWritableDatabase();
        mDatabase.insert(DataBaseContratct.TableTag.TABLE_NAME, null, values);
        mDatabase.close();
    }

    public boolean update(TagSite tagOld, TagSite tagNew){
        boolean answer;
        ContentValues values = new ContentValues();
        values.put(DataBaseContratct.TableTag.COLUMN_TAG, tagNew.getTag());

        String where = DataBaseContratct.TableTag.COLUMN_TAG + " = ? ";

        String whereArgs[] = {tagOld.getTag()};

        try {
            mDatabase = mHelper.getWritableDatabase();
            mDatabase.update(DataBaseContratct.TableTag.TABLE_NAME, values, where, whereArgs);
        }catch (Exception e){
            answer = false;
        }
        return true;
    }

    public List<TagSite> recuperateAll(){
        List<TagSite> allTags = new ArrayList<>();

        String columns[] = {DataBaseContratct.TableTag.COLUMN_TAG};
        Cursor cursor;

        mDatabase = mHelper.getReadableDatabase();
        cursor = mDatabase.query(DataBaseContratct.TableTag.TABLE_NAME, columns, null, null, null, null, DataBaseContratct.TableTag.COLUMN_TAG);

        while (cursor.moveToNext()){
            allTags.add(new TagSite(cursor.getString(0)));
        }

        cursor.close();
        mDatabase.close();

        return allTags;
    }

    public int recuperateTagId(TagSite tag){
        int id;
        String column[] = {DataBaseContratct.TableTag._ID};
        String where = DataBaseContratct.TableTag.COLUMN_TAG + " = ? ";
        String whereArgs[] = {tag.getTag()};

        Cursor cursor;

        mDatabase = mHelper.getReadableDatabase();
        cursor = mDatabase.query(DataBaseContratct.TableTag.TABLE_NAME, column, where, whereArgs, null, null, null, null);

        cursor.moveToNext();
        id = cursor.getInt(0);

        cursor.close();
        mDatabase.close();

        return id;
    }

    public void delete(TagSite tag){
        String where = DataBaseContratct.TableTag.COLUMN_TAG + "";
        String whereArgs[] = {tag.getTag()};

        mDatabase = mHelper.getWritableDatabase();
        mDatabase.delete(DataBaseContratct.TableTag.TABLE_NAME, where, whereArgs);

        mDatabase.close();
    }
}
