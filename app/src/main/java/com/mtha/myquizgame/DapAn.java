package com.mtha.myquizgame;

public class DapAn {
    private int dapan_id;
    private String dapan_noidung;
    private int dapan_dung;
    private int cauhoi_id;

    public int getCauhoi_id() {
        return cauhoi_id;
    }

    public void setCauhoi_id(int cauhoi_id) {
        this.cauhoi_id = cauhoi_id;
    }

    public int getDapan_id() {
        return dapan_id;
    }

    public void setDapan_id(int dapan_id) {
        this.dapan_id = dapan_id;
    }

    public String getDapan_noidung() {
        return dapan_noidung;
    }

    public void setDapan_noidung(String dapan_noidung) {
        this.dapan_noidung = dapan_noidung;
    }

    public int getDapan_dung() {
        return dapan_dung;
    }

    public void setDapan_dung(int dapan_dung) {
        this.dapan_dung = dapan_dung;
    }

    public DapAn() {
    }

    public DapAn( String dapan_noidung, int dapan_dung) {
        this.dapan_noidung = dapan_noidung;
        this.dapan_dung = dapan_dung;
    }

    public DapAn( String dapan_noidung, int dapan_dung, int cauhoi_id) {
        this.dapan_noidung = dapan_noidung;
        this.dapan_dung = dapan_dung;
        this.cauhoi_id = cauhoi_id;
    }

    public DapAn(int dapan_id, String dapan_noidung, int dapan_dung, int cauhoi_id) {
        this.dapan_id = dapan_id;
        this.dapan_noidung = dapan_noidung;
        this.dapan_dung = dapan_dung;
        this.cauhoi_id = cauhoi_id;
    }

    @Override
    public String toString() {
        return "DapAn{" +
                "dapan_id=" + dapan_id +
                ", dapan_noidung='" + dapan_noidung + '\'' +
                ", dapan_dung=" + dapan_dung +
                ", cauhoi_id=" + cauhoi_id +
                '}';
    }
}
