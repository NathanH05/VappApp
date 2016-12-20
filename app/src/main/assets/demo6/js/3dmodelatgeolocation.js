

// implementation of AR-Experience (aka "World")
var World = {

/*
$.post('https://ffemuvnzx6.execute-api.us-west-2.amazonaws.com/test/mydemoresource', {companyName: 'Darlan'}, function(response){
    // callback success
    alert.log("Success")
}, function(response) {
    // callback error
    alert.log("Hmm some error")
});

*/

	// true once data was fetched
	initiallyLoadedData: false,

	// different POI-Marker assets
	markerDrawable_idle: null,
	markerDrawable_selected: null,

	markerDrawable_idle2: null,
	markerDrawable_selected2: null,

	markerDrawable_idle3: null,
	markerDrawable_selected3: null,

    poiData: [],
	markerDrawable_idle4: null,
	markerDrawable_selected4: null,

	// list of AR.GeoObjects that are currently shown in the scene / World
	markerList: [],

	markerReso: [],

	offers: [],

	ntr: [],

	nat: [],

    counter: [],



	// The last selected marker
	currentMarker: null,



    newFunction: function newFunction(smth) {
            // newFunction is called once the api has received the
            // geo coordinates, surrounding geo hashes and offers to show
            // We split the geo hash and coordinates by '|' and put them
            // into the complex array object and log it
             console.log(smth);//or do whatever you want to do with this data
             var str = smth.toString();
             var smthArray = str.split("|");
             console.log(smthArray[0]);
             console.log(smthArray[1]);
             console.log(smthArray[2]);
             console.log(smthArray[3]);
             console.log(smthArray[4]);
             console.log(smthArray[5]);
             console.log(smthArray[6]);
             console.log(smthArray[7]);
             console.log(smthArray[8]);
             console.log(smthArray[9]);
             console.log(smthArray[10]);
             console.log(smthArray[11]);
             console.log(smthArray[12]);
             console.log(smthArray[13]);
             console.log(smthArray[14]);
             console.log(smthArray[15]);
             console.log(smthArray[16]);
             console.log(smthArray[17]);
             console.log(smthArray[18]);
             console.log(smthArray[19]);
             console.log(smthArray[20]);
             console.log(smthArray[21]);
             console.log(smthArray[22]);
             console.log(smthArray[23]);
             console.log(smthArray[24]);
             console.log(smthArray[25]);
             console.log(smthArray[26]);
             console.log(smthArray[27]);
             console.log(smthArray[28]);
             console.log(smthArray[29]);
             console.log(smthArray[30]);
             console.log(smthArray[31]);

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
             var offer1= smthArray[19].slice(0,7);
             var offer2 = smthArray[21].slice(0,7);
             var offer3 = smthArray[23].slice(0,7);
             var offer4 = smthArray[25].slice(0,7);
             var offer5 = smthArray[27].slice(0,7);
             var offer6 = smthArray[29].slice(0,7);

             // Define a variable to store the users current location in
             // geo  has form and the get to a 7 char precision length.

             var usersLocationGeoHash = smthArray[31];
             var usersOfferRangeHash = usersLocationGeoHash.slice(0,7);

            // Log the 7 char length precision of the 6 offers
             console.log(usersOfferRangeHash);
             console.log("Offers..");
             console.log(offer1);
             console.log(offer2);
             console.log(offer3);
             console.log(offer4);
             console.log(offer5);
             console.log(offer6);

            // Define booleans to ultimately decide whether the
            // user is within the distance of the offers and hence
            // whether the app should return the deals in the AR view
             var showMarker1 = false;
             var showMarker2 = false;
             var showMarker3 = false;
             var showMarker4 = false;
             var showMarker5 = false;
             var showMarker6 = false;

            // If the offer matches the hash of where the user is return true
            // note we may need to check it against the surrounding hashes
            // too
             if(offer1 == usersOfferRangeHash){
             showMarker1 = true;
             }
             if(offer2 == usersOfferRangeHash){
             showMarker2 = true;
             }if(offer3 == usersOfferRangeHash){
             showMarker3 = true;
             }if(offer4 == usersOfferRangeHash){
             showMarker4 = true;
             }if(offer5 == usersOfferRangeHash){
             showMarker5 = true;
             }
             if (offer6 == usersOfferRangeHash){
             showMarker6 = true;
             }

            // Log each of the marker checks
             console.log(showMarker1);
             console.log(showMarker2);
             console.log(showMarker3);
             console.log(showMarker4);
             console.log(showMarker5);
             console.log(showMarker6);



        },





// newFuncy is fired after each LocationChange and its purpose is to create 4
// POI markers for different offers. It also does an ajax post of the users
// current lat, lon in order to receive the surrounding geohashes
// within our defined distance. We'll later use these geo hashes to
// to decide which offers to return
    newFuncy: function newFuncy(centerPointLatitude,centerPointLongitude, _callback){



 var poisToCreate = 6;
 // Ensure the lat and lon JSON from the user device is in a string format.
 // This may be redundant
// centerPointLatitude = JSON.stringify(centerPointLatitude);
  centerPointLongitude = JSON.stringify(centerPointLongitude);

var centerPointLatitude2 = "'" + toString(centerPointLatitude) + "'";
var centerPointLatitude4 = JSON.stringify(toString(centerPointLatitude));
console.log(centerPointLatitude2);
console.log(centerPointLatitude4);


$.when(ajax2()).done(function (_callback){console.log(World.offers[0]);});

function ajax2(){
	World.nat[0] = -36.859096;
	World.nat[1] = 174.750140;
	World.nat[2] = -36.859372;
	World.nat[3] = 174.750454;
	World.nat[4] = -36.859638;
	World.nat[5] = 174.750524;
	World.nat[6] = -36.859728;
	World.nat[7] = 174.750846;
	World.nat[8] = -36.859728;
	World.nat[9] = 174.750846;
	World.nat[10] = -36.860110;
	World.nat[11] = 174.751281;
	World.counter[0] = 1;

return $.get('https://9ex1ark3n8.execute-api.us-west-2.amazonaws.com/test/geooffers', function(data){

World.offers[0] = JSON.stringify(data);
});
};

// In the global poiData array push the 4 markers in that represent each offer
        			World.poiData.push({
        				"id": (1),
        				"longitude": (centerPointLongitude - centerPointLongitude + 174.750127),
        				"latitude": (centerPointLatitude - centerPointLatitude + -36.859061),
        				"description": (""),
        				"altitude": "100.0",
        				"name": ("")
        			},

        			{
                    	"id": (2),
                    	"longitude": (centerPointLongitude - centerPointLongitude + 174.747649),
                    	"latitude": (centerPointLatitude - centerPointLatitude + -36.857184),
                    	"description": (""),
                    	"altitude": "100.0",
                    	"name": ("")
                    },
        			{
                    	"id": (3),
                    	"longitude": (centerPointLongitude - centerPointLongitude + 174.747885),
                    	"latitude": (centerPointLatitude - centerPointLatitude + -36.857267),
                    	"description": (""),
                    	"altitude": "100.0",
                    	"name": ("")
                    },
        			{
                    	"id": (4),
                    	"longitude": (centerPointLongitude - centerPointLongitude + 174.748027),
                    	"latitude": (centerPointLatitude - centerPointLatitude + -36.857319),
                    	"description": (""),
                    	"altitude": "100.0",
                    	"name": ("MadMex")
                    },
                    {
                    	"id": (5),
                    	"longitude": (centerPointLongitude - centerPointLongitude + 174.764355),
                    	"latitude": (centerPointLatitude - centerPointLatitude + -36.906430),
                    	"description": (""),
                    	"altitude": "100.0",
                    	"name": ("Buckley Road Dairy")
                    },
                    {
                    	"id": (6),
                    	"longitude": (centerPointLongitude - centerPointLongitude + 174.763658),
                    	"latitude": (centerPointLatitude - centerPointLatitude + -36.909330),
                    	"description": (""),
                    	"altitude": "100.0",
                    	"name": ("Revamp Hair")
                    }


                    );

// After firing the ajax post method, call the loadPoisFromDataFn and pass it the pois in the global
// poiData array and the API post response containing the nearby geohashes
// which is in the World.markerReso global array
                    $.when(ajax1()).done(function (_callback){World.loadPoisFromJsonData(World.poiData,World.markerReso);});
console.log(JSON.stringify({"userCurrentLat": centerPointLatitude, "userCurrentLon": centerPointLongitude}
                ));



//Below is the ajax that posts to our api the users current lat, lon that the
// Main2Activity java class passed when it did architectView.setLocation\
// after obtaining the users current lat and lon
        function ajax1(){
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
        console.log(data);
            World.markerReso[0] = data;
            //console.log(World.markerReso);
            //console.log(JSON.stringify(data))
           // alert("API/LAMBDA post response: " + data);


            World.passWorldMarkerFunction(World.markerReso);




        },
        // If there is an error with the post we log it
        error: function(xhr, textStatus, error){
                  console.log(xhr.statusText);
                  console.log(textStatus);
                  console.log(error);
        }
    });
};





    },

