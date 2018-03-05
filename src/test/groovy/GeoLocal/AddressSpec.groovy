package GeoLocal

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class AddressSpec extends Specification implements DomainUnitTest<Address> {


    void "create invalid Address"() {
        when:
        Address address = new Address(latitude: "5").save()
        then:
        address== null
    }

    void "create valid Address"() {
        when:
            Address address = new Address(latitude: "-1",longitude: "5")
        then:
            address.longitude == "5"
            address.latitude == "-1"

    }
}
