package org.example.service.DAO;

import org.example.entity.Client;
import org.example.entity.Ticket;
import org.example.hibernate.HibernateUtils;
import org.example.service.CRUDServices.ClientCrudService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TicketDAOServiceImpl implements TicketDaoService {
    @Override
    public Ticket createTicket(Ticket ticket) {

        Transaction transaction = null;
        try(Session session = HibernateUtils.getInstance()
                .getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.persist(ticket);
            transaction.commit();
            return ticket;
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Помилка при створенні квитка!", e);
        }
    }

    @Override
    public boolean deleteTicket(Ticket deletedTicket) {

        Transaction transaction = null;
        try(Session session = HibernateUtils.getInstance()
                .getSessionFactory().openSession()){
            if(deletedTicket == null) {
                return false;
            }

            transaction = session.beginTransaction();
            session.remove(deletedTicket);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Помилка при видаленні квитка!", e);
        }
    }

    @Override
    public List<Ticket> getAllTickets() {

        try(Session session = HibernateUtils.getInstance()
                .getSessionFactory().openSession()) {
            List<Ticket> allTickets = session.createQuery("from Ticket", Ticket.class).list();

            return allTickets;
        } catch (Exception e) {
            throw new RuntimeException("Помилка при отриманні списку клієнтів!", e);
        }
    }

    @Override
    public Ticket getTicket(Long ticketId) {

        try(Session session = HibernateUtils.getInstance()
                .getSessionFactory().openSession()) {

            Ticket ticket = session.find(Ticket.class, ticketId);
            return ticket;
        } catch (Exception e) {
            throw new RuntimeException("Не вдалося знайти квиток!", e);
        }
    }

    @Override
    public boolean updateTicket(Ticket updatedTicket, String toPlanetId) {

        Transaction transaction = null;
        try(Session session = HibernateUtils.getInstance()
                .getSessionFactory().openSession()) {

            if (updatedTicket == null) {
                return false;
            }
            updatedTicket.setToPlanetId(toPlanetId);
            transaction = session.beginTransaction();
            session.merge(updatedTicket);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Помилка при оновленні квитка!", e);
        }
    }
}
