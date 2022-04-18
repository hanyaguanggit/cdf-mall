package com.cdf.mall.demo.combination;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description TODO
 * @Author hanyaguang
 * @Date 2022/3/25 13:23
 * @Version 1.0
 */
@Repository
public class DepartmentRepo {
    //todo??? 待完善

    /**
     * 查找子部门id
     * @param id
     * @return
     */
    public List<Long> getSubDepartmentIds(Long id) {
        List<Long> deparmentIds = new ArrayList<>();
       if(id == 1001){
           deparmentIds.add(1L);
           deparmentIds.add(2L);
           deparmentIds.add(3L);
           deparmentIds.add(4L);
           deparmentIds.add(5L);
       }else if(id ==1L){
           deparmentIds.add(4L);
           deparmentIds.add(5L);
       }
        return deparmentIds;
    }

}
