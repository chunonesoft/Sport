package com.chunsoft.bean;

public class MatchRecommend {
	private int match_id;
	private String recommend_type_des;
	private String match_des;
	private String match_time;

	public String getMatch_time() {
		return match_time;
	}

	public void setMatch_time(String match_time) {
		this.match_time = match_time;
	}

	private String league_name;
	private String data_time;
	private String begin; // 盘口

	public int getMatch_id() {
		return match_id;
	}

	public void setMatch_id(int match_id) {
		this.match_id = match_id;
	}

	public String getRecommend_type_des() {
		return recommend_type_des;
	}

	public void setRecommend_type_des(String recommend_type_des) {
		this.recommend_type_des = recommend_type_des;
	}

	public String getMatch_des() {
		return match_des;
	}

	public void setMatch_des(String match_des) {
		this.match_des = match_des;
	}

	public String getLeague_name() {
		return league_name;
	}

	public void setLeague_name(String league_name) {
		this.league_name = league_name;
	}

	public String getData_time() {
		return data_time;
	}

	public void setData_time(String data_time) {
		this.data_time = data_time;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

}
