package com.carambola.service;

import com.carambola.model.User;
import com.carambola.model.form.employee.EmployeeForm;
import com.carambola.model.form.employee.EmployeeUpdateForm;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeService {

    public User update(Long id, EmployeeUpdateForm employeeUpdateForm);

    public void insert(EmployeeForm employeeForm);

}