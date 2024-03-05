package org.example;

import jakarta.persistence.Query;
import org.example.model.Item;
import org.example.model.Loan;
import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LoanService {
    public String loanItemToUser(Long itemId, Long userId) {

        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            // Obtener el ítem y el usuario
            Item item = session.get(Item.class, itemId);
            User user = session.get(User.class, userId);

            if (item == null || user == null) {
                tx.rollback();
                return "Item o usuario no encontrado";
            }

            // Crear el nuevo préstamo
            Loan loan = new Loan();
            loan.setItem(item);
            loan.setUser(user);
            loan.setCheckout_date(new Date());

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, 15);
            loan.setDue_date(cal.getTime());

            // Persistir el préstamo
            session.persist(loan);

            tx.commit();

            return "Item prestado correctamente";

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return "Fallo al prestar item";
        }

    }


    public String returnItem(long itemId) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {

            tx = session.beginTransaction();

            // Obtener el ítem
            Item item = session.get(Item.class, itemId);

            if(item == null) {
                tx.rollback();
                return "No se encontró el ítem con id " + itemId;
            }

            // Obtener el préstamo asociado
            List<Loan> loans = item.getLoans();

            if(loans == null || loans.isEmpty()) {
                tx.rollback();
                return "No hay préstamo asociado al ítem "+itemId;
            }

            // Tomar el primer préstamo
            Loan loan = loans.get(0);

            // Verificar préstamo exista
            if(loan == null) {
                tx.rollback();
                return "Préstamo no encontrado";
            }

            // Fijar fecha de devolución
            loan.setReturned_date(new Date());

            session.saveOrUpdate(loan);

            tx.commit();

            return "Item devuelto correctamente";

        } catch (Exception e) {

            if(tx != null) {
                tx.rollback();
            }

            e.printStackTrace();

            return "Fallo al devolver el item " + itemId;
        }

    }




    public List<Item> getItemsLoanedToUser(Long userId) {

        List<Item> items = new ArrayList<>();

        if (!userHasLoans(userId)) {
            return items;
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Query query = session.createQuery("SELECT l FROM Loan l WHERE l.user.id = :userId");
            query.setParameter("userId", userId);

            List<Loan> loans = (List<Loan>) ((org.hibernate.query.Query<?>) query).list();

            for(Loan loan : loans) {
                Item item = new Item();
                item.setName(loan.getItem().getName());
                item.setDescription(loan.getItem().getDescription());
                items.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return items;
    }

    private boolean userHasLoans(Long userId) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("SELECT COUNT(l) FROM Loan l WHERE l.user.id = :userId");
        query.setParameter("userId", userId);

        Long count = (Long) ((org.hibernate.query.Query<?>) query).uniqueResult();

        return count > 0;

    }


    public List<Item> getAllItemsOnLoan() {


        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Consulta para obtener todos los préstamos
            List<Item> itemsOnLoans = session.createQuery("SELECT l.item FROM Loan l WHERE l.returned_date IS NULL", Item.class).getResultList();

            return itemsOnLoans;
        }
    }


    public List<Loan> getAllLoansFromItem(Long itemId) {

        List<Loan> loans = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            loans = session.createQuery("FROM Loan l WHERE l.item.id = :itemId", Loan.class)
                    .setParameter("itemId", itemId)
                    .getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return loans;

    }
}
