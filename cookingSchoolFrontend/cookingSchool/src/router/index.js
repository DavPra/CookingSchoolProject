import { createRouter, createWebHistory } from 'vue-router';
import AdminLayout from '../layouts/AdminLayout.vue';
import UserLayout from '../layouts/UserLayout.vue';
import GuestLayout from '../layouts/GuestLayout.vue';
import HomeView from '../views/guest/HomeView.vue';
import ProfileView from '../views/user/ProfileView.vue';
import UserCourseView from '../views/user/UserCourseView.vue';
import RecipeView from '../views/user/RecipeView.vue';
import AboutView from '../views/guest/AboutView.vue';
import RegistrationView from '../views/guest/RegistrationView.vue';
import AdminCourseView from '../views/admin/AdminCourseView.vue';
import AdminRecipeView from '../views/admin/AdminRecipeView.vue';
import AdminUserView from '../views/admin/AdminUserView.vue';
import GuestCourseView from '../views/guest/GuestCourseView.vue';
import UserAboutView from '../views/user/UserAboutView.vue';
import LoginView from '../views/guest/LoginView.vue';
import AdminHomeView from '@/views/admin/AdminHomeView.vue';
import AdminCourseUserView from '@/views/admin/AdminCourseUserView.vue';
import jwtDecode from 'jwt-decode';

function isLoggedIn() {
  if (!window.localStorage.getItem('accessToken')) {
    router.push('/login');
  } else {
    return true;
  }
}


function isAdmin() {
  const token = window.localStorage.getItem('accessToken');
  const decodedToken = jwtDecode(token);
  if (decodedToken.role === 'ADMIN') {
    return true;
  } else {
    router.push('/user/courses');
  }
}


const routes = [
  {
    path: '/user',
    component: UserLayout,
    children: [
      {
        path: 'profile',
        component: ProfileView,
        beforeEnter: [isLoggedIn],
      },
      {
        path: 'courses',
        component: UserCourseView,
        beforeEnter: [isLoggedIn],
      },
      {
        path: 'recipes',
        component: RecipeView,
        beforeEnter: [isLoggedIn],
      },
      {
        path: 'about',
        component: UserAboutView,
        beforeEnter: [isLoggedIn],
      },
    ],
  },
  {
    path: '/admin',
    component: AdminLayout,
    children: [
      {
        path: '',
        component: AdminHomeView,
        beforeEnter: [isLoggedIn, isAdmin],
      },
      {
        path: 'courses',
        component: AdminCourseView,
        beforeEnter: [isLoggedIn, isAdmin],
      },
      {
        path: 'recipes',
        component: AdminRecipeView,
        beforeEnter: [isLoggedIn, isAdmin],
      },
      {
        path: 'users',
        component: AdminUserView,
        beforeEnter: [isLoggedIn, isAdmin],
      },
      {
        path: 'courseUserView/:courseId',
        name: 'adminCourseUserView',
        component: AdminCourseUserView,
        props: true,
        beforeEnter: [isLoggedIn, isAdmin],
      },
    ],
  },
  {
    path: '',
    component: GuestLayout,
    children: [
      {
        path: '',
        component: HomeView,
      },
      {
        path: 'about',
        component: AboutView,
      },
      {
        path: 'register',
        component: RegistrationView,
      },
      {
        path: 'courses',
        component: GuestCourseView,
      },
      {
        path: 'login',
        component: LoginView,
      },
    ],
  },
];

const router = createRouter({
  routes,
  history: createWebHistory(),
});

export { router };
