package GeoLocal

import groovy.json.JsonSlurper

class JsonUtil {

    static String URIEncodeToUTF8(String url) throws UnsupportedEncodingException{

        return URLEncoder.encode(url, "UTF-8")

    }

    static String parseNumberToUri(int number){
        return number.toString().replace(".", "")
    }

    static Object getJsonFromUrl(String url_str){

        def url = new URL(url_str)

        def connection = (HttpURLConnection)url.openConnection()
        connection.setRequestMethod("GET")
        connection.setRequestProperty("Accept", "application/json")
        connection.setRequestProperty("User-Agent", "Mozzilla/5.0")

        JsonSlurper json = new JsonSlurper()
        def json_list = json.parse(connection.getInputStream())

        return json_list

    }
}
