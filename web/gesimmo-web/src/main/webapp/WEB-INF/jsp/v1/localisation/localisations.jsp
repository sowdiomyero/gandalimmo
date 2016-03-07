<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



<sec:authorize access="isAuthenticated()">


    <!-- Begin Body -->

    <div id="menu_actif_location" class="container" style="margin-top: 1px; margin-bottom: 0px;">        
        <div class="center-block" >
            <center><h4><spring:message code="label.localisation.header.titre" /></h4></center>
        </div>           
    </div>

    <div class="container-fluid" style="margin-left: 5px; margin-right: 5px; ">    
        <c:if test="${filterLocalisationForm != null}">

            <div class="row localisationFilterTab" style=""> 
                <form:form commandName="filterLocalisationForm" id="localisationFilterForm" action="${pageContext.request.contextPath}/localisation/filtrer" method="GET">
                    <form:select id="localisationFilter" path="objetSaisi" name="dtype" style="" class="filter pull-left">
                        <form:option value="ALL" label="Tous les Types"/> 
                        <form:options items="${filterLocalisationForm.objets}" />             
                    </form:select>

                    <form:select id="selectZone" path="zoneSaisi" name="zone" class="filter pull-left" style="">
                        <form:option value="ALL" label="Toutes les Zones"/> 
                        <c:forEach items="${filterLocalisationForm.zones}" var="mapEntry" >
                            <form:option value="${mapEntry.key}" label="${mapEntry.value}"/> 
                        </c:forEach>
                    </form:select>

                    <form:select id="stateFilter" path="etatSaisi" name="etat" style="" class="filter pull-left">	  			
                        <form:option value="ALL" label="Tous les Etats"/>
                        <form:options items="${filterLocalisationForm.etats}" />
                    </form:select> 
                    <input type="submit" value="Filtrer" style="margin-top: 5px; width: 75px;  "/>
                </form:form>
            </div>

        </c:if>

        <div class="row">
            <c:choose>
                <c:when test="${localisationForm != null}">
                    <!--            Fiche objet localisable -->
                    <div  class="col-md-3 resizable" style="min-height: 500px; overflow-y: auto; border-right: 1px solid #EEE;" >
                        <%@include file="./listeObjetsLocalisables.jsp" %>                    
                    </div>
                    <div class="col-md-8" style="margin-left: 10px">  
                        <!--                Tableau utilisateur-->
                        <div class="row" style="margin-bottom: 15px">
                            <div class="col-lg-12" style="margin-left: 10px">
                                <div class="row "  id="localisationItemArea" >
                                    <%@include file="./ficheLocalisation.jsp" %>
                                </div> 
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:when test="${localisationForm == null}">
                     
                    <div style="position: relative; top: 200px; left: 400px; z-index: 1; 
                         background-color: rgba(0,0,0,0.4); color: white; height: 30%; width: 50%; font-weight: bold;
                         font-size: 22px; ">
                        
                        <p style="text-align: center; padding: 5px;">Aucune donnée ne correspond à votre recherche</p>
                        
                    </div>
                   
                </c:when>

            </c:choose>


        </div>

    </div>
    <script src="http://maps.googleapis.com/maps/api/js?extension=.js&output=embed"></script>
    <script src="${pageContext.request.contextPath}/js/cartographie.js"></script> 
</sec:authorize>

