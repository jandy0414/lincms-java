package cn.chenxins.utils;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MetaJson {

    private String module;

    private String auth;

    private String uri;

    public static Map<String, MetaJson> getMetaMap(WebApplicationContext context){
        RequestMappingHandlerMapping mapping = context.getBean(RequestMappingHandlerMapping.class);
        //获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        Map<String,MetaJson> authMap=new HashMap<>();
        String mName,module,auth;
        MetaJson metaJson;
        for (RequestMappingInfo info : map.keySet()){

            mName=info.getName();
            if (StringUtil.isNotBlank(mName))
            {
                String[] ma=mName.split("#");
                if (ma.length==2)
                {
                    module=ma[0];
                    auth=ma[1];
                } else  {
                    module="";
                    auth=mName;
                }
                //获取url的Set集合，一个方法可能对应多个url
                Set<String> patterns = info.getPatternsCondition().getPatterns();
                for (String url : patterns){
                    metaJson=new MetaJson(module,auth,url);
                    authMap.put(auth,metaJson);
                    break;
                }
            }
        }
        return authMap;
    }

    public static Map<String, MetaJson> getMetaMapUsingUri(WebApplicationContext context){
        RequestMappingHandlerMapping mapping = context.getBean(RequestMappingHandlerMapping.class);
        //获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        Map<String,MetaJson> authMap=new HashMap<>();
        String mName,module,auth;
        MetaJson metaJson;
        for (RequestMappingInfo info : map.keySet()){

            mName=info.getName();
            if (StringUtil.isNotBlank(mName))
            {
                String[] ma=mName.split("#");
                if (ma.length==2)
                {
                    module=ma[0];
                    auth=ma[1];
                } else  {
                    module="";
                    auth=mName;
                }
                //获取url的Set集合，一个方法可能对应多个url
                Set<String> patterns = info.getPatternsCondition().getPatterns();
                for (String url : patterns){
                    metaJson=new MetaJson(module,auth,url);
                    authMap.put(url,metaJson);
                    break;
                }
            }
        }
        return authMap;
    }


    public MetaJson(String module, String auth, String uri) {
        this.module = module;
        this.auth = auth;
        this.uri = uri;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
