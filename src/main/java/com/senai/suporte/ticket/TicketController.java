package com.senai.suporte.ticket;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketRepository repository;
    public TicketController(TicketRepository repository) { this.repository = repository; }
    @GetMapping public List<Ticket> list() { return repository.findAll(); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) @PreAuthorize("hasRole('ADMIN')")
    public Ticket create(@Valid @RequestBody Ticket ticket) { ticket.setStatus(TicketStatus.OPEN); return repository.save(ticket); }
    @PutMapping("/{id}/status") @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIAN')")
    public Ticket updateStatus(@PathVariable Long id, @RequestBody StatusRequest request) { Ticket ticket = repository.findById(id).orElseThrow(); ticket.setStatus(TicketStatus.valueOf(request.status())); return repository.save(ticket); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) { repository.deleteById(id); }
    public record StatusRequest(String status) { }
}