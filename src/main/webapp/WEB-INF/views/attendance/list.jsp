<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
  <head>
    <jsp:include page="/WEB-INF/views/layouts/header.jsp" flush="false" />
    <!-- 엑셀 다운로드 레이어 팝업 -->
    <jsp:include page="/WEB-INF/views/admin/pop/excel_down.jsp" flush="false" />
    <jsp:include page="/WEB-INF/views/admin/pop/member_memo_pop.jsp" flush="false" />
    <jsp:include page="/WEB-INF/views/admin/pop/sayu_pop.jsp" flush="false" />

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="resourcesPath" value="${contextPath}/resources" />
	<script>
		var contextPath = '${contextPath}';
		var resourcesPath = '${resourcesPath}';
	</script>
	
    <link href="${resourcesPath}/assets/css/reset.css?${nowTime}" rel="stylesheet">
	<link href="${resourcesPath}/assets/css/common.css?${nowTime}" rel="stylesheet">
	<link href="${resourcesPath}/assets/css/general.css?${nowTime}" rel="stylesheet">
	<link href="${resourcesPath}/assets/css/attendance.css?${nowTime}" rel="stylesheet">
	
	<!-- sortable 구현부 시작 -->
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<!-- sortable 구현부 끝 -->
	
  </head>
  <body>
  
    <div class="container">
    
            <div class="header-product">
            <div class="header-att">
   				<span class="shop-link-login" onclick="mypage()">
   					<img src="${resourcesPath}/assets/images/back_btn.png" class="back-img">
   				</span>
			    <div class="main-title-att-div text-center">
			    	<span class="main-title-att-span">출석부</span>
			    	<img src="${resourcesPath }/assets/images/btn_excel.png" class="attendance-img" onclick="excelDownPopup()">
			    </div>
            </div>
            
            	<input type="hidden" id="thisYear" name="thisYear" value="${attendanceSearch.year }"/>
            	<input type="hidden" id="thisMonth" name="thisMonth" value="${attendanceSearch.month }"/>
            	
				<div class="div-container">
                    
                    <div class="search-div">
						<div class="customer-select-search" style="width: 26%; margin-left:5%; float: left;">
							 <select class="select-attendance-list-team" id="teamId" name="teamId" onchange="attendanceSearch()">
		                       	<option value="99" >팀 전체</option>
		                       	<c:forEach var="team" items="${teamList }">
		                       		<c:set var="selected" value="" />
								        <c:if test="${team.id eq attendanceSearch.teamId 
								        	|| (attendanceSearch.teamId == 4 && team.id == 8)
								        	|| (attendanceSearch.teamId == 8 && team.id == 4)}">
											<c:set var="selected" value="selected" />
										</c:if>
									<option value="${team.id}" ${selected} >${team.tShortName}</option>
								</c:forEach>
		                    </select> 
	                    </div>
	                    
	                    <div class="customer-select-search" style="width: 19%; margin-left: -20px; float: left;">
							<!-- 년 선택 -->
	                   		<select class="select-attendance-list-year" id="year" name="year" onchange="attendanceSearch()">
		                       	<c:forEach var="year" items="${yearList }">
		                       		<c:set var="selected" value="" />
									<c:if test="${year eq attendanceSearch.year }">
										<c:set var="selected" value="selected" />
									</c:if>
									<option value="${year}" ${selected} >${year}년</option>
								</c:forEach>
		                    </select>
	                    </div>    
	                    
	                    <div class="customer-select-search" style="width: 15%; margin-left: 3%; float: left;">
							<!-- 월 선택 -->
	                   		<select class="select-attendance-list-month" id="month" name="month" onchange="attendanceSearch()">
		                       	<c:forEach var="month" items="${SMonthSearchType }">
		                       		<c:set var="selected" value="" />
									<c:if test="${month.getVName() eq attendanceSearch.month }">
										<c:set var="selected" value="selected" />
									</c:if>
									<option value="${month.getVName()}" ${selected} >${month.getVName()}월</option>
								</c:forEach>
		                    </select>
	                    </div>    
	                    
	                    
	                    <div class="customer-select-search" style="width: 17%; margin-left: 12px; margin-right:-3px; float: left;">
                        	<input type="text" class="md-input2" id="nameKW" name="nameKW" onkeyup="if(window.event.keyCode==13){(enterKeyEvent())}" placeholder="이름" value="${attendanceSearch.nameKW }" style="border: 1px solid #ccc;">
		            	</div>
		            
			            <div>
			            	<button class="basic-btn attendance-list-btn" id="searchBtn" onclick="attendanceSearch()">조회</button>
			            </div>
			        </div>

                    <div class="attendance-table">
					<div class="sales-table">
			            <div class="table-wrap-att">
			            
			                <table>
			                    <thead>
			                    
			                    	<c:if test="${(attendanceSearch.year == 2021 and attendanceSearch.month != 1 and attendanceSearch.month != 6) or 
			                    				(attendanceSearch.year != 2021 and attendanceSearch.month != 1 and attendanceSearch.month != 7)}">
				                    	<tr class="lastAtt">
				                            <th class="th-5p6"></th>
					                		<th class="th-18p0"></th>
					                		<th class="th-22p0"></th>
					                		<th colspan="${lastSunday.size()}" style="border-right: 2px ridge">${attendanceSearch.lastMonth }월</th>
							                <th colspan="${sunday.size()}">${attendanceSearch.month }월</th>
				                            <th></th>
				                        </tr>
			                        </c:if>
			                    	
			                        <tr>
			                            <th class="th-5p6">No</th>
			                            
			                            <!-- teamId 4 : 1새가족   /  8 : 2새가족   /  11 : 1,2청 통합 새가족 -->
		                            	 <c:choose>
						                	<c:when test="${attendanceSearch.teamId == 4 || attendanceSearch.teamId == 8}">
						                		<th class="th-18p0">이름 (동기)</th>
						                		<th class="th-22p0">인도자</th>
						                	</c:when>
						                	<c:otherwise>
						                		<th class="th-22p0">순</th>
						                		<th class="th-18p0">이름 (동기)</th>
						                	</c:otherwise>
						                </c:choose>
						                
						                <c:if test="${(attendanceSearch.year == 2021 and attendanceSearch.month != 1 and attendanceSearch.month != 6) or 
			                    				(attendanceSearch.year != 2021 and attendanceSearch.month != 1 and attendanceSearch.month != 7)}">
							                <c:forEach var="ls" items="${lastSunday}" varStatus="i">
							                	<c:set var="borderRight" value="" />
							                	<c:if test="${i.index+1 eq lastSunday.size() }">
													<c:set var="borderRight" value="border-right: 2px ridge" />
												</c:if>
				                            	<th class="lastAtt th-7p6" style="${borderRight}">${ls}</th>
				                            </c:forEach>
			                            </c:if>
			                            
			                            <c:forEach var="s" items="${sunday}" varStatus="i">
			                            	<th class="th-7p6">${s}</th>
			                            </c:forEach>
			                            <th class="th-5p6 updownImg" onclick="secondOpen()">
		                            		<img src="${resourcesPath}/assets/images/ico_accordion_down.png" data-value="down" id="updown-img" class="updown-img">
			                            </th>
			                        </tr>
			                    </thead>
			                    
			                    <c:choose>
									<c:when test="${attendanceList.size() == 0}">
										<tbody>
											<tr>
												<td colspan="7">
													<div class="no-attendance">순편성을 진행해 주세요</div>
												</td>
											</tr>
										</tbody>
									</c:when>
									<c:otherwise>
											
					                    <!-- 멤버 for문 start -->
										<!-- 팀이 새가족팀이 아니면서 순 셋팅이 안된 순원은 보여주지 않는다. -->
										<c:set var="sortableIndex" value="1" />
										<c:set var="index" value="1" />
										
										<!-- 출석부 start -->
										<c:forEach var="att" items="${attendanceList}" varStatus="i">
										
											<c:set var="tbodySortable" value="sortable"/>
											<c:choose>
												<c:when test="${i.index == 0 }">
													<c:set var="tbodySortable" value="sortable${sortableIndex}"/>
													<c:set var="groupNameTmp" value="${att.group.gName}"/>
													<tbody class="${tbodySortable }">
												</c:when>
												<c:when test="${i.index > 0 && att.group.gName == groupNameTmp && att.team.id == teamIdTmp}">
			                       					<c:set var="tbodySortable" value="sortable${sortableIndex}"/>
			                            		</c:when>
							                    <c:otherwise>
							                    	<c:set var="sortableIndex" value="${sortableIndex + 1 }" />
							                    	<c:set var="tbodySortable" value="sortable${sortableIndex}"/>
							                    	</tbody>
							                    	<tbody class="${tbodySortable }">
							                    </c:otherwise>
											</c:choose>
											<c:set var="teamIdTmp" value="${att.team.id}"/>
										
					                    
			                            				<c:choose>
				                            				<c:when test="${(att.member.teamId != 4 && att.member.teamId != 8 && att.member.teamId != 11) && att.groupId == null }">
				                            				</c:when>
				                            				<c:otherwise>
													
													
																<c:set var="attYN" value="" />
																<c:choose>
							                            			<c:when test="${att.attYn == 'Y'}">
							                            				<c:set var="attYN" value="attY" />
							                            			</c:when>
							                            			<c:otherwise>
							                            				<c:set var="attYN" value="" />
							                            			</c:otherwise>
											                    </c:choose>
				
											                    
																<input type="hidden" id="${att.id}" name="aId"/>
									                    		<c:set var="secondUseYN" value="" />
									                    		<c:set var="secondUseYN_CSS" value="" />
									                    		<c:set var="memberMemoPop" value="" />
									                    		<c:set var="notSortable" value="not-sortable" />
									                    		<c:if test="${att.attYn == 'N' }">
									                    			<c:set var="secondUseYN" value="secondYN" />
									                    			<c:set var="secondUseYN_CSS" value="display: none;" />
									                    		</c:if>
									                    		<c:if test="${att.attYn == 'Y' }">
									                    			<c:set var="memberMemoPop" value="open" />
									                    			<c:set var="notSortable" value="" />
									                    		</c:if>

									                    		
					                            				<tr class="${secondUseYN} ${notSortable} " style="${secondUseYN_CSS}">
					                            				
								                    			<c:choose>
								                    				<c:when test="${att.attYn == 'Y' }">
								                    					<td class="${attYN}" data-id="${att.member.id }">${index}
								                    						<input type="hidden" id="memberId" name="memberId" value="${att.member.id }">
								                    						<input type="hidden" id="attYn" name="attYn" value="${att.attYn }">
								                    					</td>
								                    					<c:set var="index" value="${index+1}" />
								                    				</c:when>
								                    				<c:otherwise>
								                    					<td>
								                    						<input type="hidden" id="memberId" name="memberId" value="${att.member.id }">
								                    						<input type="hidden" id="attYn" name="attYn" value="${att.attYn }">
								                    					</td>
								                    				</c:otherwise>
									                            </c:choose>
									                            
									                            <c:set var="bold" value=""/>
									                           
									                           <!-- teamId 4 : 1새가족   /  8 : 2새가족   /  11 : 1,2청 통합 새가족 -->
									                            <c:if test="${attendanceSearch.teamId != 4 && attendanceSearch.teamId != 8}">
									                            	<c:set var="bold" value="700"/>
									                            	<c:choose>
									                            		<c:when test="${i.index == 0}">
										                            		<!-- 팀 전체 검색 시 순명 앞에 팀명 출력 -->
									                            			<c:choose>
									                            				<c:when test="${attendanceSearch.teamId == '99'}">
									                            					<c:set var="groupName" value="${att.team.tShortName} / ${att.group.gName }"/>
									                            					<c:set var="groupNameTmp" value="${att.group.gName }"/>
									                            				</c:when>
									                            				<c:otherwise>
									                            					<c:set var="groupName" value="${att.group.gName }"/>
									                            					<c:set var="groupNameTmp" value="${att.group.gName }"/>
									                            				</c:otherwise>
									                            			</c:choose>
									                            		</c:when>
									                            		<c:when test="${i.index > 0 && att.group.gName != groupNameTmp}">
									                            			<!-- 팀 전체 검색 시 순명 앞에 팀명 출력 -->
									                            			<c:choose>
									                            				<c:when test="${attendanceSearch.teamId == '99'}">
									                            					
									                            					<c:choose>
									                            						<c:when test="${att.group.gName == '' || att.group.gName == null}">
									                            							<c:set var="groupName" value="${att.team.tShortName}"/>
									                            						</c:when>
									                            						<c:otherwise>
									                            							<c:set var="groupName" value="${att.team.tShortName} / ${att.group.gName }"/>
									                            						</c:otherwise>
									                            					</c:choose>
									                            					<c:set var="groupNameTmp" value="${att.group.gName }"/>
									                            				</c:when>
									                            				<c:otherwise>
									                            					<c:set var="groupName" value="${att.group.gName }"/>
									                            					<c:set var="groupNameTmp" value="${att.group.gName }"/>
									                            				</c:otherwise>
									                            			</c:choose>
									                            		</c:when>
									                            		<c:when test="${i.index > 0 && att.group.gName == groupNameTmp}">
							                            					<c:set var="groupName" value=""/>
							                            					<c:set var="groupNameTmp" value="${att.group.gName }"/>
									                            		</c:when>
									                            	</c:choose>
								                            	</c:if>
									                            
									                            
									                            <c:set var="bold" value=""/>
									                            <c:set var="bold2" value=""/>
									                            <c:set var="italic" value=""/>
									                            <c:set var="blue" value=""/>
									                            <c:if test="${att.groupGrade == '순장' && att.attYn == 'Y'}">
									                            	<c:set var="bold" value="700"/>
									                            </c:if>
									                            <c:if test="${att.group.gName == '' || att.group.gName == null}">
									                            	<c:set var="bold2" value="700"/>
									                            </c:if>
									                            <c:if test="${att.member.memState != '1' }">
									                            	<c:set var="italic" value="italic"/>
									                            </c:if>
									                            <c:if test="${((att.member.teamId == login.teamId && login.authId == 3) || login.authId < 3) && (att.member.memo != '' && att.member.memo != null) && att.attYn == 'Y'}">
									                            	<c:set var="blue" value="#284AF3"/>
									                            </c:if>
									                            
									                            
							                            	    <!-- teamId 4 : 1새가족   /  8 : 2새가족   /  11 : 1,2청 통합 새가족 -->
								                            	<c:choose>
												                	<c:when test="${attendanceSearch.teamId == 4 || attendanceSearch.teamId == 8 }">
							                            				 <c:choose>
							                            					<c:when test="${att.attYn == 'Y'}">
							                            						
							                            						
							                            						<!-- 목사님 및 본인 팀의 팀장만 지체 상황 팝업 열어서 수정 할 수 있음 -->
							                            						<%-- <c:choose>
							                            							<c:when test="${( (login.teamId == 4 || login.teamId == 8) && login.authId == 3) || login.authId < 3}">
							                            								<td class="${memberMemoPop}" onclick="memberMemoPop('${memberMemoPop}', '${att.id }', '${att.member.id}', '${att.member.name}', '${att.member.memo}', '${att.member.replaceProfileImg }')" style="font-weight: ${bold}; font-style: ${italic}; color: ${blue}; cursor: pointer;">		
							                            							</c:when>
							                            							<c:otherwise>
							                            								<td style="font-weight: ${bold}; font-style: ${italic};">
							                            							</c:otherwise>
							                            						</c:choose> --%>
							                            						
							                            						<c:set var="setStyle" value=""/>
							                            						
							                            						<c:choose>
							                            							<c:when test="${( (login.teamId == 4 || login.teamId == 8) && login.authId == 3) || login.authId < 3}">
								                            							<c:set var="setStyle" value="font-weight: ${bold}; font-style: ${italic}; color: ${blue}; cursor: pointer;"/>
							                            								<td class="${memberMemoPop}" onclick="memberMemoPop('${memberMemoPop}', '${att.id }', '${att.member.id}', '${att.member.name}', '${att.member.memo}', '${att.member.replaceProfileImg }, '${att.member.memoFlag }', '${login.authId}')" style="${setStyle}">		
							                            							</c:when>
							                            							<c:otherwise>
							                            								<c:set var="setStyle" value="font-weight: ${bold}; font-style: ${italic}; cursor: pointer;"/>
							                            								<td class="${memberMemoPop}" onclick="memberMemoPop('${memberMemoPop}', '${att.id }', '${att.member.id}', '${att.member.name}', '${att.member.memo}', '${att.member.replaceProfileImg }', '${att.member.memoFlag }', '${login.authId}')" style="${setStyle}">
							                            							</c:otherwise>
							                            						</c:choose>
							                            						
							                            						
										                            				${att.member.name } 
										                            				
										                            				<!-- 동기 표시 -->
										                            				<c:if test="${att.member.samePeriodId != null }">
										                            					<c:set var="yearTmp" value="${attendanceSearch.year - att.samePeriod.birthYear}"/>
													                            		<c:choose>
													                            			<c:when test="${yearTmp == 18}">
													                            				(0)
													                            			</c:when>
													                            			<c:when test="${yearTmp == 19}">
													                            				(1)
													                            			</c:when>
													                            			<c:when test="${yearTmp == 20}">
													                            				(2)
													                            			</c:when>
													                            			<c:when test="${yearTmp == 21}">
													                            				(3)
													                            			</c:when>
													                            			<c:when test="${yearTmp == 22}">
													                            				(4)
													                            			</c:when>
													                            			<c:when test="${yearTmp == 23}">
													                            				(5)
													                            			</c:when>
													                            			<c:when test="${yearTmp == 24}">
													                            				(6)
													                            			</c:when>
													                            			<c:when test="${yearTmp == 25}">
													                            				(7)
													                            			</c:when>
													                            			<c:when test="${yearTmp == 26}">
													                            				(8)
													                            			</c:when>
													                            			<c:otherwise>
													                            				(${fn:substring(att.samePeriod.birthYear,2,4)})
													                            			</c:otherwise>
													                            		</c:choose>
													                            	</c:if>
													                            	
													                            	<c:if test="${(login.authId <= 2 && att.member.memoFlag == 1) || (login.authId == 3 && att.member.teamId == login.teamId && att.member.memoFlag == 1)}">
													                            		<img src="${resourcesPath }/assets/images/red_jeom.png" id="${att.id}-jeom-img" style="width:3px; padding:0 0px 3px 0px; margin-left:1px;">
													                            	</c:if>
													                            	
													                            </td>
													                            
													                            <td style="font-weight: ${bold}">${att.member.guider}</td>
									                            			</c:when>
									                            			<c:otherwise>
									                            				<td style="font-style: ${italic};">2부</td>
										                            			<td></td>
									                            			</c:otherwise>
									                            		</c:choose>
							                            			</c:when>
							                            			
							                            			<c:otherwise>
							                            				
						                            					<td style="font-weight: ${bold} ${bold2}">${groupName}</td>
											                		
											                		
											                            	<c:choose>
										                            			<c:when test="${att.attYn == 'Y'}">
										                            			
										                            				<!-- 목사님 및 본인 팀의 팀장만 지체 상황 팝업 열어서 수정 할 수 있음 -->
									                            					<%-- <c:choose>
									                            						<c:when test="${(att.member.teamId == login.teamId && login.authId == 3) || login.authId < 3}">
									                            							<td class="${memberMemoPop}" onclick="memberMemoPop('${memberMemoPop}', '${att.id }', '${att.member.id}', '${att.member.name}', '${att.member.memo}', '${att.member.replaceProfileImg }')" style="font-weight: ${bold}; font-style: ${italic}; color: ${blue}; cursor:pointer; ">		
									                            						</c:when>
									                            						<c:otherwise>
									                            							<td style="font-weight: ${bold}; font-style: ${italic};">
									                            						</c:otherwise>
									                            					</c:choose> --%>
									                            					
									                            					<c:set var="setStyle" value=""/>
							                            						
							                            						<c:choose>
							                            							<c:when test="${(att.member.teamId == login.teamId && login.authId == 3) || login.authId < 3}">
								                            							<c:set var="setStyle" value="font-weight: ${bold}; font-style: ${italic}; color: ${blue}; cursor: pointer;"/>
							                            								<td class="${memberMemoPop}" onclick="memberMemoPop('${memberMemoPop}', '${att.id }', '${att.member.id}', '${att.member.name}', '${att.member.memo}', '${att.member.replaceProfileImg }', '${att.member.memoFlag }', '${login.authId}')" style="${setStyle}">		
							                            							</c:when>
							                            							<c:otherwise>
							                            								<c:set var="setStyle" value="font-weight: ${bold}; font-style: ${italic}; cursor: pointer;"/>
							                            								<td class="${memberMemoPop}" onclick="memberMemoPop('${memberMemoPop}', '${att.id }', '${att.member.id}', '${att.member.name}', '${att.member.memo}', '${att.member.replaceProfileImg }', '${att.member.memoFlag }', '${login.authId}')" style="${setStyle}">
							                            							</c:otherwise>
							                            						</c:choose>
							                            						
									                            					
										                            				${att.member.name } 
										                            				<!-- 동기 표시 -->
										                            				<c:if test="${att.member.samePeriodId != null }"> 
										                            					<c:set var="yearTmp" value="${attendanceSearch.year - att.samePeriod.birthYear}"/>
													                            		<c:choose>
													                            			<c:when test="${yearTmp == 18}">
													                            				(0)
													                            			</c:when>
													                            			<c:when test="${yearTmp == 19}">
													                            				(1)
													                            			</c:when>
													                            			<c:when test="${yearTmp == 20}">
													                            				(2)
													                            			</c:when>
													                            			<c:when test="${yearTmp == 21}">
													                            				(3)
													                            			</c:when>
													                            			<c:when test="${yearTmp == 22}">
													                            				(4)
													                            			</c:when>
													                            			<c:when test="${yearTmp == 23}">
													                            				(5)
													                            			</c:when>
													                            			<c:when test="${yearTmp == 24}">
													                            				(6)
													                            			</c:when>
													                            			<c:when test="${yearTmp == 25}">
													                            				(7)
													                            			</c:when>
													                            			<c:when test="${yearTmp == 26}">
													                            				(8)
													                            			</c:when>
													                            			<c:otherwise>
													                            				(${fn:substring(att.samePeriod.birthYear,2,4)})
													                            			</c:otherwise>
													                            		</c:choose>
													                            	</c:if>
													                            	
													                            	<c:if test="${(login.authId <= 2 && att.member.memoFlag == 1) || (login.authId == 3 && att.member.teamId == login.teamId && att.member.memoFlag == 1)}">
													                            		<img src="${resourcesPath }/assets/images/red_jeom.png" id="${att.id}-jeom-img" style="width:3px; padding:0 0px 3px 0px; margin-left:1px;">
													                            	</c:if>
													                            	
													                            	</td>
													                            	
										                            			</c:when>
										                            			<c:otherwise>
										                            				<td style="font-weight: ${bold}; font-style: ${italic};">
									                            						2부
									                            					</td>
										                            			</c:otherwise>
										                            		</c:choose>
												                            
							                            			</c:otherwise>
							                            		</c:choose>
							                            		
									                            
									                            
									                            
									                            
									                            
									                            
									                            
									                            <!-- 출석체크 checkbox -->
									                            
									                            <c:if test="${(attendanceSearch.year == 2021 and attendanceSearch.month != 1 and attendanceSearch.month != 6) or 
			                    											(attendanceSearch.year != 2021 and attendanceSearch.month != 1 and attendanceSearch.month != 7)}">
									                            
										                            <c:forEach var="lastSun" items="${lastSunday}" varStatus="i">
										                            	<c:set var="checked" value=""/>
										                            	<c:choose>
									                            			<c:when test="${i.index == 0}">
									                            				<c:if test="${att.lastFirstWeek == 'Y' }" >
												                            		<c:set var="checked" value="checked"/>
												                            	</c:if>
												                            	<td class="lastAtt">
												                            		<c:choose>
												                            			<c:when test="${att.lastSayu1 != '' && att.lastSayu1 != null}">
												                            				
												                            				<c:choose>
									                            								<c:when test="${((att.member.teamId == login.teamId && login.authId == 3) || login.authId < 3)}">
									                            									<a href="javascript:sayuPop('${att.id }', '${att.member.name }', '${lastSun }', '${att.lastSayu1}', '${i.index+1}', '1', '0')" title="${att.lastSayu1}" class="none-underline">
										                            									사유
										                            								</a>
									                            								</c:when>
									                            								<c:otherwise>
									                            									<a href="javascript:sayuPop('${att.id }', '${att.member.name }', '${lastSun }', '${att.lastSayu1}', '${i.index+1}', '0', '0')" title="${att.lastSayu1}" class="none-underline">
										                            									사유
										                            								</a>
									                            								</c:otherwise>
									                            							</c:choose>
									                            							<input type="checkbox" id="lastFirstWeek" name="lastFirstWeek" ${checked} style="display:none;" disabled>
												                            			</c:when>
												                            			<c:otherwise>
												                            				<input type="checkbox" id="lastFirstWeek" name="lastFirstWeek" ${checked} disabled>
												                            			</c:otherwise>
												                            		</c:choose>
												                            	</td>
									                            			</c:when>
									                            			<c:when test="${i.index == 1}">
									                            				<c:if test="${att.lastSecondWeek == 'Y' }" >
												                            		<c:set var="checked" value="checked"/>
												                            	</c:if>
												                            	<td class="lastAtt">
												                            		<c:choose>
												                            			<c:when test="${att.lastSayu2 != '' && att.lastSayu2 != null}">
												                            				
												                            				<c:choose>
									                            								<c:when test="${((att.member.teamId == login.teamId && login.authId == 3) || login.authId < 3)}">
									                            									<a href="javascript:sayuPop('${att.id }', '${att.member.name }', '${lastSun }', '${att.lastSayu2}', '${i.index+1}', '1', '0')" title="${att.lastSayu2}" class="none-underline">
										                            									사유
										                            								</a>
									                            								</c:when>
									                            								<c:otherwise>
									                            									<a href="javascript:sayuPop('${att.id }', '${att.member.name }', '${lastSun }', '${att.lastSayu2}', '${i.index+1}', '0', '0')" title="${att.lastSayu2}" class="none-underline">
										                            									사유
										                            								</a>
									                            								</c:otherwise>
									                            							</c:choose>
									                            							<input type="checkbox" id="lastSecondWeek" name="lastSecondWeek" ${checked} style="display:none;" disabled>
									                            							
												                            			</c:when>
												                            			<c:otherwise>
												                            				<input type="checkbox" id="lastSecondWeek" name="lastSecondWeek" ${checked} disabled>
												                            			</c:otherwise>
												                            		</c:choose>
												                            	</td>
									                            			</c:when>
									                            			<c:when test="${i.index == 2}">
									                            				<c:if test="${att.lastThirdWeek == 'Y' }" >
												                            		<c:set var="checked" value="checked"/>
												                            	</c:if>
												                            	<td class="lastAtt">
												                            		<c:choose>
												                            			<c:when test="${att.lastSayu3 != '' && att.lastSayu3 != null}">
												                            			
												                            				<c:choose>
									                            								<c:when test="${((att.member.teamId == login.teamId && login.authId == 3) || login.authId < 3)}">
									                            									<a href="javascript:sayuPop('${att.id }', '${att.member.name }', '${lastSun }', '${att.lastSayu3}', '${i.index+1}', '1', '0')" title="${att.lastSayu3}" class="none-underline">
										                            									사유
										                            								</a>
									                            								</c:when>
									                            								<c:otherwise>
									                            									<a href="javascript:sayuPop('${att.id }', '${att.member.name }', '${lastSun }', '${att.lastSayu3}', '${i.index+1}', '0', '0')" title="${att.lastSayu3}" class="none-underline">
										                            									사유
										                            								</a>
									                            								</c:otherwise>
									                            							</c:choose>
												                            				<input type="checkbox" id="lastThirdWeek" name="lastThirdWeek" ${checked} style="display:none;" disabled>
												                            				
												                            			</c:when>
												                            			<c:otherwise>
												                            				<input type="checkbox" id="lastThirdWeek" name="lastThirdWeek" ${checked} disabled>
												                            			</c:otherwise>
												                            		</c:choose>
												                            	</td>
									                            			</c:when>
									                            			<c:when test="${i.index == 3}">
									                            			
										                            			<c:set var="borderRight" value="" />
															                	<c:if test="${lastSunday.size() eq 4 }">
																					<c:set var="borderRight" value="border-right: 2px ridge" />
																				</c:if>
																				
									                            				<c:if test="${att.lastFourthWeek == 'Y' }" >
												                            		<c:set var="checked" value="checked"/>
												                            	</c:if>
												                            	<td class="lastAtt" style="${borderRight}">
												                            		<c:choose>
												                            			<c:when test="${att.lastSayu4 != '' && att.lastSayu4 != null}">
												                            			
												                            				<c:choose>
									                            								<c:when test="${((att.member.teamId == login.teamId && login.authId == 3) || login.authId < 3)}">
									                            									<a href="javascript:sayuPop('${att.id }', '${att.member.name }', '${lastSun }', '${att.lastSayu4}', '${i.index+1}', '1', '0')" title="${att.lastSayu4}" class="none-underline">
										                            									사유
										                            								</a>
									                            								</c:when>
									                            								<c:otherwise>
									                            									<a href="javascript:sayuPop('${att.id }', '${att.member.name }', '${lastSun }', '${att.lastSayu4}', '${i.index+1}', '0', '0')" title="${att.lastSayu4}" class="none-underline">
										                            									사유
										                            								</a>
									                            								</c:otherwise>
									                            							</c:choose>
									                            							<input type="checkbox" id="lastFourthWeek" name="lastFourthWeek" ${checked} style="display:none;" disabled>
									                            							
												                            			</c:when>
												                            			<c:otherwise>
												                            				<input type="checkbox" id="lastFourthWeek" name="lastFourthWeek" ${checked} disabled>
												                            			</c:otherwise>
												                            		</c:choose>
												                            	</td>
									                            			</c:when>
									                            			<c:otherwise>
									                            			
									                            				<c:set var="borderRight" value="" />
															                	<c:if test="${lastSunday.size() eq 5 }">
																					<c:set var="borderRight" value="border-right: 2px ridge" />
																				</c:if>
																				
									                            				<c:if test="${att.lastFifthWeek == 'Y' }" >
												                            		<c:set var="checked" value="checked"/>
												                            	</c:if>
												                            	<td class="lastAtt" style="${borderRight}">
												                            		<c:choose>
												                            			<c:when test="${att.lastSayu5 != '' && att.lastSayu5 != null}">
												                            			
												                            				<c:choose>
									                            								<c:when test="${((att.member.teamId == login.teamId && login.authId == 3) || login.authId < 3)}">
									                            									<a href="javascript:sayuPop('${att.id }', '${att.member.name }', '${lastSun }', '${att.lastSayu5}', '${i.index+1}', '1', '0')" title="${att.lastSayu5}" class="none-underline">
										                            									사유
										                            								</a>
									                            								</c:when>
									                            								<c:otherwise>
									                            									<a href="javascript:sayuPop('${att.id }', '${att.member.name }', '${lastSun }', '${att.lastSayu5}', '${i.index+1}', '0', '0')" title="${att.lastSayu5}" class="none-underline">
										                            									사유
										                            								</a>
									                            								</c:otherwise>
									                            							</c:choose>
									                            							<input type="checkbox" id="lastFifthWeek" name="lastFifthWeek" ${checked} style="display:none;" disabled>
									                            							
												                            			</c:when>
												                            			<c:otherwise>
												                            				<input type="checkbox" id="lastFifthWeek" name="lastFifthWeek" ${checked} disabled>
												                            			</c:otherwise>
												                            		</c:choose>
												                            	</td>
									                            			</c:otherwise>
									                            		</c:choose>
										                            </c:forEach>
									                            
									                            </c:if>
									                            
									                            
									                            
									                            
									                            <c:forEach var="sun" items="${sunday}" varStatus="i">
									                            	<c:set var="checked" value=""/>
									                            	<c:choose>
								                            			<c:when test="${i.index == 0}">
								                            				<c:if test="${att.firstWeek == 'Y' }" >
											                            		<c:set var="checked" value="checked"/>
											                            	</c:if>
											                            	<td>
											                            		<c:choose>
											                            			<c:when test="${att.sayu1 != '' && att.sayu1 != null}">
											                            				
											                            				<c:choose>
								                            								<c:when test="${((att.member.teamId == login.teamId && login.authId == 3) || login.authId < 3)}">
								                            									<a href="javascript:sayuPop('${att.id }', '${att.member.name }', '${sun }', '${att.sayu1}', '${i.index+1}', '1', '1')" title="${att.sayu1}" class="none-underline">
									                            									사유
									                            								</a>
								                            								</c:when>
								                            								<c:otherwise>
								                            									<a href="javascript:sayuPop('${att.id }', '${att.member.name }', '${sun }', '${att.sayu1}', '${i.index+1}', '0', '1')" title="${att.sayu1}" class="none-underline">
									                            									사유
									                            								</a>
								                            								</c:otherwise>
								                            							</c:choose>
								                            							<input type="checkbox" id="firstWeek" name="firstWeek" ${checked} style="display:none;">
											                            			</c:when>
											                            			<c:otherwise>
											                            				<input type="checkbox" id="firstWeek" name="firstWeek" ${checked} >
											                            			</c:otherwise>
											                            		</c:choose>
											                            	</td>
								                            			</c:when>
								                            			<c:when test="${i.index == 1}">
								                            				<c:if test="${att.secondWeek == 'Y' }" >
											                            		<c:set var="checked" value="checked"/>
											                            	</c:if>
											                            	<td>
											                            		<c:choose>
											                            			<c:when test="${att.sayu2 != '' && att.sayu2 != null}">
											                            				
											                            				<c:choose>
								                            								<c:when test="${((att.member.teamId == login.teamId && login.authId == 3) || login.authId < 3)}">
								                            									<a href="javascript:sayuPop('${att.id }', '${att.member.name }', '${sun }', '${att.sayu2}', '${i.index+1}', '1', '1')" title="${att.sayu2}" class="none-underline">
									                            									사유
									                            								</a>
								                            								</c:when>
								                            								<c:otherwise>
								                            									<a href="javascript:sayuPop('${att.id }', '${att.member.name }', '${sun }', '${att.sayu2}', '${i.index+1}', '0', '1')" title="${att.sayu2}" class="none-underline">
									                            									사유
									                            								</a>
								                            								</c:otherwise>
								                            							</c:choose>
								                            							<input type="checkbox" id="secondWeek" name="secondWeek" ${checked} style="display:none;">
								                            							
											                            			</c:when>
											                            			<c:otherwise>
											                            				<input type="checkbox" id="secondWeek" name="secondWeek" ${checked} >
											                            			</c:otherwise>
											                            		</c:choose>
											                            	</td>
								                            			</c:when>
								                            			<c:when test="${i.index == 2}">
								                            				<c:if test="${att.thirdWeek == 'Y' }" >
											                            		<c:set var="checked" value="checked"/>
											                            	</c:if>
											                            	<td>
											                            		<c:choose>
											                            			<c:when test="${att.sayu3 != '' && att.sayu3 != null}">
											                            			
											                            				<c:choose>
								                            								<c:when test="${((att.member.teamId == login.teamId && login.authId == 3) || login.authId < 3)}">
								                            									<a href="javascript:sayuPop('${att.id }', '${att.member.name }', '${sun }', '${att.sayu3}', '${i.index+1}', '1', '1')" title="${att.sayu3}" class="none-underline">
									                            									사유
									                            								</a>
								                            								</c:when>
								                            								<c:otherwise>
								                            									<a href="javascript:sayuPop('${att.id }', '${att.member.name }', '${sun }', '${att.sayu3}', '${i.index+1}', '0', '1')" title="${att.sayu3}" class="none-underline">
									                            									사유
									                            								</a>
								                            								</c:otherwise>
								                            							</c:choose>
											                            				<input type="checkbox" id="thirdWeek" name="thirdWeek" ${checked} style="display:none;">
											                            				
											                            			</c:when>
											                            			<c:otherwise>
											                            				<input type="checkbox" id="thirdWeek" name="thirdWeek" ${checked} >
											                            			</c:otherwise>
											                            		</c:choose>
											                            	</td>
								                            			</c:when>
								                            			<c:when test="${i.index == 3}">
								                            				<c:if test="${att.fourthWeek == 'Y' }" >
											                            		<c:set var="checked" value="checked"/>
											                            	</c:if>
											                            	<td>
											                            		<c:choose>
											                            			<c:when test="${att.sayu4 != '' && att.sayu4 != null}">
											                            			
											                            				<c:choose>
								                            								<c:when test="${((att.member.teamId == login.teamId && login.authId == 3) || login.authId < 3)}">
								                            									<a href="javascript:sayuPop('${att.id }', '${att.member.name }', '${sun }', '${att.sayu4}', '${i.index+1}', '1', '1')" title="${att.sayu4}" class="none-underline">
									                            									사유
									                            								</a>
								                            								</c:when>
								                            								<c:otherwise>
								                            									<a href="javascript:sayuPop('${att.id }', '${att.member.name }', '${sun }', '${att.sayu4}', '${i.index+1}', '0', '1')" title="${att.sayu4}" class="none-underline">
									                            									사유
									                            								</a>
								                            								</c:otherwise>
								                            							</c:choose>
								                            							<input type="checkbox" id="fourthWeek" name="fourthWeek" ${checked} style="display:none;">
								                            							
											                            			</c:when>
											                            			<c:otherwise>
											                            				<input type="checkbox" id="fourthWeek" name="fourthWeek" ${checked} >
											                            			</c:otherwise>
											                            		</c:choose>
											                            	</td>
								                            			</c:when>
								                            			<c:otherwise>
								                            				<c:if test="${att.fifthWeek == 'Y' }" >
											                            		<c:set var="checked" value="checked"/>
											                            	</c:if>
											                            	<td>
											                            		<c:choose>
											                            			<c:when test="${att.sayu5 != '' && att.sayu5 != null}">
											                            			
											                            				<c:choose>
								                            								<c:when test="${((att.member.teamId == login.teamId && login.authId == 3) || login.authId < 3)}">
								                            									<a href="javascript:sayuPop('${att.id }', '${att.member.name }', '${sun }', '${att.sayu5}', '${i.index+1}', '1', '1')" title="${att.sayu5}" class="none-underline">
									                            									사유
									                            								</a>
								                            								</c:when>
								                            								<c:otherwise>
								                            									<a href="javascript:sayuPop('${att.id }', '${att.member.name }', '${sun }', '${att.sayu5}', '${i.index+1}', '0', '1')" title="${att.sayu5}" class="none-underline">
									                            									사유
									                            								</a>
								                            								</c:otherwise>
								                            							</c:choose>
								                            							<input type="checkbox" id="fifthWeek" name="fifthWeek" ${checked} style="display:none;">
								                            							
											                            			</c:when>
											                            			<c:otherwise>
											                            				<input type="checkbox" id="fifthWeek" name="fifthWeek" ${checked} >
											                            			</c:otherwise>
											                            		</c:choose>
											                            	</td>
								                            			</c:otherwise>
								                            		</c:choose>
									                            </c:forEach>
									                            
									                            
									                            <c:choose>
							                            			<c:when test="${att.attYn == 'Y'}">
							                            				<input type="hidden" id="attYMemberId" name="attYMemberId" value="${att.member.id }" />
									                					<td class="${att.member.id} sortableFontSize"><input type="hidden" id="sortAttOrd" name="sortAttOrd" value="${att.attOrd }"></td>            			
							                            			</c:when>
									                            	<c:otherwise>
									                            		<input type="hidden" id="attNMemberId" name="attNMemberId" value="${att.member.id }" />
									                            		<td name="${att.member.id}"><input type="hidden" id="sortAttOrd" name="sortAttOrd" value="${att.attOrd }"></td>   
									                            	</c:otherwise>
									                            </c:choose>
									                            
									                       	 	</tr>
							                            				
								                        
						                        	</c:otherwise>
		                       			 		</c:choose>
								                        
								                        
					                    </c:forEach>
					                    <!-- 출석부 end -->
					                    
					                    
					                    </tbody>
				                    
				                    </c:otherwise>			                    	
		                    	</c:choose>
			                    	
			                    <tfoot>
			                    	<tr>
			                    		<td></td>
			                    		<td colspan="2">소계</td>
			                    		<c:set var="lastFirstWeekCnt" value="0"/>
			                    		<c:set var="lastSecondWeekCnt" value="0"/>
			                    		<c:set var="lastThirdWeekCnt" value="0"/>
			                    		<c:set var="lastFourthWeekCnt" value="0"/>
			                    		<c:set var="lastFifthWeekCnt" value="0"/>
			                    		<c:set var="firstWeekCnt" value="0"/>
			                    		<c:set var="secondWeekCnt" value="0"/>
			                    		<c:set var="thirdWeekCnt" value="0"/>
			                    		<c:set var="fourthWeekCnt" value="0"/>
			                    		<c:set var="fifthWeekCnt" value="0"/>
			                    		<c:forEach var="att" items="${attendanceList}" varStatus="i">
			                    		
			                    			<c:if test="${attendanceSearch.month != 1 and attendanceSearch.month != 7}">
			                    				<c:if test="${att.attYn == 'Y' }">
					                    			<c:if test="${att.lastFirstWeek == 'Y'}">
					                    				<c:set var="lastFirstWeekCnt" value="${lastFirstWeekCnt + 1}"/>
					                    			</c:if>
					                    			<c:if test="${att.lastSecondWeek == 'Y'}">
					                    				<c:set var="lastSecondWeekCnt" value="${lastSecondWeekCnt + 1}"/>
					                    			</c:if>
					                    			<c:if test="${att.lastThirdWeek == 'Y'}">
					                    				<c:set var="lastThirdWeekCnt" value="${lastThirdWeekCnt + 1}"/>
					                    			</c:if>
					                    			<c:if test="${att.lastFourthWeek == 'Y'}">
					                    				<c:set var="lastFourthWeekCnt" value="${lastFourthWeekCnt + 1}"/>
					                    			</c:if>
					                    			<c:if test="${att.lastFifthWeek == 'Y'}">
					                    				<c:set var="lastFifthWeekCnt" value="${lastFifthWeekCnt + 1}"/>
					                    			</c:if>
			                    				</c:if>
			                    			</c:if>
			                    			
			                    			<c:if test="${att.attYn == 'Y' }">
				                    			<c:if test="${att.firstWeek == 'Y'}">
				                    				<c:set var="firstWeekCnt" value="${firstWeekCnt + 1}"/>
				                    			</c:if>
				                    			<c:if test="${att.secondWeek == 'Y'}">
				                    				<c:set var="secondWeekCnt" value="${secondWeekCnt + 1}"/>
				                    			</c:if>
				                    			<c:if test="${att.thirdWeek == 'Y'}">
				                    				<c:set var="thirdWeekCnt" value="${thirdWeekCnt + 1}"/>
				                    			</c:if>
				                    			<c:if test="${att.fourthWeek == 'Y'}">
				                    				<c:set var="fourthWeekCnt" value="${fourthWeekCnt + 1}"/>
				                    			</c:if>
				                    			<c:if test="${att.fifthWeek == 'Y'}">
				                    				<c:set var="fifthWeekCnt" value="${fifthWeekCnt + 1}"/>
				                    			</c:if>
			                    			</c:if>
			                    		</c:forEach>
			                    		
			                    		 <c:if test="${(attendanceSearch.year == 2021 and attendanceSearch.month != 1 and attendanceSearch.month != 6) or 
			                    				(attendanceSearch.year != 2021 and attendanceSearch.month != 1 and attendanceSearch.month != 7)}">
				                    		<td class="lastAtt">${lastFirstWeekCnt }</td>
				                    		<td class="lastAtt">${lastSecondWeekCnt }</td>
				                    		<td class="lastAtt">${lastThirdWeekCnt }</td>
				                    		
				                    		<c:set var="borderRight" value="" />
						                	<c:if test="${lastSunday.size() eq 4 }">
												<c:set var="borderRight" value="border-right: 2px ridge" />
											</c:if>
																			
				                    		<td class="lastAtt" style="${borderRight}">${lastFourthWeekCnt }</td>
				                    		<c:if test="${lastSunday.size() > 4 }" >
				                    			<td class="lastAtt" style="border-right: 2px ridge;">${lastFifthWeekCnt }</td>
				                    		</c:if>
				                    	</c:if>
			                    		
			                    		<td>${firstWeekCnt }</td>
			                    		<td>${secondWeekCnt }</td>
			                    		<td>${thirdWeekCnt }</td>
			                    		<td>${fourthWeekCnt }</td>
		                    			<c:if test="${sunday.size() > 4 }">
		                    				<td>${fifthWeekCnt }</td>
		                    			</c:if>
			                    		<td></td>
			                    	</tr>
			                    </tfoot>
			                </table>
			                
			            </div>
			        </div>
			        </div>
	
				</div>
				
				<div class="div-temp1"></div>
		    	<!-- 팀장 이상만 적용 가능 -->
		    	<c:if test="${login.authId <= 4}">
			        <div class="layer_fixed3">
			        	<form>
					        <div class="form-bottom" style="text-align:center;">
				            	<button class="btn md-button2" type="button" onclick="attendanceRegist()">적용</button>
				          	</div>
			          	</form>
					</div>
				</c:if>
			
	    	</div>
	    </div>    
  </body>
  
  <input type="hidden" id="sortableIndex" name="sortableIndex" value="${sortableIndex }"/>
  <script src="${resourcesPath}/assets/js/attendance/list.js?${nowTime}"></script>

</html>
