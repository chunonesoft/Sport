package com.chunsoft.bean;

public class MatchesBean {
	public String match_id; // 比赛ID
	public String match_time; // 比赛时间
	public Immediate_leagues_Bean league; // 联赛
	public Current_Match_Bean current_match; // 当前比赛信息
	public TeamBean team1; // 主队
	public TeamBean team2; // 客队
	public String status; // 比赛分钟数
	public String match_status_before_type_cast;
	public String match_describe; // 比分描述
	public String finished; // 是否结束
	public String begin;
	public String is_home_bigdata_recommend;
	public String is_home_yinglang_recommend;
	public String is_guest_bigdata_recommend;
	public String is_guest_yinglang_recommend;
}