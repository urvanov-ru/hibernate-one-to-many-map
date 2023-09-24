/**
 * 
 */
package ru.urvanov.javaexamples.hibernateonetomanymap;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKey;
import jakarta.persistence.MapKeyJoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;


/**
 * @author fedya
 * 
 */
@Entity
@Table(name = "pet")
public class Pet implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2699175148933987413L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pet_seq")
    @SequenceGenerator(name="pet_seq",
        sequenceName="pet_id_seq", allocationSize=1)
    private Integer id;

    @Column
    @NaturalId
    private String name;

    @Version
    @Column(name = "version")
    private Integer version;

    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name="food")
    private Map<Food, PetFood> foods = new HashMap<>();

 
    /**
     * 
     */
    public Pet() {
        // TODO Auto-generated constructor stub
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Integer getVersion() {
        return version;
    }


    public Map<Food, PetFood> getFoods() {
        return foods;
    }

    public void setFoods(Map<Food, PetFood> foods) {
        this.foods = foods;
    }

}
