import Vue from 'vue'
import Router from 'vue-router'
import Outgoinput from '../components/Outgoinput'
import Outgolist from '../components/Outgolist'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Outgoinput',
      component: Outgoinput
    },
    {
      path: '/outgoes',
      name: 'Outgolist',
      component: Outgolist
    }
  ]
})
