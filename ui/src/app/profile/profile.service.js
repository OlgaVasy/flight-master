export default
/* @ngInject */
class ProfileService {
  constructor ($http, apiUrl) {
    this.$http = $http
    this.apiUrl = apiUrl
  }


 }