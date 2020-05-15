package com.rsyb.fycontrol.user;

import com.google.code.kaptcha.Constants;
import com.rsyb.Utils.Dx;
import com.rsyb.Utils.UploadUtils;
import com.rsyb.fydomain.DxBean;
import com.rsyb.fydomain.user.FyUser;

import com.rsyb.fydomain.user2.Fy2User;
import com.rsyb.fyservice.user.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/UserControl")
public class UserControl {
    @Autowired
    private UserServer userServer;

    /* 用于发送手机验证码 */
    @RequestMapping("/code")
    @ResponseBody
    public String code(@RequestBody DxBean dxBean, HttpSession session){
        System.out.println(dxBean);
        Boolean ok = null;
        try {
            ok = Dx.isOk(dxBean.getToken(), dxBean.getAuthenticate());
        } catch (IOException e) {
            return "{\"dxMsg\":false}";
        }
        if(!ok){
            //主要key/value值必须使用含有转义字符\的双引号，单引号无效
            return "{\"dxMsg\":false}";
        }
        if(dxBean.getMobile() == null){
            return "{\"dxMsg\":false}";
        }
        /* 创建发送的随机字符串 */
        String nonce_str = Dx.getNonce_str();
        System.out.println("-------------------------->验证码："+nonce_str);
        /* 发送 */
        Boolean aBoolean = Dx.sendCode(dxBean.getMobile(), nonce_str);
        if(!aBoolean){
            return "{\"dxMsg\":false}";
        }
        session.setAttribute("pthoneCode",nonce_str);

        return "{\"dxMsg\":true}";
    }

    @RequestMapping("/regist")
    public String regist(FyUser fyUser, HttpServletRequest request){
        /* 比较手机验证码 */
        String pthoneCode = (String) request.getSession().getAttribute("pthoneCode");
        if(!fyUser.getFyCode().equalsIgnoreCase(pthoneCode)){
            System.out.println("--------->pthoneCode:"+pthoneCode);
            System.out.println("--------->fyUser.getFyCode():"+fyUser.getFyCode());
            request.setAttribute("message","手机验证码不正确！" );
            request.setAttribute("fyUser",fyUser);//这是用于回显信息
            return "forward:/jsps/user/userRegist.jsp";
        }
        //生成主键
        String fyUid = UUID.randomUUID().toString().replaceAll("-","");
        fyUser.setFyUid(fyUid);

        // 生成user2
        Fy2User fy2User = getFy2User();
        String fy2Uid = UUID.randomUUID().toString().replaceAll("-","");
        fy2User.setFy2Uid(fy2Uid);
        fy2User.setFyUidy(fyUid);
        fy2User.setFy2Name(fyUser.getFyName());
        // 保存到数据库
        String message = userServer.registUser(fyUser,fy2User);
        if(message != null){
            System.out.println("注册失败" + message);
            request.setAttribute("message",message );
            request.setAttribute("fyUser",fyUser);//这是用于回显信息
            return "forward:/jsps/user/userRegist.jsp";
        }
        return "redirect:/jsps/user/userLogin.jsp";
    }

    @RequestMapping("/login")
    public String login(FyUser fyUser, HttpServletRequest request){
        String fromCode = fyUser.getFyCode();
        String sessionCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //比较验证码
        if(fromCode != null && !fromCode.equalsIgnoreCase(sessionCode)){
            request.setAttribute("message","验证码不正确！" );
            request.setAttribute("fyUser",fyUser);//这是用于回显信息
            return "forward:/jsps/user/userLogin.jsp";
        }

        FyUser fyUser2 = userServer.loginUser(fyUser);

        String message  = fyUser2.getFyCode();
        System.out.println(message);

        if(message != null){
            request.setAttribute("message",message );
            request.setAttribute("fyUser",fyUser);//这是用于回显信息
            return "forward:/jsps/user/userLogin.jsp";
        }else{
            request.getSession().setAttribute("sessionFyUser", fyUser2);
            return "redirect:/ArticleControl/findPageBeanSelect.do";
        }
    }

