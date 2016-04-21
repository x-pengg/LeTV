<%--
  Created by IntelliJ IDEA.
  User: chan
  Date: 16/4/21
  Time: 下午10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>直播</title>
    <script type="text/javascript" charset="utf-8" src="yuntv.letv.com/player/live/blive.js"></script>
</head>
<body>
</body>
<script>
    var element = document.createElement('div');
    element.setAttribute("id","player");
    document.body.appendChild(element);
    var player = new CloudLivePlayer();
    var playerConf = {
        activityId:"${activityId}"
    };
    player.init(playerConf,"player");
</script>
</html>
