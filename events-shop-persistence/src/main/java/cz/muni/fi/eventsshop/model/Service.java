package cz.muni.fi.eventsshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.Size;

@Entity
public class Service extends AbstractEntity {

    @NotNull
    @Column(nullable = false)
    @Size(min = 1, max = 32)
    private String name;

    @NotNull
    @Column(nullable = false)
    @Size(min = 1, max = 300)
    private String description;

    @NotNull
    @Column(nullable = false)
    @DecimalMin("0.00")
    private BigDecimal price;

    @NotNull
    @ManyToOne
    private Category category;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Service service = (Service) o;

        if (!getName().equals(service.getName())) {
            return false;
        }
        if (!getDescription().equals(service.getDescription())) {
            return false;
        }
        return getPrice().equals(service.getPrice());
    }

    @Override
    public int hashCode() {
        int hash = 21;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.price);
        hash = 97 * hash + Objects.hashCode(this.category);
        return hash;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
