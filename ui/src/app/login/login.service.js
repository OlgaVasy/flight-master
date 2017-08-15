export default
/* @ngInject */
class LoginService {
  constructor ($http, apiUrl) {
    this.$http = $http
    this.apiUrl = apiUrl
  }


 }