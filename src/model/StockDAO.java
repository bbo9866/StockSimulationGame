package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import util.DBUtil;

public class StockDAO {

    public static Map<String, Stock> getAllStocks() throws Exception {
        Map<String, Stock> stockMap = null;


        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement("SELECT stock_name, stock_price FROM Stock");
            rs = pstmt.executeQuery();

            stockMap = new LinkedHashMap<>();
            while (rs.next()) {
                String name = rs.getString("stock_name");
                stockMap.put(name, new Stock(name, rs.getInt("stock_price")));  // rate 제거됨
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
    
    public static boolean existsByName(String stockName) throws SQLException {
        String sql = "SELECT COUNT(*) FROM stock WHERE name = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, stockName);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
}