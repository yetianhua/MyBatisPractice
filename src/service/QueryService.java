package service;

import java.util.List;
import java.util.Random;

import bean.Command;
import bean.Message;
import dao.CommandDao;
import dao.MessageDao;
import entity.Page;
import util.Iconst;

public class QueryService {

	public List<Message> queryMessageListByPage(String cmd, String desc, Page page) {
		int totalNumber = new MessageDao().count();
		page.setTotalNumber(totalNumber);
		return new MessageDao().queryMessageListByPage(cmd, desc, page);
	}

	public List<Message> queryMessageList(String cmd, String desc) {
		return new MessageDao().queryMessageList(cmd, desc);
	}

	public String queryByCommand(String command) {

		if (Iconst.HELP_COMMAND.equals(command)) {
			StringBuilder result = new StringBuilder();
			List<Command> list = new CommandDao().queryCommandList(null, null);
			for (int i = 0; i < list.size(); i++) {
				if (i != 0) {
					result.append("<br>");
				}
				result.append("回复[" + list.get(i).getName() + "],可以查看" + list.get(i).getDescription());
			}
			return result.toString();

		}

		List<Command> list = new CommandDao().queryCommandList(command, null);
		if (list.size() > 0) {
			int randomInt = new Random().nextInt(list.get(0).getContentList().size());
			System.out.println(randomInt);
			return list.get(0).getContentList().get(randomInt).getContent();
		}
		return Iconst.NO_MATCHING_CONTENT;
	}
}
