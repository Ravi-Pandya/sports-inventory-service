package com.Pandya.inventory;

import com.Pandya.entity.Inventory;
import com.Pandya.entity.Store;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Remote(InventoryService.class)
public class InventoryServiceImpl implements InventoryService{

    @PersistenceContext
    private EntityManager em;

    public List<Inventory> getAllByBuilder(){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Inventory> query = builder.createQuery(Inventory.class);
        Root<Inventory> from = query.from(Inventory.class);
        TypedQuery<Inventory> q = em.createQuery(query.select(from));
        return q.getResultList();
    }

    @Override
    public List<Inventory> getInventoryList(){
        List<Inventory> inventoryList = em.createNamedQuery("Inventory.findAll", Inventory.class)
                .getResultList();

        return inventoryList.stream()
                .limit(inventoryList.size())
                .sorted()
                .collect(Collectors.toList());

    }

    @Override
    public void addToList(Inventory inventory){
        em.persist(inventory);
    }

    @Override
    public void removeFromList(Inventory inventory){
        Inventory correspondingInventory = em
                .createNamedQuery("Inventory.getByName",Inventory.class)
                .setParameter("name", inventory.getName())
                .getSingleResult();
        em.remove(correspondingInventory);
    }

    @Override
    public void addStore(String name, Store store){
        Inventory correspondingInventory = em
                .createNamedQuery("Inventory.getByName",Inventory.class)
                .setParameter("name",name)
                .getSingleResult();
        store.setInventory(correspondingInventory);
        em.persist(store);
    }

    @Override
    public List<Store> getAllStore(){
        return em.createNamedQuery("Store.findAll",Store.class)
                .getResultList();
    }
}

