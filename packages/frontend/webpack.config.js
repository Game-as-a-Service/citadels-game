const webpack = require('webpack')
const path = require('path')
const MiniCssExtractPlugin = require('mini-css-extract-plugin')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const { CleanWebpackPlugin } = require('clean-webpack-plugin')
const HtmlWebpackHarddiskPlugin = require('html-webpack-harddisk-plugin')

module.exports = function () {
  const isProduction = process.env.NODE_ENV
  return {
    entry: './js/index.js',
    output: {
      path: path.resolve(__dirname, 'build'),
      filename: isProduction ? 'js/bundle.[chunkhash].js' : 'js/bundle.js',
      chunkFilename: isProduction ? 'js/[id].[chunkhash].js' : 'js/[name].js'
    },
    module: {
      rules: [
        {
          test: /\.(js|jsx|ts|tsx)$/,
          use: 'awesome-typescript-loader',
          exclude: /node_modules/
        },
        // {
        //   test: /\.(js|jsx)$/,
        //   loader: 'esbuild-loader',
        //   options: {
        //     loader: 'jsx',
        //     target: 'es2015'
        //   },
        //   exclude: /node_modules/
        // },
        {
          test: /\.(sc|c)ss$/,
          use: [
            isProduction ? MiniCssExtractPlugin.loader : 'style-loader',
            {
              loader: 'css-loader',
              options: {
                url: true
              }
            },
            'postcss-loader', // autoprefix
            'sass-loader'
          ]
        },
        {
          test: /\.(png|jpg|gif)$/,
          type: 'asset',
          parser: {
            dataUrlCondition: {
              maxSize: 1024
            }
          },
          generator: {
            filename: './img/[hash:7][ext]',
            publicPath: (pathData, assetInfo) => {
              return pathData.module.rawRequest.indexOf('/css/') > -1 ? '.' : ''
            }
          }
        },
        {
          test: /\.svg$/i,
          resourceQuery: /url/,
          type: 'asset',
          generator: {
            filename: './img/[hash:7]/[ext]',
            publicPath: (pathData, assetInfo) => {
              return pathData.module.rawRequest.indexOf('/css/') > -1 ? '.' : '';
            },
          },
        },
        {
          test: /\.svg$/i,
          issuer: /\.[jt]sx?$/, 
          
          resourceQuery: { not: [/url/] },
          use: ['@svgr/webpack'],
          generator: {
            filename: './img/[hash:7]/[ext]',
            publicPath: (pathData, assetInfo) => {
              return pathData.module.rawRequest.indexOf('/css/') > -1 ? '.' : '';
            },
          }
        }
      ]
    },
    resolve: {
      extensions: ['.js', '.jsx', '.ts', '.tsx']
    },
    plugins: [
      // 提取CSS文件, 和style-loader無法共存
      new MiniCssExtractPlugin({
        filename: isProduction ? 'css/index.[chunkhash].css' : 'css/index.css',
        experimentalUseImportModule: false // 避免路徑錯誤
      }),
      // 自動加入module
      new webpack.ProvidePlugin({
        React: 'react',
        ReactDom: 'react-dom',
        PropTypes: 'prop-types'
      }),
      new HtmlWebpackPlugin({
        template: 'src/template/index.html'
        // templateParameters: {
        //   htmlWebpackPlugin: {
        //     tags: {
        //       baseTag: !isProduction ? '<base href="/">' : '<base href="' + basenameConfig.basename + '/">',
        //       ...htmlWebpackPluginTags
        //     }
        //   }
        // }
      }),
      new CleanWebpackPlugin({
        cleanStaleWebpackAssets: false,
        cleanOnceBeforeBuildPatterns: [
          '**/js/*',
          '!**/js/lib/**',
          '**/css/*',
          '!**/css/lib/**'
        ]
      }),
      // 使用devserver 時依舊會產生html
      new HtmlWebpackHarddiskPlugin()
    ],
    devtool: isProduction ? false : 'source-map',
    mode: isProduction ? 'production' : 'development',
    devServer: {
      port: 3001,
      hot: true,
      static: {
        directory: path.resolve(__dirname, 'build'),
        publicPath: '/',
      },
      historyApiFallback: true,
    }
  }
}