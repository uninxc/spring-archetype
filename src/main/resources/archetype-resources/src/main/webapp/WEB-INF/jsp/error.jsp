<!DOCTYPE html>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head lang="en">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no" />
<meta name="apple-touch-fullscreen" content="YES" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<link type="text/css" rel="stylesheet" href="/css/css.css">
<link type="text/css" rel="stylesheet" href="/css/Public.css">
<script type="text/javascript" src="/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="/js/RemMonitor.js"></script>
<title></title>
</head>
<body>
<div class="sol_page">
	<div class="content">
		<img class="kaola_logo" src="/images/sorry.png">
		<p>报错啦<br>
		  ${msg}
		</p>
	</div>
</div>
</body>
</html>