<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<sec:authorize access="isAuthenticated()">
<div class="col-md-3 left_col">
    <div class="left_col scroll-view">

        <div class="navbar nav_title" style="border: 0;">
            <a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>GesImmo</span></a>
        </div>
        <div class="clearfix"></div>

        <!-- menu prile quick info -->
        <div class="profile">
            <div class="profile_pic">
                <img src="${pageContext.request.contextPath}/tmp/images/diom.jpg" alt="..." class="img-circle profile_img">
            </div>
            <div class="profile_info">
                <span>Bienvenue,</span>
                <h2>Diom Yéro SOW</h2>
            </div>
        </div>
        <!-- /menu prile quick info -->

        <br />

        <!-- sidebar menu -->
        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">

            <div class="menu_section">
                <h3>Commercial</h3>
                <ul class="nav side-menu">
                    <li ><a><i class="fa fa-tachometer"></i> Tableau de Bord </a></li>
                    <li ng-class="{'active' : default =='niveau'}"><a><i class="fa fa-home"></i> Niveaux <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu" style="display: block">
                            <li ng-repeat="niveau in niveaux"><a href="#" ng-bind="niveau.nom"> </a></li>
                            <!--                                        <li><a href="#">Etage 2</a>
                                                                    </li>
                                                                    <li><a href="#">Etage 3</a>
                                                                    </li>-->
                        </ul>
                    </li>
                    <li><a><i class="fa fa-edit"></i> Locaux <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu" style="display: none">
                            <li><a href="#">Appartements</a>
                            </li>
                            <li><a href="#">Bureaux</a>
                            </li>
                            <li><a href="#">Commerces</a>
                            </li>
                            <li><a href="#">Salles Formations</a>
                            </li>
                            <li><a href="#">Locataires</a>
                            </li>

                        </ul>
                    </li>
                    <li><a><i class="fa fa-desktop"></i> Demandes <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu" style="display: none">
                            <li><a href="general_elements.html">Demandes En Cours</a>
                            </li>
                            <li><a href="media_gallery.html">Demandes Ouvertes</a>
                            </li>
                            <li><a href="typography.html">Demandes Résolues</a>
                            </li>

                            <li><a href="calender.html">Calender d'Interventions</a>
                            </li>
                        </ul>
                    </li>
                    <li><a><i class="fa fa-table"></i> Prestataires </a></li>
                    <li><a><i class="fa fa-bar-chart-o"></i> Equipements </a></li>
                    <li><a><i class="fa fa-bar-chart-o"></i> Activités</a></li>                                
                </ul>
            </div>

        </div>
        <!-- /sidebar menu -->

        <!-- /menu footer buttons -->
        <div class="sidebar-footer hidden-small">
            <a data-toggle="tooltip" data-placement="top" title="Settings">
                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
            </a>
            <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
            </a>
            <a data-toggle="tooltip" data-placement="top" title="Lock">
                <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
            </a>
            <a data-toggle="tooltip" data-placement="top" title="Logout">
                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
            </a>
        </div>
        <!-- /menu footer buttons -->
    </div>
</div>
            
</sec:authorize>
