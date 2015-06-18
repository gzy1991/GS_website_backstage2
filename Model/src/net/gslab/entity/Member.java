package net.gslab.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;


@Entity           //指明这是数据库实体
@Table(name="t_member")  //对应数据库的表t_member
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)            //设置cache缓存

public class Member extends BaseDomain {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int memberId;
	
	private String studentId;
	private String memberName;
	
	private String account;//登陆账户
	private String password;

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
