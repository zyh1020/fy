package com.rsyb.fyservice.user;


import com.rsyb.fydao.user.UserDao;
import com.rsyb.fydao.user2.User2Dao;
import com.rsyb.fydomain.user.FyUser;
import com.rsyb.fydomain.user2.Fy2User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserServerImpl implements UserServer {
    @Autowired
    private UserDao userDao;
    @Autowired
    private User2Dao user2Dao;
    /* 用于注册 */
    @Transactional
    public String registUser(FyUser fyUser, Fy2User fy2User) {
        FyUser user = userDao.findUser(fyUser.getFyName());
        if(user != null){
            return "用户名已存在！";
        }
        userDao.registUser(fyUser);
        user2Dao.insertUser2(fy2User);
        return null;
    }
    /* 用于登录 */
    public FyUser loginUser(FyUser fyUser){
        System.out.println(",,,,,,,,,,,,UserRegistServerImpl,,,,,,,,,,,,,,,,");
        FyUser user = userDao.findUser(fyUser.getFyName());
        if(user == null){
            user = new FyUser();
            user.setFyCode("用户名不存在");
            return user;
        }
        System.out.println("用户存在！比对密码"+user);
        if(!fyUser.getFyPassword().equals(user.getFyPassword())){
            user.setFyCode("密码不正确");
            return user;
        }
        return user;
    }

    /* 用于修改用户详细信息 */
    public void upDateUser2(Fy2User fy2User) {
        user2Dao.updateUser2(fy2User);
    }

    /* 用于修改用户头像 */
    public String upDateUser2Head(Fy2User fy2User){
        user2Dao.updateUser2OfImg(fy2User);
        return null;
    }
}
