export default

/* @ngInject */
class BookingController{
	constructor(bookingService){
		
		this.bookingService = bookingService	
		this.origin = ""
		this.destination = ""
		
		}
	 findRoute () {
		    this.route = this.bookingService.getRoute(this.origin, this.destination).then(
		      (success) => {
		    	this.route = success.data		    	
		      },
		      (failure) => {
	        alert('fail')
		      }
		    )
		  }

	}