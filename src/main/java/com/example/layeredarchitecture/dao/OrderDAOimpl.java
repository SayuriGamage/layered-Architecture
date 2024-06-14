package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.*;
import java.util.List;

public class OrderDAOimpl implements OrderDAO{
    public String genarateid() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");


        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }
   public boolean selectid(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
       Connection connection = null;
       connection = DBConnection.getDbConnection().getConnection();
       PreparedStatement stm = connection.prepareStatement("SELECT oid FROM `Orders` WHERE oid=?");
       stm.setString(1, orderDTO.getOrderId());
       return stm.executeQuery().next();
    }
  /* public boolean save(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
       Connection connection = DBConnection.getDbConnection().getConnection();
     PreparedStatement  stm = connection.prepareStatement("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)");
       stm.setString(1, orderDTO.getOrderId());
       stm.setDate(2, Date.valueOf(orderDTO.getOrderDate()));
       stm.setString(3, orderDTO.getCustomerId());
       return stm.executeUpdate()>0;
   }*/
  public boolean save(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
      Connection connection = null;
      PreparedStatement stm = null;
      try {
          connection = DBConnection.getDbConnection().getConnection();
          stm = connection.prepareStatement("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)");
          stm.setString(1, orderDTO.getOrderId());
          stm.setDate(2, Date.valueOf(orderDTO.getOrderDate()));
          stm.setString(3, orderDTO.getCustomerId());
          return stm.executeUpdate() > 0;
      } finally {
          if (stm != null) stm.close();
          if (connection != null) connection.close();
      }
  }


}
