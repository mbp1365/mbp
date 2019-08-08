<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
	<title>입고 관리</title>
	
	
	<!-- 데이트피커 css -->
	<link rel="stylesheet" href="../plugin/zebra_datepicker/css/default/zebra_datepicker.css" type="text/css">
	
	
	
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
	<!-- 데이트피커 js -->
	<script type="text/javascript" src="../plugin/zebra_datepicker/zebra_datepicker.min.js"></script>
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
								<h2>입고 관리</h2>
						</div>
						
						<div class="content_box">		
							<table style="border-collapse:collapse; border:0px gray solid;"  class="set combined_td">
								<tr>
									<td>
								<form class="srch_box_align_left"> 
									<label for="">• 고객사명</label>
									<input type="text" id="txtCustNm" readonly/>
									<input type="hidden" id="txtCustId" readonly/>
									<span class="img_srch">
										<button type="button" id="btnFindCust" ></button>
									</span>
								</form>
	
								<form class="srch_box_align_left"> 
									<label for="">• 브랜드명</label>
									<select name="" id="sbBrandVal">
										<option value="">선택</option>
										
									</select>
								</form>
							
								<form class="srch_box_align_left"> 
									<label for="">• 대분류</label>
									<select name="" id="sbCateMsCd" onchange="incomeAct.changeCateMaster(); return false;">
										<option value="">전체</option>
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
								</form>
								
								<form class="srch_box_align_left"> 
									<label for="">• 소분류</label>
									<select name="" id="sbCateSlCd">
										<option value="">전체</option>
										
									</select>
								</form>
							
								<form class="srch_box_align_left"> 
									<label for="">• 제품명</label>
									<input type="text" id="txtProdNm"/>
								</form>
								</td>
								</tr>
								<tr>
								<td>
								<form class="srch_box_align_left"> 
									<label for="">• 입고일자</label>
									<input type="text" id="txtIncomeDate"/>
								</form>
								
								<form class="srch_box_align_left"> 
									<label for="">• 입고 담당자</label>
									<input type="text" id="txtIncomeUserId" readonly/>
									<span class="img_srch">
										<button type="button"></button>
									</span>
								</form>
								
								<form class="srch_box_align_left"> 
									<label for="">• 제품상태</label>
									<select name="" id="sbProdStatusVal">
										<option value="전체">전체</option>
										<option value="01">정상</option>
										<option value="02">불량</option>	
									</select>
								</form>
								
								<form class="srch_box_align_left"> 
									<label for="">• 검수여부</label>
									<select name="" id="sbCheckYnVal">
										<option value="">전체</option>
										<option value="Y">예</option>
										<option value="N">아니오</option>
									</select>
								</form>
								
								<form class="srch_box_align_left"> 
									<label for="">• 스팀여부</label>
									<select name="" id="sbSteemYnVal">
										<option value="">전체</option>
										<option value="Y">예</option>
										<option value="N">아니오</option>
									</select>
								</form>
								
								
								<div class="submit_btn_box"> 
									<button id="inquiry" class="inquiry" type="button"  onclick="incomeAct.search(); return false;">조회</button>
								</div>
							</table>
						
							
							<div class="table_box">
								<form name="popForm"  method="post" action="" >
								    <input type="hidden" id="prodId" name="prodId" value=""/>
								</form>
								<div style="position: relative;height:590px;" id="grid-parent">
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
	
	<script type="text/javascript"> 
	$(function(){
	
		// 데이트 피커 
		//$('#registration_date').Zebra_DatePicker();
		$('#txtIncomeDate').Zebra_DatePicker();
		
		
		
		//상세조회
		$("#inquiry2").on("click",function(){
			window.open("pop_warehousing_check.html","","width=1245, height=800, resizable=no, scrollbars=no, status=no;");
		});
		
		$("#manual_input").on("click",function(){
			window.open("pop_warehousing_manual.html","","width=750, height=800, resizable=no, scrollbars=no, status=no;");
		});
			
		
		
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
	//입고조회 처리 시작...
	//************************************************//
	var selectedProdId = "";
	var selectedProdBrandId = "";
	var incomeAct = {
    	search : function(pageNo) {
			// 제품 조회 시 조회조건 제품명으로 처리 체크.
    		var prodNm = $("#txtProdNm").val();
			
			var param =	{ 'prodNm' : prodNm, 'qCurrPage' : pageNo };

			ajaxUtil.req("<c:url value='/warehousing/income/list'/>", param, 'incomeAct.searchCallBack', false)
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
    	}
    }

	//************************************************//
	//입고조회 처리 end...
	//************************************************//
	//************************************************//
	//입고 상세정보  처리 시작...
	//************************************************//
	var incomeDetailAct = {
    	viewInfoPop: function(prodId) {
    		console.log("viewInfoPop: "+prodId);

    		var x = screen.width/2 - 1000/2;
    		var y = screen.height/2 - 800/2;
       	 	//var win = window.open("<c:url value='/warehousing/pop_excel_product?fileId="+fileId+"'/>", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,width=1000,height=700,left="+x+",top="+y);
         	//win.focus();
         	var myForm = document.popForm;
			var url = "<c:url value='/warehousing/income/popIncomeDetail.do'/>";
			window.open("" ,"popForm", 
			       "toolbar=no, width=1200, height=700, directories=no, status=no, scrollorbars=no, resizable=no"); 
			myForm.action = url; 
			myForm.method="post";
			myForm.target="popForm";
			myForm.prodId = prodId;
						
			myForm.submit();
			
    	},
    	uploadFileCallBack : function() {
    		
    	}
    }
	
	
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
    				incomeAct.del();
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
                		//incomeAct.clickItem(itemTemp);
                    },
                    onDBLClick: function () {
                        this.self.select(this.dindex);
                		var itemTemp = dataGrid.list[this.dindex];
                		console.log("dblclick....."+itemTemp.prodId);
                		incomeDetailAct.viewInfoPop(itemTemp.prodId);
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
                        incomeAct.search(dataGridCurrPage);
                    }
                },
                columns: [
                    {
                        key: "rfidTag",
                        label: "RFID TAG",
                        width: 180,
                        styleClass: function () {
                            return "ABC";
                        },
                        enableFilter: true,
                        align: "center"
                    },
                    {key: "custNm", width: 180, label: "고객사", align: "center"},
                    {key: "brandNm", width: 180, label: "브랜드명", align: "center"},
                    {key: "majorCtgyNm", width: 150, label: "제품대분류", align: "center"},
                    {key: "subCtgyNm", width: 150, label: "제품소분류", align: "center"},
                    {key: "prodNo", width: 150, label: "제품번호", align: "center"},
                    {key: "prodNm", width: 140, label: "제품명", align: "center"},
                    {key: "inDate", width: 150, label: "입고 일시", align: "center"},
                    {key: "inChrgNm", width: 150, label: "입고 담당자", align: "center"},
                    {key: "prodStateNm", width: 150, label: "제품상태", align: "center"},
                    {key: "steamYn", width: 150, label: "스팀여부", align: "center"}
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
    
    var categoryMap;
	$(document.body).ready(function () {
    	
		$( '#hdUpdateYn' ).val('N');
     	gridView.initView();
        incomeAct.search(0);

    	        
        // WarehousingMainController 를 통해 하위카테고리를 상위코드명으로 HashMap으로 리턴됨. 
        // categoryMap을 통해 화면에서 상위카테고리 변경 시 하위 categoryMap을 활용한 selectBox값을 세팅함.
        categoryMap = ${cateMap};
        console.log("##$CategoryMap: "+categoryMap);
		incomeAct.changeCateMaster();
        
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