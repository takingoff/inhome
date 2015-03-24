<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>控制中心</title>
    
	<link rel="stylesheet" href="/InnMIS/jqueryUi/css/${sessionScope.theme }/jquery-ui.css">
	<link rel="stylesheet" href="/InnMIS/css/controlCenter/controlCenter.css">
	<script src="/InnMIS/jqueryUi/js/jquery-1.10.2.js"></script>
	<script src="/InnMIS/jqueryUi/js/jquery-ui-1.10.4.custom.js"></script>
	<script src="/InnMIS/js/controlCenter/menu.js"></script>
	<script src="/InnMIS/js/controlCenter/controlCenter.js"></script>
	
	<style type="text/css">
	</style>

</head>
<body>
	<!-- ////////////////////////////////////////////////////////////////////////////////////// tab head-->
	<!-- 要注意的是菜单头id必须是 xxxMenuHeadId 菜单id必须是xxxMenuId  因为添加效果就是根据这样的通配符来作为选择器的 --> 
	<div id="tabs" >
		<ul>
			<li id="todayMenuHeadId"><a href="#tabs-0" onclick='displayRoomiframe()'>今日统计</a></li>
			<li id="enteredInfoMenuHeadId"><a href="#tabs-0" onclick='displayEnteredInfoiframe()'>入 住 信 息</a></li>
			<li id="orderMenuHeadId" ><a href="#tabs-0" onclick='displayBilliframe()'>账单 统 计</a>	</li>
			<li id="roomMenuHeadId" ><a href="#tabs-0" onclick='displayRoomTypeiframe()'>房间 类 型</a></li>
			
		</ul>
		
		<div id="tabs-0">
			
		
			<div  id="todayMenuId" class="ui-widget-header">
				<button id="todayInfoPin"  onclick="todayInfoPinToggle()"></button>&ensp; &ensp;&ensp;&ensp;
				<a class="todayInfo" onclick="beforAccessOrder(3)">待住订单<%out.print("1"); %></a>&ensp; &ensp;&ensp;&ensp;
				<a class="todayInfo" onclick="beforAccessOrder(4)">新增订单<%out.print("1"); %></a>&ensp; &ensp;&ensp;&ensp;
				<a class="todayInfo" onclick="beforQueryRoom(1)">新入住<%out.print("1"); %></a>&ensp; &ensp;&ensp;&ensp;
				<a class="todayInfo" onclick="beforQueryRoom(2)">待退房<%out.print("1"); %></a>&ensp; &ensp;&ensp;&ensp;
				<a class="todayInfo" onclick="beforQueryRoom(3)">所有在住<%out.print("1"); %></a>&ensp; &ensp;&ensp;&ensp;
				<a class="todayInfo" onclick="beforeRequestEmptyRoom(1)">所有可用空房<%out.print("1"); %></a>&ensp; &ensp;&ensp;&ensp;
				<!-- <a class="todayInfo" href="">已退房xx</a>&ensp; &ensp;&ensp;&ensp; -->
				<a class="todayInfo" onclick="beforRequestTodayRoomStatistics()">剩余房统计<%out.print("1"); %></a>&ensp; &ensp;&ensp;&ensp;&ensp; &ensp;&ensp;&ensp;
				刷新：	<a id="freshButton" onclick="freshClick()"></a>
				关闭：<button id="dialogClose" onclick="closeAllDialog()"></button>
				退出：<button id="poweroff" onclick="staffLogout()"></button>
				设置：<a id="setting" target="_blank" href="#"></a>
			</div>
		
			<ul id="enteredInfoMenuId" class="positionable menu" >
				<!-- <li><a target="_blank" href="#">账目统计</a>
				</li>
				<li><a target="_blank" href="#">房间剩余统计</a>
				</li>
				<li><a target="_blank" href="#">入住信息统计</a>
				</li> -->
			</ul>
			
			<ul id="roomMenuId"  class="positionable menu" >
			</ul>
			
			<ul id="orderMenuId" class="positionable menu" >
			</ul>
			
			<div id="neckId" class="ui-widget">
				<label for="searchRoomId">房间查询： </label> <input id="searchRoomId" title="可输入，房间号，电话号码，姓名查询" ><button id="searchRoomButton" ></button>&ensp;&ensp;&ensp;
				<label for="themeBoxId">主题： </label> 
					<select id="themeBoxId" >
						<option value="hot-sneaks" <c:if test="${sessionScope.theme eq 'hot-sneaks'}">selected="selected"</c:if>>hot-sneaks</option>
						<option value="blitzer" <c:if test="${sessionScope.theme eq 'blitzer'}">selected="selected"</c:if>>blitzer</option>
						<option value="flick" <c:if test="${sessionScope.theme eq 'flick'}">selected="selected"</c:if>>flick</option>
						<option value="pepper-grinder" <c:if test="${sessionScope.theme eq 'pepper-grinder'}">selected="selected"</c:if>>pepper-grinder</option>
						<option value="swanky-purse" <c:if test="${sessionScope.theme eq 'swanky-purse'}">selected="selected"</c:if>>swanky-purse</option>
						<option value="ui-lightness" <c:if test="${sessionScope.theme eq 'ui-lightness'}">selected="selected"</c:if>>ui-lightness</option>
						<option value="redmond" <c:if test="${sessionScope.theme eq 'redmond'}">selected="selected"</c:if>>redmond</option>
						<option value="excite-bike" <c:if test="${sessionScope.theme eq 'excite-bike'}">selected="selected"</c:if>>excite-bike</option>
						<option value="south-street" <c:if test="${sessionScope.theme eq 'south-street'}">selected="selected"</c:if>>south-street</option>
						<option value="black-tie" <c:if test="${sessionScope.theme eq 'black-tie'}">selected="selected"</c:if> >black-tie</option>
						<option value="cupertino" <c:if test="${sessionScope.theme eq 'cupertino'}">selected="selected"</c:if>>cupertino</option>
						<option value="dark-hive" <c:if test="${sessionScope.theme eq 'dark-hive'}">selected="selected"</c:if>>dark-hive</option>
						<option value="dot-luv" <c:if test="${sessionScope.theme eq 'dot-luv'}">selected="selected"</c:if>>dot-luv</option>
						<option value="eggplant" <c:if test="${sessionScope.theme eq 'eggplant'}">selected="selected"</c:if>>eggplant</option>
						<option value="humanity" <c:if test="${sessionScope.theme eq 'humanity'}">selected="selected"</c:if>>humanity</option>
						<option value="le-frog" <c:if test="${sessionScope.theme eq 'le-frog'}">selected="selected"</c:if>>le-frog</option>
						<option value="mint-choc" <c:if test="${sessionScope.theme eq 'mint-choc'}">selected="selected"</c:if>>mint-choc</option>
						<option value="overcast" <c:if test="${sessionScope.theme eq 'overcast'}">selected="selected"</c:if>>overcast</option>
						<option value="smoothness" <c:if test="${sessionScope.theme eq 'smoothness'}">selected="selected"</c:if>>smoothness</option>
						<option value="start" <c:if test="${sessionScope.theme eq 'start'}">selected="selected"</c:if>>start</option>
						<option value="sunny" <c:if test="${sessionScope.theme eq 'sunny'}">selected="selected"</c:if>>sunny</option>
						<option value="trontastic" <c:if test="${sessionScope.theme eq 'trontastic'}">selected="selected"</c:if>>trontastic</option>
						<option value="ui-darkness" <c:if test="${sessionScope.theme eq 'ui-darkness'}">selected="selected"</c:if>>ui-darkness</option>
						<option value="vader" <c:if test="${sessionScope.theme eq 'vader'}">selected="selected"</c:if>>vader</option>
					</select>
				<button id="submitThemeButton" onclick="changeInnEntry('theme','themeBoxId')"></button>&ensp;&ensp;&ensp;
				
				<label for="backGroundBoxId">背景： </label> 
					<select id="backGroundBoxId" >
						<option value="ui-state-default" <c:if test="${sessionScope.back_Ground eq 'ui-state-default'}">selected="selected"</c:if> >默认</option>
						<option value="ui-widget-content" <c:if test="${sessionScope.back_Ground eq 'ui-widget-content'}">selected="selected"</c:if> >内容</option>
						<option value="ui-state-hover" <c:if test="${sessionScope.back_Ground eq 'ui-state-hover'}">selected="selected"</c:if> >停留</option>
						<option value="ui-state-active" <c:if test="${sessionScope.back_Ground eq 'ui-state-active'}">selected="selected"</c:if> >活动</option>
						<option value="ui-state-focus" <c:if test="${sessionScope.back_Ground eq 'ui-state-focus'}">selected="selected"</c:if> >焦点</option>
						<option value="ui-state-hightlight" <c:if test="${sessionScope.back_Ground eq 'ui-state-hightlight'}">selected="selected"</c:if> >高亮</option>
						<option value="" <c:if test="${sessionScope.back_Ground eq ''}">selected="selected"</c:if> >无</option>
					</select>
				<button id="submitBackGroundButton" onclick="changeInnEntry('back_Ground','backGroundBoxId')"></button>&ensp;&ensp;&ensp;
				
				<label for="foreGroundBoxId">前景： </label> 
					<select id="foreGroundBoxId" >
						<option value="ui-state-default" <c:if test="${sessionScope.fore_Ground eq 'ui-state-default'}">selected="selected"</c:if> >默认</option>
						<option value="ui-widget-content" <c:if test="${sessionScope.fore_Ground eq 'ui-widget-content'}">selected="selected"</c:if> >内容</option>
						<option value="ui-state-hover" <c:if test="${sessionScope.fore_Ground eq 'ui-state-hover'}">selected="selected"</c:if> >停留</option>
						<option value="ui-state-active" <c:if test="${sessionScope.fore_Ground eq 'ui-state-active'}">selected="selected"</c:if> >活动</option>
						<option value="ui-state-focus" <c:if test="${sessionScope.fore_Ground eq 'ui-state-focus'}">selected="selected"</c:if> >焦点</option>
						<option value="ui-state-hightlight" <c:if test="${sessionScope.fore_Ground eq 'ui-state-hightlight'}">selected="selected"</c:if> >高亮</option>
					</select>
				<button id="submitForeGroundButton" onclick="changeInnEntry('fore_Ground','foreGroundBoxId')"></button>&ensp;&ensp;&ensp;
				
				<label>切换视图： </label> 
				<button id="changeRoomViewButton" onclick="changeRoomView()"></button>&ensp;&ensp;&ensp;
				
				<label>刷新房态： </label> 
				<button id="refreshRoomTrendButton" onclick="refreshRoomTrend()"></button>&ensp;&ensp;&ensp;
				登录管理员：${sessionScope.STAFF_NAME }
			</div>
		

		</div>
		
	</div>
	
	
	<!-- //////////////////////////////////////////////////////////////////////////////////////content -->
	<!-- //////////////////room -->
	<div class="${sessionScope.back_Ground}" >
		<iframe id="iframeRoom" width="100%"  src="/InnMIS/room/roomTrendView.do" onload='iframeHeight("iframeRoom")'></iframe>
	</div>
	<div class="${sessionScope.back_Ground}">
		<iframe id="iframeRoomPage" width="100%"  src="/InnMIS/room/roomPageView.do" onload='iframeHeight("iframeRoomPage")'></iframe>
	</div>
	<!-- //////////////////roomType -->
	<div class="${sessionScope.back_Ground}"  >
		<iframe id="iframeRoomType" width="100%"   onload='iframeHeight("iframeRoomType")'></iframe>
	</div>
	
	<!-- //////////////////enteredInfo -->
	<div class="${sessionScope.back_Ground}"  >
		<iframe id="iframeEnteredInfo" width="100%" onload='iframeHeight("iframeEnteredInfo")' ></iframe>
	</div>
	<!-- //////////////////bill -->
	<div class="${sessionScope.back_Ground}"  >
		<iframe id="iframeBill" width="100%" onload='iframeHeight("iframeBill")' ></iframe>
	</div>
	
	<!-- //////////////////order -->
	<div class="${sessionScope.back_Ground}" >
		<iframe id="iframeOrder" width="100%"  src="" ></iframe>
	</div>
	
	
</body>
</html>
