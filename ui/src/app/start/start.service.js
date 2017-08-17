export default
/* @ngInject */
class StartService {
    constructor ($http, apiUrl, $localStorage) {
    this.$http = $http
    this.apiUrl = apiUrl
    this.$localStorage = $localStorage
  }

  getAllFlights () {
    return this.$http
      .get(`${this.apiUrl}/flights`)
      .then(result =>{
    		  console.log(result.data)    		  
    		  this.flightsOffered = result.data      
  })
}
  changePage () {
	    if (this.$localStorage.username !== null && this.$localStorage. password !== null) {
	      return {visibility: 'hidden'}
	    } else {
	      return {visibility: 'visible'}
	    }
	  }
	  logOut () {
		  this.$localStorage.username = null
		  this.$localStorage.password = null
	  }

	  loggedInButton () {
	    if(this.$localStorage.username === null && this.$localStorage. password === null){
	      return {visibility: 'hidden'}
	    } else {
	      return {visibility: 'visible'}
	    }
	  }
 }


