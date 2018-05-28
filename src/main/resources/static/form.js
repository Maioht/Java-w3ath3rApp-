$(document).ready(function() {
    $("#submit").click(function() {
       // console.log(arguments);
        var city = $("#city").val();
        console.log(city);

        var URL = "weather/" + city;
        $.getJSON( URL, function( data ) {
           // console.log(data);
            $("#results").toggle(true);
            $("#resultCity").html(data.name);
            $("#resultCountry").html(data.sys.country);
            $("#resultTemp").html(data.temperature);
            $("#resultHumidity").html(data.humidity);
            $("#resultWind").html(data.wind.speed);
        });
    });
});