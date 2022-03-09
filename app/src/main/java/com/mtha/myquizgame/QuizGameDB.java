package com.mtha.myquizgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class QuizGameDB extends SQLiteOpenHelper {
    public final String TAG="QuizGameDB";
    public static final String DB_NAME="quizgame.db";
    public static final int DB_VERSION=1;
    public Context context;
    //table cau hoi
    public static final String TBL_CAUHOI = "tbl_cauhoi";
    public static final String CAUHOI_ID ="cauhoi_ID";
    public static final String CAUHOI_NOIDUNG="cauhoi_noidung";

    //table dap an
    public static final String TBL_DAPAN="tbl_dapan";
    public static final String DAPAN_ID="dapan_ID";
    public static final String DAPAN_NOIDUNG="dapan_noidung";
    public static final String DAPAN_DUNG = "dapan_dung";


    public QuizGameDB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    private void taoBangCauHoi(SQLiteDatabase db){
        String sql ="Create table " +  TBL_CAUHOI +"( " + CAUHOI_ID +" Integer primary key, " +
                CAUHOI_NOIDUNG +" text ) ;" ;
        Log.i(TAG,"taoBangCauHoi: "  + sql);
        db.execSQL(sql);
    }

    private void taoBangDapAn (SQLiteDatabase db){
        String sql = " Create table " + TBL_DAPAN + " ( " + DAPAN_ID+ " Integer primary key, " +
                DAPAN_NOIDUNG +" text, " +  DAPAN_DUNG +" integer, " + CAUHOI_ID
                +" Integer);";
        Log.i(TAG,"taoBangDapAn: "  + sql);
        db.execSQL(sql);
    }

    public void xoaBangCauHoi(SQLiteDatabase db){
        String sql="DROP TABLE IF EXISTS " + TBL_CAUHOI;
        Log.i(TAG,"xoaBangCauHoi: "  + sql);
        db.execSQL(sql);
    }

    public void xoaBangDapAn(SQLiteDatabase db){
        String sql="DROP TABLE IF EXISTS " + TBL_DAPAN;
        Log.i(TAG,"xoaBangDapAn: "  + sql);
        db.execSQL(sql);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        taoBangCauHoi(sqLiteDatabase);
        taoBangDapAn(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        xoaBangCauHoi(sqLiteDatabase);
        xoaBangDapAn(sqLiteDatabase);
        onCreate(sqLiteDatabase);
    }


    public long insCauHoi(CauHoi cauHoi){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CAUHOI_NOIDUNG, cauHoi.getCauhoi_noidung());
        SQLiteDatabase db = getWritableDatabase();
        long kq= db.insert(TBL_CAUHOI, null,contentValues);
        db.close();
        return kq;
    }

    public long insDapAn(DapAn dapAn){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DAPAN_NOIDUNG, dapAn.getDapan_noidung());
        contentValues.put(DAPAN_DUNG, dapAn.getDapan_dung());
        contentValues.put(CAUHOI_ID, dapAn.getCauhoi_id());
        SQLiteDatabase db = getWritableDatabase();
        long kq= db.insert(TBL_DAPAN,null, contentValues);
        db.close();
        return kq;
    }


    public ArrayList<DapAn> getAllDapAn(){
        ArrayList<DapAn> lsDapAn = new ArrayList<>();
        String sql ="select * from " + TBL_DAPAN;
        Cursor cursor = getReadableDatabase().rawQuery(sql,null);
        while (cursor.moveToNext()){
            DapAn dapAn = new DapAn(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3));
            lsDapAn.add(dapAn);
        }
        return lsDapAn;
    }
    public ArrayList<DapAn> getAllDapAn(int id){
        ArrayList<DapAn> lsDapAn = new ArrayList<>();
        String sql ="select * from " + TBL_DAPAN +" where " + CAUHOI_ID +" = ?";
        String args[] ={ String.valueOf(id)};
        Cursor cursor = getReadableDatabase().rawQuery(sql,args);
        while (cursor.moveToNext()){
            DapAn dapAn = new DapAn(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3));
            lsDapAn.add(dapAn);
        }
        return lsDapAn;
    }
    public CauHoi_DapAn getAllCauHoiDapAn(int cauhoiID){
        SQLiteDatabase db = getReadableDatabase();
        CauHoi_DapAn cauHoi_dapAn= new CauHoi_DapAn();
        String sql ="select * from " + TBL_DAPAN +" where " + CAUHOI_ID +" = ? " ;
        String [] args ={String.valueOf(cauhoiID)};
        Cursor cursor = db.rawQuery(sql, args);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String noidung = cursor.getString(1);
            int dapAnDung = cursor.getInt(2);
            DapAn dapAn = new DapAn(id,noidung,dapAnDung,cauhoiID);
            cauHoi_dapAn.dapAnList.add(dapAn);

        }
        cauHoi_dapAn.setCauHoi(getCauHoiByID(cauhoiID));
        return cauHoi_dapAn;
    }
   
    public ArrayList<CauHoi> getAllCauHoi(){
        ArrayList<CauHoi> lsCauHoiArrayList = new ArrayList<>();
        String sql =" select * from " + TBL_CAUHOI ;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        while(cursor.moveToNext()){
            CauHoi cauHoi = new CauHoi(cursor.getInt(0), cursor.getString(1));
            lsCauHoiArrayList.add(cauHoi);
        }
        return lsCauHoiArrayList;
    }
    public DapAn getDapAnByCauHoiID(int id){
        String sql ="select * from " + TBL_DAPAN +" where " + CAUHOI_ID +" = ? " ;
        String [] args ={String.valueOf(id)};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, args);
        DapAn dapAn = new DapAn();
        while (cursor.moveToNext()){
            dapAn.setDapan_id(cursor.getInt(0));
            dapAn.setDapan_noidung(cursor.getString(1));
            dapAn.setDapan_dung(cursor.getInt(2));
            dapAn.setCauhoi_id(cursor.getInt(3));
        }
        return dapAn;
    }

    public long getCountCauHoi(){
        SQLiteDatabase db = getReadableDatabase();
        long count =DatabaseUtils.queryNumEntries(db, TBL_CAUHOI);
        db.close();
        return count;
    }
    public CauHoi getCauHoiByID(int id){
        CauHoi cauHoi = new CauHoi();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from " + TBL_CAUHOI +" where " + CAUHOI_ID +" = ?" ;
        String[] args ={String.valueOf(id)};
        Cursor cursor = db.rawQuery(sql,args);
        while (cursor.moveToNext()){
            cauHoi.setCauhoi_id(cursor.getInt(0));
            cauHoi.setCauhoi_noidung(cursor.getString(1));
        }
        return cauHoi;
    }

    public ArrayList<String> getListDapAnDung(int cauHoiID){
        ArrayList<String> kq = new ArrayList<>();
        ArrayList<DapAn> dapAns = getAllDapAn(cauHoiID);
        for (DapAn dapAn: dapAns) {
            if(dapAn.getDapan_dung()==1){
                kq.add(dapAn.getDapan_noidung());
            }
        }
        return  kq;
    }
}
