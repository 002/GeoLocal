var table = null;
var agse = {
    search: function(){
        var form = document.forms.meliForm;
        loading();
        $.ajax({
            url: '/agencySearch/getAgencies',
            dataType: 'json',
            contentType: 'application/json',
            type: 'GET',
            data: 'direccion=' + form.direccion.value +'&paymentMethod='+form.paymentMethod.value+'&radio='+form.radio.value,
            success: function(result) {
                var data = result.agencies;

                if(table!=null)
                    table.destroy();

                table = $('#agencies').DataTable( {
                    data: data,
                    searching:false,
                    columns: [
                        { data: 'description', title: "Descripción" },
                        { data: function(agency){
                            return parseInt(agency.distance)+"m";
                            }, title: "Distancia" },
                        { data: function(agency){
                            return '<button type="button" class="btn btn-primary"><i class="fas fa-search"></i></button>'
                            }, title: ""}
                    ],
                    columnDefs: [
                        { targets: [2], orderable: false, class: 'dt-right'}

                    ],
                    order: [[ 1, "asc" ]],
                    language: {
                        lengthMenu: "_MENU_ Registros",
                        zeroRecords: "No se encontraron Agencias cercanas, incrementa el radio de busqueda.",
                        info: "Mostrando página _PAGE_ de _PAGES_",
                        infoEmpty: "No se encontraron Agencias.",
                        infoFiltered: "(Filtrando de _MAX_ registros totales)",
                        paginate: {
                            "first":      "<<",
                            "last":       ">>",
                            "next":       ">",
                            "previous":   "<"
                        },
                    }
                } );

                $('#agencies tbody').on( 'click', 'button', function () {
                    agse.showInfo(table.row( $(this).parents('tr') ).data());
                } );
                var userLocation = {lat: parseFloat(result.userLocation.latitude), lng: parseFloat(result.userLocation.longitude)};
                var map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 15,
                    center: userLocation
                });
                var infoWindow = new google.maps.InfoWindow({map: map});
                infoWindow.setPosition(userLocation);
                infoWindow.setContent('Mi Dirección');
                data.forEach(agency => {
                    new google.maps.Marker({
                        position: {lat: parseFloat(agency.address.latitude), lng: parseFloat(agency.address.longitude)},
                        map: map,
                        title: agency.description
                    });
                });
                $('#map').show();

                loading();
            },
            error: function(error){
                bootbox.alert(error.responseText);
                console.log(error);
                loading();
            }
        });
    },
    showInfo: function (agency) {

        $('.modal-title').html("Agencia "+agency.description);
        $('#direction label').html(agency.address.addressLine);
        $('#city label').html(agency.address.city);
        $('#country label').html(agency.address.country);
        $('#other_info label').html(agency.address.otherInfo);

        if(agency.address.otherInfo!="") {
            $('#other_info').show();
        }else{
            $('#other_info').hide();
        }

        $('#state label').html(agency.address.state);
        $('#zip_code label').html(agency.address.zipCode);

        if(agency.address.zipCode!="") {
            $('#zip_code').show();
        }else{
            $('#zip_code').hide();
        }
        $('#zip_code label').html(agency.address.zipCode);

        $('#location label').html('('+agency.address.latitude+', '+agency.address.longitude+')');

        $('#agencyModal').modal('show');

        var location = {lat: parseFloat(agency.address.latitude), lng: parseFloat(agency.address.longitude)};
        var map = new google.maps.Map(document.getElementById('small_map'), {
            zoom: 16,
            center: location
        });
        var marker = new google.maps.Marker({
            position: location,
            map: map,
            title: agency.description
        });

    }
};

var loading = function(){
    if($('.loading_search').is(':visible'))
        $('.loading_search').fadeOut('slow');
    else
        $('.loading_search').fadeIn('slow');
};