package com.apc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ProductsDAO {
	
	Connection con = null;			
	PreparedStatement pstmt = null;	
	ResultSet rs = null;			
	
	String sql = null;				
	
	
	private ProductsDAO() {	}
	
	private static ProductsDAO instance;
	
	public static ProductsDAO getInstance() {
		if(instance==null) {
			instance = new ProductsDAO();			
		}
		return instance;
	
	}//getInstance() end
	
	//DB연동을 진행하는 메서드
	public void openConn() {
		
		try {
			Context ctx = new InitialContext();
			
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
			
			con = ds.getConnection();
			
			if(con != null) {
				System.out.println("데이터 베이스 연결 성공");
			}else {
				System.out.println("데이터 베이스 연결 실패");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}//openConn() 메서드 end
	
	public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con) {
		
		
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
	}//closeConn() end
	
	//제품 전체 리스트 cap_products 테이블에서 가져오는 메서드
	public List<ProductsDTO> getProductList(){
		
		List<ProductsDTO> list = new ArrayList<ProductsDTO>();
				
		try {
			openConn();
			
			sql = "select * from apc_products order by pcategory_fk";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ProductsDTO dto = new ProductsDTO();
				
				dto.setPno(rs.getInt("pno"));
				dto.setPname(rs.getString("pname"));
				dto.setPcategory_fk(rs.getString("pcategory_fk"));
				dto.setPimage(rs.getString("pimage"));
				dto.setPqty(rs.getInt("pqty"));
				dto.setPrice(rs.getInt("price"));				
				dto.setPsize(rs.getString("psize"));
				dto.setPcolor(rs.getString("pcolor"));				
				dto.setPcontents(rs.getString("pcontents"));
				dto.setMileage(rs.getInt("mileage"));				
				dto.setPinputdate(rs.getString("pinputdate"));
				
				list.add(dto);			
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return list;
		
	}//getProductList() end
	
	// 제품 등록 폼 페이지에 입력된 정보가 DB에 저장되도록 하는 메서드
	public int prodcutsInsert(ProductsDTO dto) {
		int result=0, count=0;
		
		
		try {
			openConn();
			
			sql="select max(pno) from apc_products";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1)+1;
			}
			
			
			sql = "insert into apc_products values(?,?,?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getPname());
			pstmt.setString(3, dto.getPcategory_fk());
			pstmt.setString(4, dto.getPimage());
			pstmt.setInt(5, dto.getPqty());
			pstmt.setInt(6, dto.getPrice());			
			pstmt.setString(7, dto.getPsize());
			pstmt.setString(8, dto.getPcolor());			
			pstmt.setString(9, dto.getPcontents());
			pstmt.setInt(10, dto.getMileage());

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}//prodcutsInsert() end
	
	//해당번호에 해당하는 상세내역 가져오기
	public ProductsDTO getProductCont(int no) {
		
		ProductsDTO dto = new ProductsDTO();
		
		
		try {
			openConn();
			
			sql = "select * from apc_products where pno=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setPno(rs.getInt("pno"));
				dto.setPname(rs.getString("pname"));
				dto.setPcategory_fk(rs.getString("pcategory_fk"));
				dto.setPimage(rs.getString("pimage"));
				dto.setPqty(rs.getInt("pqty"));
				dto.setPrice(rs.getInt("price"));				
				dto.setPsize(rs.getString("psize"));
				dto.setPcolor(rs.getString("pcolor"));
				dto.setPcontents(rs.getString("pcontents"));
				dto.setMileage(rs.getInt("mileage"));				
				dto.setPinputdate(rs.getString("pinputdate"));
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return dto;			
		
	}//getProductCont() end
	
	//수정된 정보를 db에 넘겨주는 메서드
	public int prodcutsUpdate(ProductsDTO dto) {
		
		int result=0;
		try {
			openConn();
			
			sql="update apc_products set "
					+ " pname=?, pcategory_fk=?, pimage=?, pqty=?, price=?, psize=?, pcolor=?, pcontents=?,mileage=? where pno=?";
			
			pstmt = con.prepareStatement(sql);
			
			
			pstmt.setString(1, dto.getPname());
			pstmt.setString(2, dto.getPcategory_fk());
			pstmt.setString(3, dto.getPimage());
			pstmt.setInt(4, dto.getPqty());
			pstmt.setInt(5, dto.getPrice());
			pstmt.setString(6, dto.getPsize());
			pstmt.setString(7, dto.getPcolor());
			pstmt.setString(8, dto.getPcontents());
			pstmt.setInt(9, dto.getMileage());
			pstmt.setInt(10, dto.getPno());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}//prodcutsUpdate() end
	
	//apc_products테이블에서 넘어온 번호에 해당하는 상품 삭제하는 메서드
	public int productDelete(int no) {
		int result=0;
		
		openConn();
		
		sql ="delete form apc_products where pno=?";
		
		try {
			openConn();
			
			sql ="delete from apc_products where pno=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
			
			sql="update apc_products set pno = pno-1 where pno>?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return result;		
	}//productDelete() end

}