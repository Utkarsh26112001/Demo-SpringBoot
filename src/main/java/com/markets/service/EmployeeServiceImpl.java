package com.markets.service;

import com.markets.dao.EmployeeDao;
import com.markets.entity.EmployeeEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    @Autowired
    @Override
    public List<EmployeeEntity> getAllEmployees() {
        return employeeDao.findAll();
    }

    @Override
    public EmployeeEntity saveEmployee(EmployeeEntity employee) {
        boolean exists = employeeDao.existsById(employee.geteId());
        if (!exists) {
            employeeDao.save(employee);
        } else {
            System.out.println("Record already present");
        }
        return employee;
    }

    @Override
    public EmployeeEntity deleteEmployee(Integer eid) {
        boolean exists = employeeDao.existsById(eid);
        if (!exists) {
            throw new IllegalStateException("employee with Id " + eid + " does not exists");
        }
        employeeDao.deleteById(eid);
        System.out.println("Deleted Successfully");
        return null;
    }

    @Override
    public EmployeeEntity searchEmployee(Integer eid) {
        Optional<EmployeeEntity> employeeEntityOptional = employeeDao.findById(eid);
        if (employeeEntityOptional.isPresent()) {
            return employeeEntityOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record Not Found");
        }
    }

    @Transactional
    @Override
    public EmployeeEntity updateEmployee(EmployeeEntity employee) {
        EmployeeEntity employee1 = employeeDao.findById(
                employee.geteId()).orElseThrow(() ->
                new IllegalStateException("Employee with ID " + employee.geteId() + " Does not exist"));
        employee1.setName((employee.getName()));
        employee1.setSalary(employee.getSalary());
        return employee1;
    }


}
