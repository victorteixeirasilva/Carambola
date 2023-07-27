package com.carambola.service.Implementation;

import com.carambola.model.Address;
import com.carambola.model.Role;
import com.carambola.model.User;
import com.carambola.model.form.customer.CustomerUpdateForm;
import com.carambola.model.form.employee.EmployeeForm;
import com.carambola.model.form.employee.EmployeeUpdateForm;
import com.carambola.repository.AddressRepository;
import com.carambola.repository.UserRepository;
import com.carambola.service.EmployeeService;
import com.carambola.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImplementation implements EmployeeService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ViaCepService viaCepService;



    @Override
    public User update(Long id, EmployeeUpdateForm employeeUpdateForm) {
        Optional<User> userBd = userRepository.findById(id);
        if (userBd.isPresent()){
            User user = new User();

            //Id
            user.setId(userBd.get().getId());

            //Name
            if((userBd.get().getName() == employeeUpdateForm.getName())||(employeeUpdateForm.getName()=="")){
                user.setName(userBd.get().getName());
            } else {
                user.setName(employeeUpdateForm.getName());
            }

            //Email
            if((userBd.get().getEmail() == employeeUpdateForm.getEmail())||(employeeUpdateForm.getEmail()=="")){
                user.setEmail(userBd.get().getEmail());
            } else {
                user.setEmail(employeeUpdateForm.getEmail());
            }

            //Password
            if((userBd.get().getPassword() == employeeUpdateForm.getPassword())||(employeeUpdateForm.getPassword()=="")){
                user.setPassword(userBd.get().getPassword());
            } else {
                user.setPassword(employeeUpdateForm.getPassword());
            }

            //Cpf
            user.setCpf(userBd.get().getCpf());

            //Telephone
            if((userBd.get().getTelephone() == employeeUpdateForm.getTelephone())||(employeeUpdateForm.getTelephone()==0)){
                user.setTelephone(userBd.get().getTelephone());
            } else {
                user.setTelephone(employeeUpdateForm.getTelephone());
            }

            userRepository.save(user);

        }
        return userBd.get();
    }

    @Override
    public void insert(EmployeeForm employeeForm) {
        User user = new User();

        user.setName(employeeForm.getName());
        user.setCpf(employeeForm.getCpf());
        user.setEmail(employeeForm.getEmail());
        user.setPassword(employeeForm.getPassword());
        user.setTelephone(employeeForm.getTelephone());

        Role role = new Role();
        role.setId(2);

        user.setRole(role);

        userRepository.save(user);
    }

}