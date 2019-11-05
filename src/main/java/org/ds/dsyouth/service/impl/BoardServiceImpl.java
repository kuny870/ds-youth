package org.ds.dsyouth.service.impl;

import java.util.List;

import org.ds.dsyouth.mapper.BoardMapper;
import org.ds.dsyouth.model.BoardFree;
import org.ds.dsyouth.model.BoardOpinion;
import org.ds.dsyouth.model.LeaderInfo;
import org.ds.dsyouth.model.QT;
import org.ds.dsyouth.search.QTSearch;
import org.ds.dsyouth.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;

	
	@Override
	public List<BoardOpinion> getBoardOpinionList() {
		return boardMapper.selectBoardOpinionList();
	}


	@Override
	public boolean registBoardOpinion(BoardOpinion boardOpinion) {
		return boardMapper.insertBoardOpinion(boardOpinion);
	}


	@Override
	public boolean registBoardFree(BoardFree boardFree) {
		return boardMapper.insertBoardFree(boardFree);
	}


	@Override
	public List<BoardFree> getBoardFreeList() {
		return boardMapper.selectBoardFreeList();
	}


	@Override
	public boolean registLeaderInfo(LeaderInfo leaderInfo) {
		return boardMapper.insertLeaderInfo(leaderInfo);
	}


	@Override
	public List<LeaderInfo> getLeaderInfoList() {
		return boardMapper.selectLeaderInfoList();
	}


	@Override
	public LeaderInfo getLeaderInfo(String id) {
		return boardMapper.selectLeaderInfo(id);
	}


	@Override
	public boolean removeLeaderInfo(LeaderInfo leaderInfo) {
		return boardMapper.deleteLeaderInfo(leaderInfo);
	}


	@Override
	public QT getQT(QTSearch qtSearch) {
		return boardMapper.selectQT(qtSearch);
	}

}
