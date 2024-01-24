import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/application/HomeView.vue'
import NewSongView from '@/views/application/NewSongView.vue'
import DetailView from '@/views/application/DetailView.vue'
import ApplicationLayout from '@/layouts/ApplicationLayout.vue'
import AuthenticationLayout from '@/layouts/AuthenticationLayout.vue'
import LoginView from '@/views/authentication/LoginView.vue'
import RegistrationView from '@/views/authentication/RegistrationView.vue'

const routes = [
  {
    path: '/',
    component: AuthenticationLayout,
    children: [
      {
        path: '',
        component: LoginView
      },
      {
        path: '/register',
        component: RegistrationView
      }
    ]
  },
  {
    path: '/dashboard',
    component: ApplicationLayout,
    children: [
      {
        path: '',
        component: HomeView
      },
      {
        path: '/new-song',
        component: NewSongView
      },
      {
        path: '/song/:songId',
        component: DetailView
      }
    ]
  }
]

export const router = createRouter({
  history: createWebHistory(),
  routes
})
