package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrderdetailDAO {
    public boolean savesorderdetails(List<OrderDetailDTO> orderDetails, String orderId) throws SQLException, ClassNotFoundException ;
}


