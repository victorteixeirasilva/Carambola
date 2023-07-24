package com.carambola.service.Implementation;

import com.carambola.model.Address;
import com.carambola.model.User;
import com.carambola.model.form.customer.CustomerForm;
import com.carambola.model.form.customer.CustomerUpdateForm;
import com.carambola.repository.AddressRepository;
import com.carambola.repository.UserRepository;
import com.carambola.service.CustomerService;
import com.carambola.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CustomerServiceImplementation implements CustomerService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ViaCepService viaCepService;

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

        saveUserWithCep(user);
    }

    @Override
    public User update(Long id, CustomerUpdateForm customerUpdateForm) {
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

        }
        System.out.println("------------ ERRO: Usuário não existe! -------------");
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
