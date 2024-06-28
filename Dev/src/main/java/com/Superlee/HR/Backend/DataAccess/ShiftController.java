package com.Superlee.HR.Backend.DataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShiftController extends Controller<ShiftDTO> {
    @Override
    public boolean insert(DTO dto) {
        try {
            connect();
            String insertSQL =
                    "INSERT INTO Shifts(id, startTime, endTime, branch) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
            pstmt.setInt(1, ((ShiftDTO) dto).getId());
            pstmt.setObject(2, ((ShiftDTO) dto).getStartTime());
            pstmt.setObject(3, ((ShiftDTO) dto).getEndTime());
            pstmt.setString(4, ((ShiftDTO) dto).getBranch());
            pstmt.executeUpdate();

            insertRequiredRoles((ShiftDTO) dto);
            insertAvailableWorkers((ShiftDTO) dto);
            insertAssignedWorkers((ShiftDTO) dto);
        } catch (Exception e) {
            throw new DataAccessException(e);
        } finally {
            closeConnection();
        }
        return true;
    }

    @Override
    public boolean update(DTO dto) {
        try {
            connect();
            String updateSQL =
                    "UPDATE Shifts SET startTime = ?, endTime = ?, branch = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(updateSQL);
            pstmt.setObject(1, ((ShiftDTO) dto).getStartTime());
            pstmt.setObject(2, ((ShiftDTO) dto).getEndTime());
            pstmt.setString(3, ((ShiftDTO) dto).getBranch());
            pstmt.setInt(4, ((ShiftDTO) dto).getId());
            pstmt.executeUpdate();

            deleteRequiredRoles((ShiftDTO) dto);
            deleteAvailableWorkers((ShiftDTO) dto);
            deleteAssignedWorkers((ShiftDTO) dto);

            insertRequiredRoles((ShiftDTO) dto);
            insertAvailableWorkers((ShiftDTO) dto);
            insertAssignedWorkers((ShiftDTO) dto);
        } catch (Exception e) {
            throw new DataAccessException(e);
        } finally {
            closeConnection();
        }

        return true;
    }

    @Override
    public boolean delete(DTO dto) {
        throw new UnsupportedOperationException("Deleting shifts is not supported.");
    }

    @Override
    public List<ShiftDTO> loadAll() {
        try {
            connect();
            String selectSQL = "SELECT * FROM Shifts";
            PreparedStatement pstmt = conn.prepareStatement(selectSQL);
            ResultSet rs = pstmt.executeQuery();
            List<ShiftDTO> shifts = new ArrayList<>();
            while (rs.next()) {
                ShiftDTO shift = new ShiftDTO();
                shift.setId(rs.getInt("id"));
                shift.setStartTime(LocalDateTime.parse(rs.getString("startTime")));
                shift.setEndTime(LocalDateTime.parse(rs.getString("endTime")));
                shift.setBranch(rs.getString("branch"));
                shifts.add(shift);
            }

            for (ShiftDTO shift : shifts) {
                selectSQL = "SELECT * FROM ShiftRoles WHERE shiftId = ?";
                pstmt = conn.prepareStatement(selectSQL);
                pstmt.setInt(1, shift.getId());
                rs = pstmt.executeQuery();
                Map<String, Integer> requiredRoles = shift.getRequiredRoles();
                while (rs.next())
                    requiredRoles.put(rs.getString("role"), rs.getInt("requiredCount"));
                shift.setRequiredRoles(requiredRoles);

                selectSQL = "SELECT * FROM ShiftAvailableWorkers WHERE shiftId = ?";
                pstmt = conn.prepareStatement(selectSQL);
                pstmt.setInt(1, shift.getId());
                rs = pstmt.executeQuery();
                List<String> availableWorkers = shift.getAvailableWorkers();
                while (rs.next())
                    availableWorkers.add(rs.getString("workerId"));
                shift.setAvailableWorkers(availableWorkers);

                selectSQL = "SELECT * FROM ShiftAssignedWorkers WHERE shiftId = ?";
                pstmt = conn.prepareStatement(selectSQL);
                pstmt.setInt(1, shift.getId());
                rs = pstmt.executeQuery();
                List<String> assignedWorkers = shift.getAssignedWorkers();
                while (rs.next())
                    assignedWorkers.add(rs.getString("workerId"));
                shift.setAssignedWorkers(assignedWorkers);
            }
            return shifts;
        } catch (Exception e) {
            throw new DataAccessException(e);
        } finally {
            closeConnection();
        }
    }

    private void insertRequiredRoles(ShiftDTO dto) throws SQLException {
        String insertSQL;
        PreparedStatement pstmt;
        for (String role : ((ShiftDTO) dto).getRequiredRoles().keySet()) {
            insertSQL = "INSERT INTO ShiftRoles(shiftId, role, requiredCount) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(insertSQL);
            pstmt.setInt(1, ((ShiftDTO) dto).getId());
            pstmt.setString(2, role);
            pstmt.setInt(3, ((ShiftDTO) dto).getRequiredRoles().get(role));
            pstmt.executeUpdate();
        }
    }

    private void insertAvailableWorkers(ShiftDTO dto) throws SQLException {
        String insertSQL;
        PreparedStatement pstmt;
        for (String worker : ((ShiftDTO) dto).getAvailableWorkers()) {
            insertSQL = "INSERT INTO ShiftAvailableWorkers(shiftId, workerId) VALUES (?, ?)";
            pstmt = conn.prepareStatement(insertSQL);
            pstmt.setInt(1, ((ShiftDTO) dto).getId());
            pstmt.setString(2, worker);
            pstmt.executeUpdate();
        }
    }

    private void insertAssignedWorkers(ShiftDTO dto) throws SQLException {
        String insertSQL;
        PreparedStatement pstmt;
        for (String worker : ((ShiftDTO) dto).getAssignedWorkers()) {
            insertSQL = "INSERT INTO ShiftAssignedWorkers(shiftId, workerId, role) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(insertSQL);
            pstmt.setInt(1, ((ShiftDTO) dto).getId());
            pstmt.setString(2, worker);
            pstmt.setString(3, "assigned");
            pstmt.executeUpdate();
        }
    }

    private void deleteRequiredRoles(ShiftDTO dto) throws SQLException {
        String deleteSQL = "DELETE FROM ShiftRoles WHERE shiftId = ?";
        PreparedStatement pstmt = conn.prepareStatement(deleteSQL);
        pstmt.setInt(1, ((ShiftDTO) dto).getId());
        pstmt.executeUpdate();
    }

    private void deleteAvailableWorkers(ShiftDTO dto) throws SQLException {
        String deleteSQL = "DELETE FROM ShiftAvailableWorkers WHERE shiftId = ?";
        PreparedStatement pstmt = conn.prepareStatement(deleteSQL);
        pstmt.setInt(1, ((ShiftDTO) dto).getId());
        pstmt.executeUpdate();
    }

    private void deleteAssignedWorkers(ShiftDTO dto) throws SQLException {
        String deleteSQL = "DELETE FROM ShiftAssignedWorkers WHERE shiftId = ?";
        PreparedStatement pstmt = conn.prepareStatement(deleteSQL);
        pstmt.setInt(1, ((ShiftDTO) dto).getId());
        pstmt.executeUpdate();
    }
}
