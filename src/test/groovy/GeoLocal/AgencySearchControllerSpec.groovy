package GeoLocal

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class AgencySearchControllerSpec extends Specification implements ControllerUnitTest<AgencySearchController> {

    def setupSpec(){
        Mock(AgencySearchService)
    }
    def setup() {

    }

    def cleanup() {
    }

    void "test index"() {
        given:
        controller.agencySearchService = Mock(AgencySearchService)

        when:"The index action is executed"
        controller.index()

        then:"The model is correct"
        view == '/agencySearch/index.gsp'

    }

    void "test index model"() {
        given:
        controller.agencySearchService = Mock(AgencySearchService){
            getPaymentMethods(_ as String) >> [visa: 'visa']
        }

        when:"The index action is executed"
        def model =  controller.index()

        then:"The model is correct"
        model.paymentMethods.size()>0

    }

    void "test getAgencies model whitout parameters"() {
        given:
        controller.agencySearchService = Mock(AgencySearchService)
        when:"The getAgencies action is executed"
        request.method = "GET"
        params.direccion = "Lima 78"
        controller.getAgencies()
        then:"The response is correct"
        response.text == 'error.invalid_attr\n' +
                'error.empty_necesary'

    }

    void "test getAgencies model whit parameters"() {
        given:
            Address address = Mock(Address){new Address(latitude: "5", longitude: "1")}
            controller.agencySearchService = Mock(AgencySearchService){
                getAgencies(_ as String) >> {
                    ArrayList<Agency> a = new ArrayList<Agency>()
                    Agency agency = Mock(Agency){new Agency(description: "test", paymentMethod: "rapipago", distance: 348)}
                    agency.address = address
                    a.add(agency)
                    a
                }
                getLocation(_ as String) >> address
            }
        when:"The getAgencies action is executed"
            request.method = "GET"
            params.direccion = "Lima 78, Córdoba"
            params.radio = "500"
            params.paymentMethod = "rapipago"
            controller.getAgencies()

        then:"The model is correct"
            response.json.agencies.size() > 0



    }
}
