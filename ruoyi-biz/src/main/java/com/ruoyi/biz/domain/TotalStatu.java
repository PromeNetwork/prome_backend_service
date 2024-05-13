package com.ruoyi.biz.domain;

/**
 * TotalStatu
 *
 * @author libaozhong
 * @version 2024-03-27
 **/

public class TotalStatu {
	private String coinType;
	private Long create;

	private Long transfer;


	public String getCoinType() {
		return coinType;
	}

	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}

	public Long getCreate() {
		return create;
	}

	public void setCreate(Long create) {
		this.create = create;
	}

	public Long getTransfer() {
		return transfer;
	}

	public void setTransfer(Long transfer) {
		this.transfer = transfer;
	}
}
