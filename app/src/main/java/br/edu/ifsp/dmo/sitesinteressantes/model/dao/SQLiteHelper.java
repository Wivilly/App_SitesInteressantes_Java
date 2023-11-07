package br.edu.ifsp.dmo.sitesinteressantes.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    public SQLiteHelper(Context context){
        super(context, DataBaseContratct.DATABASE_NAME, null, DataBaseContratct.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("PRAGMA foreing_keys=ON;");
        sqLiteDatabase.execSQL(DataBaseContratct.TableTag.CREATE_TABLE);
        sqLiteDatabase.execSQL(DataBaseContratct.TableSite.CREATE_TABLE);
    }

    /* i = versão antiga, e i1 = nova versão*/
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
