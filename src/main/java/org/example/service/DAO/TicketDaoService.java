package org.example.service.DAO;

import org.example.entity.Client;
import org.example.entity.Ticket;

import java.util.List;

public interface TicketDaoService {
    Ticket createTicket(Ticket ticket);

    Ticket getTicket(Long ticketId);

    List<Ticket> getAllTickets();

    Ticket updateTicket(Ticket updatedTicket, String toPlanetId);

    boolean deleteTicket(Ticket deletedTicket);
}
