package dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import bean.Message;
import db.DBAccess;
import entity.Page;
import iter.IMessageXML;

public class MessageDao {
	/**
	 * JDBC
	 * 
	 * @param cmd
	 * @param desc
	 * @return
	 *//*
		 * public List<Message> queryMessageList(String cmd, String desc) {
		 * 
		 * Connection conn = null; List<Message> messageList = new
		 * ArrayList<Message>();
		 * 
		 * try { Class.forName("com.mysql.jdbc.Driver"); conn = (Connection)
		 * DriverManager.getConnection(
		 * "jdbc:mysql://127.0.0.1:3306/message?user=root&password=3626506&useUnicode=true&characterEncoding=UTF8"
		 * ); StringBuffer sql = new StringBuffer(
		 * "select id,command,description,content from message where 1 = 1"); if
		 * (cmd != null && cmd.trim().length() > 0) { sql.append(
		 * " and command like '%" + cmd + "%'"); } if (desc != null &&
		 * desc.trim().length() > 0) { sql.append(" and description like '%" +
		 * desc + "%'"); } PreparedStatement statement =
		 * conn.prepareStatement(sql.toString()); ResultSet resultSet =
		 * statement.executeQuery();
		 * 
		 * while (resultSet.next()) { Long id = resultSet.getLong("id"); String
		 * command = resultSet.getString("command"); String description =
		 * resultSet.getString("description"); String content =
		 * resultSet.getString("content"); Message message = new Message(id,
		 * command, description, content); messageList.add(message); } } catch
		 * (ClassNotFoundException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } return messageList;
		 * }
		 */

	public List<Message> queryMessageList(String cmd, String desc) {
		List<Message> messageList = new ArrayList<Message>();
		SqlSession sqlSession = null;
		try {
			sqlSession = new DBAccess().getSqlSession();
			Message message = new Message();
			message.setCommand(cmd);
			message.setDescription(desc);
			// SqlSession.selectList(String,Object);这个方法中，String是Message.xml中配置的sql，而Object是sql中需要传递的参数（需要传递多个参数时需要封装成类来传递）
			// messageList = sqlSession.selectList("Message.queryMessageList",
			// message);
			IMessageXML xml = sqlSession.getMapper(IMessageXML.class);
			messageList = xml.queryMessageList(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return messageList;
	}

	/**
	 * 根据当前页数查询
	 * @param cmd
	 * @param desc
	 * @param page
	 * @return
	 */
	public List<Message> queryMessageListByPage(String cmd, String desc, Page page) {
		List<Message> messageList = new ArrayList<Message>();
		SqlSession sqlSession = null;
		try {
			sqlSession = new DBAccess().getSqlSession();
			Message message = new Message();
			message.setCommand(cmd);
			message.setDescription(desc);
			Map param = new HashMap();
			param.put("message", message);
			param.put("page", page);
			IMessageXML xml = sqlSession.getMapper(IMessageXML.class);
			messageList = xml.queryMessageListByPage(param);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return messageList;
	}

	public boolean deleteOne(int id) {
		int result = 0;
		SqlSession sqlSession = null;
		try {
			sqlSession = new DBAccess().getSqlSession();
			result = sqlSession.delete("deleteOne", id);
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
			sqlSession.delete("deleteBatch", ids);
			sqlSession.commit();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	/**
	 * 计算Message表的总数
	 * @return
	 */
	public int count() {
		int result = 0;
		SqlSession sqlSession = null;
		try {
			sqlSession = new DBAccess().getSqlSession();
			IMessageXML messageXML = sqlSession.getMapper(IMessageXML.class);
			result = messageXML.count();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
