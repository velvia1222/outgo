module.exports = {
    entry: './src/main/js/app.js',
    output: {
        path: __dirname,
        filename: './src/main/resources/public/js/bundle.js'
    },
    module: {
        rules: [
        ]
    },
    resolve: {
        alias: {
            vue: 'vue/dist/vue.js'
        }
    },
    watchOptions: {
      poll: true
    }
};
