package com.bigbrainiac10.easyhelpop;

import java.sql.Timestamp;

public class HelpQuestion {

	private int entryID;
	public Timestamp ask_time;
	public String asker_uuid;
	private String question;
	public Timestamp replyTime;
	public String replier_uuid;
	public String reply;
	
	private boolean viewed;
	
	private Long reserved;
	
	public HelpQuestion(int entryID, Timestamp ask_time, String asker_uuid, String question, Timestamp replyTime, String replier_uuid, String reply, boolean viewed){
		this.entryID = entryID;
		this.ask_time = ask_time;
		this.asker_uuid = asker_uuid;
		this.question = question;
		this.replyTime = replyTime;
		this.replier_uuid = replier_uuid;
		this.reply = reply;
		this.viewed = viewed;
	}
	
	public HelpQuestion(int entryID, Timestamp ask_time, String asker_uuid, String question){
		this.entryID = entryID;
		this.ask_time = ask_time;
		this.asker_uuid = asker_uuid;
		this.question = question;
		this.replyTime = null;
		this.replier_uuid = null;
		this.reply = null;
		this.viewed = false;
	}
	
	public void setViewed(boolean viewed){
		this.viewed = viewed;
	}
	
	public boolean getViewed(){
		return this.viewed;
	}
	
	public String getQuestion(){
		return this.question;
	}
	
	public int getEntryID(){
		return this.entryID;
	}
	
	public synchronized boolean reserve() {
		if (this.reserved != null) {
			long now = System.currentTimeMillis();
			if (now - this.reserved < EHOConfigManager.getReservationTimeout()) {
				return false;
			}
		}// else
		this.reserved = System.currentTimeMillis();
		return true;
	}
	
	public boolean isReserved() {
		return this.reserved != null;
	}
	
	public void release() {
		this.reserved = null;
	}
}
