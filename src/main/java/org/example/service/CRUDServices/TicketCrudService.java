package org.example.service.CRUDServices;

import org.example.entity.Ticket;

import java.util.List;

public interface TicketCrudService {
    Ticket createTicket(Long clientId, String fromPlanetId, String toPlanetId);

    Ticket getTicket(Long ticketId);

    List<Ticket> getAllTickets();

    Ticket updateTicket(Long ticketId, String toPlanetId);

    boolean deleteTicket(Long ticketId);
}
