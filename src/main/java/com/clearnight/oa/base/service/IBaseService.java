package com.clearnight.oa.base.service;

import com.alibaba.fastjson.JSONObject;
import com.clearnight.oa.base.bean.PageHelper;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by ChenZhao on 2015/11/13 0013.
 * 基础service借口
 */
public interface IBaseService{

    /**
     * 组装sql排序语句
     * @param pageHelper 分页对象
     * @return String
     */
    public String orderHQL(PageHelper pageHelper);

    /**
     * 条件查询语句
     * @param obj 需要查询的类
     * @param queryParams 查询参数Map集合
     * @return String
     */
    public String whereHQL(Object obj,Map<String,Object> queryParams);

    /**
     * 向前台页面返回数据
     * @param info 返回的信息
     * @param flag 返回的标示
     * @return String
     */
    public String returnValue(String info,boolean flag);

    /**
     * 绑定request中Date类型的参数
     * @param request request请求对象
     * @param binder 绑定参数对象
     */
    public void bindDateParameter(HttpServletRequest request,ServletRequestDataBinder binder);

    /**
     * 判断字符串是否为日期格式
     * @param dateStr
     * @param formatStr
     * @return
     */
    public  boolean isValidDate(String dateStr,String formatStr);
}
