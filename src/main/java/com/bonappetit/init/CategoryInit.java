package com.bonappetit.init;

import com.bonappetit.model.entity.Category;
import com.bonappetit.model.enums.Description;
import com.bonappetit.repo.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryInit implements CommandLineRunner {

private final CategoryRepository categoryRepository;

    public CategoryInit(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void run(String... args) throws Exception {

    if (categoryRepository.count() == 0){

        List<Category> categoryList =new ArrayList<>();

        for (Description description : Description.values()) {
            Category category = new Category(description);
            categoryList.add(category);
        }

        categoryRepository.saveAll(categoryList);

    }

    }
}
