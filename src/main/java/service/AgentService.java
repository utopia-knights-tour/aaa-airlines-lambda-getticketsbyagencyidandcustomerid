package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.TicketDao;
import datasource.HikariCPDataSource;
import entity.Ticket;
import entity.TicketQuery;

public class AgentService {

	public TicketQuery getTicketsByAgencyIdAndCustomerId(Long agencyId, Long customerId, Long page, Long pageSize)
			throws ClassNotFoundException, SQLException {
		Connection connection = null;
		try {
			connection = HikariCPDataSource.getConnection();
			TicketDao ticketDao = new TicketDao(connection);
			TicketQuery ticketQuery = new TicketQuery();
			List<Ticket> tickets = ticketDao.get(agencyId, customerId, page, pageSize);
			ticketQuery.setTickets(tickets);
			Long ticketsCount = ticketDao.getCount(agencyId, customerId);
			ticketQuery.setTicketsCount(ticketsCount);
			return ticketQuery;
		} catch (SQLException e) {
			throw e;
		} finally {
			connection.close();
		}
	}
}
