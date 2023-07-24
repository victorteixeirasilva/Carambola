package com.carambola.service.Implementation;

import com.carambola.model.Address;
import com.carambola.model.User;
import com.carambola.model.form.UserForm;
import com.carambola.model.form.UserUpdateForm;
import com.carambola.repository.AddressRepository;
import com.carambola.repository.UserRepository;
import com.carambola.service.UserService;
import com.carambola.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public Optional<User> SearchById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user;
        }
        System.out.println("------------ ERRO: Usuário não existe! -------------");
        return user;
    }

    @Override
    public Iterable<User> fetchAll() {
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
    public User update(Long id, UserUpdateForm userUpdateForm) {
        Optional<User> userBd = userRepository.findById(id);
        if (userBd.isPresent()){
            User user = new User();

            //Id
            user.setId(userBd.get().getId());

            //Name
            if((userBd.get().getName() == userUpdateForm.getName())||(userUpdateForm.getName()=="")){
                user.setName(userBd.get().getName());
            } else {
                user.setName(userUpdateForm.getName());
            }

            //Email
            if((userBd.get().getEmail() == userUpdateForm.getEmail())||(userUpdateForm.getEmail()=="")){
                user.setEmail(userBd.get().getEmail());
            } else {
                user.setEmail(userUpdateForm.getEmail());
            }

            //Password
            if((userBd.get().getPassword() == userUpdateForm.getPassword())||(userUpdateForm.getPassword()=="")){
                user.setPassword(userBd.get().getPassword());
            } else {
                user.setPassword(userUpdateForm.getPassword());
            }

            //Telephone
            if((userBd.get().getTelephone() == userUpdateForm.getTelephone())||(userUpdateForm.getTelephone()==0)){
                user.setTelephone(userBd.get().getTelephone());
            } else {
                user.setTelephone(userUpdateForm.getTelephone());
            }

            //DateOfBirth
            if((userBd.get().getDateOfBirth() == userUpdateForm.getDateOfBirth())||(userUpdateForm.getDateOfBirth()==null)){
                user.setDateOfBirth(userBd.get().getDateOfBirth());
            } else {
                user.setDateOfBirth(userUpdateForm.getDateOfBirth());
            }

            //Cpf
            user.setCpf(userBd.get().getCpf());

            //Cep
            if((userBd.get().getAddress().getCep() == userUpdateForm.getCep())||(userUpdateForm.getCep()=="")){
                user.setAddress(userBd.get().getAddress());
            } else {
                Address address = new Address();
                address.setCep(userUpdateForm.getCep());
                user.setAddress(address);
            }

            //HouseNumber
            if((userBd.get().getHouseNumber() == userUpdateForm.getHouseNumber())){
                user.setHouseNumber(userBd.get().getHouseNumber());
            } else {
                user.setHouseNumber(userUpdateForm.getHouseNumber());
            }

            saveUserWithCep(user);

        }
        System.out.println("------------ ERRO: Usuário não existe! -------------");
        return userBd.get();
    }

    @Override
    public String delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.delete(user.get());
            return "Usuário (ID:" + user.get().getId() + ", NAME:" + user.get().getName()
                    + ", EMAIL:" + user.get().getEmail() + ". Foi deletado com sucesso!";
        }
        return "Esse usuário não pode ser deletado, pois não existe!";
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
