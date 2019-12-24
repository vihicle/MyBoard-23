package org.dsu.dc.service;

import java.util.List;

import org.dsu.dc.domain.BoardVO;
import org.dsu.dc.domain.Criteria;

public interface BoardService {
	List<BoardVO> list() throws Exception;
	List<BoardVO> getList(Criteria cri);
	
	void register(BoardVO board);
	BoardVO get(Long bno);
	boolean modify(BoardVO board);
	boolean remove(Long bno);
	
	public int getTotal(Criteria cri);
	int getTotal(Long bno) throws Exception;
}
