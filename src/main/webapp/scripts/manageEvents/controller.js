'use strict';

ipponrunningApp.controller('ManageEventsController', ['$scope', 'resolvedEvent', 'Event',
    function ($scope, resolvedEvent, Event) {

        $scope.events = resolvedEvent;

        $scope.create = function () {
            Event.save($scope.event,
                function () {
                    $scope.events = Event.query();
                    $('#saveEventModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.event = Event.get({id: id});
            $('#saveEventModal').modal('show');
        };

        $scope.delete = function (id) {
            Event.delete({id: id},
                function () {
                    $scope.events = Event.query();
                });
        };

        $scope.clear = function () {
            $scope.event = {id: ""};
        };
    }]);
