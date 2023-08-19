package com.seong.rd.member;

public interface MemberMapper {
	public abstract int join(Member m);
	public abstract Member getMemberById(Member m);
	public abstract int withdrawal(Member m);
	public abstract int update(Member m);
	
}
