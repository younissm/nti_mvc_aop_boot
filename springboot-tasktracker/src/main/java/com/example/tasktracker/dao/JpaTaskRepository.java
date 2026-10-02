package com.example.tasktracker.dao;

import com.example.tasktracker.exception.TaskNotFoundException;
import com.example.tasktracker.model.Task;
import com.example.tasktracker.util.DBUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("prod")
public class JpaTaskRepository implements TaskRepository{

    private final EntityManagerFactory emf = DBUtil.getEmFactory();

    private final Logger log = LoggerFactory.getLogger(JpaTaskRepository.class);

    public JpaTaskRepository() {
        log.debug("Prod profile is set, initializing JpaTaskRepository");
    }

    @Override
    public Task add(Task task) {
        try (EntityManager em =  emf.createEntityManager()) {
            try {
                em.getTransaction().begin();
                task.setId(null);
                em.persist(task);
                em.getTransaction().commit();
                return task;
            }  catch (Exception e) {
                log.warn("Insertion failed. Database rollback");
                em.getTransaction().rollback();
                throw e;
            }
        }
    }

    @Override
    public Task update(Task task) {
        try (EntityManager em =  emf.createEntityManager()) {
            try {
                em.getTransaction().begin();
                if (em.contains(task)) {
                    Task updatedTask = em.merge(task);
                    em.getTransaction().commit();
                    return updatedTask;
                } else {
                    throw new TaskNotFoundException("Task with id " + task.getId());
                }
            } catch (Exception e) {
                log.warn("Update failed. Database rollback");
                em.getTransaction().rollback();
                throw e;
            }
        }
    }

    @Override
    public Task setCompleted(long id, boolean isCompleted) {
        try (EntityManager em =  emf.createEntityManager()) {
            try {
                em.getTransaction().begin();
                Task task = em.find(Task.class, id);
                if (task != null) {
                    task.setCompleted(isCompleted);
                    em.getTransaction().commit();
                    return task;
                } else {
                    throw new TaskNotFoundException("Task with id " + id);
                }
            }  catch (Exception e) {
                log.warn("setCompleted failed. Database rollback");
                em.getTransaction().rollback();
                throw e;
            }
        }
    }

    @Override
    public Task deleteById(long id) {
        try (EntityManager em =  emf.createEntityManager()) {
            try {
                em.getTransaction().begin();
                Task task = em.find(Task.class, id);
                if (task != null) {
                    em.remove(task);
                    em.getTransaction().commit();
                    return task;
                } else {
                    throw new TaskNotFoundException("Task with id " + id);
                }
            } catch (Exception e) {
                log.warn("Deletion failed. Database rollback");
                em.getTransaction().rollback();
                throw e;
            }
        }
    }

    @Override
    public Optional<Task> findById(long id) {
        try (EntityManager em =  emf.createEntityManager()) {
            Task task = em.find(Task.class, id);
            return Optional.ofNullable(task);
        }
    }

    @Override
    public List<Task> findAll() {
        try (EntityManager em =  emf.createEntityManager()) {
            List<Task> tasks = em.createQuery("SELECT t from Task t").getResultList();
            return tasks;
        }
    }

    @Override
    public List<Task> findByParams(@Nullable Boolean completed, @Nullable Integer limit) {
        try (EntityManager em =  emf.createEntityManager()) {
            CriteriaQuery<Task> taskCriteriaQuery = em.getCriteriaBuilder().createQuery(Task.class);

            if (completed != null) {
                taskCriteriaQuery.where(
                        taskCriteriaQuery.from(Task.class).get("completed").in(completed));
            }

            TypedQuery<Task> taskQuery = em.createQuery(taskCriteriaQuery);


            if (limit != null) {
                taskQuery.setMaxResults(limit);
            }

            return taskQuery.getResultList();
        }
    }

    @Override
    public long getTaskCount() {
        try (EntityManager em =  emf.createEntityManager()) {
            CriteriaQuery<Long> countCriteriaQuery = em.getCriteriaBuilder().createQuery(Long.class);
            Root<Task> root = countCriteriaQuery.from(Task.class);
            countCriteriaQuery.select(em.getCriteriaBuilder().count(root));
            TypedQuery<Long> query = em.createQuery(countCriteriaQuery);
            return query.getSingleResult();
        }
    }
}
