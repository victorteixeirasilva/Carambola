package com.carambola.controller;

import com.carambola.model.User;
import com.carambola.model.form.employee.EmployeeForm;
import com.carambola.model.form.employee.EmployeeUpdateForm;
import com.carambola.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @description Controlador que exibe os end points de contexto de "Employee" para ser consumido pelo
 * front-end.
 *
 * @author victorteixeirasilva 24/julho/2023
 *
 * @version 1.0
 *
 *
 * */

@RestController
@RequestMapping("user/establishment/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeForm> insert(@Valid @RequestBody EmployeeForm employeeForm){
        employeeService.insert(employeeForm);
        return ResponseEntity.ok(employeeForm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody EmployeeUpdateForm employeeUpdateForm){
        return ResponseEntity.ok(employeeService.update(id, employeeUpdateForm));
    }


}
