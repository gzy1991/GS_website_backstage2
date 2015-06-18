package net.gslab.dao;

import net.gslab.setting.Page;

public interface TopicDao {

	//获取某一页精活主题帖
	public abstract Page getBoardDigestTopics(int boardId, int pageNo,
			int pageSize);

	public abstract Page getPagedTopics(int boardId, int pageNo, int pageSize);

	//获取和主题页标题模糊匹配的主题页
	public abstract Page queryTopicByTitle(String title, int pageNo,
			int pageSize);

}