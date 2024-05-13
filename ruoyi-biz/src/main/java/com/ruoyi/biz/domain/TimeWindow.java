package com.ruoyi.biz.domain;

import java.math.BigInteger;

/**
 * TimeWindow
 *
 * @author libaozhong
 * @version 2024-03-27
 **/


public class TimeWindow {
	private  String timeWindow;

	public BigInteger getTotal() {
		return total;
	}

	public void setTotal(BigInteger total) {
		this.total = total;
	}

	private BigInteger total;

	public String getTimeWindow() {
		return timeWindow;
	}

	public void setTimeWindow(String timeWindow) {
		this.timeWindow = timeWindow;
	}

}
