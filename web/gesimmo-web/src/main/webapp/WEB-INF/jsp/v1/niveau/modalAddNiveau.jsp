<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<sec:authorize access="isAuthenticated()">
    <div class="row" style="">
        <div class="modal fade " id="addNiveauModal" tabindex="-1" role="dialog"
             aria-labelledby="addNiveauModal" aria-hidden="true" >
            <div class="modal-dialog modal-large2">
                <div class="modal-content" >
                    <form:form commandName="niveauForm" action="${pageContext.request.contextPath}/niveau/new" method="post" id="addNiveauModalForm" role="form">
                        <div class="modal-header" style="background-color: rgb(255,155,55)">
                            <h1 class="panel-title " style="color: white; font-size: 16px"> Nouveau Niveau</h1>
                        </div>
                        <div class="modal-body" style="color: #000033">
                               <fieldset><legend> Batiment : <strong style="color:red"> ${localisationForm.nom}</strong></legend>  

                                <label for="libelleNiveau"><spring:message code="niveau.form.libelle"/> : </label>
                                <form:input name="libelle"  style="width:100%" class="form-control" required="required"  path="libelleNiveau" placeholder="Libellé" id="libelleNiveau" />
    
                                <label for="superficieNiveau"><spring:message code="niveau.form.superficie"/> (m²): </label>
                                <input name="superficieNiveau"  style="width:100%" class="form-control" required="required" type="number"  name="superficieNiveau" placeholder="Supérficie" id="superficieNiveau" />
                                
                                <label  for="level"><spring:message code="niveau.form.level"/> :  </label>
                                <form:select id="level" path="level" name="level" class="form-control" required="required">
                                    <form:option value="" label="" /> 
                                    <form:options items="${niveauForm.levels}" />
                                </form:select>
                                
                                <div style="margin-bottom: 1px; margin-top: 1px;">
                                    <div ><label  for="etat">Etat : </label></div>
                                    <div >
                                        <form:select id="etat" path="etat" name="etat" class="form-control segment-select">
                                           
                                            <form:options items="${niveauForm.etats}" />
                                        </form:select>
                                    </div>
                                </div>
                              
                            <div style=" margin-top: 2px; ">    
                                <div class="row" style="margin-top: 4px; margin-left: 2px">
                                 
                                        <label for="camera"><spring:message code="niveau.form.camera"/> </label>
                                        <form:checkbox path="camera" data-toggle="toggle" data-size="mini" ></form:checkbox>
                                   
                                    
                                   
                                      <label for="wifi"><spring:message code="niveau.form.wifi"/> </label>
                                       <form:checkbox  path="wifi" data-toggle="toggle" data-size="mini" ></form:checkbox>
                                    

                                    
                                                             
                                      <label for="ascensseur"><spring:message code="niveau.form.ascensseur"/> </label>
                                       <input type="checkbox" name="ascenseur" data-toggle="toggle" data-size="mini" data-style="ios">
                                  
                                    
                               
                                    
                                
                                                             
                                      <label for="extincteur"><spring:message code="niveau.form.extincteur"/> </label>
                                       <form:checkbox  path="extincteur" data-toggle="toggle" data-size="mini"></form:checkbox>
                           
                                </div>    

                            </div> 

                                <form:input type="hidden" path ="idLocalisation" name="idLocalisation" value="${localisationForm.idLocalisation}" />

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
    
    <script type="text/javascript">

        function applyTransformatio(){
             $(".segment-select").Segment();
             $('input[type="checkbox"]').bootstrapToggle({
                  on: 'Oui',
                  onstyle :'success',
                  offstyle: 'danger',
                  style:'ios',
                  off: 'Non'
             });
        };
        
        applyTransformatio();
        

    </script>
</sec:authorize>