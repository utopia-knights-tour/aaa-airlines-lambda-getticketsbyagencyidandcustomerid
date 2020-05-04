package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Agency;

public class AgencyDao {

	private Connection connection;

	public AgencyDao(Connection connection) {
		this.connection = connection;
	}

	public Agency get(Long id) throws SQLException {
		PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Agency WHERE Agency.agencyId = ?");
		pstmt.setLong(1, id);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			Agency agency = new Agency();
			agency.setAgencyId(rs.getLong("agencyId"));
			agency.setAgencyName(rs.getString("agencyName"));
			agency.setAgencyAddress(rs.getString("agencyAddress"));
			agency.setAgencyPhone(rs.getString("agencyPhone"));
			return agency;
		}
		return null;
	}
}
