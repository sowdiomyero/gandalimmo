<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
            
<sec:authorize access="isAuthenticated()">
<div class="row tile_count">
    <div class="animated flipInY col-md-2 col-sm-4 col-xs-4 tile_stats_count">
        <div class="left"></div>
        <div class="right">
            <span class="count_top"><i class="fa fa-tree"></i> Total Niveaux</span>
            <div class="count">{{niveaux.length}} Niveaux</div>
            <span class="count_bottom"><i class="green">24 </i> Locataires</span>
        </div>
    </div>
    <div class="animated flipInY col-md-2 col-sm-4 col-xs-4 tile_stats_count">
        <div class="left"></div>
        <div class="right">
            <span class="count_top"><i class="fa fa-user"></i> Total Locataires</span>
            <div class="count">6 Locataires</div>
            <span class="count_bottom"><a href="#" style="color:blue">Plus de Détails</a></span>
        </div>
    </div>
    <div class="animated flipInY col-md-2 col-sm-4 col-xs-4 tile_stats_count">
        <div class="left"></div>
        <div class="right">
            <span class="count_top"><i class="fa fa-money"></i> Total Loyers</span>
            <div class="count green">1.386.000</div>
            <span class="count_bottom"><a href="#" style="color:blue">Plus de Détails</a></span>
        </div>
    </div>
    <div class="animated flipInY col-md-2 col-sm-4 col-xs-4 tile_stats_count">
        <div class="left"></div>
        <div class="right">
            <span class="count_top"><i class="fa fa-hourglass"></i> Taux Occupation</span>
            <div class="count">89% Occupation</div>
            <span class="count_bottom"><a href="#" style="color:blue">Plus de Détails</a></span>
        </div>
    </div>
    <div class="animated flipInY col-md-2 col-sm-4 col-xs-4 tile_stats_count">
        <div class="left"></div>
        <div class="right">
            <span class="count_top"><i class="fa fa-life-bouy"></i> Demandes Intervention</span>
            <div class="count">5 Interventions</div>
            <span class="count_bottom"><a href="#" style="color:blue">Plus de Détails</a></span>
        </div>
    </div>
    <div class="animated flipInY col-md-2 col-sm-4 col-xs-4 tile_stats_count">
        <div class="left"></div>
        <div class="right">
            <span class="count_top"><i class="fa fa-life-bouy"></i> Demandes Intervention</span>
            <div class="count">5 Demandes</div>
            <span class="count_bottom"> <a href="#" style="color:blue">Plus de Détails</a></span>
        </div>
    </div>


</div>

<div class="row">
    <div  style="float:right; padding-right: 10px">
     <span class="count_top" ng-click="reloadData()" ><i class="fa fa-refresh" ></i></span>   
</div>
    
</div>

  
</sec:authorize>