// The passWorldMarkerFunction is redundant but logs the data response from the api
    passWorldMarkerFunction: function passWorldMarkerFunction(geoData) {

    World.ntr[0] = geoData;
    console.log(World.ntr[0]);
    },

    		loaded: false,
        	rotating: false,

        	init: function initFn(lat,lon) {
        		this.createModelAtLocation(lat,lon);
        	},

        	createModelAtLocation: function createModelAtLocationFn(lat,lon) {

        		/*
        			First a location where the model should be displayed will be defined. This location will be relativ to the user.
        		*/
        		var location = new AR.GeoLocation(lat,lon);

        		/*
        			Next the model object is loaded.
        		*/
        		var clicked = false;

        		World.counter[0] = World.counter[0] + 1;
        		console.log(World.counter[0]);
        				var modelEarth = new AR.Model("assets/handbag2.wt3", {
        				onClick: function() {console.log("hi")},
                			onLoaded: this.worldLoaded,
                			scale: {
                				x: .5,
                				y: .5,
                				z: .5
                			}
                		});

                var indicatorImage = new AR.ImageResource("assets/indi.png");

                var indicatorDrawable = new AR.ImageDrawable(indicatorImage, 0.1, {
                    verticalAnchor: AR.CONST.VERTICAL_ANCHOR.TOP
                });

        		/*
        			Putting it all together the location and 3D model is added to an AR.GeoObject.
        		*/

        		var obj = new AR.GeoObject(location, {
                    drawables: {
                       cam: [modelEarth],
                       indicator: [indicatorDrawable]
                    }
                });


        	},

        	worldLoaded: function worldLoadedFn() {
        		World.loaded = true;
        		var e = document.getElementById('loadingMessage');
        		e.parentElement.removeChild(e);
        	},






	// called to inject new POI data
	loadPoisFromJsonData: function loadPoisFromJsonDataFn(poiData) {

// Note: we destroy all the markers everytime this function is called
// which should be everytime the user location changes
//AR.context.destroyAll();

		// empty list of visible markers
		World.markerList = [];
		console.log(World.ntr[0]);

                    // We split the geo hash and coordinates by '|' and put them
                    // into the complex array object and log it
                     var str = World.ntr[0].toString();
                     var smthArray = str.split("|");
                     console.log(smthArray[0]);
                     console.log(smthArray[1]);
                     console.log(smthArray[2]);
                     console.log(smthArray[3]);
                     console.log(smthArray[4]);
                     console.log(smthArray[5]);
                     console.log(smthArray[6]);
                     console.log(smthArray[7]);
                     console.log(smthArray[8]);
                     console.log(smthArray[9]);
                     console.log(smthArray[10]);
                     console.log(smthArray[11]);
                     console.log(smthArray[12]);
                     console.log(smthArray[13]);
                     console.log(smthArray[14]);
                     console.log(smthArray[15]);
                     console.log(smthArray[16]);
                     console.log(smthArray[17]);
                     console.log(smthArray[18]);
                     console.log(smthArray[19]);
                     console.log(smthArray[20]);
                     console.log(smthArray[21]);
                     console.log(smthArray[22]);
                     console.log(smthArray[23]);
                     console.log(smthArray[24]);
                     console.log(smthArray[25]);
                     console.log(smthArray[26]);
                     console.log(smthArray[27]);
                     console.log(smthArray[28]);
                     console.log(smthArray[29]);
                     console.log(smthArray[30]);
                     console.log(smthArray[31]);


             // The below elements of the array, returned by the api are
             // the offers geohash or 6 nearby offers

                     var offer1Hash = smthArray[19];
                     var offer1Hash = smthArray[19];
                     var offer2Hash = smthArray[21];
                     var offer3Hash = smthArray[23];
                     var offer4Hash = smthArray[25];
                     var offer5Hash = smthArray[27];
                     var offer6Hash = smthArray[29];

                     var randOffr1 = smthArray[35].slice(0,7);
                     var randOffr2 = smthArray[37].slice(0,7);
                     var carlsonOffr = smthArray[39].slice(0,7);
                     var burgerFOffr = smthArray[41].slice(0,7);
                     var buckleyDairyOffr = smthArray[43].slice(0,7);
                     var revampOffr = smthArray[45].slice(0,7);

                     console.log(randOffr1);
                     console.log(randOffr2);
                     console.log(carlsonOffr);
                     console.log(burgerFOffr);
                     console.log(buckleyDairyOffr);
                     console.log(revampOffr);

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

  // Define a variable to store the users current location in
             // geo  has form and the get to a 7 char precision length.
/*
                     var usersLocationGeoHash = smthArray[31];
                     var usersLocationGeoHashN = smthArray[33];
                     var usersLocationGeoHashNE = smthArray[35];
                     var usersLocationGeoHashE = smthArray[37];
                     var usersLocationGeoHashSE = smthArray[39];
                     var usersLocationGeoHashS = smthArray[41];
                     var usersLocationGeoHashSW = smthArray[43];
                     var usersLocationGeoHashW = smthArray[45];
                     var usersLocationGeoHashNW = smthArray[47];


                     var usersOfferRangeHashSliced = usersLocationGeoHash.slice(0,8);
                     var usersOfferRangeHashNSliced = usersLocationGeoHashN.slice(0,8);
                     var usersOfferRangeHashNESliced = usersLocationGeoHashNE.slice(0,8);
                     var usersOfferRangeHashESliced = usersLocationGeoHashE.slice(0,8);
                     var usersOfferRangeHashSESliced = usersLocationGeoHashSE.slice(0,8);
                     var usersOfferRangeHashSSliced = usersLocationGeoHashS.slice(0,8);
                     var usersOfferRangeHashSWSliced = usersLocationGeoHashSW.slice(0,8);
                     var usersOfferRangeHashWSliced = usersLocationGeoHashW.slice(0,8);
                     var usersOfferRangeHashNWSliced = usersLocationGeoHashNW.slice(0,8);
*/
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
                // Define booleans to ultimately decide whether the
                // user is within the distance of the offers and hence
                // whether the app should return the deals in the AR view
                     var showMarker1 = false;
                     var showMarker2 = false;
                     var showMarker3 = false;
                     var showMarker4 = false;
                     var showMarker5 = false;
                     var showMarker6 = false;

                     showMarker = [];
                     showMarker[0] = false;
                     showMarker[1] = false;
                     showMarker[2] = false;
                     showMarker[3] = false;
                     showMarker[4] = false;
                     showMarker[5] = false;


            // If the offer matches the hash of where the user is return true
            // note we may need to check it against the surrounding hashes
            // too
                     if(randOffr1 == usersOfferRangeHash || randOffr1 == northHash || randOffr1 == northEastHash || randOffr1 == eastHash || randOffr1 == southEastHash || randOffr1 == southHash || randOffr1 == southWestHash || randOffr1 == westHash || randOffr1 == northWestHash
                    ){
                     showMarker[0] = true;
                     }
                     if(randOffr2 == usersOfferRangeHash || randOffr2 == northHash || randOffr2 == northEastHash || randOffr2 == eastHash || randOffr2 == southEastHash || randOffr2 == southHash || randOffr2 == southWestHash || randOffr2 == westHash || randOffr2 == northWestHash
                     ){
                     showMarker[1] = true;
                     }
                     if(carlsonOffr == usersOfferRangeHash || carlsonOffr == northHash || carlsonOffr == northEastHash || carlsonOffr == eastHash || carlsonOffr == southEastHash || carlsonOffr == southHash || carlsonOffr == southWestHash || carlsonOffr == westHash || carlsonOffr == northWestHash
                     ){showMarker[2] = true;
                     }
                     if(burgerFOffr == usersOfferRangeHash || burgerFOffr == northHash || burgerFOffr == northEastHash || burgerFOffr == eastHash || burgerFOffr == southEastHash || burgerFOffr == southHash || burgerFOffr == southWestHash || burgerFOffr == westHash || burgerFOffr == northWestHash
                     ){showMarker[3] = true;
                     }
                     if(buckleyDairyOffr == usersOfferRangeHash || buckleyDairyOffr == northHash || buckleyDairyOffr == northEastHash || buckleyDairyOffr == eastHash || buckleyDairyOffr == southEastHash || buckleyDairyOffr == southHash || buckleyDairyOffr == southWestHash || buckleyDairyOffr == westHash || buckleyDairyOffr == northWestHash
                     ){showMarker[4] = true;
                     }
                     if(revampOffr == usersOfferRangeHash || revampOffr == northHash || revampOffr == northEastHash || revampOffr == eastHash || revampOffr == southEastHash || revampOffr == southHash || revampOffr == southWestHash || revampOffr == westHash || revampOffr == northWestHash
                     ){showMarker[5] = true;
                     }

                    // Log the result of the showMarkers test
                     console.log(showMarker[0]);
                     console.log(showMarker[1]);
                     console.log(showMarker[2]);
                     console.log(showMarker[3]);
                     console.log(showMarker[4]);
                     console.log(showMarker[5]);

		// start loading marker assets
		World.markerDrawable_idle = new AR.ImageResource("assets/roostMarker.png");
		World.markerDrawable_selected = new AR.ImageResource("assets/roostMarker.png");

		// start loading marker assets
		World.markerDrawable_idle2 = new AR.ImageResource("assets/carlsonMarker.png");
		World.markerDrawable_selected2 = new AR.ImageResource("assets/carlsonMarker.png");


		// start loading marker assets
		World.markerDrawable_idle3 = new AR.ImageResource("assets/burgerFuelMarker.png");
		World.markerDrawable_selected3 = new AR.ImageResource("assets/burgerFuelMarker.png");

		// start loading marker assets
		World.markerDrawable_idle4 = new AR.ImageResource("assets/madMex.png");
		World.markerDrawable_selected4 = new AR.ImageResource("assets/madMex.png");

		// start loading marker assets
		World.markerDrawable_idle5 = new AR.ImageResource("assets/carlsonMarker.png");
		World.markerDrawable_selected5 = new AR.ImageResource("assets/carlsonMarker.png");

		// start loading marker assets
		World.markerDrawable_idle6 = new AR.ImageResource("assets/burgerFuel.png");
		World.markerDrawable_selected6 = new AR.ImageResource("assets/burgerFuel.png");

        // check how many placeholder markers are loaded in the poiData array
		console.log(poiData.length);

		// loop through POI-information and create an AR.GeoObject (=Marker) per POI
		for (var currentPlaceNr = 0; currentPlaceNr < poiData.length; currentPlaceNr++) {
		    if(showMarker[currentPlaceNr] == true){


			var singlePoi = {
				"id": poiData[currentPlaceNr].id,
				"latitude": parseFloat(poiData[currentPlaceNr].latitude),
				"longitude": parseFloat(poiData[currentPlaceNr].longitude),
				"altitude": parseFloat(poiData[currentPlaceNr].altitude),
				"title": poiData[currentPlaceNr].name,
				"description": poiData[currentPlaceNr].description
			};
			var singlePoi2 = JSON.stringify(singlePoi);
			console.log(singlePoi2);

			/*
				To be able to deselect a marker while the user taps on the empty screen,
				the World object holds an array that contains each marker.
			*/
			World.markerList.push(new Marker(singlePoi));
			}
		}

         // Inform the user how many places are loaded
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

	// location updates, fired every time you call architectView.setLocation() in native environment
	locationChanged: function locationChangedFn(lat, lon, alt, acc) {
		/*
			The custom function World.onLocationChanged checks with the flag World.initiallyLoadedData if the function was already called. With the first call of World.onLocationChanged an object that contains geo information will be created which will be later used to create a marker using the World.loadPoisFromJsonData function.
		*/

			/*
				requestDataFromLocal with the geo information as parameters (latitude, longitude) creates different poi data to a random location in the user's vicinity.
			*/
if (!World.initiallyLoadedData) {
			// This function is the root of this JS file as it starts
			// the trickle of calls to every other funciton in this file
			// Firstly, new Funcy is called and passes the lat, lon that
			// the MainActivity2 java file is called with. It also passes it the
			// loadPoisFromJsonData function as a callback that is called once newFuncy
			// completes its post call
			World.newFuncy(lat,lon, World.loadPoisFromJsonData);
			// Sets a boolean to true if initial data is loaded.
			World.initiallyLoadedData = true;
			console.log(lat, lon);

			console.log("fired");

World.init(World.nat[0], World.nat[1]);
World.init(World.nat[2], World.nat[3]);
World.init(World.nat[4], World.nat[5]);
World.init(World.nat[6], World.nat[7]);
World.init(World.nat[8], World.nat[9]);
World.init(World.nat[10], World.nat[11]);


		}
	},

	// fired when user pressed maker in cam
	onMarkerSelected: function onMarkerSelectedFn(marker) {

		// deselect previous marker
		if (World.currentMarker) {
			if (World.currentMarker.World.poiData.id == marker.World.poiData.id) {
				return;
			}
			World.currentMarker.setDeselected(World.currentMarker);
		}

		// highlight current one
		marker.setSelected(marker);
		World.currentMarker = marker;
	},

	// screen was clicked but no geo-object was hit
	onScreenClick: function onScreenClickFn() {
		if (World.currentMarker) {
			World.currentMarker.setDeselected(World.currentMarker);
		}
	},

	// request POI data
	requestDataFromLocal: function requestDataFromLocalFn(centerPointLatitude, centerPointLongitude) {

    }


};

/*
	Set a custom function where location changes are forwarded to. There is also a possibility to set AR.context.onLocationChanged to null. In this case the function will not be called anymore and no further location updates will be received.
*/
AR.context.onLocationChanged = World.locationChanged;

/*
	To detect clicks where no drawable was hit set a custom function on AR.context.onScreenClick where the currently selected marker is deselected.
*/
AR.context.onScreenClick = World.onScreenClick;







/*
Below is a get method tester to our API if we need to test a 'GET'
$.get('https://ffemuvnzx6.execute-api.us-west-2.amazonaws.com/test/vappappoffersapi', function(data){
alert("API/LAMBDA get response: " + data.companyName + data.offerDescription + data.offerStartTime + data.offerEndTime + data.businessLat + data.businessLng);


});
*/