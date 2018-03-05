package GeoLocal

import grails.converters.JSON

class AgencySearchController {
    AgencySearchService agencySearchService
    static allowedMethods = [getAgencies: 'GET']

    def index() {

        def payment_methods_url = message(code: 'api.url.payment_methods', args: [message(code: 'default.site')])
        [
                paymentMethods: agencySearchService.getPaymentMethods(payment_methods_url),
                defaultSite: message(code: 'default.site'),
                MAP_KEY: message(code: 'MAP_KEY')
        ]
    }

    def getAgencies = {

        def direccion = ""
        def paymentMethod = ""
        int radio = 0

        try{
            direccion = params.get("direccion")
        }
        catch(MissingPropertyException e){
            render message(code:'error.empty_dir')+'\n'
        }

        try{
            paymentMethod = params.get("paymentMethod")
        }
        catch(MissingPropertyException e){
            render message(code:'error.empty_attr', args: [message(code: 'default.attr.paymentMethod')])+'\n'
        }

        try{
            radio = Integer.parseInt( params.get("radio") )
        }
        catch(MissingPropertyException e){
            render message(code:'error.empty_attr', args: [message(code: 'default.attr.radio')])+'\n'

        }
        catch(NumberFormatException e){
            render message(code:'error.invalid_attr', args: [message(code: 'default.attr.radio')])+'\n'
        }

        if(direccion.toString().isEmpty() || paymentMethod.toString().isEmpty() || radio<1) {
            render text: message(code: 'error.empty_necesary')
        }
        else {

            String geo_cod_url = message(code: 'api.url.geo_code', args: [JsonUtil.URIEncodeToUTF8(direccion), message(code: 'API_KEY')])

            Address userLocation = agencySearchService.getLocation(geo_cod_url)

            String agencies_url = message(code: 'api.url.agencies', args: [message(code: 'API_KEY'), paymentMethod, userLocation.latitude, userLocation.longitude, JsonUtil.parseNumberToUri(radio)])

            ArrayList<Agency> agencies = agencySearchService.getAgencies(agencies_url)

            def result = [userLocation: userLocation, agencies: agencies]

            grails.converters.JSON.use('deep')

            render(result as JSON)
        }
    }



}

