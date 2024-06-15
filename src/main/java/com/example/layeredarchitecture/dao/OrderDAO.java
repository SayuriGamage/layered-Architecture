package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

public interface OrderDAO {
    public String genarateid() throws SQLException, ClassNotFoundException ;

    boolean selectid(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;


    boolean isOrderPlaced(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails);

}
