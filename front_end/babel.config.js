module.exports = {
  // presets: [
  //   '@vue/cli-plugin-babel/preset',
  //   '@babel/preset-env'
  // ],
  // "env": {
  //   "test": {
  //     "presets": ["env"]
  //   }
  // }
  
    // "presets": [
    //   ['@vue/cli-plugin-babel/preset',{}],
    //   ["@babel/preset-env",
    //     {
    //       "modules": false
    //     }
    //   ]
    // ]
    
      // "presets": [
        
      // ["@babel/preset-env",
      //   {
      //     "modules": false
      //   }
      // ]
      // ]
      "presets": [
        ["@babel/preset-env", {
             "targets": { 
                 "node": "current" 
              },
              "modules": false
          }]
      ],
      "plugins": ["transform-es2015-modules-commonjs"]
  
}
