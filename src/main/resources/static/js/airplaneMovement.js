var planeSimulation = {
    start : function() {
        // If we already started return.
        if(this.started){
            return;
        }

        this.started = true;
        this.planeImage = document.getElementById("planeImage");
        this.canvas = document.getElementById('visualAirplaneCanvas');
        this.canvas.width = 1024;
        this.canvas.height = 768;
        this.context = this.canvas.getContext('2d');
        this.intervalLength = 20;
        this.airplanes = [];
        this.interval = setInterval(updatePlanes, this.intervalLength);
    },
    clear : function() {
        this.context.clearRect(0, 0, this.canvas.width, this.canvas.height);
    },
    draw: function(){
        // draw airplanes
        for(var i = 0; i < this.airplanes.length; i++){
            this.airplanes[i].draw(this.context, this.planeImage);
        }
    },
    addPlanes: function(airplanes){
        // empty airplanes
        this.airplanes = [];
        // Fill the airplane array with airplanes
        for(var i = 0; i < airplanes.length; i++){
            var this_plane = new plane(airplanes[i]);
            console.log(this_plane);
            this.airplanes.push(this_plane);
        }
    }

}
// Plane object
function plane(planeObject){
    this.dataObject = planeObject;
    this.speed = planeObject.speed;
    this.referenceLocation = {
        x: planeObject.travellingFrom.xCoordinate,
        y: planeObject.travellingFrom.yCoordinate,
    };
    this.destination = {
        x: planeObject.travellingTo.xCoordinate,
        y: planeObject.travellingTo.yCoordinate,
    };
    this.size = 40;
    this.currentJourneyProgress = planeObject.currentJourneyProgress;
    this.currentTravelDistance = planeObject.currentTravelDistance;
    this.color = "#ff0000"; // Set to pure red
    // Get movementSpeedPerSecond by normalizing vector and multiplying by speed divided by frames per sec.

    var framesPerSec = (1000 / planeSimulation.intervalLength);
    var dx = ((this.destination.x - this.referenceLocation.x) / this.currentTravelDistance);
    var dy = ((this.destination.y - this.referenceLocation.y) / this.currentTravelDistance);

    this.movementPerTick = {
        x: (this.speed * dx) / framesPerSec,
        y: (this.speed * dy) / framesPerSec
    };

    // Keep in touch with server progress
    var progress = this.currentJourneyProgress / this.currentTravelDistance;
    this.x = Math.round(this.referenceLocation.x + progress * (this.destination.x - this.referenceLocation.x));
    this.y = Math.round(this.referenceLocation.y + progress * (this.destination.y - this.referenceLocation.y));

    this.rotation = Math.round(Math.atan2( this.movementPerTick.y, this.movementPerTick.x ) * 180 / Math.PI) + 45;

    this.draw = function(context, planeImage){

        context.save();

        // update pos
        this.x += this.movementPerTick.x;
        this.y += this.movementPerTick.y;

        // Rotate canvas
        context.translate(this.x + this.size / 2, this.y + this.size / 2);
        context.rotate(this.rotation * Math.PI / 180);

        // Display as rect for now.
        context.drawImage(planeImage, -this.size / 2, -this.size / 2, this.size, this.size);
        context.restore();

        // Request server update when we reach our target
        if(this.checkPosition()){
            getVisualAirplanes();
        }
    }

    // Check whether our destination has been reached.
    this.checkPosition = function(){
        var x_satisfies = (this.destination.x <= this.referenceLocation.x) ? (this.x <= this.destination.x) : (this.x > this.destination.x);
        var y_satisfies = (this.destination.y <= this.referenceLocation.y) ? (this.y <= this.destination.y) : (this.y > this.destination.y);
        return (x_satisfies && y_satisfies);
    }
}

// Init game
function initializeCanvas(){
    planeSimulation.start();
}

function updatePlanes(){
    planeSimulation.clear();
    planeSimulation.draw();
}