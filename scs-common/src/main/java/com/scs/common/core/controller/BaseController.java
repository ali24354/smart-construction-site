package com.scs.common.core.controller;

import com.github.pagehelper.PageInfo;
import com.scs.common.core.domain.JsonResult;
import com.scs.common.core.page.TableDataInfo;
import com.scs.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * web层通用数据处理
 *
 * @author ruoyi
 */
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected JsonResult toAjax(int rows) {
        return rows > 0 ? success() : error();
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected JsonResult toAjax(boolean result) {
        return result ? success() : error();
    }

    /**
     * 返回成功
     */
    public JsonResult success() {
        return JsonResult.success();
    }

    /**
     * 返回失败消息
     */
    public JsonResult error() {
        return JsonResult.error();
    }

    /**
     * 返回成功消息
     */
    public JsonResult success(String message) {
        return JsonResult.success(message);
    }

    /**
     * 返回成功数据
     */
    public static JsonResult success(Object data) {
        return JsonResult.success("操作成功", data);
    }

    /**
     * 返回失败消息
     */
    public JsonResult error(String message) {
        return JsonResult.error(message);
    }

    /**
     * 返回错误码消息
     */
    public JsonResult error(JsonResult.Type type, String message) {
        return new JsonResult(type, message);
    }

}
