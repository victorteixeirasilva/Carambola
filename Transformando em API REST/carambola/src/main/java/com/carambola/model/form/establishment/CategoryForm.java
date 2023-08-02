package com.carambola.model.form.establishment;

import com.carambola.model.Catalog;
import com.carambola.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CategoryForm {
    private Long idCatalog;
    private Long idParentCategory;
    private String name;
}
