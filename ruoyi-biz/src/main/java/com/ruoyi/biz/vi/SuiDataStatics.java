package com.ruoyi.biz.vi;

import java.io.Serializable;
import java.util.List;

import com.ruoyi.biz.domain.Rank;
import com.ruoyi.biz.domain.TimeWindow;
import com.ruoyi.biz.domain.TotalStatu;

/**
 * SuiDataStatics
 *
 * @author libaozhong
 * @version 2024-03-27
 **/

public class SuiDataStatics implements Serializable {

	private List<TimeWindow>  shredTxByDay;

	private List<TimeWindow>  shredMintByDay;

	private List<TimeWindow>  swordTxByDay;

	private List<TimeWindow>  swordMintByDay;

	private  List<TotalStatu> totalStatu;


	private List<Rank> rank;


	public List<TimeWindow> getShredTxByDay() {
		return shredTxByDay;
	}

	public void setShredTxByDay(List<TimeWindow> shredTxByDay) {
		this.shredTxByDay = shredTxByDay;
	}

	public List<TimeWindow> getShredMintByDay() {
		return shredMintByDay;
	}

	public void setShredMintByDay(List<TimeWindow> shredMintByDay) {
		this.shredMintByDay = shredMintByDay;
	}

	public List<TimeWindow> getSwordTxByDay() {
		return swordTxByDay;
	}

	public void setSwordTxByDay(List<TimeWindow> swordTxByDay) {
		this.swordTxByDay = swordTxByDay;
	}

	public List<TimeWindow> getSwordMintByDay() {
		return swordMintByDay;
	}

	public void setSwordMintByDay(List<TimeWindow> swordMintByDay) {
		this.swordMintByDay = swordMintByDay;
	}

	public List<TotalStatu> getTotalStatu() {
		return totalStatu;
	}

	public void setTotalStatu(List<TotalStatu> totalStatu) {
		this.totalStatu = totalStatu;
	}

	public List<Rank> getRank() {
		return rank;
	}

	public void setRank(List<Rank> rank) {
		this.rank = rank;
	}
}
