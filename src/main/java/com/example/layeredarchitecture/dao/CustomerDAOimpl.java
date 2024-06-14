package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOimpl implements CustomerDAO {


    public boolean insertCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer (id,name, address) VALUES (?,?,?)");
        pstm.setString(1,customerDTO.getId());
        pstm.setString(2, customerDTO.getName());
        pstm.setString(3, customerDTO.getAddress());
      return   pstm.executeUpdate()>0;
    }

    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
        pstm.setString(1,customerDTO.getName());
        pstm.setString(2, customerDTO.getAddress());
        pstm.setString(3, customerDTO.getId());
      return  pstm.executeUpdate()>0;
    }

    public int deleteCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
        pstm.setString(1, customerDTO.getId());
      return   pstm.executeUpdate();
    }

    public String genaratenewid() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = connection.createStatement().executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String ids = rst.getString("id");
            return ids;
        }
     return null;
    }

    public boolean execute(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
        pstm.setString(1, id);
        return pstm.executeQuery().next();
    }

    public ArrayList<CustomerDTO> getallcustomers() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Customer");
        ArrayList<CustomerDTO> allcustomers=new ArrayList<>();
        while(rst.next()){
            CustomerDTO customerDTO=new CustomerDTO(
            rst.getString("id"),
            rst.getString("name"),
            rst.getString("address"));
            allcustomers.add(customerDTO);

        }
        return allcustomers;
    }
 public    boolean exitcustmer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
     Connection connection = DBConnection.getDbConnection().getConnection();
     PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
     pstm.setString(1,customerDTO.getId());
     return pstm.executeQuery().next();
    }
 public    ArrayList<CustomerDTO> getcustomerids() throws SQLException, ClassNotFoundException {
     Connection connection = DBConnection.getDbConnection().getConnection();
     Statement stm = connection.createStatement();
     ResultSet rst = stm.executeQuery("SELECT * FROM Customer");
        ArrayList<CustomerDTO> allcustomerids=new ArrayList<>();
        while (rst.next()){
            CustomerDTO customerDTO=new CustomerDTO(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("address")
            );
            allcustomerids.add(customerDTO);
        }
        return allcustomerids;
    }
}

