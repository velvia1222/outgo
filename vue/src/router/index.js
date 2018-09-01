import Vue from 'vue'
import Router from 'vue-router'
import Outgoinput from '../components/Outgoinput'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Outgoinput',
      component: Outgoinput
    }
  ]
})
