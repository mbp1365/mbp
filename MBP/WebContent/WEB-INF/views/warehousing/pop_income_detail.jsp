<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
	<title>상세조회</title>
	
	<style>
	    .grid-cell-red{
	        background: #f8d2cb;
	    }
	    .grid-cell-blue{
	        background: #dcf0f8;
	    }
	</style>
	<!-- 공통 css -->
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet"/>
	<link rel="stylesheet" href="<c:url value="/css/reset.css" />"/>
	<link rel="stylesheet" href="<c:url value="/css/style.css" />" />
	<link rel="stylesheet" href="<c:url value="/css/theme.css" />"/>
	
	<!-- font-awesome -->
	<link rel="stylesheet" type="text/css" href="https://cdn.rawgit.com/ax5ui/ax5ui-grid/master/dist/ax5grid.css">
	<link rel="stylesheet" href="<c:url value="/plugin/font-awesome/font-awesome-4.7.0/css/font-awesome.css" />" >
	<link rel="stylesheet" type="text/css" href="https://cdn.rawgit.com/ax5ui/ax5ui-toast/master/dist/ax5toast.css" />
	
	<link rel="stylesheet" type="text/css" href="https://cdn.rawgit.com/ax5ui/ax5ui-dialog/master/dist/ax5dialog.css">
	<!--[if IE]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
	<!--<script src="http://code.jquery.com/jquery-1.7.2.min.js "></script>-->
	
	
	<!-- <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.3.min.js"></script> -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="https://cdn.rawgit.com/ax5ui/ax5core/master/dist/ax5core.min.js"></script>
	<script type="text/javascript" src="https://cdn.rawgit.com/ax5ui/ax5ui-grid/master/dist/ax5grid.min.js"></script>
	<script type="text/javascript"	src="https://cdn.rawgit.com/ax5ui/ax5ui-toast/master/dist/ax5toast.min.js"></script>
	<script src="<c:url value="/js/util.ajax.js" />"></script>
	<script src="<c:url value="/js/valid/jquery.validate.min.js" />" type="text/javascript"></script>
	<script src="<c:url value="/js/mask/jquery.mask.min.js" />" type="text/javascript"></script>
	<!-- bootbox.js at 4.4.0 -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script src="https://rawgit.com/makeusabrew/bootbox/f3a04a57877cab071738de558581fbc91812dce9/bootbox.js"></script>
	    <script src="<c:url value="/js/jquery.bpopup.js" />"></script>
	<!-- 공통 js -->
	<script src="<c:url value="/js/custom.js" />"></script>
	<script type="text/javascript" src="https://cdn.rawgit.com/ax5ui/ax5ui-dialog/master/dist/ax5dialog.js"></script>
	<script src="https://cdn.rawgit.com/thomasJang/jquery-direct/master/dist/jquery-direct.min.js"></script>
