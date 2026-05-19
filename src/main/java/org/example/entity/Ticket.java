package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

@Entity
@Table(name = "Ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", insertable = false, updatable = false)
    @Generated(event = EventType.INSERT)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "from_planet_id")
    private String fromPlanetId;

    @Column(name = "to_planet_id")
    private String toPlanetId;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getFromPlanetId() {
        return fromPlanetId;
    }

    public void setFromPlanetId(String fromPlanetId) {
        this.fromPlanetId = fromPlanetId;
    }

    public String getToPlanetId() {
        return toPlanetId;
    }

    public void setToPlanetId(String toPlanetId) {
        this.toPlanetId = toPlanetId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "client=" + client +
                ", id=" + id +
                ", createdAt=" + createdAt +
                ", fromPlanetId='" + fromPlanetId + '\'' +
                ", toPlanetId='" + toPlanetId + '\'' +
                '}';
    }
}
