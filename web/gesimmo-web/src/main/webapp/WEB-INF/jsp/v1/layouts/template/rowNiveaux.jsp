<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<sec:authorize access="isAuthenticated()">
<div class="row">
    <div class="col-md-4 col-sm-4 col-xs-12" ng-repeat="niveau in niveaux">
        <div class="x_panel tile fixed_height_390">
            <div class="x_title">
                <h2>Niveau {{ niveau.nom }}</h2>
                <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    <li><a class="close-link"><i class="fa fa-close"></i></a></li>
                </ul>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <h4>Informations détaillées</h4>
                <div class="widget_summary">
                    <div class="w_left w_25">
                        Nom :
                    </div>
                    <div class="w_center w_55">
                        <p> {{ niveau.nom }}</p>
                    </div>
                    <div class="clearfix"></div>
                </div>
                
                <div class="widget_summary">
                    <div class="w_left w_25">
                        Superficie :
                    </div>
                    <div class="w_center w_55">
                        <p> {{ niveau.superficie }}</p>
                    </div>
                    <div class="clearfix"></div>
                </div>
                
                <div class="widget_summary">
                    <div class="w_left w_25">
                        Etat :
                    </div>
                    <div class="w_center w_55">
                        <p> {{ niveau.etat }}</p>
                    </div>
                    <div class="clearfix"></div>
                </div>
                
                <div class="widget_summary">
                    <div class="w_left w_25">
                        Etage :
                    </div>
                    <div class="w_center w_55">
                        <p> {{ niveau.etage }}</p>
                    </div>
                    <div class="clearfix"></div>
                </div>

            </div>
        </div>
    </div>

</div>

</sec:authorize>