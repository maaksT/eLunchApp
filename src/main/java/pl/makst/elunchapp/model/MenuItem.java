package pl.makst.elunchapp.model;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import pl.makst.elunchapp.model.enums.VatTax;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
@GeneratePojoBuilder
@Entity
@Table(name = "menuitems")
public class MenuItem {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotNull
    private UUID uuid;

    @NotBlank
    private String name;
    @Column(scale = 2,precision = 12)
    @Digits(integer = 10,fraction = 2)
    @Min(0)
    @NotNull
    private BigDecimal nettoPrice;
    @Column(scale = 2,precision = 12)
    @Digits(integer = 10,fraction = 2)
    @Min(0)
    @NotNull
    private BigDecimal bruttoPrice;
    @NotNull
    @Enumerated(EnumType.STRING)
    private VatTax vatTax;
    @NotNull
    @Size(min = 1)
    @ManyToMany
    private List<Dish> dishes;
    @NotNull
    @ManyToOne
    private Restaurant restaurant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getNettoPrice() {
        return nettoPrice;
    }

    public void setNettoPrice(BigDecimal nettoPrice) {
        this.nettoPrice = nettoPrice;
    }

    public BigDecimal getBruttoPrice() {
        return bruttoPrice;
    }

    public void setBruttoPrice(BigDecimal bruttoPrice) {
        this.bruttoPrice = bruttoPrice;
    }

    public VatTax getVatTax() {
        return vatTax;
    }

    public void setVatTax(VatTax vatTax) {
        this.vatTax = vatTax;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
