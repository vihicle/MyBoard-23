package org.dsu.dc;

import java.util.List;

import org.dsu.dc.domain.BoardVO;
import org.dsu.dc.domain.Criteria;
import org.dsu.dc.service.BoardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MyBoardApplicationTests {
	@Autowired
	BoardService service;
	
	@Test
	public void contextLoads() {
		List<BoardVO> list = service.getList(new Criteria(2, 10));
		list.forEach(board->log.info("Paging : {}", board));
	}

}
