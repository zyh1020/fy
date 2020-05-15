package com.rsyb.fydao.user;

import com.rsyb.fydomain.user.FyUser;

public interface UserDao {
    public void registUser(FyUser fyUser); //插入
    public FyUser findUser(String fyName); //查询
}
