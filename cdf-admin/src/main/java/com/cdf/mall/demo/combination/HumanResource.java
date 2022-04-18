package com.cdf.mall.demo.combination;

/**
 * @Description 人力资源的抽象类
 * @Author hanyaguang
 * @Date 2022/3/25 13:12
 * @Version 1.0
 */
public abstract class HumanResource {
    protected long id;
    protected double salary;

    public HumanResource(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public abstract double calculateSalary();
}
