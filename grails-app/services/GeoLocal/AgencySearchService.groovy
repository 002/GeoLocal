package GeoLocal

import grails.gorm.transactions.Transactional

@Transactional
class AgencySearchService {

    Object getPaymentMethods(String url_str){
        return JsonUtil.getJsonFromUrl(url_str)
    }


    Address getLocation(String url_str){
        def json_list = JsonUtil.getJsonFromUrl(url_str)

        return new Address(latitude: json_list.results[0].geometry.location.lat, longitude: json_list.results[0].geometry.location.lng)
    }

    ArrayList<Agency> getAgencies(String url_str){

        def agencies_json = JsonUtil.getJsonFromUrl(url_str)

        ArrayList<Agency> agencies = new ArrayList<Agency>()

        agencies_json.results.each{a ->
            agencies.add(new Agency(
                    description: a.description,
                    distance: Double.parseDouble(a.distance),
                    paymentMethod: a.payment_method_id,
                    phone: a.phone,
                    address: new Address(
                            addressLine: a.address.address_line,
                            city: a.address.city,
                            country: a.address.country,
                            otherInfo: a.address.other_info,
                            state: a.address.state,
                            zipCode: a.address.zip_code,
                            latitude: a.address.location.split(',')[0],
                            longitude: a.address.location.split(',')[1]
                    )
            ))
        }

        return agencies

    }

}

