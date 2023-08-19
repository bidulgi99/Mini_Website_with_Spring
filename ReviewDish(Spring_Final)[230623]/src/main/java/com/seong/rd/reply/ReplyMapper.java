package com.seong.rd.reply;

import java.util.List;

import com.seong.rd.sns.SNSMsg;

public interface ReplyMapper {
	public abstract int writeReply(Reply r);
	public abstract List<Reply> getReply(SNSMsg s);
	public abstract int update(Reply r);
	public abstract int delete(Reply r);
 }
