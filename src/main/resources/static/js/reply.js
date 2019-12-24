console.log("Reply Module...");

var replyService = (function(){
	
	function add(reply,callback,error){
		console.log("Add Reply.....");
		
		$.ajax({
			type : 'post',
		url : '/replies/new',
		data : JSON.stringify(reply),
		contentType : "application/json; charset=utf-8",
		success : function(result, status, xhr){
			if(callback){
				callback(result);
			}  
	
		},
		error : function(xhr, status, er){
			if(error){
				error(er);
			}
		}
		});
	}

	
	function getList(param, callback, error){
		var bno = param.bno;
		var page = param.page || 1;
		
		console.log("ReplyService : bno :" + bno + ", page:" + page);
		$.getJSON("/replies/pages/" + bno + "/" + page,
			function(data){
				if(callback){
					callback(data);
				}
			}).fail(function(xhr, status, er){
					if(error) {
						error();
					}
		});
	}

	
	function remove(rno,callback,error){

		$.ajax({
		ype : 'delete',
		url : 'replies/' + rno,
		success :function(result, status, xhr){
			if(callback){
				callback(result);
			}
		},
		error : function(xhr, status, er){
			if(error){
				error(er);
			}
		}
		});
	}
	
	
	function update(reply,callback,error){
		console.log("RNO: " + reply.rno);
		
		$.ajax({
			type : 'put',
		url : 'replies/' + reply.rno,
		data : JSON.stringify(reply),
		contentType : "application/json; charset=utf-8",
		success : function(result, status, xhr){
			if(callback){
				callback(result);
			}
		},
		error : function(xhr, status, er){
			if(error){
				error(er);
			}
		}
		});
	}

	function get(rno,callback,error){

		$.get("/replies" + rno,
			function(result){
			if(callback){
				callback(result);
			}
		}
		).fail(function(xhr, status, er){
			if(error){
				error();
			}
		});
	}
	
	return {
		add : add,
		getList : getList,
		remove : remove,
		update : update,
		get : get
	};
	
})();
	
	
	
	
	
	
