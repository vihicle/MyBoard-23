package org.dsu.dc.service;

import java.util.List;

import org.dsu.dc.domain.Criteria;
import org.dsu.dc.domain.ReplyVO;

public interface ReplyService {
	int register(ReplyVO vo);
	ReplyVO get(Long rno);
	int remove(Long rno);
	int modify(ReplyVO vo);
	List<ReplyVO> getList(Criteria cri, Long bno);
	int getTotal(Long bno) throws Exception;
}
