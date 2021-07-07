package com.example.park;

public class Helper {

    String qrcode;
    int s,m,h,d,mo,y;

    public Helper() {

    }

    public Helper(String qrcode, int s, int m, int h, int d, int mo, int y) {
        this.qrcode = qrcode;
        this.s = s;
        this.m = m;
        this.h = h;
        this.d = d;
        this.mo = mo;
        this.y = y;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getMo() {
        return mo;
    }

    public void setMo(int mo) {
        this.mo = mo;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
