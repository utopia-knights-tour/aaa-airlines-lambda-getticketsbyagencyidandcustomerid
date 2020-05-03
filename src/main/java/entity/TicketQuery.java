package entity;

import java.util.List;

public class TicketQuery {
	private List<Ticket> tickets;
	private Long ticketsCount;

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Long getTicketsCount() {
		return ticketsCount;
	}

	public void setTicketsCount(Long ticketsCount) {
		this.ticketsCount = ticketsCount;
	}
}
