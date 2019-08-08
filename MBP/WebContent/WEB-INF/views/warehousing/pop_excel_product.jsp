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
<body>
	<div class="wrap">	
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
								<h2>Excel 파일 제품 정보 확인.</h2>
						</div>
						<div class="content_box">
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
								</tbody>
							</table>
						</div>
						<div class="content_box">			
							<div class="submit_btn_box"> 
								<button class="save" type="button"  onclick="productTmpAct.save(); return false;">저 장</button>
								<button class="cancel" type="button"  onclick="delProudctConfirm(); return false;">취 소</button>
							</div>				
							<div class="table_box">
								<div style="position: relative;height:450px;" id="grid-parent">
								    <div data-ax5grid="data-grid" data-ax5grid-config="{
								                    showLineNumber: true,
								                    showRowSelector: false,
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
	<input type=hidden id="hdFildId" value="${fileId}">
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
	var productTmpAct = {
    	search : function(fileId) {			
			var param =	{ 'upFileId' : fileId};
			ajaxUtil.req("<c:url value='/warehousing/producttmp/list'/>", param, 'productTmpAct.searchCallBack', false)
    	},
    	searchCallBack: function(result) {
    		console.log("result.success: "+result.success);    				
			if(result.success == "success"){
				var myJSON = JSON.stringify(result);
				console.log("result result.list: "+result.list);  
				console.log("result result.list: "+result.list.length);    					
				gridView.setData(result.list);
				
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
			
    		ajaxUtil.req("<c:url value='/warehousing/producttmp/save'/>", param, 'productTmpAct.addingCallBack', false);
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
    		
    		ajaxUtil.req("<c:url value='/warehousing/producttmp/del'/>", param, 'productTmpAct.deleteCallBack', false);
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
    				productTmpAct.save();
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
    				productTmpAct.del();
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
                showRowSelector: false,
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
                columns: [                   
                    {key: "rfidTag", width: 140, label: "RFID-TAG", align: "center"},
                    {key: "prodNm", width: 140, label: "제품명", align: "center"},
                    {key: "prodNo", width: 150, label: "제품번호", align: "center"},
                    {key: "majorCtgyNm", width: 150, label: "제품대분류", align: "center"},
                    {key: "subCtgyNm", width: 150, label: "제품소분류", align: "center"},
                    {key: "jobTypeNm", width: 150, label: "작업분류", align: "center"},
                    {key: "cutCnt", width: 150, label: "촬영컷 개수", align: "center"}/* ,
                    {key: "regId", width: 150, label: "등록(수정)자", align: "center"},
                    {key: "regDt", width: 150, label: "등록(수정)일시", align: "center"} */
                ]
            });
            return this;
        },
        setData: function (dataList) {
 
        	
            dataGrid.setData(dataList);  
            return this;
        }
    };
    
    var currUpTmpFileId;
	$(document.body).ready(function () {
		currUpTmpFileId = "${fileId}"; 
     	gridView.initView();

     	productTmpAct.search(currUpTmpFileId);
     	
        $('#btnFindCust').on('click', function(){
            var container = $('#customer-pop-container').clone();
            //container.find('table').attr('id', 'example');

            customerPopBox = bootbox.dialog({
              show: false,
              message: container.html(),
              title: "고객사 찾기.",
              buttons: {
                ok: {
                  label: "OK",
                  className: "btn-primary",
                  callback: function() {
                    console.log('OK Button');
                  }
                },
                cancel: {
                  label: "Cancel",
                  className: "btn-default"
                }
              }
            });
            
                       
            customerPopBox.modal('show'); 
         });
        
       
    });
</script>