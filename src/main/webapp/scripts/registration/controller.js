'use strict';

ipponrunningApp.controller('RegistrationController', ['$scope', 'resolvedRegistration', 'Registration',
    function ($scope, resolvedRegistration, Registration) {

        $scope.registrations = resolvedRegistration;

        $scope.create = function () {
            Registration.save($scope.registration,
                function () {
                    $scope.registrations = Registration.query();
                    $('#saveRegistrationModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.registration = Registration.get({id: id});
            $('#saveRegistrationModal').modal('show');
        };

        $scope.delete = function (id) {
            Registration.delete({id: id},
                function () {
                    $scope.registrations = Registration.query();
                });
        };

        $scope.clear = function () {
            $scope.registration = {id: "", sampleTextAttribute: "", sampleDateAttribute: ""};
        };
    }]);
