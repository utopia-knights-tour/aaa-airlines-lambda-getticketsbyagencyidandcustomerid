package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Airport;
import entity.Flight;

public class FlightDao {

	private Connection connection;

	public FlightDao(Connection connection) {
		this.connection = connection;
	}

	public Flight get(Long id) throws SQLException {
		PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Flight WHERE Flight.flightId = ?");
		pstmt.setLong(1, id);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			Flight flight = new Flight();
			flight.setId(rs.getLong("flightId"));
			AirportDao airportDao = new AirportDao(connection);
			Airport sourceAirport = airportDao.get(rs.getString("sourceAirport"));
			flight.setSourceAirport(sourceAirport);
			Airport destinationAirport = airportDao.get(rs.getString("destinationAirport"));
			flight.setDestinationAirport(destinationAirport);
			flight.setDepartureDate(rs.getDate("departureDate").toLocalDate());
			flight.setDepartureTime(rs.getTime("departureTime").toLocalTime());
			flight.setArrivalDate(rs.getDate("arrivalDate").toLocalDate());
			flight.setArrivalTime(rs.getTime("arrivalTime").toLocalTime());
			flight.setSeatsAvailable(rs.getInt("seatsAvailable"));
			flight.setCost(rs.getBigDecimal("cost"));
			return flight;
		}
		return null;
	}

	public int increaseSeatsAvailable(Long id) throws SQLException {
		PreparedStatement pstmt = connection.prepareStatement(
				"UPDATE Flight SET Flight.seatsAvailable = Flight.seatsAvailable + 1 WHERE Flight.flightId = ? AND Flight.seatsAvailable > 0");
		pstmt.setLong(1, id);
		return pstmt.executeUpdate();
	}
}
