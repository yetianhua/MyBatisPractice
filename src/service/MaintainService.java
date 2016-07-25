package service;

import java.util.ArrayList;
import java.util.List;

import dao.MessageDao;

public class MaintainService {
	public boolean deleteOne(String id) {
		boolean result = false;
		Integer idNum = null;
		try {
			idNum = Integer.valueOf(id);
			result = new MessageDao().deleteOne(idNum);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void deleteBatch(String[] ids) {
		List<Integer> idList = new ArrayList<Integer>();
		for (int i = 0; i < ids.length; i++) {
			Integer item = null;
			try {
				item = Integer.valueOf(ids[i]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			if (item != null) {
				idList.add(item);
			}
		}
		new MessageDao().deleteBatch(idList);
	}
}
