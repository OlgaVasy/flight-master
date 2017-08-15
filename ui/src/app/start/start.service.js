export default
/* @ngInject */
class StartService {
  constructor ($http, apiUrl) {
    this.$http = $http
    this.apiUrl = apiUrl
  }

  getAllFlights () {
    return this.$http
      .get(`${this.apiUrl}/flights`)
      .then(result =>{
    		  console.log(result.data)    		  
    		  this.flightsOffered = result.data      
  })
}
 }


