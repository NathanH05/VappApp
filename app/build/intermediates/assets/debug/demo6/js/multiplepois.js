// implementation of AR-Experience (aka "World")
var World = {
	// true once data was fetched
	initiallyLoadedData: false,

	// different POI-Marker assets
	markerDrawable_idle: null,
	markerDrawable_selected: null,
  // The last selected marker
   currentMarker: null,

	// list of AR.GeoObjects that are currently shown in the scene / World
	markerList: [],
	offers: [],
	surroundingHashes: [],
    BITS: [16, 8, 4, 2, 1],
    BASE32: "0123456789bcdefghjkmnpqrstuvwxyz",
	// The last selected marker
	currentMarker: null,

	// called to inject new POI data
	loadPoisFromJsonData: function loadPoisFromJsonDataFn(centerPointLongitude,centerPointLatitude,category,duration,retailerName,offerDescription, offerEndTime, websity, offerDescription2, offerDescription3, rating, streetName, offerEndTime2, offerEndTime3) {
		// empty list of visible markers
//		World.markerList = [];
console.log(centerPointLatitude);
console.log(centerPointLongitude);
console.log(category);
console.log("STREET NAME PRINTING");
console.log(streetName);
		// start loading marker assets

//		World.markerDrawable_idle = new AR.ImageResource("assets/drinks.png");
//		World.markerDrawable_selected = new AR.ImageResource("assets/drinks.png");

console.log("CATEGORY SLICED");
console.log(category.slice(1,-1));

		if (category.slice(1,-1) == "drinks"){
				World.markerDrawable_idle = new AR.ImageResource("assets/drinks.png");
				World.markerDrawable_selected = new AR.ImageResource("assets/drinks.png");
				console.log("drinks true");
		}
		if (category.slice(1,-1) == "food/dining"){
				World.markerDrawable_idle = new AR.ImageResource("assets/fooddining.png");
				World.markerDrawable_selected = new AR.ImageResource("assets/fooddining.png");
		}
		if (category.slice(1,-1) == "accommodation"){
				World.markerDrawable_idle = new AR.ImageResource("assets/accomodation.png");
				World.markerDrawable_selected = new AR.ImageResource("assets/accomodation.png");
		}
		if (category.slice(1,-1) == "attractions"){
				World.markerDrawable_idle = new AR.ImageResource("assets/activities.png");
			    World.markerDrawable_selected = new AR.ImageResource("assets/activities.png");
		}
		if (category.slice(1,-1) == "shopping"){
				World.markerDrawable_idle = new AR.ImageResource("assets/shopping.png");
				World.markerDrawable_selected = new AR.ImageResource("assets/shopping.png");
		}
		if (category.slice(1,-1) == "movies"){
				World.markerDrawable_idle = new AR.ImageResource("assets/movies.png");
				World.markerDrawable_selected = new AR.ImageResource("assets/movies.png");
		}
    var allOffers = JSON.parse(World.offers[0]);
    console.log(allOffers.length);


				var poisToCreate = 1;
        		var poiData = [];


        		for (var i = 0; i < poisToCreate; i++) {
        			poiData.push({
        				"id": (""),
        				"longitude": (centerPointLongitude - centerPointLongitude + centerPointLongitude),
        				"latitude": (centerPointLatitude - centerPointLatitude + centerPointLatitude),
        				"description": "",
        				"altitude": "100.0",
        				"name": ""
        			});
        		}
        			console.log(poiData[0].id);
        console.log(streetName);

		console.log(poiData.length);
		console.log(poiData[0].description);
		console.log(offerDescription.slice(1,-1).toString());
		console.log(retailerName.slice(1,-1).toString());
		console.log(category.slice(1,-1).toString());
		console.log(duration.slice(1,-1).toString());


		// loop through POI-information and create an AR.GeoObject (=Marker) per POI
		for (var currentPlaceNr = 0; currentPlaceNr < poiData.length; currentPlaceNr++) {
			var singlePoi = {
				"id": currentPlaceNr,
				"latitude": parseFloat(poiData[currentPlaceNr].latitude),
				"longitude": parseFloat(poiData[currentPlaceNr].longitude),
				"altitude": parseFloat(poiData[currentPlaceNr].altitude),
				"title": "",
				"description": ""
			};

			/*
				To be able to deselect a marker while the user taps on the empty screen,
				the World object holds an array that contains each marker.
			*/
//					        World.markerList[0].enabled = false;

			World.markerList.push(new Marker(singlePoi, retailerName.slice(1,-1).toString(),offerDescription.slice(1,-1).toString(),category.slice(1,-1).toString(),offerEndTime.slice(1,-1).toString(),duration.toString(),websity.slice(2,-2).toString(), offerDescription2.slice(1,-1).toString(),offerDescription3.slice(1,-1).toString(),rating.slice(1,-1),streetName.slice(1,-1),offerEndTime2.slice(1,-1),offerEndTime3.slice(1,-1)));
		        console.log("dfd");
		        console.log(streetName);
		}

//        	World.loadPoisFromJsonData(poiData);
		    console.log(World.markerList[0].streetName);
		    console.log(World.markerList[0].markerObject.enabled);
		    console.log(streetName);

		World.updateStatusMessage(currentPlaceNr + ' places loaded');
	},

	// updates status message shon in small "i"-button aligned bottom center
	updateStatusMessage: function updateStatusMessageFn(message, isWarning) {

		var themeToUse = isWarning ? "e" : "c";
		var iconToUse = isWarning ? "alert" : "info";

		$("#status-message").html(message);
		$("#popupInfoButton").buttonMarkup({
			theme: themeToUse
		});
		$("#popupInfoButton").buttonMarkup({
			icon: iconToUse
		});
	},


    // Converts from radians to degrees.
    MathDegrees: function MathDegrees(radians) {
    console.log(radians);
      return radians * 180 / Math.PI;
    },

    // Converts from degrees to radians.
    MathRadians: function MathRadians(degrees) {
    console.log(degrees);
      return degrees * Math.PI / 180;
    },

    iWantGeoHashes: function iWantGeoHashes(latitude, longitude) {
console.log(latitude);

//    # Assign the users latitude and longitude, obtained from the application call, to variables
    var userCurrentLat = parseFloat(latitude);
    var userCurrentLon = parseFloat(longitude);


    var userCurrentLatDegs =  parseFloat(latitude);
    var userCurrentLonDegs = parseFloat(longitude);
console.log(userCurrentLat);
console.log(userCurrentLon);
//    # Convert the user's current latitude and longitude to radians for use in the calculations below
     userCurrentLat = World.MathRadians(userCurrentLat);
     userCurrentLon = World.MathRadians(userCurrentLon);


//    # Set the earths radius
    var R = 6378.1;
//    # Set the distance we wish to receive coordinates for (in km)
    var d = 0.15;
//    # Set the bearing for each direction we wish to obtain. e.g N, NE, E, SE, S, SW, W, NW
    var brngSouth = World.MathRadians(180);
    var brngNorth = 0;
    var brngEast = World.MathRadians(90);
    var brngWest = World.MathRadians(270);
    var brngNorthEast = World.MathRadians(45);
    var brngSouthEast = World.MathRadians(135);
    var brngSouthWest = World.MathRadians(225);
    var brngNorthWest = World.MathRadians(315);

console.log(userCurrentLon);
console.log(Math.sin(userCurrentLat));
console.log(Math.cos(d / R));
console.log(Math.cos(userCurrentLat));
console.log(Math.sin(userCurrentLat));
console.log(Math.atan2((Math.sin(brngEast)* Math.sin(d / R) * Math.cos(userCurrentLat)), (Math.cos(d / R) - Math.sin(userCurrentLat) * Math.sin(userCurrentLat))));




//    # Return the coordinates of 50 metres north of my current location
    var nthLat = Math.asin(Math.sin(userCurrentLat) * Math.cos(d / R) + Math.cos(userCurrentLat) * Math.sin(d / R) * Math.cos(brngNorth));

    var nthLon = userCurrentLon + Math.atan2(Math.sin(brngNorth) * Math.sin(d / R) * Math.cos(userCurrentLat), Math.cos(d / R) - Math.sin(userCurrentLat) * Math.sin(userCurrentLat));
console.log(nthLon);

     nthLon = (nthLon + 3 * Math.PI) % (2 * Math.PI) - Math.PI;

//    # Convert the longitude and latitude of the new coordinates to degrees
     nthLon = World.MathDegrees(nthLon);
    nthLat = World.MathDegrees(nthLat);

console.log(nthLon);
//    # Return the coordinates or 50 metres south of my current location
    var sthLat = Math.asin(Math.sin(userCurrentLat) * Math.cos(d / R) + Math.cos(userCurrentLat) * Math.sin(d / R) * Math.cos(brngSouth));

    var sthLon = userCurrentLon + Math.atan2(Math.sin(brngSouth) * Math.sin(d / R) * Math.cos(userCurrentLat),
                         Math.cos(d / R) - Math.sin(userCurrentLat) * Math.sin(userCurrentLat));

     sthLon = (sthLon + 3 * Math.PI) % (2 * Math.PI) - Math.PI;

//    # Convert the longitude and latitude of the new coordinates to degrees
    var sthLon = World.MathDegrees(sthLon);
    var sthLat = World.MathDegrees(sthLat);

//    # Return the coordinates or 50 metres east of my current location
    var eastLat = Math.asin(Math.sin(userCurrentLat) * Math.cos(d / R) + Math.cos(userCurrentLat) * Math.sin(d / R) * Math.cos(brngEast));

    var eastLng = userCurrentLon + Math.atan2(Math.sin(brngEast) * Math.sin(d / R) * Math.cos(userCurrentLat),
                         Math.cos(d / R) - Math.sin(userCurrentLat) * Math.sin(userCurrentLat));

     eastLng = (eastLng + 3 * Math.PI) % (2 * Math.PI) - Math.PI;

//    # Convert the longitude and latitude of the new coordinates to degrees
     eastLng = World.MathDegrees(eastLng);
    eastLat = World.MathDegrees(eastLat);


//    # Return the coorinates or 50 metres west of my current location
    var westLat = Math.asin(Math.sin(userCurrentLat) * Math.cos(d / R) + Math.cos(userCurrentLat) * Math.sin(d / R) * Math.cos(brngWest));

    var westLng = userCurrentLon + Math.atan2(Math.sin(brngWest) * Math.sin(d / R) * Math.cos(userCurrentLat),
                         Math.cos(d / R) - Math.sin(userCurrentLat) * Math.sin(userCurrentLat));

    var westLng = (westLng + 3 * Math.PI) % (2 * Math.PI) - Math.PI;

//    # Convert the longitude and latitude of the new coordinates to degrees
    westLat = World.MathDegrees(westLat);
    westLng = World.MathDegrees(westLng);


//    #Return the coorinates or 50 metres north east of my current location
    var nthEastLat = Math.asin(Math.sin(userCurrentLat) * Math.cos(d / R) + Math.cos(userCurrentLat) * Math.sin(d / R) * Math.cos(brngNorthEast));

    var nthEastLng = userCurrentLon + Math.atan2(Math.sin(brngNorthEast) * Math.sin(d / R) * Math.cos(userCurrentLat),
                         Math.cos(d / R) - Math.sin(userCurrentLat) * Math.sin(userCurrentLat));
    nthEastLng = (nthEastLng + 3 * Math.PI) % (2 * Math.PI) - Math.PI;

    nthEastLng = World.MathDegrees(nthEastLng);
    nthEastLat = World.MathDegrees(nthEastLat);
//    # Return the coorinates or 50 metres south east of my current location
    var sthEastLat = Math.asin(Math.sin(userCurrentLat) * Math.cos(d / R) + Math.cos(userCurrentLat) * Math.sin(d / R) * Math.cos(brngSouthEast));

    var sthEastLon = userCurrentLon + Math.atan2(Math.sin(brngSouthEast) * Math.sin(d / R) * Math.cos(userCurrentLat),
                         Math.cos(d / R) - Math.sin(userCurrentLat) * Math.sin(userCurrentLat));
    sthEastLon = (sthEastLon + 3 * Math.PI) % (2 * Math.PI) - Math.PI;

//    # Convert the longitude and latitude of the new coordinates to degrees
    sthEastLon = World.MathDegrees(sthEastLon);
    sthEastLat = World.MathDegrees(sthEastLat);

//    # Return the coorinates or 50 metres south west of my current location
    var sthWestLat = Math.asin(Math.sin(userCurrentLat) * Math.cos(d / R) + Math.cos(userCurrentLat) * Math.sin(d / R) * Math.cos(brngSouthWest));

    var sthWestLng = userCurrentLon + Math.atan2(Math.sin(brngSouthWest) * Math.sin(d / R) * Math.cos(userCurrentLat),
                         Math.cos(d / R) - Math.sin(userCurrentLat) * Math.sin(userCurrentLat));
    sthWestLng = (sthWestLng + 3 * Math.PI) % (2 * Math.PI) - Math.PI;

//    # Convert the longitude and latitude of the new coordinates to degrees
    sthWestLng = World.MathDegrees(sthWestLng);
    sthWestLat = World.MathDegrees(sthWestLat);

//    # Return the coorinates or 50 metres north west of my current location
    var nthWestLat = Math.asin(Math.sin(userCurrentLat) * Math.cos(d / R) + Math.cos(userCurrentLat) * Math.sin(d / R) * Math.cos(brngNorthWest));

    var nthWestLng = userCurrentLon + Math.atan2(Math.sin(brngNorthWest) * Math.sin(d / R) * Math.cos(userCurrentLat),
                         Math.cos(d / R) - Math.sin(userCurrentLat) * Math.sin(userCurrentLat));

    nthWestLng = (nthWestLng + 3 * Math.PI) % (2 * Math.PI) - Math.PI;

//    # Convert the longitude and latitude of the new coordinates to degrees
    nthWestLng = World.MathDegrees(nthWestLng);
    nthWestLat = World.MathDegrees(nthWestLat);

    userCurrentLat = World.MathDegrees(userCurrentLat);
    userCurrentLon = World.MathDegrees(userCurrentLon);


    console.log("|" + "40m Northeast (lat, lng): " + "|" + nthEastLat +
    nthEastLng + "|" +
    "40m East (lat, lng): " + "|" + eastLat + eastLng + "|" + "40m Southeast (lat, lng): " + "|" + sthEastLat +
    sthEastLon   + "|" +
    "40m South (lat, lng): " + "|" + sthLat + sthLon + "|" + "40m Southwest (lat, lng): " + "|" + sthWestLat +
    sthWestLng  + "|" +
    "40m West (lat, lng): " + "|" + westLat + westLng + "|" + "40m Northwest (lat, lng): " + "|" + nthWestLat +
    nthWestLng + "|" +
    "----------------"+ "GeoHashes" + 'The 40m north coord geo hash is : ' + "|" + World.encodeGeoHash(nthLat, nthLon) + "|" +
    'The 40m northeast coord geo hash is : ' + "|" + World.encodeGeoHash(nthEastLat, nthEastLng) +  "|" +
    'The 40m east coord geo hash is : ' + "|" + World.encodeGeoHash(eastLat, eastLng) + "|" +
    'The 40m southeast coord geo hash is : ' + "|" + World.encodeGeoHash(sthEastLat, sthEastLon) +  "|" +
    'The 40m south coord geo hash is : ' + "|" + World.encodeGeoHash(sthLat, sthLon) + "|" +
    'The 40m southwest coord geo hash is : ' + "|" + World.encodeGeoHash(sthWestLat, sthWestLng) +  "|" +
    'The 40m west coord geo hash is : ' + "|" + World.encodeGeoHash(westLat, westLng) +  "|" +
    'The 40m northwest coord geo hash is : ' + "|" + World.encodeGeoHash(nthWestLat, nthWestLng) +  "|" +
    'The users current location geo hash is : ' + "|" + World.encodeGeoHash(userCurrentLatDegs, userCurrentLonDegs) + "|")

    console.log("{" + "40m Northeast (lat, lng): " + nthEastLat +
    nthEastLng + "," +
    "40m East (lat, lng): " + "|" + eastLat + eastLng + "|" + "40m Southeast (lat, lng): " + "|" + sthEastLat +
    sthEastLon   + "|" +
    "40m South (lat, lng): " + "|" + sthLat + sthLon + "|" + "40m Southwest (lat, lng): " + "|" + sthWestLat +
    sthWestLng  + "|" +
    "40m West (lat, lng): " + "|" + westLat + westLng + "|" + "40m Northwest (lat, lng): " + "|" + nthWestLat +
    nthWestLng + "|" +
    "{"+ '"North" : ' + '"' + World.encodeGeoHash(nthLat, nthLon) + '"' +  "," +
    '"NorthEast" : '  + '"' + World.encodeGeoHash(nthEastLat, nthEastLng) + '"' +  "," +
    '"East" : ' + '"' + World.encodeGeoHash(eastLat, eastLng) + '"' + "," +
    '"SouthEast":' + '"' + World.encodeGeoHash(sthEastLat, sthEastLon) + '"' +   "," +
    '"South" : ' + '"' + World.encodeGeoHash(sthLat, sthLon) + '"' +  "," +
    '"SouthWest" : ' + '"' + World.encodeGeoHash(sthWestLat, sthWestLng) + '"' +  "," +
    '"West" : ' + '"' + World.encodeGeoHash(westLat, westLng)+ '"' +  "," +
    '"NorthWest" : ' + '"' + World.encodeGeoHash(nthWestLat, nthWestLng) + '"' +  "," +
    '"UsersHash" : ' + '"' + World.encodeGeoHash(userCurrentLatDegs, userCurrentLonDegs) + '"' + "}")

    },

    getHashes: function getHashes(geohash){

    /**
     * Geohash encode, decode, bounds, neighbours.
     *
     * @namespace
     */
    var Geohash = {};

    /* (Geohash-specific) Base32 map */
    Geohash.base32 = '0123456789bcdefghjkmnpqrstuvwxyz';

    /**
     * Returns all 8 adjacent cells to specified geohash.
     *
     * @param   {string} geohash - Geohash neighbours are required of.
     * @returns {{n,ne,e,se,s,sw,w,nw: string}}
     * @throws  Invalid geohash.
     */


/**
 * Determines adjacent cell in given direction.
 *
 * @param   geohash - Cell to which adjacent cell is required.
 * @param   direction - Direction from geohash (N/S/E/W).
 * @returns {string} Geocode of adjacent cell.
 * @throws  Invalid geohash.
 */
Geohash.adjacent = function(geohash, direction) {
    // based on github.com/davetroy/geohash-js

    geohash = geohash.toLowerCase();
    direction = direction.toLowerCase();

    if (geohash.length === 0) throw new Error('Invalid geohash');
    if ('nsew'.indexOf(direction) == -1) throw new Error('Invalid direction');

    var neighbour = {
        n: [ 'p0r21436x8zb9dcf5h7kjnmqesgutwvy', 'bc01fg45238967deuvhjyznpkmstqrwx' ],
        s: [ '14365h7k9dcfesgujnmqp0r2twvyx8zb', '238967debc01fg45kmstqrwxuvhjyznp' ],
        e: [ 'bc01fg45238967deuvhjyznpkmstqrwx', 'p0r21436x8zb9dcf5h7kjnmqesgutwvy' ],
        w: [ '238967debc01fg45kmstqrwxuvhjyznp', '14365h7k9dcfesgujnmqp0r2twvyx8zb' ],
    };
    var border = {
        n: [ 'prxz',     'bcfguvyz' ],
        s: [ '028b',     '0145hjnp' ],
        e: [ 'bcfguvyz', 'prxz'     ],
        w: [ '0145hjnp', '028b'     ],
    };

    var lastCh = geohash.slice(-1);    // last character of hash
    var parent = geohash.slice(0, -1); // hash without last character

    var type = geohash.length % 2;

    // check for edge-cases which don't share common prefix
    if (border[direction][type].indexOf(lastCh) != -1 && parent !== '') {
        parent = Geohash.adjacent(parent, direction);
    }

    // append letter for direction to parent
    return parent + Geohash.base32.charAt(neighbour[direction][type].indexOf(lastCh));
};


        return {
            'n':  Geohash.adjacent(geohash, 'n'),
            'ne': Geohash.adjacent(Geohash.adjacent(geohash, 'n'), 'e'),
            'e':  Geohash.adjacent(geohash, 'e'),
            'se': Geohash.adjacent(Geohash.adjacent(geohash, 's'), 'e'),
            's':  Geohash.adjacent(geohash, 's'),
            'sw': Geohash.adjacent(Geohash.adjacent(geohash, 's'), 'w'),
            'w':  Geohash.adjacent(geohash, 'w'),
            'nw': Geohash.adjacent(Geohash.adjacent(geohash, 'n'), 'w'),
        };


    },

encodeGeoHash: function encodeGeoHash(latitude, longitude) {
	var is_even=1;
	var i=0;
	var lat = []; var lon = [];
	var bit=0;
	var ch=0;
	var precision = 12;
	geohash = "";

	lat[0] = -90.0;  lat[1] = 90.0;
	lon[0] = -180.0; lon[1] = 180.0;

	while (geohash.length < precision) {
	  if (is_even) {
			mid = (lon[0] + lon[1]) / 2;
	    if (longitude > mid) {
				ch |= World.BITS[bit];
				lon[0] = mid;
	    } else
				lon[1] = mid;
	  } else {
			mid = (lat[0] + lat[1]) / 2;
	    if (latitude > mid) {
				ch |= World.BITS[bit];
				lat[0] = mid;
	    } else
				lat[1] = mid;
	  }

		is_even = !is_even;
	  if (bit < 4)
			bit++;
	  else {
			geohash += World.BASE32[ch];
			bit = 0;
			ch = 0;
	  }
	}
	return geohash;
},

    getRemainingTime: function getRemainingTime(offerEndTime){

            // Obtain the current time
            var time = new Date();
            var currentTimeSeconds = time.getSeconds();
            var currentTimeMinutes = time.getMinutes();
            var currentTimeHours = time.getHours();
            var currentTimeDate = time.getDate();
            var currentTimeMonths = time.getMonth();
            var currentTimeYear = time.getFullYear();

            // The current offer being processed, passes its end time which we split into
            // secs, mins, hours, date, month, year.
            var offerEndTime2 = offerEndTime.split(' ');
            var offerSecs = offerEndTime2[5];
            var offerMins = offerEndTime2[4];
            var offerHours = offerEndTime2[3];
            var offerDays = offerEndTime2[2];
            var offerMonth = offerEndTime2[1];
            var offerYear = offerEndTime2[0].slice(1);

            // Create a new date format for the current time and the offer time
            var t1 = new Date(currentTimeYear, currentTimeMonths+1, currentTimeDate, currentTimeHours, currentTimeMinutes, currentTimeSeconds, 0);
            var t2 = new Date(offerYear, offerMonth, offerDays, offerHours, offerMins, offerSecs, 0);
            var dif = t1.getTime() - t2.getTime();
            tOner = t1.getTime();
            tTwoer = t2.getTime();

            // Calculate the minutes and seconds difference between the current time and the offers end time
            var Seconds_from_T1_to_T2 = dif / 1000;
            var Seconds_Between_Dates = Math.abs(Seconds_from_T1_to_T2);

            // Set World global variables of the minutes and seconds remaining in the offer
            World.minutes = Math.floor(Seconds_Between_Dates / 60);
            World.seconds = Seconds_Between_Dates - World.minutes * 60;


            // If the current time in seconds is greater than that of the offers
            // end time then set the minutes and seconds to 0
             if (tOner > tTwoer){
             World.minutes = 0;
             World.seconds = 0;
             console.log("Current time is greater than the offer end time");
                        }
             // Finally return the offers remaining minutes and seconds
             return parseFloat(World.minutes),parseFloat(World.seconds);
    },

	newFuncy: function newFuncy(centerPointLatitude,centerPointLongitude, _callback){

    $.when(ajax2()).done(function (_callback){console.log(World.offers[0]);});
    console.log('ajax2 when ran');

    // The ajax2 function gets every offer loaded in the database
    function ajax2(){
    console.log('ajax2 started');


    return $.get('https://9ex1ark3n8.execute-api.us-west-2.amazonaws.com/test/geooffers', function(data){
    // We use a complex data object (an array) to hold the offers received from the API.
    // These offers are converted to string format.
        console.log('.get has received data, now we do stuff with the offers');

    World.offers[0] = JSON.stringify(data);

    var offerParse = JSON.stringify(data);
    // Parse the string as JSON
    var allOffers = JSON.parse(World.offers[0]);

    console.log(allOffers.length);
    // Loop through the length of the offers in the JSON document returned from the API
        for (i=0; i < allOffers.length; i++) {
        // For every offer, retrieve its attributes in a variable
        offerDescription = JSON.stringify(allOffers[i].offerDescription);
        offerEndTime = JSON.stringify(allOffers[i].offerEndTime);
        if (JSON.stringify(allOffers[i].offerEndTime2) != null){
                offerEndTime2 = JSON.stringify(allOffers[i].offerEndTime2);
        }
        else{
        offerEndTime2 = JSON.stringify(allOffers[i].offerEndTime);
        }
        if (JSON.stringify(allOffers[i].offerEndTime3) != null){
                offerEndTime3 = JSON.stringify(allOffers[i].offerEndTime3);
        }
        else{
        offerEndTime3 = JSON.stringify(allOffers[i].offerEndTime3);
        }

        offerEndTime2 = JSON.stringify(allOffers[i].offerEndTime2);
        offerEndTime3 = JSON.stringify(allOffers[i].offerEndTime3);
        retailerName = JSON.stringify(allOffers[i].retailerName);
        duration = JSON.stringify(allOffers[i].duration);
        streetName = JSON.stringify(allOffers[i].streetname);
        category = JSON.stringify(allOffers[i].category);
        latitude = JSON.stringify(allOffers[i].latitude);
        longitude = JSON.stringify(allOffers[i].longitude);
        retailerID = JSON.stringify(allOffers[i].retailerID);
        website = JSON.stringify(allOffers[i].website);
        offerDescription2 = JSON.stringify(allOffers[i].offerDescription2);
        offerDescription3 = JSON.stringify(allOffers[i].offerDescription3);
        rating = JSON.stringify(allOffers[i].rating);




console.log("offer Description2: ");
console.log("offer Description3: ");

      var websity = null;

           if(website == null){
            websity = "||"+ "google.com" + "||";
            }

           else{
           websity = "|" + website + "|";
           }

           if(offerDescription2 == null){
           offerDescription2 = "|nooffer2|"
           }
           if(offerDescription3 == null){
           offerDescription3 = "|nooffer3|"

           }
console.log(offerDescription2);
console.log(offerDescription3);

                console.log(website);
                console.log(websity);

        // We slice the first 4 chars and last char to remove the apostrophe, but more
        // importantly the first 3 chars of the retailer id is a random number, the
        // remaining final 12 chars is the geohash of the retailers location
        retailerGeohash = retailerID.slice(4,-1);
        // Finally we only want 7 char precision of the hash so get the first
        // 7 chars of the users geo hash
        retailerGeohash = retailerGeohash.slice(0,7);

        console.log(offerDescription+offerEndTime+retailerName+duration+category);
        console.log(retailerGeohash);

        //We split the geo hash and coordinates by '|' and put them
                            // into the complex array object and log it
                             var str = World.surroundingHashes[0].toString();
                             var smthArray = str.split("|");

                         // As we wish to use a 7 char length precision for a sizeable
                        // geohash we slice the geohash of each offer to ultimately
                        // check if these geohashes match the current user location or
                        // the users surrounding geohashes
//                                 console.log(smthArray[17]);
//                                 console.log(smthArray[19]);
//                                 console.log(smthArray[21]);
//                                 console.log(smthArray[23]);
//                                 console.log(smthArray[25]);
//                                 console.log(smthArray[27]);
//                                 console.log(smthArray[29]);
//                                 console.log(smthArray[33]);
                                         // Define a variable to store the users current location in
                                         // geo  has form and the get to a 7 char precision length.

                                         var usersLocationGeoHash = smthArray[33];
                                         var usersOfferRangeHash = usersLocationGeoHash.slice(0,7);
                                 var northHash = World.getHashes(usersOfferRangeHash)['n'];
                                 var northEastHash = World.getHashes(usersOfferRangeHash)['ne'];
                                 var eastHash = World.getHashes(usersOfferRangeHash)['e'];
                                 var southEastHash = World.getHashes(usersOfferRangeHash)['se'];
                                 var southHash = World.getHashes(usersOfferRangeHash)['s'];
                                 var southWestHash = World.getHashes(usersOfferRangeHash)['sw'];
                                 var westHash = World.getHashes(usersOfferRangeHash)['w'];
                                 var northWestHash = World.getHashes(usersOfferRangeHash)['nw'];
    console.log(usersOfferRangeHash);
    console.log(retailerGeohash);
    console.log(retailerName);
    console.log("PRINTED USER AND OFFER HASH");

        if(retailerGeohash == usersOfferRangeHash || retailerGeohash == northHash || retailerGeohash == northEastHash || retailerGeohash == eastHash || retailerGeohash == southEastHash || retailerGeohash == southHash || retailerGeohash == southWestHash || retailerGeohash == westHash || retailerGeohash == northWestHash){
            World.loadPoisFromJsonData(parseFloat(JSON.parse(longitude)),parseFloat(JSON.parse(latitude)),category,duration,retailerName,offerDescription, offerEndTime, websity, offerDescription2, offerDescription3, rating, streetName,offerEndTime2,offerEndTime3);


            console.log("3d model at geolocation loaded");
            console.log("3d model at geolocation loaded");


            // Call the get remaining time and pass it the end time for the specific offer. This way, the
            // timer for an offer is intialized when the offer is first loaded instead of at each time
            // the user taps the marker to see the pop up that holds the 'time remaining'
            World.getRemainingTime(offerEndTime);

        }
        else{
        console.log("Err not loaded");}

        }



    })
    }


// After firing the ajax post method, call the loadPoisFromDataFn and pass it the pois in the global
// poiData array and the API post response containing the nearby geohashes
// which is in the World.surroundingHashes global array
                    $.when(ajax1()).done(function (_callback){World.obtainAndStoreGeohashes();});
console.log(JSON.stringify({"userCurrentLat": centerPointLatitude, "userCurrentLon": centerPointLongitude}
                ));

//Below is the ajax that posts to our api the users current lat, lon that the
// Main2Activity java class passed when the class runs the architectView.setLocation
// after obtaining the users current lat and lon
        function ajax1(){


        console.log("ajax1 started");
        console.log(centerPointLatitude);
        console.log(centerPointLongitude);
        return $.ajax({
        url: 'https://9ex1ark3n8.execute-api.us-west-2.amazonaws.com/test/georesource',
        type: "POST",
        async: true,
        crossDomain: true,
        contentType: 'application/json',
        data: JSON.stringify({"userCurrentLat": String(centerPointLatitude),"userCurrentLon": String(centerPointLongitude)})
    ,
    // On successful post, we put the response in the global
    // markerResoArray and log it. We also call the global
    // World.passWorldMarkerFunction array and pass it the response data
        dataType: "json",
        success: function(data) {
            World.surroundingHashes[0] = data;
            console.log("ajax1 ran!!");

        },
        // If there is an error with the post we log it
        error: function(xhr, textStatus, error){
                  console.log(xhr.statusText);
                  console.log(textStatus);
                  console.log(error);
        }
    });
}    },

        	init: function initFn(lat,lon,category,duration,retailerName,offerDescription, offerStartTime) {
        		this.createModelAtLocation(lat,lon,category,duration,retailerName,offerDescription, offerStartTime);
        	},

	// fired when user pressed maker in cam
	onMarkerSelected: function onMarkerSelectedFn(marker) {
console.log("on marker selected");
//         var worldClick = 'architectsdk://' + "|" + "worldclicked" + "|" + offerDescription + "|" + category + "|" + offerEndTime + "|" + duration + "|" + website + "|" + offerDescription2 + "|" + offerDescription3 +"|" + rating +"|" + offerEndTime2 + "|" + offerEndTime3 + "|";
//         document.location = worldClick;
//console.log(marker.poiData.id);
//		// deselect previous marker
//		if (World.currentMarker) {
//			if (World.currentMarker.poiData.id == marker.poiData.id) {
//				return;
//			}
//			World.currentMarker.setDeselected(World.currentMarker);
//		}
//
//		// highlight current one
//		marker.setSelected(marker);
//		World.currentMarker = marker;
	},


	// screen was clicked but no geo-object was hit
	onScreenClick: function onScreenClickFn(marker) {
	console.log(marker);
		if (marker) {
         World.currentMarker.setDeselected(World.currentMarker);
					console.log("current marker");

		}
		else{

					console.log("ONSCREENCLICK");
        //    			World.currentMarker.setDeselected(World.currentMarker);
                    			var worldClick = 'architectsdk://' + "|" + "worldclicked" + "|" + offerDescription + "|" + category + "|" + offerEndTime + "|" + duration + "|" + website + "|" + offerDescription2 + "|" + offerDescription3 +"|" + rating +"|" + offerEndTime2 + "|" + offerEndTime3 + "|";
                                document.location = worldClick;

		}
	},

	// request POI data
	requestDataFromLocal: function requestDataFromLocalFn(centerPointLatitude, centerPointLongitude) {
		var poisToCreate = 20;
		var poiData = [];


		for (var i = 0; i < poisToCreate; i++) {
			poiData.push({
				"id": (i + 1),
				"longitude": (centerPointLongitude - centerPointLongitude + centerPointLongitude),
				"latitude": (centerPointLatitude - centerPointLatitude + centerPointLatitude),
				"description": ("This is the description of POI#" + (i + 1)),
				"altitude": "100.0",
				"name": ("POI#" + (i + 1))
			});
		}
			console.log(poiData[1].id);

		World.loadPoisFromJsonData(poiData);
	},


	// location updates, fired every time you call architectView.setLocation() in native environment
	locationChanged: function locationChangedFn(lat, lon, alt, acc) {

		/*
			The custom function World.onLocationChanged checks with the flag World.initiallyLoadedData if the function was already called. With the first call of World.onLocationChanged an object that contains geo information will be created which will be later used to create a marker using the World.loadPoisFromJsonData function.
		*/
		if (!World.initiallyLoadedData) {
			/*
				requestDataFromLocal with the geo information as parameters (latitude, longitude) creates different poi data to a random location in the user's vicinity.
			*/
			console.log(lat);
			console.log("LAT");
			World.iWantGeoHashes(lat,lon);

			World.newFuncy(lat,lon, World.obtainAndStoreGeohashes);


			World.initiallyLoadedData = true;
		}
	},

// called to create the geohashes for the user and surrounding geohashes of the users location
	 // and finally post and store those in the geo hash dynamo db cache

	obtainAndStoreGeohashes: function obtainAndStoreGeohashesFn() {

// Note: we destroy all the markers everytime this function is called
// which should be everytime the user location changes
//AR.context.destroyAll();
    console.log('obtain geohashes started');

                    // We split the geo hash and coordinates by '|' and put them
                    // into the complex array object and log it
                     var str = World.surroundingHashes[0].toString();
                     var smthArray = str.split("|");


             // The below elements of the array, returned by the api are
             // the offers geohash or 6 nearby offers

                     var offer1Hash = smthArray[19];
                     var offer1Hash = smthArray[19];
                     var offer2Hash = smthArray[21];
                     var offer3Hash = smthArray[23];
                     var offer4Hash = smthArray[25];
                     var offer5Hash = smthArray[27];
                     var offer6Hash = smthArray[29];


             // As we wish to use a 7 char length precision for a sizeable
            // geohash we slice the geohash of each offer to ultimately
            // check if these geohashes match the current user location or
            // the users surrounding geohashes
                     var northHash = smthArray[17].slice(0,7);
                     var northEastHash = smthArray[19].slice(0,7);
                     var eastHash = smthArray[21].slice(0,7);
                     var southEastHash = smthArray[23].slice(0,7);
                     var southHash = smthArray[25].slice(0,7);
                     var southWestHash = smthArray[27].slice(0,7);
                     var westHash = smthArray[29].slice(0,7);
                     var northWestHash = smthArray[29].slice(0,7);

             // Define a variable to store the users current location in
             // geo  has form and the get to a 7 char precision length.

             var usersLocationGeoHash = smthArray[31];
             var usersOfferRangeHash = usersLocationGeoHash.slice(0,7);

             // As we wish to use a 7 char length precision for a sizeable
            // geohash we slice the geohash of each offer to ultimately
            // check if these geohashes match the current user location or
            // the users surrounding geohashes
            var northHsh = smthArray[17];
            var northEastHsh = smthArray[19];
            var eastHsh = smthArray[21];
            var southEastHsh = smthArray[23];
            var southHsh = smthArray[25];
            var southWestHsh = smthArray[27];
            var westHsh = smthArray[29];
            var northWestHsh = smthArray[29];

             //Below is the ajax that stores the surrounding geohashes for a given geohash for faster indexing
                             function ajax3(){
                             return $.ajax({
                             url: 'https://9ex1ark3n8.execute-api.us-west-2.amazonaws.com/test/load-geohash-caches',
                             type: "POST",
                             async: true,
                             crossDomain: true,
                             contentType: 'application/json',
                             data: JSON.stringify({"CentreGeoHash": usersLocationGeoHash,"NorthHash":northHsh,"NorthEastHash":northEastHsh,"EastHash":eastHsh,"SouthEastHash":southEastHsh,"SouthHash":southHsh,"SouthWestHash":southWestHsh,"WestHash":westHsh,"NorthWestHash":northWestHsh})
                         ,
                         // On successful post, we put the response in the global
                         // markerResoArray and log it. We also call the global
                         // World.passWorldMarkerFunction array and pass it the response data
                             dataType: "json",
                             success: function(data) {
                                console.log(JSON.stringify(data));

                             },
                             // If there is an error with the post we log it
                             error: function(xhr, textStatus, error){
                                       console.log(xhr.statusText);
                                       console.log(textStatus);
                                       console.log(error);
                             }
                         });
                     };
                     // Run the above ajax2 function to load the users geohash and surrounding
                      // geohashes in the geohash cache
                     ajax3();

                    // Log the 7 char precision of the offers
                     console.log(usersOfferRangeHash);
                     console.log("Offers..");
                     console.log(northHash);
                     console.log(northEastHash);
                     console.log(eastHash);
                     console.log(southEastHash);
                     console.log(southHash);
                     console.log(southWestHash);
                     console.log(westHash);
                     console.log(northWestHash);

         // Inform the user how many places are loaded
		World.updateStatusMessage('places loaded');
		var worldClick = 'architectsdk://' + "|" + "worldclicked" + "|";
        document.location = worldClick;
	},
// The filterByStreet function is called by the filterbyStree global function at the bottom of this JS file. The global func is called from java every time the users location changes.
// Filter by street takes the current users street name and checks every marker loaded and then if it doesn't it disables it from showing
filterByStreet: function filterByStreet(streetName, cameFromButton){
if( cameFromButton == false){
console.log("Filter by street starting");
   	for (var i = 0; i < World.markerList.length; i++){
   	                    console.log(World.markerList[i].streetName.toLowerCase());
                        console.log(streetName.toLowerCase());
		    if(World.markerList[i].streetName.toLowerCase() == streetName.toLowerCase()){
                    console.log("Street true");
                }
                else{
                    World.markerList[i].markerObject.enabled = false;

                }
}
}
else{
    for (var i = 0; i < World.markerList.length; i++){
                  World.markerList[i].markerObject.enabled = true;
              }

}
//console.log(World.markerList.length);
//                if (typeof(Worker) !== "undefined") {
//                var result ="hp";
//                                console.log("WORKERDEFINED")
//                                     var worker = new Worker("js/worker.js");
//  worker.addEventListener('message', function(e) {
//    console.log('Worker said: ');
//    console.log(e.data);
//  }, false);
//
//  var arry = World.markerList.slice();
//  console.log(arry[0].streetName);
//
//  console.log(JSON.stringify(World.markerList))
//  var fg = [streetName, arry];
//  worker.postMessage(JSON.stringify(World.markerList));
//
//                    // Yes! Web worker support!
//                    // Some code.....
//                } else {
//                console.log("WORKERUNDEFINED")
//                    // Sorry! No Web Worker support..
//                }




},

firstAndLastFilt: function firstAndLastFilt(first){
console.log("First and last filt");
                if(first == true){console.log("First filt");

                        			for (var i = 0; i < World.markerList.length; i++){
                        			            		World.markerList[i].markerObject.enabled = false;

                }
                }
                else{console.log("Last filt");

                        			for (var i = 0; i < World.markerList.length; i++){
                        			            		World.markerList[i].markerObject.enabled = true;

                }
                }

},
filterMarkers: function filterMarkers(drinksFilt, accommodationFilt,attractionsFilt,fooddiningFilt,moviesFilt, shopFilt){

			for (var i = 0; i < World.markerList.length; i++){
console.log(drinksFilt);
console.log(accommodationFilt);
console.log(attractionsFilt);
console.log(fooddiningFilt);
console.log(moviesFilt);
console.log(shopFilt);

			if(drinksFilt == true && World.markerList[i].category == "drinks"){
            		console.log("DRINKS TRUE");
            		World.markerList[i].markerObject.enabled = true;
            			}

			if(shopFilt == true && World.markerList[i].category == "shopping"){
            		World.markerList[i].markerObject.enabled = true;
            			}

			if(moviesFilt == true && World.markerList[i].category == "movies"){
            		World.markerList[i].markerObject.enabled = true;
            			}

			if(fooddiningFilt == true && World.markerList[i].category == "food/dining"){
            		World.markerList[i].markerObject.enabled = true;
            			}

			if(attractionsFilt == true && World.markerList[i].category == "attractions"){
            		World.markerList[i].markerObject.enabled = true;
            			}

			if(accommodationFilt == true && World.markerList[i].category == "accommodation"){
            		World.markerList[i].markerObject.enabled = true;
            			}
            			if(drinksFilt == false && World.markerList[i].category == "drinks"){
                        		console.log("DRINKS TRUE");
                        		World.markerList[i].markerObject.enabled = false;
                        			}

            			if(shopFilt == false && World.markerList[i].category == "shopping"){
                        		World.markerList[i].markerObject.enabled = false;
                        			}

            			if(moviesFilt == false && World.markerList[i].category == "movies"){
                        		World.markerList[i].markerObject.enabled = false;
                        			}

            			if(fooddiningFilt == false && World.markerList[i].category == "food/dining"){
                        		World.markerList[i].markerObject.enabled = false;
                        			}

            			if(attractionsFilt == false && World.markerList[i].category == "attractions"){
                        		World.markerList[i].markerObject.enabled = false;
                        			}

            			if(accommodationFilt == false && World.markerList[i].category == "accommodation"){
                        		World.markerList[i].markerObject.enabled = false;
                        			}

            }



},

	}


/*
	Set a custom function where location changes are forwarded to. There is also a possibility to set AR.context.onLocationChanged to null. In this case the function will not be called anymore and no further location updates will be received.
*/
AR.context.onLocationChanged = World.locationChanged;
AR.context.onScreenClick = World.onScreenClick;

/*
	To detect clicks where no drawable was hit set a custom function on AR.context.onScreenClick where the currently selected marker is deselected.
*/





function filterMarkers(drinksFilt, accommodationFilt,attractionsFilt,fooddiningFilt,moviesFilt, shopFilt){World.filterMarkers(drinksFilt, accommodationFilt,attractionsFilt,fooddiningFilt,moviesFilt, shopFilt);};

function firstAndLastFilt(first){World.firstAndLastFilt(first);};

function filterByStreet(streetName,cameFromButton){World.filterByStreet(streetName,cameFromButton);};