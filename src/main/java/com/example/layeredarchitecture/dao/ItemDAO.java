package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface ItemDAO {
    public ArrayList<ItemDTO> getallAllItems() throws SQLException, ClassNotFoundException ;


    public boolean save(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    public boolean updateitem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException ;

    public boolean deleteitem(String code) throws SQLException, ClassNotFoundException ;
    public boolean executeitem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException ;

    public String genarateid() throws SQLException, ClassNotFoundException ;
    public boolean exititems(ItemDTO itemDTO) throws SQLException, ClassNotFoundException ;

    ArrayList<ItemDTO> getallitemcods() throws SQLException, ClassNotFoundException;

    ItemDTO finditem(String code) throws SQLException, ClassNotFoundException;

    ItemDTO selectItem(String newItemCode) throws SQLException, ClassNotFoundException;

    ItemDTO findItemByCode(String itemCode) throws SQLException, ClassNotFoundException;

    boolean updateItem(ItemDTO item) throws SQLException, ClassNotFoundException;
}
