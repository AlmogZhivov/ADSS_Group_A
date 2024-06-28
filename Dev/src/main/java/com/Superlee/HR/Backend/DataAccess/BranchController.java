package com.Superlee.HR.Backend.DataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BranchController extends Controller<BranchDTO> {
    @Override
    public boolean insert(DTO dto) {
        assert dto instanceof BranchDTO;
        try {
            connect();
            String insertSQL = "INSERT INTO Branch(name, address, manager) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
            pstmt.setString(1, ((BranchDTO) dto).getName());
            pstmt.setString(2, ((BranchDTO) dto).getAddress());
            pstmt.setString(3, ((BranchDTO) dto).getManager());
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new DataAccessException(e);
        } finally {
            closeConnection();
        }
        return true;
    }

    @Override
    public boolean update(DTO dto) {
        assert dto instanceof BranchDTO;
        try {
            connect();
            String updateSQL = "UPDATE Branch SET address = ?, manager = ? WHERE name = ?";
            PreparedStatement pstmt = conn.prepareStatement(updateSQL);
            pstmt.setString(1, ((BranchDTO) dto).getAddress());
            pstmt.setString(2, ((BranchDTO) dto).getManager());
            pstmt.setString(3, ((BranchDTO) dto).getName());
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new DataAccessException(e);
        } finally {
            closeConnection();
        }
        return true;
    }

    @Override
    public boolean delete(DTO dto) {
        throw new UnsupportedOperationException("Deleting branches is not supported.");
    }

    @Override
    public List<BranchDTO> loadAll() {
        try {
            connect();
            String selectSQL = "SELECT * FROM Branch";
            PreparedStatement pstmt = conn.prepareStatement(selectSQL);
            ResultSet rs = pstmt.executeQuery();
            List<BranchDTO> branches = new ArrayList<>();
            while (rs.next())
                branches.add(new BranchDTO(rs.getString("name"), rs.getString("address"), rs.getString("manager")));
            return branches;
        } catch (Exception e) {
            throw new DataAccessException(e);
        } finally {
            closeConnection();
        }
    }
}
