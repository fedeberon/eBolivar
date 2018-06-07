<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="attrib" required="true"  %>

    <input <jsp:doBody/>  name="${pageScope.attrib}" onclick="fMostrar(document.formBuscar.${pageScope.attrib});" type="checkbox">
    ${pageScope.attrib}</input>
