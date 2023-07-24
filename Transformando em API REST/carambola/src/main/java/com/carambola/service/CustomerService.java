package com.carambola.service;

import com.carambola.model.User;
import com.carambola.model.form.customer.CustomerForm;
import com.carambola.model.form.customer.CustomerUpdateForm;

public interface CustomerService {

    public User update(Long id, CustomerUpdateForm customerUpdateForm);

    public void insert(CustomerForm userForm);
}
