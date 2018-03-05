<!doctype html>
<html>
<head>

    <title>Agency Search</title>
    <asset:stylesheet src="application.css"/>
    <asset:stylesheet src="estilo.css"/>
    <link href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/plug-ins/f3e99a6c02/integration/bootstrap/3/dataTables.bootstrap.css">

</head>
<body>
<div id="head">
    <div class="row">
        <div class="col-md-12">
            #MELI
        </div>
    </div>
</div>
<div id="search_title">Buscar Agencias Cercanas</div>
<div class="container">

    <div class="row">
        <g:form name="meliForm">
            <div class="search_form">
                <div class="col-md-4">
                    <div class="form-group">
                        <label class="control-label"><i class="fas fa-map-marker"></i> Dirección en la que te encuentras</label>
                        <input class="form-control" name="direccion" type="text" placeholder="Dirección, Ciudad" required/>

                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="control-label"><i class="far fa-credit-card"></i> Seleccionar un Medio de Pago</label>
                        <g:select class="form-control" name="paymentMethod" optionKey="id" optionValue="name" from="${paymentMethods}"
                                  noSelection="['':'Seleccionar un Medio de Pago']" required="true">

                        </g:select>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="control-label"><i class="far fa-dot-circle"></i> En un Radio (en metros) de</label>
                        <input class="form-control" name="radio" type="number" value="500" placeholder="Ej: 200 metros" required/>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <center>
                            <button onclick="agse.search();" type="button" style="margin-top:24px" class="btn btn-primary">Buscar Agencias</button>
                        </center>
                    </div>
                </div>
            </div>
        </g:form>
        <div class="row-result">
            <div class="loading_search">
                <div class="text"><p><i class="fa fa-spin fa-spinner"></i> Cargando...</p></div>
                <div class="blackScreen"></div>
            </div>

            <div class="col-md-7">
                <table class="table table-striped table-bordered" cellspacing="0" width="100%" id="agencies"></table>
            </div>
            <div class="col-md-5">
                <div id="map"></div>
            </div>

        </div>
    </div><!-- end row -->
</div><!-- end container -->

<div id="agencyModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Modal title</h4>
            </div>
            <div class="modal-body">
                <p id="direction">Dirección: <label class="control-label"></label></p>
                <p id="city">Ciudad: <label class="control-label"></label></p>
                <p id="state">Provincia: <label class="control-label"></label></p>
                <p id="country">País: <label class="control-label"></label></p>
                <p id="zip_code">Código Postal: <label class="control-label"></label></p>
                <p id="other_info">Otra Informacion: <label class="control-label"></label></p>
                <p id="location">Localización Geografica: <label class="info-txt small"></label></p>
                <div id="small_map"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<asset:javascript src="application.js"/>

<script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>
<script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.4.0/bootbox.min.js"></script>

<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=${MAP_KEY}">
</script>

</body>
</html>
