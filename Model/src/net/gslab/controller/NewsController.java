package net.gslab.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.gslab.entity.News;
import net.gslab.entity.User;
import net.gslab.service.NewsService;
import net.gslab.setting.CommonConstant;
import net.gslab.setting.Page;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller(value="newsController")
@RequestMapping("/news")
public class NewsController extends BaseController{

	@Resource(name="newsServiceImpl")
	private NewsService newsService;
	
	
	//添加新闻
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

       
    //删除新闻,前台传入ID，根据ID删除新闻，删除后，检验是否删除成功
   	@RequestMapping("/deleteByID")
   	public void delNews(int id){
   		System.out.println("in the NewsController");
   		//News news = new News();  		
   		newsService.delete(id) ;
   	}
   	
  //返回全部新闻 ，返回json串
   	@RequestMapping(value="/newsList") 
	public  @ResponseBody List<News> show(Model model)  //后台往前台传输数据时用model
	{
		List<News> news=newsService.listNews();
		model.addAttribute("news",news);   //等效于request和respond
		return news;
	}
	

	//根据新闻id，获得数据库中的新闻
	@RequestMapping(value ="/getByID",method = RequestMethod.GET)
	public @ResponseBody News getByID(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="id")Integer id)//id是指新闻id，
	
	{
		System.out.println(id);
		return newsService.find(id);
	}
	
/*
	@RequestMapping(value = "/getPage", method = RequestMethod.GET)  //例子，刘定顺用这个测试page功能
	public @ResponseBody Page<News>  list(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="pg")Integer pg) { //pg,是指第几页
		System.out.println(pg);
		return newsService.getPage(pg,tableIndex);
	}
*/
	
/*
	@RequestMapping("/get9news")
	public @ResponseBody Page<News>  list_9news(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="pg")Integer pg) 
			//pg:参数，前台有这个参数，是指第几页；@RequestParam：必须有的参数
	{
		System.out.println(pg);
		return newsService.getPage(pg,tableIndex); //tableIndex，指明是哪个数据表
	}
*/
	
}
