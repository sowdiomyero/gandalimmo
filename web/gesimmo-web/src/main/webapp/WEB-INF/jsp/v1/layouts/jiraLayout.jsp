<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">

    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" >
        <title>Gestion Immo</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
            <link href="${pageContext.request.contextPath}/css/idyal_ccs.css" type="text/css" rel="stylesheet">
            <link href="${pageContext.request.contextPath}/css/gandal.css" type="text/css" rel="stylesheet">
            <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
            <link href="${pageContext.request.contextPath}/css/jquery.dataTables.css" type="text/css" rel="stylesheet">
            <link href="${pageContext.request.contextPath}/css/dataTables.responsive.css" type="text/css" rel="stylesheet">
            <link href="${pageContext.request.contextPath}/css/sb-admin-rtl.css" type="text/css" rel="stylesheet">
            <link href="${pageContext.request.contextPath}/css/sb-admin.css" type="text/css" rel="stylesheet">
            <link href="${pageContext.request.contextPath}/css/font-awesome.css" type="text/css" rel="stylesheet">
            <link href="${pageContext.request.contextPath}/css/selectize.css" type="text/css" rel="stylesheet">
            <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/jquery-dataTable.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/gandalFunctions.js"></script>
            <script src="${pageContext.request.contextPath}/js/gandalAjax.js"></script>
            <script src="${pageContext.request.contextPath}/js/notify.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/gesimmo.js"></script>                                             
            <script src="${pageContext.request.contextPath}/js/selectize.js"></script>
            <script src="${pageContext.request.contextPath}/js/dataTables.responsive.js"></script>
            <style>
                 body {
                    background: none repeat scroll 0 0 white;
                    overflow-x: hidden;
                }
                nav.sidebar-menu-collapsed {
                    width: 44px;
                }
                nav.sidebar-menu-expanded {
                    width: auto;
                }
                nav.sidebar {
                    position: fixed;
                    top: 50px;
                    bottom: 50px;
                    padding: 20px 10px;                    
                    left: 0px;                    
                    height: 100%;
/*                    background: none repeat scroll 0 0 #0099ff;*/
                    background:rgba(26,04,82,0.47);
                    color: white;
                    
                }
                nav.sidebar a#justify-icon {
                    outline: 0;
                    color: white;
                    font-size: 24px;
                    font-style: normal;
                }
                nav.sidebar a#logout-icon {
                    outline: 0;
                    color: white;
                    font-size: 24px;
                    font-style: normal;
                    position: absolute;
                    bottom: 50px;
                    left: 10px;
                }
                nav.sidebar ul.level1 {
                    margin: 0;
                    padding: 0;
                    margin-top: 20px;
                }
                nav.sidebar ul.level1 li:hover {
/*                   background: rgb(255,155,55);*/

                   color: white;
                }
                nav.sidebar ul.level1 li {
                    margin: 0;
                    padding: 0;
                    margin-top: 20px;
                    list-style-type: none;
                }
                nav.sidebar ul.level1 li a.expandable {
                    outline: 0;
                    display: block;
                    position: relative;
                    width: 100%;
                    height: 30px;
                    color: white;
                    text-decoration: none;
                    text-align: left;
                    padding: 4px 4px 4px 0px;
                    font-size: 20px;
                }
                nav.sidebar ul.level1 li a.expandable:hover {
                    color: #bbbbbb;
                }
                nav.sidebar ul.level1 li a.expandable span.expanded-element {
                    display: none;
                    font-size: 11px;
                    position: relative;
                    bottom: 5px;
                }
                nav.sidebar ul.level1 li.active {
                    margin-left: -4px;
                }
                nav.sidebar ul.level1 li.active a.expandable {
                    background: none repeat scroll 0 0 black;
                    border-radius: 4px;
                    color: white !important;
                    width: 30px;
                    padding: 4px;
                }
                nav.sidebar ul.level1 li.active a.expandable:hover {
                    color: white !important;
                }
                nav.sidebar ul.level1 ul.level2 {
                    margin: 20px 6px 20px 30px;
                    padding: 0;
                    display: none;
                }
                nav.sidebar ul.level1 ul.level2 li {
                    margin: 0;
                    padding: 0;
                    margin-top: 10px;
                    padding-bottom: 10px;
                    list-style-type: none;
                    border-bottom: solid white 1px;
                }
                nav.sidebar ul.level1 ul.level2 li:last-child {
                    border-bottom: none;
                }
                nav.sidebar ul.level1 ul.level2 li a {
                    text-decoration: none;
                    outline: 0;
                    color: white;
                    text-decoration: none;
                    font-size: 11px;
}
            </style>
            <script type='text/javascript'>
  (function () {
    $(function () {
        var SideBAR;
        SideBAR = (function () {
            function SideBAR() {}

            SideBAR.prototype.expandMyMenu = function () {
                return $("nav.sidebar").removeClass("sidebar-menu-collapsed").addClass("sidebar-menu-expanded");
            };

            SideBAR.prototype.collapseMyMenu = function () {
                return $("nav.sidebar").removeClass("sidebar-menu-expanded").addClass("sidebar-menu-collapsed");
            };

            SideBAR.prototype.showMenuTexts = function () {
                return $("nav.sidebar ul a span.expanded-element").show();
            };

            SideBAR.prototype.hideMenuTexts = function () {
                return $("nav.sidebar ul a span.expanded-element").hide();
            };

            SideBAR.prototype.showActiveSubMenu = function () {
                $("li.active ul.level2").show();
                return $("li.active a.expandable").css({
                    width: "100%"
                });
            };

            SideBAR.prototype.hideActiveSubMenu = function () {
                return $("li.active ul.level2").hide();
            };

            SideBAR.prototype.adjustPaddingOnExpand = function () {
                $("ul.level1 li a.expandable").css({
                    padding: "1px 4px 4px 0px"
                });
                return $("ul.level1 li.active a.expandable").css({
                    padding: "1px 4px 4px 4px"
                });
            };

            SideBAR.prototype.resetOriginalPaddingOnCollapse = function () {
                $("ul.nbs-level1 li a.expandable").css({
                    padding: "4px 4px 4px 0px"
                });
                return $("ul.level1 li.active a.expandable").css({
                    padding: "4px"
                });
            };

            SideBAR.prototype.ignite = function () {
                return (function (instance) {
                    return $("#justify-icon").click(function (e) {
                        if ($(this).parent("nav.sidebar").hasClass("sidebar-menu-collapsed")) {
                            instance.adjustPaddingOnExpand();
                            instance.expandMyMenu();
                            instance.showMenuTexts();
                            instance.showActiveSubMenu();
                            $(this).css({
                                color: "#000"
                            });
                        } else if ($(this).parent("nav.sidebar").hasClass("sidebar-menu-expanded")) {
                            instance.resetOriginalPaddingOnCollapse();
                            instance.collapseMyMenu();
                            instance.hideMenuTexts();
                            instance.hideActiveSubMenu();
                            $(this).css({
                                color: "#FFF"
                            });
                        }
                        return false;
                    });
                })(this);
            };

            return SideBAR;

        })();
        return (new SideBAR).ignite();
    });

}).call(this);


             </script>

     </head>
     <body style="" onload="">
         <div class="wrap">
             
           <div class="row">           
            
             <div class="navbar navbar-bright navbar-fixed-top" role="banner" style="">
                 <!-- Begin header -->
                 <div class="navbar-header">                    
                         <tiles:insertAttribute name="header"/>                

                 </div>
             </div>            
           </div>           
             <!-- Begin footer-->
             <div class="row">
                  <div class="col-md-1">    
             <!-- NAVIGATION DE GAUCHE -->
