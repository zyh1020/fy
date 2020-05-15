package com.rsyb.fyservice.user;

import com.rsyb.fydomain.user.FyUser;
import com.rsyb.fydomain.user2.Fy2User;

public interface UserServer {
    public String registUser(FyUser fyUser, Fy2User fy2User);
    public FyUser loginUser(FyUser fyUser);
    public void upDateUser2(Fy2User fy2User);
    /* 用于修改用户头像 */
    public String upDateUser2Head(Fy2User fy2User);
}
