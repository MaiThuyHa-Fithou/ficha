package com.mtha.myquizgame;

public class CauHoi {
    private int cauhoi_id;
    private String cauhoi_noidung;

    public int getCauhoi_id() {
        return cauhoi_id;
    }

    public void setCauhoi_id(int cauhoi_id) {
        this.cauhoi_id = cauhoi_id;
    }

    public String getCauhoi_noidung() {
        return cauhoi_noidung;
    }

    public void setCauhoi_noidung(String cauhoi_noidung) {
        this.cauhoi_noidung = cauhoi_noidung;
    }

    public CauHoi() {
    }

    public CauHoi(String cauhoi_noidung) {
        this.cauhoi_noidung = cauhoi_noidung;
    }

    public CauHoi(int cauhoi_id, String cauhoi_noidung) {
        this.cauhoi_id = cauhoi_id;
        this.cauhoi_noidung = cauhoi_noidung;
    }

    @Override
    public String toString() {
        return "CauHoi{" +
                "cauhoi_id=" + cauhoi_id +
                ", cauhoi_noidung='" + cauhoi_noidung + '\'' +
                '}';
    }
}
