package model;

import model.Stock;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockDAO {

    public static Map<String, Stock> getAllStocks() throws Exception {
        Map<String, Stock> stockMap = new LinkedHashMap<>();

        String sql = "SELECT stock_name, stock_price FROM Stock";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("stock_name");
                int price = rs.getInt("stock_price");
                stockMap.put(name, new Stock(name, price));  // rate 제거됨
            }
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }

        return stockMap;
    }
    
    public static void insertStock(Stock stock) throws Exception {
        String sql = "INSERT INTO stock (id, stock_name, stock_price) VALUES (stock_seq.NEXTVAL, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, stock.getName());
            pstmt.setInt(2, stock.getPrice());
            pstmt.executeUpdate();
        }
    }
}