package board.model;

import board.control.Action;
import board.control.ActionForward;

public class BoardreplyForm implements Action{

   @Override
   public void execute(ActionForward forward) {
      // TODO Auto-generated method stub
      int id =Integer.parseInt(forward.getRequest().getParameter("id"));
      BoardDAO dao = new BoardDAO(forward);
      BoardDTO dto = dao.boardDetail(id);
         
      //dto.setTitle("[Re]"+dto.getTitle());
      
      forward.setMainData(dto);

   }
}