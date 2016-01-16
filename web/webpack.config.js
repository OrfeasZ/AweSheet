var path = require('path');
var webpack = require('webpack');

module.exports = {
    entry: [],
    output: {
        path: path.join(__dirname, 'dist'),
        filename: 'bundle.js',
        publicPath: '/static/'
    },
    plugins: [],
    module: {
        loaders: [{
            test: /\.js$/,
            loaders: [ 'babel' ],
            exclude: /node_modules/,
            include: __dirname
        }, {
            test: /\.json$/,
            loaders: [ 'json' ],
            exclude: /node_modules/,
            include: __dirname
        }]
    }
};

if (process.env.NODE_ENV === 'production')
{
    module.exports.plugins.push(new webpack.optimize.UglifyJsPlugin({ output: { comments: false }, sourceMap: false }));
    module.exports.plugins.push(new webpack.optimize.OccurenceOrderPlugin());
    module.exports.plugins.push(new webpack.optimize.DedupePlugin());
}
else
{
    module.exports.devtool = 'cheap-module-eval-source-map';

    module.exports.entry.push('webpack-hot-middleware/client');

    module.exports.plugins.push(new webpack.HotModuleReplacementPlugin());
    module.exports.plugins.push(new webpack.NoErrorsPlugin());
}

module.exports.entry.push('./src/index');
