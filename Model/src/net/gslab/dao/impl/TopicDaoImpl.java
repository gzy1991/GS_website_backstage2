package net.gslab.dao.impl;

import org.springframework.stereotype.Repository;

import net.gslab.dao.TopicDao;
import net.gslab.entity.Topic;
import net.gslab.setting.Page;

@Repository(value="topicDaoImpl")
public class TopicDaoImpl extends BaseDaoImpl<Topic> implements TopicDao {
	private final String GET_BOARD_DIGEST_TOPICS = "from Topic t where t.boardId=?";
	private final String GET_PAGED_TOPICS = "from Topic where boardId=?";
	private final String QUERY_TOPIC_BY_TITLE = "from Topic where topicTitle like?";

	//获取某一页精活主题帖
	/* (non-Javadoc)
	 * @see net.gslab.dao.impl.TopicDao#getBoardDigestTopics(int, int, int)
	 */
	@Override
	public Page getBoardDigestTopics(int boardId,int pageNo,int pageSize){
		return pagedQuery(GET_BOARD_DIGEST_TOPICS,pageNo,pageSize,boardId);
	}
	
	/* (non-Javadoc)
	 * @see net.gslab.dao.impl.TopicDao#getPagedTopics(int, int, int)
	 */
	@Override
	public Page getPagedTopics(int boardId,int pageNo,int pageSize){
		return pagedQuery(GET_PAGED_TOPICS,pageNo,pageSize,boardId);
	}
	
	//获取和主题页标题模糊匹配的主题页
	/* (non-Javadoc)
	 * @see net.gslab.dao.impl.TopicDao#queryTopicByTitle(java.lang.String, int, int)
	 */
	@Override
	public Page queryTopicByTitle(String title,int pageNo,int pageSize){
		return pagedQuery(QUERY_TOPIC_BY_TITLE,pageNo,pageSize);
	}
}
