package com.chunsoft.bean;

public class MatchEvent {
	int match_id;
	String event_type;
	String event_type_des;
	String player_name1;
	String player_name2;
	int happen_time;
	String match_des;
	String home_or_guest;
	boolean is_home;
	String data_time;

	public int getMatch_id() {
		return match_id;
	}

	public void setMatch_id(int match_id) {
		this.match_id = match_id;
	}

	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}

	public String getEvent_type_des() {
		return event_type_des;
	}

	public void setEvent_type_des(String event_type_des) {
		this.event_type_des = event_type_des;
	}

	public String getPlayer_name1() {
		return player_name1;
	}

	public void setPlayer_name1(String player_name1) {
		this.player_name1 = player_name1;
	}

	public String getPlayer_name2() {
		return player_name2;
	}

	public void setPlayer_name2(String player_name2) {
		this.player_name2 = player_name2;
	}

	public int getHappen_time() {
		return happen_time;
	}

	public void setHappen_time(int happen_time) {
		this.happen_time = happen_time;
	}

	public String getMatch_des() {
		return match_des;
	}

	public void setMatch_des(String match_des) {
		this.match_des = match_des;
	}

	public String getHome_or_guest() {
		return home_or_guest;
	}

	public void setHome_or_guest(String home_or_guest) {
		this.home_or_guest = home_or_guest;
	}

	public boolean isIs_home() {
		return is_home;
	}

	public void setIs_home(boolean is_home) {
		this.is_home = is_home;
	}

	public String getData_time() {
		return data_time;
	}

	public void setData_time(String data_time) {
		this.data_time = data_time;
	}

}
