package board.model;

import board.control.Action;
import board.control.ActionForward;

public class BoardreplyReg implements Action{

   @Override
   public void execute(ActionForward forward) {
      // TODO Auto-generated method stub
   
      
      BoardDTO dto = new BoardDTO();
      
      dto.setId(Integer.parseInt(forward.getRequest().getParameter("id")));
      dto.setTitle(forward.getRequest().getParameter("title"));
       dto.setPname(forward.getRequest().getParameter("pname"));
       dto.setPw(forward.getRequest().getParameter("pw"));
       dto.setContent(forward.getRequest().getParameter("content"));
   
       new BoardDAO(forward).boardreply(dto);
       forward.setMsg("작성되었습니다.");
      forward.setGoUrl("detail?id="+dto.getId());
      
      
   
        forward.setUrl("alert");


      

   }
}