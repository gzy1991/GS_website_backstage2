package net.gslab.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import net.gslab.entity.News;
import net.gslab.service.NewsService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value="newsController")
@RequestMapping("/news")
public class NewsController extends BaseController{

	@Resource(name="newsServiceImpl")
	private NewsService newsService;
	
	
	
	@RequestMapping("/add")
	public void addNews(){
		System.out.println("in the NewsController");
		News news = new News();
		Date date = new Date();//date.toString()输出格林威治时间
		System.out.println(date);
		String strDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		System.out.println(strDate);
		//news.setNewsId(1);
		news.setContent("first news test");
		news.setPublishDate(strDate);
		news.setPublishName("关振宇");
		//该外键不存在会报错：Cannot add or update a child row: a foreign key constraint fails (`model`.`t_news`, CONSTRAINT `newsPublisher` FOREIGN KEY (`publishName`) REFERENCES `t_user` (`userName`))

		
		newsService.save(news);
	}
       @RequestMapping(value = "/getPage", method = RequestMethod.GET)
	public @ResponseBody Page<News>  list(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="pageIndex")Integer pageIndex) {
		/**
		 * @param pageIndex   请求的页码
         * @param pageSize   每页的记录条数
         * @param 
		 */
		//return newsService.getPage(pageIndex);  //使用默认的pageSize
		return newsService.getPage(pageIndex,2);  //自定义pageSize为2  
		//return newsService.getPage("from News e where e.newsName='me'",pageIndex); // 使用默认的pageSize
		//return newsService.getPage("from News e where e.newsName='me'",pageIndex,1);//自定义pageSize为1
	}

	
}
