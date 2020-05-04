package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Airport;

public class AirportDao {

	private Connection connection;

	public AirportDao(Connection connection) {
		this.connection = connection;
	}

	public Airport get(String id) throws SQLException {
		PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Airport WHERE Airport.airportCode = ?");
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			Airport airport = new Airport();
			airport.setAirportCode(rs.getString("airportCode"));
			airport.setAirportName(rs.getString("airportName"));
			airport.setAirportLocation(rs.getString("airportLocation"));
			return airport;
		}
		return null;
	}
}
