package entity;

public class Ticket {
	private Long id;
	private Customer customer;
	private Agency agency;
	private Flight flight;
	private Integer canceled;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
