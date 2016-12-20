function Marker(poiData, retailerName,offerDescription,category,offerEndTime,duration, website,offerDescription2,offerDescription3,rating, streetName, offerEndTime2, offerEndTime3) {

    /*
        For creating the marker a new object AR.GeoObject will be created at the specified geolocation. An AR.GeoObject connects one or more AR.GeoLocations with multiple AR.Drawables. The AR.Drawables can be defined for multiple targets. A target can be the camera, the radar or a direction indicator. Both the radar and direction indicators will be covered in more detail in later examples.
    */

    this.poiData = poiData;
    this.retailerName = retailerName;
    this.offerDescription = offerDescription;
    this.offerDescription2 = offerDescription2;
    this.offerDescription3 = offerDescription3;
    this.category = category;
    this.offerEndTime = offerEndTime;
    this.website = website;
    this.rating = rating;
    this.streetName = streetName;
    this.offerEndTime2 = offerEndTime2;
    this.offerEndTime3 = offerEndTime3;



 (function() {    console.log(this.retailerName);
                  console.log(this.offerDescription);
                  console.log(this.category);
                  console.log(this.offerEndTime);
                  console.log(this.duration);});


    // create the AR.GeoLocation from the poi data
    var markerLocation = new AR.GeoLocation(poiData.latitude, poiData.longitude, poiData.altitude);

    // create an AR.ImageDrawable for the marker in idle state
    this.markerDrawable_idle = new AR.ImageDrawable(World.markerDrawable_idle, 1.6, {
        zOrder: 0,
        opacity: 1.0,
        scale:0.7,
        /*
            To react on user interaction, an onClick property can be set for each AR.Drawable. The property is a function which will be called each time the user taps on the drawable. The function called on each tap is returned from the following helper function defined in marker.js. The function returns a function which checks the selected state with the help of the variable isSelected and executes the appropriate function. The clicked marker is passed as an argument.
        */
        onClick: (function(){console.log("CLICKED2")})
    });

    // create an AR.ImageDrawable for the marker in selected state
    this.markerDrawable_selected = new AR.ImageDrawable(World.markerDrawable_selected, 1.6, {
        zOrder: 0,
        opacity: 0.0,
        height:2,
        scale:.7
        ,        onClick: (function() { console.log(this.retailerName);
                                                 console.log(offerDescription);
                                                 console.log(category);
                                                 console.log(offerEndTime);
                                                 console.log(duration);console.log("CLICKED");var offerDetls = 'architectsdk://' + "|" + retailerName + "|" + offerDescription + "|" + category + "|" + offerEndTime + "|" + duration + "|" + website + "|" + offerDescription2 + "|" + offerDescription3 +"|" + rating +"|" + offerEndTime2 + "|" + offerEndTime3 + "|";
                                                 console.log(offerDetls); document.location = offerDetls;
                                                             World.onMarkerSelected();
                                                             console.log(marker.isSelected);})
    });

    // create an AR.Label for the marker's title
    this.titleLabel = new AR.Label(poiData.title.trunc(10), 1, {
        zOrder: 1,
        offsetY: 0.55,
        style: {
            textColor: '#FFFFFF',
            fontStyle: AR.CONST.FONT_STYLE.BOLD
        }
    });

    // create an AR.Label for the marker's description
    this.descriptionLabel = new AR.Label(poiData.description.trunc(15), 0.8, {
        zOrder: 1,
        offsetY: -0.55,
        style: {
            textColor: '#FFFFFF'
        }
    });

    // create the AR.GeoObject with the drawable objects
    this.markerObject = new AR.GeoObject(markerLocation, {
        drawables: {
            cam: [this.markerDrawable_idle, this.markerDrawable_selected, this.titleLabel, this.descriptionLabel]
        },
        enabled: true
    });

    return this;
}

Marker.prototype.getOnClickTrigger = function(marker,poiData) {

    /*
        The setSelected and setDeselected functions are prototype Marker functions.
        Both functions perform the same steps but inverted, hence only one function (setSelected) is covered in detail. Three steps are necessary to select the marker. First the state will be set appropriately. Second the background drawable will be enabled and the standard background disabled. This is done by setting the opacity property to 1.0 for the visible state and to 0.0 for an invisible state. Third the onClick function is set only for the background drawable of the selected marker.
    */

    return function(poiData) {
        if (marker.isSelected) {
        World.onMarkerSelected(marker);

console.log("CLICKED2");var offerDetls = 'architectsdk://' + "|" + retailerName + "|" + offerDescription + "|" + category + "|" + offerEndTime + "|" + duration + "|"; console.log(offerDetls); document.location = offerDetls;
            Marker.prototype.setDeselected(marker);
            console.log(marker.isSelected);
//            console.log(retailerName);
//            var offerDetls = 'architectsdk://' + "|" + retailerName + "|" + offerDescription + "|" + category + "|" + offerEndTime + "|" + duration + "|";
//            console.log(offerDetls);
//            document.location = offerDetls;

console.log(poiData.title);
        } else {
        console.log("marker not selected");
            Marker.prototype.setSelected(marker);
            try {
                    console.log("trying for workld onmarker selected");

                World.onMarkerSelected(marker);
            } catch (err) {
                alert(err);
            }

        }

        return true;
    };
};

Marker.prototype.setSelected = function(marker,poiData) {

    marker.isSelected = true;

    marker.markerDrawable_idle.opacity = 0.0;
    marker.markerDrawable_selected.opacity = 1.0;

    marker.markerDrawable_idle.onClick = console.log("idle onClicked");
//    function(poiData){var offerDetls =
//     'architectsdk://' + "|" + retailerName + "|" + offerDescription + "|" + category + "|" + offerEndTime + "|" + duration + "|";
//                                                                        console.log(offerDetls);var n = poiData.title.split("|")[1];
//                                                                        console.log(this.poiData.title);
//                                                                        console.log(n);
//                                                                        console.log(poiData.title.split("|")[0]);
//                                                                        document.location = offerDetls;};
    marker.markerDrawable_selected.onClick = console.log("selected On click fired");
//    Marker.prototype.getOnClickTrigger(marker);
};

Marker.prototype.setDeselected = function(marker) {

    marker.isSelected = false;

    marker.markerDrawable_idle.opacity = 1.0;
    marker.markerDrawable_selected.opacity = 0.0;

    marker.markerDrawable_idle.onClick = Marker.prototype.getOnClickTrigger(marker);
    marker.markerDrawable_selected.onClick = null;
};

// will truncate all strings longer than given max-length "n". e.g. "foobar".trunc(3) -> "foo..."
String.prototype.trunc = function(n) {
    return this.substr(0, n - 1) + (this.length > n ? '...' : '');
};