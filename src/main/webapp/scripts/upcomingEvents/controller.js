'use strict';

ipponrunningApp.controller('UpComingEventsController', ['$scope', 'events', 'UpComingEvents',
    function ($scope, events, UpComingEvents) {

        $scope.events = events;
}]);