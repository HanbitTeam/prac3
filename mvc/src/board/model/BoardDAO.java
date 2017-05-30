package board.model;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import board.control.ActionForward;

public class BoardDAO {

   Connection con;
   PreparedStatement ptmt;
   ResultSet rs;
   String sql;
   ActionForward forward;
   String path = "";
   
   ArrayList<String> imgList = new ArrayList<>();
   
   public BoardDAO(ActionForward forward) {
      // TODO Auto-generated constructor stub
      this.forward = forward;
      String up = "up";
      path = forward.getRequest().getRealPath(up);
      path = "D:\\webWork\\mvc\\WebContent\\up";
      
      imgList.add("bmp");
      imgList.add("jpg");
      imgList.add("jpeg");
      imgList.add("png");
      imgList.add("gif");
      
      try {
         Context init = new InitialContext();
         DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
         con = ds.getConnection();
         
         
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      
      
   }
   
   
   Object boardList()
   {
	    
	   ArrayList<BoardDTO> res = new ArrayList<>(); 
	     
    
      
      try {
    	  
    	  
    	  
   	   sql= "select count(*) from notice";
   	   
   	   ptmt= con.prepareStatement(sql);
   	   
   	   rs=ptmt.executeQuery();
   	   
   	   rs.next();
   	   
   	   forward.setTotal(rs.getInt(1));
   	   
   	   sql = "select * from"
   	             +"(select rownum rnum, t1.*from"
   	             +"(select * from notice order by gid desc,seq) t1)"
   	             +"where rnum> ? and rnum <= ?";
         ptmt = con.prepareStatement(sql);
         
         ptmt.setInt(1,forward.getStart() );
         ptmt.setInt(2,forward.getEnd() );
         
         rs = ptmt.executeQuery();
         
         while(rs.next())
         {
            BoardDTO dto = new BoardDTO();
            
            dto.setId(rs.getInt("id"));
            dto.setLev(rs.getInt("lev"));
            dto.setCnt(rs.getInt("cnt"));
            dto.setTitle(rs.getString("title"));
            dto.setPname(rs.getString("pname"));
            dto.setReg_date(rs.getDate("reg_date"));
            
            res.add(dto);
         }
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally{
         close();
      }
      
      return res;
   }
   
   
   ////조회수 추가 
   void addCount(int id)
   {
      sql = "update notice set cnt = cnt+1 where id =?";
      
      try {
         ptmt = con.prepareStatement(sql);
         
         ptmt.setInt(1, id);
         
         ptmt.executeUpdate();
         

      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      
      }
   
   }
   
   
   ////게시판 디테일
   BoardDTO boardDetail(int id)
   {
      sql = "select * from notice where id = ?";
      
      try {
         ptmt = con.prepareStatement(sql);
         
         ptmt.setInt(1, id);
         
         rs = ptmt.executeQuery();
         
         if(rs.next())
         {
            BoardDTO dto = new BoardDTO();
            
            dto.setId(rs.getInt("id"));
            dto.setCnt(rs.getInt("cnt"));
            dto.setTitle(rs.getString("title"));
            dto.setPname(rs.getString("pname"));
            dto.setReg_date(rs.getDate("reg_date"));
            dto.setContent(rs.getString("content"));
            dto.setUpfile(rs.getString("upfile"));
            
            if(dto.getUpfile()==null || 
                  dto.getUpfile().equals("null") || 
                  dto.getUpfile().equals(""))
               {
                  dto.setUpfile(null);
               }
            
            dto.setExtImg(
               dto.getUpfile()!=null && 
               imgList.contains(dto.getUpfile().substring(dto.getUpfile().lastIndexOf(".")+1)));
            
         
            return dto;
         }
         
         
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally{
         close();
      }
      
      return null;
   }
   
   void insertReg(BoardDTO dto)
   {
      
      
      try {
         dto.setId(0);
         
         sql = "select max(id)+1 from notice";
         ptmt = con.prepareStatement(sql);
         
         
         rs = ptmt.executeQuery();
         
         if(rs.next())
         {
            dto.setId(rs.getInt(1));
         }
         
         
         sql = "insert into notice "
               + "(id, gid, seq, lev, reg_date, cnt, pname, pw, title, content, upfile) values"
               + " (?,?,?,?, sysdate,?,?,?,?,?,?)";
         
         ptmt = con.prepareStatement(sql);
         
         ptmt.setInt(1, dto.getId());
         ptmt.setInt(2, dto.getGid());
         ptmt.setInt(3, 0);
         ptmt.setInt(4, 0);
         ptmt.setInt(5, -1);
         ptmt.setString(6, dto.getPname());
         ptmt.setString(7, dto.getPw());
         ptmt.setString(8, dto.getTitle());
         ptmt.setString(9, dto.getContent());
         ptmt.setString(10, dto.getUpfile());
            
            
         ptmt.executeUpdate();
         
         
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally{
         close();
      }
      
      
   }
   
   void fileDelete(int id)
   {
      sql = "select * from notice where id = ?";
      
      try {
         ptmt = con.prepareStatement(sql);
         
         ptmt.setInt(1, id);
         
         rs = ptmt.executeQuery();
         
         if(rs.next())
         {   
            String fileName = rs.getString("upfile");
            
            if(fileName==null || 
                  !fileName.equals("null") || 
                  !fileName.equals(""))
               {
                  File ff = new File(path+"/"+fileName);
                  ff.delete();
                  
                  sql = "update notice set upfile = '' where id = ?";
                  
                  ptmt = con.prepareStatement(sql);
                  
                  ptmt.setInt(1, id);
                  
                  ptmt.executeUpdate();
               }
            
         }

         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      

   
   }
   
   
   
   public boolean boardDelete(BoardDTO dto)
   {
      boolean res =false;
      
      try {
         
         
         sql = "select * from notice where id=? and pw=?";
         ptmt = con.prepareStatement(sql);
         
         ptmt.setInt(1, dto.getId());
         ptmt.setString(2, dto.getPw());
         
         rs = ptmt.executeQuery();
         
         if(rs.next())
         {
            
            fileDelete(dto.getId());
            
            sql = "delete from notice where id =?";
            ptmt = con.prepareStatement(sql);
            
            ptmt.setInt(1, dto.getId());
            ptmt.executeUpdate();
               
            res = true;   
         }

      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally{
         close();
      }
   
      return res;      
   }

   
   public boolean boardmodify(BoardDTO dto)
   {
      boolean res =false;
      
      try {
         
         
         sql = "select * from notice where id=? and pw=?";
         ptmt = con.prepareStatement(sql);
         
         ptmt.setInt(1, dto.getId());
         ptmt.setString(2, dto.getPw());
         
         rs = ptmt.executeQuery();
         
         if(rs.next())
         {

            sql = "update notice set title=?, pname=?, content=? where id =?";
            ptmt = con.prepareStatement(sql);
            
            ptmt.setString(1, dto.getTitle());
            ptmt.setString(2, dto.getPname());
            ptmt.setString(3, dto.getContent());
            ptmt.setInt(4, dto.getId());
            ptmt.executeUpdate();
               
            res = true;   
         }

      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally{
         close();
      }
   
      return res;      
   }
   
   public void boardreply(BoardDTO dto){
         
         //   1. 원글에서 필요한 정보 --- gid, seq, lev
         //   2. 현재 글 밑의 seq 변경
         //   3. 답변달기 --- gid, seq+1, lev+1
      
      try {
         // 1. 원글에서 필요한 정보 --- gid, seq, lev
         sql = "select * from notice where id = ?" ;
         ptmt = con.prepareStatement(sql);
         
         ptmt.setInt(1, dto.getId());
         
         
         rs = ptmt.executeQuery();
         
         rs.next();
         
         dto.setGid(rs.getInt("gid"));   
         dto.setSeq(rs.getInt("seq"));      
         dto.setLev(rs.getInt("lev"));      
         
//         2. 현재 글 밑의 seq 변경
      
         sql = "update notice set seq = seq+1 where gid=? and seq > ?";
         ptmt = con.prepareStatement(sql);
         
         ptmt.setInt(1, dto.getGid());
         ptmt.setInt(2, dto.getSeq());
         ptmt.executeUpdate();
            
         
         dto.setId(0);
         
         sql = "select max(id)+1 from notice";
         ptmt = con.prepareStatement(sql);
         
         
         rs = ptmt.executeQuery();
         
         if(rs.next())
         {
            dto.setId(rs.getInt(1));
         }
         
         
         sql = "insert into notice "
               + "(id, gid, seq, lev, reg_date, cnt, pname, pw, title, content) values"
               + " (?,?,?,?, sysdate,?,?,?,?,?)";
         
         ptmt = con.prepareStatement(sql);
         
         ptmt.setInt(1, dto.getId());
         ptmt.setInt(2, dto.getGid());
         ptmt.setInt(3, dto.getSeq()+1);
         ptmt.setInt(4, dto.getLev()+1);
         ptmt.setInt(5, -1);
         ptmt.setString(6, dto.getPname());
         ptmt.setString(7, dto.getPw());
         ptmt.setString(8, dto.getTitle());
         ptmt.setString(9, dto.getContent());
       
            
            
         ptmt.executeUpdate();
         
         
         
         
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally{close();}
      
      
   }
   
   public void close()
   {
      if(rs!=null)try {rs.close();} catch (SQLException e) {}
      if(ptmt!=null)try {ptmt.close();} catch (SQLException e) {}
      if(con!=null)try {con.close();} catch (SQLException e) {}
   }
   
   
}