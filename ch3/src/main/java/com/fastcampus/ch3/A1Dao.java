package com.fastcampus.ch3;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

@Repository
public class A1Dao {
	@Autowired
	DataSource ds;
	
	public int insert(String key, String value) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
//			conn = ds.getConnection();
			conn = DataSourceUtils.getConnection(ds);
			System.out.println("conn = " + conn);
			pstmt = conn.prepareStatement("insert into a1 values(?,?)");
			pstmt.setString(1, key);
			pstmt.setString(2, value);
			
			return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
//			close(conn, pstmt);
			close(pstmt);
			DataSourceUtils.releaseConnection(conn, ds);
		}
	}
	
	private void close(AutoCloseable... acs) {
        for(AutoCloseable ac :acs)
            try { if(ac!=null) ac.close(); } catch(Exception e) { e.printStackTrace(); }
    }

	public void deleteAll() throws Exception {
		Connection conn = DataSourceUtils.getConnection(ds);
		String sql = "delete from A1";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
		close(pstmt);
	}
}
