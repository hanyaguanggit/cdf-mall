package com.cdf.mall.demo.combination;

/**
 * @Description 员工
 * @Author hanyaguang
 * @Date 2022/3/25 13:14
 * @Version 1.0
 */
public class Employee extends HumanResource{
    public Employee(long id,double salary) {
        super(id);
        this.salary = salary;
    }

    /**
     * 工资
     * @return
     */
    @Override
    public double calculateSalary() {
        return  salary;
    }


}
