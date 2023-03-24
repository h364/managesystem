const { defineConfig } = require('@vue/cli-service')

const path = require('path');
function resolve(dir) {
  return path.join(__dirname, dir)
}

module.exports = defineConfig({
  transpileDependencies: true
})


module.exports = {
  chainWebpack: (config) => {
    config.resolve.alias
      .set('@', resolve('./src'))
      .set('@components', resolve('./src/components'))
      .set('@views', resolve('./src/views'))
      .set('@assets', resolve('./src/assets'))
      .set('@network', resolve('./src/network'))
  }
}
