package ru.urvanov.javaexamples.hibernateonetomanymap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import jakarta.persistence.EntityManager;

/**
 * Hibernate one-to-many java.util.Map
 *
 */
public class App {
    public static void main(String[] args) {

        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure().build();

        MetadataSources sources = new MetadataSources(standardRegistry)
                .addAnnotatedClass(Pet.class).addAnnotatedClass(Food.class)
                .addAnnotatedClass(PetFood.class);

        Metadata metadata = sources.buildMetadata();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
                .build();

        try (EntityManager em = sessionFactory.createEntityManager();
                Session session = em.unwrap(Session.class)) {

            method1(session);
            method2(session);
        }
    }

    public static void method1(Session session) {
        Transaction transaction = session.beginTransaction();
        Pet pet = new Pet();
        pet.setName("Mask");
        session.persist(pet);

        Food apple = new Food();
        apple.setCode(FoodType.APPLE);
        session.persist(apple);

        Food carrot = new Food();
        carrot.setCode(FoodType.CARROT);
        session.persist(carrot);

        pet = session.bySimpleNaturalId(Pet.class).load("Mask");

        PetFood petFood = new PetFood();
        petFood.setFood(apple);
        petFood.setPet(pet);
        petFood.setFoodCount(10);
        pet.getFoods().put(apple, petFood);

        session.flush();
        transaction.commit();

    }

    public static void method2(Session session) {
        Transaction transaction = session.beginTransaction();
        Pet pet = session.bySimpleNaturalId(Pet.class).load("Mask");
        Food apple = session.bySimpleNaturalId(Food.class).load(FoodType.APPLE);
        Food carrot = session.bySimpleNaturalId(Food.class)
                .load(FoodType.CARROT);

        PetFood petFood = new PetFood();
        petFood.setFood(carrot);
        petFood.setPet(pet);
        petFood.setFoodCount(999);
        pet.getFoods().put(carrot, petFood);

        session.flush();
        transaction.commit();
    }
}
