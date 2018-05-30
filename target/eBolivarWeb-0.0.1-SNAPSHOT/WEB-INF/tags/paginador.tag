<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="page" required="true"  %>
<%@attribute name="noMorePages" required="true"  %>

    <c:if test="${pageScope.page>1}">
        <a class='button' onclick='pagAnterior();'>
        <img  src='/eBolivar/img/icons/control_rewind_blue.png' />Anterior
        </a>
      </c:if>
      <c:if test='${pageScope.noMorePages!=1}'>
      <a class='button' onclick='pagSiguiente();'>
        Siguiente&nbsp;<img  src='/eBolivar/img/icons/control_fastforward_blue.png' />
      </a>
      </c:if>
      