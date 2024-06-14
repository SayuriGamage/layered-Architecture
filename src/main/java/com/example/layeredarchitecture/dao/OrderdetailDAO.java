package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrderdetailDAO {
    boolean saveOrderDetail(OrderDetailDTO orderDetailDTO,String orderId) throws SQLException, ClassNotFoundException;

}


