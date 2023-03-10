package com.markets.controller;

import com.markets.MarketsApplication;
import com.markets.entity.EmployeeEntity;
import com.markets.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class EmployeeController {
    private EmployeeService employeeService;
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/service-instances/{MarketsApplication}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String MarketsApplication) {
        return this.discoveryClient.getInstances(MarketsApplication);
    }

    @GetMapping("/getall")
    public List<EmployeeEntity> displayall(){
        return employeeService.getAllEmployees();
    }
    @PostMapping("/saveEmployee")
    public ResponseEntity<EmployeeEntity> saveEmployee(@RequestBody EmployeeEntity employee) {
        // save employee added
        EmployeeEntity tempEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<EmployeeEntity>(tempEmployee,new HttpHeaders(),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{eid}")
    public ResponseEntity<EmployeeEntity> deleteEmployee(@PathVariable("eid") Integer eid){
        EmployeeEntity employee = employeeService.deleteEmployee(eid);
        return new ResponseEntity<EmployeeEntity>(employee,new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping( "/getbyID/{eid}")
    public ResponseEntity<EmployeeEntity> searchEmployee(@PathVariable("eid") Integer eid){
        EmployeeEntity employee = employeeService.searchEmployee(eid);
        return new ResponseEntity<EmployeeEntity>(employee,new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<EmployeeEntity> updateEmployee(@RequestBody EmployeeEntity employee){
        EmployeeEntity tempEmployee1 = employeeService.updateEmployee(employee);
        return new ResponseEntity<EmployeeEntity>(tempEmployee1,new HttpHeaders(),HttpStatus.OK);

    }
}
