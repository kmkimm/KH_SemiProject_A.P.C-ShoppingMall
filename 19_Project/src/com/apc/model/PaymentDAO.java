package com.apc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

	Connection con = null;             // DB 연결하는 객체.
	PreparedStatement pstmt = null;    // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null;               // SQL문을 실행 후 결과 값을 가지고 있는 객체.
	
	String sql = null;                 // SQL문을 저장할 객체.
	
	// ProductDAO 객체를 싱글턴 방식으로 만들어 보자.
	// 1단계 : 싱글턴 방식으로 객체를 만들기 위해서는 우선적으로
	//		   기본 생성자의 접근 제어자를 public에서 private으로 바꿔 주어야 한다.
	
	// 2단계 : ProductDAO 객체를 정적 멤버로 선언해 주어야 한다. - static으로 선언해야 함.
	private static PaymentDAO instance;
	
	private PaymentDAO() { }  // 기본 생성자
		
	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance() 라는 메서드를
	// 만들어서 이 getInstance() 메서드에 외부에서 접근할 수 있게 해야 함.
	public static PaymentDAO getInstance() {
		
		if(instance == null) {
			instance = new PaymentDAO();
		}
		
		return instance;
	}  // getInstance() 메서드 end
		
	
	// DB를 연동하는 작업을 진행하는 메서드
	public void openConn() {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "semi";
		String password = "1234";
		
		// 1단계 : 오라클 드라이버 로딩.
		try {
			// 1단계 : 오라클 드라이버 로딩.
			Class.forName(driver);
			
			// 2단계 : 오라클 데이터베이스와 연결 진행.
			con = DriverManager.getConnection(url, user, password);
			
			if(con != null) {
				System.out.println("데이터베이스 연결 성공");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}  // openConn() 메서드 end
	

	// DB에 연결된 자원을 종료하는 메서드
	public void closeConn(ResultSet rs, PreparedStatement pstmt,
						Connection con) {
	
		try {
			if(rs != null) {
				rs.close();
			}
			
			if(pstmt != null) {
				pstmt.close();
			}
			
			if(con != null) {
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}  // closeConn() 메서드 end
	
	
	// 결제내역을 불러오는 메서드
		public List<PaymentDTO> getPaymentList(String id) {
			
			List<PaymentDTO> list = new ArrayList<PaymentDTO>();
			
			try {
				openConn();
				
				sql = "select * from apc_payment where order_id = ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
				PaymentDTO dto = new PaymentDTO();
				
				dto.setOrder_no(rs.getInt("order_no"));;
				dto.setCartno_fk(rs.getInt("cartno_fk"));
				dto.setOrder_id(rs.getString("order_id"));
				dto.setPno_fk(rs.getInt("pno_fk"));
				dto.setPname(rs.getString("pname"));
				dto.setPqty(rs.getInt("pqty"));
				dto.setPrice(rs.getInt("price"));
				dto.setTranscost(rs.getInt("transcost"));
				dto.setPaytype(rs.getInt("paytype"));
				dto.setOrderdate(rs.getString("orderdate"));
				dto.setOrdername(rs.getString("ordername"));
				dto.setOrderaddr(rs.getString("orderaddr"));
				dto.setOrderphone(rs.getString("orderphone"));
				
				list.add(dto);
				
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			
			return list;
		}  // getPaymentInfo() 메서드 end
		
	
}