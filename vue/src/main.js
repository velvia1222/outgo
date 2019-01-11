import Vue from 'vue'
import router from './router'
import Buefy from 'buefy'
import 'buefy/dist/buefy.css'
import App from './App.vue'

Vue.use(Buefy)

new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
