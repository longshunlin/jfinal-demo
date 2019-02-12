package com.mc.jfinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.kit.StrKit;
import org.apache.commons.lang.StringEscapeUtils;

/**
 * Created by Administrator on 2018/4/28.
 */
public class SqlInterceptor implements Interceptor {

    public void intercept(Invocation invocation) {
        Object[] args = invocation.getArgs();
        if (args != null && args.length > 0){
            for (int i = 0; i < args.length; i++) {
                if (args[i]!=null && args[i].getClass() == String.class) {
                    if( StrKit.notBlank(String.valueOf(args[i])) ) {
                        args[i] = StringEscapeUtils.escapeSql(args[i].toString());
                    }
                }
            }
        }
        invocation.setReturnValue(args);
        invocation.invoke();
    }
}
