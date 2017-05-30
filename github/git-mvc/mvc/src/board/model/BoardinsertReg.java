package board.model;

import java.io.IOException;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.control.Action;
import board.control.ActionForward;

public class BoardinsertReg implements Action{

	@Override
	public void execute(ActionForward forward) {
		// TODO Auto-generated method stub
		try {
		
			String up = "up";
			String path = forward.getRequest().getRealPath(up);
			path = "D:/webWork/mvc/WebContent/up";
			
			BoardDTO dto = new BoardDTO();
			
			MultipartRequest mr = 
					new MultipartRequest(
						forward.getRequest(),
						path,
						10*1024*1024,
						"utf-8",
						new DefaultFileRenamePolicy()
					);
			
			
			
			
			dto.setTitle(mr.getParameter("title"));
			dto.setPname(mr.getParameter("pname"));
			dto.setPw(mr.getParameter("pw"));
			dto.setContent(mr.getParameter("content"));
			dto.setUpfile(mr.getFilesystemName("upfile"));
			
			new BoardDAO(forward).insertReg(dto);
			
			forward.setRedirect(true);
			forward.setUrl("detail?id="+dto.getId());
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
