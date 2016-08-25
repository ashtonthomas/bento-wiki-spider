require('bendystraw')({
  paths: {
    scripts: 'app'
  },

  angular: {
    enabled: true,
    templateModule: 'bendy.templates',
    envModule: 'bendy.env',
  },

  browserSync: {
    open: false
  },

  images: {
    bower: ['bower_components/rolodex/**/*']
  },

  deploy: {
    cache: true
  },

  styles: {
    sourcemaps: false,
  },

  build: {
    uncss: true
  },

  scripts: {
    babel: true
  },
})
