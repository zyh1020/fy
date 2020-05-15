package com.rsyb.fycontrol.article;

import com.rsyb.Utils.UploadUtils;
import com.rsyb.fydomain.EditorResultImg;
import com.rsyb.fydomain.PageBean;
import com.rsyb.fydomain.article.FyArticle;
import com.rsyb.fydomain.user.FyUser;
import com.rsyb.fyservice.article.ArticleServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/ArticleControl")
public class ArticleControl {

    @Autowired
    private ArticleServer articleServer;
    /* 到添加一篇文章页面 */
    @RequestMapping("/toArticleFrom")
    public String toArticleFrom(HttpSession httpSession){
        FyUser sessionFyUser = (FyUser) httpSession.getAttribute("sessionFyUser");
        if (sessionFyUser == null){
            httpSession.setAttribute("message","你还未登录");
            return "redirect:/jsps/user/userLogin.jsp";
        }
        return "forward:/WEB-INF/jsps/article/articleFrom.jsp";
    }
    /* 添加一篇文章*/
    @RequestMapping("/addArticleControl")
    public String addArticleControl(FyArticle fyArticle, HttpSession httpSession){
        FyUser sessionFyUser = (FyUser) httpSession.getAttribute("sessionFyUser");
        if (sessionFyUser == null){
            httpSession.setAttribute("message","你还未登录");
            return "redirect:/jsps/user/userLogin.jsp";
        }
        //生成主键
        String fyTid = UUID.randomUUID().toString().replaceAll("-","");
        fyArticle.setFyTid(fyTid);
        fyArticle.setFyGdate(new Date());
        System.out.println(fyArticle);
        fyArticle.setFyUid(sessionFyUser.getFyUid());
        articleServer.addArticle(fyArticle);
        /* 到该用户的修改文章页面 */
        return "redirect:/ArticleControl/findUpdateArticle.do";
    }

    /* 查询某用户文章修改列表 */
    @RequestMapping("/findUpdateArticle")
    public String findUpdateArticle(String fyTitle, HttpServletRequest request){
        FyUser sessionFyUser = (FyUser) request.getSession().getAttribute("sessionFyUser");
        if (sessionFyUser == null){
            request.getSession().setAttribute("message","你还未登录");
            return "redirect:/jsps/user/userLogin.jsp";
        }
        String fyUid = sessionFyUser.getFyUid();
        List<FyArticle> updateArticle = articleServer.findUpdateArticle(fyTitle, fyUid);
        request.setAttribute("articleUpdateList",updateArticle);
        request.setAttribute("fyTitle",fyTitle);
        return "forward:/WEB-INF/jsps/article/articleUpDateList.jsp";
    }

    /* 查询修改某一篇文章 */
    /* 并跳转到修改页面  */
    @RequestMapping("/findOneArticleByFyTid")
    public String findOneArticleByFyTid(String fyTid, HttpServletRequest request){
        FyArticle fyArticle = articleServer.findOneArticleByFyTid(fyTid);
        System.out.println("需要修改的："+fyArticle);
        request.setAttribute("fyArticle",fyArticle);
        return "forward:/WEB-INF/jsps/article/articleUpDate.jsp";
    }

    /* 修改一篇文章*/
    /* 到查询某用户文章列表control */
    @RequestMapping("/updateArticle")
    public String updateArticle(FyArticle fyArticle,HttpServletRequest request){
        System.out.println("更新获取表单值："+fyArticle);
        FyUser sessionFyUser = (FyUser) request.getSession().getAttribute("sessionFyUser");
        if (sessionFyUser == null){
            request.getSession().setAttribute("message","你还未登录");
            return "redirect:/jsps/user/userLogin.jsp";
        }
        String fyUid = sessionFyUser.getFyUid();
        fyArticle.setFyGdate(new Date());
        articleServer.UpdateArticle(fyArticle);
        return "redirect:/ArticleControl/findUpdateArticle.do?fyUid="+fyUid;
    }

    /* 删除文章 */
    /* 到查询某用户文章列表control */
    @GetMapping("/deleteArticle")
    public String deleteArticle(String fyTid,HttpServletRequest request){
        FyUser sessionFyUser = (FyUser) request.getSession().getAttribute("sessionFyUser");
        if (sessionFyUser == null){
            request.getSession().setAttribute("message","你还未登录");
            return "redirect:/jsps/user/userLogin.jsp";
        }
        String fyUid = sessionFyUser.getFyUid();
        articleServer.deleteArticle(fyTid);
        return "redirect:/ArticleControl/findUpdateArticle.do?fyUid="+fyUid;
    }

    /* 分页查询列表 */
    /*  跳转到首页  */
    @RequestMapping("/findPageBeanSelect")
    public String findPageBeanSelect(Integer currentPage,String fySort,HttpServletRequest request){
        if(currentPage == null || currentPage < 1){
            currentPage = 1;
        }
        PageBean<FyArticle> pageBean = articleServer.findPageBean(currentPage,fySort);
        request.setAttribute("pageBean",pageBean);
        request.setAttribute("fySort",fySort); //点击上一页下一页传递条件
        return "forward:/jsps/main.jsp";
    }

    /* 查询一篇文章 */
    @RequestMapping("/findOneArticle")
    public String findOneArticle(String fyTid,HttpServletRequest request){
        System.out.println("查询一篇文章，文章id："+fyTid);
        FyArticle oneArticle = articleServer.findOneArticleByFyTid(fyTid);
        System.out.println("查询一篇文章:"+oneArticle);
        request.setAttribute("oneArticle",oneArticle);
        return "forward:/jsps/article/articleOne.jsp";
    }

    /* 条件查询 */
    @RequestMapping("/findAriticleCondition")
    public String findAriticleCondition(FyArticle fyArticle,HttpServletRequest request){
        System.out.println("条件查询文章，条件:"+fyArticle);
        List<FyArticle> fyArticles = articleServer.conditionSelectArticles(fyArticle);
        System.out.println("查询结果文章:"+fyArticles);
        request.setAttribute("fyArticle",fyArticle);
        request.setAttribute("fyArticles",fyArticles);
        return "forward:/jsps/article/articleSelectList.jsp";
    }

    /* 上传图片 */
    @RequestMapping("/fileUpload")
    @ResponseBody
    public EditorResultImg fileUpload(MultipartFile multipartFile){

        // 设置图片名称，不能重复，可以使用uuid
        String picName = UUID.randomUUID().toString().replaceAll("-","");
        // 获取文件名
        String oriName = multipartFile.getOriginalFilename();
        // 获取图片后缀
        String extName = oriName.substring(oriName.lastIndexOf("."));
        // 存储的文件名
        String fileName = picName + extName;
        System.out.println("文件名："+fileName);
        //生成文件夹
        String pathOfImg = UploadUtils.generateRandomDir(picName);
        File file = new File("/mypic/xx"+pathOfImg);
        if(!file.exists()){
            file.mkdirs();
        }


        // 开始上传
        try {
            multipartFile.transferTo(new File("/mypic/xx" +pathOfImg+ fileName));
        } catch (IOException e) {

        }
        String fy2Img2 = "/pic/xx"+pathOfImg+fileName;
        EditorResultImg editorResultImg = new EditorResultImg();
        editorResultImg.setErrno(0);
        String[] data = {fy2Img2};
        editorResultImg.setData(data);
        return editorResultImg;
    }


}
