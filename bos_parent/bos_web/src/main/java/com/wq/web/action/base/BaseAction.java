package com.wq.web.action.base;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wq.utils.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

    public static final String HOME = "home";
    public static final String LIST = "list";

    public PageBean pageBean = new PageBean();
    private DetachedCriteria criteria = null;

    public T model;
    public BaseAction() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class<T> clazz = (Class<T>) type.getActualTypeArguments()[0];
        criteria = DetachedCriteria.forClass(clazz);
        pageBean.setDetachedCriteria(criteria);
        try {
            model = (T) clazz.newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //将java对象转成json,并输出到网页
     /*
            使用json-lib将pageBean转换为json对象,然后通过输出流写会页面中
            JsonObject一般将单个对象转为json
            JsonArray将数组或者集合对象转为json
        */
    public void java2Json(Object obj, String[] strings){

        //将数据转为json
        JsonConfig config = new JsonConfig();
        config.setExcludes(strings);
        String json = JSONObject.fromObject(obj,config).toString();

        //输出到网页端
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void java2Json(List list, String[] strings){

        //将数据转为json
        JsonConfig config = new JsonConfig();
        config.setExcludes(strings);
        String json = JSONArray.fromObject(list,config).toString();

        //输出到网页端
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public T getModel() {
        return model;
    }

    public void setPage(int page) {
        pageBean.setCurrentPage(page);
    }

    public void setRows(int rows) {
        pageBean.setPageSize(rows);
    }
}
