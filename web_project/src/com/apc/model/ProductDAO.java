package com.apc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class ProductDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = null;
	
	private static ProductDAO instance = null;
	
	private ProductDAO() { }
	
	public static ProductDAO getInstance() {
		
		if(instance == null) {
			
			instance = new ProductDAO();
			
		}
		return instance;
	}//getInstance() end
	
	//DB연동하는 작업을 하는 메서드 -- DBCP방식으로 데이터베이스와 연결 진행
	public void openConn() {
		
		
		try {
			//1단계: JNDI서버 객체 생성
			Context ctx = new InitialContext();
			
			//2단계: lookup() 메서드 이요해 매칭되는 커넥션 찾기
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle"); //context.xml에 적은 jdbc/myoracle 을 적기
			
			//3단계: DataSource객체를 이용해 커넥션 객체를 하나 가져오기
			con = ds.getConnection(); //getConnection()은 Connection반환타입
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}//openConn() end 

	public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con) {
		
		
			try {
				if( rs != null) {
					rs.close();
				}
				
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(con != null) {
					con.close();
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}//closeConn() end 

	//DB에 제품 데이터 저장하는 메서드
	public int productUpload(ProductDTO dto) {
		
	int result =0, count=0;
		
		try {
			openConn();
			
			sql="select max(pno) from apc_products";
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1)+1;
			}
			
			sql="insert into apc_products values(?,?,?,?,?,?,?,?,?, ?, sysdate)";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getPname());
			pstmt.setString(3, dto.getPcategory_fk());
			pstmt.setString(4, dto.getPimage());
			pstmt.setInt(5, dto.getPqty());
			pstmt.setInt(6, dto.getPrice());
			pstmt.setString(7, dto.getPsize());
			pstmt.setString(8, dto.getPcolor());
//			pstmt.setString(9, dto.getPicon());
			pstmt.setString(9, dto.getPcontents());
			pstmt.setInt(10, dto.getMileage());
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
	}
	
	//전체 제품리스트(중복제거)를 조회하는 메서드 
	public List<ProductDTO> getProductList() {
		
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		
		try {
			openConn();
			
			sql="select * from apc_products where pno in (select min(pno) from apc_products group by pname) "
					+ " order by pno desc";
			
			
//			sql="select * from apc_products where pcategory_fk like ? "
//					+ " order by pno desc " ;
			
			pstmt=con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ProductDTO dto = new ProductDTO();
				
				dto.setPno(rs.getInt("pno"));
				dto.setPname(rs.getString("pname"));
				dto.setPcategory_fk(rs.getString("pcategory_fk"));
				dto.setPimage(rs.getString("pimage"));
				dto.setPqty(rs.getInt("pqty"));
				dto.setPrice(rs.getInt("price"));
				dto.setPsize(rs.getString("psize"));
				dto.setPcolor(rs.getString("pcolor"));
//				dto.setPicon(rs.getString("picon"));
				dto.setPcontents(rs.getString("pcontents"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setPinputdate(rs.getString("pinputdate"));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		return list;
	}//getProductList() end
	
	

	//코드에 해당하는 제품리스트를 조회하는 메서드 
	public List<ProductDTO> getProductList(String code) {
		
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		
		try {
			openConn();
			
			sql="select * from apc_products where pno in (select min(pno) from apc_products group by pname) "
					+ " and pcategory_fk like ? order by pno desc";
			
			
//			sql="select * from apc_products where pcategory_fk like ? "
//					+ " order by pno desc " ;
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, code+"%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ProductDTO dto = new ProductDTO();
				
				dto.setPno(rs.getInt("pno"));
				dto.setPname(rs.getString("pname"));
				dto.setPcategory_fk(rs.getString("pcategory_fk"));
				dto.setPimage(rs.getString("pimage"));
				dto.setPqty(rs.getInt("pqty"));
				dto.setPrice(rs.getInt("price"));
				dto.setPsize(rs.getString("psize"));
				dto.setPcolor(rs.getString("pcolor"));
//				dto.setPicon(rs.getString("picon"));
				dto.setPcontents(rs.getString("pcontents"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setPinputdate(rs.getString("pinputdate"));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		return list;
	}//getProductList() end

	//코드에 해당하는 상세정보를 조회하는 메서드
	public ProductDTO getProductCont(int pno) {
		
		ProductDTO dto = new ProductDTO();
		
		try {
			openConn();
			
			sql="select * from apc_products where pno = ? ";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, pno);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				
				dto.setPno(rs.getInt("pno"));
				dto.setPname(rs.getString("pname"));
				dto.setPcategory_fk(rs.getString("pcategory_fk"));
				dto.setPimage(rs.getString("pimage"));
				dto.setPqty(rs.getInt("pqty"));
				dto.setPrice(rs.getInt("price"));
				dto.setPsize(rs.getString("psize"));
				dto.setPcolor(rs.getString("pcolor"));
//				dto.setPicon(rs.getString("picon"));
				dto.setPcontents(rs.getString("pcontents"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setPinputdate(rs.getString("pinputdate"));
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		return dto;
		
	}//getProductCont();
	
	//제품명과 칼라에 해당하는 min(pno)제품정보를 가져오는 메서드
	public ProductDTO getProductCont(String pname, String color) {
		
		ProductDTO dto = new ProductDTO();
		System.out.println(pname.substring(0,5));
		System.out.println();
		
		try {
			openConn();
			
			sql="select * from apc_products where pno = (select min(pno)from apc_products "
					+ " where pname like ? and pcolor = ?  group by pname)";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, pname.substring(0, 5)+"%");
			pstmt.setString(2, color);
			
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				
				dto.setPno(rs.getInt("pno"));
				dto.setPname(rs.getString("pname"));
				dto.setPcategory_fk(rs.getString("pcategory_fk"));
				dto.setPimage(rs.getString("pimage"));
				dto.setPqty(rs.getInt("pqty"));
				dto.setPrice(rs.getInt("price"));
				dto.setPsize(rs.getString("psize"));
				dto.setPcolor(rs.getString("pcolor"));
//				dto.setPicon(rs.getString("picon"));
				dto.setPcontents(rs.getString("pcontents"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setPinputdate(rs.getString("pinputdate"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		return dto;
	}//getProductCont(pname, color)
	
	//오버리딩 pname, color, size에 해당하는 pno 찾기
	public ProductDTO getProductCont(String pname, String color, String size) {
		ProductDTO dto = new ProductDTO();
		
		try {
			openConn();
			
			sql="select * from apc_products where pname like ? and pcolor = ? and psize=? ";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, pname.substring(0, 5)+"%");
			pstmt.setString(2, color);
			pstmt.setString(3, size);
			
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				
				dto.setPno(rs.getInt("pno"));
				dto.setPname(rs.getString("pname"));
				dto.setPcategory_fk(rs.getString("pcategory_fk"));
				dto.setPimage(rs.getString("pimage"));
				dto.setPqty(rs.getInt("pqty"));
				dto.setPrice(rs.getInt("price"));
				dto.setPsize(rs.getString("psize"));
				dto.setPcolor(rs.getString("pcolor"));
//				dto.setPicon(rs.getString("picon"));
				dto.setPcontents(rs.getString("pcontents"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setPinputdate(rs.getString("pinputdate"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		return dto;
	}//getProductCont(pname, color, size) end
	

	//dto에 저장된 다중파일로 업로드된 파일이름을 나누는 작업을 하는 메서드  
	public String[] getPorudctImg(ProductDTO dto) {
		
		StringTokenizer st = null;
		
		String image = dto.getPimage();
		
		st = new StringTokenizer(image,",");
		String[] arrImg = new String[st.countTokens()];
		
		for(int i =0; i<arrImg.length; i++){
			arrImg[i]=st.nextToken();
		}
		
		
		return arrImg;
		
	}//getProductImg() end 

	//A제품 칼라 정보를 가져오는 메서드 
	public String[] getProductColor(String pname) {
		
		String [] colors = null;
		int count =0;
		try {
			openConn();
			
			//배열의 길이 정하기 
			sql="select count(distinct pcolor) from apc_products where pname like ? ";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, pname.substring(0,5)+"%");
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
			System.out.println("count:"+count);
			
			colors= new String[count];
			
			sql="select distinct pcolor from apc_products where pname like ?";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, pname.substring(0,5)+"%");
			
			rs=pstmt.executeQuery();
			
			int i=0;
			
			while(rs.next()) {
				
				System.out.println("i:"+i);
				System.out.println("rs.getString :"+ rs.getString("pcolor"));
			
				colors[i]=rs.getString("pcolor");
			
				System.out.println(colors[i]);
				
				i++;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		
		return colors;
	}//getProductColor() end 
	
	
	//A제품 사이즈 정보를 가져오는 메서드 
	public String[] getProductSize(String pname) {
		
		String short_name = pname.substring(0,5);
		System.out.println(short_name);
		String [] sizes = null;
		int count =0;
		try {
			openConn();
			
			//배열의 길이 정하기 
			sql="select count(distinct psize) from apc_products where pname like ? ";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, pname.substring(0,5)+"%");
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
			System.out.println("size scount:"+count);
			
			sizes= new String[count];
			
			sql="select distinct psize from apc_products where pname like ? order by psize";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, pname.substring(0,5)+"%");
			
			rs=pstmt.executeQuery();
			
			int i=0;
			
			while(rs.next()) {
				
				System.out.println("i:"+i);
				System.out.println("rs.getString :"+ rs.getString("psize"));
				
				sizes[i]=rs.getString("psize");
				
				System.out.println(sizes[i]);
				
				i++;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		
		return sizes;
	}//getProductSize() end 


	
//*********************** 경연님 *********************

	public ProductDTO getProductContent(int pno_fk) {
		
		ProductDTO dto = new ProductDTO();
		
		try {
			openConn();
			sql = "select * from apc_products where pno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pno_fk);
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
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	} // getProductContent() end
	






	
	
	
}
