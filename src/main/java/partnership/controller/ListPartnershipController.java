package partnership.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.vo.AdminVO;
import kr.or.ddit.vo.PartnershipVO;
import partnership.service.IPartnershipService;
import partnership.service.PartnershipServiceImpl;

@WebServlet("/partnership/list.do")
public class ListPartnershipController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IPartnershipService pashService = PartnershipServiceImpl.getInstance();
		List<PartnershipVO> pashList = pashService.allListPartnership();
		req.setAttribute("pashList", pashList);
		
		HttpSession session = req.getSession();
		AdminVO av = (AdminVO)session.getAttribute("loginCode");
		session.setAttribute("joinCode", "pashList");
		
		if(av != null) {
			req.getRequestDispatcher("/views/indexAdmin.jsp").forward(req, resp);
		}else {
			req.getRequestDispatcher("/views/indexMain.jsp").forward(req, resp);
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
