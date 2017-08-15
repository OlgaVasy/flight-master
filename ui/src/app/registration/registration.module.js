import registrationComponent from './registration.component.js'
import registrationService from './registration.service'

export default
  angular
    .module('reg.page', [])
    .component('registrationComponent', registrationComponent)
    .service('registrationService', registrationService)
    .name