package com.seong.rd.sns;

import java.util.List;

import com.seong.rd.member.Member;

public interface SNSMapper {
	public abstract int write(SNSMsg s);
	public abstract List<SNSMsg> get();
	public abstract int countAllMsg();
	public abstract int getMsgCount(PageStartEnd pse);
	public abstract int getUserMsgCount(Member m);
	public abstract List<SNSMsg> getList(PageStartEnd pse);
	public abstract int deleteMsg(SNSMsg s);
	public abstract int updateMsg(SNSMsg s);
}
