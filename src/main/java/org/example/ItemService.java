package org.example;

import jakarta.persistence.Query;
import org.example.model.Box;
import org.example.model.Category;
import org.example.model.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;


    public class ItemService {

        public String reassignItemToBox(Long itemId, Long boxId) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();

                // Recuperar el objeto Item de la base de datos usando su ID
                Item item = session.get(Item.class, itemId);

                if (item == null) {
                    transaction.rollback(); // Deshacer la transacción si el ítem no se encuentra
                    return "Ítem no encontrado";
                }

                // Recuperar la caja de la base de datos usando su ID
                Box box = session.get(Box.class, boxId);
                if (box == null) {
                    transaction.rollback(); // Deshacer la transacción si la caja no se encuentra
                    return "Caja no encontrada";
                }

                // Modificar la caja del objeto Item
                item.setBox(box);

                // Guardar los cambios en la base de datos
                session.update(item);
                transaction.commit();

                return "Item reubicado correctamente";
            } catch (Exception e) {
                e.printStackTrace();
                return "Fallo al reubicar el ítem";
            }
        }


        public List<Item> listAllItems() {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                // Consulta para obtener todos los ítems
                return session.createQuery("FROM Item", Item.class).getResultList();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public void update(int id, String name, String description) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;

            try {
                tx = session.beginTransaction();

                Item item = session.get(Item.class, id);

                if (name != null && !name.isEmpty()) {
                    item.setName(name);
                }

                if (description != null && !description.isEmpty()) {
                    item.setDescription(description);
                }

                session.update(item);

                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw e;
            } finally {
                session.close();
            }
        }

        public void delete(int id) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;

            try {
                tx = session.beginTransaction();

                Item item = session.get(Item.class, id);

                if (item != null) {
                    session.delete(item);
                }

                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw e;
            } finally {
                session.close();
            }
        }

        public void setCategories(int itemId, String[] categoryNames) {

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;

            try {

                tx = session.beginTransaction();

                Item item = session.get(Item.class, itemId);

                if(item != null) {

                    item.getCategories().clear();

                    for(String name : categoryNames) {

                        // Buscar categoria existente
                        Category category = session.get(Category.class, name);

                        if(category == null) {
                            // Crear y persistir nueva
                            category = new Category(name);
                            session.save(category);
                        }

                        item.getCategories().add(category);

                    }

                    session.saveOrUpdate(item);

                }

                tx.commit();

            } catch (Exception e) {

                if(tx != null) {
                    tx.rollback();
                }

                throw e;

            } finally {

                session.close();

            }

        }


        public void create(String name, String description) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;

            try {
                tx = session.beginTransaction();

                Item item = new Item(name, description);
                session.save(item);

                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw e;
            } finally {
                session.close();
            }
        }

        public Item get(int id) {
            Session session = HibernateUtil.getSessionFactory().openSession();

            try {
                return session.get(Item.class, id);
            } catch (Exception e) {
                throw e;
            } finally {
                session.close();
            }
        }

        public List<Item> search(String name) {

            Session session = HibernateUtil.getSessionFactory().openSession();

            try {
                Query query = session.createQuery("FROM Item i WHERE i.name LIKE :name");
                query.setParameter("name", "%" + name + "%");

                return query.getResultList();

            } catch (Exception e) {
                throw e;

            } finally {
                session.close();
            }

        }
    }
