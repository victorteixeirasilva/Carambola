package com.carambola.service;

import com.carambola.model.User;
import com.carambola.model.form.establishment.EstablishmentForm;
import com.carambola.model.form.establishment.EstablishmentUpdateForm;
import org.springframework.stereotype.Component;

@Component
public interface EstablishmentService {
    public User update(Long id, EstablishmentUpdateForm establishmentUpdateForm);

    public void insert(EstablishmentForm establishmentForm);
}
