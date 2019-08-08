<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
	<title>제품 관리</title>
	
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
	<link rel="stylesheet" href="../css/reset.css"/>
	<link rel="stylesheet" href="../css/style.css"/>
	<link rel="stylesheet" href="../css/theme.css"/>
	
	<!-- font-awesome -->
	<link rel="stylesheet" type="text/css" href="https://cdn.rawgit.com/ax5ui/ax5ui-grid/master/dist/ax5grid.css">
	<link rel="stylesheet" href="../plugin/font-awesome/font-awesome-4.7.0/css/font-awesome.css">
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
	<script src="../js/custom.js"></script>
	<script type="text/javascript" src="https://cdn.rawgit.com/ax5ui/ax5ui-dialog/master/dist/ax5dialog.js"></script>
	<script src="https://cdn.rawgit.com/thomasJang/jquery-direct/master/dist/jquery-direct.min.js"></script>
	
	<!-- upload js css -->
	<script src="../js/simpleUpload.js"></script>
</head>
<body>
	<div class="wrap"> 
	
		<header class="top_section"> 
			<jsp:include page="../header/top_navi.jsp" flush="false" />
		</header>
		<!-- top_section -->
		<div id="customer-pop-container" style="display:none">
          	<label for="">• 고객사명</label>
			<input type="text" id="txtSchCustNmOnPop"/>
			<button class="inquiry" type="button" style="background-color:#337ab7" onclick="popCustAct.search(); return false;">조회</button>	
            <!-- <div data-ax5grid="popUpGrid" data-ax5grid-config="{}" style="height: 1000px;"></div> -->
            <div class="table_box" style="width:100%;height:250px;">
				<table id="popCustTable" style="width:100%;overflow-x:auto">
					<thead> 
						<tr height=28> 
							<th style="vertical-align:middle;text-align:center" width=30>No</th>
							<th style="vertical-align:middle;text-align:center" width=100>고객사명</th>
							<th style="vertical-align:middle;text-align:center" width=100>담당자명</th>
							<th style="vertical-align:middle;text-algin:center" width=100>연락처</th>
						</tr>
					</thead>
					<tbody> 
					</tbody>
				</table>
			</div>
        </div>
		<div class="middle_section"> 
			<div class="middle_section_contents">
				<div class="main">
					
					<div class="main_content"> 
					
						<div class="main_title">
								<h2>제품 관리</h2>
						</div>
						<div class="content_box">
							<div class="submit_btn_box"> 
								<button type="button" class="save" onclick="productAct.add(); return false;">저장</button>
								<button type="button" class="cancel" onclick="productAct.cleanInit(); return false;">초기화</button>
								<button type="button" class="inquiry" onclick="productAct.popUploadingFile(); return false;">Excel Import</button>
								<form method="POST" action="<c:url value="warehousing/product/uploadProdFile" />" enctype="multipart/form-data"> 
									<input type="file" id="fileCsv"  style="display:none"/>
								</form>
								
								<form name="popForm"  method="post" action="" >
								    <input type="hidden" id="cmd" name="cmd" value="openPop" />
								    <input type="hidden" id="upFileId" name="upFileId" value="test23"/>
								</form>
	
							</div>
							<div class="table_box"> 
								<table class="set">
									<tbody> 
										<tr>
											<th>* 고객사명</th>
											<td width=35%>
												<div class="name_box"> 
													<input type="text"  readonly  id="txtCustNm"/> 
													<button type="button" id="btnFindCust" >찾기</button>
													<input type="text" id="txtCustId">
												</div>
											</td>
											<th>* 브랜드명</th>
											<td>
												<div class="name_box"> 
													<select name="" id="sbBrandVal">
													<!-- 고객사에 따라 브랜드 option 추가  -->													
														<option value="">전체</option>
													
													</select>
												</div>
											</td>
										</tr>
										<tr>
											<th>* RFID TAG</th>
											<td colspan="3">
												<input type="text" size=24 maxlength=24  id="txtRfidId"/>
												<input type="text" size=14 id="txtProdId"/>
												<input type="text" size=3 id="hdUpdateYn"/>
											</td>
										</tr>
										<tr>
											<th>* 제품분류</th>
											<td colspan="3">
												<div class="name_box"> 
												
													<!-- 대분류 -->
													<select name="" id="sbCateMsCd" onchange="productAct.changeCateMaster(); return false;">
														<c:choose>
															<c:when test="${!empty jobTypeCdList}">
																<c:forEach var="cateMasterList" items="${cateMasterList}">
																	<option value="${cateMasterList.cateId}">${cateMasterList.cateName}</option>
																</c:forEach>
															</c:when>
															<c:otherwise>
																<option value="">없음</option>
															</c:otherwise>
														</c:choose>
													</select>
													
													<!-- 소분류 -->
													<select name="" id="sbCateSlCd">
														<option value="선택">선택</option>
														<!-- 제품분류 option 추가  -->
													</select>
												</div>
											</td>
										</tr>
										<tr>
											<th>* 제품 번호</th>
											<td>
												<input type="text"  size=30 maxlength=50   id="txtProdNo"/>
											</td>
											<th>* 제품명</th>
											<td>
													<input type="text" size=66 maxlength=300  id="txtProdNm"/>
												
											</td>
										</tr>
										<tr>
											<th>* 작업분류</th>
											<td>
												<div class="name_box"> 
													<select name="" id="sbJobCd">
														<c:choose>
															<c:when test="${!empty jobTypeCdList}">
																<c:forEach var="jobTypeCdList" items="${jobTypeCdList}">
																	<option value="${jobTypeCdList.codeId}">${jobTypeCdList.codeName}</option>
																</c:forEach>
															</c:when>
															<c:otherwise>
																<option value="">없음</option>
															</c:otherwise>
														</c:choose>
														<!-- 제품분류 option 추가  -->
													</select>
												</div>
											</td>
										
											<th>촬영 컷 개수</th>
											<td>
												<div class="name_box"> 
													<input type="number" id="txtCutCnt" value="0"/>
												</div>
											</td>
											
										</tr>
									</tbody>
								</table>
							</div>							
						</div>
						<!-- content_box -->
					
					
					
						<div class="content_box">		

							<form class="srch_box_align_left"> 
								<label for="">• 제품명</label>
								<input type="text" id="txtSchProdNm"/>
							</form>
							
							<form class="srch_box_align_left"> 
								<label for="">• 등록일자</label>
								<input type="text" id="txtSchRegDt"/>
							</form>
							
							<div class="submit_btn_box"> 
								<button class="inquiry" type="button"  onclick="productAct.search(0); return false;">조회</button>
							</div>
							
							<div class="table_box">
								<div style="position: relative;height:420px;" id="grid-parent">
								    <div data-ax5grid="data-grid" data-ax5grid-config="{
								                    showLineNumber: true,
								                    showRowSelector: true,
								                    sortable: true,
								                    header: {align:'center'}
								                    }" style="height: 100%;">
									</div>
								</div>
							</div>
							<!-- table_box -->						
							
						</div>	
						<!-- content_box -->
						
						
					</div>
					<!-- main_content -->
				</div>
				<!-- main -->
			</div>
		</div>
		<!-- middle_section -->
		
	</div>
	<!-- wrap -->	
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
	// 고객사 조회 팝업 처리 로직
	//************************************************//
	var popCustAct = {
       	search : function() {
       		// 고객사명으로 조회
   			var custNm = $("#txtSchCustNmOnPop").val();
   			
   			console.log("custNm: "+custNm);   			
   			
   			var param =	{ 'custNm' : custNm, 'qCurrPage' : 0 };
   			ajaxUtil.req("<c:url value='/customer/customerPopList'/>", param, 'popCustAct.searchCallBack', false)
       	},
       	searchCallBack: function(result) {
            // 고객사 정보	테이블 생성.			
   			if(result.success == "success"){
   				$('#popCustTable > tbody > tr').remove();
   				//var myJSON = JSON.stringify(result.list);
   				for (var ii = 0; ii < result.list.length; ii++) {
   					var temp = result.list[ii];
   					console.log("item: "+JSON.stringify(temp));
   					var jsonStr = JSON.stringify(temp);
   					$('#popCustTable > tbody').append('<tr height=30 align=center valign=middel><td>'+(ii+1)+'</td><td><a href="#" onclick="popCustAct.selectedItem(\''+temp.custId+'\', \''+temp.custNm+'\');">'+temp.custNm+'</a></td><td align=center>'+temp.chrgNm+'</td><td align=center>'+temp.coctNo+'</td></tr>');
   				}
   				
   			} else {
   			}
       	},
       	selectedItem: function(id, name) {
       		// 고객사 선택 시 처리.
       		console.log('id: '+id);
       		console.log('name: '+name);
       		$('#txtCustNm').val(name);
       		$('#txtCustId').val(id);
       		customerPopBox.modal('hide'); 
       		popCustAct.searchBrand();
       	},
       	clearBrand : function() {
			$("#sbBrandVal option").remove();
			$("#sbBrandVal").append("<option value=''>전체</option>");
       	},
       	searchBrand : function() {
       		// 고객사 아이디로 브랜드 리스트 조회.
   			var custId = $("#txtCustId").val();
   			   			
   			var param =	{ 'custId' : custId };
   			
   			ajaxUtil.req("<c:url value='/brand/combobrandList'/>", param, 'popCustAct.searchComboCallBack', false)
       	},
       	searchComboCallBack: function(result) {
       		// 브랜드 리스트 조회 후 처리.
       		// 브랜드 selectBox에 옵션 추가 처리.  				
   			if(result.success == "success"){
   				$('#popCustTable > tbody > tr').remove();
   				//var myJSON = JSON.stringify(result.list);
   				$("#sbBrandVal option").remove();
   				$("#sbBrandVal").append("<option value=''>전체</option>");
   				
   				for (var ii = 0; ii < result.list.length; ii++) {
   					var temp = result.list[ii];
   					console.log("item: "+JSON.stringify(temp));
   	   				$("#sbBrandVal").append("<option value='"+temp.brandId+"'>"+temp.brandNm+"</option>");
   					//var jsonStr = JSON.stringify(temp);
   					//$('#popCustTable > tbody').append('<tr height=30 align=center valign=middel><td>'+(ii+1)+'</td><td><a href="#" onclick="popCustAct.selectedItem(\''+temp.custId+'\', \''+temp.custNm+'\');">'+temp.custNm+'</a></td><td align=center>'+temp.chrgNm+'</td><td align=center>'+temp.coctNo+'</td></tr>');
   				}
   				$("#sbBrandVal").val(selectedProdBrandId)
   				
   			} else {
   			}
       	}
    }
	//************************************************//
	//고객사 팝업 조회 처리 end...
	//************************************************//
	
	
	//************************************************//
	//계약서 관리 처리 시작...
	//************************************************//
	var selectedProdId = "";
	var selectedProdBrandId = "";
	var productAct = {
    	search : function(pageNo) {
			// 제품 조회 시 조회조건 제품명으로 처리 체크.
    		var prodNm = $("#txtSchProdNm").val();
			
			var param =	{ 'prodNm' : prodNm, 'qCurrPage' : pageNo };

			ajaxUtil.req("<c:url value='/warehousing/product/list'/>", param, 'productAct.searchCallBack', false)
    	},
    	searchCallBack: function(result) {
    		console.log("result.success: "+result.success);    				
			if(result.success == "success"){
				//var myJSON = JSON.stringify(result);
				//console.log("result myJSON: "+myJSON);    					
				gridView.setData(dataGridCurrPage, result.pageListSize, result.totalPage, result.totalCount, result.list);
				
			} else {
				//$('#mod_cctv').modal('hide');
			}
    	}, 
    	checkProductDupl: function() {

    		var txtEmailId = $( '#txtEmailId' ).val();
    		var txtEmailAddrNm = $( '#txtEmailAddrNm' ).val();

    		if (txtEmailId == "") {
    			tipsAlert("이메일 정보를 입력하세요."); 
    			$( '#txtEmailId' ).focus();
    			return;
    		}
    		
    		
			var param =	{ 'emailId' : txtEmailId, 'emailAddrNm' : txtEmailAddrNm };

			var myJSON = JSON.stringify(param);
			console.log("result myJSON: "+myJSON); 
			ajaxUtil.req("<c:url value='/warehousing/product/checkProductDupl'/>", param, 'productAct.checkProductDuplCallBack', false)
    	}, 
    	checkProductDuplCallBack: function(result) {

    		if(result.success == "success"){
				var myJSON = JSON.stringify(result);
				console.log("result myJSON: "+myJSON);    	
				if (result.checkCount > 0) {
	    			toastView.viewMessage("중복되는 이메일이 있습니다. 확인바랍니다."); 
	    			return;
				}else {
	    			toastView.viewMessage("사용 가능한 이메일주소입니다."); 
					$( '#hdEmailDuplYn' ).val("Y");
				}
				
			} else {
				//$('#mod_cctv').modal('hide');
			}
    	},
    	add : function() {
    		var hdUpdateYn = $( '#hdUpdateYn' ).val();

    		var txtProdId = $( '#txtProdId' ).val();
    		var txtCustId = $( '#txtCustId' ).val();
    		var txtRfidId = $( '#txtRfidId' ).val();
    		var sbBrandVal = $( '#sbBrandVal' ).val();
    		var sbCateMsCd = $( '#sbCateMsCd' ).val();
    		var sbCateSlCd = $( '#sbCateSlCd' ).val();
    		var txtProdNo = $( '#txtProdNo' ).val();
    		var txtProdNm = $( '#txtProdNm' ).val();
    		var sbJobCd = $( '#sbJobCd' ).val();
    		var txtCutCnt = $( '#txtCutCnt' ).val();
    		
    		console.log("hdUpdateYn: "+hdUpdateYn);

    		if (jQuery.trim(txtCustId) == "") {
    			tipsAlert("고객사를 선택하세요."); 
	   			return;
	   		}
    		
    		if (jQuery.trim(sbBrandVal) == "") {
    			tipsAlert("브랜드명를 선택하세요.");
    			$( '#sbBrandVal' ).focus();
    			return;
    		}
    		if (jQuery.trim(sbCateMsCd) == "") {
    			tipsAlert("제품분류를 선택하세요."); 
	   			return;
	   		}
    		if (jQuery.trim(sbCateSlCd) == "") {
    			tipsAlert("제품분류를 선택하세요."); 
	   			return;
	   		}
    		if (jQuery.trim(sbJobCd) == "") {
    			tipsAlert("작업분류를 선택하세요."); 
	   			return;
	   		}
    		if (jQuery.trim(txtProdNo) == "") {
    			tipsAlert("제품번호를 입력하세요."); 
	   			return;
	   		}
    		if (jQuery.trim(txtProdNm) == "") {
    			tipsAlert("제품명을 입력하세요."); 
	   			return;
	   		}
    		
    		var param =	{ 'prodId' : txtProdId, 'brandId' : sbBrandVal, 'rfidTag' : txtRfidId, 'majorCtgyCd' : sbCateMsCd, 'subCtgyCd' : sbCateSlCd, 'prodNo' : txtProdNo
						, 'prodNm' : txtProdNm, 'cutCnt': txtCutCnt, 'jobTypeCd': sbJobCd, 'updateYn': hdUpdateYn};

			var paramStr = JSON.stringify(param);
			console.log("param: "+paramStr)
			
    		ajaxUtil.req("<c:url value='/warehousing/product/add'/>", param, 'productAct.addingCallBack', false);
    	},    	
    	addingCallBack: function(result) {
    		console.log("result.success: "+result.success);    				
    		if(result.success == "success"){
    			productAct.cleanInit();
    			productAct.search();
    			
    		} else {
    			//$('#mod_cctv').modal('hide');
    		}
    	},
    	del : function() {
    		var param = {};
    		var index = 0;
    		for (ii = 0; ii < dataGrid.list.length; ii++) {
    			//console.log(ii);
    			var itemTemp = dataGrid.list[ii];
    			if (itemTemp.__selected__ == true) {
    				console.log(itemTemp);
    				param['productList['+index+'].prodId'] = itemTemp.prodId;
    				index++;
    			}
    		}
    		
    		ajaxUtil.req("<c:url value='/warehousing/product/del'/>", param, 'productAct.deleteCallBack', false);
    	},
    	deleteCallBack: function(result) {
    		console.log("result.success: "+result.success);    				
    		if(result.success == "success"){
    			toastView.viewMessage("삭제 처리 되었습니다."); 
    			productAct.cleanInit();
    			productAct.search();
    		} else {
    			//$('#mod_cctv').modal('hide');
    		}
    	},
    	cleanInit: function() {
    		var list = [];
    		$( '#hdUpdateYn' ).val("N");
    		$( '#txtProdId' ).val("");
    		$( '#txtRfidId' ).val("");
    		$( '#sbCateMsCd' ).val("");
    		$( '#sbCateSlCd' ).val("");
    		$( '#txtProdNo' ).val("");
    		$( '#txtProdNm' ).val("");
    		$( '#txtCutCnt' ).val("0");
    		$( '#sbJobCd' ).val("");
    		
    		
    		popCustAct.searchBrand();
    		
    		selectedProdId = "";
    		selectedProdBrandId = "";
    	},
    	clickItem: function(selectedItem) {
    		console.log("selectedItem: "+selectedItem);
    		var paramStr = JSON.stringify(selectedItem);
			console.log("param: "+paramStr)
    		$( '#hdUpdateYn' ).val("Y");
    		$( '#txtProdId' ).val(selectedItem.prodId);
    		$( '#txtRfidId' ).val(selectedItem.rfidTag);
    		$( '#sbCateMsCd' ).val(selectedItem.majorCtgyCd);
    		$( '#sbCateSlCd' ).val(selectedItem.subCtgyCd);
    		$( '#txtProdNo' ).val(selectedItem.prodNo);
    		$( '#txtProdNm' ).val(selectedItem.prodNm);
    		$( '#txtCutCnt' ).val(selectedItem.cutCnt);
    		$( '#sbJobCd' ).val(selectedItem.jobTypeCd);
    		$( '#txtCustId' ).val(selectedItem.custId);
    		$( '#txtCustNm' ).val(selectedItem.custNm);
    		/*
    		
    		private String rfidTag; //  'rfid_tag'
    		private String majorCtgyCd;   //'카테고리대'
    		private String majorCtgyNm;   //'카테고리대 명칭'
    		private String subCtgyCd;   //'카테고리소'
    		private String subCtgyNm;   //'카테고리소 명칭'
    		private String prodNo;  // 'prod_no'
    		private String prodNm;  // 'prod_nm'
    		private int cutCnt;  //  '촬영컷 수'
    		private String jobTypeCd;   // 작업분류코드 
    		private String jobTypeNm;   // 작업분류코드명 
    	    		*/
       		popCustAct.searchBrand();

       		
    		selectedProdId = selectedItem.prodId;
    		selectedProdBrandId = selectedItem.brandId;
    	},
    	changeCateMaster: function() {
    		var temp = $('#sbCateMsCd').val();
    		console.log('sbCateMsCd val: '+temp);

            $("#sbCateSlCd option").remove();
    		var categoryList = categoryMap[temp];
            for (var ii = 0; ii < categoryList.length; ii++) {
            	var temp = categoryList[ii];
            	$("#sbCateSlCd").append("<option value='"+temp.cateId+"'>"+temp.cateName+"</option>");
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
	
	
	
	// 제품  리스트 삭제 처리 confirm
    function delProudctConfirm() {
    	bootbox.confirm({
    		title: "알림창.",
    		message: "삭제하시겠습니까?",
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
    				productAct.del();
    			}else {
    			}
    		}
    	});
    }
	// 계약서 리스트 삭제 처리 confirm end....

	//************************************************//
	//계약서 관리 화면 조회 리스트 그리드 처리 
	//************************************************//
	var dataGrid;
    var dataGridCurrPage;
    
    var gridView = {   		
         initView: function () {
        	dataGrid = this.target;
        	dataGrid = new ax5.ui.grid();
        	dataGrid.setConfig({
                target: $('[data-ax5grid="data-grid"]'),
                
                showLineNumber: true,
                showRowSelector: true,
                multipleSelect: false,
                lineNumberColumnWidth: 40,
                rowSelectorColumnWidth: 28,
                sortable: true, // 모든 컬럼에 정렬 아이콘 표시
                multiSort: false, // 다중 정렬 여부
                remoteSort: false, // remoteSort에 함수를 sortable 컬럼이 클릭되었을때 실행 setColumnSort를 직접 구현. (remoteSort를 사용하면 헤더에 정렬 상태만 표시 하고 데이터 정렬은 처리 안함)
                header: {
                    align: "center",
                    columnHeight: 28
                },
                body: {
                    align: "center",
                    columnHeight: 28,
                    onClick: function () {
                        // console.log(this);
                        this.self.select(this.dindex);
                		var itemTemp = dataGrid.list[this.dindex];
                		productAct.clickItem(itemTemp);
                    }
                },
                page: {
                    navigationItemCount: 9,
                    height: 30,
                    display: true,
                    firstIcon: '<i class="fa fa-step-backward" aria-hidden="true"></i>',
                    prevIcon: '<i class="fa fa-caret-left" aria-hidden="true"></i>',
                    nextIcon: '<i class="fa fa-caret-right" aria-hidden="true"></i>',
                    lastIcon: '<i class="fa fa-step-forward" aria-hidden="true"></i>',
                    onChange: function () {
                       // gridView.setData(this.page.selectPage);
                        dataGridCurrPage = this.page.selectPage;
                        console.log('dataGridCurrPage: '+dataGridCurrPage);
                        productAct.search(dataGridCurrPage);
                    }
                },
                columns: [
                    {
                        key: "custNm",
                        label: "고객사명",
                        width: 180,
                        styleClass: function () {
                            return "ABC";
                        },
                        enableFilter: true,
                        align: "center"
                    },
                    {key: "brandNm", width: 180, label: "브랜드명", align: "center"},
                    {key: "prodNm", width: 140, label: "제품명", align: "center"},
                    {key: "prodNo", width: 150, label: "제품번호", align: "center"},
                    {key: "majorCtgyNm", width: 150, label: "제품대분류", align: "center"},
                    {key: "subCtgyNm", width: 150, label: "제품소분류", align: "center"},
                    {key: "jobTypeNm", width: 150, label: "작업분류", align: "center"},
                    {key: "cutCnt", width: 150, label: "촬영컷 개수", align: "center"},
                    {key: "regDt", width: 150, label: "등록(수정)일시", align: "center"},
                    {key: "regId", width: 150, label: "등록(수정)자", align: "center"}
                ]
            });
            return this;
        },
        setData: function (_pageNo, _pageListSize, _totalPage, _totalCount, dataList) {
 
            /* var list = [];
            for (var i = 0, l = 10; i < l; i++) {
                list.push($.extend({}, sampleData[(Math.floor(Math.random() * sampleData.length))]));
            }  */
            
            console.log("pageNo: "+_pageNo)
        	console.log("_pageListSize: "+_pageListSize)
        	console.log("_totalPage: "+_totalPage)
        	console.log("_totalCount: "+_totalCount)
 
        	
            dataGrid.setData({
                list: dataList,
                page: {
                    currentPage: _pageNo || 0,
                    pageSize: _pageListSize,
                    totalElements: _totalCount,                          
                    totalPages: _totalPage
                }
            });  
            return this;
        }
    };
    
    var checkProdFileAct = {
    	checkFileData: function(fileId) {
    		console.log("checkFileData: "+fileId);
    		console.log("checkFileData: "+$( '#upFileId' ).val());

    		$( '#upFileId' ).val(fileId);
    		var x = screen.width/2 - 1000/2;
    		var y = screen.height/2 - 800/2;
       	 	//var win = window.open("<c:url value='/warehousing/pop_excel_product?fileId="+fileId+"'/>", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,width=1000,height=700,left="+x+",top="+y);
         	//win.focus();
         	var myForm = document.popForm;
			var url = "<c:url value='/warehousing/producttmp/popExcelProd.do'/>";
			window.open("" ,"popForm", 
			       "toolbar=no, width=1200, height=700, directories=no, status=no, scrollorbars=no, resizable=no"); 
			myForm.action = url; 
			myForm.method="post";
			myForm.target="popForm";
			myForm.cmd = fileId;
			myForm.upFileId = fileId;
			
    		document.getElementById('upFileId').value = fileId;
    		console.log("checkFileData2: "+fileId);   		
			console.log("@#@#@ upFildId: "+$( '#upFileId' ).val());
			
			myForm.submit();
			
    	},
    	uploadFileCallBack : function() {
    		
    	}
    }
    var categoryMap;
	$(document.body).ready(function () {
    	
		$( '#hdUpdateYn' ).val('N');
     	gridView.initView();
        productAct.search(0);

    	// import csv파일 버튼 클릭 후 파일 찾기 완료 시 처리.
    	// uploading처리 후 해당 파일 로딩 후 현재 로그인 사용자로 저장 후 다시 불러오기 처리한다.    	
        $('#fileCsv').change(function() {
			console.log("ev.value: "+$('#fileCsv').val());
            // your code
			$(this).simpleUpload("/warehousing/producttmp/uploadProdFile", {

				start: function(file){
					//upload started
					console.log("upload started");
				},

				progress: function(progress){
					//received progress
					console.log("upload progress: " + Math.round(progress) + "%");
				},

				success: function(data){
					//upload successful
					console.log("upload successful!");
					var fileObj = JSON.parse(data);
					console.log("fileObj.upFileId: "+fileObj.upFileId);

					checkProdFileAct.checkFileData(fileObj.upFileId);
				},

				error: function(error){
					//upload failed
					console.log("upload error: " + error.name + ": " + error.message);
				}

			});

			
        });
        
        // WarehousingMainController 를 통해 하위카테고리를 상위코드명으로 HashMap으로 리턴됨. 
        // categoryMap을 통해 화면에서 상위카테고리 변경 시 하위 categoryMap을 활용한 selectBox값을 세팅함.
        categoryMap = ${cateMap};
        //console.log("##$CategoryMap: "+categoryMap);
		productAct.changeCateMaster();
        
        $('#btnFindCust').on('click', function(){
            var container = $('#customer-pop-container').clone();
            //container.find('table').attr('id', 'example');

            customerPopBox = bootbox.dialog({
              show: false,
              message: container.html(),
              title: "고객사 찾기.",
              buttons: {
                ok: {
                  label: "확인",
                  className: "btn-primary",
                  callback: function() {
                    console.log('OK Button');
                  }
                },
                cancel: {
                  label: "취소",
                  className: "btn-default"
                }
              }
            });
            
                       
            customerPopBox.modal('show'); 
         });
        
       
    });
</script>