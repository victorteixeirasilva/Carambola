package com.carambola.service.Implementation;

import com.carambola.model.Address;
import com.carambola.model.Role;
import com.carambola.model.User;
import com.carambola.model.form.establishment.EstablishmentForm;
import com.carambola.model.form.establishment.EstablishmentUpdateForm;
import com.carambola.repository.AddressRepository;
import com.carambola.repository.UserRepository;
import com.carambola.service.EstablishmentService;
import com.carambola.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstablishmentServiceImplementation implements EstablishmentService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ViaCepService viaCepService;


    @Override
    public User update(Long id, EstablishmentUpdateForm establishmentUpdateForm) {
        Optional<User> userBd = userRepository.findById(id);
        if (userBd.isPresent()){
            User user = new User();

            //Id
            user.setId(userBd.get().getId());

            //Name
            if((userBd.get().getName() == establishmentUpdateForm.getName())||(establishmentUpdateForm.getName()=="")){
                user.setName(userBd.get().getName());
            } else {
                user.setName(establishmentUpdateForm.getName());
            }

            //Email
            if((userBd.get().getEmail() == establishmentUpdateForm.getEmail())||(establishmentUpdateForm.getEmail()=="")){
                user.setEmail(userBd.get().getEmail());
            } else {
                user.setEmail(establishmentUpdateForm.getEmail());
            }

            //Password
            if((userBd.get().getPassword() == establishmentUpdateForm.getPassword())||(establishmentUpdateForm.getPassword()=="")){
                user.setPassword(userBd.get().getPassword());
            } else {
                user.setPassword(establishmentUpdateForm.getPassword());
            }

            //Telephone
            if((userBd.get().getTelephone() == establishmentUpdateForm.getTelephone())||(establishmentUpdateForm.getTelephone()==0)){
                user.setTelephone(userBd.get().getTelephone());
            } else {
                user.setTelephone(establishmentUpdateForm.getTelephone());
            }

            //Cpf
            user.setCnpj(userBd.get().getCnpj());

            //Cep
            if((userBd.get().getAddress().getCep() == establishmentUpdateForm.getCep())||(establishmentUpdateForm.getCep()=="")){
                user.setAddress(userBd.get().getAddress());
            } else {
                Address address = new Address();
                address.setCep(establishmentUpdateForm.getCep());
                user.setAddress(address);
            }

            //HouseNumber
            if((userBd.get().getHouseNumber() == establishmentUpdateForm.getHouseNumber())){
                user.setHouseNumber(userBd.get().getHouseNumber());
            } else {
                user.setHouseNumber(establishmentUpdateForm.getHouseNumber());
            }

            saveUserWithCep(user);

        }
        return userBd.get();
    }

    @Override
    public void insert(EstablishmentForm establishmentForm) {

        User user = new User();

        user.setName(establishmentForm.getName());
        user.setCnpj(establishmentForm.getCnpj());
        user.setEmail(establishmentForm.getEmail());
        user.setPassword(establishmentForm.getPassword());
        user.setTelephone(establishmentForm.getTelephone());
        user.setHouseNumber(establishmentForm.getHouseNumber());

        Address address = new Address();
        address.setCep(establishmentForm.getCep());
        user.setAddress(address);

        Role role = new Role();
        role.setId(1);

        user.setRole(role);


        saveUserWithCep(user);
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
