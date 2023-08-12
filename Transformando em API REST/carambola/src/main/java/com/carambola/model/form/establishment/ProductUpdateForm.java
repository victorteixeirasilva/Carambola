package com.carambola.model.form.establishment;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ProductUpdateForm {
        private Long idCategory;
        private String name;
        private String description;
        private boolean haveStock;
        private Double value;
}
