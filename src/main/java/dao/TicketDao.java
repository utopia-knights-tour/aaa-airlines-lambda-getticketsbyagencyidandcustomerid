package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Agency;
import entity.Customer;
import entity.Flight;
import entity.Ticket;

public class TicketDao {

	private Connection connection;

	public TicketDao(Connection connection) {
		this.connection = connection;
	}

	public List<Ticket> get(Long agencyId, Long customerId, Long page, Long pageSize) throws SQLException {
		List<Ticket> tickets = new ArrayList<Ticket>();
		String sql = "SELECT * FROM Ticket WHERE Ticket.agencyId = ? AND Ticket.customerId = ? AND Ticket.canceled = 0";
		if (page != null && pageSize != null) {
			sql += " LIMIT ?,?";
		}
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setLong(1, agencyId);
		pstmt.setLong(2, customerId);
		if (page != null && pageSize != null) {
			pstmt.setLong(3, (page - 1) * 10);
			pstmt.setLong(4, pageSize);
		}
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Ticket ticket = new Ticket();
			ticket.setId(rs.getLong("ticketId"));
			CustomerDao customerDao = new CustomerDao(connection);
			Customer customer = customerDao.get(rs.getLong("customerId"));
			ticket.setCustomer(customer);
			AgencyDao agencyDao = new AgencyDao(connection);
			Agency agency = agencyDao.get(rs.getLong("agencyId"));
			ticket.setAgency(agency);
			FlightDao flightDao = new FlightDao(connection);
			Flight flight = flightDao.get(rs.getLong("flightId"));
			ticket.setFlight(flight);
			tickets.add(ticket);
		}
		return tickets;
	}

	public Long getCount(Long agencyId, Long customerId) throws SQLException {
		PreparedStatement pstmt = connection.prepareStatement(
				"SELECT COUNT(*) AS count FROM  Ticket WHERE Ticket.agencyId = ? AND Ticket.customerId = ?");
		pstmt.setLong(1, agencyId);
		pstmt.setLong(2, customerId);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getLong("count");
		}
		return null;
	}
}
