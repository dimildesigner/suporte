package com.senai.suporte.ticket;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Ticket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @NotBlank @Size(max = 120) private String title;
    @NotBlank @Size(max = 2000) private String description;
    @NotBlank @Size(max = 80) private String requester;
    @NotBlank @Size(max = 40) private String priority;
    @Enumerated(EnumType.STRING) private TicketStatus status = TicketStatus.OPEN;
    private LocalDateTime createdAt;
    @PrePersist void onCreate() { createdAt = LocalDateTime.now(); }
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String value) { title = value; }
    public String getDescription() { return description; }
    public void setDescription(String value) { description = value; }
    public String getRequester() { return requester; }
    public void setRequester(String value) { requester = value; }
    public String getPriority() { return priority; }
    public void setPriority(String value) { priority = value; }
    public TicketStatus getStatus() { return status; }
    public void setStatus(TicketStatus value) { status = value; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}