<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<nav class='sidebar sidebar-menu-collapsed bgcode' style="z-index: 2000"> 
            <a href='#' id='justify-icon'>
                <span class='glyphicon glyphicon-align-justify'></span>
            </a>
                 
            <ul class='level1'>
                
                 <li class='active'> <a class='expandable' href='#' title='Dashboard'>
                        <span class='glyphicon glyphicon-home collapsed-element'></span>
                        <span class='expanded-element'>Site</span>
                    </a>

                    <ul class='level2'>
                        <li> <a href='#' title='Traffic'>Batiment iPod</a>

                        </li>
                        <li> <a href='#' title='Conversion rate'>Batiment iPad</a>

                        </li>
                        <li> <a href='#' title='Purchases'>Batiment iPhone</a>

                        </li>
                    </ul>
                </li>
                <li> <a class='expandable' href='#' title='Parkings'>
                        <span class='glyphicon glyphicon-registration-mark collapsed-element'></span>
                        <span class='expanded-element'>Acteurs</span>
                    </a>

                </li>
                <li> <a class='expandable' href='#' title='Infrastructures'>
                        <span class='glyphicon glyphicon-camera collapsed-element'></span>
                        <span class='expanded-element'>Infrastructures</span>
                    </a>

                </li>
                <li> <a class='expandable' href='#' title='Interventions'>
                        <span class='glyphicon glyphicon-calendar collapsed-element'></span>
                        <span class='expanded-element'>Interventions</span>
                    </a>

                </li>

                 <li> <a class='expandable' href='#' title='Statistiques'>
                        <span class='glyphicon glyphicon-wrench collapsed-element'></span>
                        <span class='expanded-element'>Statistiques</span>
                    </a>

                </li>
<!--                 <li> <a class='expandable' href='#' title='Prestataires'>
                        <span class='glyphicon glyphicon-fire collapsed-element'></span>
                        <span class='expanded-element'>Prestataires</span>
                    </a>

                </li>-->
                <li> <a class='expandable' href='#' title='Settings'>
                        <span class='glyphicon glyphicon-cog collapsed-element'></span>
                        <span class='expanded-element'>Administration </span>
                    </a>

                </li>
                
            </ul> 
            <a href='${pageContext.request.contextPath}/j_spring_security_logout' id='logout-icon' title='Logout'>
                <span class='glyphicon glyphicon-off'></span>
            </a>

        </nav>