package entity;

public class Ticket {
	private Long ticketId;
	private Customer customer;
	private Agency agency;
	private Flight flight;
	private Integer canceled;

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Integer getCanceled() {
		return canceled;
	}

	public void setCanceled(Integer canceled) {
		this.canceled = canceled;
	}
}
