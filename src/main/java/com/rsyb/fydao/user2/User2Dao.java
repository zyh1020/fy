package com.rsyb.fydao.user2;

import com.rsyb.fydomain.user2.Fy2User;

public interface User2Dao {
    // 插入
    public void insertUser2(Fy2User fy2User);
    // 主键查询
    public Fy2User selectByFy2Uid(String fy2Uid);
    // 外键查询
    public Fy2User selectByFyUidy(String fyUidy);
    // 修改
    public void  updateUser2(Fy2User fy2User);
    // 修改头像
    public void  updateUser2OfImg(Fy2User fy2User);
}
