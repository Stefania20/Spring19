package com.sena.spring19.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CancionDao implements ICancion {
    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)

    @Override
    public List<Cancion> findAll() {
        
        return em.createQuery("from Cancion").getResultList();
    }

    @Transactional
    @Override
    public void save(Cancion cancion) {
        if(cancion.getId() != null && cancion.getId() > 0){
            em.merge(cancion);
        }else{
            em.persist(cancion);
        }
        
    }
    @Override
    @Transactional(readOnly = true)
    public Cancion findOne(Integer id) {
        
        return em.find(Cancion.class, id);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        em.remove(findOne(id));
        
    }

    
}
