const path = require('path');

const resolve = dir => {
  return path.join(__dirname, dir);
};

module.exports = {
    publicPath: '/',
    lintOnSave: false,
    productionSourceMap: false,

    devServer:{
        open: true,
        port: 8280,
    },

    chainWebpack: config => {
        config.resolve.alias
        .set('@', resolve('src'))
        .set('_c', resolve('src/components'))
    },

    configureWebpack: {
        entry: {
            main: ["babel-polyfill", '@/main'],
        },
    },
};
