package org.dsu.dc.service;

import java.util.List;

import org.dsu.dc.domain.BoardVO;
import org.dsu.dc.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardMapper mapper;
	
	@Override
	public List<BoardVO> list() throws Exception {
		return mapper.getList();
	}

	@Override
	public void register(BoardVO board) {
		log.info("[Board Register...] {}", board);
		mapper.insertSelectKey(board);
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("[Board Get...] bno={}", bno);
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("[Board Modify...] {}", board);
		return mapper.update(board) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("[Board Remove...] bnp = {}", bno);
		return mapper.delete(bno) == 1;
	}

}
