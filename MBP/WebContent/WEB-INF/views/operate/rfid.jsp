<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
	<title>RFID 태그관리</title>
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
</head>
<body>
	<div class="wrap"> 
	
		<header class="top_section"> 
			<jsp:include page="../header/top_navi.jsp" flush="false" />
		</header>
		<!-- top_section -->
		
		<div class="middle_section"> 
			<div class="middle_section_contents">
				<div class="main">
					
					<div class="main_content"> 
					
						<div class="main_title">
								<h2>RFID 태그관리</h2>
						</div>
						<div class="content_box">
							<div class="submit_btn_box"> 
								<button type="button" class="save" onclick="rfidAct.add(); return false;">저장</button>
								<button type="button" class="cancel" onclick="rfidAct.cleanInit(); return false;">초기화</button>
							</div>
							<div class="table_box">
								<table class="set">
									<tbody> 
										<tr>
											<th>* 태그번호</th>
											<td>
												<div class="name_box"> 
													<input type="text" id="txtRfidId" maxlength=24 />
													<input type="hidden" id="txtRfidSeqId">
													<input type="hidden" id="hdUpdateYn">
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
								<label for="">• 태그번호</label>
								<input type="text" id="txtSchRfidId" maxlength=24/>
							</form>

							<div class="submit_btn_box"> 
								<button class="inquiry" type="button" onclick="rfidAct.search(); return false;">조회</button>
								<button class="cancel" type="button" onclick="delContractConfirm();">삭제</button>
							</div>		
							
							<div class="table_box">
								<div style="position: relative;height:300px;" id="grid-parent">
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
	//계약서 관리 처리 시작...
	//************************************************//
	var selectedContractId = "";
	var selectedContractBrandId = "";
	var rfidAct = {
    	search : function(page_no) {
			var rfidId = $("#txtSchRfidId").val();
			console.log("rfidId: "+rfidId);
			
			
			var param =	{ 'rfidId' : rfidId, 'qCurrPage' : page_no };

			var paramStr = JSON.stringify(param);
			console.log("param: "+paramStr)
			ajaxUtil.req("<c:url value='/operate/rfidmgr/list'/>", param, 'rfidAct.searchCallBack', false)
    	},
    	searchCallBack: function(result) {
    		console.log("result.success: "+result.success);    				
			if(result.success == "success"){
				var myJSON = JSON.stringify(result);
				//console.log("result myJSON: "+myJSON);    					
				gridView.setData(dataGridCurrPage, result.pageListSize, result.totalPage, result.totalCount, result.list);
				
			} else {
				//$('#mod_cctv').modal('hide');
			}
    	}, 
    	checkContractDupl: function() {

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
			ajaxUtil.req("<c:url value='/contract/checkContractDupl'/>", param, 'rfidAct.checkContractDuplCallBack', false)
    	}, 
    	checkContractDuplCallBack: function(result) {

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

    		var txtRfidId = $( '#txtRfidId' ).val();
    		var txtRfidSeqId = $( '#txtRfidSeqId' ).val();
    		
    		console.log("11 hdUpdateYn: "+hdUpdateYn);
    		
    		if (txtRfidId.length < 24) {
    			tipsAlert("RFID자리수는 24자리입니다. 확인바랍니다.")
    			$( '#txtRfidId' ).focus();
    			return;
    		}
    		
    		var param =	{ 'rfidId' : txtRfidId, 'rfidSeqId' : txtRfidSeqId, 'updateYn': hdUpdateYn};

			var paramStr = JSON.stringify(param);
			console.log("11 param: "+paramStr)
			
    		ajaxUtil.req("<c:url value='/operate/rfidmgr/add'/>", param, 'rfidAct.testCallBack', false);
    	},    	
    	testCallBack: function(result) {
    		console.log("result.success: "+result.success);    				
    		if(result.success == "success"){

    			rfidAct.cleanInit();
    			rfidAct.search();
    			
    		} else {
    			tipsAlert(result.message);
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
    				param['rfidList['+index+'].rfidSeqId'] = itemTemp.rfidSeqId;
    				index++;
    			}
    		}
    		
    		var codes = JSON.stringify(param);

    		console.log("param: "+codes);
    		ajaxUtil.req("<c:url value='/operate/rfidmgr/del'/>", param, 'rfidAct.deleteCallBack', false);
    	},
    	deleteCallBack: function(result) {
    		console.log("result.success: "+result.success);    				
    		if(result.success == "success"){
    			toastView.viewMessage("삭제 처리 되었습니다."); 
    			rfidAct.cleanInit();
    			rfidAct.search();
    		} else {
    			//$('#mod_cctv').modal('hide');
    		}
    	},
    	cleanInit: function() {
    		var list = [];
    		$( '#hdUpdateYn' ).val("N");
    		$( '#txtRfidId').val("");
    		$( '#txtRfidSeqId' ).val("0");
    		
    		selectedRfidId = "";
    	},
    	clickItem: function(selectedItem) {
    		console.log("selectedItem: "+selectedItem);
    		var paramStr = JSON.stringify(selectedItem);
			console.log("param: "+paramStr)
    		$( '#hdUpdateYn' ).val("Y");
    		$( '#txtRfidId' ).val(selectedItem.rfidId);
    		$( '#txtRfidSeqId' ).val(selectedItem.rfidSeqId);

    	}
    }

	
	// 계약서 리스트 삭제 처리 confirm
    function delContractConfirm() {
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
    			console.log('This was logged in the callback: ' + result);
    			if (result == true) {
    				rfidAct.del();
        			console.log('This was true: ' + result);
    			}else {
        			console.log('This was false: ' + result);
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
                		rfidAct.clickItem(itemTemp);
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
                        dataGridCurrPage = this.page.selectPage;
                        console.log('dataGridCurrPage: '+dataGridCurrPage);
                		rfidAct.search(this.page.selectPage);
                        //gridView.setData(this.page.selectPage);
                    }
                },
                columns: [                    
                    {key: "rfidId", width: 180, label: "RFID태그번호", align: "center"},
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
	$(document.body).ready(function () {
    	
		$( '#hdUpdateYn' ).val('N');
		$( '#txtRfidSeqId' ).val("0");
     	gridView.initView();
        rfidAct.search(0);
       
    });
</script>