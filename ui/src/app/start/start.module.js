import startComponent from './start.component.js'
import startService from './start.service'

export default
  angular
    .module('start.page', [])
    .component('startComponent', startComponent)
    .service('startService', startService)
    .name