</head>
<body class="bgc_light_grey">
			
	<div class="wrap main">
		
		<div class="main_content"> 
		
			<div class="main_title">
					<h2></h2>
			</div>
			<div class="main_title">
					<h2>입고 상세조회</h2>
			</div>
			
			
			<div class="content_box">
				<div class="table_box">
					<table class="set combined_td">
						<tbody>
						
							<tr> 
								<th rowspan="4">제품 기본정보</th>
								<td>
									<label>• RFID 태그</label>
									<label>- A0000B0000C00D00001</label>
								</td>
								<td> 
									<label>• 제품분류</label>
									<label>- 신발</label>
									<label>- 샌들</label>
								</td>
							</tr>
							<tr> 
								<td> 
									<label>• 제품번호 </label>
									<label>- AB12345CD</label>
								</td>
								<td> 
									<label>• 제품명</label>
									<label>- 소가죽 샌들</label>
								</td>
							</tr>
							<tr> 
								<td> 
									<label>• 작업분류</label>
									<label>- 2D</label>
								</td>
								<td> 
									<label>• 촬영 컷 개수</label>
									<label>- 5</label>
								</td>
							</tr>
							<tr> 
								<td> 
									<label>• 등록일시</label>
									<label>
										- 
										<span>2017-10-01</span>
										<span> 14:10:20</span>
									</label>
								</td>
								<td> 
									<label>• 등록자</label>
									<label>- 홍길동</label>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				
				<div class="submit_btn_box" style="margin-top:5px;margin-bottom:0;"> 
					<button class="save" type="submit">저장</button>
				</div>
				
			</div>
			
			<div class="content_box">		
				<div class="table_box">
					<table class="set">
						<tbody> 
							<tr>
								<th>* 입고여부</th>
								<td>
									<input type="radio" name="boolean_check_1" value="yes">예
									<input type="radio" name="boolean_check_1" value="no">아니오
								</td>
							</tr>
							<tr>
								<th>제품사진</th>
								<td>
								
									<div class="td_img_container">
										<div class="img_box product_img">	
											<img class="" src="<c:url value="/images/sample.jpg"/>" alt="" />
											<div class="td_btn_box">
												<button type="button" class="td_btn td_btn_cancel"></button>
											</div>
										</div>
									</div>
										<div class="file_box"> 
											<label for="">• 파일명 - AB12345CD</label>
											<input type="text" readonly/>
											<button type="button">파일 찾기</button>
										</div>
									
								</td>
							</tr>
							<tr>
								<th>검수사진</th>
								<td>
									<div class="td_img_container">
										<div class="img_box check_img">	
											<img class="" src="<c:url value="/images/sample.jpg"/>" alt="" />
											<div class="td_btn_box">
												<button type="button" class="td_btn td_btn_cancel"></button>
											</div>
										</div>
										<div class="img_box check_img">	
											<img class="" src="<c:url value="/images/sample.jpg"/>" alt="" />
											<div class="td_btn_box">
												<button type="button" class="td_btn td_btn_cancel"></button>
											</div>
										</div>
										<div class="img_box check_img">	
											<img class="" src="<c:url value="/images/sample.jpg"/>" alt="" />
											<div class="td_btn_box">
												<button type="button" class="td_btn td_btn_cancel"></button>
											</div>
										</div>
										<div class="img_box check_img">	
											<img class="" src="<c:url value="/images/sample.jpg"/>" alt="" />
											<div class="td_btn_box">
												<button type="button" class="td_btn td_btn_cancel"></button>
											</div>
										</div>
									</div>
								
									<div class="file_box"> 
										<label for="">• 파일명 - AB12345CD</label>
										<input type="text" readonly/>
										<button type="button">파일 찾기</button>
									</div>
									<div class="file_box"> 
										<label for="">• 파일명 - AB12345CD</label>
										<input type="text"readonly/>
										<button type="button">파일 찾기</button>
									</div>
									<div class="file_box"> 
										<label for="">• 파일명 - AB12345CD</label>
										<input type="text" readonly/>
										<button type="button">파일 찾기</button>
									</div>
									<div class="file_box">
										<label for="">• 파일명 - AB12345CD</label>
										<input type="text" readonly/>
										<button type="button">파일 찾기</button>
									</div>
									
								</td>
							</tr>
							<tr>
								<th>제품상태</th>
								<td>
									<input type="radio" name="faulty_check_1" value="normal">정상
									<input type="radio" name="faulty_check_1" value="faulty">불량
								</td>
							</tr>
							<tr>
								<th>불량사유</th>
								<td>
									<div class="note_box"> 
										<input type="text" />
									</div>
								</td>
							</tr>
							<tr>
								<th>스팀여부</th>
								<td>
									<input type="radio" name="boolean_check_2" value="yes">예
									<input type="radio" name="boolean_check_2" value="no">아니오
								</td>
							</tr>
							<tr>
								<th>실측사이즈 측정</th>
								<td>
									<div class="note_box"> 
										<input type="text" />
									</div>
								</td>
							</tr>
							<tr>
								<th>케어텍</th>
								<td>
									<div class="td_img_container">
										<div class="img_box caretag_img">	
											<img class="" src="<c:url value="/images/care_tag.png"/>" alt="" />
											<div class="td_btn_box">
												<button type="button" class="td_btn td_btn_down"></button>
												<button type="button" class="td_btn td_btn_cancel"></button>
											</div>
										</div>
										<div class="img_box caretag_img">	
											<img class="" src="<c:url value="/images/care_tag2.png"/>" alt="" />
											<div class="td_btn_box">
												<button type="button" class="td_btn td_btn_down"></button>
												<button type="button" class="td_btn td_btn_cancel"></button>
											</div>
										</div>
									</div>
									
									<div class="file_box"> 
										<label for="">• 파일명 - AB12345CD</label>
										<input type="text" readonly/>
										<button type="button">파일 찾기</button>
									</div>
									<div class="file_box"> 
										<label for="">• 파일명 - AB12345CD</label>
										<input type="text"readonly/>
										<button type="button">파일 찾기</button>
									</div>
									
								</td>
							</tr>
							
						</tbody>
					</table><!-- set -->
				</div>
				<!-- table_box -->
			</div>	
			<!-- content_box -->
		</div>
		<!-- main_content -->
	</div>
	<!-- wrap main -->
	<script type="text/javascript"> 
		$(function(){
		
			// 저장
			$(".save").on("click",function(){
		
				/*
					부모화면으로 일자 / 시간 리턴 해야 함
					
				*/
				
				//닫기
				 close();
			});
		
			// 창 닫기 함수
			function close(){
				parent.close();
				window.close();
				self.close();
			}
		});
	</script>
