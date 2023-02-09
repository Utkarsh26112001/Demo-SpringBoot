package com.markets.service;

import com.markets.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {
    List<EmployeeEntity> getAllEmployees();

    EmployeeEntity saveEmployee(EmployeeEntity employee);

    EmployeeEntity deleteEmployee(Integer eid);

    EmployeeEntity searchEmployee(Integer eid);

    EmployeeEntity updateEmployee(EmployeeEntity employee);
//    EmployeeEntity getEmployeeById(long id);
//    void deleteEmployeeById(long id);
//    void updateEmployee(EmployeeEntity employee);

}
