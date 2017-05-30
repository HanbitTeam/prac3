package board.model;

import board.control.Action;
import board.control.ActionForward;

public class Boarddetail implements Action{

	@Override
	public void execute(ActionForward forward) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(forward.getRequest().getParameter("id"));
		BoardDAO dao = new BoardDAO(forward);
		dao.addCount(id);
		BoardDTO dto = dao.boardDetail(id);
		
		
		
		
		forward.setMainData(dto);

	}
}