    @RequestMapping("/quit")
    public String quit(HttpServletRequest request){
        request.getSession().invalidate();//销毁session
        return "redirect:/index.jsp";
    }

    @RequestMapping("/updateUser2")
    public String updateUser2(Fy2User fy2User,HttpSession httpSession){
        System.out.println(fy2User);
        FyUser fyUser = (FyUser) httpSession.getAttribute("sessionFyUser");
        if(fyUser == null){
            httpSession.setAttribute("message","你还没有登录！");
            return "redirect:/jsps/user/userLogin.jsp";
        }
        userServer.upDateUser2(fy2User);
        String fy2Img = fyUser.getFy2User().getFy2Img();
        /* 修改图片链接 */
        fy2User.setFy2Img(fy2Img);
        /* 修改session中的对象*/
        fyUser.setFy2User(fy2User);
        System.out.println(fyUser);
        httpSession.setAttribute("sessionFyUser",fyUser);
        return "redirect:/jsps/main.jsp";
    }

    @RequestMapping(value = "/uploadHead",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String,String> uploadHead(Fy2User fy2User, MultipartFile multipartFile,HttpSession httpSession){

        Map rmap = new HashMap();

        FyUser fyUser = (FyUser) httpSession.getAttribute("sessionFyUser");

        if(fyUser == null){
            httpSession.setAttribute("message","你还没有登录！");
            rmap.put("isOk","C");
            return rmap;
        }

        // 设置图片名称，不能重复，可以使用uuid
        String picName = UUID.randomUUID().toString().replaceAll("-","");
        // 获取文件名
        String oriName = multipartFile.getOriginalFilename();
        // 获取图片后缀
        String extName = oriName.substring(oriName.lastIndexOf("."));
        if(!extName.endsWith("jpg")){
            rmap.put("isOk","C");
            return rmap;
        }
        // 存储的文件名
        String fileName = picName + extName;
        System.out.println("文件名："+fileName);

        //生成文件夹
        String pathOfImg = UploadUtils.generateRandomDir(picName);

        File file = new File("/mypic"+pathOfImg);
        if(!file.exists()){
            boolean mkdirs = file.mkdirs();
            if(!mkdirs)
            {
                rmap.put("isOk","B");
                return rmap;
            }
        }

        System.out.println("存储路径!"+"/mypic" +pathOfImg);

        // 开始上传
        try {
            multipartFile.transferTo(new File("/mypic" +pathOfImg+ fileName));
        } catch (IOException e) {
            rmap.put("isOk","D");
            return rmap;
        }
        String fy2Img = fy2User.getFy2Img();
        System.out.println("原文件路径！"+fy2Img);


        String fy2Img2 = "/pic"+pathOfImg+fileName;
        System.out.println("改后文件路径"+fy2Img2);
        fy2User.setFy2Img(fy2Img2);
        userServer.upDateUser2Head(fy2User);

        /* 更改session中的值 */
        Fy2User fy2User1 = fyUser.getFy2User();
        fy2User1.setFy2Img(fy2Img2);
        fyUser.setFy2User(fy2User1);
        httpSession.setAttribute("sessionFyUser",fyUser);

        rmap.put("isOk","A");
        rmap.put("imgUrl",fy2Img2);
        return rmap;
    }
    private Fy2User getFy2User(){
        Fy2User fy2User = new Fy2User();
        String fy2Uid = UUID.randomUUID().toString().replaceAll("-","");
        fy2User.setFy2Uid(fy2Uid);
        fy2User.setFy2Age("18");
        fy2User.setFy2Img("/pic/every.jpg");
        fy2User.setFy2Qm("这个人很懒没有签名！");
        fy2User.setFy2Sex("女");
        return fy2User;
    }


}
