package com.rsyb.fydomain;

public class DxBean {
    String token;
    String authenticate;
    String mobile;

    @Override
    public String toString() {
        return "DxBean{" +
                "token='" + token + '\'' +
                ", authenticate='" + authenticate + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(String authenticate) {
        this.authenticate = authenticate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
