package cz.muni.fi.eventsshop.service.DTO;

import cz.muni.fi.eventsshop.model.Category;

/**
 * Created by pato on 1.6.2017.
 */
public class CategoryDTO {

    public CategoryDTO() {}
    public CategoryDTO(Category c) {
        this.id = c.getId();
        this.description = c.getDescription();
        this.name = c.getName();
    }

    private long id;
    private String name;
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
