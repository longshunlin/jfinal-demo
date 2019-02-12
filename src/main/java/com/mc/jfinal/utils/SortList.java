package com.mc.jfinal.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * 排序对象
 * @param <E>
 */
public class SortList<E> {
    /**
     * 排序方法
     * @param list 要排序的列表
     * @param method 获取排序值的方法名
     * @param sort asc升序 desc降序
     */
    public void sort(List<E> list, final String method, final String sort) {
        Collections.sort(list, (a, b) -> {
            int ret = 0;
            try {
                Method m1 = ((E) a).getClass().getMethod(method, null);
                Method m2 = ((E) b).getClass().getMethod(method, null);
                Object v1 = m1.invoke(((E) a), null);
                Object v2 = m2.invoke(((E) b), null);

                if (sort != null && "desc".equals(sort)) { //倒序
                    if(v1 instanceof Integer) {
                        Integer i1 = (Integer)v1;
                        Integer i2 = (Integer)v2;
                        ret = i2.compareTo(i1);
                    } else {
                        ret = v2.toString().compareTo(v1.toString());
                    }
                } else {
                    if(v1 instanceof Integer) {
                        Integer i1 = (Integer)v1;
                        Integer i2 = (Integer)v2;
                        ret = i1.compareTo(i2);
                    } else {
                        ret = v1.toString().compareTo(v2.toString());
                    }
                }
            } catch (NoSuchMethodException ne) {
                System.out.println(ne);
            } catch (IllegalAccessException ie) {
                System.out.println(ie);
            } catch (InvocationTargetException it) {
                System.out.println(it);
            }
            return ret;
        });
    }

    public static void main(String[] args) {

    }
}
