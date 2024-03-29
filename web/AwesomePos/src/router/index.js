import Vue from 'vue'
import Router from 'vue-router'
import Pos from '@/components/page/Pos'
import Page from '@/components/page/Page'
import Error from '@/components/page/Error'
import FlexBox from '@/components/page/FlexBox'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Pos',
      component: Pos
    },
    {
      path: '/page/:pageName',
      name: 'Page',
      component: Page
    },
    {
      path: '/FlexBox',
      name: 'FlexBox',
      component: FlexBox
    },
    {
      //404页面的处理
      path:'*',
      component: Error
    }

  ]
})
