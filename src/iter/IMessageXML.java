package iter;

import java.util.List;
import java.util.Map;

import bean.Message;

public interface IMessageXML {
	public List<Message> queryMessageList(Message message);
	public List<Message> queryMessageListByPage(Map param);
	public int count();
}
