package board.model;

import board.control.Action;
import board.control.ActionForward;

public class Boardlist implements Action{

	@Override
	public void execute(ActionForward forward) {
		// TODO Auto-generated method stub
		
		forward.setStart((forward.getNowPage()-1)*forward.getLimit());
		
		forward.setEnd(forward.getNowPage()*forward.getLimit());
		
		Object res =new BoardDAO(forward).boardList();
		
		
		
		forward.setMainData(res);

	}
}
