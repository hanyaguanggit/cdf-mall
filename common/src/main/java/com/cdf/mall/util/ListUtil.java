package com.cdf.mall.util;

import java.util.List;

/**
 * Created by gaocl on 16-3-28.
 */
public class ListUtil {

//    public static String listToString(List<Object> lst) {
//        StringBuffer sb = new StringBuffer();
//        for (Object obj : lst) {
//            sb.append(obj.toString() + ",");
//        }
//        return sb.substring(0, sb.length() - 1);
//    }
    
	/**
	 * 作 者：姚晓进<br/>
	 * @param lst
	 * @return
	 */
    public static <T> String listToString(List<T> lst) {
        StringBuffer sb = new StringBuffer();
        for (T t : lst) {
            sb.append(t.toString() + ",");
        }
        if(lst.size()==0){
        	return "";
        }
        return sb.substring(0, sb.length() - 1);
    }


    /**
     * 开始分页
     * @param list
     * @param pageNum 页码
     * @param pageSize 每页多少条数据
     * @return
     */
    public static List startPage(List list, Integer pageNum, Integer pageSize) {
        if (list == null) {
            return null;
        }
        if (list.size() == 0) {
            return null;
        }

        Integer count = list.size(); // 记录总数
        Integer pageCount = 0; // 页数
        if (count % pageSize == 0) {
            pageCount = count / pageSize;
        } else {
            pageCount = count / pageSize + 1;
        }

        int fromIndex = 0; // 开始索引
        int toIndex = 0; // 结束索引

        if (pageNum != pageCount) {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        } else {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = count;
        }
        if(fromIndex >= list.size()){
            return null;
        }

        List pageList = list.subList(fromIndex, toIndex);

        return pageList;
    }
}
