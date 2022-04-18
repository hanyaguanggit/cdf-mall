package com.cdf.mall.demo.combination;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 部门
 * @Author hanyaguang
 * @Date 2022/3/25 13:16
 * @Version 1.0
 */
public class Department extends HumanResource {
    private List<HumanResource> subNodes = new ArrayList<>();

    private String name; //部门名称
    public Department(long id) {
        super(id);
    }

    public Department(long id, List<HumanResource> subNodes, String name) {
        super(id);
        this.subNodes = subNodes;
        this.name = name;
    }

    public List<HumanResource> getSubNodes() {
        return subNodes;
    }

    public void setSubNodes(List<HumanResource> subNodes) {
        this.subNodes = subNodes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 计算部门总工资
     * @return
     */
    @Override
    public double calculateSalary() {
        double totalSalary = 0;
        for (HumanResource hr : subNodes) {
            totalSalary += hr.calculateSalary();
        }
        this.salary = totalSalary;
        return totalSalary;
    }

    public void addSubNode(HumanResource hr) {
        subNodes.add(hr);
    }
}
