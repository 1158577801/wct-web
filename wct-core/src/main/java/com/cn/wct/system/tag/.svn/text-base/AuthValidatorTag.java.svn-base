package com.cn.wct.system.tag;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.cn.wct.system.configRoute.SysConstant;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.redis.Redis;

/**
 * 
 * @author wangzhen
 * 2015年11月3日 下午11:53:36
 * description 【 自定义标签权限控制】
 */
public class AuthValidatorTag extends TagSupport {
    private static final long serialVersionUID = 1L;

    /**操作标识*/
    private String ifAnyGranted = null;
    /**数据ID*/
    private String dataId = null;
    /**数据类型*/
    private String dataType = null;
    

    /**
     * 判断操作是否显示
     * @return String
     * @throws JspException jsp
     */
    @SuppressWarnings("unchecked")
	@Override
    public int doStartTag() throws JspException {
    	ServletRequest servletRequest=pageContext.getRequest();
    	HttpServletRequest httpRequest=(HttpServletRequest)servletRequest;
        HttpSession session=httpRequest.getSession();
        Object s=session.getAttribute(SysConstant.CACHE_KEY);
        if(null!=s){
        	/*Record re=(Record)s;
        	String user_id=re.get("user_id");
        	if(null==user_id){
        		 return SKIP_BODY;
        	}
        	Object b=Redis.use(SysConstant.REDIS_PROP_KIT.get("cacheName")).get(SysConstant.USER_OPERATELIST+"_"+user_id);
        	if(null!=b){
        		Map<String, String> map=(Map<String, String>)b;
        		if(map.containsKey(ifAnyGranted.trim())){
        			return EVAL_BODY_INCLUDE;
        		}
        	}*/
    	}
    	//1、如果返回的是：EVAL_BODY_INCLUDE, 则继续计算Body。                                             
    	//2、如果返回的是：SKIP_BODY 就不计算 Body。 
        return SKIP_BODY;
    }



	public String getIfAnyGranted() {
		return ifAnyGranted;
	}

	public void setIfAnyGranted(String ifAnyGranted) {
		this.ifAnyGranted = ifAnyGranted;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
   
}
