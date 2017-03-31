package com.photon.challenge.modals;


public class DataBean implements Comparable<DataBean> {

    private final int value;
    private final int indexX;
    private final int indexY;


    public DataBean(int value, int indexX, int indexY) {
        super();
        this.value = value;
        this.indexX = indexX;
        this.indexY = indexY;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(DataBean dataBean) {
        if (value > dataBean.getValue()) {
            return 1;
        } else if (value < dataBean.getValue()) {
            return -1;
        } else {
            return 0;
        }
    }

    public int getIndexY() {
        return indexY;
    }

    public int getIndexX() {
        return indexX;
    }
}
