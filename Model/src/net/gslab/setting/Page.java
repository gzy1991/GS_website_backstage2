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

	private List<T> data;
	private long totalCount;

	public Page(long totalSize,List data){
		this.totalCount = totalSize;
		this.data = data;
	}

	/**
	 * @return the data
	 */
	public List getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List data) {
		this.data = data;
	}

	/**
	 * @return the totalCount
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}