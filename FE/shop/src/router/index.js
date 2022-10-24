import Vue from 'vue'
import VueRouter from 'vue-router'
import MainView from '@/views/MainView'
import LoginView from '@/views/LoginView'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'main',
    component: MainView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
