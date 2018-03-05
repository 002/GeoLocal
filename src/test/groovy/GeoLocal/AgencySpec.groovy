package GeoLocal

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class AgencySpec extends Specification implements DomainUnitTest<Agency> {

    void "test create invalid Agency"() {
        when:
        Agency agency = new Agency(address: "Lima 78")
        then:
        agency.errors.getErrorCount() > 0
    }

    void "test create valid Agency"() {
        when:
        Agency agency = new Agency(description: 'valid', paymentMethod: 'visa',  address:  new Address()).save()
        then:
        agency.errors.getErrorCount() == 0
    }
}
