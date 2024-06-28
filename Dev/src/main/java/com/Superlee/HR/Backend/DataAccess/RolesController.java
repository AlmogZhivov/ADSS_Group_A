package com.Superlee.HR.Backend.DataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;

public class RolesController extends Controller<RolesDTO> {
    @Override
    public boolean insert(DTO dto) {
        assert dto instanceof RolesDTO;
        try {
            connect();
            String insertSQL = "INSERT INTO Roles(value, name) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
            pstmt.setInt(1, ((RolesDTO) dto).getValue());
            pstmt.setString(2, ((RolesDTO) dto).getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            closeConnection();
        }
        return true;
    }

    @Override
    public boolean update(DTO dto) {
        throw new UnsupportedOperationException("Updating roles is not supported.");
    }

    @Override
    public boolean delete(DTO dto) {
        throw new UnsupportedOperationException("Deleting roles is not supported.");
    }

    @Override
    public List<RolesDTO> loadAll() {
        try {
            connect();
            String selectSQL = "SELECT * FROM Roles";
            PreparedStatement pstmt = conn.prepareStatement(selectSQL);
            ResultSet rs = pstmt.executeQuery();
            List<RolesDTO> roles = new ArrayList<>();
            while (rs.next())
                roles.add(new RolesDTO(rs.getInt("value"), rs.getString("name")));
            return roles;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            closeConnection();
        }
    }
}
