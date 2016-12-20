
self.addEventListener('message', function(e) {
var arr = new Array();
console.log(e.data);
var obj = JSON.parse(e.data);
var arr = [];

for(var x in obj){
  arr.push(obj[x]);
}
console.log(JSON.stringify(arr[0]));
// console.log(e.data[0]);

//var n = (e.data[0]).toLowerCase();
//var d = (e.data[1]).toLowerCase();
//for (var i = 0; i < e.data[0].length; i++){
//
//
//                                                                            var n = (e.data[0].toLowerCase();
//                                                                            var d = e.data[1].toLowerCase();
////                                                       	                    console.log(n);
////                                                                            console.log(d);
//
//
//                                                    		    if(n == d){
//                                                                        console.log("Street true");
//                                                                         arr.push(true);
//                                                                    }
//                                                                    else{
//                                                                                                                                              console.log("Street true");
//                                                                      console.log("Street false");
//
////                                                                        e.data[0][i].markerObject.enabled = false;
//                                                                         arr.push(false);
//
//                                                                    }
//
//                                                        setTimeout(n, 10);
//
//
//                                                        console.log(n);
////                                                        console.log(d);
//                                                        console.log(arr[0]);
                                                        console.log(JSON.stringify(arr[1]));
                                                        console.log(JSON.stringify(arr[2]));
  self.postMessage(e.data);
}, false);