<!--             <nav class='sidebar sidebar-menu-collapsed bgcode' style="z-index: 2000"> 

            <a href='#' id='justify-icon'>
                <span class='glyphicon glyphicon-align-justify'></span>
            </a>
                 
            <ul class='level1'>
                
                 <li class='active'> <a class='expandable' href='#' title='Dashboard'>
                        <span class='glyphicon glyphicon-home collapsed-element'></span>
                        <span class='expanded-element'>Batiment</span>
                    </a>


                    <ul class='level2'>

                        <li> <a href='#' title='Traffic'>Traffic</a>

                        </li>
                        <li> <a href='#' title='Conversion rate'>Conversion rate</a>

                        </li>
                        <li> <a href='#' title='Purchases'>Purchases</a>

                        </li>

                    </ul>

                </li>
                <li> <a class='expandable' href='#' title='Parkings'>
                        <span class='glyphicon glyphicon-registration-mark collapsed-element'></span>
                        <span class='expanded-element'>Parkings</span>
                    </a>

                </li>
                <li> <a class='expandable' href='#' title='Equipements'>
                        <span class='glyphicon glyphicon-bookmark collapsed-element'></span>
                        <span class='expanded-element'>Equipements</span>
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
                 <li> <a class='expandable' href='#' title='Localisation'>
                        <span class='glyphicon glyphicon-globe collapsed-element'></span>
                        <span class='expanded-element'>Localisation</span>
                    </a>

                </li>
                 <li> <a class='expandable' href='#' title='Statistiques'>
                        <span class='glyphicon glyphicon-wrench collapsed-element'></span>
                        <span class='expanded-element'>Statistiques</span>
                    </a>

                </li>
                 <li> <a class='expandable' href='#' title='Prestataires'>
                        <span class='glyphicon glyphicon-fire collapsed-element'></span>
                        <span class='expanded-element'>Prestataires</span>
                    </a>

                </li>
                <li> <a class='expandable' href='#' title='Settings'>
                        <span class='glyphicon glyphicon-cog collapsed-element'></span>
                        <span class='expanded-element'>Administration </span>
                    </a>

                </li>
                
            </ul> 
            <a href='${pageContext.request.contextPath}/j_spring_security_logout' id='logout-icon' title='Logout'>
                <span class='glyphicon glyphicon-off'></span>
            </a>

        </nav>-->
               
                     <tiles:insertAttribute name="nav"/>
              
             </div>
                 <div class="col-md-11">
                     <p style="padding-top: 50px; height: 100%;">Contenu de la page</p>                     
                 </div>                 
             </div>
             
             <div id="row">
                 <div class="col-md-3" id="footerid">
                     <tiles:insertAttribute name="footer"/>
                 </div>
             </div>

         </div>

    </body>
</html>