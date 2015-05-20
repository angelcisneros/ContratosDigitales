<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="popUpError" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <form id="firmar" role="form" method="post" action="firmarPF" enctype="multipart/form-data">
            <div class="modal-content">
                <div class="modal-header amarillo">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                    </button>
                    <h2 class="modal-title"><strong>Error...</strong></h2>
                </div>
                <div class="modal-body">
                    <h1>Ups!....</h1>
                    Encontramos los siguientes errores :'(
                    <ol>
                        <c:forEach items="${errores}" var="e" varStatus="status">
                            <li>${e}</li>
                        </c:forEach>
                    </ol>
                </div>
                <div class="modal-footer amarillo">
                    <button id="aceptoErrores" type="button" class="btn btn-default" data-dismiss="modal">Aceptar</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </form>
    </div>
    <!-- /.modal-dialog -->
</div>