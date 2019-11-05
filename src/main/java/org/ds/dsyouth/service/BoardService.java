package org.ds.dsyouth.service;

import java.util.List;

import org.ds.dsyouth.model.BoardFree;
import org.ds.dsyouth.model.BoardOpinion;
import org.ds.dsyouth.model.LeaderInfo;
import org.ds.dsyouth.model.QT;
import org.ds.dsyouth.search.QTSearch;

public interface BoardService {

	boolean registBoardOpinion(BoardOpinion boardOpinion);
	boolean registBoardFree(BoardFree boardFree);
	
	List<BoardOpinion> getBoardOpinionList();
	List<BoardFree> getBoardFreeList();
	
	boolean registLeaderInfo(LeaderInfo leaderInfo);
	boolean removeLeaderInfo(LeaderInfo leaderInfo);
	List<LeaderInfo> getLeaderInfoList();
	LeaderInfo getLeaderInfo(String id);
	
	QT getQT(QTSearch qtSearch);
	
}
