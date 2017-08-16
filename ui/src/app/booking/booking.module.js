import bookingComponent from './booking.component.js'
import bookingService from './booking.service'

export default
  angular
    .module('booking.page', [])
    .component('bookingComponent', bookingComponent)
    .service('bookingService', bookingService)
    .name