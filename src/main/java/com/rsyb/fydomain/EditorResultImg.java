package com.rsyb.fydomain;

import java.util.Arrays;

public class EditorResultImg {
    private Integer errno;
    private String[] data;

    @Override
    public String toString() {
        return "EditorResultImg{" +
                "errno=" + errno +
                ", data=" + Arrays.toString(data) +
                '}';
    }

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

}
