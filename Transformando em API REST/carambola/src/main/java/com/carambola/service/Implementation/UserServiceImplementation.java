package com.carambola.service.Implementation;

import com.carambola.model.Address;
import com.carambola.model.User;
import com.carambola.model.form.UserForm;
import com.carambola.repository.AddressRepository;
import com.carambola.repository.UserRepository;
import com.carambola.service.UserService;
import com.carambola.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description Implementação da <b>Strategy</b> {@link  UserService}, a qual pode ser
 * injetada pelo Spring (via {@link org.springframework.beans.factory.annotation.Autowired}).
 * Com isso, como essa classe é um {@link org.springframework.stereotype.Service}, ela será
 * tratada como um <b>Singleton</b>.
 *  Essa classe do tipo service, serve para conter a regra de negócio.
 *
 * @author victorteixeirasilva 24/julho/2023
 *
 * @version 1.0
 * */

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<User> allUsers() {
       return userRepository.findAll();
    }

    @Override
    public void insert(UserForm userForm) {
        User user = new User();

        user.setName(userForm.getName());
        user.setCpf(userForm.getCpf());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setTelephone(userForm.getTelephone());
        user.setDateOfBirth(userForm.getDateOfBirth());
        user.setHouseNumber(userForm.getHouseNumber());

        Address address = new Address();
        address.setCep(userForm.getCep());
        user.setAddress(address);

        saveUserWithCep(user);
    }

    @Override
    public void update(Long id, User user) {

    }

    @Override
    public void delete(Long id) {

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
