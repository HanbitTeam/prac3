package board.model;

import java.io.IOException;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.control.Action;
import board.control.ActionForward;

public class BoarddeleteReg implements Action{

   @Override
   public void execute(ActionForward forward) {
      // TODO Auto-generated method stub
   
         
         BoardDTO dto = new BoardDTO();
         dto.setId(Integer.parseInt(forward.getRequest().getParameter("id")));
         dto.setPw(forward.getRequest().getParameter("pw"));
               
         forward.setMsg("암호가 일치하지 않습니다.");
         forward.setGoUrl("deleteForm?id="+dto.getId());
         
         if(new BoardDAO(forward).boardDelete(dto))
         {
        	 forward.setMsg("삭제되었습니다.");
             forward.setGoUrl("list");
            
         }
      
           forward.setUrl("alert");
   

   }
}