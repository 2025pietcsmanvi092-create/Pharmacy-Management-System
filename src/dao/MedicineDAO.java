package dao;

import database.DBConnection;
import model.Medicine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicineDAO {
    // Fetch all medicines
    public ArrayList<Medicine> getAllMedicines() {

        ArrayList<Medicine> medicines = new ArrayList<>();

        String sql = "SELECT * FROM Medicines";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Medicine medicine = new Medicine();

                medicine.setId(rs.getInt("id"));
                medicine.setName(rs.getString("name"));
                medicine.setCategory(rs.getString("category"));
                medicine.setQuantity(rs.getInt("quantity"));
                medicine.setPrice(rs.getDouble("price"));
                medicine.setExpiryDate(rs.getString("expiry_date"));

                medicines.add(medicine);
            }

        } catch (SQLException | NullPointerException e) {
            System.out.println("Error loading medicines: " + e.getMessage());
        }

        return medicines;
    }


    // Add a new medicine
    public boolean addMedicine(Medicine medicine) {

        String sql = "INSERT INTO Medicines " +
                "(name, category, quantity, price, expiry_date) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            if (con == null) {
                return false;
            }

            ps.setString(1, medicine.getName());
            ps.setString(2, medicine.getCategory());
            ps.setInt(3, medicine.getQuantity());
            ps.setDouble(4, medicine.getPrice());
            ps.setString(5, medicine.getExpiryDate());

            return ps.executeUpdate() > 0;

        } catch (SQLException | NullPointerException e) {
            System.out.println("Error adding medicine: " + e.getMessage());
            return false;
        }
    }


    // Update an existing medicine
    public boolean updateMedicine(Medicine medicine) {

        String sql = "UPDATE Medicines SET name = ?, category = ?, " +
                "quantity = ?, price = ?, expiry_date = ? WHERE id = ?";

        try (
                Connection con = DBConnection.getConnection()
        ) {

            if (con == null) {
                return false;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, medicine.getName());
                ps.setString(2, medicine.getCategory());
                ps.setInt(3, medicine.getQuantity());
                ps.setDouble(4, medicine.getPrice());
                ps.setString(5, medicine.getExpiryDate());
                ps.setInt(6, medicine.getId());

                return ps.executeUpdate() > 0;
            }

        } catch (SQLException | NullPointerException e) {
            System.out.println("Error updating medicine: " + e.getMessage());
            return false;
        }
    }


    // Delete a medicine using its ID
    public boolean deleteMedicine(int id) {

        String sql = "DELETE FROM Medicines WHERE id = ?";

        try (
                Connection con = DBConnection.getConnection()
        ) {

            if (con == null) {
                return false;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, id);

                return ps.executeUpdate() > 0;
            }

        } catch (SQLException | NullPointerException e) {
            System.out.println("Error deleting medicine: " + e.getMessage());
            return false;
        }
    }
}
