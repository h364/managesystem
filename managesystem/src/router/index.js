import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    redirect: '/login',
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/regist',
    name: 'Regist',
    component: () => import('../views/Regist.vue')
  },
  {
    path: '/manage',
    name: 'Manage',
    redirect: '/manage/home',
    component: () => import('../views/Manage.vue'),
    children: [
      { path: 'user', component: () => import('../views/User.vue') },
      { path: 'home', component: () => import('../views/Home.vue') },
      { path: 'file', component: () => import('../views/File.vue') },
      { path: 'person', component: () => import('../views/Person.vue') }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
