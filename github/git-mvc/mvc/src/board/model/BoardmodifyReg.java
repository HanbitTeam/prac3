package board.model;

import java.io.IOException;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.control.Action;
import board.control.ActionForward;

public class BoardmodifyReg implements Action{

   @Override
   public void execute(ActionForward forward) {
      // TODO Auto-generated method stub
   
      
      BoardDTO dto = new BoardDTO();
      

      dto.setId(Integer.parseInt(forward.getRequest().getParameter("id")));
      dto.setTitle(forward.getRequest().getParameter("title"));
       dto.setPname(forward.getRequest().getParameter("pname"));
       dto.setPw(forward.getRequest().getParameter("pw"));
       dto.setContent(forward.getRequest().getParameter("content"));
   
       
      
      forward.setMsg("암호가 일치하지 않습니다.");
      forward.setGoUrl("modifyForm?id="+dto.getId());
      
      if(new BoardDAO(forward).boardmodify(dto))
      {
         forward.setMsg("수정되었습니다.");
         forward.setGoUrl("detail?id="+dto.getId());
         
      }
   
        forward.setUrl("alert");


      

   }
}