package com.carambola.model.form.establishment;

import com.carambola.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ProductForm {
    private Long idCategory;
    private String name;
    private String description;
    private boolean haveStock;
    private Double value;
}
