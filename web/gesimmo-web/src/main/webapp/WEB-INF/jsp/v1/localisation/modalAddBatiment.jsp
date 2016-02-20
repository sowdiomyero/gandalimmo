<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<sec:authorize access="isAuthenticated()">
    <div class="row" style="">
        <div class="modal fade " id="addLocalisationModal" tabindex="-1" role="dialog"
             aria-labelledby="addLocalisationModal" aria-hidden="true" >
            <div class="modal-dialog modal-large2">
                <div class="modal-content" >
                    <form:form commandName="batimentForm" action="${pageContext.request.contextPath}/batiment/new" method="post" id="addLocalisationModalForm">
                        <div class="modal-header" style="background-color: rgb(255,155,55)">
                            <h1 class="panel-title " style="color: white; font-size: 16px"> Nouveau Batiment</h1>
                        </div>
                        <div class="modal-body" style="color: #000033">
                            <fieldset><legend> Site : <strong style="color:red"> ${localisationForm.nom}</strong></legend>  

                                <label for="selectDtype"><spring:message code="label.localisation.tableau.header.dtype"/>: </label>
                                <form:input name="dT"  style="width:100%" class="form-control" required="required"  path="dT" placeholder="description" id="selectDtype" readonly="true"/>

                                <label for="addDescLocalisationInput"><spring:message code="label.localisation.tableau.header.description"/> : </label>
                                <form:input name="description" style="width:100%"  class="form-control" required="required"  path="description" placeholder="description" id="addDescLocalisationInput"/>

                                <label for="nomLocaliteCorrige"><spring:message code="label.localisation.tableau.header.nom"/>: </label>
                                <form:input name="nom" style="width:100%" class="form-control nomLocaliteCorrige" id="nomLocaliteCorrige" required="required"  path="nom" placeholder="" />

                                <div id="clefMessage"></div>
                                <label for="cleLocalite">Clef : </label>
                                <form:input class="form-control cleLocalite" style="width:100%" path="cleLocalite" id="cleLocalite" required="required"  placeholder="Cle de l'objet"/>
    
<%--                                
                                <label  for="nbNiveauxLocalisation"><spring:message code="label.localisation.tableau.header.nbniveaux"/>: </label>
                                <form:input name="nbNiveaux" style="width:100%" class="form-control graviteLocalisation" id="nbNiveauxLocalisation" required="required" placeholder="Nombre d'étages + RDC"  path="nbNiveaux" value="${localisationForm.nbNiveaux}" />
--%>

                                <label  for="typeLocalite">Type Batiment : </label>
                                <form:select id="typeLocalite" path="typeBatiment" name="type" class="form-control">
                                    <form:option value="" label="" /> 
                                    <form:options items="${batimentForm.typesBatiment}" />
                                </form:select>
                                
                                <label  for="typeLocalite">Etat Batiment : </label>
                                <form:select id="typeLocalite" path="etatBatiment" name="type" class="form-control">
                                    <form:option value="" label="" /> 
                                    <form:options items="${batimentForm.etatsBatiment}" />
                                </form:select>
                                
                                <label  for="selectResponsable">Responsable : </label>
                                <form:select id="selectResponsable" path="responsable" name="responsableAttribution" class="form-control">
                                    <form:option value="" label=""/> 
                                    <form:options items="${batimentForm.listResponsables}" itemLabel="fullName"  itemValue="idUser" />
                                </form:select>
                                
                               

                                <form:input type="hidden" path ="oldResponsable" value="${localisationForm.oldResponsable}" />
                                <form:input type="hidden" path ="idLocalisation" value="${localisationForm.idLocalisation}" />

                            </fieldset>
                        </div>
                        <div class="modal-footer ">
                            <button type="submit"  class="btn btn-info" value="submit"><spring:message code="label.bouton.valider"/></button>
                            <button type="button" id ="closeLocalisationForm" onClick=""  data-dismiss="modal" class="btn btn-warning"><spring:message code="label.bouton.fermer"/></button>
                        </div>

                    </form:form>                         
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div> <!-- Fin Modal Modification Fiche -->
    </div>
</sec:authorize>