import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Hi1 from '@/components/Hi1'
import Hi2 from '@/components/Hi2'
import Params from '@/components/Params'
import Error from '@/components/Error'

Vue.use(Router)

export default new Router({
  //mode的设置  history：去掉连接#   hash：连接带#
  mode:'history',
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld,
      alias:'/home'
    },
    // {
    //   path: '/kean',
    //   name: 'HelloWorld',
    //   components: {
    //     default: HelloWorld,
    //     left: Hi2,
    //     right: Hi1
    //   }
    // },
    {
      //以括号的形式添加正则 表示只允许传数字
      path: '/params/:newsId(\\d+)/:newsTitle',
      component: Params,
      // beforeEnter: (to, from, next)=>{
      //   console.log('我进入了params模板');
      //   console.log(to);
      //   console.log(from);
      //   //允许跳转 next(true);
      //   //不允许跳转 next(false);
      //   // 跳转到主页  next({path:'/home'});
      //   next();
      // }
    },{
      path:'/gohome',
      redirect:'/',//重定向
    },
    {//传递参数的重定向
      path: '/goParams/:newsId(\\d+)/:newsTitle',
      redirect: '/params/:newsId(\\d+)/:newsTitle',
    },
    {//使用alias别名的形式，我们也可以实现类似重定向的效果。
      path:'/hi1',
      component: Hi1,
      alias:'/jspan'
    },
    {
      //404页面的处理
      path:'*',
      component: Error
    }
  ]
})
