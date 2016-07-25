package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Message;
import entity.Page;
import service.QueryService;

public class MessageListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		// 获取查询参数
		String cmd = req.getParameter("command");
		String desc = req.getParameter("description");
		String currentPage = req.getParameter("currentPage");
		if(currentPage == null){
			currentPage = "1";
		}
		Page page = new Page();
		page.setCurrentPage(Integer.valueOf(currentPage));
		//List<Message> messageList = new QueryService().queryMessageList(cmd, desc);
		List<Message> messageList = new QueryService().queryMessageListByPage(cmd, desc, page);
		
		req.setAttribute("messageList", messageList);
		req.setAttribute("page", page);
		req.getRequestDispatcher("/WEB-INF/jsps/back/list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
