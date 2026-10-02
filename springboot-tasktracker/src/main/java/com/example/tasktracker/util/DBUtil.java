package com.example.tasktracker.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBUtil {
    private static final EntityManagerFactory emFactory = buildEmFactory();

    private static EntityManagerFactory buildEmFactory() {
        try {
            return Persistence.createEntityManagerFactory("tasks");
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEmFactory() {
        return emFactory;
    }
}
