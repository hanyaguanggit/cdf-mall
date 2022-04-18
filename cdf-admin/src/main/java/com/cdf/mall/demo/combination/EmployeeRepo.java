package com.cdf.mall.demo.combination;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 模拟员工操作类
 * @Author hanyaguang
 * @Date 2022/3/25 13:23
 * @Version 1.0
 */
@Repository
public class EmployeeRepo {

    //todo???  待完善

    /**
     * 查询该部门所有员工id
     * @param id
     * @return
     */
    public List getDepartmentEmployeeIds(long id) {
        List<Long> employIds = new ArrayList<>();
        if(id == 2L){
            employIds.add(111L);
            employIds.add(112L);
            employIds.add(113L);
        }else if(id == 4L){
            employIds.add(133L);
            employIds.add(134L);
        }
        return employIds;
    }

    /**
     * 获取员工工资
     * @param employeeId
     * @return
     */
    public double getEmployeeSalary(Long employeeId) {
        return 2000;
    }
}
