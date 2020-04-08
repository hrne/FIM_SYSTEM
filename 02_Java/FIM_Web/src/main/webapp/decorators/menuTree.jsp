<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

                    <ul class="nav" id="side-menu">  
                                      
<c:if test='${applicationScope["settings"]["demo.enable"] == "true"}' >
                    
                        <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                            <!-- /input-group -->
                        </li>
                        <li>
                            <a href="<c:url value='/index'/>"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Charts<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="<c:url value='/flot'/>">Flot Charts</a>
                                </li>
                                <li>
                                    <a href="<c:url value='/morris'/>">Morris.js Charts</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="<c:url value='/tables'/>"><i class="fa fa-table fa-fw"></i> Tables</a>
                        </li>
                        <li>
                            <a href="<c:url value='/forms'/>"><i class="fa fa-edit fa-fw"></i> Forms</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-wrench fa-fw"></i> UI Elements<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="<c:url value='/panels-wells'/>">Panels and Wells</a>
                                </li>
                                <li>
                                    <a href="<c:url value='/buttons'/>">Buttons</a>
                                </li>
                                <li>
                                    <a href="<c:url value='/notifications'/>">Notifications</a>
                                </li>
                                <li>
                                    <a href="<c:url value='/typography'/>">Typography</a>
                                </li>
                                <li>
                                    <a href="<c:url value='/icons'/>"> Icons</a>
                                </li>
                                <li>
                                    <a href="<c:url value='/grid'/>">Grid</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-sitemap fa-fw"></i> Multi-Level Dropdown<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="#">Second Level Item</a>
                                </li>
                                <li>
                                    <a href="#">Second Level Item</a>
                                </li>
                                <li>
                                    <a href="#">Third Level <span class="fa arrow"></span></a>
                                    <ul class="nav nav-third-level">
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                    </ul>
                                    <!-- /.nav-third-level -->
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-files-o fa-fw"></i> Sample Pages<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="<c:url value='/blank'/>">Blank Page</a>
                                </li>
                                <li>
                                    <a href="<c:url value='/login'/>">Login Page</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>

</c:if>                           

                        <li>
                            <a href="#"><i class="fa fa-files-o fa-fw"></i><spring:message code='userModule'/><span class="fa arrow"></span></a>                                                  
                            <ul class="nav nav-second-level collapse">
                                <li>
                                    <a href="<c:url value='/user/add'/>"><spring:message code='addUser'/></a>
                                </li>
                                <li>
                                    <a href="<c:url value='/users'/>"><spring:message code='listUser'/></a>
                                </li>
                            </ul>
                        </li>
              
                    </ul>


