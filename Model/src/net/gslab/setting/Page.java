/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.gslab.setting;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.gslab.entity.User;

public class Page<T> implements Serializable {
	private static final long serialVersionUID = -3930180379790344299L;

	/** 默认每页记录�?*/
	private static final int DEFAULT_PAGE_SIZE = 20;

	/** 每页记录�?*/
	private int pageSize = DEFAULT_PAGE_SIZE;
	private long start;//当页第一条数据在list中的位置
	private List data;
	private long totalCount;

	public Page(long start,long totalSize,int pageSize,List data){
		this.start = start;
		this.totalCount = totalSize;
		this.pageSize = pageSize;
		this.data = data;
	}
	
	public Page() {
		// TODO Auto-generated constructor stub
		this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList());
	}
	
	public long getTotalPageCount(){
		if(totalCount%pageSize==0){
			return totalCount/pageSize;
		}else{
			return totalCount/pageSize+1;
		}
	}

	public long getCurrentPageNo(){
		return start/pageSize+1;
	}

	public boolean isHasNextPage(){
		return this.getCurrentPageNo()<this.getTotalPageCount();
	}
	
	public boolean isHasPreviousPage(){
		return this.getCurrentPageNo()>1;
	}
	
	protected static int getStartOfPage(int pageNo){
		return getStartOfPage(pageNo,DEFAULT_PAGE_SIZE);
	}
	

	public static int getStartOfPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return (pageNo-1)*pageSize;

	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}



}