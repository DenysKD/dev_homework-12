package org.example.service.CRUDServices;

import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.example.exceptions.InputClientException;
import org.example.exceptions.InputPlanetException;
import org.example.exceptions.InputTicketException;
import org.example.exceptions.OutputTicketException;
import org.example.service.DAO.TicketDAOServiceImpl;

import java.util.List;

public class TicketCrudServiceImpl implements TicketCrudService {
    TicketDAOServiceImpl ticketDAOService;

    public TicketCrudServiceImpl(){
        ticketDAOService = new TicketDAOServiceImpl();
    }
    public Ticket createTicket(Long clientId, String fromPlanetId, String toPlanetId) {
        if(clientId == null || clientId < 1) {
            throw new InputClientException("Поле ID не може бути порожнім або меншим одиниці!");
        }
        if(fromPlanetId == null || fromPlanetId.isBlank()) {
            throw new InputPlanetException("Поле стартової планети не може бути порожнім!");
        }
        if(toPlanetId == null || toPlanetId.isBlank()) {
            throw new InputPlanetException("Поле кінцевої планети не може бути пустим!");
        }
        if(fromPlanetId.equals(toPlanetId)){
            throw new InputPlanetException("Стартова та кінцева планети не можуть бути однаковими!");
        }

        ClientCrudService clientCrudService = new ClientCrudService();
        Client client = clientCrudService.getClient(clientId);
        if(client == null){
            throw new InputClientException("Такого клієнта не існує!");
        }

        PlanetCrudService planetCrudService = new PlanetCrudService();
        Planet startPlanet = planetCrudService.getPlanet(fromPlanetId);
        Planet endPlanet = planetCrudService.getPlanet(toPlanetId);
        if(startPlanet == null){
            throw new InputPlanetException("Такої(стартової) планети не існує!");
        }
        if(endPlanet == null){
            throw new InputPlanetException("Такої(кінцевої) планети не існує!");
        }

        Ticket ticket = new Ticket();
        ticket.setClient(client);
        ticket.setFromPlanetId(fromPlanetId);
        ticket.setToPlanetId(toPlanetId);
        return ticketDAOService.createTicket(ticket);
    }

    public Ticket getTicket(Long ticketId) {
        if(ticketId == null || ticketId < 1L){
            throw new InputTicketException("Поле ID не може бути порожнім або меншим одиниці!");
        }
        Ticket ticket = ticketDAOService.getTicket(ticketId);
        if (ticket == null) {
            throw new OutputTicketException("Квитка за таким ID не існує!");
        }
        return ticket;
    }

    public List<Ticket> getAllTickets() {
        return ticketDAOService.getAllTickets();
    }

    public Ticket updateTicket(Long ticketId, String toPlanetId) {
        if (ticketId == null || ticketId < 1) {
            throw new InputTicketException("Поле ID не може бути порожнім або меншим одиниці!");
        }
        PlanetCrudService planetCrudService = new PlanetCrudService();
        Planet checkedPlanet = planetCrudService.getPlanet(toPlanetId);
        //чи допустимо по правилах писати так скорочено * Planet checkedPlanet = new PlanetCrudService().getPlanet(toPlanetId); * ?
        if (toPlanetId == null || toPlanetId.isBlank()) {
            throw new InputPlanetException("Поле кінцевої планети не може бути пустим!");
        }
        Ticket updatedTicket = ticketDAOService.getTicket(ticketId);
        if (updatedTicket == null) {
            throw new InputTicketException("Квитка за таким ID не існує!");
        }
        if (updatedTicket.getToPlanetId().equals(toPlanetId)) {
            throw new InputTicketException("Новий пункт призначення не може бути таким самим як і існуючий!");
        }
        if(updatedTicket.getFromPlanetId().equals(toPlanetId)) {
            throw new InputTicketException("Стартова та кінцева планети не можуть бути однаковими!");
        }
        return ticketDAOService.updateTicket(updatedTicket, toPlanetId);
    }

    public boolean deleteTicket(Long ticketId) {
        if (ticketId == null || ticketId < 1) {
            throw new InputTicketException("Поле ID не може бути порожнім або меншим одиниці!");
        }
        Ticket deletedTicket = ticketDAOService.getTicket(ticketId);
        if (deletedTicket == null) {
            throw new OutputTicketException("Квитка за таким ID не існує!");
        }
        return ticketDAOService.deleteTicket(deletedTicket);
    }
}
