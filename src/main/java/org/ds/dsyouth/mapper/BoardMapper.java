package org.ds.dsyouth.mapper;

import java.util.List;

import org.ds.dsyouth.model.BoardFree;
import org.ds.dsyouth.model.BoardOpinion;
import org.ds.dsyouth.model.LeaderInfo;
import org.ds.dsyouth.model.QT;
import org.ds.dsyouth.search.QTSearch;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardMapper {

	boolean insertBoardOpinion(BoardOpinion boardOpinion);
	boolean insertBoardFree(BoardFree boardFree);
	
	List<BoardOpinion> selectBoardOpinionList();
	List<BoardFree> selectBoardFreeList();
	
	boolean insertLeaderInfo(LeaderInfo leaderInfo);
	boolean deleteLeaderInfo(LeaderInfo leaderInfo);
	List<LeaderInfo> selectLeaderInfoList();
	LeaderInfo selectLeaderInfo(String id);
	
	// qt
	QT selectQT(QTSearch qtSearch);
	
}
