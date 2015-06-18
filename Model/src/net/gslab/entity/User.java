package net.gslab.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.gslab.entity.User.ClassType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

@Entity               //指明这是数据库实体
@Table(name = "t_user")  //对应数据库的表t_user
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)            //设置cache缓存
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String studentId;
	private String userName;
	private String password;

	public enum UserType {
		admin(0), member(1);
		private int value = 0;

		private UserType(int value) { // 必须是private的，否则编译错误
			this.value = value;
		}

		public static UserType valueOf(int value) { // 手写的从int到enum的转换函数
			switch (value) {
			case 0:
				return admin;
			case 1:
				return member;
			default:
				return null;
			}
		}

		public int value() {
			return this.value;
		}
	}

	public enum Locked {
		unlocked(0), locked(1);
		private int value = 0;

		private Locked(int value) { // 必须是private的，否则编译错误
			this.value = value;
		}

		public static Locked valueOf(int value) { // 手写的从int到enum的转换函数
			switch (value) {
			case 0:
				return unlocked;
			case 1:
				return locked;
			default:
				return null;
			}
		}

		public int value() {
			return this.value;
		}
	}

	public enum Category {
		FEP(0), XNN(1), VLAB(2), OME(3), ACA(4);
		private int value = 0;

		private Category(int value) { // 必须是private的，否则编译错误
			this.value = value;
		}

		public static Category valueOf(int value) { // 手写的从int到enum的转换函数
			switch (value) {
			case 0:
				return FEP;
			case 1:
				return XNN;
			case 2:
				return VLAB;
			case 3:
				return OME;
			case 4:
				return ACA;
			default:
				return null;
			}
		}

		public int value() {
			return this.value;
		}
		
	}

	public enum Gender {
		Male("0"), Female("1");
		private String value = " ";

		private Gender(String value) {
			this.value = value;
		}

		public static Gender get(String source){
			for(Gender g:values()){
				if(g.toString().equals(source))
					return g;
			}
			return null;
		}
		
		public String toString(){
			return this.value;
		}
	}

	public enum ClassType {
		Jap("0"), Eng("1"), Net("2");
		private final String value;

		private ClassType(String value) {
			this.value = value;
		}
      
	    public String toString() {  
	        return this.value;  
	    }  
	    
	    public static ClassType get(int v) {  
	        String str = String.valueOf(v);  
	        return get(str);  
	    }  
	  
	    public static ClassType get(String str) { 
	    	System.out.println("what's values"+values().toString());
	    	
	    	
	        for (ClassType e : values()) {  
	            if(e.toString().equals(str)) {  
	                return e;  
	            }  
	        }  
	        return null;  
	    }  
	
	}

	private String QQ;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	private UserType userType;
	private Locked locked;
	private int credit;
	private Category category;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	
	@Enumerated(EnumType.STRING)
	@Column(name="classType")
	private ClassType classType;
	private String grade;
	private int classNo;
	private String major;
	private String mobilePhone;
	private String email;
	
/*
	private String subTitle11;
	private String subTitle12;
	private String subTitle13;
	private String subTitle14;
	private String subTitle15;
	private String subTitle16;
	private String subTitle17;
	private String subTitle18;
	private String subTitle19;
	private String subTitle8;
	private String subTitle9;

	*/
	
	private String subTitle2;
	private String subTitle3;
	private String subTitle4;
	private String subTitle5;
	private String subTitle6;
	private String subTitle7;
	
	private String subTitle8;
	private String subTitle9;

	private Date lastVisit;
	private String lastIp;
	
	private String imgUrl;

	private String address;//
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNowAddress() {
		return nowAddress;
	}

	public void setNowAddress(String nowAddress) {
		this.nowAddress = nowAddress;
	}

	public String getLowAddress() {
		return lowAddress;
	}

	public void setLowAddress(String lowAddress) {
		this.lowAddress = lowAddress;
	}

	public String getHighClass() {
		return highClass;
	}

	public void setHighClass(String highClass) {
		this.highClass = highClass;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getChangeGroup() {
		return changeGroup;
	}

	public void setChangeGroup(String changeGroup) {
		this.changeGroup = changeGroup;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public void setLocked(Locked locked) {
		this.locked = locked;
	}

	private String nowAddress;
	private String lowAddress;//
	
	private String highClass;//
	
	private String condition;//

	
	private String changeGroup;//
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(int i) {
		System.out.println("userType.valueof(i)"+UserType.valueOf(i));
		this.userType = UserType.valueOf(i);
	}

	public Locked getLocked() {
		return locked;
	}

	public void setLocked(int i) {
		this.locked = Locked.valueOf(i);
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public ClassType getClassType() {
		return classType;
	}

	public void setClassType(ClassType classType) {
		this.classType = classType;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getClassNo() {
		return classNo;
	}

	public void setClassNo(int classNo) {
		this.classNo = classNo;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	

	public String getSubTitle2() {
		return subTitle2;
	}

	public void setSubTitle2(String subTitle2) {
		this.subTitle2 = subTitle2;
	}

	public String getSubTitle3() {
		return subTitle3;
	}

	public void setSubTitle3(String subTitle3) {
		this.subTitle3 = subTitle3;
	}

	public String getSubTitle4() {
		return subTitle4;
	}

	public void setSubTitle4(String subTitle4) {
		this.subTitle4 = subTitle4;
	}

	public String getSubTitle5() {
		return subTitle5;
	}

	public void setSubTitle5(String subTitle5) {
		this.subTitle5 = subTitle5;
	}

	public String getSubTitle6() {
		return subTitle6;
	}

	public void setSubTitle6(String subTitle6) {
		this.subTitle6 = subTitle6;
	}

	public String getSubTitle7() {
		return subTitle7;
	}

	public void setSubTitle7(String subTitle7) {
		this.subTitle7 = subTitle7;
	}

	public String getSubTitle8() {
		return subTitle8;
	}

	public void setSubTitle8(String subTitle8) {
		this.subTitle8 = subTitle8;
	}

	public String getSubTitle9() {
		return subTitle9;
	}

	public void setSubTitle9(String subTitle9) {
		this.subTitle9 = subTitle9;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setLastVisit(Date date) {
		// TODO Auto-generated method stub
		this.lastVisit = date;

	}

	public void setLastIp(String remoteAddr) {
		// TODO Auto-generated method stub
		this.lastIp = remoteAddr;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getQQ() {
		return QQ;
	}

	public void setQQ(String qQ) {
		QQ = qQ;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}

}
