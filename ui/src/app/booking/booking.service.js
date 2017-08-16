export default
/* @ngInject */
class BookingService {
  constructor ($http, apiUrl) {
    this.$http = $http
    this.apiUrl = apiUrl   
    this.message = ""
    this.route = null
  }

  getRoute (origin, destination) {
    return this.$http({
      method: 'GET',
      url: `${this.apiUrl}/flights/route`,
      params: {origin: origin, destination: destination}     
    }).then((response) => {      	
        return this.route = response.data      
      }, (response) => {
    	  console.log("fail")  
    	  console.log(response.data)   
    	  return this.route = response.data
    	  
      }) 
 }
  hideTable () {
	    if (this.route == null) {
	      return {visibility: 'hidden'}
	    } else {
	    	console.log(this.route)
	      return {visibility: 'visible'}
	    }
	  }}


