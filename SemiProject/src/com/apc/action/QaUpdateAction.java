package com.apc.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apc.controller.Action;
import com.apc.controller.ActionForward;
import com.apc.model.QaDAO;
import com.apc.model.QaDTO;

public class QaUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//qa_content.jsp에서 수정버튼을 눌렀을 경우, 넘겨받은 번호에 해당하는 정보를 
		//DB에서 수정하여 VIEW PAGE로 보여주는 비즈니스 로직
		
		int num = Integer.parseInt(request.getParameter("num"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		QaDAO dao = QaDAO.getInstance();
		QaDTO dto = dao.getQaContent(num);
		
		request.setAttribute("cont", dto);
		request.setAttribute("page", page);
		
		ActionForward forward= new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("board/qa_update.jsp");
		return forward;
	}

}
