package br.edu.ifsp.dmo.sitesinteressantes.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dmo.sitesinteressantes.model.Site;
import br.edu.ifsp.dmo.sitesinteressantes.model.TagSite;

public class SiteDao {
    private SQLiteHelper mHelper;
    private SQLiteDatabase mDataBase;
    private Context context;

    public SiteDao(Context context){
        this.context = context;
        mHelper = new SQLiteHelper(context);
    }

    public void create(Site site){
        TagSiteDao tagDao = new TagSiteDao(context);
        int tagId = tagDao.recuperateTagId(site.getTag());

        ContentValues values = new ContentValues();
        values.put(DataBaseContratct.TableSite.COLUMN_TITLE, site.getTitle());
        values.put(DataBaseContratct.TableSite.COLUMN_URL, site.getUrl());
        values.put(DataBaseContratct.TableSite.COLUMN_TAG_ID, tagId);

        mDataBase = mHelper.getWritableDatabase();
        mDataBase.insert(DataBaseContratct.TableSite.TABLE_NAME, null, values);
        mDataBase.close();
    }

    public List<Site> recuperateAll(){
        String query = "SELECT " + "S." + DataBaseContratct.TableSite.COLUMN_TITLE + ", " + "S." + DataBaseContratct.TableSite.COLUMN_URL + ", " + "T." + DataBaseContratct.TableTag.COLUMN_TAG + " FROM " + DataBaseContratct.TableSite.TABLE_NAME + " AS S" + " INNER JOIN " + DataBaseContratct.TableTag.TABLE_NAME + " AS T" + " ON S." + DataBaseContratct.TableSite.COLUMN_TAG_ID + " = T." + DataBaseContratct.TableTag._ID + " ORDER BY S." + DataBaseContratct.TableSite.COLUMN_TITLE;

        mDataBase = mHelper.getReadableDatabase();
        Cursor cursor = mDataBase.rawQuery(query, null);

        List<Site> list = new ArrayList<>();

        while (cursor.moveToNext()){
            list.add(new Site(cursor.getString(0), cursor.getString(1), new TagSite(cursor.getString(2))));
        }

        cursor.close();
        return list;
    }
}