</body>
</html>

<script type="text/javascript"> 

	function tipsAlert(messageStr) {
		bootbox.alert({
			title: "경고",
		    message: messageStr,
		    size: 'small'
		});
	}
	
	var axToast  = new ax5.ui.toast({
	    containerPosition: "top-right",
	    onStateChanged: function(){
	        console.log(this);
	    }
	});
	
	var toastView = {
		viewMessage : function(message) {
			axToast.push(message, function () {
	            // close toast
	            console.log(this);
	        });
		}    		
	}
	
	
	//************************************************//
	//계약서 관리 처리 시작...
	//************************************************//
	var selectedProdId = "";
	var incomePopAct = {
    	search : function(prodId) {			
			var param =	{ 'prodId' : prodId};
			ajaxUtil.req("<c:url value='/warehousing/income/get'/>", param, 'incomePopAct.searchCallBack', false)
    	},
    	searchCallBack: function(result) {
    		console.log("result.success: "+result.success);    				
			if(result.success == "success"){
				var myJSON = JSON.stringify(result);
				console.log("result result.list: "+result.list);  
				console.log("result result.list: "+result.list.length);    					
				//gridView.setData(result.list);
				
			} else {
				//$('#mod_cctv').modal('hide');
			}
    	}, 
    	save : function() {
    		var hdUpdateYn = $( '#hdUpdateYn' ).val();

    		var txtCustId = $( '#txtCustId' ).val();
    		var sbBrandVal = $( '#sbBrandVal' ).val();
    		

    		if (jQuery.trim(txtCustId) == "") {
    			tipsAlert("고객사를 선택하세요."); 
	   			return;
	   		}
    		
    		if (jQuery.trim(sbBrandVal) == "") {
    			tipsAlert("브랜드명를 선택하세요.");
    			$( '#sbBrandVal' ).focus();
    			return;
    		}
    		
    		var param =	{ 'upFileId' : currUpTmpFileId, 'brandId' : sbBrandVal, 'custId' : txtCustId};

			var paramStr = JSON.stringify(param);
			console.log("param: "+paramStr)
			
    		ajaxUtil.req("<c:url value='/warehousing/income/save'/>", param, 'incomePopAct.addingCallBack', false);
    	},
    	addingCallBack: function(result) {
    		console.log("result.success: "+result.success);    				
    		if(result.success == "success"){
    			tipsAlert("저장 처리 되었습니다."); 
    			window.close();
    		}
    	}, 
    	del : function() {    		
    		var param =	{ 'upFileId' : currUpTmpFileId};
    		
    		ajaxUtil.req("<c:url value='/warehousing/producttmp/del'/>", param, 'incomePopAct.deleteCallBack', false);
    	},
    	deleteCallBack: function(result) {
    		console.log("result.success: "+result.success);    				
    		if(result.success == "success"){
    			window.close();
    		}
    	},
        popUploadingFile: function() {
        	// import csv파일 버튼 클릭 시 파일 찾기 모달창 띄위기.
       		$("#fileCsv").trigger("click");
       }
    }

	//************************************************//
	//제품 관리 처리 end...
	//************************************************//
	
	// 제품  리스트 저장 처리 confirm
    function delProudctConfirm() {
    	bootbox.confirm({
    		title: "알림창.",
    		message: "저장 하시겠습니까?",
    		buttons: {
    			cancel: {
    				label: '취소',
    				className: 'btn-danger'
    			},
    			confirm: {
    				label: '확인',
    				className: 'btn-success'
    			}
    		},
    		callback: function (result) {
    			if (result == true) {
    				incomePopAct.save();
    			}else {
    			}
    		}
    	});
    }
	
	// 제품  리스트 취소  처리 confirm
    function delProudctConfirm() {
    	bootbox.confirm({
    		title: "알림창.",
    		message: "취소 하시겠습니까?",
    		buttons: {
    			cancel: {
    				label: '취소',
    				className: 'btn-danger'
    			},
    			confirm: {
    				label: '확인',
    				className: 'btn-success'
    			}
    		},
    		callback: function (result) {
    			if (result == true) {
    				incomePopAct.del();
    			}else {
    			}
    		}
    	});
    }
	// 계약서 리스트 삭제 처리 confirm end....

	
    };
    
    var currUpTmpFileId;
	$(document.body).ready(function () {
		currProdId = "${prodId}"; 

     	incomePopAct.search(currProdId);
     	
       
       
    });
</script>