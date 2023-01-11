import {createRouter, createWebHistory} from 'vue-router';
import Cookies from "js-cookie";
const routes = [
    {
        path: '/login',
        name: 'Login',
        component: () => import('../views/backstage/Login.vue')
    },
    {
        path: '/backstage/',
        name: 'BackstageTemplate',
        component: () => import('../views/BackstageTemplate.vue'),
        children: [
            {
                path: '',
                name: 'Home',
                component: () => import('../views/backstage/Home.vue')
            },
            {
                path: 'user',
                name: 'User',
                component: () => import('../views/backstage/User.vue')
            },
            {
                path: 'admin',
                name: 'Admin',
                component: () => import('../views/backstage/Admin.vue')
            },
            {
                path: 'category',
                name: 'Category',
                component: () => import('../views/backstage/Category.vue')
            },
            {
                path: 'facilities',
                name: 'Facilities',
                component: () => import('../views/backstage/Facility.vue')
            },
            {
                path: 'message',
                name: 'Message',
                component: () => import('../views/backstage/Message.vue')
            }
        ]
    },
    {
        path: '/reception/',
        name: 'ReceptionTemplate',
        component: () => import('../views/ReceptionTemplate.vue'),
        children: [
            {
                path: '',
                name: 'ReceptionHome',
                component: () => import('../views/reception/Home.vue')
            },
            {
                path: 'login',
                name: 'ReceptionLogin',
                component: () => import('../views/reception/Login.vue')
            },
            {
                path: 'facilities',
                name: 'ReceptionFacilities',
                component: () => import('../views/reception/Facilities.vue'),
            },
            {
                path: 'facility',
                name: 'ReceptionFacility',
                component: () => import('../views/reception/Facility.vue'),
            }
        ]
    },
    {
      path: "/:catchAll(.*)",
      name: "error",
      component: () => import('../views/error/404.vue')
    }
]

const router = createRouter({
    history: createWebHistory("/"),
    routes: routes,
})

//路由首位
// router.beforeEach((to,from,next) => {
//     if (to.path === '/login') next()
//     const admin = Cookies.get("admin")
//     if (!admin && to.path !== '/login') return next("/login")
//     next()
// })

//输出对象
export default router;