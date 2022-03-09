package com.mtha.myquizgame;

import java.util.ArrayList;
import java.util.List;

public class CauHoi_DapAn {
    List<DapAn> dapAnList = new ArrayList<>();
    CauHoi cauHoi;

    public CauHoi_DapAn() {
        dapAnList = new ArrayList<>();
        cauHoi = new CauHoi();
    }

    public List<DapAn> getDapAnList() {
        return dapAnList;
    }

    public void setDapAnList(List<DapAn> dapAnList) {
        this.dapAnList = dapAnList;
    }

    public CauHoi getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(CauHoi cauHoi) {
        this.cauHoi = cauHoi;
    }
}
