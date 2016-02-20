<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<sec:authorize access="isAuthenticated()">
    <div class ="row" style="margin-left: 0px; color:blue;padding-right: 15px; padding-top: 5px;padding-bottom: 5px; font-weight: bold; border-radius: 0px 0px 12px 12px; border-bottom: 3px solid #fefefe;background-color: rgb(255,155,55) ">
        <div style="margin-left: 8px;">
           <c:if test="${localisationForm.dT == 'SITE'}">        
                <a style="color:white;" href="${pageContext.request.contextPath}/browse/site/${localisationForm.idLocalisation}">${localisationForm.cleLocalite} </a> 
            </c:if>
            <c:if test="${localisationForm.dT == 'BATIMENT'}">        
                <a style="color:white;" href="${pageContext.request.contextPath}/browse/batiment/${localisationForm.idLocalisation}">${localisationForm.cleLocalite} </a> 
            </c:if>
        <br/> 
     
        </div>   
     
    </div>
   <div style="margin-left: 8px; ">
    <div  style="padding: 4px; margin: 4px;display: inline-table " id="btnModifierObjetLocalisable">
        <button class="btn btn-default btn-xs" data-toggle="modal"  
                data-target="#localisationModalForm"   data-id="${localisationForm.idLocalisation}">
            <span class="glyphicon glyphicon-pencil" data-toggle="modal" data-target="#localisationModalForm"></span> Modifier 
        </button>

    </div>
    <div style="padding: 4px; margin: 4px; display: inline-table">
        <button class="btn btn-default btn-xs" data-title="localisationMapModalForm" data-target="#localisationMapModalForm"
                data-id="${localisationForm.idLocalisation}"
                data-nom="${localisationForm.nom}"
                data-lat="${localisationForm.latitude}"
                data-lon="${localisationForm.longitude}"             
                
                data-toggle="modal"  id="btnLocalisationMapModalForm" >
            <span class="glyphicon glyphicon-map-marker"></span> Géographie
        </button>

    </div>
    <c:if test="${localisationForm.dT == 'BATIMENT'}">
     <div  style="padding: 4px; margin: 4px; display: inline-table">
        <button class="btn btn-default btn-xs" data-toggle="modal" data-id="${localisationForm.idLocalisation}"  
                data-target="#addNiveauModal" id="btnAjouterNiveau" >
            <span class="glyphicon glyphicon-plus-sign" data-target="#addNiveauModal"></span> Niveau
        </button>
    </div>
     </c:if>
            
     <c:if test="${localisationForm.dT == 'SITE'}">
        <div  style="padding: 4px; margin: 4px; display: inline-table">
           <button class="btn btn-default btn-xs" data-toggle="modal" data-id="${localisationForm.idLocalisation}" 
                   data-target="#addLocalisationModal" id="btnAjouterBatiment" >
               <span class="glyphicon glyphicon-plus-sign" data-toggle="modal" data-target="#addLocalisationModal"></span> Batiment
           </button>
        </div>
     </c:if>
    
    <div  style="padding: 4px; margin: 4px;display: inline-table">
        <button class="btn btn-default btn-xs" data-toggle="modal"  
                data-target="#AffectProjectToProjet " id="btnAffecterProjet" >
            <span class="glyphicon glyphicon-floppy-save"></span> Dupliquer
        </button>
    </div>
    

    <div  style="padding: 4px; margin: 4px; display: inline-table">
        <button class="btn btn-default btn-xs" data-toggle="modal"  
                data-target="#AffectProjectToProgramme " id="btnAffectProjectToProgramme" >
            <span class="glyphicon glyphicon-download-alt"></span> Exporter
        </button>
    </div>

</div>

           
            
            
<!-- MODAL POUR MODIFICATION DE LA FICHE -->

<%@include file="./modalEditLocalisation.jsp" %>
<%@include file="./modalAddBatiment.jsp" %>
<%@include file="./modalViewLocalisationMap.jsp" %>
<%@include file="../niveau/modalAddNiveau.jsp" %>

    </sec:authorize>
               
