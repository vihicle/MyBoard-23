package org.dsu.dc.service;

import java.util.List;

import org.dsu.dc.domain.BoardVO;

public interface BoardService {
	List<BoardVO> list() throws Exception;
	
	void register(BoardVO board);
	BoardVO get(Long bno);
	boolean modify(BoardVO board);
	boolean remove(Long bno);
}
