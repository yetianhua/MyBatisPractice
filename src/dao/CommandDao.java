package dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import bean.Command;
import db.DBAccess;

public class CommandDao {
	public List<Command> queryCommandList(String name, String desc) {
		List<Command> commandList = new ArrayList<Command>();
		SqlSession sqlSession = null;
		try {
			sqlSession = new DBAccess().getSqlSession();
			Command command = new Command();
			command.setName(name);
			command.setDescription(desc);
			// SqlSession.selectList(String,Object);这个方法中，String是Message.xml中配置的sql，而Object是sql中需要传递的参数（需要传递多个参数时需要封装成类来传递）
			commandList = sqlSession.selectList("Command.queryCommandList", command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return commandList;
	}

	public boolean deleteOne(int id) {
		int result = 0;
		SqlSession sqlSession = null;
		try {
			sqlSession = new DBAccess().getSqlSession();
			result = sqlSession.delete("Command.deleteOne", id);
			sqlSession.commit();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		if (result == 0) {
			return false;
		} else {
			return true;
		}
	}

	public void deleteBatch(List<Integer> ids) {
		SqlSession sqlSession = null;
		try {
			sqlSession = new DBAccess().getSqlSession();
			sqlSession.delete("Command.deleteBatch", ids);
			sqlSession.commit();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
}
