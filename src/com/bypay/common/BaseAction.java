package com.bypay.common;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @Description:
 * @Author: ljl
 * @Date: 2014-6-10 下午1:16:08
 */
public abstract class BaseAction extends ActionSupport implements SessionAware,
    ServletRequestAware, ServletResponseAware {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  public Logger log = LoggerFactory.getLogger(this.getClass());
  private HttpServletRequest request;
  private HttpServletResponse response;
  private HttpSession session;
  private Map<String, Object> att;

  public void setServletRequest(HttpServletRequest request) {
    this.request = request;
  }

  public void setServletResponse(HttpServletResponse response) {
    this.response = response;
  }

  public void setSession(Map<String, Object> att) {
    this.att = att;
  }

  public HttpServletRequest getRequest() {
    return ServletActionContext.getRequest();
  }

  public HttpServletResponse getResponse() {
    return ServletActionContext.getResponse();
  }

  public HttpSession getSession() {
    return ServletActionContext.getRequest().getSession();
  }

  public void copyProperties(Object target, Object source) {
    BeanUtils.copyProperties(target, source);
  }

  public <T> T copyProperties(Class<T> destClass, Object orig) {
    return BeanUtils.copyProperties(destClass, orig);
  }

  /**
   * 往request里面设置值
   * 
   * @param key
   *          要设置的关键字
   * @param obj
   *          要设置的值
   */
  protected void setAttribute(String key, Object obj) {
    this.getRequest().setAttribute(key, obj);
  }

  /**
   * 往session里面设置值
   * 
   * @param attrName
   *          关键字
   * @param obj
   *          设置的值
   */
  protected void setSessionAttr(String attrName, Object obj) {
    this.getSession().setAttribute(attrName, obj);
  }

  /**
   * 从session中取得相应的值
   * 
   * @param key
   * @return
   */
  public Object getSessionObj(Object key) {
    return ActionContext.getContext().getSession().get(key);
  }

  /**
   * 获取参数值（字符串）
   * 
   * @param parameterName
   *          要获取参数的名称
   * @return 参数的值
   */
  protected String getParameterForString(String parameterName) {
    return this.getRequest().getParameter(parameterName);
  }

  /**
   * 获取参数值（长整型）
   * 
   * @param parameterName
   *          要获取参数的名称
   * @return 参数的值
   */
  protected long getParameterForLong(String parameterName) throws NumberFormatException {
    if (this.getRequest().getParameter(parameterName) == null
        || this.getRequest().getParameter(parameterName) == "") {
      return 0;
    }
    return Long.parseLong(this.getRequest().getParameter(parameterName));
  }

  /**
   * 获取参数值（整型）
   * 
   * @param parameterName
   *          要获取参数的名称
   * @return 参数的值
   */
  protected int getParameterForInt(String parameterName) throws NumberFormatException {
    if (this.getRequest().getParameter(parameterName) == null
        || this.getRequest().getParameter(parameterName) == "") {
      return 0;
    }
    return Integer.parseInt(this.getRequest().getParameter(parameterName));
  }

  /**
   * 获取参数值(字符串数组)
   * 
   * @param parameterName
   *          要获取参数的名称
   * @return 参数的值
   */
  protected String[] getParameterValues(String parameterName) throws Exception {
    return this.getRequest().getParameterValues(parameterName);
  }

  /**
   * 
   * @Description: 返回json字符串
   * @Auther: ljl
   * @param object
   * @throws IOException
   * @Date: 2014-9-9 下午3:02:43
   */
  protected void renderJSON(Object obj) throws IOException {
    JSONObject jsonObjectFromMap = JSONObject.fromObject(obj);
    renderText(jsonObjectFromMap.toString());
  }

  /**
   * 
   * @Description: 直接输出字符
   * @Auther: ljl
   * @param text
   * @return
   * @throws IOException
   * @Date: 2014-9-9 下午3:02:17
   */
  protected String renderText(String text) throws IOException {
    getResponse().setContentType("text/html;charset=utf-8");
    getResponse().getWriter().print(text);
    return null;
  }
}
