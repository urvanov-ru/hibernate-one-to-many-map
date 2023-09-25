package ru.urvanov.javaexamples.hibernateonetomanymap;

import java.io.Serializable;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "food")
public class Food implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 8791181701061581183L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="food_seq")
    @SequenceGenerator(name="food_seq",
        sequenceName="food_id_seq", allocationSize=1)
    private Integer id;
    
    @Enumerated(value = EnumType.STRING)
    @Column(name = "code")
    @NaturalId
    private FoodType code;
    
    @Version
    @Column(name = "version")
    private Integer version;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the code
     */
    public FoodType getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(FoodType code) {
        this.code = code;
    }

    /**
     * @return the version
     */
    public Integer getVersion() {
        return version;
    }

    @Override
    public int hashCode() {
        return code == null ? -1 : code.hashCode();
    }
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof Food) {
            Food foodOther = (Food)other;
            if (foodOther.getCode() != null && code != null && code == foodOther.getCode()) 
                return true;
        }
        return false;
    }
}
