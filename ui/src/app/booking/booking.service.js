export default
/* @ngInject */
class BookingService {
  constructor ($http, apiUrl, $localStorage) {
    this.$http = $http
    this.apiUrl = apiUrl   
    this.message = ""
    this.route = null  
    this.$localStorage = $localStorage
    
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
  bookAFlight (route){
	  route = this.route
	  let newFlights = {
	    		credentials: {
	    	        password: this.$localStorage.password,
	    	        username: this.$localStorage.username
	    	         },
	    	      flight: route
	    	    }	 
	  console.log(newFlights)
	    return this.$http({
	      method: 'POST',
	      url: `${this.apiUrl}/bookedFlight/bookedFlights`,
	      data: newFlights,
	      params: {flight: route}
	    }).then(
	      (success) => {
	        console.log('Flight has been booked!') 
	        console.log(newFlights)
	      },
	      (failure) => {
	    	  
	      }
	    )
	  }
  
  hideTable () {
	    if (this.route === null || this.route === undefined) {
	      return {visibility: 'hidden'}
	    } else {
	    	console.log(this.route)
	      return {visibility: 'visible'}
	    }
	  }
  logOut () {
	  this.$localStorage.username = null
	  this.$localStorage.password = null				 
      this.$state.go('welcome')				  
}}


