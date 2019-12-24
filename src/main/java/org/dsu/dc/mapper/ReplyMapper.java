package org.dsu.dc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dsu.dc.domain.Criteria;
import org.dsu.dc.domain.ReplyVO;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReplyMapper {
	int insert(ReplyVO vo);
	ReplyVO read(Long rno);
	int delete(Long rno);
	int update(ReplyVO vo);
	List<ReplyVO> getListWithPaging(
			@Param("cri") Criteria cri,
			@Param("bno") Long bno);
	
	int getTotalCount(@Param("bno") Long bno);
	
}
