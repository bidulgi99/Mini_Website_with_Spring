package com.seong.rd.dataroom;

import java.util.List;

public interface DataroomMapper {
	public abstract int upload(Dataroom d);
	public abstract List<Dataroom> getFileList();
	public abstract int deleteFile(Dataroom d);
}
