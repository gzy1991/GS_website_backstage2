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
    private int tableIndex=CommonConstant.NEWS;
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
	
	//删除新闻,前台传入ID，根据ID删除新闻，删除后，检验是否删除成功
	@RequestMapping("/deleteByID")
	public void delNews(int id){
		System.out.println("in the NewsController");
		//News news = new News();
		
		newsService.delete(id) ;
		
	}
	/*
	@RequestMapping(value="/newsUpdate") //修改新闻
	public String show(Model model)  //后台往前台传输数据时用model
	{
		List<News> news=newsService.listNews();
		model.addAttribute("news",news);   //等效于request和respnd
		return "showNews";
	}
	*/
	@RequestMapping(value="/newsList") //返回新闻 ，返回json串
	public  @ResponseBody List<News> show(Model model)  //后台往前台传输数据时用model
	{
		List<News> news=newsService.listNews();
		model.addAttribute("news",news);   //等效于request和respond
		return news;
	}
	/*
	@RequestMapping(value="/newsList2") //返回新闻，
	public String show2(Model model)  //后台往前台传输数据时用model
	{
		List<News> news=newsService.listNews();
		model.addAttribute("news",news);   //等效于request和respond
		return "showNews";
	}
	*/
	@RequestMapping(value = "/getPage", method = RequestMethod.GET)
	public @ResponseBody Page<News>  list(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="pg")Integer pg) {
		System.out.println(pg);
		return newsService.getPage(pg,tableIndex);
	}
}
