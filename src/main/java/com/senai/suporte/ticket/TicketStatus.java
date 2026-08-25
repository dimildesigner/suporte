package com.senai.suporte.ticket;

public enum TicketStatus {
    OPEN("Aberto"), IN_PROGRESS("Em atendimento"), RESOLVED("Resolvido");
    private final String label;
    TicketStatus(String label) { this.label = label; }
    public String getLabel() { return label; }
}