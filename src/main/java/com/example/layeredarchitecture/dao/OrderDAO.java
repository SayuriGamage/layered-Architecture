package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface OrderDAO {
    public String genarateid() throws SQLException, ClassNotFoundException ;

    boolean selectid(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;

    boolean save(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;

}
