package com.clearnight.oa.base.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.clearnight.oa.base.bean.PageHelper;
import com.clearnight.oa.base.consts.BaseConsts;
import com.clearnight.oa.base.service.IBaseService;
import com.clearnight.oa.user.bean.UserBasic;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestDataBinder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ChenZhao on 2015/11/13 0013.
 * 基础service借口实现类
 */
@Service
public class BaseServiceImpl implements IBaseService{
    @Override
    public String orderHQL(PageHelper pageHelper) {
        String orderString = "";
        if (pageHelper.getSort() != null && pageHelper.getOrder() != null) {
            orderString = " order by t." + pageHelper.getSort() + " " + pageHelper.getOrder();
        }

        return orderString;
    }

    @Override
    public  String whereHQL(Object obj, Map<String, Object> queryParams) {
        String hql = null;
        Field [] fields = obj.getClass().getDeclaredFields();
        if(fields!=null && fields.length>0){
            hql = " WHERE 1=1";
            for(Field field : fields){
                field.setAccessible(true);
	                /*获得字段名称*/
                String fieldName = field.getName();
                try {
                    Object valueObj = field.get(obj);
                    if(field.getType().toString().endsWith("String")){
                        if(valueObj!=null && !valueObj.equals("")){
                            hql += " AND t."+fieldName+" like :"+fieldName;
                            queryParams.put(fieldName,"%"+valueObj+"%");
                        }
                    }else if(field.getType().toString().endsWith("Date")){
                        if(valueObj!=null){
                            Date date = (Date)valueObj;
                            String queryDate;
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(date);
                            int hours = calendar.get(Calendar.HOUR_OF_DAY);
                            int minutes = calendar.get(Calendar.MINUTE);
                            int seconds = calendar.get(Calendar.SECOND);
                            if(hours==0 && minutes==0 && seconds==0){
                                SimpleDateFormat sdf = new SimpleDateFormat(BaseConsts.dateStringFormat);
                                queryDate = sdf.format(date);
                            }else{
                                SimpleDateFormat sdf = new SimpleDateFormat(BaseConsts.dateTimeStringFormat);
                                queryDate = sdf.format(date);
                            }
                            hql += " AND t."+fieldName+" like '%"+queryDate+"%'";
                            //queryParams.put(fieldName,"%"+queryDate+"%");
                        }
                    }else{
                        if(valueObj!=null){
                            hql += " AND t."+fieldName+" = :"+fieldName;
                            queryParams.put(fieldName,valueObj);
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return hql;
    }

    @Override
    public String returnValue(String info,boolean flag) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("flag",flag);
        jsonObject.put("info",info);
        return jsonObject.toJSONString();
    }

    @Override
    public void bindDateParameter(HttpServletRequest request, ServletRequestDataBinder binder) {
        Map<Object,Object> requestParameterMap = request.getParameterMap();
        Set<Object> keySet = requestParameterMap.keySet();
        DateFormat fmt;
        CustomDateEditor dateEditor;
        String [] parameterValues;
        for(Object obj : keySet){
            Object parameterValue = requestParameterMap.get(obj);
            parameterValues = (String[])parameterValue;
            boolean flag1 = this.isValidDate(parameterValues[0],BaseConsts.DATEFORMATSTR_ONE);
            boolean flag2 = this.isValidDate(parameterValues[0],BaseConsts.DATEFORMATSTR_TWO);
            if(flag1){
                fmt = new SimpleDateFormat(BaseConsts.DATEFORMATSTR_ONE);
                dateEditor = new CustomDateEditor(fmt, true);
		        binder.registerCustomEditor(Date.class, dateEditor);
            }else if(flag2){
                fmt = new SimpleDateFormat(BaseConsts.DATEFORMATSTR_TWO);
                dateEditor = new CustomDateEditor(fmt, true);
		        binder.registerCustomEditor(Date.class, dateEditor);
            }

        }
    }

    @Override
    public  boolean isValidDate(String dateStr,String formatStr) {
        boolean convertSuccess=true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(dateStr);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess=false;
        }
        return convertSuccess;
    }
}
