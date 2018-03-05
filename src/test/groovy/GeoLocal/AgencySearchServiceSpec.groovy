package GeoLocal

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class AgencySearchServiceSpec extends Specification implements ServiceUnitTest<AgencySearchService>{

    def setupSpec(){
        Mock(Address)

    }
    def setup() {
    }

    def cleanup() {
    }

    void "test invalid url on getLocation"() {

        when:
        service.getLocation( "bad joke")
        then:
        thrown(IllegalStateException)

    }

    void "test invalid url on getAgencies"() {

        when:
        service.getAgencies( "bad joke")
        then:
        thrown(IllegalStateException)

    }

}
