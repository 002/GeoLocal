package GeoLocal

class Agency {
    String description
    String paymentMethod
    double distance
    String phone
    static hasOne = [address:Address]

    static constraints = {
        description nullable: false, blank: false
        paymentMethod nullable: false, blank: false
        distance default: 0
        phone nullable: true, blank: true
        address nullable: false
    }
}