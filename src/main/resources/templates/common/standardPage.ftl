<#import "/spring.ftl" as spring />

<#macro standardPage title>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <title>${title}</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="Clean responsive bootstrap website template">
  <meta name="author" content="">
  <!-- styles -->
  <link href="<@spring.url '/css/bootstrap.css'/>" rel="stylesheet">
  <link href="<@spring.url '/css/bootstrap-responsive.css'/>" rel="stylesheet">
  <link href="<@spring.url '/css/docs.css'/>" rel="stylesheet">
  <link href="<@spring.url '/css/prettyPhoto.css'/>" rel="stylesheet">
  <link href="<@spring.url '/js/google-code-prettify/prettify.css'/>" rel="stylesheet">
  <link href="<@spring.url '/css/flexslider.css'/>" rel="stylesheet">
  <link href="<@spring.url '/css/refineslide.css'/>" rel="stylesheet">
  <link href="<@spring.url '/css/font-awesome.css'/>" rel="stylesheet">
  <link href="<@spring.url '/css/animate.css'/>" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:400italic,400,600,700" rel="stylesheet">

  <link href="<@spring.url '/css/style.css'/>" rel="stylesheet">
  <link href="<@spring.url '/css/color/default.css'/>" rel="stylesheet">

  <!-- fav and touch icons -->
  <link rel="shortcut icon" href="<@spring.url '/ico/favicon.ico'/>">
  <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<@spring.url '/ico/apple-touch-icon-144-precomposed.png'/>">
  <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<@spring.url '/ico/apple-touch-icon-114-precomposed.png'/>">
  <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<@spring.url '/ico/apple-touch-icon-72-precomposed.png'/>">
  <link rel="apple-touch-icon-precomposed" href="<@spring.url '/ico/apple-touch-icon-57-precomposed.png'/>">

  <!-- =======================================================
    Theme Name: Plato
    Theme URL: https://bootstrapmade.com/plato-responsive-bootstrap-website-template/
    Author: BootstrapMade.com
    Author URL: https://bootstrapmade.com
  ======================================================= -->
</head>

<body>
<#include "header.ftl">  
	
</#macro>

