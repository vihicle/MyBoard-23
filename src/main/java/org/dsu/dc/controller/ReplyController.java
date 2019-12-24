package org.dsu.dc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dsu.dc.domain.Criteria;
import org.dsu.dc.domain.PageDTO;
import org.dsu.dc.domain.ReplyVO;
import org.dsu.dc.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/replies/*")
@RestController
@Slf4j
public class ReplyController {
	@Autowired
	private ReplyService service;
	
	@PostMapping(value="/new", consumes="application/json",
				produces= { MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> 
			create(@RequestBody ReplyVO vo) throws Exception{
		log.info("[Reply] ReplyVO ");
		int insertCount = service.register(vo);
		
		log.info("[Reply] Reply INSERT COUNT : {}", insertCount);
		
		return insertCount == 1 ?
				new ResponseEntity<>("success", HttpStatus.OK)
			  : new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/pages/{bno}/{page}",
			produces= { MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<Map<String, Object>> getList(
			@PathVariable("page") int page, @PathVariable("bno") Long bno)
						throws Exception{
		log.info("[Reply] GetList.....");
		
		Criteria cri = new Criteria(page, 10);
		log.info("[Reply] Criteria : {}", cri);
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<ReplyVO> list = service.getList(cri, bno);
		map.put("list", list);
		
		int total = service.getTotal(bno);
		PageDTO pageMaker = new PageDTO(cri, total);
		
		map.put("pageMaker", pageMaker);
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(method= {RequestMethod.PUT, RequestMethod.PATCH},
					value="/{rno}", consumes="application/json",
					produces= { MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(
			@RequestBody ReplyVO vo, @PathVariable("rno") Long rno) throws Exception{
		vo.setRno(rno);
		log.info("[Reply] RNO : {} , Modify {}", rno ,vo);
		return service.modify(vo) == 1 ?
				new ResponseEntity<>("success", HttpStatus.OK)
			:	new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
			
	
	@GetMapping(value="/{rno}",
				produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno)
			throws Exception{
		log.info("[Reply] Get... : {} ", rno);
		
		return new ResponseEntity<>(service.get(rno), HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{rno}",
			produces= {MediaType.TEXT_PLAIN_VALUE} )
	
	
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno)
			throws Exception{
		log.info("[Reply] Remove... : {} ", rno);
		
		return service.remove(rno) == 1 ?
					new ResponseEntity<>("success", HttpStatus.OK)
				:	new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
