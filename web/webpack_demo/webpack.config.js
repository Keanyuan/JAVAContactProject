const path = require('path')

module.exports = {
    //入口文件的配置项
    entry: {
        //里面的entery是可以随便写的
        entry: "./src/entry.js",
        //这里我们又引入了一个入口文件
        // entry2: "./src/entry2.js",

    },
    //出口文件的配置项
    output: {
        //打包的路径文件
        path: path.resolve(__dirname, 'dist'),
        //打包的文件名称 //输出的多文件名称  用【】表示
        filename: "[name].js"

        // filename: "bundle.js"
    },
    //模块：例如解读CSS,图片如何转换，压缩
    module: {
        rules: [
            {
                //用于匹配处理文件的扩展名的表达式，这个选项是必须进行配置的；
                test: /\.css$/,
                //loader名称，就是你要使用模块的名称，这个选项也必须进行配置，否则报错；
                use: ['style-loader', 'css-loader']
            //    include/exclude:手动添加必须处理的文件（文件夹）或屏蔽不需要处理的文件（文件夹）（可选）；
            //    query：为loaders提供额外的设置选项（可选）
            }
        ]
    },
    //插件，用于生产模版和各项功能
    plugins: [],
    //配置webpack开发服务功能
    //配置完以后 npm install webpack-dev-server --save-dev
    //npm run server 启动热更新
    devServer: {
        //设置基本目录结构
        contentBase: path.resolve(__dirname, 'dist'),
        //服务器的IP地址，可以使用IP也可以使用localhost
        host: 'localhost',
        //服务端压缩是否开启
        compress: true,
        //配置服务端口号
        port: 1717
    },
}