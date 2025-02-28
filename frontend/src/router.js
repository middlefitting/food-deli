
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import FrontOrderManager from "./components/listers/FrontOrderCards"
import FrontOrderDetail from "./components/listers/FrontOrderDetail"
import FrontPaymentManager from "./components/listers/FrontPaymentCards"
import FrontPaymentDetail from "./components/listers/FrontPaymentDetail"

import StroeCookingManager from "./components/listers/StroeCookingCards"
import StroeCookingDetail from "./components/listers/StroeCookingDetail"

import RiderRiderManager from "./components/listers/RiderRiderCards"
import RiderRiderDetail from "./components/listers/RiderRiderDetail"


import DashboardView from "./components/DashboardView"
import DashboardViewDetail from "./components/DashboardViewDetail"

export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/fronts/orders',
                name: 'FrontOrderManager',
                component: FrontOrderManager
            },
            {
                path: '/fronts/orders/:id',
                name: 'FrontOrderDetail',
                component: FrontOrderDetail
            },
            {
                path: '/fronts/payments',
                name: 'FrontPaymentManager',
                component: FrontPaymentManager
            },
            {
                path: '/fronts/payments/:id',
                name: 'FrontPaymentDetail',
                component: FrontPaymentDetail
            },

            {
                path: '/stroes/cookings',
                name: 'StroeCookingManager',
                component: StroeCookingManager
            },
            {
                path: '/stroes/cookings/:id',
                name: 'StroeCookingDetail',
                component: StroeCookingDetail
            },

            {
                path: '/riders/riders',
                name: 'RiderRiderManager',
                component: RiderRiderManager
            },
            {
                path: '/riders/riders/:id',
                name: 'RiderRiderDetail',
                component: RiderRiderDetail
            },


            {
                path: '/customers/dashboards',
                name: 'DashboardView',
                component: DashboardView
            },
            {
                path: '/customers/dashboards/:id',
                name: 'DashboardViewDetail',
                component: DashboardViewDetail
            },


    ]
})
