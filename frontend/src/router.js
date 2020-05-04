import Vue from 'vue'
import Router from 'vue-router'
import People from '@/components/People'
import Feed from '@/components/Feed'
import Profile from '@/components/Profile'
import User from '@/components/User'
import Login from '@/components/Login'

import store from './store'

Vue.use(Router);

const router = new Router({
    mode: 'history', // uris without hashes #, see https://router.vuejs.org/guide/essentials/history-mode.html#html5-history-mode
    routes: [
        { path: '/people', component: People,
            meta: {
                requiresAuth: true
            }
        },
        { path: '/feed', component: Feed,
            meta: {
                requiresAuth: true
            }
        },
        { path: '/profile', component: Profile,
            meta: {
                requiresAuth: true
            }
        },
        { path: '/user', component: User },
        { path: '/login', component: Login },

        // otherwise redirect to home
        { path: '*', redirect: '/profile' }
    ]
});

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        // this route requires auth, check if logged in
        // if not, redirect to login page.
        if (!store.getters.isLoggedIn) {
            next({
                path: '/login'
            })
        } else {
            next();
        }
    } else {
        next(); // make sure to always call next()!
    }
});

export default router;