package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public interface CustomerDAO {
    public boolean insertCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException ;

    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException ;

    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException ;

    public String genaratenewid() throws SQLException, ClassNotFoundException ;

    public boolean execute(String id) throws SQLException, ClassNotFoundException ;

    public ArrayList<CustomerDTO> getallcustomers() throws SQLException, ClassNotFoundException ;

    boolean exitcustmer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDTO> getcustomerids() throws SQLException, ClassNotFoundException;

    CustomerDTO selectCustomer(String newValue) throws SQLException, ClassNotFoundException;
}
