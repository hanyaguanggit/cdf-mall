package com.cdf.mall.demo.combination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 构建组织架构
 *
 *    示例数据：
 *              电子商务部（总工资）
 *                        后勤部（总工资）
 *                              员工1
 *                                 工资
 *                              员工2
 *                                 工资
 *                        开发部（总工资）
 *                              员工1
 *                                 工资
 *                              员工2
 *                                 工资
 *                        人事部（总工资）
 *                              员工1
 *                                  工资
 *                              员工2
 *                                  工资
 * @Author hanyaguang
 * @Date 2022/3/25 13:21
 * @Version 1.0
 */
@Component
public class Organization {

    private static final long ORGANIZATION_ROOT_ID = 1001;
    // 依赖注入
    @Autowired
    private DepartmentRepo departmentRepo;
    // 依赖注入
    @Autowired
    private EmployeeRepo employeeRepo;

    public Department buildOrganization() {
        Department rootDepartment = new Department(ORGANIZATION_ROOT_ID);
        Department department =  buildOrganization(rootDepartment);
        return department;
    }


    private Department buildOrganization(Department department) {
        List<Long> subDepartmentIds = departmentRepo.getSubDepartmentIds(department.getId());
        //部门
        for (Long subDepartmentId : subDepartmentIds) {
            Department subDepartment = new Department(subDepartmentId);
            department.addSubNode(subDepartment);
            buildOrganization(subDepartment);
        }
        //员工
        List<Long> employeeIds = employeeRepo.getDepartmentEmployeeIds(department.getId());
        for (Long employeeId : employeeIds) {
            double salary = employeeRepo.getEmployeeSalary(employeeId);
            department.addSubNode(new Employee(employeeId, salary));
            double salary2 = department.calculateSalary();

        }
        for (Long id : subDepartmentIds) {
            System.out.println("部门"+department.id+"的总工资="+department.calculateSalary());
        }
        return department;
    }

    public static void main(String[] args) {
        Organization organization = new Organization();
        System.out.println(organization.buildOrganization());
    }
}
