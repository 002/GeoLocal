package GeoLocal

class Address {
    String addressLine
    String city
    String country
    String otherInfo
    String state
    String zipCode
    String latitude
    String longitude

    static belongsTo = [agency:Agency]

    static constraints = {
        latitude nullable: false, blank: false
        longitude nullable: false, blank: false
        otherInfo nullable: true, blank: true
        zipCode nullable: true, blank: true
        addressLine nullable: true, blank: true
        city nullable: true, blank: true
        country nullable: true, blank: true
        state nullable: true, blank: true
    }

}

