package com.rsyb.fycontrol.data;

import com.rsyb.fydomain.data.FyConditionData;
import com.rsyb.fydomain.data.FyData;
import com.rsyb.fydomain.data.analysis.AnanlysisLine;
import com.rsyb.fydomain.data.analysis.AnanlysisSort;
import com.rsyb.fydomain.data.analysis.ContionSort;
import com.rsyb.fydomain.user.FyUser;
import com.rsyb.fyservice.data.DataServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/DataControl")
public class DataControl {

    @Autowired
    private DataServer dataServer;

    /* 注册的拦截器 */
    @InitBinder
    public void InitBinder(HttpServletRequest request,
                           ServletRequestDataBinder binder) {
        // 不要删除下行注释!!! 将来"yyyy-MM-dd"将配置到properties文件中
        // SimpleDateFormat dateFormat = new
        // SimpleDateFormat(getText("date.format", request.getLocale()));
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null, new CustomDateEditor(
                dateFormat, true));
    }


    /* 到添加数据页面 */
    @RequestMapping("/toAddDataFrom")
    public String toAddDataFrom(HttpServletRequest request){
        FyUser sessionFyUser = (FyUser)request.getSession().getAttribute("sessionFyUser");
        if (sessionFyUser == null){
            request.getSession().setAttribute("message","你还未登录");
            return "redirect:/jsps/user/userLogin.jsp";
        }
        return "forward:/WEB-INF/jsps/data/dataFrom.jsp";
    }

    /* 添加一条数据*/
    /* 跳转到操作页面 */
    @RequestMapping("/addOneData")
    public String addOneData(FyData fyData, HttpServletRequest request){
        System.out.println("，，，，，，，，，添加肺炎数据："+fyData);

        FyUser sessionFyUser = (FyUser)request.getSession().getAttribute("sessionFyUser");

        if (sessionFyUser == null){
            request.getSession().setAttribute("message","你还未登录");
            return "redirect:/jsps/user/userLogin.jsp";
        }
        String fyDid = UUID.randomUUID().toString().replaceAll("-","");
        fyData.setFyDid(fyDid);
        fyData.setFyUid(sessionFyUser.getFyUid()); //暂时设置回头session域中那
        dataServer.addOneData(fyData);
        return "redirect:/DataControl/upDateDataLists.do";
    }


    /*  条件查询 */
    /*  转发到条件查询列表  */
    @RequestMapping("/findDatasByFyData")
    public String findDatasByFyData(FyConditionData fyConditionData, HttpServletRequest request){
        System.out.println("，，，，，条件查询："+fyConditionData);
        List<FyData> fyDatas = dataServer.conditionFIndDatas(fyConditionData);
        request.setAttribute("fyConditionData",fyConditionData);
        request.setAttribute("fyDatas",fyDatas);
        return "forward:/jsps/data/dataSelectList.jsp";
    }

    /*  查询更新 */
    /*  跳转到查询更新列表页面  */
    @RequestMapping("/upDateDataLists")
    public String upDateDataLists(FyConditionData fyConditionData, HttpServletRequest request){
        System.out.println("，，，，，条件查询列表："+fyConditionData);
        FyUser sessionFyUser = (FyUser)request.getSession().getAttribute("sessionFyUser");
        if (sessionFyUser == null){
            request.getSession().setAttribute("message","你还未登录");
            return "redirect:/jsps/user/userLogin.jsp";
        }
        fyConditionData.setFyUid(sessionFyUser.getFyUid());
        List<FyData> fyDatas = dataServer.conditionFIndDatas(fyConditionData);
        request.setAttribute("fyConditionData",fyConditionData);
        request.setAttribute("fyDatas",fyDatas);
        return "forward:/WEB-INF/jsps/data/dataUpDateList.jsp";

    }

    /* 删除肺炎数据 */
    /* 该用户的可操作列表页面 */
    @GetMapping("/deleteOneData")
    public String deleteOneData(String fyDid, HttpServletRequest request){
        FyUser sessionFyUser = (FyUser)request.getSession().getAttribute("sessionFyUser");
        if (sessionFyUser == null){
            request.getSession().setAttribute("message","你还未登录");
            return "redirect:/jsps/user/userLogin.jsp";
        }
        dataServer.deleteOneData(sessionFyUser.getFyUid(),fyDid);
        return "redirect:/DataControl/upDateDataLists.do";
    }

    /* 查询文章 */
    @GetMapping("findOneData")
    public String findOneData(String fyDid,HttpServletRequest request){
        FyData oneData = dataServer.findOneData(fyDid);
        request.setAttribute("oneData",oneData);
        return "forward:/jsps/data/dataOne.jsp";
    }

    /* 修改前 查询文章 */
    @GetMapping("findUpdateOneData")
    public String findUpdateOneData(String fyDid,HttpServletRequest request) {
        FyUser sessionFyUser = (FyUser)request.getSession().getAttribute("sessionFyUser");
        if (sessionFyUser == null){
            request.getSession().setAttribute("message","你还未登录");
            return "redirect:/jsps/user/userLogin.jsp";
        }
        FyData fyData = dataServer.findOneData(fyDid);
        request.setAttribute("fyData",fyData);
        return "forward:/WEB-INF/jsps/data/DataUpDate.jsp";
    }

    /* 修改文章 */
    @RequestMapping("upDateOneData")
    public String upDateOneData(FyData fyData,HttpServletRequest request){
        System.out.println(",,,,,,,,,,修改文章信息，，，，，，，，，，");
        FyUser sessionFyUser = (FyUser)request.getSession().getAttribute("sessionFyUser");
        if (sessionFyUser == null){
            request.getSession().setAttribute("message","你还未登录");
            return "redirect:/jsps/user/userLogin.jsp";
        }
        dataServer.updateOneData(fyData);
        request.setAttribute("fyData",fyData);
        return "redirect:/DataControl/upDateDataLists.do";
    }

    /* 数据分析整理 */
    @ResponseBody
    @RequestMapping("/AnalysisFyData")
    public Map AnalysisFyData(ContionSort contionSort){
        System.out.println(",,,,,,,,成功进入，，，，，，，");
        System.out.println(contionSort);


        String fyCity = contionSort.getFyCity();
        Date fyTdate = contionSort.getFyTdate();
        if(fyTdate == null) {
            Date date = new Date(); //获取当前的系统时间。
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = sdf.format(date);
            try {
                fyTdate = sdf.parse(strDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //创建四种情况分别统计
        ContionSort ysContionSort = new ContionSort();
        ysContionSort.setFyCity(fyCity);
        ysContionSort.setFySort("YS");
        ysContionSort.setFyTdate(fyTdate);
        Integer ys = dataServer.conuntFySort(ysContionSort);
        ysContionSort.setFyNumber(1);
        Integer ys01 = dataServer.conuntDate(ysContionSort);
        ysContionSort.setFyNumber(2);
        Integer ys02 = dataServer.conuntDate(ysContionSort);
        ysContionSort.setFyNumber(3);
        Integer ys03 = dataServer.conuntDate(ysContionSort);
        ysContionSort.setFyNumber(4);
        Integer ys04 = dataServer.conuntDate(ysContionSort);
        ysContionSort.setFyNumber(5);
        Integer ys05 = dataServer.conuntDate(ysContionSort);
        ysContionSort.setFyNumber(6);
        Integer ys06 = dataServer.conuntDate(ysContionSort);
        List<Integer> ysdatas = new ArrayList<Integer>();
        ysdatas.add(ys);
        ysdatas.add(ys01);
        ysdatas.add(ys02);
        ysdatas.add(ys03);
        ysdatas.add(ys04);
        ysdatas.add(ys05);
        ysdatas.add(ys06);


        ContionSort qzContionSort = new ContionSort();
        qzContionSort.setFyCity(fyCity);
        qzContionSort.setFySort("QZ");
        qzContionSort.setFyTdate(fyTdate);
        Integer qz = dataServer.conuntFySort(qzContionSort);
        qzContionSort.setFyNumber(1);
        Integer qz01 = dataServer.conuntDate(qzContionSort);
        qzContionSort.setFyNumber(2);
        Integer qz02 = dataServer.conuntDate(qzContionSort);
        qzContionSort.setFyNumber(3);
        Integer qz03 = dataServer.conuntDate(qzContionSort);
        qzContionSort.setFyNumber(4);
        Integer qz04 = dataServer.conuntDate(qzContionSort);
        qzContionSort.setFyNumber(5);
        Integer qz05 = dataServer.conuntDate(qzContionSort);
        qzContionSort.setFyNumber(6);
        Integer qz06 = dataServer.conuntDate(qzContionSort);
        List<Integer> qzdatas = new ArrayList<Integer>();
        qzdatas.add(qz);
        qzdatas.add(qz01);
        qzdatas.add(qz02);
        qzdatas.add(qz03);
        qzdatas.add(qz04);
        qzdatas.add(qz05);
        qzdatas.add(qz06);

        ContionSort swContionSort = new ContionSort();
        swContionSort.setFyCity(fyCity);
        swContionSort.setFySort("SW");
        swContionSort.setFyTdate(fyTdate);
        Integer sw = dataServer.conuntFySort(swContionSort);
        swContionSort.setFyNumber(1);
        Integer sw01 = dataServer.conuntDate(swContionSort);
        swContionSort.setFyNumber(2);
        Integer sw02 = dataServer.conuntDate(swContionSort);
        swContionSort.setFyNumber(3);
        Integer sw03 = dataServer.conuntDate(swContionSort);
        swContionSort.setFyNumber(4);
        Integer sw04 = dataServer.conuntDate(swContionSort);
        swContionSort.setFyNumber(5);
        Integer sw05 = dataServer.conuntDate(swContionSort);
        swContionSort.setFyNumber(6);
        Integer sw06 = dataServer.conuntDate(swContionSort);

        List<Integer> swdatas = new ArrayList<Integer>();
        swdatas.add(sw);
        swdatas.add(sw01);
        swdatas.add(sw02);
        swdatas.add(sw03);
        swdatas.add(sw04);
        swdatas.add(sw05);
        swdatas.add(sw06);


        contionSort.setFySort("ZY");
        Integer zy = dataServer.conuntFySort(contionSort);
        contionSort.setFyNumber(1);
        Integer zy01 = dataServer.conuntDate(contionSort);
        contionSort.setFyNumber(2);
        Integer zy02 = dataServer.conuntDate(contionSort);
        contionSort.setFyNumber(3);
        Integer zy03 = dataServer.conuntDate(contionSort);
        contionSort.setFyNumber(4);
        Integer zy04 = dataServer.conuntDate(contionSort);
        contionSort.setFyNumber(5);
        Integer zy05 = dataServer.conuntDate(contionSort);
        contionSort.setFyNumber(6);
        Integer zy06 = dataServer.conuntDate(contionSort);
        List<Integer> zydatas = new ArrayList<Integer>();
        zydatas.add(zy);
        zydatas.add(zy01);
        zydatas.add(zy02);
        zydatas.add(zy03);
        zydatas.add(zy04);
        zydatas.add(zy05);
        zydatas.add(zy06);

        Map map = new HashMap();
        List<AnanlysisSort> lists = new ArrayList<AnanlysisSort>();
        lists.add(new AnanlysisSort("疑似",ys));
        lists.add(new AnanlysisSort("确诊",qz));
        lists.add(new AnanlysisSort("治愈",zy));
        lists.add(new AnanlysisSort("死亡",sw));

        List<AnanlysisLine>  Lines = new ArrayList<AnanlysisLine>();
        Lines.add( new AnanlysisLine("疑似",ysdatas));
        Lines.add( new AnanlysisLine("确诊",qzdatas));
        Lines.add( new AnanlysisLine("治愈",zydatas));
        Lines.add( new AnanlysisLine("死亡",swdatas));

        map.put("AnanlysisSort", lists);
        map.put("AnanlysisLine", Lines);

        return map;
    }

    @RequestMapping("/test")
    public String test(Date fyTdate){
        System.out.println(",,,,,,,,,,,,,,,,"+fyTdate);
        Integer integer = dataServer.testDate(fyTdate);
        System.out.println(",,,,,,,,,,,,,,"+integer);
        return null;
    }
}
