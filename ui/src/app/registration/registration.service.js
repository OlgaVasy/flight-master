export default
/* @ngInject */
class RegistrationService {
  constructor ($http, apiUrl) {
    this.$http = $http
    this.apiUrl = apiUrl
  }

 }