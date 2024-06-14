package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;



public class OrderdetailDAOimpl implements OrderdetailDAO {
   public boolean saveOrderDetail(OrderDetailDTO orderDetailDTO,String orderId) throws SQLException, ClassNotFoundException {
       Connection connection = DBConnection.getDbConnection().getConnection();
    PreparedStatement   stm = connection.prepareStatement("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)");

       stm.setString(1, orderId);
       stm.setString(2, orderDetailDTO.getItemCode());
       stm.setBigDecimal(3, orderDetailDTO.getUnitPrice());
       stm.setInt(4, orderDetailDTO.getQty());
       return stm.executeUpdate() == 1;
   }

}
