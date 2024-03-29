package com.carambola.service.Implementation;

import com.carambola.exception.ResponseModel;
import com.carambola.model.Address;
import com.carambola.model.FormOfPayment;
import com.carambola.model.Role;
import com.carambola.model.User;
import com.carambola.model.form.customer.CustomerForm;
import com.carambola.model.form.customer.CustomerUpdateForm;
import com.carambola.repository.AddressRepository;
import com.carambola.repository.CustomerRepository;
import com.carambola.repository.UserRepository;
import com.carambola.service.CustomerService;
import com.carambola.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImplementation implements CustomerService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ViaCepService viaCepService;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void insert(CustomerForm customerForm) {
        User user = new User();

        user.setName(customerForm.getName());
        user.setCpf(customerForm.getCpf());
        user.setEmail(customerForm.getEmail());
        user.setPassword(customerForm.getPassword());
        user.setTelephone(customerForm.getTelephone());
        user.setDateOfBirth(customerForm.getDateOfBirth());
        user.setHouseNumber(customerForm.getHouseNumber());

        Address address = new Address();
        address.setCep(customerForm.getCep());
        user.setAddress(address);

        Role role = new Role();
        role.setId(3);

        user.setRole(role);

        saveUserWithCep(user);
    }

    @Override
    public ResponseEntity showEstablishments() {
         List<User> users = customerRepository.showEstablishments();
         if (!users.isEmpty()){
             return ResponseEntity.ok(users);
         } else {
             ResponseModel responseModel = new ResponseModel(404,"Não existem estabelicimentos cadastrados!");
             return new ResponseEntity<>(responseModel,HttpStatus.NOT_FOUND);
         }
    }

    @Override
    public ResponseEntity update(Long id, CustomerUpdateForm customerUpdateForm) {
        Optional<User> userBd = userRepository.findById(id);
        if (userBd.isPresent()){
            User user = new User();

            //Id
            user.setId(userBd.get().getId());

            //Name
            if((userBd.get().getName() == customerUpdateForm.getName())||(customerUpdateForm.getName()=="")){
                user.setName(userBd.get().getName());
            } else {
                user.setName(customerUpdateForm.getName());
            }

            //Email
            if((userBd.get().getEmail() == customerUpdateForm.getEmail())||(customerUpdateForm.getEmail()=="")){
                user.setEmail(userBd.get().getEmail());
            } else {
                user.setEmail(customerUpdateForm.getEmail());
            }

            //Password
            if((userBd.get().getPassword() == customerUpdateForm.getPassword())||(customerUpdateForm.getPassword()=="")){
                user.setPassword(userBd.get().getPassword());
            } else {
                user.setPassword(customerUpdateForm.getPassword());
            }

            //Telephone
            if((userBd.get().getTelephone() == customerUpdateForm.getTelephone())||(customerUpdateForm.getTelephone()==0)){
                user.setTelephone(userBd.get().getTelephone());
            } else {
                user.setTelephone(customerUpdateForm.getTelephone());
            }

            //DateOfBirth
            if((userBd.get().getDateOfBirth() == customerUpdateForm.getDateOfBirth())||(customerUpdateForm.getDateOfBirth()==null)){
                user.setDateOfBirth(userBd.get().getDateOfBirth());
            } else {
                user.setDateOfBirth(customerUpdateForm.getDateOfBirth());
            }

            //Cpf
            user.setCpf(userBd.get().getCpf());

            //Cep
            if((userBd.get().getAddress().getCep() == customerUpdateForm.getCep())||(customerUpdateForm.getCep()=="")){
                user.setAddress(userBd.get().getAddress());
            } else {
                Address address = new Address();
                address.setCep(customerUpdateForm.getCep());
                user.setAddress(address);
            }

            //HouseNumber
            if((userBd.get().getHouseNumber() == customerUpdateForm.getHouseNumber())){
                user.setHouseNumber(userBd.get().getHouseNumber());
            } else {
                user.setHouseNumber(customerUpdateForm.getHouseNumber());
            }

            saveUserWithCep(user);
            return ResponseEntity.ok(user);
        } else {
            ResponseModel responseModel = new ResponseModel(404,"Não foi possível encontrar esse usuário!");
            return new ResponseEntity(responseModel, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public User insertFormOfPayment(Long id, FormOfPayment formOfPayment) {
        Optional<User> userBd = userRepository.findById(id);
        if (userBd.isPresent()){
            User user = new User();

            //Id
            user.setId(userBd.get().getId());
            //Name
            user.setName(userBd.get().getName());
            //Email
            user.setEmail(userBd.get().getEmail());
            //Password
            user.setPassword(userBd.get().getPassword());
            //Telephone
            user.setTelephone(userBd.get().getTelephone());
            //DateOfBirth
            user.setDateOfBirth(userBd.get().getDateOfBirth());
            //Cpf
            user.setCpf(userBd.get().getCpf());
            //Cep
            user.setAddress(userBd.get().getAddress());
            //HouseNumber
            user.setHouseNumber(userBd.get().getHouseNumber());
            //FormOfPayment
            user.setFormOfPayment(formOfPayment);

            saveUserWithCep(user);

        }
        return userBd.get();
    }

    private void saveUserWithCep(User user){
        String cep = user.getAddress().getCep();
        Address address = addressRepository.findById(cep).orElseGet(() -> {
            Address newAddress = viaCepService.consultCEP(cep);
            addressRepository.save(newAddress);
            return newAddress;
        });
        user.setAddress(address);
        userRepository.save(user);
    }

}
