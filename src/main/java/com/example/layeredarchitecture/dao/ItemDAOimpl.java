package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ItemDAOimpl implements ItemDAO{

    public ArrayList<ItemDTO> getallAllItems() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = ((Statement) stm).executeQuery("SELECT * FROM Item");
        ArrayList<ItemDTO> allitems= new ArrayList<>();
        while(rst.next()){
            ItemDTO itemDTO=new ItemDTO(
                    rst.getString("code"),
                    rst.getString("description"),
                    rst.getBigDecimal("unitPrice"),
                    rst.getInt("qtyOnHand")
            );
            allitems.add(itemDTO);
        }
        return allitems;
    }


    public boolean save(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)");
        pstm.setString(1, itemDTO.getCode());
        pstm.setString(2, itemDTO.getDescription());
        pstm.setBigDecimal(3, itemDTO.getUnitPrice());
        pstm.setInt(4, itemDTO.getQtyOnHand());
     return    pstm.executeUpdate()>0;
    }

    public boolean updateitem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
        pstm.setString(1, itemDTO.getDescription());
        pstm.setBigDecimal(2, itemDTO.getUnitPrice());
        pstm.setInt(3, itemDTO.getQtyOnHand());
        pstm.setString(4, itemDTO.getCode());
       return pstm.executeUpdate()>0;
    }

    public boolean deleteitem(String code) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");
        pstm.setString(1, code);
       return pstm.executeUpdate()>0;
    }

    public boolean executeitem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");
        pstm.setString(1, itemDTO.getCode());
        return pstm.executeQuery().next();
    }

    public String genarateid() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = connection.createStatement().executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            return id;
        }
        return  null;
    }

    public boolean exititems(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");
        pstm.setString(1, itemDTO.getCode());
        return pstm.executeQuery().next();
    }
  public   ArrayList<ItemDTO> getallitemcods() throws SQLException, ClassNotFoundException {
      Connection connection = DBConnection.getDbConnection().getConnection();
      Statement stm = connection.createStatement();
      ResultSet rst = stm.executeQuery("SELECT * FROM Item");
      ArrayList<ItemDTO> allitemcodes=new ArrayList<>();
      while (rst.next()){
          ItemDTO itemDTO=new ItemDTO(
                  rst.getString("code"),
                  rst.getString("description"),
                  rst.getBigDecimal("unitPrice"),
                  rst.getInt("qtyOnHand")

          );
          allitemcodes.add(itemDTO);
      }
      return allitemcodes;
    }
   public ItemDTO finditem(String code) throws SQLException, ClassNotFoundException {
       Connection connection = DBConnection.getDbConnection().getConnection();
       PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
       pstm.setString(1, code);
       ResultSet rst = pstm.executeQuery();
       rst.next();
       return new ItemDTO(code, rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));

   }


    public boolean update(ItemDTO item) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");

        pstm.setString(1, item.getDescription());
        pstm.setBigDecimal(2, item.getUnitPrice());
        pstm.setInt(3, item.getQtyOnHand());
        pstm.setString(4, item.getCode());
        return pstm.executeUpdate()>0;
    }
   public ItemDTO selectItem(String newItemCode) throws SQLException, ClassNotFoundException {
       Connection connection = DBConnection.getDbConnection().getConnection();
       PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
       pstm.setString(1, newItemCode + "");
       ResultSet rst = pstm.executeQuery();
       rst.next();
     return   new ItemDTO(newItemCode + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
   }
    public ItemDTO findItemByCode(String itemCode) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM Item WHERE code = ?");
        stm.setString(1,itemCode);
        ResultSet rst=stm.executeQuery();
      if( rst.next()){
          String code = rst.getString("code");
          String description = rst.getString("description");
          BigDecimal unitPrice = rst.getBigDecimal("unitPrice");
          int qtyOnHand = rst.getInt("qtyOnHand");

          return new ItemDTO(code, description, unitPrice, qtyOnHand);
      }
return null;
    }
  public   boolean isItemSearchedAndUpdated(Connection connection, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
      String code = null;
      int qty = 0;
      for(OrderDetailDTO detail : orderDetails){
          code=detail.getItemCode();
          qty=detail.getQty();
      }
      ItemDTO item = findItemByCode(code);
      item.setQtyOnHand(item.getQtyOnHand() - qty);

      PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
      pstm.setString(1, item.getDescription());
      pstm.setBigDecimal(2, item.getUnitPrice());
      pstm.setInt(3, item.getQtyOnHand());
      pstm.setString(4, item.getCode());

      if (!(pstm.executeUpdate() > 0)) {
          return false;
      } else {
          return true;
      }
  }


    }

